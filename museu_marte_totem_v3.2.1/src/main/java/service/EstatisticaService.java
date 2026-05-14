package service;

import java.text.DecimalFormat;
import java.util.List;
import models.RoboExplorador;
import pesquisa.DadosEstatisticas;
import pesquisa.Pergunta;
import repository.EstatisticasRepository;

/**
 * Camada de regra de negocio das estatisticas.
 */
public class EstatisticaService {

    private final EstatisticasRepository repository;
    private final List<RoboExplorador> listaRobos;
    private final List<Pergunta> listaPerguntas;
    private final DadosEstatisticas dados;

    /**
     * Recebe os catalogos e carrega as estatisticas ja salvas.
     */
    public EstatisticaService(EstatisticasRepository repository,
                              List<RoboExplorador> listaRobos,
                              List<Pergunta> listaPerguntas) {
        this.repository = repository;
        this.listaRobos = listaRobos;
        this.listaPerguntas = listaPerguntas;
        // Ao iniciar o service, carrega do arquivo tudo que ja foi salvo antes.
        this.dados = repository.carregar(listaRobos.size(), listaPerguntas.size(), 4);
    }

    /**
     * Registra que um robo foi aberto na tela de detalhes.
     */
    public void registrarVisualizacaoRobo(int indice) {
        int[] visualizacoesRobos = dados.getVisualizacoesRobos();
        if (indice >= 0 && indice < visualizacoesRobos.length) {
            // Cada abertura da tela de detalhes conta como uma visualizacao.
            visualizacoesRobos[indice]++;
            salvar();
        }
    }

    /**
     * Registra a nota dada para um robo especifico.
     */
    public void registrarNotaRobo(int indice, int nota) {
        int[] somaNotasRobos = dados.getSomaNotasRobos();
        int[] quantidadeNotasRobos = dados.getQuantidadeNotasRobos();
        if (indice >= 0 && indice < somaNotasRobos.length && nota >= 1 && nota <= 5) {
            // Guarda a soma e a quantidade para calcular a media depois.
            somaNotasRobos[indice] += nota;
            quantidadeNotasRobos[indice]++;
            salvar();
        }
    }

    /**
     * Registra as respostas e a pontuacao final de um questionario.
     */
    public void registrarQuestionario(int[] respostas, int pontuacao) {
        // Registra um questionario completo e acumula a pontuacao total.
        dados.setTotalQuestionariosConcluidos(dados.getTotalQuestionariosConcluidos() + 1);
        dados.setSomaPontuacaoQuestionarios(dados.getSomaPontuacaoQuestionarios() + pontuacao);

        // Conta quantas vezes cada alternativa foi marcada em cada pergunta.
        int[][] contagemRespostasPerguntas = dados.getContagemRespostasPerguntas();
        for (int i = 0; i < respostas.length && i < contagemRespostasPerguntas.length; i++) {
            int resposta = respostas[i];
            if (resposta >= 0 && resposta < contagemRespostasPerguntas[i].length) {
                contagemRespostasPerguntas[i][resposta]++;
            }
        }

        salvar();
    }

    /**
     * Registra a avaliacao geral da experiencia no final da visita.
     */
    public void registrarNotaFinal(int nota) {
        int[] contagemNotasFinais = dados.getContagemNotasFinais();
        if (nota >= 1 && nota <= 5) {
            // A nota final representa a avaliacao geral da experiencia.
            dados.setSomaNotasFinais(dados.getSomaNotasFinais() + nota);
            contagemNotasFinais[nota - 1]++;
            salvar();
        }
    }

    /**
     * Calcula a media de estrelas de um robo.
     */
    public double calcularMediaRobo(int indice) {
        int[] somaNotasRobos = dados.getSomaNotasRobos();
        int[] quantidadeNotasRobos = dados.getQuantidadeNotasRobos();
        if (indice < 0 || indice >= quantidadeNotasRobos.length || quantidadeNotasRobos[indice] == 0) {
            // Sem votos, a media fica 0 para evitar divisao por zero.
            return 0.0;
        }
        return (double) somaNotasRobos[indice] / (double) quantidadeNotasRobos[indice];
    }

    /**
     * Calcula a media geral das notas finais da experiencia.
     */
    public double calcularMediaNotaFinal() {
        int totalNotas = 0;
        for (int valor : dados.getContagemNotasFinais()) {
            totalNotas += valor;
        }
        if (totalNotas == 0) {
            return 0.0;
        }
        return (double) dados.getSomaNotasFinais() / (double) totalNotas;
    }

    /**
     * Calcula a media de acertos dos questionarios respondidos.
     */
    public double calcularMediaAcertosQuestionario() {
        if (dados.getTotalQuestionariosConcluidos() == 0) {
            return 0.0;
        }
        return (double) dados.getSomaPontuacaoQuestionarios()
                / (double) dados.getTotalQuestionariosConcluidos();
    }

    /**
     * Monta o texto completo exibido na tela de relatorio.
     */
    public String gerarRelatorioTexto() {
        // O service monta o texto final porque ele conhece os dados e as regras do relatorio.
        DecimalFormat formato = new DecimalFormat("0.00");
        String quebra = System.lineSeparator();
        StringBuilder relatorio = new StringBuilder();

        relatorio.append("RELATORIO ESTATISTICO DA EXPOSICAO").append(quebra);
        relatorio.append("====================================").append(quebra).append(quebra);

        relatorio.append("1. VISITAS E QUESTIONARIOS").append(quebra);
        relatorio.append("- Total de questionarios concluidos: ")
                .append(dados.getTotalQuestionariosConcluidos()).append(quebra);
        relatorio.append("- Media de acertos no quiz: ")
                .append(formato.format(calcularMediaAcertosQuestionario()))
                .append(" de 5").append(quebra).append(quebra);

        relatorio.append("2. VISUALIZACOES POR ROBO").append(quebra);
        int[] visualizacoesRobos = dados.getVisualizacoesRobos();
        for (int i = 0; i < listaRobos.size(); i++) {
            relatorio.append("- ")
                    .append(listaRobos.get(i).getNome())
                    .append(": ")
                    .append(visualizacoesRobos[i])
                    .append(" visualizacao(oes)").append(quebra);
        }
        relatorio.append(quebra);

        relatorio.append("3. AVALIACAO DE CADA ROBO").append(quebra);
        int[] quantidadeNotasRobos = dados.getQuantidadeNotasRobos();
        for (int i = 0; i < listaRobos.size(); i++) {
            relatorio.append("- ")
                    .append(listaRobos.get(i).getNome())
                    .append(": media ")
                    .append(formato.format(calcularMediaRobo(i)))
                    .append(" estrela(s)")
                    .append(" | total de notas: ")
                    .append(quantidadeNotasRobos[i]).append(quebra);
        }
        relatorio.append(quebra);

        relatorio.append("4. AVALIACAO FINAL DA EXPERIENCIA").append(quebra);
        relatorio.append("- Media geral da experiencia: ")
                .append(formato.format(calcularMediaNotaFinal()))
                .append(" estrela(s)").append(quebra);
        int[] contagemNotasFinais = dados.getContagemNotasFinais();
        for (int i = 0; i < contagemNotasFinais.length; i++) {
            relatorio.append("- Nota ")
                    .append(i + 1)
                    .append(": ")
                    .append(contagemNotasFinais[i])
                    .append(" voto(s)").append(quebra);
        }
        relatorio.append(quebra);

        relatorio.append("5. RESPOSTAS DO QUESTIONARIO").append(quebra);
        int[][] contagemRespostasPerguntas = dados.getContagemRespostasPerguntas();
        for (int i = 0; i < listaPerguntas.size(); i++) {
            relatorio.append(listaPerguntas.get(i).getEnunciado()).append(quebra);
            String[] opcoes = listaPerguntas.get(i).getOpcoes();
            for (int j = 0; j < opcoes.length; j++) {
                relatorio.append("   - ")
                        .append(opcoes[j])
                        .append(": ")
                        .append(contagemRespostasPerguntas[i][j])
                        .append(" marcacao(oes)").append(quebra);
            }
            relatorio.append(quebra);
        }

        relatorio.append("6. ROBO MAIS VISUALIZADO").append(quebra);
        relatorio.append("- ").append(obterRoboMaisVisualizado()).append(quebra).append(quebra);

        relatorio.append("Observacao: os dados agora sao salvos em arquivo local.").append(quebra);
        relatorio.append("Arquivo: ").append(System.getProperty("user.home"))
                .append("\\museu-marte-estatisticas.properties");

        return relatorio.toString();
    }

    /**
     * Retorna o nome do robo com mais visualizacoes.
     */
    private String obterRoboMaisVisualizado() {
        int maior = -1;
        int indiceMaior = -1;
        int[] visualizacoesRobos = dados.getVisualizacoesRobos();

        for (int i = 0; i < visualizacoesRobos.length; i++) {
            if (visualizacoesRobos[i] > maior) {
                maior = visualizacoesRobos[i];
                indiceMaior = i;
            }
        }

        if (indiceMaior == -1 || maior <= 0) {
            return "Ainda nao ha visualizacoes registradas.";
        }

        return listaRobos.get(indiceMaior).getNome() + " com " + maior + " visualizacao(oes).";
    }

    /**
     * Salva os dados atuais usando o repository configurado.
     */
    private void salvar() {
        // Centraliza a gravacao para todo registro ja persistir imediatamente.
        repository.salvar(dados);
    }
}

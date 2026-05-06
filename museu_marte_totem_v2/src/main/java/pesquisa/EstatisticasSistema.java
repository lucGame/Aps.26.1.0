package pesquisa;

    import java.text.DecimalFormat;
    import java.util.List;
    import models.CatalogoRobos;
    import models.RoboExplorador;

    /**
     * Classe singleton que guarda todas as estatísticas em memória.
     *
     * Importante: não salva nada em disco.
     * Tudo é mantido apenas enquanto o programa estiver aberto.
     */
    public class EstatisticasSistema {

        protected static EstatisticasSistema instancia;

        protected int totalQuestionariosConcluidos;
        protected int somaPontuacaoQuestionarios;
        protected int somaNotasFinais;
        protected int[] contagemNotasFinais;

        protected int[] visualizacoesRobos;
        protected int[] somaNotasRobos;
        protected int[] quantidadeNotasRobos;

        protected int[][] contagemRespostasPerguntas;

        protected List<RoboExplorador> listaRobos;
        protected List<Pergunta> listaPerguntas;

        /**
         * Construtor do singleton.
         */
        protected EstatisticasSistema() {
            listaRobos = CatalogoRobos.getListaRobos();
            listaPerguntas = CatalogoPerguntas.getListaPerguntas();

            contagemNotasFinais = new int[5];
            visualizacoesRobos = new int[listaRobos.size()];
            somaNotasRobos = new int[listaRobos.size()];
            quantidadeNotasRobos = new int[listaRobos.size()];
            contagemRespostasPerguntas = new int[listaPerguntas.size()][4];
        }

        public static EstatisticasSistema getInstancia() {
            if (instancia == null) {
                instancia = new EstatisticasSistema();
            }
            return instancia;
        }

        public void registrarVisualizacaoRobo(int indice) {
            if (indice >= 0 && indice < visualizacoesRobos.length) {
                visualizacoesRobos[indice]++;
            }
        }

        public void registrarNotaRobo(int indice, int nota) {
            if (indice >= 0 && indice < somaNotasRobos.length && nota >= 1 && nota <= 5) {
                somaNotasRobos[indice] += nota;
                quantidadeNotasRobos[indice]++;
            }
        }

        public void registrarQuestionario(int[] respostas, int pontuacao) {
            totalQuestionariosConcluidos++;
            somaPontuacaoQuestionarios += pontuacao;

            for (int i = 0; i < respostas.length && i < contagemRespostasPerguntas.length; i++) {
                int resposta = respostas[i];
                if (resposta >= 0 && resposta < contagemRespostasPerguntas[i].length) {
                    contagemRespostasPerguntas[i][resposta]++;
                }
            }
        }

        public void registrarNotaFinal(int nota) {
            if (nota >= 1 && nota <= 5) {
                somaNotasFinais += nota;
                contagemNotasFinais[nota - 1]++;
            }
        }

        public double calcularMediaRobo(int indice) {
            if (indice < 0 || indice >= quantidadeNotasRobos.length) {
                return 0.0;
            }
            if (quantidadeNotasRobos[indice] == 0) {
                return 0.0;
            }
            return (double) somaNotasRobos[indice] / (double) quantidadeNotasRobos[indice];
        }

        public double calcularMediaNotaFinal() {
            int totalNotas = 0;
            for (int i = 0; i < contagemNotasFinais.length; i++) {
                totalNotas += contagemNotasFinais[i];
            }
            if (totalNotas == 0) {
                return 0.0;
            }
            return (double) somaNotasFinais / (double) totalNotas;
        }

        public double calcularMediaAcertosQuestionario() {
            if (totalQuestionariosConcluidos == 0) {
                return 0.0;
            }
            return (double) somaPontuacaoQuestionarios / (double) totalQuestionariosConcluidos;
        }

        public String gerarRelatorioTexto() {
            DecimalFormat formato = new DecimalFormat("0.00");
            StringBuilder relatorio = new StringBuilder();

            relatorio.append("RELATÓRIO ESTATÍSTICO DA EXPOSIÇÃO");
            relatorio.append("====================================");

            relatorio.append("1. VISITAS E QUESTIONÁRIOS");
            relatorio.append("- Total de questionários concluídos: ").append(totalQuestionariosConcluidos).append("");
            relatorio.append("- Média de acertos no quiz: ")
                     .append(formato.format(calcularMediaAcertosQuestionario()))
                     .append(" de 5");

            relatorio.append("2. VISUALIZAÇÕES POR ROBÔ");
            for (int i = 0; i < listaRobos.size(); i++) {
                relatorio.append("- ")
                         .append(listaRobos.get(i).getNome())
                         .append(": ")
                         .append(visualizacoesRobos[i])
                         .append(" visualização(ões)");
            }
            relatorio.append("");

            relatorio.append("3. AVALIAÇÃO DE CADA ROBÔ");
            for (int i = 0; i < listaRobos.size(); i++) {
                relatorio.append("- ")
                         .append(listaRobos.get(i).getNome())
                         .append(": média ")
                         .append(formato.format(calcularMediaRobo(i)))
                         .append(" estrela(s)")
                         .append(" | total de notas: ")
                         .append(quantidadeNotasRobos[i])
                         .append("");
            }
            relatorio.append("");

            relatorio.append("4. AVALIAÇÃO FINAL DA EXPERIÊNCIA");
            relatorio.append("- Média geral da experiência: ")
                     .append(formato.format(calcularMediaNotaFinal()))
                     .append(" estrela(s)");
            for (int i = 0; i < contagemNotasFinais.length; i++) {
                relatorio.append("- Nota ")
                         .append(i + 1)
                         .append(": ")
                         .append(contagemNotasFinais[i])
                         .append(" voto(s)");
            }
            relatorio.append("");

            relatorio.append("5. RESPOSTAS DO QUESTIONÁRIO");
            for (int i = 0; i < listaPerguntas.size(); i++) {
                relatorio.append(listaPerguntas.get(i).getEnunciado()).append("");
                String[] opcoes = listaPerguntas.get(i).getOpcoes();
                for (int j = 0; j < opcoes.length; j++) {
                    relatorio.append("   - ")
                             .append(opcoes[j])
                             .append(": ")
                             .append(contagemRespostasPerguntas[i][j])
                             .append(" marcação(ões)");
                }
                relatorio.append("");
            }

            relatorio.append("6. ROBÔ MAIS VISUALIZADO");
            relatorio.append("- ").append(obterRoboMaisVisualizado()).append("");

            relatorio.append("Observação: todos os dados ficam apenas em memória.");
            relatorio.append("Ao fechar o programa, as estatísticas são reiniciadas.");

            return relatorio.toString();
        }

        protected String obterRoboMaisVisualizado() {
            int maior = -1;
            int indiceMaior = -1;

            for (int i = 0; i < visualizacoesRobos.length; i++) {
                if (visualizacoesRobos[i] > maior) {
                    maior = visualizacoesRobos[i];
                    indiceMaior = i;
                }
            }

            if (indiceMaior == -1 || maior <= 0) {
                return "Ainda não há visualizações registradas.";
            }

            return listaRobos.get(indiceMaior).getNome() + " com " + maior + " visualização(ões).";
        }
    }

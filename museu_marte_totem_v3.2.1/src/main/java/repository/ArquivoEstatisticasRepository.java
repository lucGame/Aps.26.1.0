package repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import pesquisa.DadosEstatisticas;

/**
 * Repository simples baseado em arquivo .properties.
 *
 * O arquivo fica fora do codigo-fonte para que as estatisticas continuem
 * existindo mesmo depois que o programa for fechado.
 */
public class ArquivoEstatisticasRepository implements EstatisticasRepository {

    private static final String NOME_ARQUIVO = "museu-marte-estatisticas.properties";

    private final File arquivo;

    /**
     * Define o local do arquivo onde as estatisticas serao guardadas.
     */
    public ArquivoEstatisticasRepository() {
        // Salva o arquivo na pasta do usuario, fora da pasta do projeto.
        String pastaUsuario = System.getProperty("user.home");
        this.arquivo = new File(pastaUsuario, NOME_ARQUIVO);
    }

    /**
     * Le o arquivo .properties e monta o objeto com as estatisticas.
     */
    @Override
    public DadosEstatisticas carregar(int quantidadeRobos, int quantidadePerguntas, int quantidadeOpcoes) {
        // Comeca com tudo zerado. Se o arquivo existir, os valores serao preenchidos abaixo.
        DadosEstatisticas dados = new DadosEstatisticas(quantidadeRobos, quantidadePerguntas, quantidadeOpcoes);

        if (!arquivo.exists()) {
            return dados;
        }

        // Properties e um formato simples de chave=valor, suficiente para este projeto.
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(arquivo)) {
            properties.load(input);
        } catch (IOException e) {
            // Se nao conseguir ler o arquivo, o sistema continua com estatisticas zeradas.
            return dados;
        }

        dados.setTotalQuestionariosConcluidos(lerInteiro(properties, "questionarios.total"));
        dados.setSomaPontuacaoQuestionarios(lerInteiro(properties, "questionarios.somaPontuacao"));
        dados.setSomaNotasFinais(lerInteiro(properties, "notasFinais.soma"));

        carregarVetor(properties, "notasFinais.contagem", dados.getContagemNotasFinais());
        carregarVetor(properties, "robos.visualizacoes", dados.getVisualizacoesRobos());
        carregarVetor(properties, "robos.somaNotas", dados.getSomaNotasRobos());
        carregarVetor(properties, "robos.quantidadeNotas", dados.getQuantidadeNotasRobos());
        carregarMatriz(properties, "perguntas.respostas", dados.getContagemRespostasPerguntas());

        return dados;
    }

    /**
     * Grava as estatisticas atuais no arquivo .properties.
     */
    @Override
    public void salvar(DadosEstatisticas dados) {
        // Transforma os dados em pares chave=valor para gravar no arquivo .properties.
        Properties properties = new Properties();

        properties.setProperty("questionarios.total", String.valueOf(dados.getTotalQuestionariosConcluidos()));
        properties.setProperty("questionarios.somaPontuacao", String.valueOf(dados.getSomaPontuacaoQuestionarios()));
        properties.setProperty("notasFinais.soma", String.valueOf(dados.getSomaNotasFinais()));
        properties.setProperty("notasFinais.contagem", formatarVetor(dados.getContagemNotasFinais()));
        properties.setProperty("robos.visualizacoes", formatarVetor(dados.getVisualizacoesRobos()));
        properties.setProperty("robos.somaNotas", formatarVetor(dados.getSomaNotasRobos()));
        properties.setProperty("robos.quantidadeNotas", formatarVetor(dados.getQuantidadeNotasRobos()));
        properties.setProperty("perguntas.respostas", formatarMatriz(dados.getContagemRespostasPerguntas()));

        try (FileOutputStream output = new FileOutputStream(arquivo)) {
            properties.store(output, "Estatisticas do Museu de Marte");
        } catch (IOException e) {
            System.out.println("Nao foi possivel salvar as estatisticas: " + e.getMessage());
        }
    }

    /**
     * Le um numero inteiro do arquivo e retorna zero se ele estiver invalido.
     */
    private int lerInteiro(Properties properties, String chave) {
        // Evita erro caso o arquivo esteja vazio, incompleto ou com algum valor invalido.
        try {
            return Integer.parseInt(properties.getProperty(chave, "0"));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Converte uma chave do arquivo em um vetor de inteiros.
     */
    private void carregarVetor(Properties properties, String chave, int[] destino) {
        // Vetores sao salvos como texto separado por virgula. Exemplo: 1,0,3,2.
        String valor = properties.getProperty(chave, "");
        if (valor.trim().isEmpty()) {
            return;
        }

        String[] partes = valor.split(",");
        for (int i = 0; i < partes.length && i < destino.length; i++) {
            try {
                destino[i] = Integer.parseInt(partes[i]);
            } catch (NumberFormatException e) {
                destino[i] = 0;
            }
        }
    }

    /**
     * Converte uma chave do arquivo em uma matriz de inteiros.
     */
    private void carregarMatriz(Properties properties, String chave, int[][] destino) {
        // Matrizes sao salvas com linhas separadas por ponto e virgula.
        // Exemplo: 1,0,0,0;0,2,0,0.
        String valor = properties.getProperty(chave, "");
        if (valor.trim().isEmpty()) {
            return;
        }

        String[] linhas = valor.split(";");
        for (int i = 0; i < linhas.length && i < destino.length; i++) {
            carregarValoresLinha(linhas[i], destino[i]);
        }
    }

    /**
     * Preenche uma linha da matriz usando valores separados por virgula.
     */
    private void carregarValoresLinha(String linha, int[] destino) {
        String[] partes = linha.split(",");
        for (int i = 0; i < partes.length && i < destino.length; i++) {
            try {
                destino[i] = Integer.parseInt(partes[i]);
            } catch (NumberFormatException e) {
                destino[i] = 0;
            }
        }
    }

    /**
     * Transforma um vetor em texto separado por virgula.
     */
    private String formatarVetor(int[] valores) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < valores.length; i++) {
            if (i > 0) {
                builder.append(",");
            }
            builder.append(valores[i]);
        }
        return builder.toString();
    }

    /**
     * Transforma uma matriz em texto para salvar no arquivo.
     */
    private String formatarMatriz(int[][] valores) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < valores.length; i++) {
            if (i > 0) {
                builder.append(";");
            }
            builder.append(formatarVetor(valores[i]));
        }
        return builder.toString();
    }
}

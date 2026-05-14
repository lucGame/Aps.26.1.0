package pesquisa;

/**
 * Estrutura de dados das estatisticas do sistema.
 *
 * Esta classe nao contem regra de negocio. Ela existe para facilitar
 * o carregamento e a gravacao das informacoes pelo repository.
 */
public class DadosEstatisticas {

    private int totalQuestionariosConcluidos;
    private int somaPontuacaoQuestionarios;
    private int somaNotasFinais;
    private int[] contagemNotasFinais;
    private int[] visualizacoesRobos;
    private int[] somaNotasRobos;
    private int[] quantidadeNotasRobos;
    private int[][] contagemRespostasPerguntas;

    /**
     * Cria os contadores com tamanho adequado para os catalogos atuais.
     */
    public DadosEstatisticas(int quantidadeRobos, int quantidadePerguntas, int quantidadeOpcoes) {
        // O tamanho dos vetores depende da quantidade atual de robos e perguntas do catalogo.
        contagemNotasFinais = new int[5];
        visualizacoesRobos = new int[quantidadeRobos];
        somaNotasRobos = new int[quantidadeRobos];
        quantidadeNotasRobos = new int[quantidadeRobos];
        contagemRespostasPerguntas = new int[quantidadePerguntas][quantidadeOpcoes];
    }

    /**
     * Retorna quantos questionarios foram finalizados.
     */
    public int getTotalQuestionariosConcluidos() {
        return totalQuestionariosConcluidos;
    }

    /**
     * Altera o total de questionarios finalizados.
     */
    public void setTotalQuestionariosConcluidos(int totalQuestionariosConcluidos) {
        this.totalQuestionariosConcluidos = totalQuestionariosConcluidos;
    }

    /**
     * Retorna a soma de pontos obtidos nos questionarios.
     */
    public int getSomaPontuacaoQuestionarios() {
        return somaPontuacaoQuestionarios;
    }

    /**
     * Altera a soma de pontos obtidos nos questionarios.
     */
    public void setSomaPontuacaoQuestionarios(int somaPontuacaoQuestionarios) {
        this.somaPontuacaoQuestionarios = somaPontuacaoQuestionarios;
    }

    /**
     * Retorna a soma das notas finais da experiencia.
     */
    public int getSomaNotasFinais() {
        return somaNotasFinais;
    }

    /**
     * Altera a soma das notas finais da experiencia.
     */
    public void setSomaNotasFinais(int somaNotasFinais) {
        this.somaNotasFinais = somaNotasFinais;
    }

    /**
     * Retorna a contagem de notas finais de 1 a 5.
     */
    public int[] getContagemNotasFinais() {
        return contagemNotasFinais;
    }

    /**
     * Retorna a quantidade de visualizacoes de cada robo.
     */
    public int[] getVisualizacoesRobos() {
        return visualizacoesRobos;
    }

    /**
     * Retorna a soma das notas recebidas por cada robo.
     */
    public int[] getSomaNotasRobos() {
        return somaNotasRobos;
    }

    /**
     * Retorna quantas notas cada robo recebeu.
     */
    public int[] getQuantidadeNotasRobos() {
        return quantidadeNotasRobos;
    }

    /**
     * Retorna a contagem de respostas marcadas em cada pergunta.
     */
    public int[][] getContagemRespostasPerguntas() {
        return contagemRespostasPerguntas;
    }
}

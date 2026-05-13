package pesquisa;

/**
 * Classe simples para representar uma pergunta objetiva.
 */
public class Pergunta {

    protected String enunciado;
    protected String[] opcoes;
    protected int indiceCorreto;

    public Pergunta(String enunciado, String[] opcoes, int indiceCorreto) {
        this.enunciado = enunciado;
        this.opcoes = opcoes;
        this.indiceCorreto = indiceCorreto;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public String[] getOpcoes() {
        return opcoes;
    }

    public int getIndiceCorreto() {
        return indiceCorreto;
    }
}

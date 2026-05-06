package models;

/**
 * Classe base com propriedades gerais.
 *
 * Ela existe para atender ao requisito acadêmico de herança.
 * Outras classes podem herdar estas propriedades básicas.
 */
public class RegistroBase {

    protected int id;
    protected String nome;
    protected String imagem;

    /**
     * Construtor padrão da classe base.
     *
     * @param id identificador interno
     * @param nome nome do registro
     * @param imagem caminho da imagem no projeto
     */
    public RegistroBase(int id, String nome, String imagem) {
        this.id = id;
        this.nome = nome;
        this.imagem = imagem;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}

package models;

/**
 * Classe que representa um robô explorador de Marte.
 *
 * Esta classe herda propriedades básicas de RegistroBase
 * e adiciona os dados específicos exigidos no projeto.
 */
public class RoboExplorador extends RegistroBase {

    protected String dadosLancamento;
    protected String veiculoLancamento;
    protected String localLancamento;
    protected String entradaOrbitaMarte;
    protected String dadosAterrissagem;
    protected String localPouso;
    protected String plataforma;
    protected String curiosidades;
    protected String descricaoHistorica;

    /**
     * Construtor completo do robô.
     */
    public RoboExplorador(int id, String nome, String imagem,
                          String dadosLancamento, String veiculoLancamento,
                          String localLancamento, String entradaOrbitaMarte,
                          String dadosAterrissagem, String localPouso,
                          String plataforma, String curiosidades,
                          String descricaoHistorica) {
        super(id, nome, imagem);
        this.dadosLancamento = dadosLancamento;
        this.veiculoLancamento = veiculoLancamento;
        this.localLancamento = localLancamento;
        this.entradaOrbitaMarte = entradaOrbitaMarte;
        this.dadosAterrissagem = dadosAterrissagem;
        this.localPouso = localPouso;
        this.plataforma = plataforma;
        this.curiosidades = curiosidades;
        this.descricaoHistorica = descricaoHistorica;
    }

    public String getDadosLancamento() {
        return dadosLancamento;
    }

    public String getVeiculoLancamento() {
        return veiculoLancamento;
    }

    public String getLocalLancamento() {
        return localLancamento;
    }

    public String getEntradaOrbitaMarte() {
        return entradaOrbitaMarte;
    }

    public String getDadosAterrissagem() {
        return dadosAterrissagem;
    }

    public String getLocalPouso() {
        return localPouso;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public String getCuriosidades() {
        return curiosidades;
    }

    public String getDescricaoHistorica() {
        return descricaoHistorica;
    }
}

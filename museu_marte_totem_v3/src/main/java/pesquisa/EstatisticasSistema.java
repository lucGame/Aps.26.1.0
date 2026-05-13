package pesquisa;

import models.CatalogoRobos;
import repository.ArquivoEstatisticasRepository;
import service.EstatisticaService;

/**
 * Fachada de compatibilidade para as telas Swing.
 *
 * As regras de negocio ficam em EstatisticaService e a persistencia fica
 * em ArquivoEstatisticasRepository.
 */
public class EstatisticasSistema {

    private static EstatisticasSistema instancia;

    private final EstatisticaService estatisticaService;

    /**
     * Cria o service de estatisticas com o repository de arquivo.
     */
    private EstatisticasSistema() {
        // Monta as dependencias usadas pelas telas: repository, catalogo de robos e perguntas.
        estatisticaService = new EstatisticaService(
                new ArquivoEstatisticasRepository(),
                CatalogoRobos.getListaRobos(),
                CatalogoPerguntas.getListaPerguntas()
        );
    }

    /**
     * Retorna a unica instancia usada pelas telas Swing.
     */
    public static EstatisticasSistema getInstancia() {
        // Mantem compatibilidade com as telas antigas, que ja chamavam getInstancia().
        if (instancia == null) {
            instancia = new EstatisticasSistema();
        }
        return instancia;
    }

    /**
     * Encaminha o registro de visualizacao para o service.
     */
    public void registrarVisualizacaoRobo(int indice) {
        estatisticaService.registrarVisualizacaoRobo(indice);
    }

    /**
     * Encaminha o registro de nota do robo para o service.
     */
    public void registrarNotaRobo(int indice, int nota) {
        estatisticaService.registrarNotaRobo(indice, nota);
    }

    /**
     * Encaminha o registro de questionario para o service.
     */
    public void registrarQuestionario(int[] respostas, int pontuacao) {
        estatisticaService.registrarQuestionario(respostas, pontuacao);
    }

    /**
     * Encaminha o registro da nota final para o service.
     */
    public void registrarNotaFinal(int nota) {
        estatisticaService.registrarNotaFinal(nota);
    }

    /**
     * Retorna a media de avaliacao de um robo.
     */
    public double calcularMediaRobo(int indice) {
        return estatisticaService.calcularMediaRobo(indice);
    }

    /**
     * Retorna a media geral das notas finais.
     */
    public double calcularMediaNotaFinal() {
        return estatisticaService.calcularMediaNotaFinal();
    }

    /**
     * Retorna a media de acertos dos questionarios.
     */
    public double calcularMediaAcertosQuestionario() {
        return estatisticaService.calcularMediaAcertosQuestionario();
    }

    /**
     * Retorna o texto usado pela tela de relatorio.
     */
    public String gerarRelatorioTexto() {
        return estatisticaService.gerarRelatorioTexto();
    }
}

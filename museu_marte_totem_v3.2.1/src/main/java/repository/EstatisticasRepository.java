package repository;

import pesquisa.DadosEstatisticas;

public interface EstatisticasRepository {

    /**
     * Carrega as estatisticas salvas ou retorna dados zerados.
     */
    DadosEstatisticas carregar(int quantidadeRobos, int quantidadePerguntas, int quantidadeOpcoes);

    /**
     * Salva as estatisticas atuais.
     */
    void salvar(DadosEstatisticas dados);
}

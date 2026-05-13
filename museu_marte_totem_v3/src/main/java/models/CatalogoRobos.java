package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por montar a lista fixa de robôs do sistema.
 *
 * Como o projeto é básico e sem banco de dados, todos os dados ficam
 * carregados em memória, dentro desta classe.
 */
public class CatalogoRobos {

    protected static List<RoboExplorador> listaRobos;

    /**
     * Retorna a lista de robôs.
     * Se a lista ainda não existir, ela é criada uma única vez.
     *
     * @return lista com os robôs cadastrados
     */
    public static List<RoboExplorador> getListaRobos() {
        if (listaRobos == null) {
            carregarRobos();
        }
        return listaRobos;
    }

    /**
     * Cria todos os robôs exigidos no enunciado.
     */
    protected static void carregarRobos() {
        listaRobos = new ArrayList<RoboExplorador>();

        listaRobos.add(new RoboExplorador(
                1,
                "Sojourner",
                "/imagens/sojourner.png",
                "Lançamento em 04/12/1996. A missão Mars Pathfinder levou o pequeno rover Sojourner ao espaço.",
                "Delta II",
                "Cape Canaveral, Flórida, EUA.",
                "Não se aplica. A missão seguiu em trajetória direta até Marte, sem entrada em órbita marciana própria.",
                "A nave Mars Pathfinder pousou em 04/07/1997. O rover desceu da plataforma em 06/07/1997 para iniciar a exploração local.",
                "Ares Vallis, região de Chryse Planitia.",
                "Mars Pathfinder",
                "Foi o primeiro veículo com rodas a operar em Marte. Seu nome homenageia Sojourner Truth. Mesmo sendo pequeno, abriu caminho para os rovers mais modernos.",
                "Sojourner é um marco da exploração espacial. Ele provou que um robô móvel podia explorar Marte de forma prática e segura. A missão demonstrou novas técnicas de pouso com airbags e mostrou ao mundo imagens e análises do solo marciano. Historicamente, foi uma missão de transição entre os landers fixos e os grandes rovers científicos que vieram depois."
        ));

        listaRobos.add(new RoboExplorador(
                2,
                "Spirit",
                "/imagens/spirit.png",
                "Lançamento em 10/06/2003 pela missão Mars Exploration Rover-A.",
                "Delta II 7925-9.5",
                "Cape Canaveral, Flórida, EUA.",
                "Não se aplica. A espaçonave realizou entrada atmosférica direta para pouso.",
                "Pouso em 03/01/2004 (horário dos EUA) / 04/01/2004 UTC. O local recebeu o nome de Columbia Memorial Station.",
                "Cratera Gusev, Marte.",
                "Mars Exploration Rover (MER-A)",
                "Spirit foi planejado para durar 90 sols, mas trabalhou por vários anos. Ficou preso em solo macio no fim da missão, mas continuou enviando dados por bastante tempo.",
                "Spirit e Opportunity foram gêmeos robóticos criados para procurar sinais de água antiga em Marte. Spirit encontrou evidências de ambientes que já tiveram ação da água e mostrou que Marte teve um passado geológico mais ativo do que se imaginava. Seu legado é científico e simbólico, pois ajudou a manter o interesse público em missões longas na superfície marciana."
        ));

        listaRobos.add(new RoboExplorador(
                3,
                "Opportunity",
                "/imagens/opportunity1.png",
                "Lançamento em 07/07/2003 pela missão Mars Exploration Rover-B.",
                "Delta II Heavy 7925",
                "Cape Canaveral, Flórida, EUA.",
                "Não se aplica. A missão seguiu para pouso direto em Marte.",
                "Pouso em 24/01/2004 (horário dos EUA) / 25/01/2004 UTC. A área de pouso ficou conhecida como Challenger Memorial Station.",
                "Meridiani Planum, dentro da pequena cratera Eagle.",
                "Mars Exploration Rover (MER-B)",
                "Opportunity superou muito sua vida útil planejada. Rodou mais de 45 km e funcionou por quase 15 anos, tornando-se uma das missões mais bem-sucedidas da história de Marte.",
                "Opportunity reforçou a ideia de que Marte já teve água líquida em seu passado. Ao analisar rochas e minerais, a missão mostrou ambientes que poderiam ter sido favoráveis à vida microbiana. Seu tempo de operação surpreendeu o mundo e transformou o rover em um símbolo de resistência e exploração científica."
        ));

        listaRobos.add(new RoboExplorador(
                4,
                "Curiosity",
                "/imagens/curiosity.png",
                "Lançamento em 26/11/2011 pela missão Mars Science Laboratory.",
                "Atlas V 541",
                "Cape Canaveral, Flórida, EUA.",
                "Não se aplica. A missão foi desenhada para entrada, descida e pouso direto em Marte.",
                "Pouso em 06/08/2012. O ponto de toque foi chamado de Bradbury Landing.",
                "Cratera Gale, Marte.",
                "Mars Science Laboratory (MSL)",
                "Foi o primeiro grande rover a usar a técnica do 'sky crane', um guindaste aéreo que baixou o robô por cabos até o chão marciano.",
                "Curiosity foi enviado para responder uma pergunta central: Marte já teve condições para vida microbiana? O rover encontrou sinais químicos e geológicos de ambientes antigos habitáveis. Seu laboratório móvel, muito mais completo que o dos rovers anteriores, ampliou bastante o nível da ciência feita diretamente na superfície de Marte."
        ));

        listaRobos.add(new RoboExplorador(
                5,
                "Perseverance",
                "/imagens/perseverance.png",
                "Lançamento em 30/07/2020 pela missão Mars 2020.",
                "Atlas V 541",
                "Cape Canaveral, Flórida, EUA.",
                "Não se aplica. A missão fez entrada atmosférica direta para o pouso.",
                "Pouso em 18/02/2021. A área principal de pouso ficou conhecida como Octavia E. Butler Landing.",
                "Cratera Jezero, Marte.",
                "Mars 2020",
                "Levou o helicóptero Ingenuity, que realizou voos históricos em Marte. Também coleta amostras para possível retorno futuro à Terra.",
                "Perseverance é uma missão voltada à astrobiologia. Seu foco é procurar sinais de vida antiga e selecionar amostras de rochas e solo marciano. A missão representa uma nova etapa da exploração de Marte, porque pensa não apenas em analisar o planeta no local, mas também em trazer material para laboratórios terrestres no futuro."
        ));

        listaRobos.add(new RoboExplorador(
                6,
                "Phoenix",
                "/imagens/phoenix.png",
                "Lançamento em 04/08/2007.",
                "Delta II",
                "Cape Canaveral, Flórida, EUA.",
                "Não se aplica. A missão pousou diretamente em Marte sem órbita marciana própria.",
                "Pouso em 25/05/2008.",
                "Green Valley, em Vastitas Borealis, região ártica de Marte.",
                "Phoenix Mars Lander",
                "Phoenix confirmou a presença de gelo de água logo abaixo da superfície marciana em alta latitude. Foi uma missão parada, sem rodas, mas muito importante.",
                "Phoenix estudou o Ártico marciano, onde havia grande chance de encontrar gelo de água. A missão ajudou a entender melhor a química do solo, o clima local e a presença de água congelada perto da superfície. Mesmo sem se locomover, foi essencial para o avanço do conhecimento sobre habitabilidade em Marte."
        ));

        listaRobos.add(new RoboExplorador(
                7,
                "InSight Lander",
                "/imagens/insight.png",
                "Lançamento em 05/05/2018.",
                "Atlas V 401",
                "Vandenberg, Califórnia, EUA.",
                "Não se aplica. A missão desceu diretamente para pouso em Marte.",
                "Pouso em 26/11/2018.",
                "Elysium Planitia, Marte.",
                "InSight",
                "InSight não foi criado para percorrer o solo, mas para 'ouvir' Marte por dentro com um sismômetro. A missão estudou o interior do planeta.",
                "InSight trouxe uma abordagem diferente: em vez de procurar rochas em vários lugares, ficou parado para investigar a estrutura interna de Marte. Seu objetivo principal era entender crosta, manto e núcleo marcianos. Isso foi importante para comparar a evolução de Marte com a da Terra e de outros planetas rochosos."
        ));

        listaRobos.add(new RoboExplorador(
                8,
                "Zhurong",
                "/imagens/zhurong.png",
                "Lançamento em 23/07/2020 pela missão Tianwen-1.",
                "Long March 5",
                "Wenchang Spacecraft Launch Site, China.",
                "A missão Tianwen-1 entrou em órbita de Marte em 10/02/2021 antes da tentativa de pouso.",
                "O conjunto pousou em 14/05/2021 e o rover iniciou sua descida da plataforma em 22/05/2021.",
                "Utopia Planitia, Marte.",
                "Tianwen-1",
                "Zhurong foi o primeiro rover chinês em Marte. Sua missão colocou a China entre os países com sucesso em operação de rover no planeta vermelho.",
                "Zhurong marcou a entrada da China na exploração de Marte com pouso e operação de superfície. A missão combinou orbiter, módulo de pouso e rover. O feito foi historicamente importante porque mostrou o crescimento tecnológico do programa espacial chinês e ampliou a cooperação e a competição científica no estudo de Marte."
        ));

        listaRobos.add(new RoboExplorador(
                9,
                "Mars 2 Rovers",
                "/imagens/mars2.png",
                "Lançamento em 19/05/1971 pela União Soviética.",
                "Proton-K com estágio Blok D",
                "Baikonur Cosmodrome.",
                "A nave principal entrou em órbita de Marte em 27/11/1971. O módulo de descida foi separado antes disso.",
                "O módulo de descida atingiu Marte em 27/11/1971, mas o pouso falhou. O pequeno rover PrOP-M, que estava a bordo, não chegou a operar.",
                "Região estimada ao sul de Marte, com local exato incerto. A missão é associada a uma área próxima da bacia de Hellas.",
                "Mars 2 / rover PrOP-M",
                "Mesmo com falha, Mars 2 é lembrada porque seu módulo foi o primeiro objeto humano a alcançar a superfície de Marte. O pequeno rover embarcado era uma ideia muito avançada para a época.",
                "A missão Mars 2 pertence ao esforço soviético de explorar Marte no início da década de 1970. Ela transportava um pequeno veículo robótico chamado PrOP-M, que teria se movido na superfície preso ao módulo por um cabo. A falha no pouso impediu a operação do rover, mas a missão continua historicamente relevante por ter sido pioneira e muito ousada para seu tempo."
        ));
    }
}

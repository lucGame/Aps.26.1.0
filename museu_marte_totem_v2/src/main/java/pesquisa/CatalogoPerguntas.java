package pesquisa;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe com as cinco perguntas do questionário.
 */
public class CatalogoPerguntas {

    protected static List<Pergunta> listaPerguntas;

    public static List<Pergunta> getListaPerguntas() {
        if (listaPerguntas == null) {
            carregarPerguntas();
        }
        return listaPerguntas;
    }

    protected static void carregarPerguntas() {
        listaPerguntas = new ArrayList<Pergunta>();

        listaPerguntas.add(new Pergunta(
                "1) Qual foi o primeiro veículo com rodas a operar em Marte?",
                new String[]{"Spirit", "Sojourner", "Curiosity", "Zhurong"},
                1
        ));

        listaPerguntas.add(new Pergunta(
                "2) Qual missão ficou famosa por usar o sistema de pouso chamado 'sky crane' pela primeira vez?",
                new String[]{"Phoenix", "Opportunity", "Curiosity", "InSight"},
                2
        ));

        listaPerguntas.add(new Pergunta(
                "3) Qual robô chinês explorou a região de Utopia Planitia?",
                new String[]{"Perseverance", "Mars 2 Rovers", "Zhurong", "Spirit"},
                2
        ));

        listaPerguntas.add(new Pergunta(
                "4) Qual missão foi criada para estudar o interior de Marte com um sismômetro?",
                new String[]{"InSight Lander", "Phoenix", "Sojourner", "Opportunity"},
                0
        ));

        listaPerguntas.add(new Pergunta(
                "5) Em qual cratera pousou o rover Perseverance?",
                new String[]{"Cratera Gale", "Cratera Jezero", "Cratera Gusev", "Eagle Crater"},
                1
        ));
    }
}

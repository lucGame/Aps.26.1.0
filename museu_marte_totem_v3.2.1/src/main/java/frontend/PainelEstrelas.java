package frontend;

import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Painel reutilizavel para selecao de 1 a 5 estrelas.
 */
public class PainelEstrelas extends JPanel {

    private static final String ESTRELA_VAZIA = "\u2606";
    private static final String ESTRELA_CHEIA = "\u2605";

    protected JButton[] btnEstrelas;
    protected int notaSelecionada;

    public PainelEstrelas() {
        btnEstrelas = new JButton[5];
        notaSelecionada = 0;

        setLayout(new FlowLayout(FlowLayout.CENTER, 8, 4));
        setOpaque(false);

        criarBotoes();
    }

    protected void criarBotoes() {
        for (int i = 0; i < btnEstrelas.length; i++) {
            final int nota = i + 1;

            btnEstrelas[i] = new JButton(ESTRELA_VAZIA);
            btnEstrelas[i].setFont(new Font("SansSerif", Font.BOLD, 28));
            btnEstrelas[i].setFocusPainted(false);
            btnEstrelas[i].setContentAreaFilled(false);
            btnEstrelas[i].setBorderPainted(false);
            btnEstrelas[i].setForeground(UtilTela.COR_DESTAQUE);
            btnEstrelas[i].addActionListener(e -> definirNota(nota));

            add(btnEstrelas[i]);
        }
    }

    public void definirNota(int nota) {
        notaSelecionada = nota;
        for (int i = 0; i < btnEstrelas.length; i++) {
            btnEstrelas[i].setText(i < nota ? ESTRELA_CHEIA : ESTRELA_VAZIA);
        }
    }

    public int getNotaSelecionada() {
        return notaSelecionada;
    }

    public void limpar() {
        definirNota(0);
    }
}

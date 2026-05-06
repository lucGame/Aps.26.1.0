package frontend;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Painel reutilizável para seleção de 1 a 5 estrelas.
 */
public class PainelEstrelas extends JPanel {

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

            btnEstrelas[i] = new JButton("☆");
            btnEstrelas[i].setFont(new Font("SansSerif", Font.BOLD, 28));
            btnEstrelas[i].setFocusPainted(false);
            btnEstrelas[i].setContentAreaFilled(false);
            btnEstrelas[i].setBorderPainted(false);
            btnEstrelas[i].setForeground(UtilTela.COR_DESTAQUE);

            btnEstrelas[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    definirNota(nota);
                }
            });

            add(btnEstrelas[i]);
        }
    }

    public void definirNota(int nota) {
        this.notaSelecionada = nota;
        for (int i = 0; i < btnEstrelas.length; i++) {
            if (i < nota) {
                btnEstrelas[i].setText("★");
            } else {
                btnEstrelas[i].setText("☆");
            }
        }
    }

    public int getNotaSelecionada() {
        return notaSelecionada;
    }

    public void limpar() {
        definirNota(0);
    }
}

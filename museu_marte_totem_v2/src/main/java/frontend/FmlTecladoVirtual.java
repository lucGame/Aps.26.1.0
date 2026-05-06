package frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Teclado virtual simples para uso no totem touchscreen.
 */
public class FmlTecladoVirtual extends JDialog {

    protected JTextField txtDigitacao;
    protected JButton btnApagar;
    protected JButton btnLimpar;
    protected JButton btnEspaco;
    protected JButton btnConfirmar;
    protected JButton btnCancelar;

    protected String textoFinal;
    protected boolean confirmado;

    public FmlTecladoVirtual(String textoInicial) {
        UtilTela.configurarDialogo(this, "Teclado Virtual", 420, 560);
        setModal(true);
        textoFinal = textoInicial;
        confirmado = false;
        montarTela();
    }

    protected void montarTela() {
        setLayout(new BorderLayout(10, 10));

        JPanel pnlTopo = new JPanel(new BorderLayout());
        pnlTopo.setOpaque(false);
        pnlTopo.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));

        JLabel lblTitulo = UtilTela.criarTitulo("Teclado Virtual");
        JLabel lblInfo = UtilTela.criarSubtitulo("Toque nas letras para pesquisar um robô");

        txtDigitacao = new JTextField(textoFinal == null ? "" : textoFinal);
        txtDigitacao.setEditable(false);
        txtDigitacao.setFont(new Font("SansSerif", Font.BOLD, 20));

        JPanel pnlCabecalho = new JPanel(new BorderLayout());
        pnlCabecalho.setOpaque(false);
        pnlCabecalho.add(lblTitulo, BorderLayout.NORTH);
        pnlCabecalho.add(lblInfo, BorderLayout.CENTER);

        pnlTopo.add(pnlCabecalho, BorderLayout.NORTH);
        pnlTopo.add(txtDigitacao, BorderLayout.SOUTH);

        add(pnlTopo, BorderLayout.NORTH);

        JPanel pnlTeclas = new JPanel(new GridLayout(5, 6, 8, 8));
        pnlTeclas.setOpaque(false);
        pnlTeclas.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        String[] letras = {
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z"
        };

        for (int i = 0; i < letras.length; i++) {
            JButton btnLetra = criarBotaoTecla(letras[i]);
            pnlTeclas.add(btnLetra);
        }

        btnEspaco = criarBotaoTecla("ESPAÇO");
        btnApagar = criarBotaoTecla("APAGAR");
        btnLimpar = criarBotaoTecla("LIMPAR");
        btnConfirmar = criarBotaoTecla("OK");

        pnlTeclas.add(btnEspaco);
        pnlTeclas.add(btnApagar);
        pnlTeclas.add(btnLimpar);
        pnlTeclas.add(btnConfirmar);

        add(pnlTeclas, BorderLayout.CENTER);

        JPanel pnlRodape = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        pnlRodape.setOpaque(false);
        btnCancelar = UtilTela.criarBotaoSecundario("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        pnlRodape.add(btnCancelar);
        add(pnlRodape, BorderLayout.SOUTH);
    }

    protected JButton criarBotaoTecla(final String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("SansSerif", Font.BOLD, 16));
        botao.setFocusPainted(false);

        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tratarToqueTecla(texto);
            }
        });

        return botao;
    }

    protected void tratarToqueTecla(String tecla) {
        String textoAtual = txtDigitacao.getText();

        if ("APAGAR".equals(tecla)) {
            if (textoAtual.length() > 0) {
                txtDigitacao.setText(textoAtual.substring(0, textoAtual.length() - 1));
            }
            return;
        }

        if ("LIMPAR".equals(tecla)) {
            txtDigitacao.setText("");
            return;
        }

        if ("ESPAÇO".equals(tecla)) {
            txtDigitacao.setText(textoAtual + " ");
            return;
        }

        if ("OK".equals(tecla)) {
            textoFinal = txtDigitacao.getText();
            confirmado = true;
            dispose();
            return;
        }

        txtDigitacao.setText(textoAtual + tecla);
    }

    public String getTextoFinal() {
        return textoFinal;
    }

    public boolean isConfirmado() {
        return confirmado;
    }
}

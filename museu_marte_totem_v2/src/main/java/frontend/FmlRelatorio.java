package frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import pesquisa.EstatisticasSistema;

/**
 * Tela que apresenta os dados estatísticos da exposição.
 */
public class FmlRelatorio extends JDialog {

    protected JTextArea txtRelatorio;
    protected JButton btnAtualizar;
    protected JButton btnVoltar;

    public FmlRelatorio() {
        UtilTela.configurarDialogo(this, "Relatório Estatístico", 430, 700);
        setModal(true);
        montarTela();
        atualizarRelatorio();
    }

    protected void montarTela() {
        setLayout(new BorderLayout(10, 10));

        JPanel pnlTopo = new JPanel(new BorderLayout());
        pnlTopo.setOpaque(false);
        pnlTopo.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));
        pnlTopo.add(UtilTela.criarTitulo("Relatório da Exposição"), BorderLayout.NORTH);
        pnlTopo.add(UtilTela.criarSubtitulo("Dados temporários em memória"), BorderLayout.SOUTH);
        add(pnlTopo, BorderLayout.NORTH);

        txtRelatorio = new JTextArea();
        txtRelatorio.setEditable(false);
        txtRelatorio.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtRelatorio.setBackground(UtilTela.COR_CARTAO);
        txtRelatorio.setForeground(UtilTela.COR_TEXTO);
        txtRelatorio.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JScrollPane scroll = new JScrollPane(txtRelatorio);
        scroll.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        add(scroll, BorderLayout.CENTER);

        JPanel pnlRodape = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        pnlRodape.setOpaque(false);
        btnAtualizar = UtilTela.criarBotaoSecundario("Atualizar");
        btnVoltar = UtilTela.criarBotaoSecundario("Voltar");

        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarRelatorio();
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        pnlRodape.add(btnAtualizar);
        pnlRodape.add(btnVoltar);
        add(pnlRodape, BorderLayout.SOUTH);
    }

    protected void atualizarRelatorio() {
        txtRelatorio.setText(EstatisticasSistema.getInstancia().gerarRelatorioTexto());
        txtRelatorio.setCaretPosition(0);
    }
}

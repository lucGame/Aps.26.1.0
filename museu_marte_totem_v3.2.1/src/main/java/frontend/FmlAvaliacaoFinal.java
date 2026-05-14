package frontend;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import pesquisa.EstatisticasSistema;

/**
 * Tela de avaliacao final da experiencia do visitante.
 */
public class FmlAvaliacaoFinal extends JDialog {

    // Variables declaration - do not modify
    JButton btnLimparBusca;


    public FmlAvaliacaoFinal() {
        initComponents();
        UtilTela.configurarDialogo(this, "Avaliacao Final", 420, 360);
        setModal(true);
        configurarTela();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlConteudo = new javax.swing.JPanel();
        pnlCentro = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        pnlEstrelas = new frontend.PainelEstrelas();
        pnlRodape = new javax.swing.JPanel();
        btnConcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        pnlConteudo.setBackground(new java.awt.Color(214, 108, 34));
        pnlConteudo.setLayout(new java.awt.BorderLayout(10, 10));

        pnlCentro.setBackground(new java.awt.Color(214, 108, 34));
        pnlCentro.setLayout(new java.awt.GridLayout(3, 1));

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 26)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Avalie a exposição");
        pnlCentro.add(lblTitulo);

        lblSubtitulo.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        lblSubtitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblSubtitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSubtitulo.setText("De uma nota geral de 1 a 5 estrelas");
        pnlCentro.add(lblSubtitulo);

        pnlEstrelas.setBackground(new java.awt.Color(214, 108, 34));
        pnlEstrelas.setForeground(new java.awt.Color(255, 255, 255));
        pnlCentro.add(pnlEstrelas);

        pnlConteudo.add(pnlCentro, java.awt.BorderLayout.CENTER);

        pnlRodape.setBackground(new java.awt.Color(214, 108, 34));

        btnConcluir.setText("Finalizar visita");
        pnlRodape.add(btnConcluir);

        pnlConteudo.add(pnlRodape, java.awt.BorderLayout.SOUTH);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlConteudo, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlConteudo, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    protected void configurarTela() {
        btnConcluir.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { concluirAvaliacao(); }});
    }

    protected void concluirAvaliacao() {
        int nota = pnlEstrelas.getNotaSelecionada();
        if (nota <= 0) {
            JOptionPane.showMessageDialog(this, "Escolha de 1 a 5 estrelas antes de finalizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        EstatisticasSistema.getInstancia().registrarNotaFinal(nota);
        JOptionPane.showMessageDialog(this, "Obrigado pela visita ao museu! Sua opiniao e muito importante. O sistema retornara ao inicio.", "Mensagem final", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConcluir;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlCentro;
    private javax.swing.JPanel pnlConteudo;
    private frontend.PainelEstrelas pnlEstrelas;
    private javax.swing.JPanel pnlRodape;
    // End of variables declaration//GEN-END:variables

}

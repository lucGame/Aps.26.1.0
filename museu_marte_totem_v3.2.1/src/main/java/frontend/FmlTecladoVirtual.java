package frontend;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;

/**
 * Teclado virtual simples para uso no totem touchscreen.
 */
public class FmlTecladoVirtual extends JDialog {

    protected String textoFinal;
    protected boolean confirmado;

    public FmlTecladoVirtual(String textoInicial) {
        initComponents();
        UtilTela.configurarDialogo(this, "Teclado Virtual", 430, 620);
        setModal(true);
        textoFinal = textoInicial;
        confirmado = false;
        configurarTela();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlConteudo = new javax.swing.JPanel();
        pnlTopo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblInfo = new javax.swing.JLabel();
        txtDigitacao = new javax.swing.JTextField();
        pnlTeclas = new javax.swing.JPanel();
        pnlRodape = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        pnlConteudo.setBackground(new java.awt.Color(214, 108, 34));
        pnlConteudo.setLayout(new java.awt.BorderLayout(10, 10));

        pnlTopo.setBackground(new java.awt.Color(214, 108, 34));
        pnlTopo.setLayout(new java.awt.GridLayout(3, 1));

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 26)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Teclado Virtual");
        pnlTopo.add(lblTitulo);

        lblInfo.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        lblInfo.setForeground(new java.awt.Color(255, 255, 255));
        lblInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInfo.setText("Toque nas letras para pesquisar um robo");
        pnlTopo.add(lblInfo);

        txtDigitacao.setBackground(new java.awt.Color(184, 100, 51));
        txtDigitacao.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        pnlTopo.add(txtDigitacao);

        pnlConteudo.add(pnlTopo, java.awt.BorderLayout.NORTH);

        pnlTeclas.setBackground(new java.awt.Color(214, 108, 34));
        pnlTeclas.setLayout(new java.awt.GridLayout(5, 6, 8, 8));
        pnlConteudo.add(pnlTeclas, java.awt.BorderLayout.CENTER);

        pnlRodape.setBackground(new java.awt.Color(214, 108, 34));

        btnCancelar.setText("Cancelar");
        pnlRodape.add(btnCancelar);

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
        txtDigitacao.setText(textoFinal == null ? "" : textoFinal);
        pnlTeclas.removeAll();
        adicionarLinhaTeclas("Q", "W", "E", "R", "T", "Y", "U");
        adicionarLinhaTeclas("I", "O", "P", "A", "S", "D", "F");
        adicionarLinhaTeclas("G", "H", "J", "K", "L", "Z", "X");
        adicionarLinhaTeclas("C", "V", "B", "N", "M");
        adicionarLinhaComandos();
        btnCancelar.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); }});
        pnlTeclas.revalidate();
        pnlTeclas.repaint();
    }

    protected void adicionarLinhaTeclas(String... teclas) {
        javax.swing.JPanel linha = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 4));
        linha.setOpaque(false);
        for (String tecla : teclas) {
            JButton botao = criarBotaoTecla(tecla);
            botao.setPreferredSize(new java.awt.Dimension(48, 42));
            linha.add(botao);
        }
        pnlTeclas.add(linha);
    }

    protected void adicionarLinhaComandos() {
        javax.swing.JPanel linha = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 6, 4));
        linha.setOpaque(false);
        JButton btnEspaco = criarBotaoTecla("ESPACO");
        JButton btnApagar = criarBotaoTecla("APAGAR");
        JButton btnLimpar = criarBotaoTecla("LIMPAR");
        JButton btnConfirmar = criarBotaoTecla("OK");
        btnEspaco.setPreferredSize(new java.awt.Dimension(100, 42));
        btnApagar.setPreferredSize(new java.awt.Dimension(88, 42));
        btnLimpar.setPreferredSize(new java.awt.Dimension(82, 42));
        btnConfirmar.setPreferredSize(new java.awt.Dimension(70, 42));
        linha.add(btnEspaco);
        linha.add(btnApagar);
        linha.add(btnLimpar);
        linha.add(btnConfirmar);
        pnlTeclas.add(linha);
    }

    protected JButton criarBotaoTecla(final String texto) {
        JButton botao = new JButton(texto);
        estilizarBotaoTecla(botao, "OK".equals(texto) ? new java.awt.Color(255, 0, 15) : new java.awt.Color(103, 100, 246));
        botao.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { tratarToqueTecla(texto); }});
        return botao;
    }

    protected void estilizarBotaoTecla(JButton botao, java.awt.Color cor) {
        botao.setBackground(cor);
        botao.setForeground(new java.awt.Color(184, 100, 51));
        botao.setFont(new Font("SansSerif", Font.BOLD, 13));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(8, 6, 8, 6));
        botao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    protected void tratarToqueTecla(String tecla) {
        String textoAtual = txtDigitacao.getText();
        if ("APAGAR".equals(tecla)) {
            if (textoAtual.length() > 0) txtDigitacao.setText(textoAtual.substring(0, textoAtual.length() - 1));
            return;
        }
        if ("LIMPAR".equals(tecla)) { txtDigitacao.setText(""); return; }
        if ("ESPACO".equals(tecla)) { txtDigitacao.setText(textoAtual + " "); return; }
        if ("OK".equals(tecla)) { textoFinal = txtDigitacao.getText(); confirmado = true; dispose(); return; }
        txtDigitacao.setText(textoAtual + tecla);
    }

    public String getTextoFinal() { return textoFinal; }
    public boolean isConfirmado() { return confirmado; }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlConteudo;
    private javax.swing.JPanel pnlRodape;
    private javax.swing.JPanel pnlTeclas;
    private javax.swing.JPanel pnlTopo;
    private javax.swing.JTextField txtDigitacao;
    // End of variables declaration//GEN-END:variables

}

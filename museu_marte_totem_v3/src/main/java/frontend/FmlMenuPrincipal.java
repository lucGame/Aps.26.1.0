package frontend;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import models.CatalogoRobos;
import models.RoboExplorador;
import pesquisa.EstatisticasSistema;

/**
 * Menu principal do totem interativo.
 */
public class FmlMenuPrincipal extends JDialog {

    protected List<RoboExplorador> listaRobos;

    public FmlMenuPrincipal() {
        initComponents();
        listaRobos = CatalogoRobos.getListaRobos();
        setModal(true);
        atualizarListaRobos("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlConteudo = new javax.swing.JPanel();
        pnlTopo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        lblInstrucao = new javax.swing.JLabel();
        pnlBusca = new javax.swing.JPanel();
        txtBusca = new javax.swing.JTextField();
        btnTeclado = new javax.swing.JButton();
        btnLimparBusca = new javax.swing.JButton();
        scrollRobos = new javax.swing.JScrollPane();
        pnlListaRobos = new javax.swing.JPanel();
        pnlRodape = new javax.swing.JPanel();
        btnQuestionario = new javax.swing.JButton();
        btnRelatorio = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        pnlConteudo.setBackground(new java.awt.Color(204, 255, 204));
        pnlConteudo.setLayout(new java.awt.BorderLayout(10, 10));

        pnlTopo.setLayout(new java.awt.GridLayout(4, 1));

        lblTitulo.setBackground(new java.awt.Color(204, 255, 204));
        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 26)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(153, 255, 153));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Museu Multitematico");
        pnlTopo.add(lblTitulo);

        lblSubtitulo.setBackground(new java.awt.Color(153, 255, 153));
        lblSubtitulo.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        lblSubtitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSubtitulo.setText("Totem de Robos Exploradores de Marte");
        pnlTopo.add(lblSubtitulo);

        lblInstrucao.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblInstrucao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInstrucao.setText("Toque em um cartao para abrir a pagina do robo.");
        pnlTopo.add(lblInstrucao);

        pnlBusca.setBackground(new java.awt.Color(204, 255, 204));
        pnlBusca.setLayout(new java.awt.GridLayout(0, 1, 0, 10));

        txtBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscaActionPerformed(evt);
            }
        });
        pnlBusca.add(txtBusca);

        btnTeclado.setText("Teclado");
        btnTeclado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTecladoActionPerformed(evt);
            }
        });
        pnlBusca.add(btnTeclado);

        btnLimparBusca.setText("Limpar");
        btnLimparBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparBuscaActionPerformed(evt);
            }
        });
        pnlBusca.add(btnLimparBusca);

        pnlTopo.add(pnlBusca);

        pnlConteudo.add(pnlTopo, java.awt.BorderLayout.NORTH);

        scrollRobos.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollRobos.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        pnlListaRobos.setLayout(new javax.swing.BoxLayout(pnlListaRobos, javax.swing.BoxLayout.PAGE_AXIS));
        scrollRobos.setViewportView(pnlListaRobos);

        pnlConteudo.add(scrollRobos, java.awt.BorderLayout.CENTER);

        pnlRodape.setBackground(new java.awt.Color(153, 255, 153));
        pnlRodape.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        btnQuestionario.setText("Responder questionario");
        btnQuestionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuestionarioActionPerformed(evt);
            }
        });
        pnlRodape.add(btnQuestionario);

        btnRelatorio.setText("Ver relatorio estatistico");
        btnRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatorioActionPerformed(evt);
            }
        });
        pnlRodape.add(btnRelatorio);

        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        pnlRodape.add(btnSair);

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

    private void btnTecladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTecladoActionPerformed
        FmlTecladoVirtual fmlTecladoVirtual = new FmlTecladoVirtual(txtBusca.getText());
        fmlTecladoVirtual.setVisible(true);
        if (fmlTecladoVirtual.isConfirmado()) {
            txtBusca.setText(fmlTecladoVirtual.getTextoFinal());
            atualizarListaRobos(txtBusca.getText());
        }
    }//GEN-LAST:event_btnTecladoActionPerformed

    private void txtBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscaActionPerformed
        atualizarListaRobos(txtBusca.getText());
    }//GEN-LAST:event_txtBuscaActionPerformed

    private void btnLimparBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparBuscaActionPerformed
        txtBusca.setText("");
        atualizarListaRobos("");
    }//GEN-LAST:event_btnLimparBuscaActionPerformed

    private void btnQuestionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuestionarioActionPerformed
        FmlQuestionario fmlQuestionario = new FmlQuestionario();
        fmlQuestionario.setLocationRelativeTo(this);
        fmlQuestionario.setVisible(true);
    }//GEN-LAST:event_btnQuestionarioActionPerformed

    private void btnRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatorioActionPerformed
        FmlRelatorio fmlRelatorio = new FmlRelatorio();
        fmlRelatorio.setLocationRelativeTo(this);
        fmlRelatorio.setVisible(true);
    }//GEN-LAST:event_btnRelatorioActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        int resposta = JOptionPane.showConfirmDialog(this,
                "Deseja realmente sair do totem?", "Confirmar saida",
                JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_btnSairActionPerformed

    protected void atualizarListaRobos(String filtro) {
        pnlListaRobos.removeAll();
        int quantidadeExibida = 0;

        for (int i = 0; i < listaRobos.size(); i++) {
            final int indiceRobo = i;
            final RoboExplorador robo = listaRobos.get(i);

            if (deveExibirRobo(robo, filtro)) {
                JButton btnRobo = criarBotaoRobo(robo, indiceRobo);
                pnlListaRobos.add(btnRobo);
                quantidadeExibida++;
            }
        }

        if (quantidadeExibida == 0) {
            JLabel lblSemResultados = criarLabel("Nenhum robo encontrado com esse filtro.", 14, Font.PLAIN);
            lblSemResultados.setForeground(new Color(245, 245, 245));
            pnlListaRobos.add(lblSemResultados);
        }

        pnlListaRobos.revalidate();
        pnlListaRobos.repaint();
    }

    private JLabel criarLabel(String texto, int tamanho, int estilo) {
        JLabel lbl = new JLabel(texto, SwingConstants.CENTER);
        lbl.setFont(new Font("SansSerif", estilo, tamanho));
        return lbl;
    }

    private JButton criarBotaoRobo(RoboExplorador robo, int indiceRobo) {
        JButton btnRobo = new JButton("<html><div style='text-align:left;'><b>" + robo.getNome()
                + "</b><br/><span style='font-size:11px;'>" + robo.getPlataforma()
                + "</span><br/><span style='color:#cccccc;'>Toque para ver detalhes</span></div></html>");
        btnRobo.setHorizontalAlignment(SwingConstants.LEFT);
        btnRobo.setFocusPainted(false);
        btnRobo.setBackground(new Color(48, 48, 58));
        btnRobo.setForeground(new Color(245, 245, 245));
        btnRobo.setFont(new Font("SansSerif", Font.BOLD, 17));
        btnRobo.setPreferredSize(new Dimension(360, 86));
        btnRobo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 70, 82)),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)));
        btnRobo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRobo.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRobo.addActionListener(e -> abrirDetalhesRobo(robo, indiceRobo));
        return btnRobo;
    }

    protected boolean deveExibirRobo(RoboExplorador robo, String filtro) {
        if (filtro == null || filtro.trim().isEmpty()) {
            return true;
        }
        String f = filtro.trim().toLowerCase();
        return robo.getNome().toLowerCase().contains(f)
                || robo.getPlataforma().toLowerCase().contains(f);
    }

    protected void abrirDetalhesRobo(RoboExplorador robo, int indiceRobo) {
        EstatisticasSistema.getInstancia().registrarVisualizacaoRobo(indiceRobo);
        FmlDetalhesRobo fmlDetalhesRobo = new FmlDetalhesRobo(robo, indiceRobo);
        fmlDetalhesRobo.setLocationRelativeTo(this);
        fmlDetalhesRobo.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLimparBusca;
    private javax.swing.JButton btnQuestionario;
    private javax.swing.JButton btnRelatorio;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnTeclado;
    private javax.swing.JLabel lblInstrucao;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlBusca;
    private javax.swing.JPanel pnlConteudo;
    private javax.swing.JPanel pnlListaRobos;
    private javax.swing.JPanel pnlRodape;
    private javax.swing.JPanel pnlTopo;
    private javax.swing.JScrollPane scrollRobos;
    private javax.swing.JTextField txtBusca;
    // End of variables declaration//GEN-END:variables
}

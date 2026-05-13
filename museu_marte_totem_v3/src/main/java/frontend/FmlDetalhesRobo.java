package frontend;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import models.RoboExplorador;
import pesquisa.EstatisticasSistema;

/**
 * Tela individual de detalhes do robo.
 */
public class FmlDetalhesRobo extends JDialog {

    protected RoboExplorador robo;
    protected int indiceRobo;

    public FmlDetalhesRobo(RoboExplorador robo, int indiceRobo) {
        initComponents();
        this.robo = robo;
        this.indiceRobo = indiceRobo;
        UtilTela.configurarDialogo(this, robo.getNome(), 430, 760);
        setModal(true);
        configurarTela();
        carregarDadosRobo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlConteudo = new javax.swing.JPanel();
        scrollDetalhes = new javax.swing.JScrollPane();
        pnlPrincipal = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblImagem = new javax.swing.JLabel();
        lblAvaliacao = new javax.swing.JLabel();
        pnlEstrelas = new frontend.PainelEstrelas();
        pnlBotoes = new javax.swing.JPanel();
        btnSalvarNota = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        pnlConteudo.setLayout(new java.awt.BorderLayout());

        pnlPrincipal.setLayout(new java.awt.GridLayout(0, 1, 0, 7));

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 26)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Nome do robo");
        pnlPrincipal.add(lblTitulo);

        lblImagem.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        lblImagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImagem.setText("Imagem ilustrativa");
        pnlPrincipal.add(lblImagem);

        lblAvaliacao.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        lblAvaliacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAvaliacao.setText("De uma nota de 1 a 5 estrelas para este robo");
        pnlPrincipal.add(lblAvaliacao);
        pnlPrincipal.add(pnlEstrelas);

        btnSalvarNota.setText("Salvar nota");
        pnlBotoes.add(btnSalvarNota);

        btnVoltar.setText("Voltar");
        pnlBotoes.add(btnVoltar);

        pnlPrincipal.add(pnlBotoes);

        scrollDetalhes.setViewportView(pnlPrincipal);

        pnlConteudo.add(scrollDetalhes, java.awt.BorderLayout.CENTER);

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

    private void criarBotaoSecundario(javax.swing.JButton botao, String texto) {
        botao.setBackground(new java.awt.Color(103, 100, 246));
        botao.setFont(new Font("SansSerif", Font.BOLD, 16));
        botao.setForeground(java.awt.Color.WHITE);
        botao.setText(texto);
        botao.setFocusPainted(false);
        botao.setPreferredSize(new Dimension(160, 50));
    }

    protected void configurarTela() {
        scrollDetalhes.getVerticalScrollBar().setUnitIncrement(16);
        scrollDetalhes.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        btnSalvarNota.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { salvarNota(); }});
        btnVoltar.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); }});
    }

    protected void carregarDadosRobo() {
        pnlPrincipal.removeAll();
        lblTitulo.setText(robo.getNome());
        lblTitulo.setForeground(new java.awt.Color(26, 26, 26));
        ImageIcon icone = UtilTela.carregarIcone(robo.getImagem(), 400, 200);
        if (icone != null) {
            lblImagem.setIcon(icone);
            lblImagem.setText("");
        }
        pnlPrincipal.add(lblTitulo);
        pnlPrincipal.add(lblImagem);

        adicionarBlocoTexto("Nome do robo", robo.getNome());
        adicionarBlocoTexto("Dados de lancamento", robo.getDadosLancamento());
        adicionarBlocoTexto("Veiculo de lancamento", robo.getVeiculoLancamento());
        adicionarBlocoTexto("Local de lancamento", robo.getLocalLancamento());
        adicionarBlocoTexto("Entrada em orbita de Marte", robo.getEntradaOrbitaMarte());
        adicionarBlocoTexto("Dados de aterrissagem em Marte", robo.getDadosAterrissagem());
        adicionarBlocoTexto("Local de pouso", robo.getLocalPouso());
        adicionarBlocoTexto("Plataforma", robo.getPlataforma());
        adicionarBlocoTexto("Curiosidades", robo.getCuriosidades());
        adicionarBlocoTexto("Descricao historica", robo.getDescricaoHistorica());
        pnlPrincipal.add(lblAvaliacao);
        pnlPrincipal.add(pnlEstrelas);
        pnlPrincipal.add(pnlBotoes);
        pnlPrincipal.revalidate();
        pnlPrincipal.repaint();
    }

    protected void adicionarBlocoTexto(String titulo, String conteudo) {
        JPanel pnlBloco = new JPanel();
        pnlBloco.setLayout(new javax.swing.BoxLayout(pnlBloco, javax.swing.BoxLayout.Y_AXIS));
        pnlBloco.setBackground(new java.awt.Color(39, 39, 46));
        pnlBloco.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new java.awt.Color(70, 70, 82)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        pnlBloco.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlBloco.setMaximumSize(new Dimension(360, 150));

        JLabel lblTituloCampo = new JLabel(titulo);
        lblTituloCampo.setForeground(UtilTela.COR_PRIMARIA);
        lblTituloCampo.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTituloCampo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea txtConteudo = new JTextArea(conteudo);
        txtConteudo.setLineWrap(true);
        txtConteudo.setWrapStyleWord(true);
        txtConteudo.setEditable(false);
        txtConteudo.setFocusable(false);
        txtConteudo.setBackground(new java.awt.Color(39, 39, 46));
        txtConteudo.setForeground(new java.awt.Color(245, 245, 245));
        txtConteudo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtConteudo.setBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0));
        txtConteudo.setRows(calcularLinhas(conteudo));
        txtConteudo.setMaximumSize(new Dimension(338, 150));
        txtConteudo.setAlignmentX(Component.LEFT_ALIGNMENT);

        pnlBloco.add(lblTituloCampo);
        pnlBloco.add(txtConteudo);
        pnlPrincipal.add(pnlBloco);
    }

    protected int calcularLinhas(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return 2;
        }
        int linhas = (texto.length() / 48) + 1;
        return Math.max(2, Math.min(6, linhas));
    }

    protected void salvarNota() {
        int nota = pnlEstrelas.getNotaSelecionada();
        if (nota <= 0) {
            JOptionPane.showMessageDialog(this, "Selecione de 1 a 5 estrelas antes de salvar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        EstatisticasSistema.getInstancia().registrarNotaRobo(indiceRobo, nota);
        JOptionPane.showMessageDialog(this, "Nota salva com sucesso! Obrigado pela avaliacao.", "Avaliacao registrada", JOptionPane.INFORMATION_MESSAGE);
        pnlEstrelas.limpar();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalvarNota;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JLabel lblAvaliacao;
    private javax.swing.JLabel lblImagem;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlBotoes;
    private javax.swing.JPanel pnlConteudo;
    private frontend.PainelEstrelas pnlEstrelas;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JScrollPane scrollDetalhes;
    // End of variables declaration//GEN-END:variables

}

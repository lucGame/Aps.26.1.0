package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import pesquisa.CatalogoPerguntas;
import pesquisa.EstatisticasSistema;
import pesquisa.Pergunta;

/**
 * Tela do questionario interativo.
 */
public class FmlQuestionario extends JDialog {

    protected List<Pergunta> listaPerguntas;
    protected int[] respostas;
    protected int indicePerguntaAtual;
    protected JRadioButton[] rdbOpcoes;
    protected ButtonGroup grupoOpcoes;

    public FmlQuestionario() {
        initComponents();
        listaPerguntas = CatalogoPerguntas.getListaPerguntas();
        respostas = new int[listaPerguntas.size()];
        Arrays.fill(respostas, -1);
        indicePerguntaAtual = 0;
        UtilTela.configurarDialogo(this, "Questionario Interativo", 430, 500);
        setModal(true);
        configurarTela();
        carregarPerguntaAtual();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlConteudo = new javax.swing.JPanel();
        pnlTopo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblProgresso = new javax.swing.JLabel();
        pnlCentro = new javax.swing.JPanel();
        txtPergunta = new javax.swing.JTextArea();
        pnlOpcoes = new javax.swing.JPanel();
        rdbOpcao1 = new javax.swing.JRadioButton();
        rdbOpcao2 = new javax.swing.JRadioButton();
        rdbOpcao3 = new javax.swing.JRadioButton();
        rdbOpcao4 = new javax.swing.JRadioButton();
        pnlRodape = new javax.swing.JPanel();
        btnAnterior = new javax.swing.JButton();
        btnProximo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        pnlConteudo.setLayout(new java.awt.BorderLayout(10, 10));

        pnlTopo.setLayout(new java.awt.GridLayout(2, 1));

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 26)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Quiz de Mart");
        pnlTopo.add(lblTitulo);

        lblProgresso.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        lblProgresso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProgresso.setText("Pergunta 1 de 5");
        pnlTopo.add(lblProgresso);

        pnlConteudo.add(pnlTopo, java.awt.BorderLayout.NORTH);

        pnlCentro.setLayout(new java.awt.BorderLayout(10, 10));

        txtPergunta.setText("Pergunta exibida aqui");
        txtPergunta.setLineWrap(true);
        txtPergunta.setWrapStyleWord(true);
        pnlCentro.add(txtPergunta, java.awt.BorderLayout.NORTH);

        pnlOpcoes.setLayout(new java.awt.GridLayout(4, 1, 0, 8));

        rdbOpcao1.setText("Opcao 1");
        pnlOpcoes.add(rdbOpcao1);

        rdbOpcao2.setText("Opcao 2");
        pnlOpcoes.add(rdbOpcao2);

        rdbOpcao3.setText("Opcao 3");
        pnlOpcoes.add(rdbOpcao3);

        rdbOpcao4.setText("Opcao 4");
        pnlOpcoes.add(rdbOpcao4);

        pnlCentro.add(pnlOpcoes, java.awt.BorderLayout.CENTER);

        pnlConteudo.add(pnlCentro, java.awt.BorderLayout.CENTER);

        btnAnterior.setText("Anterior");
        pnlRodape.add(btnAnterior);

        btnProximo.setText("Proxima");
        pnlRodape.add(btnProximo);

        pnlConteudo.add(pnlRodape, java.awt.BorderLayout.SOUTH);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlConteudo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlConteudo, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    protected void configurarTela() {
        grupoOpcoes = new ButtonGroup();
        rdbOpcoes = new JRadioButton[] {rdbOpcao1, rdbOpcao2, rdbOpcao3, rdbOpcao4};
        grupoOpcoes.add(rdbOpcao1);
        grupoOpcoes.add(rdbOpcao2);
        grupoOpcoes.add(rdbOpcao3);
        grupoOpcoes.add(rdbOpcao4);
        btnAnterior.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { voltarPergunta(); }});
        btnProximo.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { avancarOuFinalizar(); }});
    }

    protected void carregarPerguntaAtual() {
        Pergunta perguntaAtual = listaPerguntas.get(indicePerguntaAtual);
        lblProgresso.setText("Pergunta " + (indicePerguntaAtual + 1) + " de " + listaPerguntas.size());
        txtPergunta.setText(perguntaAtual.getEnunciado());
        grupoOpcoes.clearSelection();
        for (int i = 0; i < rdbOpcoes.length; i++) {
            rdbOpcoes[i].setText(perguntaAtual.getOpcoes()[i]);
            if (respostas[indicePerguntaAtual] == i) rdbOpcoes[i].setSelected(true);
        }
        btnAnterior.setEnabled(indicePerguntaAtual > 0);
        btnProximo.setText(indicePerguntaAtual == listaPerguntas.size() - 1 ? "Finalizar" : "Proxima");
    }

    protected boolean salvarRespostaAtual() {
        for (int i = 0; i < rdbOpcoes.length; i++) {
            if (rdbOpcoes[i].isSelected()) {
                respostas[indicePerguntaAtual] = i;
                return true;
            }
        }
        return false;
    }

    protected void avancarOuFinalizar() {
        if (!salvarRespostaAtual()) {
            JOptionPane.showMessageDialog(this, "Selecione uma opcao antes de continuar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (indicePerguntaAtual < listaPerguntas.size() - 1) {
            indicePerguntaAtual++;
            carregarPerguntaAtual();
        } else {
            finalizarQuestionario();
        }
    }

    protected void voltarPergunta() {
        salvarRespostaAtual();
        if (indicePerguntaAtual > 0) {
            indicePerguntaAtual--;
            carregarPerguntaAtual();
        }
    }

    protected void finalizarQuestionario() {
        if (!salvarRespostaAtual()) {
            JOptionPane.showMessageDialog(this, "Selecione uma opcao antes de finalizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int acertos = calcularAcertos();
        EstatisticasSistema.getInstancia().registrarQuestionario(respostas, acertos);
        JOptionPane.showMessageDialog(this, montarMensagemResultado(acertos), "Resultado do Questionario", JOptionPane.INFORMATION_MESSAGE);
        FmlAvaliacaoFinal fmlAvaliacaoFinal = new FmlAvaliacaoFinal();
        fmlAvaliacaoFinal.setLocationRelativeTo(this);
        fmlAvaliacaoFinal.setVisible(true);
        dispose();
    }

    protected int calcularAcertos() {
        int acertos = 0;
        for (int i = 0; i < listaPerguntas.size(); i++) {
            if (respostas[i] == listaPerguntas.get(i).getIndiceCorreto()) acertos++;
        }
        return acertos;
    }

    protected String montarMensagemResultado(int acertos) {
        String mensagem = "Voce acertou " + acertos + " de " + listaPerguntas.size() + " pergunta(s). ";
        if (acertos == 5) return mensagem + "Excelente! Voce conhece muito bem os exploradores de Marte.";
        if (acertos >= 3) return mensagem + "Muito bom! Voce aprendeu bastante com a exposicao.";
        return mensagem + "Obrigado por participar! Continue explorando a exposicao para aprender ainda mais.";
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnProximo;
    private javax.swing.JLabel lblProgresso;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlCentro;
    private javax.swing.JPanel pnlConteudo;
    private javax.swing.JPanel pnlOpcoes;
    private javax.swing.JPanel pnlRodape;
    private javax.swing.JPanel pnlTopo;
    private javax.swing.JRadioButton rdbOpcao1;
    private javax.swing.JRadioButton rdbOpcao2;
    private javax.swing.JRadioButton rdbOpcao3;
    private javax.swing.JRadioButton rdbOpcao4;
    private javax.swing.JTextArea txtPergunta;
    // End of variables declaration//GEN-END:variables

}

package frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
 * Tela individual de detalhes do robô.
 */
public class FmlDetalhesRobo extends JDialog {

    protected RoboExplorador robo;
    protected int indiceRobo;

    protected JLabel lblImagem;
    protected PainelEstrelas pnlEstrelas;
    protected JButton btnSalvarNota;
    protected JButton btnVoltar;

    public FmlDetalhesRobo(RoboExplorador robo, int indiceRobo) {
        this.robo = robo;
        this.indiceRobo = indiceRobo;

        UtilTela.configurarDialogo(this, robo.getNome(), 430, 820);
        setModal(true);
        montarTela();
    }

    protected void montarTela() {
        setLayout(new BorderLayout());

        JPanel pnlPrincipal = new JPanel();
        pnlPrincipal.setOpaque(false);
        pnlPrincipal.setLayout(new BoxLayout(pnlPrincipal, BoxLayout.Y_AXIS));
        pnlPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblTitulo = UtilTela.criarTitulo(robo.getNome());
        pnlPrincipal.add(lblTitulo);
        pnlPrincipal.add(Box.createVerticalStrut(10));

        lblImagem = new JLabel("Imagem ilustrativa", SwingConstants.CENTER);
        lblImagem.setPreferredSize(new Dimension(360, 190));
        lblImagem.setAlignmentX(CENTER_ALIGNMENT);
        lblImagem.setForeground(UtilTela.COR_TEXTO);
        ImageIcon icone = UtilTela.carregarIcone(robo.getImagem(), 360, 190);
        if (icone != null) {
            lblImagem.setIcon(icone);
            lblImagem.setText("");
        }
        pnlPrincipal.add(lblImagem);
        pnlPrincipal.add(Box.createVerticalStrut(12));

        adicionarBlocoTexto(pnlPrincipal, "Nome do robô", robo.getNome());
        adicionarBlocoTexto(pnlPrincipal, "Dados de lançamento", robo.getDadosLancamento());
        adicionarBlocoTexto(pnlPrincipal, "Veículo de lançamento", robo.getVeiculoLancamento());
        adicionarBlocoTexto(pnlPrincipal, "Local de lançamento", robo.getLocalLancamento());
        adicionarBlocoTexto(pnlPrincipal, "Entrada em órbita de Marte", robo.getEntradaOrbitaMarte());
        adicionarBlocoTexto(pnlPrincipal, "Dados de aterrissagem em Marte", robo.getDadosAterrissagem());
        adicionarBlocoTexto(pnlPrincipal, "Local de pouso", robo.getLocalPouso());
        adicionarBlocoTexto(pnlPrincipal, "Plataforma", robo.getPlataforma());
        adicionarBlocoTexto(pnlPrincipal, "Curiosidades", robo.getCuriosidades());
        adicionarBlocoTexto(pnlPrincipal, "Descrição histórica", robo.getDescricaoHistorica());

        JLabel lblAvaliacao = UtilTela.criarSubtitulo("Dê uma nota de 1 a 5 estrelas para este robô");
        lblAvaliacao.setAlignmentX(CENTER_ALIGNMENT);
        pnlPrincipal.add(lblAvaliacao);

        pnlEstrelas = new PainelEstrelas();
        pnlEstrelas.setAlignmentX(CENTER_ALIGNMENT);
        pnlPrincipal.add(pnlEstrelas);
        pnlPrincipal.add(Box.createVerticalStrut(10));

        JPanel pnlBotoes = new JPanel();
        pnlBotoes.setOpaque(false);
        btnSalvarNota = UtilTela.criarBotaoSecundario("Salvar nota");
        btnVoltar = UtilTela.criarBotaoSecundario("Voltar");

        btnSalvarNota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarNota();
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        pnlBotoes.add(btnSalvarNota);
        pnlBotoes.add(btnVoltar);
        pnlPrincipal.add(pnlBotoes);

        JScrollPane scroll = new JScrollPane(pnlPrincipal);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        add(scroll, BorderLayout.CENTER);
    }

    protected void adicionarBlocoTexto(JPanel painel, String titulo, String conteudo) {
        JLabel lblTituloCampo = new JLabel(titulo);
        lblTituloCampo.setForeground(UtilTela.COR_PRIMARIA);
        lblTituloCampo.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTituloCampo.setAlignmentX(LEFT_ALIGNMENT);

        JTextArea txtConteudo = new JTextArea(conteudo);
        txtConteudo.setLineWrap(true);
        txtConteudo.setWrapStyleWord(true);
        txtConteudo.setEditable(false);
        txtConteudo.setFocusable(false);
        txtConteudo.setBackground(UtilTela.COR_CARTAO);
        txtConteudo.setForeground(UtilTela.COR_TEXTO);
        txtConteudo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtConteudo.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        txtConteudo.setMaximumSize(new Dimension(360, 200));
        txtConteudo.setAlignmentX(LEFT_ALIGNMENT);

        painel.add(lblTituloCampo);
        painel.add(Box.createVerticalStrut(4));
        painel.add(txtConteudo);
        painel.add(Box.createVerticalStrut(10));
    }

    protected void salvarNota() {
        int nota = pnlEstrelas.getNotaSelecionada();
        if (nota <= 0) {
            JOptionPane.showMessageDialog(this,
                    "Selecione de 1 a 5 estrelas antes de salvar.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        EstatisticasSistema.getInstancia().registrarNotaRobo(indiceRobo, nota);
        JOptionPane.showMessageDialog(this,
                "Nota salva com sucesso! Obrigado pela avaliação.",
                "Avaliação registrada",
                JOptionPane.INFORMATION_MESSAGE);
        pnlEstrelas.limpar();
    }
}

package frontend;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import models.CatalogoRobos;
import models.RoboExplorador;
import pesquisa.EstatisticasSistema;

/**
 * Menu principal do totem interativo.
 */
public class FmlMenuPrincipal extends JDialog {

    protected List<RoboExplorador> listaRobos;

    protected JTextField txtBusca;
    protected JButton btnTeclado;
    protected JButton btnLimparBusca;
    protected JPanel pnlListaRobos;
    protected JButton btnQuestionario;
    protected JButton btnRelatorio;
    protected JButton btnSair;

    public FmlMenuPrincipal() {
        listaRobos = CatalogoRobos.getListaRobos();
        UtilTela.configurarDialogo(this, "Museu de Marte", 430, 820);
        setModal(true);
        montarTela();
        atualizarListaRobos("");
    }

    protected void montarTela() {
        setLayout(new BorderLayout(10, 10));

        JPanel pnlTopo = new JPanel();
        pnlTopo.setOpaque(false);
        pnlTopo.setLayout(new BoxLayout(pnlTopo, BoxLayout.Y_AXIS));
        pnlTopo.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));

        JLabel lblTitulo = UtilTela.criarTitulo("Museu Multitemático");
        JLabel lblSubtitulo = UtilTela.criarSubtitulo("Totem de Robôs Exploradores de Marte");
        JLabel lblInstrucao = new JLabel("Toque em um cartão para abrir a página do robô.", SwingConstants.CENTER);
        lblInstrucao.setForeground(UtilTela.COR_TEXTO);
        lblInstrucao.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblInstrucao.setAlignmentX(Component.CENTER_ALIGNMENT);

        pnlTopo.add(lblTitulo);
        pnlTopo.add(Box.createVerticalStrut(4));
        pnlTopo.add(lblSubtitulo);
        pnlTopo.add(Box.createVerticalStrut(8));
        pnlTopo.add(lblInstrucao);
        pnlTopo.add(Box.createVerticalStrut(12));

        JPanel pnlBusca = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        pnlBusca.setOpaque(false);

        txtBusca = new JTextField(16);
        txtBusca.setEditable(false);
        txtBusca.setFont(new Font("SansSerif", Font.BOLD, 16));
        txtBusca.setPreferredSize(new Dimension(180, 38));
        txtBusca.setToolTipText("Toque para usar o teclado virtual");
        txtBusca.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirTecladoVirtual();
            }
        });

        btnTeclado = UtilTela.criarBotaoSecundario("Teclado");
        btnLimparBusca = UtilTela.criarBotaoSecundario("Limpar");

        btnTeclado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTecladoVirtual();
            }
        });

        btnLimparBusca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtBusca.setText("");
                atualizarListaRobos("");
            }
        });

        pnlBusca.add(txtBusca);
        pnlBusca.add(btnTeclado);
        pnlBusca.add(btnLimparBusca);
        pnlTopo.add(pnlBusca);

        add(pnlTopo, BorderLayout.NORTH);

        pnlListaRobos = new JPanel();
        pnlListaRobos.setOpaque(false);
        pnlListaRobos.setLayout(new BoxLayout(pnlListaRobos, BoxLayout.Y_AXIS));
        pnlListaRobos.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JScrollPane scroll = new JScrollPane(pnlListaRobos);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        add(scroll, BorderLayout.CENTER);

        JPanel pnlRodape = new JPanel();
        pnlRodape.setOpaque(false);
        pnlRodape.setLayout(new BoxLayout(pnlRodape, BoxLayout.Y_AXIS));
        pnlRodape.setBorder(BorderFactory.createEmptyBorder(5, 15, 15, 15));

        btnQuestionario = UtilTela.criarBotaoGrande("Responder questionário");
        btnRelatorio = UtilTela.criarBotaoGrande("Ver relatório estatístico");
        btnSair = UtilTela.criarBotaoGrande("Sair");

        btnQuestionario.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRelatorio.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSair.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnQuestionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirQuestionario();
            }
        });

        btnRelatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirRelatorio();
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sairSistema();
            }
        });

        pnlRodape.add(btnQuestionario);
        pnlRodape.add(Box.createVerticalStrut(8));
        pnlRodape.add(btnRelatorio);
        pnlRodape.add(Box.createVerticalStrut(8));
        pnlRodape.add(btnSair);

        add(pnlRodape, BorderLayout.SOUTH);
    }

    protected void abrirTecladoVirtual() {
        FmlTecladoVirtual fmlTecladoVirtual = new FmlTecladoVirtual(txtBusca.getText());
        fmlTecladoVirtual.setLocationRelativeTo(this);
        fmlTecladoVirtual.setVisible(true);

        if (fmlTecladoVirtual.isConfirmado()) {
            txtBusca.setText(fmlTecladoVirtual.getTextoFinal());
            atualizarListaRobos(txtBusca.getText());
        }
    }

    protected void atualizarListaRobos(String filtro) {
        pnlListaRobos.removeAll();
        int quantidadeExibida = 0;

        for (int i = 0; i < listaRobos.size(); i++) {
            final int indiceRobo = i;
            final RoboExplorador robo = listaRobos.get(i);

            if (deveExibirRobo(robo, filtro)) {
                JButton btnRobo = new JButton(
                        "<html><div style='text-align:left;'><b>" + robo.getNome() +
                        "</b><br/>" + robo.getPlataforma() +
                        "<br/>Toque para ver detalhes</div></html>");
                btnRobo.setHorizontalAlignment(SwingConstants.LEFT);
                btnRobo.setFocusPainted(false);
                btnRobo.setBackground(UtilTela.COR_CARTAO);
                btnRobo.setForeground(UtilTela.COR_TEXTO);
                btnRobo.setFont(new Font("SansSerif", Font.BOLD, 17));
                btnRobo.setPreferredSize(new Dimension(360, 90));
                btnRobo.setMaximumSize(new Dimension(360, 90));
                btnRobo.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
                btnRobo.setAlignmentX(Component.CENTER_ALIGNMENT);

                btnRobo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        abrirDetalhesRobo(robo, indiceRobo);
                    }
                });

                pnlListaRobos.add(btnRobo);
                pnlListaRobos.add(Box.createVerticalStrut(10));
                quantidadeExibida++;
            }
        }

        if (quantidadeExibida == 0) {
            JLabel lblSemResultados = new JLabel("Nenhum robô encontrado com esse filtro.", SwingConstants.CENTER);
            lblSemResultados.setForeground(UtilTela.COR_TEXTO);
            lblSemResultados.setAlignmentX(Component.CENTER_ALIGNMENT);
            pnlListaRobos.add(lblSemResultados);
        }

        pnlListaRobos.revalidate();
        pnlListaRobos.repaint();
    }

    protected boolean deveExibirRobo(RoboExplorador robo, String filtro) {
        if (filtro == null || filtro.trim().isEmpty()) {
            return true;
        }
        return robo.getNome().toLowerCase().contains(filtro.trim().toLowerCase());
    }

    protected void abrirDetalhesRobo(RoboExplorador robo, int indiceRobo) {
        EstatisticasSistema.getInstancia().registrarVisualizacaoRobo(indiceRobo);
        FmlDetalhesRobo fmlDetalhesRobo = new FmlDetalhesRobo(robo, indiceRobo);
        fmlDetalhesRobo.setLocationRelativeTo(this);
        fmlDetalhesRobo.setVisible(true);
    }

    protected void abrirQuestionario() {
        FmlQuestionario fmlQuestionario = new FmlQuestionario();
        fmlQuestionario.setLocationRelativeTo(this);
        fmlQuestionario.setVisible(true);
    }

    protected void abrirRelatorio() {
        FmlRelatorio fmlRelatorio = new FmlRelatorio();
        fmlRelatorio.setLocationRelativeTo(this);
        fmlRelatorio.setVisible(true);
    }

    protected void sairSistema() {
        int resposta = JOptionPane.showConfirmDialog(this,
                "Deseja realmente sair do totem?",
                "Confirmar saída",
                JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            dispose();
        }
    }
}

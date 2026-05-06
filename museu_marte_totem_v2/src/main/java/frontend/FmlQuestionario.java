package frontend;

    import java.awt.BorderLayout;
    import java.awt.Font;
    import java.awt.GridLayout;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.util.Arrays;
    import java.util.List;
    import javax.swing.BorderFactory;
    import javax.swing.ButtonGroup;
    import javax.swing.JButton;
    import javax.swing.JDialog;
    import javax.swing.JLabel;
    import javax.swing.JOptionPane;
    import javax.swing.JPanel;
    import javax.swing.JRadioButton;
    import javax.swing.JTextArea;
    import pesquisa.CatalogoPerguntas;
    import pesquisa.EstatisticasSistema;
    import pesquisa.Pergunta;

    /**
     * Tela do questionário interativo.
     *
     * As respostas são guardadas em vetor de inteiros, sem gravação em disco.
     */
    public class FmlQuestionario extends JDialog {

        protected List<Pergunta> listaPerguntas;
        protected int[] respostas;
        protected int indicePerguntaAtual;

        protected JLabel lblProgresso;
        protected JTextArea txtPergunta;
        protected JRadioButton[] rdbOpcoes;
        protected ButtonGroup grupoOpcoes;
        protected JButton btnAnterior;
        protected JButton btnProximo;
        protected JButton btnFinalizar;

        public FmlQuestionario() {
            listaPerguntas = CatalogoPerguntas.getListaPerguntas();
            respostas = new int[listaPerguntas.size()];
            Arrays.fill(respostas, -1);
            indicePerguntaAtual = 0;

            UtilTela.configurarDialogo(this, "Questionário Interativo", 430, 620);
            setModal(true);
            montarTela();
            carregarPerguntaAtual();
        }

        protected void montarTela() {
            setLayout(new BorderLayout(10, 10));

            JPanel pnlTopo = new JPanel(new BorderLayout());
            pnlTopo.setOpaque(false);
            pnlTopo.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));
            pnlTopo.add(UtilTela.criarTitulo("Quiz de Marte"), BorderLayout.NORTH);

            lblProgresso = UtilTela.criarSubtitulo("Pergunta 1 de 5");
            pnlTopo.add(lblProgresso, BorderLayout.SOUTH);
            add(pnlTopo, BorderLayout.NORTH);

            JPanel pnlCentro = new JPanel(new BorderLayout(10, 10));
            pnlCentro.setOpaque(false);
            pnlCentro.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

            txtPergunta = new JTextArea();
            txtPergunta.setLineWrap(true);
            txtPergunta.setWrapStyleWord(true);
            txtPergunta.setEditable(false);
            txtPergunta.setFocusable(false);
            txtPergunta.setFont(new Font("SansSerif", Font.BOLD, 18));
            txtPergunta.setBackground(UtilTela.COR_CARTAO);
            txtPergunta.setForeground(UtilTela.COR_TEXTO);
            txtPergunta.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
            pnlCentro.add(txtPergunta, BorderLayout.NORTH);

            JPanel pnlOpcoes = new JPanel(new GridLayout(4, 1, 8, 8));
            pnlOpcoes.setOpaque(false);

            grupoOpcoes = new ButtonGroup();
            rdbOpcoes = new JRadioButton[4];

            for (int i = 0; i < rdbOpcoes.length; i++) {
                rdbOpcoes[i] = new JRadioButton();
                rdbOpcoes[i].setOpaque(true);
                rdbOpcoes[i].setBackground(UtilTela.COR_CARTAO);
                rdbOpcoes[i].setForeground(UtilTela.COR_TEXTO);
                rdbOpcoes[i].setFont(new Font("SansSerif", Font.PLAIN, 16));
                rdbOpcoes[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                grupoOpcoes.add(rdbOpcoes[i]);
                pnlOpcoes.add(rdbOpcoes[i]);
            }

            pnlCentro.add(pnlOpcoes, BorderLayout.CENTER);
            add(pnlCentro, BorderLayout.CENTER);

            JPanel pnlRodape = new JPanel();
            pnlRodape.setOpaque(false);
            btnAnterior = UtilTela.criarBotaoSecundario("Anterior");
            btnProximo = UtilTela.criarBotaoSecundario("Próxima");
            btnFinalizar = UtilTela.criarBotaoSecundario("Finalizar");

            btnAnterior.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    voltarPergunta();
                }
            });

            btnProximo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    avancarPergunta();
                }
            });

            btnFinalizar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    finalizarQuestionario();
                }
            });

            pnlRodape.add(btnAnterior);
            pnlRodape.add(btnProximo);
            pnlRodape.add(btnFinalizar);
            add(pnlRodape, BorderLayout.SOUTH);
        }

        protected void carregarPerguntaAtual() {
            Pergunta perguntaAtual = listaPerguntas.get(indicePerguntaAtual);
            lblProgresso.setText("Pergunta " + (indicePerguntaAtual + 1) + " de " + listaPerguntas.size());
            txtPergunta.setText(perguntaAtual.getEnunciado());

            grupoOpcoes.clearSelection();
            for (int i = 0; i < rdbOpcoes.length; i++) {
                rdbOpcoes[i].setText(perguntaAtual.getOpcoes()[i]);
                if (respostas[indicePerguntaAtual] == i) {
                    rdbOpcoes[i].setSelected(true);
                }
            }

            btnAnterior.setEnabled(indicePerguntaAtual > 0);
            btnProximo.setEnabled(indicePerguntaAtual < listaPerguntas.size() - 1);
            btnFinalizar.setEnabled(indicePerguntaAtual == listaPerguntas.size() - 1);
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

        protected void avancarPergunta() {
            if (!salvarRespostaAtual()) {
                JOptionPane.showMessageDialog(this,
                        "Selecione uma opção antes de continuar.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (indicePerguntaAtual < listaPerguntas.size() - 1) {
                indicePerguntaAtual++;
                carregarPerguntaAtual();
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
                JOptionPane.showMessageDialog(this,
                        "Selecione uma opção antes de finalizar.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int acertos = calcularAcertos();
            EstatisticasSistema.getInstancia().registrarQuestionario(respostas, acertos);

            JOptionPane.showMessageDialog(this,
                    montarMensagemResultado(acertos),
                    "Resultado do Questionário",
                    JOptionPane.INFORMATION_MESSAGE);

            FmlAvaliacaoFinal fmlAvaliacaoFinal = new FmlAvaliacaoFinal();
            fmlAvaliacaoFinal.setLocationRelativeTo(this);
            fmlAvaliacaoFinal.setVisible(true);
            dispose();
        }

        protected int calcularAcertos() {
            int acertos = 0;
            for (int i = 0; i < listaPerguntas.size(); i++) {
                if (respostas[i] == listaPerguntas.get(i).getIndiceCorreto()) {
                    acertos++;
                }
            }
            return acertos;
        }

        protected String montarMensagemResultado(int acertos) {
            String mensagem = "Você acertou " + acertos + " de " + listaPerguntas.size() + " pergunta(s).";

            if (acertos == 5) {
                mensagem += "Excelente! Você conhece muito bem os exploradores de Marte.";
            } else if (acertos >= 3) {
                mensagem += "Muito bom! Você aprendeu bastante com a exposição.";
            } else {
                mensagem += "Obrigado por participar! Continue explorando a exposição para aprender ainda mais.";
            }

            return mensagem;
        }
    }

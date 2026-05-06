package frontend;

    import java.awt.BorderLayout;
    import java.awt.FlowLayout;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import javax.swing.BorderFactory;
    import javax.swing.JButton;
    import javax.swing.JDialog;
    import javax.swing.JLabel;
    import javax.swing.JOptionPane;
    import javax.swing.JPanel;
    import pesquisa.EstatisticasSistema;

    /**
     * Tela de avaliação final da experiência do visitante.
     */
    public class FmlAvaliacaoFinal extends JDialog {

        protected PainelEstrelas pnlEstrelas;
        protected JButton btnConcluir;

        public FmlAvaliacaoFinal() {
            UtilTela.configurarDialogo(this, "Avaliação Final", 420, 340);
            setModal(true);
            montarTela();
        }

        protected void montarTela() {
            setLayout(new BorderLayout(10, 10));

            JPanel pnlCentro = new JPanel();
            pnlCentro.setOpaque(false);
            pnlCentro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            pnlCentro.setLayout(new BorderLayout(10, 10));

            JLabel lblTitulo = UtilTela.criarTitulo("Avalie a exposição");
            JLabel lblSubtitulo = UtilTela.criarSubtitulo("Dê uma nota geral de 1 a 5 estrelas");

            JPanel pnlTextos = new JPanel(new BorderLayout());
            pnlTextos.setOpaque(false);
            pnlTextos.add(lblTitulo, BorderLayout.NORTH);
            pnlTextos.add(lblSubtitulo, BorderLayout.CENTER);

            pnlEstrelas = new PainelEstrelas();

            pnlCentro.add(pnlTextos, BorderLayout.NORTH);
            pnlCentro.add(pnlEstrelas, BorderLayout.CENTER);

            add(pnlCentro, BorderLayout.CENTER);

            JPanel pnlRodape = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            pnlRodape.setOpaque(false);
            btnConcluir = UtilTela.criarBotaoGrande("Finalizar visita");
            btnConcluir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    concluirAvaliacao();
                }
            });
            pnlRodape.add(btnConcluir);

            add(pnlRodape, BorderLayout.SOUTH);
        }

        protected void concluirAvaliacao() {
            int nota = pnlEstrelas.getNotaSelecionada();
            if (nota <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Escolha de 1 a 5 estrelas antes de finalizar.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            EstatisticasSistema.getInstancia().registrarNotaFinal(nota);

            JOptionPane.showMessageDialog(this, "Obrigado pela visita ao museu! Sua opinião é muito importante. O sistema retornará ao início.", "Mensagem final", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

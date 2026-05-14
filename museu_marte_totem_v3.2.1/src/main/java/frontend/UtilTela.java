package frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Classe utilitária com recursos visuais simples.
 */
public class UtilTela {

    public static final Color COR_FUNDO = new Color(24, 24, 28);
    public static final Color COR_CARTAO = new Color(39, 39, 46);
    public static final Color COR_TEXTO = new Color(245, 245, 245);
    public static final Color COR_PRIMARIA = new Color(255, 0, 15);
    public static final Color COR_SECUNDARIA = new Color(103, 100, 246);
    public static final Color COR_DESTAQUE = new Color(255, 200, 0);

    public static void configurarDialogo(JDialog dialogo, String titulo, int largura, int altura) {
        dialogo.setTitle(titulo);
        dialogo.setSize(largura, altura);
        dialogo.setResizable(false);
        dialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogo.getContentPane().setBackground(COR_FUNDO);
        dialogo.setLocationRelativeTo(null);
    }

    public static JButton criarBotaoGrande(String texto) {
        JButton botao = new JButton(texto);
        botao.setFocusPainted(false);
        botao.setBackground(COR_PRIMARIA);
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("SansSerif", Font.BOLD, 18));
        botao.setPreferredSize(new Dimension(320, 60));
        botao.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        return botao;
    }

    public static JButton criarBotaoSecundario(String texto) {
        JButton botao = new JButton(texto);
        botao.setFocusPainted(false);
        botao.setBackground(COR_SECUNDARIA);
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("SansSerif", Font.BOLD, 16));
        botao.setPreferredSize(new Dimension(160, 50));
        return botao;
    }

    public static JLabel criarTitulo(String texto) {
        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setForeground(COR_TEXTO);
        label.setFont(new Font("SansSerif", Font.BOLD, 26));
        return label;
    }

    public static JLabel criarSubtitulo(String texto) {
        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setForeground(COR_TEXTO);
        label.setFont(new Font("SansSerif", Font.PLAIN, 15));
        return label;
    }

    public static ImageIcon carregarIcone(String caminho, int largura, int altura) {
        try {
            URL url = UtilTela.class.getResource(caminho);
            if (url == null) {
                return null;
            }
            ImageIcon iconeOriginal = new ImageIcon(url);
            Image imagem = iconeOriginal.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            return new ImageIcon(imagem);
        } catch (Exception e) {
            return null;
        }
    }
}

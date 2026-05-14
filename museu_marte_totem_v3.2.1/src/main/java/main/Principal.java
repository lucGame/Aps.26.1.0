package main;

import frontend.FmlMenuPrincipal;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Classe principal do sistema.
 *
 * O objetivo desta classe é iniciar o programa, aplicar um visual padrão
 * do sistema operacional e abrir a tela principal do totem.
 */
public class Principal {

    /**
     * Método principal do programa.
     *
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                iniciarSistema();
            }
        });
    }

    /**
     * Configura o visual do sistema e abre o menu principal.
     */
    protected static void iniciarSistema() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Não foi possível aplicar o tema do sistema.");
        }

        FmlMenuPrincipal fmlMenuPrincipal = new FmlMenuPrincipal();
        fmlMenuPrincipal.setVisible(true);
        System.exit(0);
    }
}

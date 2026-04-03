package main;

import vista.FrmLogin;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {
        // 1. Opcional: Aplicar el estilo visual del sistema operativo
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("No se pudo establecer el estilo visual: " + e.getMessage());
        }

        // 2. Ejecutar la aplicación en el hilo de eventos de Swing (Buenas prácticas)
        java.awt.EventQueue.invokeLater(() -> {
            // Creamos la instancia del Login
            FrmLogin login = new FrmLogin();
            
            // Lo hacemos visible
            login.setVisible(true);
        });
    }
}
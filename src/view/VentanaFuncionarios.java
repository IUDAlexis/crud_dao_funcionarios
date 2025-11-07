package src.view;

import javax.swing.*;

import src.App;

import java.awt.*;

public class VentanaFuncionarios extends JFrame {

    public VentanaFuncionarios() {
        super("Gestión de Funcionarios");

        // Configuración de la ventana
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Título o etiqueta principal
        JLabel label = new JLabel("Ventana de Funcionarios", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        // Botón para volver
        JButton btnVolver = new JButton("Volver");
        add(btnVolver, BorderLayout.SOUTH);

        // Acción del botón volver
        btnVolver.addActionListener(e -> {
            dispose(); // Cierra esta ventana
            App.main(null); // Vuelve a la ventana principal
        });

        setVisible(true);
    }
}

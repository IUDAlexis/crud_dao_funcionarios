package src;

import javax.swing.*;

import src.view.VentanaFuncionarios;

import java.awt.*;
import java.awt.event.*;

// Clase principal
public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Mi App Local en Java");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JLabel label = new JLabel("¡Hola desde tu aplicación Java local!", SwingConstants.CENTER);
        frame.add(label, BorderLayout.CENTER);
        JButton btnFuncionarios = new JButton("Ir a Funcionarios");
        frame.add(btnFuncionarios, BorderLayout.SOUTH);

        btnFuncionarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new VentanaFuncionarios();
            }
        });

        frame.setVisible(true);
    }
}

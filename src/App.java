import javax.swing.*;
import java.awt.*;
import view.VentanaFuncionarios;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::crearVentanaPrincipal);
    }

    private static void crearVentanaPrincipal() {
        JFrame frame = new JFrame("Menú Principal - IUDigital");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        JPanel cardFuncionarios = crearCard("Funcionarios", "src/img/logo.png");
        JButton btnFuncionarios = crearBoton("Funcionarios");
        btnFuncionarios.addActionListener(e -> {
            frame.setVisible(false);
            new VentanaFuncionarios(frame).setVisible(true);
        });
        cardFuncionarios.add(btnFuncionarios, BorderLayout.SOUTH);

        JPanel cardGrupo = crearCard("Grupo Familiar", "src/img/grupoFamiliar.png");
        JButton btnGrupo = crearBoton("Registrar Grupo Familiar");
        btnGrupo.addActionListener(e -> {
            frame.setVisible(false);
        });
        cardGrupo.add(btnGrupo, BorderLayout.SOUTH);

        JPanel cardFormacion = crearCard("Estado de Formación", "src/img/estadoFormacion.jpg");
        JButton btnFormacion = crearBoton("Agregar Estado de Formación");
        btnFormacion.addActionListener(e -> {
            frame.setVisible(false);
        });
        cardFormacion.add(btnFormacion, BorderLayout.SOUTH);

        // Agregar las cards al frame
        gbc.gridx = 0; gbc.gridy = 0; frame.add(cardFuncionarios, gbc);
        gbc.gridx = 1; gbc.gridy = 0; frame.add(cardGrupo, gbc);
        gbc.gridx = 2; gbc.gridy = 0; frame.add(cardFormacion, gbc);

        frame.setVisible(true);
    }

    private static JPanel crearCard(String titulo, String rutaImagen) {
        JPanel card = new JPanel(new BorderLayout()) {
            private Image fondo = new ImageIcon(rutaImagen).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (fondo != null) {
                    g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        card.setPreferredSize(new Dimension(250, 250));
        card.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK, 2),
            titulo,
            0, 0, new Font("Segoe UI", Font.BOLD, 14)
        ));
        card.setBackground(Color.WHITE);
        return card;
    }

    private static JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(new Color(59, 89, 182));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(200, 40));
        return btn;
    }
}

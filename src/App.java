import javax.swing.*;
import java.awt.*;
import view.VentanaFuncionarios;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::crearVentanaPrincipal);
    }

    private static void crearVentanaPrincipal() {
        JFrame frame = new JFrame("Menú Principal - IUDigital");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());
        JPanel card = new JPanel() {
            private Image fondo;
            {
                fondo = new ImageIcon("src/img/logo.png").getImage(); // ← ajusta tu ruta
            }
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (fondo != null) {
                    g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        card.setPreferredSize(new Dimension(400, 300));
        card.setLayout(new GridBagLayout()); 
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JButton btnFuncionarios = new JButton("Ir a Funcionarios");
        btnFuncionarios.setBackground(new Color(59, 89, 182));
        btnFuncionarios.setForeground(Color.WHITE);
        btnFuncionarios.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnFuncionarios.setFocusPainted(false);
        btnFuncionarios.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnFuncionarios.addActionListener(e -> {
            frame.setVisible(false);
            new VentanaFuncionarios(frame).setVisible(true);
        });
        card.add(btnFuncionarios);
        frame.add(card);
        frame.setVisible(true);
    }
}

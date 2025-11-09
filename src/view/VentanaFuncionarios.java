package view;

import dao.FuncionarioDaoJDBC;
import model.Funcionario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.List;

public class VentanaFuncionarios extends JFrame {

    private JTextField txtId, txtNumeroId, txtNombres, txtApellidos, txtEstadoCivil,
            txtDireccion, txtTelefono, txtFechaNacimiento;
    private JComboBox<String> cbTipoId, cbSexo;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private FuncionarioDaoJDBC dao;
    private JFrame ventanaMenu;

    public VentanaFuncionarios(JFrame ventanaMenu) {
        this.ventanaMenu = ventanaMenu;
        setTitle("Gestión de Funcionarios - IUDigital");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        inicializarDAO();
        inicializarComponentes();
        cargarTabla();
    }

    private void inicializarDAO() {
        try {
            dao = new FuncionarioDaoJDBC();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al realizar la conexión con la base de datos:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void inicializarComponentes() {
        JPanel panelForm = new JPanel(new GridLayout(10, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos del Funcionario"));

        txtId = new JTextField();
        txtId.setEditable(false);

        String[] tiposId = {"CC", "TI", "PA"};
        cbTipoId = new JComboBox<>(tiposId);

        txtNumeroId = new JTextField();
        txtNombres = new JTextField();
        txtApellidos = new JTextField();
        txtEstadoCivil = new JTextField();

        String[] sexos = {"F", "M", "Otro"};
        cbSexo = new JComboBox<>(sexos);

        txtDireccion = new JTextField();
        txtTelefono = new JTextField();

        try {
            MaskFormatter fechaMask = new MaskFormatter("####-##-##");
            fechaMask.setPlaceholderCharacter('_');
            txtFechaNacimiento = new JFormattedTextField(fechaMask);
        } catch (Exception ex) {
            txtFechaNacimiento = new JFormattedTextField();
        }
        panelForm.add(new JLabel("ID:")).setVisible(false); panelForm.add(txtId).setVisible(false); 
        panelForm.add(new JLabel("Tipo Identificación:")); panelForm.add(cbTipoId);
        panelForm.add(new JLabel("Número Identificación:")); panelForm.add(txtNumeroId);
        panelForm.add(new JLabel("Nombres:")); panelForm.add(txtNombres);
        panelForm.add(new JLabel("Apellidos:")); panelForm.add(txtApellidos);
        panelForm.add(new JLabel("Estado Civil:")); panelForm.add(txtEstadoCivil);
        panelForm.add(new JLabel("Sexo:")); panelForm.add(cbSexo);
        panelForm.add(new JLabel("Dirección:")); panelForm.add(txtDireccion);
        panelForm.add(new JLabel("Teléfono:")); panelForm.add(txtTelefono);
        panelForm.add(new JLabel("Fecha Nacimiento:")); panelForm.add(txtFechaNacimiento);

        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnGuardar = new JButton("Guardar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");
        JButton btnVolver = new JButton("Volver al Menú");
        JButton btnEstudios = new JButton("Registrar Estudios");
        JButton btnMiembros = new JButton("Registrar Miembros Familiares");

        panelBotones.add(btnGuardar).setBackground(new Color(34, 139, 34)); btnGuardar.setForeground(Color.WHITE);
        panelBotones.add(btnActualizar).setBackground(new Color(34, 139, 34)); btnActualizar.setForeground(Color.WHITE);
        panelBotones.add(btnEliminar).setBackground(new Color(59, 89, 182)); btnEliminar.setForeground(Color.WHITE);
        panelBotones.add(btnLimpiar).setBackground(new Color(59, 89, 182)); btnLimpiar.setForeground(Color.WHITE);
        panelBotones.add(btnEstudios).setBackground(new Color(59, 89, 182)); btnEstudios.setForeground(Color.WHITE);
        panelBotones.add(btnMiembros).setBackground(new Color(59, 89, 182)); btnMiembros.setForeground(Color.WHITE);
        panelBotones.add(btnVolver).setBackground(new Color(220, 20, 60)); btnVolver.setForeground(Color.WHITE);


        // TABLA
        modeloTabla = new DefaultTableModel(
                new String[]{"ID", "Tipo ID", "Número Identificación", "Nombres", "Apellidos",
                        "Estado Civil", "Sexo", "Dirección", "Teléfono", "Fecha Nacimiento"}, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createTitledBorder("Listado de Funcionarios"));
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(0).setWidth(0);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
            
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    txtId.setText(String.valueOf(modeloTabla.getValueAt(fila, 0)));
                    cbTipoId.setSelectedItem(modeloTabla.getValueAt(fila, 1).toString());
                    txtNumeroId.setText(String.valueOf(modeloTabla.getValueAt(fila, 2)));
                    txtNombres.setText(String.valueOf(modeloTabla.getValueAt(fila, 3)));
                    txtApellidos.setText(String.valueOf(modeloTabla.getValueAt(fila, 4)));
                    txtEstadoCivil.setText(String.valueOf(modeloTabla.getValueAt(fila, 5)));
                    cbSexo.setSelectedItem(modeloTabla.getValueAt(fila, 6).toString());
                    txtDireccion.setText(String.valueOf(modeloTabla.getValueAt(fila, 7)));
                    txtTelefono.setText(String.valueOf(modeloTabla.getValueAt(fila, 8)));
                    txtFechaNacimiento.setText(String.valueOf(modeloTabla.getValueAt(fila, 9)));
                }
            }
        });

        // ACCIONES BOTONES
        btnGuardar.addActionListener(e -> guardarFuncionario());
        btnActualizar.addActionListener(e -> actualizarFuncionario());
        btnEliminar.addActionListener(e -> eliminarFuncionario());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnVolver.addActionListener(e -> volverAlMenu());

        add(panelForm, BorderLayout.WEST);
        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void guardarFuncionario() {
        try {
            Funcionario f = new Funcionario();
            f.setTipoIdentificacion((String) cbTipoId.getSelectedItem());
            f.setNumeroIdentificacion(txtNumeroId.getText());
            f.setNombres(txtNombres.getText());
            f.setApellidos(txtApellidos.getText());
            f.setEstadoCivil(txtEstadoCivil.getText());
            f.setSexo((String) cbSexo.getSelectedItem());
            f.setDireccion(txtDireccion.getText());
            f.setTelefono(txtTelefono.getText());
            f.setFechaNacimiento(LocalDate.parse(txtFechaNacimiento.getText()));

            dao.insertar(f);
            JOptionPane.showMessageDialog(this, "Funcionario guardado correctamente");
            cargarTabla();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar los datos: " + ex.getMessage());
        }
    }

    private void actualizarFuncionario() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un funcionario para actualizar");
            return;
        }
        try {
            Funcionario f = new Funcionario();
            f.setIdFuncionario(Integer.parseInt(txtId.getText()));
            f.setTipoIdentificacion((String) cbTipoId.getSelectedItem());
            f.setNumeroIdentificacion(txtNumeroId.getText());
            f.setNombres(txtNombres.getText());
            f.setApellidos(txtApellidos.getText());
            f.setEstadoCivil(txtEstadoCivil.getText());
            f.setSexo((String) cbSexo.getSelectedItem());
            f.setDireccion(txtDireccion.getText());
            f.setTelefono(txtTelefono.getText());
            f.setFechaNacimiento(LocalDate.parse(txtFechaNacimiento.getText()));

            dao.actualizar(f);
            JOptionPane.showMessageDialog(this, "Funcionario actualizado correctamente");
            cargarTabla();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar los datos: " + ex.getMessage());
        }
    }

    private void eliminarFuncionario() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un funcionario para eliminar");
            return;
        }
        int id = Integer.parseInt(txtId.getText());
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar funcionario?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                dao.eliminar(id);
                JOptionPane.showMessageDialog(this, "Registro de funcionario eliminado correctamente");
                cargarTabla();
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar los datos: " + ex.getMessage());
            }
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        cbTipoId.setSelectedIndex(0);
        txtNumeroId.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtEstadoCivil.setText("");
        cbSexo.setSelectedIndex(0);
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtFechaNacimiento.setText("");
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        try {
            List<Funcionario> lista = dao.listarTodos();
            for (Funcionario f : lista) {
                modeloTabla.addRow(new Object[]{
                        f.getIdFuncionario(),
                        f.getTipoIdentificacion(),
                        f.getNumeroIdentificacion(),
                        f.getNombres(),
                        f.getApellidos(),
                        f.getEstadoCivil(),
                        f.getSexo(),
                        f.getDireccion(),
                        f.getTelefono(),
                        f.getFechaNacimiento()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar la información de la tabla: " + ex.getMessage());
        }
    }

    private void volverAlMenu() {
        dispose();
        if (ventanaMenu != null) ventanaMenu.setVisible(true);
    }
}

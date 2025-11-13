package view;

import dao.FuncionarioDaoJDBC;
import dao.TipoDocumentoJDBC;
import dao.EstadoCivilJDBC;
import model.EstadoCivil;
import model.Funcionario;
import model.TipoDocumento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.List;

public class VentanaFuncionarios extends JFrame {

    private JTextField txtId, txtNumeroId, txtNombres, txtApellidos,
            txtDireccion, txtTelefono;
    private JFormattedTextField txtFechaNacimiento;
    private JComboBox<String> cbTipoId;
    private JComboBox<String> cbEstadoCivil;
    private JComboBox<String> cbSexo;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private FuncionarioDaoJDBC dao;
    private TipoDocumentoJDBC daoTipo;
    private EstadoCivilJDBC daoEstado;
    private JFrame ventanaMenu;

    public VentanaFuncionarios(JFrame ventanaMenu) {
        this.ventanaMenu = ventanaMenu;
        setTitle("Gestión de Funcionarios - IUDigital");
        setSize(1200, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        inicializarDAO();
        inicializarComponentes();
        cargarListas();
        cargarTabla();
    }

    private void inicializarDAO() {
        try {
            dao = new FuncionarioDaoJDBC();
            daoTipo = new TipoDocumentoJDBC();
            daoEstado = new EstadoCivilJDBC();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al conectar con la base de datos:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void inicializarComponentes() {
        JPanel panelForm = new JPanel(new GridLayout(10, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos del Funcionario"));

        txtId = new JTextField();
        txtId.setEditable(false);

        cbTipoId = new JComboBox<>();
        cbEstadoCivil = new JComboBox<>();

        txtNumeroId = new JTextField();
        txtNombres = new JTextField();
        txtApellidos = new JTextField();

        String[] sexos = {
                "Femenino", "Masculino", "No binario",
                "Transgénero", "Transexual", "Intersexual",
                "Prefiero no decirlo", "Otro"
        };
        cbSexo = new JComboBox<>(sexos);

        txtDireccion = new JTextField();
        txtTelefono = new JTextField();

        try {
            MaskFormatter maskFecha = new MaskFormatter("####-##-##");
            maskFecha.setPlaceholderCharacter('_');
            txtFechaNacimiento = new JFormattedTextField(maskFecha);
        } catch (Exception e) {
            txtFechaNacimiento = new JFormattedTextField();
        }
        panelForm.add(new JLabel("ID *:"));
        panelForm.add(txtId);
        panelForm.add(new JLabel("Tipo Documento *:"));
        panelForm.add(cbTipoId);
        panelForm.add(new JLabel("Número Identificación *:"));
        panelForm.add(txtNumeroId);
        panelForm.add(new JLabel("Nombres *:"));
        panelForm.add(txtNombres);
        panelForm.add(new JLabel("Apellidos *:"));
        panelForm.add(txtApellidos);
        panelForm.add(new JLabel("Estado Civil *:"));
        panelForm.add(cbEstadoCivil);
        panelForm.add(new JLabel("Sexo *:"));
        panelForm.add(cbSexo);
        panelForm.add(new JLabel("Dirección *:"));
        panelForm.add(txtDireccion);
        panelForm.add(new JLabel("Teléfono *:"));
        panelForm.add(txtTelefono);
        panelForm.add(new JLabel("Fecha Nacimiento (yyyy-MM-dd) *:"));
        panelForm.add(txtFechaNacimiento);

        // BOTONES
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnGuardar = new JButton("Guardar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");
        JButton btnEstudios = new JButton("Agregar Estado de Formación");
        JButton btnMiembros = new JButton("Registrar Grupo Familiar");
        JButton btnVolver = new JButton("Volver al Menú");

        panelBotones.add(btnGuardar).setBackground(new Color(34, 139, 34)); btnGuardar.setForeground(Color.WHITE);
        panelBotones.add(btnActualizar).setBackground(new Color(34, 139, 34)); btnActualizar.setForeground(Color.WHITE);
        panelBotones.add(btnEliminar).setBackground(new Color(59, 89, 182)); btnEliminar.setForeground(Color.WHITE);
        panelBotones.add(btnLimpiar).setBackground(new Color(59, 89, 182)); btnLimpiar.setForeground(Color.WHITE);
        panelBotones.add(btnEstudios).setBackground(new Color(59, 89, 182)); btnEstudios.setForeground(Color.WHITE);
        panelBotones.add(btnMiembros).setBackground(new Color(59, 89, 182)); btnMiembros.setForeground(Color.WHITE);
        panelBotones.add(btnVolver).setBackground(new Color(220, 20, 60)); btnVolver.setForeground(Color.WHITE);
        // TABLA
        modeloTabla = new DefaultTableModel(
                new String[] { "ID", "Tipo Doc", "Número", "Nombres", "Apellidos",
                        "Estado Civil", "Sexo", "Dirección", "Teléfono", "Fecha Nacimiento" },
                0);
        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(28);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        int[] anchos = { 60, 100, 120, 150, 150, 120, 100, 150, 120, 150 };
        for (int i = 0; i < anchos.length; i++) {
            tabla.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        JScrollPane scroll = new JScrollPane(tabla,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(850, 400));
        scroll.setBorder(BorderFactory.createTitledBorder("Listado de Funcionarios"));

        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    txtId.setText(modeloTabla.getValueAt(fila, 0).toString());
                    cbTipoId.setSelectedItem(modeloTabla.getValueAt(fila, 1).toString());
                    txtNumeroId.setText(modeloTabla.getValueAt(fila, 2).toString());
                    txtNombres.setText(modeloTabla.getValueAt(fila, 3).toString());
                    txtApellidos.setText(modeloTabla.getValueAt(fila, 4).toString());
                    cbEstadoCivil.setSelectedItem(modeloTabla.getValueAt(fila, 5).toString());
                    cbSexo.setSelectedItem(modeloTabla.getValueAt(fila, 6).toString());
                    txtDireccion.setText(modeloTabla.getValueAt(fila, 7).toString());
                    txtTelefono.setText(modeloTabla.getValueAt(fila, 8).toString());
                    txtFechaNacimiento.setText(modeloTabla.getValueAt(fila, 9).toString());
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

    private void cargarListas() {
        try {
            cbTipoId.removeAllItems();
            cbEstadoCivil.removeAllItems();

            for (TipoDocumento t : daoTipo.listarTodos())
                cbTipoId.addItem(t.getNombre());
            for (EstadoCivil e : daoEstado.listarTodos())
                cbEstadoCivil.addItem(e.getNombre());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error cargando listas: " + e.getMessage());
        }
    }

    private boolean validarCampos() {
        if (cbTipoId.getSelectedItem() == null ||
                cbEstadoCivil.getSelectedItem() == null ||
                txtNumeroId.getText().trim().isEmpty() ||
                txtNombres.getText().trim().isEmpty() ||
                txtApellidos.getText().trim().isEmpty() ||
                txtDireccion.getText().trim().isEmpty() ||
                txtTelefono.getText().trim().isEmpty() ||
                txtFechaNacimiento.getText().contains("_")) {
            JOptionPane.showMessageDialog(this,
                    "Todos los campos marcados con * son obligatorios.",
                    "Campos requeridos", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try {
            LocalDate.parse(txtFechaNacimiento.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "La fecha debe tener formato válido (yyyy-MM-dd).",
                    "Error de formato", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void guardarFuncionario() {
        try {
            if (!validarCampos())
                return;
            if (Long.parseLong(txtId.getText()) > 0) {
                JOptionPane.showMessageDialog(this, "EL Funcionario ya existe, por favor use el boton actualizar");
                return;
            }
            Funcionario f = new Funcionario();
            TipoDocumento tipo = obtenerTipoDocumentoPorNombre(cbTipoId.getSelectedItem().toString());
            EstadoCivil estado = obtenerEstadoCivilPorNombre(cbEstadoCivil.getSelectedItem().toString());

            f.setTipoDocumento(tipo);
            f.setEstadoCivil(estado);
            f.setNumeroIdentificacion(txtNumeroId.getText().trim());
            f.setNombres(txtNombres.getText().trim());
            f.setApellidos(txtApellidos.getText().trim());
            f.setSexo(cbSexo.getSelectedItem().toString());
            f.setDireccion(txtDireccion.getText().trim());
            f.setTelefono(txtTelefono.getText().trim());
            f.setFechaNacimiento(LocalDate.parse(txtFechaNacimiento.getText()));

            dao.insertar(f);
            JOptionPane.showMessageDialog(this, "Funcionario guardado correctamente");
            cargarTabla();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
        }
    }

    private void actualizarFuncionario() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un funcionario para actualizar");
            return;
        }
        try {
            if (!validarCampos())
                return;

            Funcionario f = new Funcionario();
            f.setFuncionarioId(Long.parseLong(txtId.getText()));
            f.setTipoDocumento(obtenerTipoDocumentoPorNombre(cbTipoId.getSelectedItem().toString()));
            f.setEstadoCivil(obtenerEstadoCivilPorNombre(cbEstadoCivil.getSelectedItem().toString()));
            f.setNumeroIdentificacion(txtNumeroId.getText().trim());
            f.setNombres(txtNombres.getText().trim());
            f.setApellidos(txtApellidos.getText().trim());
            f.setSexo(cbSexo.getSelectedItem().toString());
            f.setDireccion(txtDireccion.getText().trim());
            f.setTelefono(txtTelefono.getText().trim());
            f.setFechaNacimiento(LocalDate.parse(txtFechaNacimiento.getText()));

            dao.actualizar(f);
            JOptionPane.showMessageDialog(this, "Funcionario actualizado correctamente");
            cargarTabla();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage());
        }
    }

    private void eliminarFuncionario() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un funcionario para eliminar");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar funcionario?", "Confirmar",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                dao.eliminar(Long.parseLong(txtId.getText()));
                JOptionPane.showMessageDialog(this, "Funcionario eliminado correctamente");
                cargarTabla();
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
            }
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        cbTipoId.setSelectedIndex(0);
        cbEstadoCivil.setSelectedIndex(0);
        txtNumeroId.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
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
                modeloTabla.addRow(new Object[] {
                        f.getFuncionarioId(),
                        f.getTipoDocumento().getNombre(),
                        f.getNumeroIdentificacion(),
                        f.getNombres(),
                        f.getApellidos(),
                        f.getEstadoCivil().getNombre(),
                        f.getSexo(),
                        f.getDireccion(),
                        f.getTelefono(),
                        f.getFechaNacimiento()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar tabla: " + ex.getMessage());
        }
    }

    private void volverAlMenu() {
        dispose();
        if (ventanaMenu != null)
            ventanaMenu.setVisible(true);
    }

    private TipoDocumento obtenerTipoDocumentoPorNombre(String nombre) {
        try {
            for (TipoDocumento t : daoTipo.listarTodos()) {
                if (t.getNombre().equalsIgnoreCase(nombre)) {
                    return t;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error obteniendo TipoDocumento: " + e.getMessage());
        }
        return null;
    }

    private EstadoCivil obtenerEstadoCivilPorNombre(String nombre) {
        try {
            for (EstadoCivil e : daoEstado.listarTodos()) {
                if (e.getNombre().equalsIgnoreCase(nombre)) {
                    return e;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error obteniendo EstadoCivil: " + e.getMessage());
        }
        return null;
    }
}

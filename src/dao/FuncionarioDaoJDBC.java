package dao;

import model.Funcionario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ui.ConexionDB;
public class FuncionarioDaoJDBC {

    public void insertar(Funcionario f) throws SQLException {
        String sql = "INSERT INTO funcionarios (tipo_identificacion, numero_identificacion, nombres, apellidos, estado_civil, sexo, direccion, telefono, fecha_nacimiento) VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, f.getTipoIdentificacion());
            ps.setString(2, f.getNumeroIdentificacion());
            ps.setString(3, f.getNombres());
            ps.setString(4, f.getApellidos());
            ps.setString(5, f.getEstadoCivil());
            ps.setString(6, f.getSexo());
            ps.setString(7, f.getDireccion());
            ps.setString(8, f.getTelefono());
            ps.setDate(9, Date.valueOf(f.getFechaNacimiento()));
            ps.executeUpdate();
        }
    }

    public void actualizar(Funcionario f) throws SQLException {
        String sql = "UPDATE funcionarios SET tipo_identificacion=?, numero_identificacion=?, nombres=?, apellidos=?, estado_civil=?, sexo=?, direccion=?, telefono=?, fecha_nacimiento=? WHERE id_Funcionario=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, f.getTipoIdentificacion());
            ps.setString(2, f.getNumeroIdentificacion());
            ps.setString(3, f.getNombres());
            ps.setString(4, f.getApellidos());
            ps.setString(5, f.getEstadoCivil());
            ps.setString(6, f.getSexo());
            ps.setString(7, f.getDireccion());
            ps.setString(8, f.getTelefono());
            ps.setDate(9, Date.valueOf(f.getFechaNacimiento()));
            ps.setInt(10, f.getIdFuncionario());
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM funcionarios WHERE id_Funcionario=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Funcionario> listarTodos() throws SQLException {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";
        try (Connection con = ConexionDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setIdFuncionario(rs.getInt("id_Funcionario"));
                f.setTipoIdentificacion(rs.getString("tipo_identificacion"));
                f.setNumeroIdentificacion(rs.getString("numero_identificacion"));
                f.setNombres(rs.getString("nombres"));
                f.setApellidos(rs.getString("apellidos"));
                f.setEstadoCivil(rs.getString("estado_civil"));
                f.setSexo(rs.getString("sexo"));
                f.setDireccion(rs.getString("direccion"));
                f.setTelefono(rs.getString("telefono"));
                f.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                lista.add(f);
            }
        }
        return lista;
    }
}

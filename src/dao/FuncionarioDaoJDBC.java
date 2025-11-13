package dao;

import model.Funcionario;
import model.EstadoCivil;
import model.TipoDocumento;
import ui.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDaoJDBC {
    public void insertar(Funcionario f) throws SQLException {
        String sql = "INSERT INTO funcionarios (numero_identificacion, nombres, apellidos, sexo, direccion, telefono, fecha_nacimiento, estado_civil_id, tipo_documento_id) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, f.getNumeroIdentificacion());
            ps.setString(2, f.getNombres());
            ps.setString(3, f.getApellidos());
            ps.setString(4, f.getSexo());
            ps.setString(5, f.getDireccion());
            ps.setString(6, f.getTelefono());
            ps.setDate(7, Date.valueOf(f.getFechaNacimiento()));
            ps.setLong(8, f.getEstadoCivil().getEstadoCivilId());
            ps.setLong(9, f.getTipoDocumento().getTipoDocumentoId());
            ps.executeUpdate();
        }
    }

    public void actualizar(Funcionario f) throws SQLException {
        String sql = "UPDATE funcionarios SET numero_identificacion=?, nombres=?, apellidos=?, sexo=?, direccion=?, telefono=?, fecha_nacimiento=?, estado_civil_id=?, tipo_documento_id=? "
                   + "WHERE funcionario_id=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, f.getNumeroIdentificacion());
            ps.setString(2, f.getNombres());
            ps.setString(3, f.getApellidos());
            ps.setString(4, f.getSexo());
            ps.setString(5, f.getDireccion());
            ps.setString(6, f.getTelefono());
            ps.setDate(7, Date.valueOf(f.getFechaNacimiento()));
            ps.setLong(8, f.getEstadoCivil().getEstadoCivilId());
            ps.setLong(9, f.getTipoDocumento().getTipoDocumentoId());
            ps.setLong(10, f.getFuncionarioId());
            ps.executeUpdate();
        }
    }

    public void eliminar(long id) throws SQLException {
        String sql = "DELETE FROM funcionarios WHERE funcionario_id=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    public List<Funcionario> listarTodos() throws SQLException {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT f.funcionario_id, f.numero_identificacion, f.nombres, f.apellidos, f.sexo, f.direccion, f.telefono, f.fecha_nacimiento, "
                   + "ec.estado_civil_id, ec.nombre AS estado_civil_nombre, "
                   + "td.tipo_documento_id, td.nombre AS tipo_documento_nombre "
                   + "FROM funcionarios f "
                   + "LEFT JOIN estado_civil ec ON f.estado_civil_id = ec.estado_civil_id "
                   + "LEFT JOIN tipo_documento td ON f.tipo_documento_id = td.tipo_documento_id";

        try (Connection con = ConexionDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setFuncionarioId(rs.getLong("funcionario_id"));
                f.setNumeroIdentificacion(rs.getString("numero_identificacion"));
                f.setNombres(rs.getString("nombres"));
                f.setApellidos(rs.getString("apellidos"));
                f.setSexo(rs.getString("sexo"));
                f.setDireccion(rs.getString("direccion"));
                f.setTelefono(rs.getString("telefono"));
                f.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());

                EstadoCivil ec = new EstadoCivil();
                ec.setEstadoCivilId(rs.getLong("estado_civil_id"));
                ec.setNombre(rs.getString("estado_civil_nombre"));
                f.setEstadoCivil(ec);

                TipoDocumento td = new TipoDocumento();
                td.setTipoDocumentoId(rs.getLong("tipo_documento_id"));
                td.setNombre(rs.getString("tipo_documento_nombre"));
                f.setTipoDocumento(td);

                lista.add(f);
            }
        }
        return lista;
    }
}

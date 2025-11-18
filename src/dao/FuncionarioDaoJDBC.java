package dao;

import model.Funcionario;
import model.EstadoCivil;
import model.TipoDocumento;
import ui.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDaoJDBC implements FuncionarioDao {

    @Override
    public void insertar(Funcionario f) {
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
            ps.setInt(8, (int) f.getEstadoCivil().getEstadoCivilId());
            ps.setInt(9, (int) f.getTipoDocumento().getTipoDocumentoId());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al insertar funcionario: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Funcionario f) {
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
            ps.setInt(8, (int) f.getEstadoCivil().getEstadoCivilId());
            ps.setInt(9, (int) f.getTipoDocumento().getTipoDocumentoId());
            ps.setInt(10, (int) f.getFuncionarioId());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar funcionario: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM funcionarios WHERE funcionario_id=?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al eliminar funcionario: " + e.getMessage());
        }
    }

    @Override
    public Funcionario buscarPorId(int idFuncionario) {
        String sql = "SELECT f.funcionario_id, f.numero_identificacion, f.nombres, f.apellidos, f.sexo, f.direccion, f.telefono, f.fecha_nacimiento, "
                + "ec.estado_civil_id, ec.nombre AS estado_civil_nombre, "
                + "td.tipo_documento_id, td.nombre AS tipo_documento_nombre "
                + "FROM funcionarios f "
                + "LEFT JOIN estado_civil ec ON f.estado_civil_id = ec.estado_civil_id "
                + "LEFT JOIN tipo_documento td ON f.tipo_documento_id = td.tipo_documento_id "
                + "WHERE f.funcionario_id=?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idFuncionario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Funcionario f = new Funcionario();

                    f.setFuncionarioId(rs.getInt("funcionario_id"));
                    f.setNumeroIdentificacion(rs.getString("numero_identificacion"));
                    f.setNombres(rs.getString("nombres"));
                    f.setApellidos(rs.getString("apellidos"));
                    f.setSexo(rs.getString("sexo"));
                    f.setDireccion(rs.getString("direccion"));
                    f.setTelefono(rs.getString("telefono"));

                    Date fechaSQL = rs.getDate("fecha_nacimiento");
                    if (fechaSQL != null) {
                        f.setFechaNacimiento(fechaSQL.toLocalDate());
                    }

                    EstadoCivil ec = new EstadoCivil();
                    ec.setEstadoCivilId(rs.getInt("estado_civil_id"));
                    ec.setNombre(rs.getString("estado_civil_nombre"));
                    f.setEstadoCivil(ec);

                    TipoDocumento td = new TipoDocumento();
                    td.setTipoDocumentoId(rs.getInt("tipo_documento_id"));
                    td.setNombre(rs.getString("tipo_documento_nombre"));
                    f.setTipoDocumento(td);

                    return f;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar funcionario por ID: " + e.getMessage());
        }

        return null; // si no existe
    }

    @Override
    public List<Funcionario> listarTodos() {
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

                f.setFuncionarioId(rs.getInt("funcionario_id"));
                f.setNumeroIdentificacion(rs.getString("numero_identificacion"));
                f.setNombres(rs.getString("nombres"));
                f.setApellidos(rs.getString("apellidos"));
                f.setSexo(rs.getString("sexo"));
                f.setDireccion(rs.getString("direccion"));
                f.setTelefono(rs.getString("telefono"));

                Date fechaSQL = rs.getDate("fecha_nacimiento");
                if (fechaSQL != null) {
                    f.setFechaNacimiento(fechaSQL.toLocalDate());
                }

                EstadoCivil ec = new EstadoCivil();
                ec.setEstadoCivilId(rs.getInt("estado_civil_id"));
                ec.setNombre(rs.getString("estado_civil_nombre"));
                f.setEstadoCivil(ec);

                TipoDocumento td = new TipoDocumento();
                td.setTipoDocumentoId(rs.getInt("tipo_documento_id"));
                td.setNombre(rs.getString("tipo_documento_nombre"));
                f.setTipoDocumento(td);

                lista.add(f);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar funcionarios: " + e.getMessage());
        }

        return lista;
    }
}

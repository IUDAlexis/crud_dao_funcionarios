package dao;

import model.EstadoCivil;
import ui.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstadoCivilJDBC {

    public List<EstadoCivil> listarTodos() throws SQLException {
        List<EstadoCivil> lista = new ArrayList<>();
        String sql = "SELECT estado_civil_id, nombre FROM estado_civil";

        try (Connection con = ConexionDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                EstadoCivil estado = new EstadoCivil();
                estado.setEstadoCivilId(rs.getLong("estado_civil_id"));
                estado.setNombre(rs.getString("nombre"));
                lista.add(estado);
            }
        }
        return lista;
    }
}

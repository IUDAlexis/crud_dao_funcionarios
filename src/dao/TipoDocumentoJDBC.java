package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.TipoDocumento;
import ui.ConexionDB;

public class TipoDocumentoJDBC {

    public List<TipoDocumento> listarTodos() throws SQLException {
        List<TipoDocumento> lista = new ArrayList<>();
        String sql = "SELECT tipo_documento_id, nombre FROM tipo_documento";
        try (Connection con = ConexionDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                TipoDocumento tipo = new TipoDocumento();
                tipo.setTipoDocumentoId(rs.getLong("tipo_documento_id"));
                tipo.setNombre(rs.getString("nombre"));
                lista.add(tipo);
            }
        }
        return lista;
    }
}

package com.trivia.dao;

import com.trivia.beans.Pregunta;
import com.trivia.conexion.ConectarBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PreguntaDAO {

    // Método para obtener preguntas aleatorias (mínimo 10 según la rúbrica)
    public List<Pregunta> obtenerPreguntasAleatorias() {
        List<Pregunta> lista = new ArrayList<>();
        String sql = "SELECT id, pregunta, opcion1, opcion2, opcion3, opcion_correcta FROM preguntas ORDER BY RAND() LIMIT 10";

        try (Connection con = ConectarBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pregunta p = new Pregunta();
                p.setId(rs.getInt("id"));
                p.setPregunta(rs.getString("pregunta"));
                p.setOpcion1(rs.getString("opcion1"));
                p.setOpcion2(rs.getString("opcion2"));
                p.setOpcion3(rs.getString("opcion3"));
                p.setOpcionCorrecta(rs.getString("opcion_correcta"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Método para guardar el historial de respuestas del usuario
    public boolean guardarRespuesta(String cedulaUsuario, int idPregunta, String respuestaDada, boolean esCorrecta) {
        String sql = "INSERT INTO respuestas (id_usuario, id_pregunta, respuesta_dada, correcta) VALUES (?, ?, ?, ?)";
        try (Connection con = ConectarBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cedulaUsuario);
            ps.setInt(2, idPregunta);
            ps.setString(3, respuestaDada);
            ps.setBoolean(4, esCorrecta);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
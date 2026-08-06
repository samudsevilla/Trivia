package com.trivia.dao;

import com.trivia.beans.Usuario;
import com.trivia.conexion.ConectarBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean registrarUsuario(Usuario u) {
        String sql = "INSERT INTO usuarios (cedula, nombre, usuario, password, correo, carrera, telefono, semestre, puntaje) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConectarBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, u.getCedula());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getUsuario());
            ps.setString(4, u.getPassword()); // <-- Incluyendo la contraseña
            ps.setString(5, u.getCorreo());
            ps.setString(6, u.getCarrera());
            ps.setString(7, u.getTelefono());
            ps.setInt(8, u.getSemestre());
            ps.setInt(9, 0); // Puntaje inicial en 0
            
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Usuario validarUsuario(String usuario, String password) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND password = ?";
        Usuario u = null;
        
        try (Connection con = ConectarBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, usuario);
            ps.setString(2, password);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    u = new Usuario();
                    u.setCedula(rs.getString("cedula"));
                    u.setNombre(rs.getString("nombre"));
                    u.setUsuario(rs.getString("usuario"));
                    u.setPassword(rs.getString("password"));
                    u.setCorreo(rs.getString("correo"));
                    u.setCarrera(rs.getString("carrera"));
                    u.setTelefono(rs.getString("telefono"));
                    u.setSemestre(rs.getInt("semestre"));
                    u.setPuntaje(rs.getInt("puntaje"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }
    
    public boolean actualizarPuntaje(String cedula, int nuevoPuntaje) {
        String sql = "UPDATE usuarios SET puntaje = ? WHERE cedula = ?";
        try (Connection con = ConectarBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, nuevoPuntaje);
            ps.setString(2, cedula);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public java.util.List<Usuario> obtenerRanking() {
        java.util.List<Usuario> listaRanking = new java.util.ArrayList<>();
        String sql = "SELECT nombre, carrera, semestre, puntaje FROM usuarios ORDER BY puntaje DESC";
        
        try (Connection con = ConectarBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setNombre(rs.getString("nombre"));
                u.setCarrera(rs.getString("carrera"));
                u.setSemestre(rs.getInt("semestre"));
                u.setPuntaje(rs.getInt("puntaje"));
                listaRanking.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaRanking;
    }
}
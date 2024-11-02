package eu.andreatt.ejerciciol_dein.dao;

import eu.andreatt.ejerciciol_dein.bbdd.ConexionBD;
import eu.andreatt.ejerciciol_dein.model.Usuarios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuariosDAO {
    private ConexionBD conexion;


    public boolean verificarUsuario(Usuarios usuario) {
        try {
            conexion = new ConexionBD();
            String sql = "SELECT * FROM usuarios WHERE usuario = ? AND password = ?";
            PreparedStatement statement = conexion.getConexion().prepareStatement(sql);
                statement.setString(1, usuario.getUsuario());
                statement.setString(2, usuario.getPassword());

                ResultSet resultSet = statement.executeQuery();
                return resultSet.next(); // Retorna true si existe un resultado
            } catch (SQLException e) {
                e.printStackTrace(); // Manejo básico de excepciones, deberías implementar un mejor manejo
            }
            return false;
        }
    }




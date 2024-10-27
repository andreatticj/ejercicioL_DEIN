package eu.andreatt.ejerciciol_dein.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eu.andreatt.ejerciciol_dein.bbdd.ConexionBD;
import eu.andreatt.ejerciciol_dein.model.InformacionAeropuertosPublicos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class AeropuertosPublicosDao {
private ConexionBD conexion;
	
	/** CARGAR LOS AEROPUERTOS PÚBLICOS EXISTENTES EN BASE DE DATOS */
	public ObservableList<InformacionAeropuertosPublicos> cargarAeropuertosPublicos() {

		ObservableList<InformacionAeropuertosPublicos> aeropuertosPublicos = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBD();
			String consulta = "SELECT a.id, a.nombre, a.anio_inauguracion, a.capacidad, d.pais, d.ciudad, d.calle, d.numero, ap.financiacion, ap.num_trabajadores FROM aeropuertos AS a JOIN direcciones AS d ON a.id_direccion = d.id JOIN aeropuertos_publicos AS ap ON a.id = ap.id_aeropuerto";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				int anioInauguracion = rs.getInt("anio_inauguracion");
				int capacidad = rs.getInt("capacidad");
				String pais = rs.getString("pais");
				String ciudad = rs.getString("ciudad");
				String calle = rs.getString("calle");
				int numero = rs.getInt("numero");
				float financiacion = rs.getInt("financiacion");
				int numTrabajadores = rs.getInt("num_trabajadores");
				
				aeropuertosPublicos.add(new InformacionAeropuertosPublicos(id, nombre, anioInauguracion, capacidad, pais, ciudad, calle, numero, financiacion, numTrabajadores));
			}
			rs.close();
			conexion.closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aeropuertosPublicos;
	}
	
	/** INSERTAR AEROPUERTO PÚBLICO EN BASE DE DATOS */
	public boolean insertarAeropuertoPublico(int idAeropuerto, float financiacion, int numTrabajadores) {
		try {
			conexion = new ConexionBD();       
			String consulta = "INSERT INTO aeropuertos_publicos (id_aeropuerto, financiacion, num_trabajadores) VALUES (?, ?, ?)";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setInt(1, idAeropuerto);
	        pstmt.setFloat(2, financiacion);
	        pstmt.setInt(3, numTrabajadores);
	    	     
	    	pstmt.executeUpdate();        
	    	conexion.closeConnection();
	    	return true;
	    
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
	
	/** BORRAR AEROPUERTO PÚBLICO EN BASE DE DATOS */
	public boolean borrarAeropuertoPublico(int idAeropuerto) {
		try {
			conexion = new ConexionBD();       
			String consulta = "DELETE FROM aeropuertos_publicos WHERE id_aeropuerto = ?";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setInt(1, idAeropuerto);
	    	     
			int filasAfectadas = pstmt.executeUpdate();
					    
		    conexion.closeConnection();

		    if (filasAfectadas > 0) {
		        return true;
		    } else {
		        return false;
		    }
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
	
	/** ACTUALIZAR AEROPUERTO PÚBLICO EN BASE DE DATOS */
	public boolean actualizarAeropuertoPublico(int idAeropuerto, float financiacion, int numTrabajadores) {
		try {
			conexion = new ConexionBD();       
			String consulta = "UPDATE aeropuertos_publicos SET financiacion = ?, num_trabajadores = ? WHERE id_aeropuerto = ?";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setFloat(1, financiacion);
	        pstmt.setInt(2, numTrabajadores);
	        pstmt.setInt(3, idAeropuerto);
	    	     
			int filasAfectadas = pstmt.executeUpdate();
					    
		    conexion.closeConnection();

		    if (filasAfectadas > 0) {
		        return true;
		    } else {
		        return false;
		    }
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
}
package eu.andreatt.ejerciciol_dein.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import eu.andreatt.ejerciciol_dein.bbdd.ConexionBD;
import eu.andreatt.ejerciciol_dein.model.Aviones;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AvionesDao {
	private ConexionBD conexion;

	/**
	 * Carga todos los aviones existentes en la base de datos.
	 *
	 * @return ObservableList de Aviones que contiene todos los aviones en la base de datos.
	 */
	public ObservableList<Aviones> cargarAviones() {
		ObservableList<Aviones> aviones = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBD();
			String consulta = "SELECT id, modelo, numero_asientos, velocidad_maxima, activado, id_aeropuerto FROM aviones";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String modelo = rs.getString("modelo");
				int numero_asientos = rs.getInt("numero_asientos");
				int velocidad_maxima = rs.getInt("velocidad_maxima");
				int activado = rs.getInt("activado");
				int id_aeropuerto = rs.getInt("id_aeropuerto");

				aviones.add(new Aviones(id, modelo, numero_asientos, velocidad_maxima, activado, id_aeropuerto));
			}
			rs.close();
			conexion.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aviones;
	}

	/**
	 * Carga los aviones según el aeropuerto solicitado.
	 *
	 * @param ida El ID del aeropuerto para filtrar los aviones.
	 * @return ObservableList de Aviones que pertenecen al aeropuerto especificado.
	 */
	public ObservableList<Aviones> dameAvionesPorAeropuerto(int ida) {
		ObservableList<Aviones> aviones = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBD();
			String consulta = "SELECT id, modelo, numero_asientos, velocidad_maxima, activado, id_aeropuerto FROM aviones where id_aeropuerto = "+ida;
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String modelo = rs.getString("modelo");
				int numero_asientos = rs.getInt("numero_asientos");
				float velocidad_maxima = rs.getFloat("velocidad_maxima");
				int activado = rs.getInt("activado");
				int id_aeropuerto = rs.getInt("id_aeropuerto");

				aviones.add(new Aviones(id, modelo, numero_asientos, velocidad_maxima, activado, id_aeropuerto));
			}
			rs.close();
			conexion.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aviones;
	}

	/**
	 * Inserta un nuevo avión en la base de datos.
	 *
	 * @param modelo El modelo del avión a insertar.
	 * @param numero_asientos El número de asientos del avión.
	 * @param velocidad_maxima La velocidad máxima del avión.
	 * @param activado El estado del avión (activado/desactivado).
	 * @param id_aeropuerto El ID del aeropuerto al que pertenece el avión.
	 * @return true si la inserción fue exitosa, false en caso contrario.
	 */
	public boolean insertarAvion(String modelo, int numero_asientos, float velocidad_maxima, int activado, int id_aeropuerto) {
		try {
			conexion = new ConexionBD();
			String consulta = "INSERT INTO aviones (modelo,numero_asientos,velocidad_maxima,activado,id_aeropuerto) VALUES (?, ?, ?, ?,?)";

			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setString(1, modelo);
			pstmt.setInt(2, numero_asientos);
			pstmt.setFloat(3, velocidad_maxima);
			pstmt.setInt(4, activado);
			pstmt.setInt(5, id_aeropuerto);

			pstmt.executeUpdate();
			conexion.closeConnection();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Borra un avión de la base de datos según su ID.
	 *
	 * @param id El ID del avión a borrar.
	 * @return true si el avión fue borrado exitosamente, false en caso contrario.
	 */
	public boolean borrarAvion(int id) {
		try {
			conexion = new ConexionBD();
			String consulta = "DELETE FROM aviones WHERE id = ?";

			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setInt(1, id);

			pstmt.executeUpdate();
			conexion.closeConnection();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Actualiza el estado de un avión (activado/desactivado) en la base de datos.
	 *
	 * @param id El ID del avión a actualizar.
	 * @param activado El nuevo estado del avión (activado/desactivado).
	 * @return true si la actualización fue exitosa, false en caso contrario.
	 */
	public boolean actualizarAvionActivo(int id, int activado) {
		try {
			conexion = new ConexionBD();
			String consulta = "UPDATE aviones SET activado = ? WHERE id = ?";

			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setInt(1, activado);
			pstmt.setInt(2, id);

			pstmt.executeUpdate();
			conexion.closeConnection();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Verifica si un modelo de avión ya existe en la base de datos para un aeropuerto específico.
	 *
	 * @param modelo El modelo del avión a verificar.
	 * @param id_aeropuerto El ID del aeropuerto al que pertenece el avión.
	 * @return true si el modelo existe, false en caso contrario.
	 */
	public boolean existeModeloEnAeropuerto(String modelo, int id_aeropuerto) {
		boolean existe = false;

		try {
			conexion = new ConexionBD();
			String consulta = "SELECT * FROM aviones WHERE modelo = ? and id_aeropuerto = ?";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setString(1, modelo);
			pstmt.setInt(2, id_aeropuerto);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				existe = true;
			}

			rs.close();
			conexion.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return existe;
	}
}

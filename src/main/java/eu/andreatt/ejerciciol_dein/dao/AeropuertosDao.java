package eu.andreatt.ejerciciol_dein.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import eu.andreatt.ejerciciol_dein.bbdd.ConexionBD;
import eu.andreatt.ejerciciol_dein.model.Aeropuertos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Clase de acceso a datos para gestionar las operaciones relacionadas
 * con los aeropuertos en la base de datos.
 */
public class AeropuertosDao {
	private ConexionBD conexion; // Conexión a la base de datos

	/**
	 * Carga los aeropuertos existentes en la base de datos.
	 *
	 * @return Una lista observable de objetos Aeropuertos.
	 */
	public ObservableList<Aeropuertos> cargarAeropuertos() {
		ObservableList<Aeropuertos> aeropuertos = FXCollections.observableArrayList();
		try {
			String consulta = "SELECT id, nombre, anio_inauguracion, capacidad, id_direccion FROM aeropuertos";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				int anio_inauguracion = rs.getInt("anio_inauguracion");
				int capacidad = rs.getInt("capacidad");
				int id_direccion = rs.getInt("id_direccion");

				aeropuertos.add(new Aeropuertos(id, nombre, anio_inauguracion, capacidad, id_direccion));
			}
			rs.close();
			conexion.closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aeropuertos;
	}

	/**
	 * Inserta un nuevo aeropuerto en la base de datos.
	 *
	 * @param nombre El nombre del aeropuerto.
	 * @param anioInauguracion El año de inauguración del aeropuerto.
	 * @param capacidad La capacidad del aeropuerto.
	 * @param idDireccion El ID de la dirección asociada al aeropuerto.
	 * @return true si la inserción fue exitosa, false en caso contrario.
	 */
	public boolean insertarAeropuerto(String nombre, int anioInauguracion, int capacidad, int idDireccion) {
		try {
			String consulta = "INSERT INTO aeropuertos (nombre, anio_inauguracion, capacidad, id_direccion) VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setString(1, nombre);
			pstmt.setInt(2, anioInauguracion);
			pstmt.setInt(3, capacidad);
			pstmt.setInt(4, idDireccion);

			pstmt.executeUpdate();
			conexion.closeConnection();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Borra un aeropuerto de la base de datos.
	 *
	 * @param nombre El nombre del aeropuerto a borrar.
	 * @param anioInauguracion El año de inauguración del aeropuerto a borrar.
	 * @param capacidad La capacidad del aeropuerto a borrar.
	 * @param idDireccion El ID de la dirección del aeropuerto a borrar.
	 * @return true si la eliminación fue exitosa, false en caso contrario.
	 */
	public boolean borrarAeropuerto(String nombre, int anioInauguracion, int capacidad, int idDireccion) {
		try {
			String consulta = "DELETE FROM aeropuertos WHERE nombre = ? AND anio_inauguracion = ? AND capacidad = ? AND id_direccion = ?";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);

			pstmt.setString(1, nombre);
			pstmt.setInt(2, anioInauguracion);
			pstmt.setInt(3, capacidad);
			pstmt.setInt(4, idDireccion);

			int filasAfectadas = pstmt.executeUpdate();

			conexion.closeConnection();

			return filasAfectadas > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Actualiza la información de un aeropuerto en la base de datos.
	 *
	 * @param id El ID del aeropuerto a actualizar.
	 * @param nombre El nuevo nombre del aeropuerto.
	 * @param anioInauguracion El nuevo año de inauguración del aeropuerto.
	 * @param capacidad La nueva capacidad del aeropuerto.
	 * @param id_direccion El nuevo ID de la dirección asociada al aeropuerto.
	 * @return true si la actualización fue exitosa, false en caso contrario.
	 */
	public boolean actualizarAeropuerto(int id, String nombre, int anioInauguracion, int capacidad, int id_direccion) {
		try {
			String consulta = "UPDATE aeropuertos SET nombre = ?, anio_inauguracion = ?, capacidad = ?, id_direccion = ? WHERE id = ?";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setString(1, nombre);
			pstmt.setInt(2, anioInauguracion);
			pstmt.setInt(3, capacidad);
			pstmt.setInt(4, id_direccion);
			pstmt.setInt(5, id);

			pstmt.executeUpdate();
			conexion.closeConnection();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Obtiene el ID más grande de los aeropuertos en la base de datos.
	 *
	 * @return El ID más grande, o -1 si no se encontró ningún ID.
	 */
	public int dameMaxIdAeropuertos() {
		try {
			String consulta = "SELECT MAX(id) AS max_id FROM aeropuertos";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("max_id");
			}

			rs.close();
			conexion.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * Obtiene el ID del aeropuerto solicitado.
	 *
	 * @param nombre El nombre del aeropuerto.
	 * @param anioInauguracion El año de inauguración del aeropuerto.
	 * @param capacidad La capacidad del aeropuerto.
	 * @param idDireccion El ID de la dirección del aeropuerto.
	 * @return El ID del aeropuerto, o -1 si no se encontró.
	 */
	public int dameIdDeAeropuerto(String nombre, int anioInauguracion, int capacidad, int idDireccion) {
		try {
			String consulta = "SELECT id FROM aeropuertos WHERE nombre = ? AND anio_inauguracion = ? AND capacidad = ? AND id_direccion = ?";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);

			pstmt.setString(1, nombre);
			pstmt.setInt(2, anioInauguracion);
			pstmt.setInt(3, capacidad);
			pstmt.setInt(4, idDireccion);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("id");
			}

			rs.close();
			conexion.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * Verifica si existe un aeropuerto con el nombre dado en la base de datos.
	 *
	 * @param nombre El nombre del aeropuerto a verificar.
	 * @return true si el aeropuerto existe, false en caso contrario.
	 */
	public boolean existeNombreAeropuerto(String nombre) {
		boolean existe = false;

		try {
			String consulta = "SELECT * FROM aeropuertos WHERE nombre = ?";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setString(1, nombre);

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

	/**
	 * Inserta una imagen para un aeropuerto en la base de datos.
	 *
	 * @param ruta La ruta del archivo de imagen.
	 * @param id El ID del aeropuerto al que se asociará la imagen.
	 * @return true si la inserción fue exitosa, false en caso contrario.
	 */
	public boolean insertarImagen(String ruta, int id) {
		try {
			File file = new File(ruta);
			byte[] imageData = Files.readAllBytes(file.toPath());

			String consulta = "UPDATE aeropuertos SET imagen = ? WHERE id = ?";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setBytes(1, imageData);
			pstmt.setInt(2, id);

			pstmt.executeUpdate();
			conexion.closeConnection();
			return true;

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Obtiene la imagen asociada a un aeropuerto a partir de su ID.
	 *
	 * @param id El ID del aeropuerto.
	 * @return Un InputStream de la imagen, o null si no se encontró.
	 */
	public InputStream getImagen(int id) {
		try {
			String consulta = "SELECT imagen FROM aeropuertos WHERE id = ?";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getBinaryStream("imagen");
			}
			rs.close();
			conexion.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

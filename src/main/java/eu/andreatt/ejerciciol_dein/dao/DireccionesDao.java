package eu.andreatt.ejerciciol_dein.dao;

import eu.andreatt.ejerciciol_dein.bbdd.ConexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DireccionesDao {
	private ConexionBD conexion;

	/**
	 * Inserta una nueva dirección en la base de datos.
	 *
	 * @param pais el país de la dirección.
	 * @param ciudad la ciudad de la dirección.
	 * @param calle la calle de la dirección.
	 * @param numero el número de la dirección.
	 * @return true si la inserción fue exitosa, false en caso contrario.
	 */
	public boolean insertarDireccion(String pais, String ciudad, String calle, int numero) {
		try {
			conexion = new ConexionBD();
			String consulta = "INSERT INTO direcciones (pais, ciudad, calle, numero) VALUES (?, ?, ?, ?)";

			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setString(1, pais);
			pstmt.setString(2, ciudad);
			pstmt.setString(3, calle);
			pstmt.setInt(4, numero);

			pstmt.executeUpdate();
			conexion.closeConnection();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Actualiza una dirección existente en la base de datos.
	 *
	 * @param id el ID de la dirección a actualizar.
	 * @param pais el nuevo país de la dirección.
	 * @param ciudad la nueva ciudad de la dirección.
	 * @param calle la nueva calle de la dirección.
	 * @param numero el nuevo número de la dirección.
	 * @return true si la actualización fue exitosa, false en caso contrario.
	 */
	public boolean actualizarDireccion(int id, String pais, String ciudad, String calle, int numero) {
		try {
			conexion = new ConexionBD();
			String consulta = "UPDATE direcciones SET pais = ?, ciudad = ?, calle = ?, numero = ? WHERE id = ?";

			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setString(1, pais);
			pstmt.setString(2, ciudad);
			pstmt.setString(3, calle);
			pstmt.setInt(4, numero);
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
	 * Verifica si una dirección ya existe en la base de datos.
	 *
	 * @param pais el país de la dirección.
	 * @param ciudad la ciudad de la dirección.
	 * @param calle la calle de la dirección.
	 * @param numero el número de la dirección.
	 * @return el ID de la dirección si existe, -1 en caso contrario.
	 */
	public int existeDireccion(String pais, String ciudad, String calle, int numero) {
		try {
			conexion = new ConexionBD();
			String consulta = "SELECT id FROM direcciones WHERE pais = ? AND ciudad = ? AND calle = ? AND numero = ?";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setString(1, pais);
			pstmt.setString(2, ciudad);
			pstmt.setString(3, calle);
			pstmt.setInt(4, numero);

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
	 * Obtiene el ID más grande de las direcciones en la base de datos.
	 *
	 * @return el ID más grande encontrado, -1 si no hay direcciones.
	 */
	public int dameMaxIdDirecciones() {
		try {
			conexion = new ConexionBD();
			String consulta = "SELECT MAX(id) AS max_id FROM direcciones";
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
}

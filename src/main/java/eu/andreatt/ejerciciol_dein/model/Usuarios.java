package eu.andreatt.ejerciciol_dein.model;

import java.util.Objects;

/**
 * Clase que representa un usuario en el contexto de los ejercicios L-M.
 * Esta clase almacena información sobre el nombre de usuario y la contraseña.
 */
public class Usuarios {

	/** Nombre del usuario. */
	private String usuario;

	/** Contraseña del usuario. */
	private String password;

	/**
	 * Constructor que inicializa un objeto Usuarios
	 * con el nombre de usuario y la contraseña proporcionados.
	 *
	 * @param usuario Nombre del usuario.
	 * @param password Contraseña del usuario.
	 */
	public Usuarios(String usuario, String password) {
		this.usuario = usuario;
		this.password = password;
	}

	/**
	 * Obtiene el nombre del usuario.
	 *
	 * @return usuario El nombre del usuario.
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Establece el nombre del usuario.
	 *
	 * @param usuario Nombre a establecer para el usuario.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Obtiene la contraseña del usuario.
	 *
	 * @return password La contraseña del usuario.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Establece la contraseña del usuario.
	 *
	 * @param password Contraseña a establecer para el usuario.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Calcula y devuelve el código hash de este objeto Usuarios.
	 *
	 * @return int Código hash basado en los atributos relevantes del usuario.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(password, usuario);
	}

	/**
	 * Compara este objeto Usuarios con otro para determinar si son iguales.
	 *
	 * @param obj Objeto con el que se comparará esta información del usuario.
	 * @return boolean True si los usuarios son iguales, false de lo contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuarios other = (Usuarios) obj;
		return Objects.equals(password, other.password) && Objects.equals(usuario, other.usuario);
	}
}

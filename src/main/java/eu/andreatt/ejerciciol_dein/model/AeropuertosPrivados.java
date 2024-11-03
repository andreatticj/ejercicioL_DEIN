package eu.andreatt.ejerciciol_dein.model;

import java.util.Objects;

/**
 * Clase que representa un aeropuerto privado en el contexto de los ejercicios L-M.
 * Incluye información específica sobre el aeropuerto, como su identificador y el número de socios que lo gestionan.
 */
public class AeropuertosPrivados {

	/** Identificador único del aeropuerto privado. */
	private int idAeropuerto;

	/** Número de socios que gestionan el aeropuerto privado. */
	private int numeroSocios;

	/**
	 * Constructor que inicializa un objeto AeropuertosPrivados con los parámetros especificados.
	 *
	 * @param idAeropuerto Identificador único del aeropuerto privado.
	 * @param numeroSocios Número de socios que gestionan el aeropuerto privado.
	 */
	public AeropuertosPrivados(int idAeropuerto, int numeroSocios) {
		this.idAeropuerto = idAeropuerto;
		this.numeroSocios = numeroSocios;
	}

	/**
	 * Obtiene el identificador del aeropuerto privado.
	 *
	 * @return idAeropuerto El identificador único del aeropuerto privado.
	 */
	public int getId_aeropuerto() {
		return idAeropuerto;
	}

	/**
	 * Establece el identificador del aeropuerto privado.
	 *
	 * @param id_aeropuerto Identificador único del aeropuerto privado a establecer.
	 */
	public void setId_aeropuerto(int id_aeropuerto) {
		this.idAeropuerto = id_aeropuerto;
	}

	/**
	 * Obtiene el número de socios que gestionan el aeropuerto privado.
	 *
	 * @return numeroSocios El número de socios.
	 */
	public int getNumero_socios() {
		return numeroSocios;
	}

	/**
	 * Establece el número de socios que gestionan el aeropuerto privado.
	 *
	 * @param numero_socios Número de socios a establecer para el aeropuerto privado.
	 */
	public void setNumero_socios(int numero_socios) {
		this.numeroSocios = numero_socios;
	}

	/**
	 * Calcula y devuelve el código hash de este objeto AeropuertosPrivados.
	 *
	 * @return int Código hash basado en los atributos relevantes del aeropuerto privado.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(idAeropuerto, numeroSocios);
	}

	/**
	 * Compara este objeto AeropuertosPrivados con otro para determinar si son iguales.
	 *
	 * @param obj Objeto con el que se comparará este AeropuertosPrivados.
	 * @return boolean True si los objetos son iguales, false de lo contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AeropuertosPrivados other = (AeropuertosPrivados) obj;
		return idAeropuerto == other.idAeropuerto && numeroSocios == other.numeroSocios;
	}
}

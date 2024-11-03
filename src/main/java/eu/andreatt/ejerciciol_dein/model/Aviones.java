package eu.andreatt.ejerciciol_dein.model;

import java.util.Objects;

/**
 * Clase que representa un avión en el contexto de los ejercicios L-M.
 * Esta clase incluye información relevante sobre el avión, como su modelo,
 * capacidad, velocidad máxima y estado de activación, así como el aeropuerto al que está asignado.
 */
public class Aviones {

	/** Identificador único del avión. */
	private int id;

	/** Número de asientos disponibles en el avión. */
	private int numeroAsientos;

	/** Estado de activación del avión (1 para activado, 0 para desactivado). */
	private int activado;

	/** Identificador del aeropuerto al que está asignado el avión. */
	private int idAeropuerto;

	/** Modelo del avión. */
	private String modelo;

	/** Velocidad máxima que puede alcanzar el avión. */
	private float velocidadMaxima;

	/**
	 * Constructor que inicializa un objeto Aviones con los parámetros especificados.
	 *
	 * @param id Identificador único del avión.
	 * @param modelo Modelo del avión.
	 * @param numeroAsientos Número de asientos disponibles en el avión.
	 * @param velocidadMaxima Velocidad máxima que puede alcanzar el avión.
	 * @param activado Estado de activación del avión.
	 * @param idAeropuerto Identificador del aeropuerto al que está asignado el avión.
	 */
	public Aviones(int id, String modelo, int numeroAsientos, float velocidadMaxima, int activado, int idAeropuerto) {
		this.id = id;
		this.modelo = modelo;
		this.numeroAsientos = numeroAsientos;
		this.velocidadMaxima = velocidadMaxima;
		this.activado = activado;
		this.idAeropuerto = idAeropuerto;
	}

	/**
	 * Obtiene el identificador del avión.
	 *
	 * @return id El identificador único del avión.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Establece el identificador del avión.
	 *
	 * @param id Identificador único del avión a establecer.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Obtiene el número de asientos disponibles en el avión.
	 *
	 * @return numeroAsientos El número de asientos en el avión.
	 */
	public int getNumeroAsientos() {
		return numeroAsientos;
	}

	/**
	 * Establece el número de asientos disponibles en el avión.
	 *
	 * @param numeroAsientos Número de asientos a establecer para el avión.
	 */
	public void setNumeroAsientos(int numeroAsientos) {
		this.numeroAsientos = numeroAsientos;
	}

	/**
	 * Obtiene la velocidad máxima que puede alcanzar el avión.
	 *
	 * @return velocidadMaxima La velocidad máxima del avión.
	 */
	public float getVelocidadMaxima() {
		return velocidadMaxima;
	}

	/**
	 * Establece la velocidad máxima que puede alcanzar el avión.
	 *
	 * @param velocidadMaxima Velocidad máxima a establecer para el avión.
	 */
	public void setVelocidadMaxima(float velocidadMaxima) {
		this.velocidadMaxima = velocidadMaxima;
	}

	/**
	 * Obtiene el estado de activación del avión.
	 *
	 * @return activado El estado de activación (1 o 0) del avión.
	 */
	public int getActivado() {
		return activado;
	}

	/**
	 * Establece el estado de activación del avión.
	 *
	 * @param activado Estado de activación a establecer (1 o 0) para el avión.
	 */
	public void setActivado(int activado) {
		this.activado = activado;
	}

	/**
	 * Obtiene el identificador del aeropuerto al que está asignado el avión.
	 *
	 * @return idAeropuerto El identificador del aeropuerto asignado al avión.
	 */
	public int getIdAeropuerto() {
		return idAeropuerto;
	}

	/**
	 * Establece el identificador del aeropuerto al que está asignado el avión.
	 *
	 * @param idAeropuerto Identificador del aeropuerto a establecer para el avión.
	 */
	public void setIdAeropuerto(int idAeropuerto) {
		this.idAeropuerto = idAeropuerto;
	}

	/**
	 * Obtiene el modelo del avión.
	 *
	 * @return modelo El modelo del avión.
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * Establece el modelo del avión.
	 *
	 * @param modelo Modelo a establecer para el avión.
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * Calcula y devuelve el código hash de este objeto Aviones.
	 *
	 * @return int Código hash basado en los atributos relevantes del avión.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(activado, id, idAeropuerto, modelo, numeroAsientos, velocidadMaxima);
	}

	/**
	 * Compara este objeto Aviones con otro para determinar si son iguales.
	 *
	 * @param obj Objeto con el que se comparará este Aviones.
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
		Aviones other = (Aviones) obj;
		return activado == other.activado && id == other.id && idAeropuerto == other.idAeropuerto
				&& Objects.equals(modelo, other.modelo) && numeroAsientos == other.numeroAsientos
				&& velocidadMaxima == other.velocidadMaxima;
	}

	/**
	 * Devuelve una representación en cadena del objeto Aviones.
	 *
	 * @return String Representación en cadena del modelo del avión.
	 */
	public String toString() {
		return this.modelo;
	}
}

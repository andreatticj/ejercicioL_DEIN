package eu.andreatt.ejerciciol_dein.model;

import java.util.Objects;

/**
 * Clase que representa un aeropuerto público en el contexto de los ejercicios L-M.
 * Esta clase incluye información relevante sobre el aeropuerto, como su identificador,
 * la financiación que recibe y el número de trabajadores que emplea.
 */
public class AeropuertosPublicos {

	/** Identificador único del aeropuerto público. */
	private int idAeropuerto;

	/** Número de trabajadores que operan en el aeropuerto público. */
	private int numTrabajadores;

	/** Cantidad de financiación que recibe el aeropuerto público. */
	private float financiacion;

	/**
	 * Constructor que inicializa un objeto AeropuertosPublicos con los parámetros especificados.
	 *
	 * @param idAeropuerto Identificador único del aeropuerto público.
	 * @param financiacion Cantidad de financiación asignada al aeropuerto público.
	 * @param numTrabajadores Número de trabajadores que emplea el aeropuerto público.
	 */
	public AeropuertosPublicos(int idAeropuerto, float financiacion, int numTrabajadores) {
		this.idAeropuerto = idAeropuerto;
		this.financiacion = financiacion;
		this.numTrabajadores = numTrabajadores;
	}

	/**
	 * Obtiene el identificador del aeropuerto público.
	 *
	 * @return idAeropuerto El identificador único del aeropuerto público.
	 */
	public int getId_aeropuerto() {
		return idAeropuerto;
	}

	/**
	 * Establece el identificador del aeropuerto público.
	 *
	 * @param id_aeropuerto Identificador único del aeropuerto público a establecer.
	 */
	public void setId_aeropuerto(int id_aeropuerto) {
		this.idAeropuerto = id_aeropuerto;
	}

	/**
	 * Obtiene el número de trabajadores que operan en el aeropuerto público.
	 *
	 * @return numTrabajadores El número de trabajadores del aeropuerto público.
	 */
	public int getNum_trabajadores() {
		return numTrabajadores;
	}

	/**
	 * Establece el número de trabajadores que operan en el aeropuerto público.
	 *
	 * @param num_trabajadores Número de trabajadores a establecer para el aeropuerto público.
	 */
	public void setNum_trabajadores(int num_trabajadores) {
		this.numTrabajadores = num_trabajadores;
	}

	/**
	 * Obtiene la cantidad de financiación que recibe el aeropuerto público.
	 *
	 * @return financiacion La cantidad de financiación asignada al aeropuerto público.
	 */
	public float getFinanciacion() {
		return financiacion;
	}

	/**
	 * Establece la cantidad de financiación que recibe el aeropuerto público.
	 *
	 * @param financiacion Cantidad de financiación a establecer para el aeropuerto público.
	 */
	public void setFinanciacion(float financiacion) {
		this.financiacion = financiacion;
	}

	/**
	 * Calcula y devuelve el código hash de este objeto AeropuertosPublicos.
	 *
	 * @return int Código hash basado en los atributos relevantes del aeropuerto público.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(financiacion, idAeropuerto, numTrabajadores);
	}

	/**
	 * Compara este objeto AeropuertosPublicos con otro para determinar si son iguales.
	 *
	 * @param obj Objeto con el que se comparará este AeropuertosPublicos.
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
		AeropuertosPublicos other = (AeropuertosPublicos) obj;
		return Float.floatToIntBits(financiacion) == Float.floatToIntBits(other.financiacion)
				&& idAeropuerto == other.idAeropuerto && numTrabajadores == other.numTrabajadores;
	}
}

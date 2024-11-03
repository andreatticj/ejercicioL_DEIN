package eu.andreatt.ejerciciol_dein.model;

import java.util.Objects;

/**
 * Clase que representa una dirección en el contexto de los ejercicios L-M.
 * Esta clase almacena información sobre el país, ciudad, calle y número
 * de una dirección específica, así como un identificador único para cada dirección.
 */
public class Direcciones {

	/** Identificador único de la dirección. */
	private int id;

	/** Número de la dirección (puerta, edificio, etc.). */
	private int numero;

	/** País donde se encuentra la dirección. */
	private String pais;

	/** Ciudad donde se encuentra la dirección. */
	private String ciudad;

	/** Calle donde se encuentra la dirección. */
	private String calle;

	/**
	 * Constructor que inicializa un objeto Direcciones con los parámetros proporcionados.
	 *
	 * @param id Identificador único de la dirección.
	 * @param pais Nombre del país de la dirección.
	 * @param ciudad Nombre de la ciudad de la dirección.
	 * @param calle Nombre de la calle de la dirección.
	 * @param numero Número específico de la dirección (puerta, edificio, etc.).
	 */
	public Direcciones(int id, String pais, String ciudad, String calle, int numero) {
		this.id = id;
		this.pais = pais;
		this.ciudad = ciudad;
		this.calle = calle;
		this.numero = numero;
	}

	/**
	 * Obtiene el identificador de la dirección.
	 *
	 * @return id El identificador único de la dirección.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Establece el identificador de la dirección.
	 *
	 * @param id Identificador único a establecer para la dirección.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Obtiene el número de la dirección.
	 *
	 * @return numero El número específico de la dirección.
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Establece el número de la dirección.
	 *
	 * @param numero Número a establecer para la dirección.
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * Obtiene el país de la dirección.
	 *
	 * @return pais El nombre del país de la dirección.
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Establece el país de la dirección.
	 *
	 * @param pais Nombre del país a establecer para la dirección.
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * Obtiene la ciudad de la dirección.
	 *
	 * @return ciudad El nombre de la ciudad de la dirección.
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Establece la ciudad de la dirección.
	 *
	 * @param ciudad Nombre de la ciudad a establecer para la dirección.
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * Obtiene la calle de la dirección.
	 *
	 * @return calle El nombre de la calle de la dirección.
	 */
	public String getCalle() {
		return calle;
	}

	/**
	 * Establece la calle de la dirección.
	 *
	 * @param calle Nombre de la calle a establecer para la dirección.
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}

	/**
	 * Calcula y devuelve el código hash de este objeto Direcciones.
	 *
	 * @return int Código hash basado en los atributos relevantes de la dirección.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(calle, ciudad, id, numero, pais);
	}

	/**
	 * Compara este objeto Direcciones con otro para determinar si son iguales.
	 *
	 * @param obj Objeto con el que se comparará esta dirección.
	 * @return boolean True si las direcciones son iguales, false de lo contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Direcciones other = (Direcciones) obj;
		return Objects.equals(calle, other.calle) && Objects.equals(ciudad, other.ciudad) && id == other.id
				&& numero == other.numero && Objects.equals(pais, other.pais);
	}
}

package eu.andreatt.ejerciciol_dein.model;

import java.util.Objects;

/**
 * Clase que representa un aeropuerto en el contexto de los ejercicios L-M.
 * Incluye información sobre el aeropuerto como su nombre, año de inauguración, capacidad y un identificador de dirección.
 */
public class Aeropuertos {

	/** Identificador único del aeropuerto. */
	private int id;

	/** Año en que se inauguró el aeropuerto. */
	private int anioInaguracion;

	/** Capacidad del aeropuerto en términos de número de pasajeros. */
	private int capacidad;

	/** Identificador de la dirección asociada al aeropuerto. */
	private int id_direccion;

	/** Nombre del aeropuerto. */
	private String nombre;

	/**
	 * Constructor que inicializa un objeto Aeropuertos con los parámetros especificados.
	 *
	 * @param id Identificador único del aeropuerto.
	 * @param nombre Nombre del aeropuerto.
	 * @param anioInaguracion Año de inauguración del aeropuerto.
	 * @param capacidad Capacidad máxima del aeropuerto.
	 * @param id_direccion Identificador de la dirección asociada al aeropuerto.
	 */
	public Aeropuertos(int id, String nombre, int anioInaguracion, int capacidad, int id_direccion) {
		this.id = id;
		this.nombre = nombre;
		this.anioInaguracion = anioInaguracion;
		this.capacidad = capacidad;
		this.id_direccion = id_direccion;
	}

	/**
	 * Obtiene el identificador del aeropuerto.
	 *
	 * @return id El identificador único del aeropuerto.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Establece el identificador del aeropuerto.
	 *
	 * @param id Identificador único del aeropuerto a establecer.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Obtiene el año de inauguración del aeropuerto.
	 *
	 * @return anioInauguracion El año de inauguración.
	 */
	public int getAnioInaguracion() {
		return anioInaguracion;
	}

	/**
	 * Establece el año de inauguración del aeropuerto.
	 *
	 * @param anioInauguracion Año de inauguración a establecer.
	 */
	public void setAnioInaguracion(int anioInauguracion) {
		this.anioInaguracion = anioInaguracion;
	}

	/**
	 * Obtiene la capacidad del aeropuerto.
	 *
	 * @return capacidad La capacidad del aeropuerto en términos de pasajeros.
	 */
	public int getCapacidad() {
		return capacidad;
	}

	/**
	 * Establece la capacidad del aeropuerto.
	 *
	 * @param capacidad Capacidad a establecer para el aeropuerto.
	 */
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	/**
	 * Obtiene el identificador de la dirección asociada al aeropuerto.
	 *
	 * @return id_direccion El identificador de la dirección.
	 */
	public int getId_direccion() {
		return id_direccion;
	}

	/**
	 * Establece el identificador de la dirección asociada al aeropuerto.
	 *
	 * @param id_direccion Identificador de la dirección a establecer.
	 */
	public void setId_direccion(int id_direccion) {
		this.id_direccion = id_direccion;
	}

	/**
	 * Obtiene el nombre del aeropuerto.
	 *
	 * @return nombre El nombre del aeropuerto.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del aeropuerto.
	 *
	 * @param nombre Nombre a establecer para el aeropuerto.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Calcula y devuelve el código hash de este objeto Aeropuertos.
	 *
	 * @return int Código hash basado en los atributos relevantes del aeropuerto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(anioInaguracion, capacidad, id, id_direccion, nombre);
	}

	/**
	 * Compara este objeto Aeropuertos con otro para determinar si son iguales.
	 *
	 * @param obj Objeto con el que se comparará este Aeropuertos.
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
		Aeropuertos other = (Aeropuertos) obj;
		return anioInaguracion == other.anioInaguracion && capacidad == other.capacidad && id == other.id
				&& id_direccion == other.id_direccion && Objects.equals(nombre, other.nombre);
	}

	/**
	 * Devuelve una representación en forma de cadena del nombre del aeropuerto.
	 *
	 * @return String Nombre del aeropuerto.
	 */
	public String toString() {
		return this.nombre;
	}
}

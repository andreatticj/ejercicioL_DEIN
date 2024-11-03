package eu.andreatt.ejerciciol_dein.model;

import java.util.Objects;

/**
 * Clase que representa la información de los aeropuertos privados en el contexto de los ejercicios L-M.
 * Esta clase almacena detalles sobre el aeropuerto, como su nombre, ubicación,
 * capacidad, año de inauguración, número de socios y otros atributos relevantes.
 */
public class InformacionAeropuertosPrivados {

	/** Identificador único del aeropuerto privado. */
	private int id;

	/** Año en que se inauguró el aeropuerto. */
	private int anioInauguracion;

	/** Capacidad máxima del aeropuerto (número de aviones o pasajeros). */
	private int capacidad;

	/** Número específico de la dirección del aeropuerto. */
	private int numero;

	/** Número de socios que pertenecen al aeropuerto privado. */
	private int numeroSocios;

	/** Nombre del aeropuerto. */
	private String nombre;

	/** País donde se encuentra el aeropuerto. */
	private String pais;

	/** Ciudad donde se ubica el aeropuerto. */
	private String ciudad;

	/** Calle donde se localiza el aeropuerto. */
	private String calle;

	/**
	 * Constructor que inicializa un objeto InformacionAeropuertosPrivados
	 * con los parámetros proporcionados.
	 *
	 * @param id Identificador único del aeropuerto privado.
	 * @param nombre Nombre del aeropuerto.
	 * @param anioInauguracion Año de inauguración del aeropuerto.
	 * @param capacidad Capacidad máxima del aeropuerto.
	 * @param pais Nombre del país donde se ubica el aeropuerto.
	 * @param ciudad Nombre de la ciudad donde se localiza el aeropuerto.
	 * @param calle Nombre de la calle donde se encuentra el aeropuerto.
	 * @param numero Número específico de la dirección del aeropuerto.
	 * @param numeroSocios Número de socios que pertenecen al aeropuerto.
	 */
	public InformacionAeropuertosPrivados(int id, String nombre, int anioInauguracion, int capacidad, String pais, String ciudad, String calle, int numero, int numeroSocios) {
		this.id = id;
		this.nombre = nombre;
		this.anioInauguracion = anioInauguracion;
		this.capacidad = capacidad;
		this.numero = numero;
		this.numeroSocios = numeroSocios;
		this.pais = pais;
		this.ciudad = ciudad;
		this.calle = calle;
	}

	/**
	 * Obtiene el identificador del aeropuerto.
	 *
	 * @return id El identificador único del aeropuerto privado.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Establece el identificador del aeropuerto.
	 *
	 * @param id Identificador único a establecer para el aeropuerto privado.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Obtiene el año de inauguración del aeropuerto.
	 *
	 * @return anioInauguracion El año en que se inauguró el aeropuerto.
	 */
	public int getAnioInauguracion() {
		return anioInauguracion;
	}

	/**
	 * Establece el año de inauguración del aeropuerto.
	 *
	 * @param anioInauguracion Año a establecer para la inauguración del aeropuerto.
	 */
	public void setAnioInauguracion(int anioInauguracion) {
		this.anioInauguracion = anioInauguracion;
	}

	/**
	 * Obtiene la capacidad del aeropuerto.
	 *
	 * @return capacidad La capacidad máxima del aeropuerto.
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
	 * Obtiene el número específico de la dirección del aeropuerto.
	 *
	 * @return numero El número de la dirección del aeropuerto.
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Establece el número específico de la dirección del aeropuerto.
	 *
	 * @param numero Número a establecer para la dirección del aeropuerto.
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * Obtiene el número de socios del aeropuerto.
	 *
	 * @return numeroSocios El número de socios que pertenecen al aeropuerto.
	 */
	public int getNumeroSocios() {
		return numeroSocios;
	}

	/**
	 * Establece el número de socios del aeropuerto.
	 *
	 * @param numeroSocios Número de socios a establecer para el aeropuerto.
	 */
	public void setNumeroSocios(int numeroSocios) {
		this.numeroSocios = numeroSocios;
	}

	/**
	 * Obtiene el nombre del aeropuerto.
	 *
	 * @return nombre El nombre del aeropuerto privado.
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
	 * Obtiene el país del aeropuerto.
	 *
	 * @return pais El nombre del país donde se encuentra el aeropuerto.
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Establece el país del aeropuerto.
	 *
	 * @param pais Nombre del país a establecer para el aeropuerto.
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * Obtiene la ciudad del aeropuerto.
	 *
	 * @return ciudad El nombre de la ciudad donde se encuentra el aeropuerto.
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Establece la ciudad del aeropuerto.
	 *
	 * @param ciudad Nombre de la ciudad a establecer para el aeropuerto.
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * Obtiene la calle del aeropuerto.
	 *
	 * @return calle El nombre de la calle donde se localiza el aeropuerto.
	 */
	public String getCalle() {
		return calle;
	}

	/**
	 * Establece la calle del aeropuerto.
	 *
	 * @param calle Nombre de la calle a establecer para el aeropuerto.
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}

	/**
	 * Calcula y devuelve el código hash de este objeto InformacionAeropuertosPrivados.
	 *
	 * @return int Código hash basado en los atributos relevantes del aeropuerto privado.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(anioInauguracion, calle, capacidad, ciudad, id, nombre, numero, numeroSocios, pais);
	}

	/**
	 * Compara este objeto InformacionAeropuertosPrivados con otro para determinar si son iguales.
	 *
	 * @param obj Objeto con el que se comparará esta información de aeropuerto privado.
	 * @return boolean True si los aeropuertos son iguales, false de lo contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InformacionAeropuertosPrivados other = (InformacionAeropuertosPrivados) obj;
		return anioInauguracion == other.anioInauguracion && Objects.equals(calle, other.calle)
				&& capacidad == other.capacidad && Objects.equals(ciudad, other.ciudad) && id == other.id
				&& Objects.equals(nombre, other.nombre) && numero == other.numero && numeroSocios == other.numeroSocios
				&& Objects.equals(pais, other.pais);
	}
}

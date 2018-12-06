package model.vo;
import java.time.LocalDateTime;

public class Estacion implements Comparable <Estacion>, Vertice
{
	private int id;

	private String nombre;

	private double latitud;

	private double longitud;

	private int capacidad;

	private int numeroViajes;

	private LocalDateTime fechaInicio;

	public Estacion (int id, String nombre, double latitud, double longitud, int capacidad, LocalDateTime fecha) 
	{
		this.id = id;
		this.nombre = nombre;
		this.latitud = latitud;
		this.longitud = longitud;
		this.capacidad = capacidad;
		fechaInicio = fecha;
		numeroViajes = 0;
	}

	/**
	 * @return the id
	 */
	@Override
	public int darId()
	{
		return id;
	}

	/**
	 * @return the name
	 */
	public String darNombre()
	{
		return nombre;
	}

	/**
	 * @return the latitude
	 */
	@Override
	public double darLatitud()
	{
		return latitud;
	}

	/**
	 * @return the longitude
	 */
	@Override
	public double darLongitud()
	{
		return longitud;
	}

	/**
	 * @return the dpcapacity
	 */
	public int darCapacidad()
	{
		return capacidad;
	}

	/**
	 * @return the online_date
	 */
	public LocalDateTime darFechaInicio()
	{
		return fechaInicio;
	}

	/**
	 * @param id the id to set
	 */
	public void cambiarId(int id)
	{
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void cambiarNombre(String name)
	{
		nombre = name;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void cambiarLatitud(double latitude)
	{
		latitud = latitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void cambiarLongitud(double longitude)
	{
		longitud = longitude;
	}

	/**
	 * @param dpcapacity the dpcapacity to set
	 */
	public void cambiarCapacidad(int dpcapacity)
	{
		capacidad = dpcapacity;
	}

	/**
	 * @param online_date the online_date to set
	 */
	public void cambiarFechaInicio(LocalDateTime online_date)
	{
		fechaInicio = online_date;
	}

	public void aumentarNumeroViajes()
	{
		numeroViajes++;
	}

	@Override
	public int compareTo(Estacion est)
	{
		if (est.id == this.id)
			return 0;
		else if (this.id < est.id)
			return -1;
		else
			return 1;
	}
}
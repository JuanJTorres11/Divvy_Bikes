package model.vo;

public class Interseccion implements Vertice
{
	// Atributos

	private int id;

	private double latitud;

	private double longitud;


	// Constructor

	public Interseccion (int id, double latitud, double longitud) 
	{
		this.id = id;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	// Métodos

	/**
	 * @return the id
	 */
	@Override
	public int darId()
	{
		return id;
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
	 * @param id the id to set
	 */
	public void cambiarId(int id)
	{
		this.id = id;
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
}
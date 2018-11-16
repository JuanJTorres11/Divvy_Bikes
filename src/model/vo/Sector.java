package model.vo;

import model.data_structures.DoublyLinkedList;

public class Sector
{
	private double latMin;

	private double latMax;

	private double longMin;

	private double longMax;

	private int id;

	private DoublyLinkedList<Integer, Estacion> estaciones;

	private DoublyLinkedList<Integer, Interseccion> intersecciones;

	public Sector(double latMin, double latMax, double longMin, double longMax, int id)
	{
		this.latMin = latMin;
		this.latMax = latMax;
		this.longMin = longMin;
		this.longMax = longMax;
		this.id = id;
		intersecciones = new DoublyLinkedList<Integer, Interseccion>();
		estaciones = new DoublyLinkedList<Integer, Estacion>();
	}

	public void agregarInterseccion(Interseccion inter)
	{
		intersecciones.add(inter);
	}

	public void agregarEstacion (Estacion est)
	{
		estaciones.add(est);
	}

	/**
	 * @return the latMin
	 */
	public double darLatMin()
	{
		return latMin;
	}

	/**
	 * @return the latMax
	 */
	public double darLatMax()
	{
		return latMax;
	}

	/**
	 * @return the longMin
	 */
	public double darLongMin()
	{
		return longMin;
	}

	/**
	 * @return the longMax
	 */
	public double darLongMax()
	{
		return longMax;
	}

	/**
	 * @return the id
	 */
	public int darId()
	{
		return id;
	}

	public DoublyLinkedList<Integer, Interseccion> darIntersecciones()
	{
		return intersecciones;
	}

	public DoublyLinkedList<Integer, Estacion> darEstaciones()
	{
		return estaciones;
	}

	/**
	 * @param latMin the latMin to set
	 */
	public void cambiarLatMin(double latMin)
	{
		this.latMin = latMin;
	}

	/**
	 * @param latMax the latMax to set
	 */
	public void cambiarLatMax(double latMax)
	{
		this.latMax = latMax;
	}

	/**
	 * @param longMin the longMin to set
	 */
	public void cambiarLongMin(double longMin)
	{
		this.longMin = longMin;
	}

	/**
	 * @param longMax the longMax to set
	 */
	public void cambiarLongMax(double longMax)
	{
		this.longMax = longMax;
	}

	/**
	 * @param id the id to set
	 */
	public void cambiarId(int id)
	{
		this.id = id;
	}
}
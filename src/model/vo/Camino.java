package model.vo;

public class Camino implements Comparable<Camino>
{
	/**
	 * Vertice de inicio
	 */
	int idInicio;

	/**
	 * Vertice donde termina
	 */
	int idFin;

	/**
	 * Peso del camino, en este caso la distancia
	 */
	double peso;

	int duracion;

	int cont;

	public Camino (int i, int f, double w)
	{
		idInicio = i;
		idFin = f;
		peso = w;
	}

	public Camino (int i, int f, double w, int d)
	{
		idInicio = i;
		idFin = f;
		peso = w;
		duracion = d;
		cont = 1;
	}

	public Camino (int i, int f, int d)
	{
		idInicio = i;
		idFin = f;
		peso = 0;
		duracion = d;
		cont = 1;
	}

	public Camino ()
	{
		idInicio = 0;
		idFin = 0;
		peso = 0;
		duracion = 0;
		cont = 0;
	}

	/**
	 * @return the inicio
	 */
	public int darInicio()
	{
		return idInicio;
	}

	/**
	 * @return the fin
	 */
	public int darFin()
	{
		return idFin;
	}

	/**
	 * @return the peso
	 */
	public Double darPeso()
	{
		return peso;
	}

	/**
	 * @param inicio the inicio to cambiar
	 */
	public void cambiarInicio(int inicio)
	{
		idInicio = inicio;
	}

	/**
	 * @param fin the fin to cambiar
	 */
	public void cambiarFin(int fin)
	{
		idFin = fin;
	}

	/**
	 * @param peso the peso to cambiar
	 */
	public void cambiarPeso(Double peso)
	{
		this.peso = peso;
	}

	public int compareTo(Camino o) 
	{
		if (peso < o.peso)
			return -1;
		else if (peso > o.peso)
			return 1;
		return 0;
	}

	public void agregarDuracion(int duration)
	{
		// TODO Auto-generated method stub

	}
}
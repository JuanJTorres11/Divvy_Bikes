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

	public Camino (int i, int f, double w)
	{
		idInicio = i;
		idFin = f;
		peso = w;
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
}
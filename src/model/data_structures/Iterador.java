package model.data_structures;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Clase que representa el iterador sencillo (sólo avanza hacia adelante).
 * @param <E> Tipo de información que almacena el iterador.
 */
public class Iterador <K,V> implements Iterator<V>, Serializable 
{
	/**
	 * Constante de serialización
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * El nodo donde se encuentra el iterador.
	 */
	private KVNode <K,V> actual;

	/**
	 * Constructor iterador.
	 * @param primerNodo
	 */
	public Iterador(KVNode <K,V> primerNodo) 
	{
		actual = primerNodo;
	}

	/**
	 * Indica si aún hay elementos por recorrer
	 * @return true en caso de que  aún haya elemetos o false en caso contrario
	 */
	public boolean hasNext() 
	{
		return actual != null;
	}

	public V next() 
	{
		V valor = actual.darValor();
		actual = actual.darSiguiente();
		return valor;
	}
}
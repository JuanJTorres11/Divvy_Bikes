package model.data_structures;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Clase que representa el iterador sencillo (sólo avanza hacia adelante).
 * @param <E> Tipo de información que almacena el iterador.
 */
public class IteradorK <K,V> implements Iterator<K>, Serializable 
{

	/**
	 * Constante de serialización
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * El nodo donde se encuentra el iterador.
	 */
	private Node <K,V> actual;

	/**
	 * Constructor iterador.
	 * @param primerNodo
	 */
	public IteradorK(Node <K,V> primerNodo) 
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

	/**
	 * Devuelve el siguiente elemento a recorrer
	 * <b>post:</b> se actualizado actual al siguiente del actual
	 * @return objeto en actual
	 */

	public K next() 
	{
		K valor = actual.darLlave();
		actual = actual.darSiguiente();
		return valor;
	}
}
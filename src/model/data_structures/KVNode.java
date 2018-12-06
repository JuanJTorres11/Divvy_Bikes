package model.data_structures;

import java.io.Serializable;

public class KVNode <K,V> implements Serializable
{
	/**
	 * Constante de serializaci�n
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Llave del elemento almacenado en el nodo.
	 */
	private K llave;

	/**
	 * Elemento almacenado en el nodo.
	 */
	private V valor;

	/**
	 * Siguiente nodo.
	 */
	private KVNode <K,V> siguiente;

	/**
	 * M�todo constructor del nodo doblemente encadenado
	 * @param elemento elemento que se almacenar� en el nodo.
	 */
	public KVNode(V elemento) 
	{
		valor = elemento;
		llave = null;
		siguiente = null;
	}

	/**
	 * M�todo constructor del nodo doblemente encadenado
	 * @param elemento elemento que se almacenar� en el nodo.
	 */
	public KVNode(K key, V elemento) 
	{
		valor = elemento;
		llave = key;
		siguiente = null;
	}

	/**
	 * M�todo que retorna el elemento almacenado en el nodo.
	 * @return El elemento almacenado en el nodo.
	 */
	public V darValor()
	{
		return valor;
	}

	/**
	 * M�todo que retorna la llave del elemento almacenado en el nodo.
	 * @return La llave del elemento en el nodo.
	 */
	public K darLlave()
	{
		return llave;
	}

	/**
	 * Cambia el elemento almacenado en el nodo.
	 * @param elemento El nuevo elemento que se almacenar� en el nodo.
	 */
	public void cambiarElemento(K key, V elemento)
	{
		llave = key;
		valor = elemento;
	}

	/**
	 * M�todo que retorna el siguiente nodo.
	 * @return Siguiente nodo
	 */
	public KVNode <K,V> darSiguiente()
	{
		return siguiente;
	}

	/**
	 * M�todo que cambia el siguiente nodo.
	 * <b>post: </b> Se ha cambiado el siguiente nodo
	 * @param siguiente El nuevo siguiente nodo
	 */
	public void cambiarSiguiente(KVNode<K,V> siguiente)
	{
		this.siguiente = siguiente;
	}
}
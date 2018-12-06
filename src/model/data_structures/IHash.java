package model.data_structures;

import java.util.Iterator;

public interface IHash <K extends Comparable<K>, V>
{
	/**
	 * Agregar	 una	 dupla	 (K,	 V)	 a	 la	 tabla.	 Si	 la	 llave	 K	
	existe,	 se	 reemplaza	 su	 valor	 V	 asociado.	 V	 no	
	puede	ser	null.
	 * @throws Exception 
	 */
	public void	put (K key,V value);

	/**
	 * Obtener	el	valor	V	asociado	a	la	llave	K. V	no	puede	
	ser	null.
	 * @param key
	 * @return
	 */
	public V get(K key);

	/**
	 * Borrar	la	dupla	asociada	a	la	llave	K.	Se	obtiene	el	
	valor	 V	 asociado	 a	 la	 llave	 K.	 Se	 obtiene	 null si	 la	
	llave	K	no	existe.
	 * @param key
	 * @return
	 */
	public V delete (K key);

	/**
	 * @return  Conjunto	de	llaves	K	presentes	en	la	tabla
	 */
	public Iterator	iterator();
}
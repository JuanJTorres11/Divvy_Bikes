package model.data_structures;

import java.util.Iterator;

public class HashChain <K extends Comparable<K>, V> implements IHash<K,V>, Iterable
{
	private int capacidad;

	private int numeroElementos;

	private static final int factorCarga = 5;

	private DoublyLinkedList <K,V>[] lista;

	public HashChain (int capacidad)
	{
		this.capacidad = capacidad;
		lista = new DoublyLinkedList [capacidad];
		for (int i = 0; i < capacidad; i++)
			lista[i] = new DoublyLinkedList();
		numeroElementos = 0;
	}

	@Override
	public void put(K key, V value)
	{
		if (value != null && key != null)
		{
			V temp = get (key);
			if ( temp != null)
				temp = value;
			else
			{
				if (numeroElementos / capacidad <= factorCarga)
				{
					lista[hash(key)].add(key,value);
					numeroElementos ++;
				}
				else
				{
					rehash();
					lista[hash(key)].add(key,value);
					numeroElementos ++;
				}
			}
		}
	}

	@Override
	public V get(K key)
	{
		V value = null;
		boolean encontrado = false;
		DoublyLinkedList <K,V> tmp = lista[hash(key)];
		Node <K,V> iter = tmp.getFirst();
		while (iter != null && !encontrado)
		{
			if (iter.darLlave().equals(key))
			{
				encontrado = true;
				value = iter.darValor();
			}
			iter = iter.darSiguiente();
		}
		return value;
	}

	@Override
	public V delete(K key)
	{
		V value = null;
		boolean encontrado = false;
		DoublyLinkedList <K,V> tmp = lista[hash(key)];
		Node <K,V> iter = tmp.getFirst();
		int i = 0;
		while (iter != null && !encontrado)
		{
			if (iter.darLlave().equals(key))
			{
				encontrado = true;
				value = iter.darValor();
				tmp.removeAtK(i);
			}
			iter = iter.darSiguiente();
			i++;
		}
		return value;
	}

	private int hash(K key)
	{
		return (key.hashCode() & 0x7fffffff) % capacidad;
	}

	private void rehash()
	{
		capacidad *= 2;
		HashChain<K, V> temp = new HashChain<K, V> (capacidad);
		for (int i = 0; i < capacidad / 2; i++)
		{
			Node <K,V> iter = lista[i].getFirst();
			while (iter != null)
			{
				temp.put(iter.darLlave(), iter.darValor());
				iter = iter.darSiguiente();
			}
		}
		this.lista = temp.lista;
	}

	public int size()
	{
		return numeroElementos;
	}

	@Override
	public Iterator iterator()
	{
		DoublyLinkedList<K, V> temp = new DoublyLinkedList<K,V>();
		for(int i = 0; i < lista.length; i++)
			temp.concat(lista[i]);
		return temp.iterator();
	}

	public void clear()
	{
		lista = new DoublyLinkedList [capacidad];
	}

	@Override
	public Iterator iterador() {
		DoublyLinkedList<K, V> temp = new DoublyLinkedList<K,V>();
		for(int i = 0; i < lista.length; i++)
			temp.concat(lista[i]);
		return temp.iterator();
	}
}
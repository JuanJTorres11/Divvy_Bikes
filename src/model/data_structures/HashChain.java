package model.data_structures;

import java.util.Iterator;

public class HashChain <K extends Comparable<K>, V> implements IHash<K,V>, Iterable<V>
{
	private int capacidad;

	private int numeroElementos;

	private static final int factorCarga = 5;

	private KVLinkedList <K,V>[] lista;

	@SuppressWarnings("unchecked")
	public HashChain (int capacidad)
	{
		this.capacidad = capacidad;
		lista = new KVLinkedList [capacidad];
		for (int i = 0; i < capacidad; i++)
			lista[i] = new KVLinkedList<K,V>();
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
		KVLinkedList <K,V> tmp = lista[hash(key)];
		KVNode <K,V> iter = tmp.getFirst();
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
		KVLinkedList <K,V> tmp = lista[hash(key)];
		KVNode <K,V> iter = tmp.getFirst();
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
			KVNode <K,V> iter = lista[i].getFirst();
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
	public Iterator<V> iterator()
	{
		KVLinkedList<K, V> temp = new KVLinkedList<K,V>();
		for(int i = 0; i < lista.length; i++)
			temp.concat(lista[i]);
		return temp.iterator();
	}

	public Iterator<K> iteratorK()
	{
		KVLinkedList<K, V> temp = new KVLinkedList<K,V>();
		for(int i = 0; i < lista.length; i++)
			temp.concat(lista[i]);
		return temp.iteradorK();
	}

	@SuppressWarnings("unchecked")
	public void clear()
	{
		lista = new KVLinkedList [capacidad];
	}
}
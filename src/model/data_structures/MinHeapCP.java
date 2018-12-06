package model.data_structures;

public class MinHeapCP <T extends Comparable <T>>
{

	private T[] lista;
	private int size;
	private int tamanoMax;

	public MinHeapCP (int max)
	{
		lista = (T[]) new Comparable [max];
		size = 0;
		tamanoMax = max;
	}

	public int darNumElementos()
	{
		return size;
	}

	public void agregar(T elemento)
	{
		if (elemento != null)
		{
			lista[size + 1] = elemento;
			size++;
			swim(size);
		}
	}

	public T min()
	{
		if (!esVacia())
		{
			T max = lista[1];
			exch(1,size);
			lista[size--] = null;
			sink(1);
			return max;
		}
		else
			return null;
	}

	public boolean esVacia()
	{
		return size == 0;
	}

	public int tamanoMax()
	{
		return tamanoMax;
	}

	private  void exch( int i, int j)
	{
		T temp = lista[i];
		lista[i] = lista[j];
		lista[j] = temp;
	}

	private boolean less(T v, T w)
	{
		return v.compareTo(w) > 0;
	}

	private void swim(int k)
	{
		while (k > 1 && less(lista[k/2],lista[k]))
		{
			exch(k/2, k);
			k = k/2;
		}
	}

	private void sink(int k)
	{
		while (2*k <= size)
		{
			int j = 2*k;
			if (j < size && less(lista[j], lista[j+1])) j++;
			if (!less(lista[k], lista[j])) break;
			exch(k, j);
			k = j;
		}
	}

	public T get(int i)
	{
		return lista[i];
	}
}
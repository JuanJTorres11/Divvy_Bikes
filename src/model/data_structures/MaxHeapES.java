package model.data_structures;
import model.vo.Estacion;
import model.vo.Viaje;

public class MaxHeapES  <T extends Comparable <T>> implements IColaPrioridad <T>
{
	
		private T[] lista;
		private int size;
		private int tamanoMax;

		public MaxHeapES (int max)
		{
			lista = (T[]) new Comparable [max];
			size = 0;
			tamanoMax = max;
		}

		@Override
		public int darNumElementos()
		{
			return size;
		}

		@Override
		public void agregar(T elemento)
		{
			if (elemento != null)
			{
				lista[size + 1] = elemento;
				size++;
				swim(size);
			}
		}

		@Override
		public T max()
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

		@Override
		public boolean esVacia()
		{
			return size == 0;
		}

		@Override
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
			Estacion e1 = (Estacion)v;
			Estacion e2= (Estacion)w;
			return (e1.darCantidadViajesIn()+e1.darCantidadViajesOut())<(e2.darCantidadViajesIn()+e2.darCantidadViajesOut());
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

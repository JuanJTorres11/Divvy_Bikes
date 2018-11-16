package model.data_structures;

import java.util.Iterator;

/**
 * Clase que representa la lista doblemente encadenada
 * @param <E> Tipo de los objetos que almacenará la lista.
 */
public class DoublyLinkedList <K,V> implements ILista
{
	/**
	 * Atributo que indica la cantidad de elementos que han sido almacenados en la lista.
	 */
	private int cantidadElementos;

	/**
	 * Primer nodo de la lista.
	 */
	private Node <K,V> primerNodo;

	private Node<K,V> ultimoNodo;

	/**
	 * Construye una lista vacia
	 * <b>post:< /b> se ha inicializado el primer nodo en null
	 */
	public DoublyLinkedList () 
	{
		primerNodo = null;
		cantidadElementos = 0;
	}

	/**
	 * Se construye una nueva lista cuyo primer nodo  guardará al elemento que llega por parámentro
	 * @param nPrimero el elemento a guardar en el primer nodo
	 * @throws NullPointerException si el elemento recibido es nulo
	 */
	public DoublyLinkedList (K llave, V valor)
	{
		if(valor == null)
			throw new NullPointerException("Se recibe un elemento nulo");

		primerNodo = new Node <K,V>(llave, valor);
		cantidadElementos = 1;
	}

	/**
	 * Indica el tamaño de la lista.
	 * @return La cantidad de nodos de la lista.
	 */
	public int size() 
	{
		return cantidadElementos;
	}

	/**
	 * Indica si la lista está vacia
	 * @return true si la lista está vacia o false en caso contrario
	 */
	public boolean isEmpty() 
	{
		return primerNodo == null;
	}

	/**
	 * Borra todos los elementos de la lista. Actualiza la cantidad de elementos en 0
	 * <b>post:</b> No hay elementos en la lista
	 */
	public void clear() 
	{
		primerNodo = null;
		cantidadElementos = 0;
	}

	/**
	 * Método que retorna el iterador de la lista.
	 * @return el iterador de la lista.
	 */
	public Iterator<V> iterator() 
	{
		return new Iterador <K,V> (primerNodo);
	}

	/**
	 * Elimina el nodo que contiene al objeto que llega por parámetro.
	 * Actualiza la cantidad de elementos.
	 * @param objeto el objeto que se desea eliminar. objeto != null
	 * @return true en caso que exista el objeto y se pueda eliminar o false en caso contrario
	 */
	public boolean remove(Object o) 
	{
		boolean eliminado = false;
		boolean encontrado = false;
		Node<K,V> actual = (Node<K,V>) primerNodo;
		while (actual!= null && !encontrado)
		{
			if (actual.darValor().equals(o))
			{
				if (actual.darAnterior() != null)
					actual.darAnterior().cambiarSiguiente(actual.darSiguiente());
				else primerNodo = (Node<K,V>) actual.darSiguiente();
				if (actual.darSiguiente() != null)
				{
					Node<K,V> siguiente = (Node<K,V>) actual.darSiguiente();
					siguiente.cambiarAnterior(actual.darAnterior());
				}
				encontrado = true;
				eliminado = true;
				cantidadElementos --;
			}
			else 
			{
				actual = (Node<K,V>) actual.darSiguiente();
			}
		}
		return eliminado;
	}

	/**
	 * Elimina el nodo en la posición por parámetro.
	 * Actualiza la cantidad de elementos.
	 * @param pos la posición que se desea eliminar
	 * @return el elemento eliminado
	 * @throws IndexOutOfBoundsException si index < 0 o index >= size()
	 */
	public V removeAtK (int index) 
	{
		V eliminado = null;
		if (!isEmpty())
		{		
			if (index < 0 || index >= cantidadElementos)
				throw new IndexOutOfBoundsException();

			Node<K,V> actual = (Node<K,V>) primerNodo;
			int posActual = 0;
			while( posActual < (index))
			{
				posActual ++;
				actual = (Node<K,V>) actual.darSiguiente();
			}
			eliminado = actual.darValor();
			if (actual.darAnterior() != null)
				actual.darAnterior().cambiarSiguiente(actual.darSiguiente().darSiguiente());
			else primerNodo = (Node<K,V>) actual.darSiguiente();
			if (actual.darSiguiente() != null)
			{
				Node<K,V> siguiente = (Node<K,V>) actual.darSiguiente();
				siguiente.cambiarAnterior(actual.darAnterior());
			}
			cantidadElementos --;
		}
		return eliminado;
	}

	@Override
	public Node<K,V> getFirst()
	{
		return primerNodo;	
	}

	public Node <K,V> getLast()
	{
		return ultimoNodo;		
	}

	public Node <K,V> get (int index)
	{
		if(index < 0 || index >= cantidadElementos)
		{
			throw new IndexOutOfBoundsException("Se está pidiendo el indice: " + index + " y el tamaño de la lista es de " + cantidadElementos);
		}
		Node <K,V> actual = null;
		if ((index-0) <= (cantidadElementos-1-index))
		{
			actual = primerNodo;
			for (int i = 0; i <= index-0; i++)
				actual = actual.darSiguiente();
		}
		else
		{
			actual = ultimoNodo;
			for (int i = 0; i <= cantidadElementos-1-index; i++)
				actual = actual.darAnterior();
		}
		return actual;
	}

	public Node<K,V> get (K key)
	{
		Node <K,V> buscado = null;
		Node <K,V> actual = primerNodo;
		while (actual != null)
		{
			if (actual.darLlave().equals(key))
			{
				buscado = actual;
				break;
			}
			actual = actual.darSiguiente();
		}
		return buscado;
	}

	/**
	 * Devuelve el nodo de la posición dada
	 * @param pos la posición  buscada
	 * @return el nodo en la posición dada 
	 * @throws IndexOutOfBoundsException si index < 0 o index >= size()
	 */
	public V getElement (int index)
	{
		if(index < 0 || index >= cantidadElementos)
		{
			throw new IndexOutOfBoundsException("Se está pidiendo el indice: " + index + " y el tamaño de la lista es de " + cantidadElementos);
		}

		Node <K,V> actual = primerNodo;
		int posActual = 0;
		while(actual != null && posActual < index)
		{
			actual = actual.darSiguiente();
			posActual ++;
		}
		return actual.darValor();
	}


	public void concat (DoublyLinkedList <K,V> lista)
	{
		lista.getFirst().cambiarAnterior(ultimoNodo);
		ultimoNodo.cambiarSiguiente(lista.getFirst());
		ultimoNodo = lista.getLast();
		cantidadElementos += lista.size();
	}

	/**
	 * Agrega un elemento al inicio de la lista
	 * Un elemento no se agrega si la lista ya tiene un elemento con el mismo id.
	 * Se actualiza la cantidad de elementos.
	 * @param e el elemento que se desea agregar.
	 * @return true en caso que se agregue el elemento o false en caso contrario. 
	 * @throws NullPointerException si el elemento es nulo
	 */
	@Override
	public boolean add(Object e)
	{
		boolean añadido = false;
		if (e != null)
		{
			Node nuevo = new Node ((V) e);
			if (primerNodo == null)
			{
				primerNodo = nuevo;
				ultimoNodo = nuevo;
				añadido = true;
				cantidadElementos ++;
			}
			else
			{
				primerNodo.cambiarAnterior(nuevo);
				nuevo.cambiarSiguiente(primerNodo);
				primerNodo = nuevo;
				añadido = true;
				cantidadElementos ++;
			}
		}
		else throw new NullPointerException();
		return añadido;
	}

	/**
	 * Agrega un elemento al inicio de la lista
	 * Un elemento no se agrega si la lista ya tiene un elemento con el mismo id.
	 * Se actualiza la cantidad de elementos.
	 * @param e el elemento que se desea agregar.
	 * @return true en caso que se agregue el elemento o false en caso contrario. 
	 * @throws NullPointerException si el elemento es nulo
	 */
	public boolean add(K key, V value)
	{
		boolean añadido = false;
		if (value != null && key != null)
		{
			Node nuevo = new Node (key, value);
			if (primerNodo == null)
			{
				primerNodo = nuevo;
				ultimoNodo = nuevo;
				añadido = true;
				cantidadElementos ++;
			}
			else
			{
				primerNodo.cambiarAnterior(nuevo);
				nuevo.cambiarSiguiente(primerNodo);
				primerNodo = nuevo;
				añadido = true;
				cantidadElementos ++;
			}
		}
		else throw new NullPointerException();

		return añadido;
	}

	@Override
	public boolean addAtEnd(Object e)
	{
		boolean añadido = false;

		if (e != null)
		{
			Node <K,V> nuevo = new Node <K,V>((V) e);
			if(isEmpty())
			{
				primerNodo = nuevo;
				ultimoNodo = nuevo;
				añadido = true;
				cantidadElementos ++;
			}
			else
			{
				ultimoNodo.cambiarSiguiente(nuevo);
				nuevo.cambiarAnterior(ultimoNodo);
				ultimoNodo = nuevo;
				añadido = true;
				cantidadElementos ++;  
			}
		}
		else throw new NullPointerException();

		return añadido;
	}

	public boolean addAtEnd(K key, V value)
	{
		boolean añadido = false;

		if (value != null)
		{
			Node <K,V> nuevo = new Node <K,V>(key, value);
			if(isEmpty())
			{
				primerNodo = nuevo;
				ultimoNodo = nuevo;
				añadido = true;
				cantidadElementos ++;
			}
			else
			{
				ultimoNodo.cambiarSiguiente(nuevo);
				nuevo.cambiarAnterior(ultimoNodo);
				ultimoNodo = nuevo;
				añadido = true;
				cantidadElementos ++;  
			}
		}
		else throw new NullPointerException();

		return añadido;
	}

	/**
	 * Agrega un elemento en la posición dada de la lista. Todos los elementos siguientes se desplazan.
	 * Actualiza la cantidad de elementos.
	 * @param pos la posición donde se desea agregar. Si pos es igual al tamaño de la lista se agrega al final
	 * @param elem el elemento que se desea agregar
	 * @throws IndexOutOfBoundsException si el inidice es < 0 o > size()
	 * @throws NullPointerException Si el elemento que se quiere agregar es null.
	 */
	@Override
	public void addAtK(int index, Object elemento)
	{
		if (index < 0 || index > cantidadElementos)
			throw new IndexOutOfBoundsException();

		if (elemento != null)
		{
			if (isEmpty())
			{
				primerNodo = new Node <K,V>((V) elemento);
				cantidadElementos ++;
			}
			else
			{
				Node <K,V> nuevo = new Node <K,V>((V) elemento);
				if (index == 0)
					add(elemento);

				else if (index == cantidadElementos)
					addAtEnd(elemento);

				else 
				{
					Node <K,V> n = primerNodo;
					int posActual = 0;
					while( posActual < (index-1))
					{
						posActual++;
						n = n.darSiguiente();
					}
					nuevo.cambiarSiguiente(n.darSiguiente());
					nuevo.cambiarAnterior(n);
					n.cambiarSiguiente(nuevo);
					cantidadElementos ++;
				}
			}
		}
		else throw new NullPointerException();
	}

	public void addAtK(int index, K key, V value)
	{
		if (index < 0 || index > cantidadElementos)
			throw new IndexOutOfBoundsException();

		if (value != null)
		{
			if (isEmpty())
			{
				primerNodo = new Node <K,V>(key, value);
				cantidadElementos ++;
			}
			else
			{
				Node <K,V> nuevo = new Node <K,V>(key, value);
				if (index == 0)
					add(key, value);

				else if (index == cantidadElementos)
					addAtEnd(key, value);

				else 
				{
					Node <K,V> n = primerNodo;
					int posActual = 0;
					while( posActual < (index-1))
					{
						posActual++;
						n = n.darSiguiente();
					}
					nuevo.cambiarSiguiente(n.darSiguiente());
					nuevo.cambiarAnterior(n);
					n.cambiarSiguiente(nuevo);
					cantidadElementos ++;
				}
			}
		}
		else throw new NullPointerException();
	}
}
package model.data_structures;

import java.util.Iterator;

public class Cola <T> implements ICola <T>
{
	/**
	 * nodo del elemento que ingresó de primeras
	 */
	private KVNode primerNodo;

	/**
	 * nodo del elemento que fue el último en ingresar
	 */
	private KVNode ultimoNodo;


	private int size;

	public Cola (KVNode nodoInicial) 
	{
		primerNodo = ultimoNodo = nodoInicial;
		size = 1;
	}
	public Cola(){
	}

	@Override
	public Iterator<T> iterator()
	{
		return new Iterador (primerNodo);
	}

	public KVNode getPrimerNodo()
	{
		return primerNodo;
	}

	public KVNode getUltimoNodo()
	{
		return ultimoNodo;
	}

	@Override
	public boolean isEmpty() 
	{
		return size==0;
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public void enqueue(T t)
	{
		if( 0 < size)
		{
			KVNode nodoAgregandose =  new KVNode(t);
			ultimoNodo.cambiarSiguiente(nodoAgregandose);
			ultimoNodo = nodoAgregandose; 
			size ++;
		}
		else
		{
			KVNode nodoInicial =  new KVNode(t);
			primerNodo = nodoInicial;
			ultimoNodo = nodoInicial;
			size = 1;
		}
	}

	// retorna null si no hay elementos en la lista
	@Override
	public T dequeue()
	{
		if(0 < size)
		{
			T resp = (T) primerNodo.darValor();
			primerNodo = primerNodo.darSiguiente();
			size --;
			return resp;
		}
		return null;
	}

	public void setFirstKVNode(KVNode pNodo)
	{
		primerNodo = pNodo;
	}
}
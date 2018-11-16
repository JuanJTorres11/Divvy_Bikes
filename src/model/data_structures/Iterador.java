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
	private Node <K,V> actual;

	/**
	 * Constructor iterador.
	 * @param primerNodo
	 */
	public Iterador(Node <K,V> primerNodo) 
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
	//TODO creo que va a ser necesario hacer un metodo de consulta diferente al de avance. Porque este metodo de avance va guardando "valor" (si sé que se borra cuando
	//termina el método pero aun con eso para qué estar guardando cosas una y otra vez cuando se puede dividir el metodo en consulta y avance

	//TODO Además este método recorre listas sencillas no dobles
	public V next() 
	{
		V valor = actual.darValor();
		actual = actual.darSiguiente();
		return valor;
	}

	public V previous ()
	{
		//TODO Retorna el elemento anterior ya que la lista doble carga los viajes de atras hacia adelante 
		// y se requiere que en las pilas y colas estén ordenadas como aparece en el archivo.
		return null;
	}
}
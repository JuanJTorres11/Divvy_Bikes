package model.data_structures;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Clase que representa el iterador sencillo (s�lo avanza hacia adelante).
 * @param <E> Tipo de informaci�n que almacena el iterador.
 */
public class Iterador <K,V> implements Iterator<V>, Serializable 
{

	/**
	 * Constante de serializaci�n
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
	 * Indica si a�n hay elementos por recorrer
	 * @return true en caso de que  a�n haya elemetos o false en caso contrario
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
	//TODO creo que va a ser necesario hacer un metodo de consulta diferente al de avance. Porque este metodo de avance va guardando "valor" (si s� que se borra cuando
	//termina el m�todo pero aun con eso para qu� estar guardando cosas una y otra vez cuando se puede dividir el metodo en consulta y avance

	//TODO Adem�s este m�todo recorre listas sencillas no dobles
	public V next() 
	{
		V valor = actual.darValor();
		actual = actual.darSiguiente();
		return valor;
	}

	public V previous ()
	{
		//TODO Retorna el elemento anterior ya que la lista doble carga los viajes de atras hacia adelante 
		// y se requiere que en las pilas y colas est�n ordenadas como aparece en el archivo.
		return null;
	}
}
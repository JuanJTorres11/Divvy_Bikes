package model.data_structures;

public interface IColaPrioridad <T extends Comparable <T>> 
{
	/**
	 * Retorna n�mero de elementos presentes en la cola de prioridad
	 * @return
	 */
	public int darNumElementos();

	/**
	 * Agrega un elemento a la cola. Se genera Exception en el caso que se sobrepase el tama�o m�ximo de la cola
	 * @param elemento
	 * @throws Exception 
	 */
	public void agregar(T elemento) throws Exception;

	/**
	 * Saca/atiende el elemento m�ximo en la cola y lo retorna; null en caso de cola vac�a
	 * @return
	 */
	public T max();

	/**
	 * @return si la cola est� vac�a o no.
	 */
	public boolean esVacia();

	/**
	 * @return la capacidad m�xima de la cola.
	 */
	public int tamanoMax();
}
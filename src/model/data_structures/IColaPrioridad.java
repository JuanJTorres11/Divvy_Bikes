package model.data_structures;

public interface IColaPrioridad <T extends Comparable <T>> 
{
	/**
	 * Retorna número de elementos presentes en la cola de prioridad
	 * @return
	 */
	public int darNumElementos();

	/**
	 * Agrega un elemento a la cola. Se genera Exception en el caso que se sobrepase el tamaño máximo de la cola
	 * @param elemento
	 * @throws Exception 
	 */
	public void agregar(T elemento) throws Exception;

	/**
	 * Saca/atiende el elemento máximo en la cola y lo retorna; null en caso de cola vacía
	 * @return
	 */
	public T max();

	/**
	 * @return si la cola está vacía o no.
	 */
	public boolean esVacia();

	/**
	 * @return la capacidad máxima de la cola.
	 */
	public int tamanoMax();
}
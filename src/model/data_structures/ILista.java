package model.data_structures;

/**
 * Abstract Data Type for a doubly-linked list of generic objects
 * This ADT should contain the basic operations to manage a list
 * add, addAtEnd, AddAtK, getElement, getCurrentElement, getSize, delete, deleteAtK
 * next, previous
 * @param <E>
 */
public interface ILista <K,V> extends Iterable<V>
{
	public boolean addAtEnd(V elem);

	public void addAtK (int i, V elem);

	public V getElement(int i);

	public int size();

	public boolean remove(V elem);

	public V removeAtK(int i);

	public boolean add(V e);

	Node<K, V> getFirst();
}
package model.data_structures;

import model.vo.Interseccion;
import model.vo.Vertice;

public class Grafo <K extends Comparable <K>, V extends Vertice, A > implements IGrafo<K, V, A>
{
	private int vertices;

	private int arcos;

	HashChain <K,DoublyLinkedList<K,V>>listaAdyacencia;

	public Grafo ()
	{
		vertices = 0;
		arcos = 0;
		listaAdyacencia = new HashChain <K, DoublyLinkedList<K,V>>(80000);
	}
	@Override
	public int V()
	{
		return vertices;
	}

	@Override
	public int E()
	{
		return arcos;
	}

	@Override
	public void addVertex(K idVertex, V infoVertex)
	{
		DoublyLinkedList<K,V> temp = new DoublyLinkedList <K,V>(idVertex, infoVertex);
		listaAdyacencia.put(idVertex, temp);
	}

	@Override
	public void addEdge(K idVertexIni, K idVertexFin, A infoArc)
	{
		DoublyLinkedList<K,V> temp = listaAdyacencia.get(idVertexIni);
		temp.add(idVertexFin, (V) new Interseccion((int) idVertexFin,infoArc));
	}

	@Override
	public V getInfoVertex(K idVertex)
	{
		return listaAdyacencia.get(idVertex).getFirst().darValor();
	}

	@Override
	public void setInfoVertex(K idVertex, V infoVertex)
	{
		listaAdyacencia.get(idVertex).getFirst().cambiarElemento(idVertex, infoVertex);
	}

	@Override
	public A getInfoArc(K idVertexIni, K idVertexFin)
	{
		return (A) listaAdyacencia.get(idVertexIni).get(idVertexFin).darValor().darInformaciónArco();
	}

	@Override
	public void setInfoArc(K idVertexIni, K idVertexFin, A infoArc)
	{
		listaAdyacencia.get(idVertexIni).get(idVertexFin).darValor().cambiarInformacionArco(infoArc);
	}

	@Override
	public Iterable<K> adj(K idVertex)
	{
		return listaAdyacencia.get(idVertex);
	}
}
package model.data_structures;

import java.util.Iterator;

import model.vo.Camino;
import model.vo.Vertice;

public class GrafoNoDirigido <K extends Comparable <K>, V extends Vertice, A extends Camino> implements IGrafo<K, V, A>
{
	private int vertices;

	private int arcos;

	public HashChain <K,KVLinkedList<K,A>> listaAdyacencia;

	private HashChain <K,V> infoVertices;

	public GrafoNoDirigido()
	{
		vertices = 0;
		arcos = 0;
		listaAdyacencia = new HashChain <K, KVLinkedList<K,A>>(70000);
		infoVertices = new HashChain <K,V> (70000);
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
		KVLinkedList<K,A> temp = new KVLinkedList <K,A>();
		listaAdyacencia.put(idVertex, temp);
		infoVertices.put(idVertex, infoVertex);
		vertices++;
	}

	@Override
	public void addEdge(K idVertexIni, K idVertexFin, A infoArc)
	{
		KVLinkedList<K,A> temp = listaAdyacencia.get(idVertexIni);
		temp.add(idVertexFin, infoArc);
		temp = listaAdyacencia.get(idVertexFin);
		temp.add(idVertexIni, infoArc);
		arcos++;
	}

	@Override
	public V getInfoVertex(K idVertex)
	{
		return infoVertices.get(idVertex);
	}

	@Override
	public void setInfoVertex(K idVertex, V infoVertex)
	{
		infoVertices.put(idVertex, infoVertex);
	}

	@Override
	public A getInfoArc(K idVertexIni, K idVertexFin)
	{
		return listaAdyacencia.get(idVertexIni).get(idVertexFin).darValor();
	}

	@Override
	public void setInfoArc(K idVertexIni, K idVertexFin, A infoArc)
	{
		listaAdyacencia.get(idVertexIni).get(idVertexFin).cambiarElemento(idVertexFin, infoArc);
	}

	@Override
	public Iterator<K> adj(K idVertex)
	{
		return listaAdyacencia.get(idVertex).iteradorK();
	}

	public Iterator<V> vertices()
	{
		return infoVertices.iterator();
	}
	
	public Iterator<A> arcos()
	{
		return listaAdyacencia.iterator().next().iterator();
	}
}
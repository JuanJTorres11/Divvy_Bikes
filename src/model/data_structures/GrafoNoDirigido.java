package model.data_structures;

import java.util.Iterator;

import model.vo.Interseccion;
import model.vo.Vertice;

public class GrafoNoDirigido <K extends Comparable <K>, V extends Vertice<A>, A > implements IGrafo<K, V, A>
{
	private int vertices;

	private int arcos;

	HashChain <K,KVLinkedList<K,V>>listaAdyacencia;

	public GrafoNoDirigido ()
	{
		vertices = 0;
		arcos = 0;
		listaAdyacencia = new HashChain <K, KVLinkedList<K,V>>(80000);
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
		KVLinkedList<K,V> temp = new KVLinkedList <K,V>(idVertex, infoVertex);
		listaAdyacencia.put(idVertex, temp);
		vertices++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addEdge(K idVertexIni, K idVertexFin, A infoArc)
	{
		listaAdyacencia.get(idVertexIni).add(idVertexFin, (V) new Interseccion<A>((int) idVertexFin,infoArc));
		listaAdyacencia.get(idVertexFin).add(idVertexIni, (V) new Interseccion<A>((int) idVertexIni,infoArc));
		arcos++;
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
		return listaAdyacencia.get(idVertexIni).get(idVertexFin).darValor().darInformaciónArco();
	}

	@Override
	public void setInfoArc(K idVertexIni, K idVertexFin, A infoArc)
	{
		listaAdyacencia.get(idVertexIni).get(idVertexFin).darValor().cambiarInformacionArco(infoArc);
	}

	@Override
	public Iterator<K> adj(K idVertex)
	{
		return listaAdyacencia.get(idVertex).iteradorK();
	}
}
package model.data_structures;

import model.vo.Camino;

public class Kruskal
{
	private Cola<Camino> mst;
	public Kruskal(GrafoNoDirigido G)
	{
		mst = new Cola<Camino>();
		MinHeapCP<Camino> pq = new MinHeapCP<Camino>(G.arcos());
		UnionFind uf = new UnionFind(G.V());
		while (!pq.esVacia() && mst.size() < G.V()-1)
		{
			Camino e = pq.min(); // Get min weight edge on pq
			int v = e.darInicio()-627, w = e.darFin()-627; // and its vertices.
			if (uf.connected(v, w))
				continue; // Ignore ineligible edges.
			uf.union(v, w); // Merge components.
			mst.enqueue(e); // Add edge to mst.
		}
	}
	public Iterable<Camino> edges()
	{
		return mst;
	}
	public double weight()
	{
		// See Exercise 4.3.31.
	}
}
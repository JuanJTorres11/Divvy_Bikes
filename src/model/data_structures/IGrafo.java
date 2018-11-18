package model.data_structures;

import java.util.Iterator;

public interface IGrafo <K extends Comparable<K>,V, A>
{
	/**
	 * @return N�mero de v�rtices
	 */
	int V();

	/**
	 * @return N�mero de arcos. Cada arco No dirigido debe contarse una �nica vez.
	 */
	int E();

	/**
	 * Adiciona un v�rtice con un Id �nico. El v�rtice tiene la informaci�n InfoVertex
	 * @param idVertex
	 * @param infoVertex
	 */
	void addVertex( K idVertex, V infoVertex);

	/**
	 * Adiciona el arco No dirigido entre el vertice IdVertexIni y el vertice IdVertexFin. El arco tiene la informaci�n infoArc.
	 * @param idVertexIni
	 * @param idVertexFin
	 * @param infoArc
	 */
	void addEdge(K idVertexIni, K idVertexFin, A infoArc );

	/**
	 * @param idVertex
	 * @return Obtener la informaci�n de un v�rtice
	 */
	V getInfoVertex(K idVertex);

	/**
	 * Modificar la informaci�n del v�rtice idVertex
	 * @param idVertex
	 * @param infoVertex
	 */
	void setInfoVertex(K idVertex, V infoVertex);

	/**
	 * @param idVertexIni
	 * @param idVertexFin
	 * @return Obtener la informaci�n de un arco
	 */
	A getInfoArc(K idVertexIni, K idVertexFin);

	/**
	 * Modificar la informaci�n del arco entre los v�rtices idVertexIni e idVertexFin
	 * @param idVertexIni
	 * @param idVertexFin
	 * @param infoArc
	 */
	void setInfoArc(K idVertexIni, K idVertexFin, A infoArc);

	/**
	 * @param idVertex
	 * @return Retorna los identificadores de los v�rtices adyacentes a idVertex.
	 */
	Iterator <K> adj (K idVertex);
}
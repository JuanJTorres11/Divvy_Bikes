package model.data_structures;

import java.util.Iterator;

public interface IGrafo <K extends Comparable<K>,V, A>
{
	/**
	 * @return Número de vértices
	 */
	int V();

	/**
	 * @return Número de arcos. Cada arco No dirigido debe contarse una única vez.
	 */
	int E();

	/**
	 * Adiciona un vértice con un Id único. El vértice tiene la información InfoVertex
	 * @param idVertex
	 * @param infoVertex
	 */
	void addVertex( K idVertex, V infoVertex);

	/**
	 * Adiciona el arco No dirigido entre el vertice IdVertexIni y el vertice IdVertexFin. El arco tiene la información infoArc.
	 * @param idVertexIni
	 * @param idVertexFin
	 * @param infoArc
	 */
	void addEdge(K idVertexIni, K idVertexFin, A infoArc );

	/**
	 * @param idVertex
	 * @return Obtener la información de un vértice
	 */
	V getInfoVertex(K idVertex);

	/**
	 * Modificar la información del vértice idVertex
	 * @param idVertex
	 * @param infoVertex
	 */
	void setInfoVertex(K idVertex, V infoVertex);

	/**
	 * @param idVertexIni
	 * @param idVertexFin
	 * @return Obtener la información de un arco
	 */
	A getInfoArc(K idVertexIni, K idVertexFin);

	/**
	 * Modificar la información del arco entre los vértices idVertexIni e idVertexFin
	 * @param idVertexIni
	 * @param idVertexFin
	 * @param infoArc
	 */
	void setInfoArc(K idVertexIni, K idVertexFin, A infoArc);

	/**
	 * @param idVertex
	 * @return Retorna los identificadores de los vértices adyacentes a idVertex.
	 */
	Iterator <K> adj (K idVertex);
}
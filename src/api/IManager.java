package api;

import model.data_structures.IGrafo;
import model.data_structures.IKVLista;
import model.vo.Camino;
import model.vo.ComponenteFuertementeConectada;
import model.vo.Estacion;

public interface IManager
{
	/**
	 * Dada la direccion del json que se desea cargar, se generan vo's, estructuras y datos necesarias
	 * @return true si se lo logro cargar, false de lo contrario
	 */
	public void cargarSistema();

	/**
	 * Retorna el camino de costo m�nimo (menor distancia) para un viaje en bicicleta que inicia en un punto (lat,lon) y
	 * finaliza en un punto (lat,lon) escogidos aleatoriamente.
	 * @return El camino a seguir
	 */
	public Camino A1_menorDistancia(double latInicial, double lonInicial, double latFinal, double lonFinal);

	/**
	 * Retorna el camino de costo m�nimo (menor n�mero de v�rtices) para un viaje en bicicleta que inicia en un punto (lat,lon) y
	 * finaliza en un punto (lat,lon) escogidos aleatoriamente.
	 * @return El camino a seguir
	 */
	public Camino A2_menorNumVertices(double latInicial, double lonInicial, double latFinal, double lonFinal);

	/**
	 * Retorna la lista de las n estaciones m�s congestionadas de Chicago (aquellas que contiene la mayor cantidad de viajes que salen y llegan a esta) 
	 * @param n. El n�mero de estaciones
	 * @return Una lista de las n estaciones m�s congestionadas
	 */
	public IKVLista<Integer, Estacion> B1_estacionesCongestionadas(int n);

	/**
	 * Retorna una lista con las rutas m�nimas (con criterio distancia harvesiana) que conecten las n estaciones encontradas.
	 * @return Una lista con las rutas encontradas.
	 */
	public IKVLista<Integer, Camino> B2_rutasMinimas(IKVLista<Integer, Estacion> stations);

	/**
	 * Crea un Grafo Dirigido tomando como v�rtices �nicamente los nodos estaci�n y como arcos los viajes de bicicletas entre las mismas.
	 * Retorna la informaci�n del grafo: El n�mero de v�rtices y arcos
	 * @return La informaci�n dle grafo
	 */
	public IGrafo C1_grafoEstaciones();

	/**
	 * Persisten en formato JSON el grafo de estaciones creado anteriormente
	 */
	public void C1_persistirGrafoEstaciones(IGrafo grafoEstaciones);

	/**
	 * Calcule los componentes fuertemente conexos presentes en el grafo de estaciones anteriormente construido.
	 * Retorna la informaci�n de las componentes: El n�mero total de componentes, y por cada componente: su color, el n�mero de v�rtices y estaciones.
	 * @param grafo grafo dirigido para calcular componentes fuertemente conectadas
	 * @return La informaci�n de la componentes
	 */
	public IKVLista<Integer,ComponenteFuertementeConectada> C2_componentesFuertementeConectados(IGrafo grafo);

	/**
	 * A partir del grafo de estaciones construido anteriormente pinte sobre el mapa de la red vial de Chicago
	 */
	public void C3_pintarGrafoEstaciones(IGrafo grafoEstaciones);
}
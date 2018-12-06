package controller;

import api.IManager;
import model.vo.Camino;
import model.vo.ComponenteFuertementeConectada;
import model.vo.Estacion;
import model.data_structures.IGrafo;
import model.data_structures.IKVLista;
import model.logic.Manager;

public class Controller 
{
	/**
	 * modela el manejador de la clase l�gica
	 */
	private static IManager manager =new Manager();

	//Carga El sistema
	public static void cargarSistema()
	{
		manager.cargarSistema();
	}

	//A1
	public static Camino A1_menorDistancia(double latInicial, double lonInicial, double latFinal, double lonFinal)
	{
		return manager.A1_menorDistancia(latInicial,lonInicial,latFinal,lonFinal);
	}

	//A2
	public static Camino A2_menorNumVertices(double latInicial, double lonInicial, double latFinal, double lonFinal)
	{
		return manager.A2_menorNumVertices(latInicial,lonInicial,latFinal,lonFinal);
	}

	//B1
	public static IKVLista<?, Estacion> B1_estacionesCongestionadas(int n)
	{
		return manager.B1_estacionesCongestionadas(n);
	}

	//B2
	public static IKVLista<?, Camino> B2_rutasMinimas(IKVLista<?,Estacion> estaciones)
	{
		return manager.B2_rutasMinimas(estaciones);
	}

	//C1
	public static IGrafo C1_grafoEstaciones()
	{
		IGrafo grafoEstaciones = manager.C1_grafoEstaciones();
		manager.C1_persistirGrafoEstaciones(grafoEstaciones);
		return grafoEstaciones;
	}

	//C2
	public static IKVLista<?,ComponenteFuertementeConectada> C2_componentesFuertementeConectados(IGrafo grafo)
	{
		return manager.C2_componentesFuertementeConectados(grafo);
	}

	//C3
	public static void C3_pintarGrafoEstaciones(IGrafo grafoEstaciones)
	{
		manager.C3_pintarGrafoEstaciones(grafoEstaciones);
	}
}

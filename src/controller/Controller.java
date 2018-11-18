package controller;

import api.IManager;
import model.logic.Manager;

public class Controller
{
	private static IManager manager = new Manager();

	public static int[] cargarDatos(String dataTrips, String dataStations)
	{
		return manager.cargar(dataTrips, dataStations);
	}

	public String caminoCostoMinimo (double latI, double longI, double latF, double longF)
	{
		return manager.caminoCostoMinimo(latI, longI, latF, longF);
	}

	public String caminoMasCorto (double latI, double longI, double latF, double longF)
	{
		return manager.caminoMasCorto(latI, longI, latF, longF);
	}

	public String estacionesMasCongestionadas()
	{
		return manager.estacionesMasCongestionadas();
	}

	public String rutasEstaciones()
	{
		return manager.rutasEstaciones();
	}

	public String crearGrafo()
	{
		return manager.crearGrafo();
	}

	public String componentesConectados()
	{
		return manager.componentesConectados();
	}

	public void visualizarMapa()
	{
		manager.visualizarMapa();
	}
}
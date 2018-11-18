package api;

import java.io.File;

import model.logic.DibujarFiguras;

public interface IManager
{
	int[] cargar(String rutaTrips, String rutaStations);

	public void cargarIntersecciones();

	public void cargarEstaciones(String ruta);

	public void generarJSON(String archivo);

	public DibujarFiguras mostrarMapa();

	public String caminoCostoMinimo (double latI, double longI, double latF, double longF);

	public String caminoMasCorto (double latI, double longI, double latF, double longF);

	public String estacionesMasCongestionadas();

	public String rutasEstaciones();

	public String crearGrafo();

	public String componentesConectados();
	
	public void visualizarMapa();
}
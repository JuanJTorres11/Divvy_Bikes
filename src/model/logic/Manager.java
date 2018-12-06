package model.logic;

import model.data_structures.KVLinkedList;
import model.data_structures.GrafoNoDirigido;
import model.data_structures.IGrafo;
import model.data_structures.IKVLista;
import model.vo.Camino;
import model.vo.ComponenteFuertementeConectada;
import model.vo.Estacion;
import model.vo.Interseccion;
import model.vo.Vertice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.teamdev.jxmaps.MapViewOptions;

import api.IManager;

public class Manager <K extends Comparable <K> ,V> implements IManager
{
	//-------------------------------------------------------------------------------------
	// CONSTANTES
	//-------------------------------------------------------------------------------------

	//Ruta del archivo
	public static final String RUTAS = "data" + File.separator + "Bike_Routes.JSON";

	//Ruta del archivo de datos 2017-Q1
	public static final String TRIPS_Q1 = "data" + File.separator + "Divvy_Trips_2017_Q1.csv";

	//Ruta del archivo de trips 2017-Q2
	public static final String TRIPS_Q2 = "data" + File.separator + "Divvy_Trips_2017_Q2.csv";

	//Ruta del archivo de trips 2017-Q3
	public static final String TRIPS_Q3 = "data" + File.separator + "Divvy_Trips_2017_Q3.csv";

	//Ruta del archivo de trips 2017-Q4
	public static final String TRIPS_Q4 = "data" + File.separator + "Divvy_Trips_2017_Q4.csv";

	//Ruta del archivo de stations 2017-Q1-Q2
	public static final String STATIONS_Q1_Q2 = "data" + File.separator + "Divvy_Stations_2017_Q1Q2.csv";

	//Ruta del archivo de stations 2017-Q3-Q4
	public static final String STATIONS_Q3_Q4 = "data" + File.separator + "Divvy_Stations_2017_Q3Q4.csv";

	//Ruta del archivo de Intersecciones en la ciudad de Chicago
	public static final String INTERSECCIONES = "data" + File.separator + "Nodes_of_Chicago_Street_Lines.txt";

	//Ruta del archivo de Intersecciones en la ciudad de Chicago
	public static final String ARCOS = "data" + File.separator + "Adjacency_list_of_Chicago_Street_Lines.txt";

	//Ruta del grafo
	public static final String GRAFO = "data" + File.separator + "grafo.json";

	//-------------------------------------------------------------------------------------
	// ATRIBUTOS
	//-------------------------------------------------------------------------------------

	private double latMin;

	private double latMax;

	private double longMin;

	private double longMax;

	// Estructuras donde se cargan los archivos

	GrafoNoDirigido <Integer, Vertice, Camino> grafo = new GrafoNoDirigido <Integer, Vertice, Camino>();

	//-------------------------------------------------------------------------------------
	// MÉTODOS DE LA GUIA
	//-------------------------------------------------------------------------------------

	public DibujarFiguras mostrarMapa(IKVLista <Integer,Vertice> circulos, IKVLista <Integer,Vertice> rectangulos, IKVLista <Integer,Camino> lineas)
	{
		MapViewOptions options = new MapViewOptions();
		options.importPlaces();
		options.setApiKey("***REMOVED***");
		DibujarFiguras mapa = new DibujarFiguras(options, circulos, rectangulos, lineas);
		return mapa;
	}

	public void cargarSistema()
	{
		Type tipoGrafo = new TypeToken<GrafoNoDirigido<Integer, Vertice, Camino>>() {}.getType();
		Gson json = new Gson();
		try
		{
			grafo = json.fromJson(new FileReader(GRAFO), (Type) tipoGrafo);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public Camino A1_menorDistancia(double latInicial, double lonInicial, double latFinal, double lonFinal)
	{
		//TODO Visualizacion Mapa
		return null;
	}

	public Camino A2_menorNumVertices(double latInicial, double lonInicial, double latFinal, double lonFinal)
	{
		//TODO Visualizacion Mapa
		return null;
	}

	public IKVLista<?, Estacion> B1_estacionesCongestionadas(int n)
	{
		//TODO Visualizacion Mapa
		return null;
	}

	public IKVLista<?, Camino> B2_rutasMinimas(IKVLista<?, Estacion> stations)
	{
		//TODO Visualizacion Mapa
		return null;
	}

	public IGrafo C1_grafoEstaciones()
	{
		//TODO Visualizacion Mapa
		return null;
	}

	public void C1_persistirGrafoEstaciones(IGrafo grafoEstaciones)
	{
		//TODO Visualizacion Mapa
	}

	public IKVLista<?, ComponenteFuertementeConectada> C2_componentesFuertementeConectados(IGrafo grafo)
	{
		//TODO Visualizacion Mapa
		return null;
	}

	public void C3_pintarGrafoEstaciones(IGrafo grafoEstaciones)
	{
		//TODO Visualizacion Mapa!

	}

	//-------------------------------------------------------------------------------------
	// EXTENSIÓN
	//-------------------------------------------------------------------------------------

	public void generarJSON(String archivo)
	{
		try
		{
			Type tipoGrafo = new TypeToken<GrafoNoDirigido<Integer, Vertice, Camino>>() {}.getType();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			File file = new File (archivo);
			if (!file.exists())
				file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write(gson.toJson(grafo,(Type) tipoGrafo));
			writer.close();
		}
		catch (Exception e)
		{
			System.out.println("Hubo un problema al intentar generar el JSON, el problema es: " + e.getMessage());
			//e.printStackTrace();
		}
	}

	private static final int RADIO_TIERRA = 6371;

	private static double distancia (double startLat, double startLong, double endLat, double endLong)
	{
		double dLat  = Math.toRadians((endLat - startLat));
		double dLong = Math.toRadians((endLong - startLong));

		startLat = Math.toRadians(startLat);
		endLat   = Math.toRadians(endLat);

		double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return RADIO_TIERRA * c;
	}

	private static double haversin(double val)
	{
		return Math.pow(Math.sin(val / 2), 2);
	}

	private static LocalDateTime convertirFecha_Hora_LDT (String fecha, String hora)
	{
		String[] datosFecha = fecha.split("/");
		String[] datosHora = hora.split(":");

		int agno = Integer.parseInt(datosFecha[2]);
		int mes = Integer.parseInt(datosFecha[0]);
		int dia = Integer.parseInt(datosFecha[1]);
		int horas = Integer.parseInt(datosHora[0]);
		int minutos = Integer.parseInt(datosHora[1]);
		if (datosHora.length > 2)
		{
			int segundos = Integer.parseInt(datosHora[2]);
			return LocalDateTime.of(agno, mes, dia, horas, minutos, segundos);
		}
		else
			return LocalDateTime.of(agno, mes, dia, horas, minutos);
	}
}
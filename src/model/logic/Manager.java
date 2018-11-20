package model.logic;

import model.data_structures.DoublyLinkedList;
import model.data_structures.GrafoNoDirigido;
import model.vo.Estacion;
import model.vo.Interseccion;
import model.vo.Trip;
import model.vo.Vertice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
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
	public static final String GRAFO = "data" + File.separator + "test.json";

	//-------------------------------------------------------------------------------------
	// ATRIBUTOS
	//-------------------------------------------------------------------------------------

	private double latMin;

	private double latMax;

	private double longMin;

	private double longMax;

	// Estructuras donde se cargan los archivos

	GrafoNoDirigido <Integer, Vertice<Double>, Double> grafo = new GrafoNoDirigido <Integer, Vertice<Double>, Double>();

	private DoublyLinkedList <Integer, Interseccion<Double>> intersecciones = new DoublyLinkedList <Integer, Interseccion<Double>> ();

	private DoublyLinkedList <Integer, Estacion<Double>> estaciones = new DoublyLinkedList <Integer, Estacion<Double>> ();

	private DoublyLinkedList <Integer,Trip> viajes = new DoublyLinkedList <Integer,Trip>();

	//-------------------------------------------------------------------------------------
	// MÉTODOS DE LA GUIA
	//-------------------------------------------------------------------------------------

	@Override
	public void cargarIntersecciones()
	{
		int i = 0;
		// Carga de vertices
		try
		{
			FileReader lector = new FileReader(INTERSECCIONES);
			BufferedReader reader = new BufferedReader (lector);
			String l = reader.readLine();
			while (l != null)
			{
				String [] linea = l.split(",");
				int id = Integer.parseInt(linea[0]);
				double latitud = Double.parseDouble(linea[2]);
				double longitud = Double.parseDouble(linea[1]);
				Interseccion<Double> inter = new Interseccion<Double> (id, latitud, longitud);
				intersecciones.add(id, inter);
				i ++;
				if (i == 1)
				{
					latMin = latitud;
					latMax = latitud;
					longMin = longitud;
					longMax = longitud;
				}
				else
				{
					if (latitud < latMin)
						latMin = latitud;
					else if (latitud > latMax)
						latMax = latitud;
					if (longitud < longMin)
						longMin = longitud;
					else if (longitud > longMax)
						longMax = longitud;
				}
				l = reader.readLine();
			}
			reader.close();
			lector.close();
		}
		catch (Exception e)
		{
			System.out.println("Hubo un problema al leer las interseciones, en la linea: " + i + "el mensaje es: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void cargarGrafo ()
	{
		Gson json = new Gson();
		try
		{
			grafo = json.fromJson(new FileReader(GRAFO), grafo.getClass());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public DoublyLinkedList <Integer, Trip> cargarViajes (String tripsFile)
	{
		DoublyLinkedList<Integer, Trip> temp = new DoublyLinkedList<Integer, Trip> ();
		int i = 1;
		try 
		{
			FileReader lector = new FileReader(tripsFile);
			BufferedReader reader = new BufferedReader (lector);
			reader.readLine();
			String l = reader.readLine();
			while (l != null  )
			{
				String [] linea = l.split(",");
				int tripId = Integer.parseInt(linea[0].charAt(0) == '"'? linea[0].substring(1, linea[0].length()-1) : linea[0]);
				String [] ini = (linea [1].charAt(0) == '"'? linea[1].substring(1, linea[1].length()-1) : linea[1]).split(" ");
				String [] fin = (linea [2].charAt(0) == '"'? linea[2].substring(1, linea[2].length()-1) : linea[2]).split(" ");
				LocalDateTime start = convertirFecha_Hora_LDT(ini [0], ini[1]);
				LocalDateTime end = convertirFecha_Hora_LDT(fin[0], fin[1]);
				int bikeId = Integer.parseInt(linea [3].charAt(0) == '"'? linea[3].substring(1, linea[3].length()-1) : linea[3]);
				int duration = Integer.parseInt(linea [4].charAt(0) == '"'? linea[4].substring(1, linea[4].length()-1) : linea[4]);
				int fromStId = Integer.parseInt(linea [5].charAt(0) == '"'? linea[5].substring(1, linea[5].length()-1) : linea[5]);
				String fromSt = linea [6].charAt(0) == '"'? linea[6].substring(1, linea[6].length()-1) : linea[6];
				int toStId = Integer.parseInt(linea [7].charAt(0) == '"'? linea[7].substring(1, linea[7].length()-1) : linea[7]);
				String toSt = linea [8].charAt(0) == '"'? linea[8].substring(1, linea[8].length()-1) : linea[8];
				String tipo = linea [9].equals("\"Dependent\"")? "Subscriber" : linea[9].charAt(0) == '"'? linea [9].substring(1, linea[9].length()-1) : linea[9];
				String genero = null;
				int year = 0;
				if (linea.length > 10 && linea[10].length() > 2)
					genero = linea [10].charAt(0) == '"'? linea[10].substring(1, linea[10].length()-1) : linea[10];
					if (linea.length > 11 && linea[11].length() > 2)
						year = Integer.parseInt(linea [11].charAt(0) == '"'? linea[11].substring(1, linea[11].length()-1) : linea[11]);

					Trip viaje = new Trip(tripId, start, end, bikeId, duration, fromStId, fromSt, toStId, toSt, tipo, genero, year);
					temp.add(viaje);
					l = reader.readLine();
					i++;
			}
			reader.close();
			lector.close();
		}
		catch (Exception e)
		{
			System.out.println("Murio en: " + i + " cargando " + tripsFile);
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public void cargarEstaciones(String ruta)
	{
		int i = 0;
		try 
		{
			FileReader lector = new FileReader(ruta);
			BufferedReader reader = new BufferedReader (lector);
			reader.readLine();
			String l = reader.readLine();
			while (l != null)
			{
				String [] linea = l.split(",");
				int id = Integer.parseInt(linea[0].charAt(0) == '"'? linea[0].substring(1, linea[0].length()-1) : linea[0]);
				String nombre = linea[1].charAt(0) == '"'? linea[1].substring(1, linea[1].length()-1) : linea[1];
				double latitud = Double.parseDouble(linea[3].charAt(0) == '"'? linea[3].substring(1, linea[3].length()-1) : linea[3]);
				double longitud = Double.parseDouble(linea[4].charAt(0) == '"'? linea[4].substring(1, linea[4].length()-1) : linea[4]);
				int capacidad = Integer.parseInt(linea[5].charAt(0) == '"'? linea[5].substring(1, linea[5].length()-1) : linea[5]);
				String [] date = (linea[6].charAt(0) == '"'? linea[6].substring(1, linea[6].length()-1) : linea[6]).split(" ");
				LocalDateTime fecha = convertirFecha_Hora_LDT(date [0], date[1]);

				Estacion<Double> station = new Estacion<Double>(id, nombre, latitud, longitud, capacidad, fecha);
				grafo.addVertex(id, station);
				estaciones.add(id, station);
				l = reader.readLine();
				i++;
			}
			reader.close();
			lector.close();
		}
		catch (Exception e)
		{
			System.out.println("Hubo un problema al leer las estaciones, en la linea: " + i + " \n el mensaje es: " + e.getMessage());
			e.printStackTrace();
		}		
	}

	@Override
	public DibujarFiguras mostrarMapa()
	{
		MapViewOptions options = new MapViewOptions();
		options.importPlaces();
		options.setApiKey("***REMOVED***");
		DibujarFiguras mapa = new DibujarFiguras(options, intersecciones, estaciones);
		return mapa;
	}

	@Override
	public String caminoCostoMinimo(double latI, double longI, double latF, double longF) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String caminoMasCorto(double latI, double longI, double latF, double longF) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String estacionesMasCongestionadas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String rutasEstaciones() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String crearGrafo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String componentesConectados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void visualizarMapa() {
		// TODO Auto-generated method stub
	}

	//-------------------------------------------------------------------------------------
	// EXTENSIÓN
	//-------------------------------------------------------------------------------------

	@Override
	public void generarJSON(String archivo)
	{
		try
		{
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			File file = new File (archivo);
			if (!file.exists())
				file.createNewFile();
			FileWriter writer = new FileWriter(file);
			gson.toJson(grafo,writer);
			writer.close();
		}
		catch (Exception e)
		{
			System.out.println("Hubo un problema al intentar generar el JSON, el problema es: " + e.getMessage());
			//e.printStackTrace();
		}
	}

	private static final int RADIO_TIERRA = 6371;

	public int [] cargar(String rutaTrips, String rutaStations)
	{
		String [] trips = rutaTrips.split(":");
		String [] stations = rutaStations.split(":");
		Scanner linea = new Scanner(System.in);
		int opcion;

		//Q1
		cargarGrafo();
		cargarIntersecciones();
		cargarEstaciones(stations[0]);
		viajes = cargarViajes(trips[0]);
		System.out.println("Se cargaron: " + viajes.size() + " viajes.");
		System.out.println("Se cargó la información de Q1 correctamente");

		//Q2
		System.out.println("¿Quiere cargar la información de Q2? \n Responda 1 para sí");
		opcion = linea.nextInt();
		if (opcion == 1)
		{
			viajes.concat(cargarViajes(trips[1]));
			System.out.println("Se cargaron: " + viajes.size() + " viajes.");
			System.out.println("Se cargó la información de Q2 correctamente");
		}

		//Q3
		System.out.println("¿Quiere cargar la información de Q3? \n Responda 1 para sí");
		opcion = linea.nextInt();
		if (opcion == 1)
		{
			cargarEstaciones(stations[1]);
			viajes.concat(cargarViajes(trips[2]));
			System.out.println("Se cargaron: " + viajes.size() + " viajes.");
			System.out.println("Se cargó la información de Q3 correctamente");
		}

		//Q4
		System.out.println("¿Quiere cargar la información de Q4? \n Responda 1 para sí o 0 para no");
		opcion = linea.nextInt();
		if (opcion == 1)
		{
			viajes.concat(cargarViajes(trips[3]));
			System.out.println("Se cargaron: " + viajes.size() + " viajes.");
			System.out.println("Se cargó la información de Q4 correctamente");
		}

		int [] i = {viajes.size(), estaciones.size(), };
		return i;
	}

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
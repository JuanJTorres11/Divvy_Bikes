package model.logic;

import model.data_structures.KVLinkedList;
import model.data_structures.GrafoDirigido;
import model.data_structures.GrafoNoDirigido;
import model.data_structures.IGrafo;
import model.data_structures.MaxHeapES;
import model.data_structures.KVNode;
import model.data_structures.Kruskal;
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
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Iterator;
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

	//Ruta de la API Key de Maps
	public static final String KEY = "data" + File.separator + "API.txt";

	//-------------------------------------------------------------------------------------
	// ATRIBUTOS
	//-------------------------------------------------------------------------------------

	private double latMin;

	private double latMax;

	private double longMin;

	private double longMax;

	// Estructuras donde se cargan los archivos

	private GrafoNoDirigido <Integer, Vertice, Camino> grafo = new GrafoNoDirigido <Integer, Vertice, Camino>();

	//-------------------------------------------------------------------------------------
	// M�TODOS DE LA GUIA
	//-------------------------------------------------------------------------------------

	public DibujarFiguras mostrarMapa(IKVLista circulos, IKVLista rectangulos, IKVLista <Integer,Camino> lineas)
	{
		MapViewOptions options = new MapViewOptions();
		options.importPlaces();
		options.setApiKey(new BufferedReader(new FileReader(KEY)).readLine());
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
		Estacion estacionInicio=EstacionMasCercana(latInicial, lonInicial);
		Estacion estacionFin=EstacionMasCercana(latFinal, lonFinal);
		KVLinkedList<Integer, Vertice> listcam= dijkstra(estacionInicio, estacionFin);
		Camino camResp= new Camino(estacionInicio.darId(), estacionFin.darId(),0);
		return camResp;

	}

	public Camino A2_menorNumVertices(double latInicial, double lonInicial, double latFinal, double lonFinal)
	{
		//TODO Visualizacion Mapa
		return null;
	}

	public Estacion EstacionMasCercana(double pLatitud, double pLongitud)
	{
		Estacion estacionResp = null;
		double distanciaMinima = 6371;
		Iterator<Vertice> iter = grafo.vertices();
		int i = 0;
		while (iter.hasNext())
		{
			Vertice temp = iter.next();
			if (temp  instanceof Estacion)
			{
				Estacion eTemp = (Estacion)temp;
				if(distancia(pLatitud, pLongitud, eTemp.darLatitud(), eTemp.darLongitud())<distanciaMinima)
				{
					estacionResp=eTemp;
				}
			}

			i++;
		}
		return estacionResp;
	}

	private IKVLista<Integer, Estacion> b = new KVLinkedList <Integer, Estacion> ();
	/**
	 * Determinar las n estaciones de bicicleta m�s congestionadas en Chicago (aquellas que contiene la mayor cantidad de viajes que salen y llegan a esta).
	 */
	public IKVLista<Integer, Estacion> B1_estacionesCongestionadas(int n)
	{
		Iterator<Vertice> iter = grafo.vertices();
		Estacion [] estaciones = new Estacion [585];
		int i = 0;
		while (iter.hasNext())
		{
			Vertice temp = iter.next();
			if (temp  instanceof Estacion)
				estaciones[i] =(Estacion) temp;
			i++;
		}
		Sorts<Estacion> sort = new Sorts<Estacion>();
		sort.quickSort(estaciones, 0, estaciones.length-1);
		KVLinkedList<Integer, Estacion> masCongestionadas = new KVLinkedList<Integer, Estacion> ();
		for (int j = 0; j < n; j++)
			masCongestionadas.add(estaciones[j]);
		mostrarMapa(null,masCongestionadas, null);
		b = masCongestionadas;
		return masCongestionadas;
	}

	public IKVLista<Integer, Camino> B2_rutasMinimas(IKVLista<Integer, Estacion> stations)
	{
		GrafoNoDirigido<Integer, Estacion, Camino> g = new GrafoNoDirigido<Integer, Estacion, Camino>();
		Iterator<Estacion> iter1 = b.iterator();
		while (iter1.hasNext())
		{
			Estacion temp = iter1.next();
			g.addVertex(temp.darId(),temp);
			Iterator iter2 = grafo.adyacentes(temp.darId());
			while (iter2.hasNext());
			{
				Camino c = (Camino) iter2.next();
				g.addEdge(c.darInicio(), c.darFin(), c);
			}
		}
		Kruskal k = new Kruskal(g);
		KVLinkedList<Integer, Camino> resp = new KVLinkedList<Integer, Camino> ();
		Iterator<Camino> iter3 = k.edges().iterator();
		while (iter3.hasNext())
			resp.add(iter3.next());
		mostrarMapa(null, b, resp);
		return resp;
	}

	public IGrafo C1_grafoEstaciones()
	{
		Iterator<Vertice> iter = grafo.vertices();
		GrafoDirigido<Integer, Estacion, Camino> diG = new GrafoDirigido <Integer, Estacion, Camino>();
		while (iter.hasNext())
		{
			Vertice temp = iter.next();
			if (temp  instanceof Estacion)
				diG.addVertex(temp.darId(),(Estacion)temp);
		}
		loadTrips(TRIPS_Q1, diG);
		loadTrips(TRIPS_Q2, diG);
		loadTrips(TRIPS_Q3, diG);
		loadTrips(TRIPS_Q4, diG);

		KVLinkedList<Integer, Estacion> estaciones = new KVLinkedList<Integer, Estacion> ();
		KVLinkedList<Integer, Camino> caminos = (KVLinkedList<Integer, Camino>) diG.arcos();
		Iterator<Estacion> iter2 = diG.vertices();
		while (iter.hasNext())
		{
			estaciones.add((Estacion) iter.next());
		}
		mostrarMapa(null, estaciones, caminos);
		return diG;
	}

	public void C1_persistirGrafoEstaciones(IGrafo grafoEstaciones)
	{

	}

	public IKVLista<Integer, ComponenteFuertementeConectada> C2_componentesFuertementeConectados(IGrafo grafo)
	{
		//TODO Visualizacion Mapa
		return null;
	}

	public void C3_pintarGrafoEstaciones(IGrafo grafoEstaciones)
	{
		//TODO Visualizacion Mapa!

	}

	//-------------------------------------------------------------------------------------
	// EXTENSI�N
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
	
	public  KVLinkedList<Integer,Vertice> dijkstra(Vertice v_origen, Vertice v_destino)
	{

		KVLinkedList<Integer,Vertice> listtemp = new KVLinkedList<>();
		return cortDijkstra(v_destino, v_origen);
	}

	public KVLinkedList<Integer,Vertice> cortDijkstra(Vertice v_origen, Vertice v_destino) 
	{
		int[] posVertPadre = new int[grafo.V()];
		for (int i = 0; i < posVertPadre.length; i++) 
		{
			posVertPadre[i] = -1;
		}

		Iterator iteradorG = grafo.vertices(); 
		Object[][] matrizAdyacentes = new Object[grafo.V()][grafo.V()];
		for (int i = 0; i < grafo.listaAdyacencia.size(); i++) 
		{
			for (int j = 0; j < grafo.listaAdyacencia.size(); j++) 
			{
				matrizAdyacentes[i][j] = iteradorG.next();
			}
		}


		KVLinkedList<Integer,Vertice> listVertVisitados = new  KVLinkedList<Integer,Vertice>();
		return listVertVisitados;

	}

	public void loadTrips (String tripsFile, GrafoDirigido diG)
	{
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
				int duration = Integer.parseInt(linea [4].charAt(0) == '"'? linea[4].substring(1, linea[4].length()-1) : linea[4]);
				int fromStId = Integer.parseInt(linea [5].charAt(0) == '"'? linea[5].substring(1, linea[5].length()-1) : linea[5]);
				int toStId = Integer.parseInt(linea [7].charAt(0) == '"'? linea[7].substring(1, linea[7].length()-1) : linea[7]);
				Camino cam = null;
				cam = diG.getInfoArc(fromStId, toStId);
				if(cam == null)
				{
					cam = new Camino (fromStId, toStId, duration);
					diG.addEdge(fromStId, toStId,cam);
				}
				else
					cam.agregarDuracion(duration);

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
	}

	/**
	 * Descarga un archivo desde un servidor web sin guardarlo en buffer de memoria.
	 * @param url URL del archivo a descargar.
	 * @param archivo dirección a donde se guardará el archivo.
	 */
	public void descargarArchivo (String url, String archivo)
	{
		ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(url).openStream());
		FileOutputStream fileOutputStream = new FileOutputStream(archivo);
		fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
	}

}
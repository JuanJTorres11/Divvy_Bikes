package model.logic;

import model.data_structures.DoublyLinkedList;
import model.data_structures.Grafo;
import model.vo.Estacion;
import model.vo.Interseccion;
import model.vo.Sector;
import model.vo.Vertice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Iterator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teamdev.jxmaps.MapViewOptions;

import api.IManager;

public class Manager <K extends Comparable <K> ,V> implements IManager
{
	//-------------------------------------------------------------------------------------
	// CONSTANTES
	//-------------------------------------------------------------------------------------

	//Ruta del archivo de stations 2017-Q3-Q4
	public static final String STATIONS_Q3_Q4 = "data" + File.separator + "Divvy_Stations_2017_Q3Q4.csv";

	//Ruta del archivo de Intersecciones en la ciudad de Chicago
	public static final String INTERSECCIONES = "data" + File.separator + "Nodes_of_Chicago_Street_Lines.txt";

	//Ruta del archivo de Intersecciones en la ciudad de Chicago
	public static final String ARCOS = "data" + File.separator + "Adjacency_list_of_Chicago_Street_Lines.txt";

	private final static int totalStationsQ3Q4 = 585;

	//-------------------------------------------------------------------------------------
	// ATRIBUTOS
	//-------------------------------------------------------------------------------------

	private double latMin;

	private double latMax;

	private double longMin;

	private double longMax;

	// Estructuras donde se cargan los archivos

	Grafo<Integer, Vertice<Double>, Double> grafo = new Grafo<Integer, Vertice<Double>, Double>();

	private Sector[] sectores;

	private DoublyLinkedList <Integer, Interseccion<Double>> intersecciones = new DoublyLinkedList <Integer, Interseccion<Double>> ();

	private DoublyLinkedList <Integer, Estacion<Double>> estaciones = new DoublyLinkedList <Integer, Estacion<Double>> ();
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
				grafo.addVertex(id, inter);
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

		// Carga de arcos
		int j = 5;
		try
		{
			FileReader lector = new FileReader(ARCOS);
			BufferedReader reader = new BufferedReader (lector);
			for (int k = 0; k < 5; k++)
				reader.readLine();
			String l = reader.readLine();
			while (l != null)
			{
				String [] linea = l.split(" ");
				for (int w = 1; w < linea.length; w++)
					grafo.addEdge(Integer.parseInt(linea[0]), Integer.parseInt(linea[w]), distanciaVertices(Integer.parseInt(linea[0]), Integer.parseInt(linea[w])));
				j ++;
				l = reader.readLine();
			}
			reader.close();
			lector.close();
		}
		catch (Exception e)
		{
			System.out.println("Hubo un problema al leer las conexiones entre interseciones, en la linea: " + j + " el mensaje es: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void cargarEstaciones()
	{
		int i = 0;
		try 
		{
			FileReader lector = new FileReader(STATIONS_Q3_Q4);
			BufferedReader reader = new BufferedReader (lector);
			reader.readLine();
			String l = reader.readLine();
			sectorizar();
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

				// Agregar Arco
				DoublyLinkedList<Integer, Interseccion> lista = sectores[darSector(latitud, longitud)-1].darIntersecciones();
				if (lista != null)
				{
					Interseccion<Double> cercano = lista.getFirst().darValor();
					Double distancia = distancia(cercano.darLatitud(), cercano.darLongitud(), latitud, longitud);
					for (Interseccion<Double> temp: lista)
					{
						if (distancia(temp.darLatitud(),temp.darLongitud(), latitud, longitud) < distancia)
						{
							cercano = temp;
							distancia = distancia(temp.darLatitud(),temp.darLongitud(), latitud, longitud);
						}
					}
					grafo.addEdge(id, cercano.darId(), distancia);
				}
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

	@Override
	public DibujarFiguras mostrarMapa()
	{
		MapViewOptions options = new MapViewOptions();
		options.importPlaces();
		options.setApiKey("***REMOVED***");
		DibujarFiguras mapa = new DibujarFiguras(options, intersecciones, estaciones);
		return mapa;
	}

	//-------------------------------------------------------------------------------------
	// EXTENSIÓN
	//-------------------------------------------------------------------------------------

	private static final int RADIO_TIERRA = 6371;

	private double distanciaVertices (int vertice1, int vertice2)
	{
		return distancia(grafo.getInfoVertex(vertice1).darLatitud(), grafo.getInfoVertex(vertice1).darLongitud(), grafo.getInfoVertex(vertice2).darLatitud(),grafo.getInfoVertex(vertice2).darLongitud());
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

	public void sectorizar()
	{
		sectores = new Sector[100];
		double difx = (longMax - longMin)/10;
		double dify = (latMax - latMin)/10;

		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				sectores[i*10 + j] = new Sector(0,0,0,0,0);
				Sector tmp = sectores[i*10 + j];
				tmp.cambiarId(i*10 + j+1); // El sector x está en la posición x-1 en el arreglo.
				tmp.cambiarLatMax(latMax-dify*i);
				tmp.cambiarLatMin(tmp.darLatMax()-dify);
				tmp.cambiarLongMin(longMin+difx*j);
				tmp.cambiarLongMax(tmp.darLongMin()+difx);
			}
		}

		Iterator <Interseccion<Double>> iter = intersecciones.iterator();
		while (iter.hasNext())
		{
			Interseccion<Double> temp = iter.next();
			try
			{
				sectores[darSector(temp.darLatitud(), temp.darLongitud()) - 1].agregarInterseccion(temp);
			}
			catch (Exception e)
			{
				System.out.println("El vertice " + temp.darId() + " quedó fuera de la sectorización");
			}
		}
		Iterator <Estacion<Double>> iter2 = estaciones.iterator();
		while (iter.hasNext())
		{
			Estacion<Double> temp = iter2.next();
			try
			{
				sectores[darSector(temp.darLatitud(), temp.darLongitud()) - 1].agregarEstacion(temp);
			}
			catch (Exception e)
			{
				System.out.println("El vertice " + temp.darId() + " quedó fuera de la sectorización");
			}
		}
	}

	public int darSector(double latitud, double longitud) throws Exception
	{
		double difx = (longMax - longMin)/ 10;
		double dify = (latMax - latMin)/ 10;
		int x = (int) ( (longitud - longMin) / difx );
		int y = (int) ( (latMax - latitud) / dify );

		if (y < 10 && x < 10 && y >= 0 && x >= 0)
			return y * 10 + x + 1;
		else if (y == 10 && latitud == latMin)
		{
			if (x == 10 && longitud == longMax)
				return (y-1)*10 + x;
			else if (x < 10)
				return (y-1)*10 + x + 1;
		}
		else if (x == 10 && longitud == longMax)
			return y*10 + x;
		else
			throw new Exception ("Esas coordenadas están por fuera de las permitidas");
		return 0;
	}
}
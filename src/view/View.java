package view;

import java.util.Scanner;
import controller.Controller;
import model.logic.Manager;

public class View 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		Controller controlador = new Controller();
		boolean fin=false;
		while(!fin)
		{
			printMenu();

			int option = sc.nextInt();

			switch(option)
			{
			case 0:  //Carga de datos
				String dataTrips = "";  // ruta del archivo de Trips
				String dataStations = ""; // ruta del archivo de Stations
				dataTrips = Manager.TRIPS_Q1 + ":" + Manager.TRIPS_Q2 +":" + Manager.TRIPS_Q3 + ":" + Manager.TRIPS_Q4;
				dataStations = Manager.STATIONS_Q1_Q2 + ":" + Manager.STATIONS_Q3_Q4;

				//Memoria y tiempo
				long memoryBeforeCase1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				long startTime = System.currentTimeMillis();

				Controller.cargarDatos(dataTrips, dataStations);

				//Tiempo en cargar
				long endTime = System.currentTimeMillis();
				long duration = endTime - startTime;

				//Memoria usada
				long memoryAfterCase1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				System.out.println("Tiempo en cargar: " + duration + " milisegundos \nMemoria utilizada:  "+ ((memoryAfterCase1 - memoryBeforeCase1)/1000000.0) + " MB");
				break;
			case 1:
				Double latI = sc.nextDouble();
				Double latF = sc.nextDouble();
				Double longI = sc.nextDouble();
				Double longF = sc.nextDouble();
				controlador.caminoCostoMinimo(latI, longI, latF, longF);
				break;
			case 2:
				Double latiI = sc.nextDouble();
				Double latiF = sc.nextDouble();
				Double longiI = sc.nextDouble();
				Double longiF = sc.nextDouble();
				controlador.caminoMasCorto(latiI, longiI, latiF, longiF);
				break;
			case 3:
				controlador.estacionesMasCongestionadas();
				break;
			case 4:
				controlador.rutasEstaciones();
				break;
			case 5:
				controlador.crearGrafo();
				break;
			case 6:
				controlador.componentesConectados();
				break;
			case 7:
				controlador.visualizarMapa();
				break;
			case 8:
				fin=true;
				sc.close();
				break;
			}
		}
		System.exit(0);
	}

	private static void printMenu()
	{
		System.out.println("---------ISIS 1206 - Estructuras de datos----------");
		System.out.println("---------------------Proyecto 3----------------------");
		System.out.println("0. Cargar datos de todos los archivos");
		System.out.println("1.  Crear JSON");
		System.out.println("2.  Encontrar el camino de costo mínimo (menor distancia) para un viaje en bicicleta");
		System.out.println("3.  Encontrar el camino más corto (menor número de vértices) para un viaje en bicicleta");
		System.out.println("4.  Determinar las n estaciones de bicicleta más congestionadas en Chicago");
		System.out.println("5.  Calcular las rutas mínimas (con criterio distancia harvesiana) que conecten las n\r\n" + 
				"	estaciones encontradas en el punto anterior");
		System.out.println("6. Cree un Grafo Dirigido tomando como vértices únicamente los nodos estación y como\r\n" + 
				"	arcos los viajes de bicicletas entre las mismas.");
		System.out.println("7. Calcule los componentes fuertemente conexos presentes en el grafo construido en el\r\n" + 
				"	punto anterior.");
		System.out.println("1.  A partir del grafo construido en el punto 5 pinte sobre el mapa de\r\n" + 
				"	la red vial de Chicago utilizando Google Maps");
		System.out.println("8.  Cerrar");
		System.out.println("Digite el número de opción para ejecutar la tarea, luego presione enter: (Ej., 1):");
	}
}
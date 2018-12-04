package view;

import java.util.Scanner;
import controller.Controller;
import model.data_structures.GrafoNoDirigido;
import model.data_structures.IGrafo;
import model.data_structures.ILista;
import model.vo.Estacion;
import model.vo.ComponenteFuertementeConectada;
import model.vo.Camino;

/**
 * view del programa
 */
public class View 
{
	public static void main(String[] args) 
	{
		ILista<?,Estacion> lista1B = null;
		IGrafo grafo1C = new GrafoNoDirigido();
		Scanner sc = new Scanner(System.in);
		boolean fin=false;
		while(!fin)
		{
			//imprime menu
			printMenu();

			//opcion req
			int option = sc.nextInt();

			switch(option)
			{
			case 1: 

				//Memoria y tiempo
				long memoryBeforeCase1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				long startTime = System.nanoTime();

				//Cargar data
				Controller.cargarSistema();

				//Tiempo en cargar
				long endTime = System.nanoTime();
				long duration = (endTime - startTime)/(1000000);

				//Memoria usada
				long memoryAfterCase1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				System.out.println("Tiempo en cargar: " + duration + " milisegundos \nMemoria utilizada:  "+ ((memoryAfterCase1 - memoryBeforeCase1)/1000000.0) + " MB");

				break;

			case 2: //1A

				System.out.println("---Informacion del vertice de inicio---");

				System.out.println("Digite su latitud : (Ej. 48.62)");
				String latitudInicial = sc.next();
				//conversion de string a double
				Double latInic = Double.parseDouble(latitudInicial);

				System.out.println("Digite su longitud :  (Ej. -47.86)");
				String longitudInicial = sc.next();
				Double logInic = Double.parseDouble(longitudInicial);

				System.out.println("---Informacion del vertice de fin---");

				System.out.println("Digite su latitud : (Ej. 48.62)");
				String latitudFinal = sc.next();
				Double latFin = Double.parseDouble(latitudFinal);

				System.out.println("Digite su longitud :  (Ej. -47.86)");
				String longitudFinal = sc.next();
				Double logFin = Double.parseDouble(longitudFinal);

				Camino camino1A = Controller.A1_menorDistancia(latInic,logInic,latFin,logFin);

				System.out.println();
				System.out.println("Vertices del camino :   ");
				System.out.println("Vertice inicial : " + latInic + " - " + logInic);
				System.out.println("Vertice final : "+ latFin + " - " + logFin);

				System.out.println("Distancia estimada del camino :  ");
				System.out.println("Estacion mas cercana a origen :  ");
				System.out.println("Estaciones mas cercana a destino :  ");

				// TODO Mostrar el mapa Google Maps (segun enunciado)

				break;

			case 3: //2A

				System.out.println("---Informacion del vertice de inicio---");

				System.out.println("Digite su latitud : (Ej. 48.62)");
				String latitudInicial2A = sc.next();
				Double latInic2A = Double.parseDouble(latitudInicial2A);

				System.out.println("Digite su longitud :  (Ej. -47.86)");
				String longitudInicial2A = sc.next();
				Double logInic2A = Double.parseDouble(longitudInicial2A);

				System.out.println("---Informacion del vertice de fin---");

				System.out.println("Digite su latitud : (Ej. 48.62)");
				String latitudFinal2A = sc.next();
				Double latFin2A = Double.parseDouble(latitudFinal2A);

				System.out.println("Digite su longitud :  (Ej. -47.86)");
				String longitudFinal2A = sc.next();
				Double logFin2A = Double.parseDouble(longitudFinal2A);

				Camino camino2A = Controller.A2_menorNumVertices(latInic2A, logInic2A, latFin2A, logFin2A);

				System.out.println();
				System.out.println("Vertices del camino :   ");
				System.out.println("Vertice inicial : " + latInic2A + " - " + logInic2A);
				System.out.println("Vertice final : "+ latFin2A + " - " + logFin2A);

				System.out.println("Distancia estimada del camino :  ");
				System.out.println("Estacion mas cercana a origen :  ");
				System.out.println("Estaciones mas cercana a destino :  ");

				// TODO Mostrar el mapa Google Maps (segun enunciado)

				break;

			case 4: //1B

				System.out.println("Ingrese el número de estaciones");
				String n1 = sc.next();
				int n = 0;
				try
				{
					n = Integer.parseInt(n1);
				}
				catch (Exception e) 
				{
					System.err.println("Número de estaciones inválido");
					break;
				}

				lista1B = Controller.B1_estacionesCongestionadas(n);

				System.out.println("Informacion de las estaciones ");
				for (int i = 0; lista1B != null && i < lista1B.size(); i++) 
				{
					System.out.println("Nombre : " );
					System.out.println("Latitud :" +"AQUI ANADA LA LATITUD" +"Longitud : " + "AQUI ANADA LA LONGITUD");
					System.out.println("Total viajes que llegaron : ");
					System.out.println("Total viajes que salieron : ");
				}

				// TODO Mostrar el mapa Google Maps (segun enunciado)

				break;

			case 5: //2B

				ILista<?,Estacion> estaciones = lista1B;
				ILista<?,Camino> lista2B = Controller.B2_rutasMinimas(estaciones);

				System.out.println("Informacion rutas minimas ");
				for (int i = 0; lista2B != null && i < lista2B.size(); i++) 
				{
					//depende de la estructura manejada para almacenar 
					// los vertices los imprimen aqui
					System.out.println("Identificador vertice con id " + "AQUI VA EL ID ACTUAL");


					System.out.println("Distancia total "+ "AQUI VA LA DISTANCIA");
				}

				// TODO Mostrar el mapa Google Maps (segun enunciado)

				break;

			case 6: //1C

				grafo1C = Controller.C1_grafoEstaciones();

				// TODO Mostrar la informacion del grafo (segun enunciado)
				System.out.println("Numero de vertices : ");
				System.out.println("Numero de arcos : ");

				// TODO Mostrar el mapa Google Maps (segun enunciado)

				break;

			case 7: //2C

				ILista <?, ComponenteFuertementeConectada> compFuertesConectadas = Controller.C2_componentesFuertementeConectados(grafo1C);

				// TODO Mostrar la informacion de cada componente fuertemente conectada (segun enunciado)
				System.out.println("Total componentes conectadas :");

				// TODO Mostrar el mapa Google Maps (segun enunciado)

				break;

			case 8: //3C

				IGrafo grafoEstaciones = grafo1C;
				Controller.C3_pintarGrafoEstaciones(grafoEstaciones);

				// TODO Mostrar el mapa Google Maps (segun enunciado)

				break;

			case 9: 
				fin=true;
				sc.close();
				break;

			}
		}
	}
	/**
	 * Menu 
	 */
	private static void printMenu()
	{
		System.out.println("---------ISIS 1206 - Estructuras de datos----------");
		System.out.println("---------------------Proyecto 3----------------------");
		System.out.println("Iniciar la Fuente de Datos a Consultar :");
		System.out.println("1. Cargar el grafo no dirigido construido en el taller 8 .");

		System.out.println("\nParte A:\n");
		System.out.println("2. Encontrar el camino de costo mínimo (menor distancia) para un viaje en bicicleta (1A)");
		System.out.println("3. Encontrar el camino más corto (menor número de vértices) para un viaje en bicicleta (2A)");

		System.out.println("\nParte B:\n");
		System.out.println("4. Determinar las n estaciones de bicicleta más congestionadas en Chicago (1B)");
		System.out.println("5. Calcular las rutas mínimas (con criterio distancia harvesiana) que conecten las n estaciones encontradas en el punto anterior (2B)");

		System.out.println("\nParte C:\n");
		System.out.println("6. Crear un Grafo Dirigido tomando como vértices las estaciones y como arcos los viajes de bicicletas (1C)");
		System.out.println("7. Calcular lo componentes fuertemente conexos del grafo construido en el punto anterior (2C)");
		System.out.println("8. Pinte el grafo construido anteriormente (3C)");
		System.out.println("9. Salir");
		System.out.println("Ingrese el numero de la opcion seleccionada y presione <Enter> para confirmar: (e.g., 1):");
	}
}
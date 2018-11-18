package view;

import java.awt.BorderLayout;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import controller.Controller;
import model.logic.DibujarFiguras;

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
			case 1:
				//Memoria y tiempo
				long memoryBeforeCase1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				long startTime = System.currentTimeMillis();

				//Cargar
				controlador.cargarIntersecciones();

				//Tiempo en cargar
				long endTime = System.currentTimeMillis();
				long duration = endTime - startTime;

				//Memoria usada
				long memoryAfterCase1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				System.out.println("Tiempo en cargar: " + duration + " milisegundos \nMemoria utilizada:  "+ ((memoryAfterCase1 - memoryBeforeCase1)/1000000.0) + " MB");
				break;
			case 2:
				//Memoria y tiempo
				long memoryBeforeCase2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				long startTime2 = System.currentTimeMillis();

				//Cargar
				controlador.cargarEstaciones();

				//Tiempo en cargar
				long endTime2 = System.currentTimeMillis();
				long duration2 = endTime2 - startTime2;

				//Memoria usada
				long memoryAfterCase2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				System.out.println("Tiempo en cargar: " + duration2 + " milisegundos \nMemoria utilizada:  "+ ((memoryAfterCase2 - memoryBeforeCase2)/1000000.0) + " MB");
				break;
			case 3:
				String ruta;
				System.out.println("Ingrese la ruta en que quiere guardar el JSON del grafo");
				ruta = sc.next();
				controlador.generarJSON(ruta);
				System.out.println("Se ha generado exitosamente el JSON con la información del grafo");
				break;
			case 4:
				DibujarFiguras mapa = controlador.mostrarMapa();
				JFrame frame = new JFrame("Mapa");
				frame.add(mapa, BorderLayout.CENTER);
				frame.setSize(700, 500);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				break;
			case 5:
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
		System.out.println("1.  Cargar las intersecciones");
		System.out.println("2.  Cargar las estaciones");
		System.out.println("3.  Crear JSON");
		System.out.println("4.  Mostrar Mapa");
		System.out.println("5.  Salir");
		System.out.println("Digite el número de opción para ejecutar la tarea, luego presione enter: (Ej., 1):");
	}
}
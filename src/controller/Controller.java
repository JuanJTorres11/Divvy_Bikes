package controller;

import java.io.File;

import api.IManager;
import model.logic.DibujarFiguras;
import model.logic.Manager;

public class Controller
{
	private static IManager manager = new Manager();

	public void cargarEstaciones()
	{
		manager.cargarEstaciones();
	}

	public void cargarIntersecciones()
	{
		manager.cargarIntersecciones();
	}

	public void generarJSON(String archivo)
	{
		manager.generarJSON(archivo);	
	}

	public DibujarFiguras mostrarMapa()
	{
		return manager.mostrarMapa();
	}
}
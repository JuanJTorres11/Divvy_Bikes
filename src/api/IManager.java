package api;

import java.io.File;

import model.logic.DibujarFiguras;

public interface IManager
{
	public void cargarIntersecciones();

	public void cargarEstaciones();

	public void generarJSON(String archivo);

	public DibujarFiguras mostrarMapa();
}
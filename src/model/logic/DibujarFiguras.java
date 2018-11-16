/*
 * Copyright (c) 2000-2017 TeamDev Ltd. All rights reserved.
 * Use is subject to Apache 2.0 license terms.
 */
package model.logic;

import com.teamdev.jxmaps.ControlPosition;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.LatLngBounds;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.MapViewOptions;
import com.teamdev.jxmaps.Rectangle;
import com.teamdev.jxmaps.RectangleOptions;
import com.teamdev.jxmaps.Circle;
import com.teamdev.jxmaps.CircleOptions;
import com.teamdev.jxmaps.Polygon;
import com.teamdev.jxmaps.PolygonOptions;
import com.teamdev.jxmaps.swing.MapView;

import model.data_structures.DoublyLinkedList;
import model.data_structures.ILista;
import model.vo.Estacion;
import model.vo.Interseccion;

import java.awt.*;

/**
 * This example demonstrates how to draw polygons on the map.
 *
 * @author Vitaly Eremenko
 */
public class DibujarFiguras extends MapView
{
	// Atributos

	private static final long serialVersionUID = 1L;

	Map map;

	ILista <Integer, Interseccion> intersecciones;

	ILista <Integer, Estacion> estaciones;

	ILista circulos = new DoublyLinkedList<>();
	
	ILista rectangulos = new DoublyLinkedList<>();
	
	LatLngBounds bordes = new LatLngBounds (new LatLng (41.657972891282526, -87.81235055856227), new LatLng (42.063999, -87.52849012951941));

	// Constructor
	public DibujarFiguras(MapViewOptions options, ILista <Integer, Interseccion> inter, ILista <Integer, Estacion> est)
	{
		super(options);
		// Setting of a ready handler to MapView object. onMapReady will be called when map initialization is done and
		// the map object is ready to use. Current implementation of onMapReady customizes the map object.
		setOnMapReadyHandler(new MapReadyHandler()
		{
			@Override
			public void onMapReady(MapStatus status)
			{
				// Check if the map is loaded correctly
				if (status == MapStatus.MAP_STATUS_OK)
				{
					map = getMap();
					intersecciones = inter;
					estaciones = est;
					rectangulo();
					circulo();
					// Creating a map options object
					MapOptions mapOptions = new MapOptions();
					// Creating a map type control options object
					MapTypeControlOptions controlOptions = new MapTypeControlOptions();
					// Changing position of the map type control
					controlOptions.setPosition(ControlPosition.TOP_RIGHT);
					// Setting map type control options
					mapOptions.setMapTypeControlOptions(controlOptions);
					// Setting map options
					map.setOptions(mapOptions);
					// Setting the map center
					map.setCenter(new LatLng(41.875486, -87.626570));
					// Setting initial zoom value
					map.setZoom(9.0);
				}
			}
		});
	}

	// Métodos

	public void circulo ()
	{
		CircleOptions opciones = new CircleOptions();
		opciones.setFillOpacity(0);
		opciones.setStrokeColor(Color.RED.toString());
		opciones.setStrokeWeight(5.0);

		for (Interseccion inter: intersecciones)
		{
			Circle circulo = new Circle(map);
			circulo.setCenter(new LatLng(inter.darLatitud(), inter.darLongitud()));
			circulo.setRadius(50);
			circulo.setOptions(opciones);
		}
	}
	public void rectangulo()
	{
		RectangleOptions opciones = new RectangleOptions();
		opciones.setFillOpacity(0);
		opciones.setStrokeColor(Color.BLUE.toString());
		int i = 0;
		for (Estacion rect: estaciones)
		{
			Rectangle rectangulo = new Rectangle (map);
			LatLngBounds bordes = new LatLngBounds (new LatLng (rect.darLatitud() - 0.0004, rect.darLongitud() - 0.0006), new LatLng (rect.darLatitud() + 0.0004, rect.darLongitud() + 0.0006));
			rectangulo.setBounds(bordes);
			rectangulo.setOptions(opciones);
		}
	}
}
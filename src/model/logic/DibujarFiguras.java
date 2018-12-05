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
import com.teamdev.jxmaps.swing.MapView;
import model.data_structures.ILista;
import model.vo.Camino;
import model.vo.Vertice;

public class DibujarFiguras extends MapView
{
	// Atributos

	private static final long serialVersionUID = 1L;

	Map map;

	ILista <?, Vertice<?>> circulos;

	ILista <?, Vertice<?>> rectangulos;

	ILista <?, Camino> lineas; 

	LatLngBounds bordes = new LatLngBounds (new LatLng (41.657972891282526, -87.81235055856227), new LatLng (42.063999, -87.52849012951941));

	// Constructor
	public DibujarFiguras(MapViewOptions options, ILista <?, Vertice<?>> circ, ILista <?, Vertice<?>> rect, ILista <?, Camino> linea)
	{
		super(options);

		setOnMapReadyHandler(new MapReadyHandler()
		{
			@Override
			public void onMapReady(MapStatus status)
			{
				//  Verifica si el mapa cargó bien
				if (status == MapStatus.MAP_STATUS_OK)
				{
					// crea las instancias necesarias
					map = getMap();
					circulos = circ;
					rectangulos = rect;
					lineas = linea;

					// Crea las opciones para el mapa
					MapOptions mapOptions = new MapOptions();
					// Crea controles para el mapa
					MapTypeControlOptions controlOptions = new MapTypeControlOptions();
					controlOptions.setPosition(ControlPosition.TOP_RIGHT);
					mapOptions.setMapTypeControlOptions(controlOptions);
					// Se configuran las opciones
					map.setOptions(mapOptions);
					// Coordenadas del centro de Chicago
					map.setCenter(new LatLng(41.875486, -87.626570));
					// Zoom adecuado para ver Chicago
					map.setZoom(10.0);
					// Se instancian las figuras a mostrar
					rectangulo();
					circulo();
					linea();
				}
			}
		});
	}

	// Métodos

	public void circulo ()
	{
		CircleOptions opciones = new CircleOptions();
		opciones.setFillOpacity(0);
		opciones.setStrokeColor("#CB4335"); //rojo
		opciones.setStrokeWeight(5.0);

		for (Vertice<?> inter: circulos)
		{
			Circle circulo = new Circle(map);
			circulo.setCenter(new LatLng(inter.darLatitud(),inter.darLongitud()));
			circulo.setRadius(40);
			circulo.setOptions(opciones);
		}
	}

	public void rectangulo()
	{
		RectangleOptions opciones = new RectangleOptions();
		opciones.setFillOpacity(0);
		opciones.setStrokeColor("#2E86C1  "); // azul
		for (Vertice<?> rect: rectangulos)
		{
			Rectangle rectangulo = new Rectangle (map);
			LatLngBounds bordes = new LatLngBounds (new LatLng (rect.darLatitud() - 0.0004, rect.darLongitud() - 0.0006), new LatLng (rect.darLatitud() + 0.0004, rect.darLongitud() + 0.0006));
			rectangulo.setBounds(bordes);
			rectangulo.setOptions(opciones);
		}
	}

	public void linea()
	{

	}
}
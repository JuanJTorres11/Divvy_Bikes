package model.vo;

public interface Vertice <A>
{	
	public int darId();

	public double darLatitud();

	public double darLongitud();
	
	public A darInformaci�nArco();
	
	public void cambiarInformacionArco(A info);
}
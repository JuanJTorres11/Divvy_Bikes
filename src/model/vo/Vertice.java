package model.vo;

public interface Vertice <A>
{	
	public int darId();

	public double darLatitud();

	public double darLongitud();
	
	public A darInformaciónArco();
	
	public void cambiarInformacionArco(A info);
}
package taller1_hamburguesasCorral.modelo;

public class Ingrediente 
{
	///Attributes
	private String nombre;
	private int costoAdicional;
	private int calorias;
	
	///Constructor
		
	public Ingrediente(String iNombre, int iCosto, int icalorias) 
	{
		nombre = iNombre;
		costoAdicional = iCosto;
		calorias = icalorias;
	}

	public String getNombre()
	{
		return nombre;
	}
	
	public int getCostoAdicional()
	{
		return costoAdicional;
	}
	
	public int getCalorias()
	{
		return calorias;
	}
	
}

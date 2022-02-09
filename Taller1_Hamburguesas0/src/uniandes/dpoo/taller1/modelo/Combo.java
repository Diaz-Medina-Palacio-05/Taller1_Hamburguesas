package uniandes.dpoo.taller1.modelo;

public class Combo {
	
	// ************************************************************************
	// Atributos
	// ************************************************************************
	
	private double descuento;
	private String nombreCombo;
	
	// ************************************************************************
	// Constructores
	// ************************************************************************
	
	public Combo(String Nombre, double descuento)
	{
		this.descuento = descuento;
		this.nombreCombo = Nombre;
		
	}
	
	// ************************************************************************
	// Metodos para consultar los atributos
	// ************************************************************************
	
	public void agregarItemACombo(IProducto itemCombo)
	{
		
	}
	
	public int getPrecio()
	{
		return 0;
		
	}
	
	public String generarTextoFactura()
	{
		return null;
		
	}
	
	public String getNombre()
	{
		return null;
		
	}
	

}

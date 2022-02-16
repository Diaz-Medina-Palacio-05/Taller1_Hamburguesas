package taller1_hamburguesasCorral.modelo;

public class ProductoMenu implements Producto
{
	private String nombre;
	private int precioBase;
	private int calorias;
	
	public ProductoMenu(String pNombre, int pPrecio, int icalorias) 
	{
		nombre = pNombre;
		precioBase = pPrecio;
		calorias = icalorias;
	}
	@Override
	public int getPrecio() 
	{
		return precioBase;
	}

	@Override
	public String getNombre() 
	{
		return nombre;
	}

	@Override
	public String generarTextoFactura() 
	{
		return nombre + ": " + precioBase;
	}

	public int getCalorias()
	{
		return calorias;
	}
}

package taller1_hamburguesasCorral.modelo;

public class ProductoMenu implements Producto
{
	private String nombre;
	private int precioBase;
	
	public ProductoMenu(String pNombre, int pPrecio) 
	{
		nombre = pNombre;
		precioBase = pPrecio;
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

}

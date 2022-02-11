package taller1_hamburguesasCorral.modelo;

import java.util.ArrayList;

public class ProductoAjustado implements Producto
{
	
	private ProductoMenu base;
	private ArrayList<Ingrediente> agregados;
	private ArrayList<Ingrediente> eliminados;
	
	public ProductoAjustado(ProductoMenu item)
	{
		base = item;
		agregados = new ArrayList<Ingrediente>();
		eliminados = new ArrayList<Ingrediente>();
	}

	@Override
	public int getPrecio() 
	{
		return base.getPrecio();
	}

	@Override
	public String getNombre() 
	{
		return base.getNombre();
	}

	@Override
	public String generarTextoFactura() 
	{
		return base.generarTextoFactura();
	}

}

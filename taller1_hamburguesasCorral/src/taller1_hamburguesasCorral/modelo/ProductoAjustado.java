package taller1_hamburguesasCorral.modelo;

import java.util.ArrayList;

public class ProductoAjustado implements Producto
{
	///Attributes
	private Producto base;
	private ArrayList<Ingrediente> agregados;
	private ArrayList<Ingrediente> eliminados;
	
	//Constructor
	public ProductoAjustado(Producto item)
	{
		base = item;
		agregados = new ArrayList<Ingrediente>();
		eliminados = new ArrayList<Ingrediente>();
	}

	///Methods
	
	@Override
	public int getPrecio() 
	{
		int precioBase = base.getPrecio();
		for (Ingrediente i: agregados)
		{
			precioBase += i.getCostoAdicional();
		}
		return precioBase;
	}

	@Override
	public String getNombre() 
	{ 
		String nombreEditado = base.getNombre();
		if (agregados.isEmpty() == false)
		{
			nombreEditado += " con adicion de ";
			for (Ingrediente ingred: agregados) 
			{
				nombreEditado += ingred.getNombre() + " ";
			} 
		}
		
		if (eliminados.isEmpty() == false)
		{
			nombreEditado += " sin ";
			for (Ingrediente ingred: eliminados) 
			{
				nombreEditado += ingred.getNombre() + " ";
			} 
		}
			
		return nombreEditado;
	}

	@Override
	public String generarTextoFactura() 
	{
		return getNombre() + ": " + getPrecio();
	}
	
	public void agregarIngrediente(Ingrediente Ingred) 
	{
		agregados.add(Ingred);
	}
	
	public void eliminarIngrediente(Ingrediente noIngred) 
	{
		eliminados.add(noIngred);
	}

}

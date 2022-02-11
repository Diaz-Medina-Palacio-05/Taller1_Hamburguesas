package taller1_hamburguesasCorral.modelo;

import java.util.ArrayList;

public class Combo implements Producto
{
	///Attributes
	private double descuento;
	private String nombreCombo;
	private ArrayList<ProductoMenu> itemsCombo;
	///Constructor
	
	public Combo(String nombre, double menos)
	{
		descuento = menos;
		nombreCombo = nombre;
		itemsCombo = new ArrayList<ProductoMenu>();
	}
	
	public void agregarCombo(ProductoMenu itemCombo)
	{
		itemsCombo.add(itemCombo);
	}
	
	@Override
	public int getPrecio() 
	{
		int pFinal = 0;
		for (ProductoMenu item: itemsCombo) 
		{
			int precio0 = item.getPrecio();
			double precio = precio0 - (descuento * precio0);
			pFinal += (int) precio;
		}
			
		return pFinal;
	}

	@Override
	public String getNombre() 
	{
		return nombreCombo;
	}

	@Override
	public String generarTextoFactura() 
	{
		int valor = getPrecio();
		return nombreCombo + ": " + valor;
	}

}

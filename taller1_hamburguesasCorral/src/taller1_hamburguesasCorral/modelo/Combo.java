package taller1_hamburguesasCorral.modelo;

import java.util.ArrayList;

public class Combo implements Producto
{
	///Attributes
	private double descuento;
	private String nombreCombo;
	private ArrayList<ProductoMenu> itemsCombo;
	private Bebida bebidaCombo;
	private int calorias;
	///Constructor
	
	public Combo(String nombre, double menos, int pcalorias)
	{
		descuento = menos;
		nombreCombo = nombre;
		itemsCombo = new ArrayList<ProductoMenu>();
		bebidaCombo = null;
		calorias = pcalorias;
	}
	
	public void agregarBebida(Bebida pbebidaCombo)
	{
		bebidaCombo = pbebidaCombo;
	}
	
	public void agregarCombo(ProductoMenu itemCombo)
	{
		itemsCombo.add(itemCombo);
	}
	
	public int getCalorias()
	{
		return calorias;
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
		
		int precioBebida = bebidaCombo.getCostoAdicional();
		
		return pFinal + precioBebida;
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

package taller1_hamburguesasCorral.modelo;

import java.util.ArrayList;

public class Combo implements Producto
{
	///Attributes
	private double descuento;
	private String nombreCombo;
	private ArrayList<ProductoMenu> itemsCombo;
	private Bebida bebidaCombo;
	///Constructor
	
	public Combo(String nombre, double menos)
	{
		descuento = menos;
		nombreCombo = nombre;
		itemsCombo = new ArrayList<ProductoMenu>();
		bebidaCombo = null;
	}
	
	public void agregarBebida(Bebida pbebidaCombo)
	{
		bebidaCombo = pbebidaCombo;
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

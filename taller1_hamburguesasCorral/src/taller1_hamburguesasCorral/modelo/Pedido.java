package taller1_hamburguesasCorral.modelo;

import java.util.ArrayList;

public class Pedido 
{
	///Attributes
	private int numeroPedido;
	private int iDPedido;
	private String nombreCliente;
	private String direccionCliente;
	public ArrayList<ProductoMenu> itemsPedido;
	
	///Constructor
	
	public Pedido (String nombre, String direccion) 
	{
		this.numeroPedido ++;
		this.iDPedido ++;
		this.nombreCliente = nombre;
		this.direccionCliente = direccion;
		itemsPedido = new ArrayList<ProductoMenu>();
	}
	
	///Models
	
	public int getIDPedido() 
	{
		return iDPedido;
	}

	public void añadirPedido(ProductoMenu nuevoItem) 
	{
		itemsPedido.add(nuevoItem);
	}
	
	private int getPrecioNetoPedido() 
	{
		int PrecioNeto = 0;
		for (Producto item: itemsPedido) 
		{
			int valor = item.getPrecio();
			PrecioNeto += valor;
		}
		return PrecioNeto;
	}
	
	private int getPrecioTotalPedido()
	{
		int Neto = getPrecioNetoPedido();
		int pIVA = getPrecioIVAPedido();
		int Total = Neto + pIVA;
		return Total;
	}
	
	private int getPrecioIVAPedido() 
	{
		int Neto = getPrecioNetoPedido();
		float pIVA = Neto * 19;
		int intIVA = (int)pIVA;
		return intIVA;
	}
	
	private String darTextoFactura() 
	{
		return null;
	}
	
	public void guardarFactura() 
	{
		
	}
	
}

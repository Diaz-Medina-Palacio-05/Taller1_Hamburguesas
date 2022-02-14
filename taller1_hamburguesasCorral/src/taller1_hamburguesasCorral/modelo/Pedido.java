package taller1_hamburguesasCorral.modelo;

import java.util.ArrayList;

public class Pedido 
{
	///Attributes
	private int numeroPedido;
	private int iDPedido;
	private String nombreCliente;
	private String direccionCliente;
	public ArrayList<Producto> itemsPedido;
	
	///Constructor
	public Pedido (String nombre, String direccion, int noPedido) 
	{
		nombreCliente = nombre;
		direccionCliente = direccion;
		itemsPedido = new ArrayList<>();
		numeroPedido = noPedido;
		iDPedido = noPedido - 1; 
	}
	
	///Models
	
	public int getIDPedido() 
	{
		return iDPedido;
	}

	public void añadirPedido(Producto nuevoItem) 
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
		double pIVA = Neto * 0.19;
		int intIVA = (int)pIVA;
		return intIVA;
	}
	
	private String darTextoFactura() 
	{
		String txtFactura = "\n ====== Factura No. " + 
				numeroPedido + " ====== \n" +  "\nNombre Cliente: " + nombreCliente + "\nDireccion Cliente:  " 
			+ direccionCliente + "\n" + "---------------------------------";
		if (itemsPedido.size() != 0) 
		{
			txtFactura += "\n        ==== Pedido ====         \n";
			for (Producto m: itemsPedido)
				txtFactura += ("\n" + m.generarTextoFactura());
			txtFactura += "\n---------------------------------" + "\nTotal Neto:                  $...." 
				+ getPrecioNetoPedido() + "\n+ Precio IVA (19%)           $...." + getPrecioIVAPedido() + 
				"\n---------------------------------" + "\nPrecio Total:                $...." + getPrecioTotalPedido();
		}
		else
		{
			txtFactura += "\nNo ha añadido ningún producto";
		}
		return txtFactura;
	}
	
	public void guardarFactura() 	
	{
		String nuevaFactura = darTextoFactura();
		numeroPedido++;
		iDPedido++;
		System.out.println(nuevaFactura);
	}
	
	public Producto getLastProducto()
	{
		return itemsPedido.get(itemsPedido.size() - 1);
	}
	
}

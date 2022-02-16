package taller1_hamburguesasCorral.modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pedido 
{
	///Attributes
	private int numeroPedido;
	private int iDPedido;
	private String nombreCliente;
	private String direccionCliente;
	public ArrayList<Producto> itemsPedido;
	public ArrayList<Producto> calorias;
	
	///Constructor
	public Pedido (String nombre, String direccion, int noPedido) 
	{
		nombreCliente = nombre;
		direccionCliente = direccion;
		itemsPedido = new ArrayList<>();
		numeroPedido = noPedido;
		iDPedido = noPedido; 
	}
	
	///Models
	
	public int getIDPedido() 
	{
		return iDPedido;
	}

	public void anadirPedido(Producto nuevoItem) 
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
	
	private int getCaloriasPedido() 
	{
		int Calorias = 0;
		for (Producto item: itemsPedido) 
		{
			int valor = item.getCalorias();
			Calorias += valor;
		}
		return Calorias;
	}
		

	
	private int getPrecioIVAPedido() 
	{
		int Neto = getPrecioNetoPedido();
		double pIVA = Neto * 0.19;
		int intIVA = (int)pIVA;
		return intIVA;
	}
	
	
	
	
	
	public String darTextoFactura()
	{
		String txtFactura = "\n ====== Factura No. " + 
				numeroPedido + " ====== \n" +  "\nNombre Cliente: " + nombreCliente + "\nDireccion Cliente:  " 
			+ direccionCliente + "\n" + "---------------------------------";
		if (itemsPedido.size() != 0) 
		{
			txtFactura += "\n        ==== Pedido ====         \n";
			for (Producto m: itemsPedido)
			{
				txtFactura += ("\n" + m.generarTextoFactura());
				txtFactura += ("\n Calorias Totales del pedido: " + getCaloriasPedido());
				txtFactura += "\n---------------------------------" + "\nTotal Neto:                  $...." 
				+ getPrecioNetoPedido() + "\n+ Precio IVA (19%)           $...." + getPrecioIVAPedido() + 
				"\n---------------------------------" + "\nPrecio Total:                $...." + getPrecioTotalPedido();
			}
		}
		else
		{
			txtFactura += "\nNo ha anadido ningun producto";
		}
		return txtFactura;
	}
	
	public String guardarFactura() throws IOException 	
	{
		String nuevaFactura = darTextoFactura();
		numeroPedido++;
		File nombre = new File("./facturas/" + iDPedido + ".txt");
		if (!nombre.createNewFile()) 
		{
			System.err.print("El archivo ya existe");
		}
		BufferedWriter archivo = new BufferedWriter(new FileWriter(nombre));
		archivo.write(nuevaFactura);
		archivo.close();
		return nuevaFactura;
	}
	
	public Producto getLastProducto()
	{
		return itemsPedido.get(itemsPedido.size() - 1);
	}

	public Boolean comparar(Pedido otroPedido) {
		Boolean iguales = true;
		
		int cantProductosMios = itemsPedido.size();
		int cantProductosOtro = otroPedido.itemsPedido.size();
		
		if (cantProductosMios != cantProductosOtro)
		{
			iguales = false;
		}
		else
		{
			for (int i = 0; i < cantProductosMios && iguales == true; i++)
			{
				Producto itemMio = itemsPedido.get(i);
				Producto itemOtro = otroPedido.itemsPedido.get(i);
				
				String nombreProductoMio = itemMio.getNombre();
				String nombreProductoOtro = itemOtro.getNombre();
				
				if (!nombreProductoMio.equals(nombreProductoOtro))
				{
					iguales = false;
				}
			}
		}

		return iguales;
	}
	
}

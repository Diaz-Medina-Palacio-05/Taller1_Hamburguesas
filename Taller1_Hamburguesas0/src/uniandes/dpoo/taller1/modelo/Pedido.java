package uniandes.dpoo.taller1.modelo;

import java.io.File;

public class Pedido {
	
	// ************************************************************************
	// Atributos
	// ************************************************************************
	
	private int numeroPedidos;
	
	private int idPedido;
	
	private String nombreCliente;
	
	private String dirreccionCliente;
	
	// ************************************************************************
	// Constructores
	// ************************************************************************
	
	public Pedido (String nombreCliente, String direccionCliente)
	{
		this.numeroPedidos = int algo;
		this.idPedido = int algo;
		this.nombreCliente = nombreCliente;
		this.dirreccionCliente = direccionCliente;
		
	}
	
	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************
	
	public int getIdPedido()
	{
		return idPedido;
		
	}
	
	// ************************************************************************
	// Otros Métodos
	// ************************************************************************
	
	int TODO;
	String TODO2; 
	
	public void agregarProducto(IProducto nuevoItem)
	{
		
	}
	
	private int getPrecioNetoPedido ()
	{
		return TODO;
		
	}
	
	private int getPrecioTotalPedido ()
	{
		return TODO;
		
	}
	
	private int getPrecioIvaPedido ()
	{
		return TODO;
		
	}
	
	private String generarTextoFactura ()
	{
		return TODO2;
		
	}
	
	public void guardarFactura(File archivo)
	{
		
	}
	
	
	
	
	
	
	
}

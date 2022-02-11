package taller1_hamburguesasCorral.logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import taller1_hamburguesasCorral.modelo.Combo;
import taller1_hamburguesasCorral.modelo.Ingrediente;
import taller1_hamburguesasCorral.modelo.Pedido;
import taller1_hamburguesasCorral.modelo.Producto;
import taller1_hamburguesasCorral.modelo.ProductoMenu;


public class Restaurante 
{
	///Attributes
	public ArrayList<Combo> Combos = new ArrayList<>();
	public ArrayList<Pedido> Pedidos = new ArrayList<>();
	public ArrayList<Ingrediente> Ingredientes = new ArrayList<>();
	public ArrayList<ProductoMenu> menuBase = new ArrayList<>();
	///Constructor
	
	
	///Models
	
	

	public void iniciarPedido(String nombreCliente, String direccionCliente) 
	{
		Pedido pedido = new Pedido(nombreCliente, direccionCliente);
		Pedidos.add(pedido);
	}
	
	public void cerrarGuardarPedido() 
	{
		
	}
	
	public Pedido getPedidoEnCurso() 
	{
		return null;
	}
	
	public ArrayList<Producto> getMenuBase() 
	{
		return null;
	}
	
	public ArrayList<Ingrediente> getIngredientes()
	{
		return null;
	}
	
	public void cargarInfoRestaurante() 
	{
		try 
		{
			cargarInfoMenu();
			cargarInfoIngredientes();
			System.out.println("\n===== Nuestro Menu =====\n");
			for (ProductoMenu i: menuBase) 
			{
				System.out.println(i.generarTextoFactura());  ; 
			}
			System.out.println("\n===== Adiciones =====\n");
			for (Ingrediente a: Ingredientes) 
			{
				String nombre = a.getNombre();
				int precio = a.getCostoAdicional();
				System.out.println(nombre + ": " + precio);
			}
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		} 
		
	}
	
	private void cargarInfoIngredientes() throws IOException 
	{ 
		File añadidos = new File("./data/ingredientes.txt");
        BufferedReader br = new BufferedReader(new FileReader(añadidos));
        String line = br.readLine();
        String[] info;
        Ingrediente prodIngrediente;

        while (line != null) {
            info = line.split(";");
            String nombre = info[0];
            int precio = Integer.parseInt(info[1]);
            prodIngrediente = new Ingrediente(nombre, precio);
            Ingredientes.add(prodIngrediente);
            line = br.readLine();
        }
        br.close(); 
	}
	
	private void cargarInfoMenu() throws IOException 
	{
		File menu = new File("./data/menu.txt");
        BufferedReader br = new BufferedReader(new FileReader(menu));
        String line = br.readLine();
        String[] info;
        ProductoMenu productoMenu;

        while (line != null) {
            info = line.split(";");
            String nombre = info[0];
            int precio = Integer.parseInt(info[1]);
            productoMenu = new ProductoMenu(nombre, precio);
            menuBase.add(productoMenu);
            line = br.readLine();
        }
        br.close(); 
	}
	
	private void cargarInfoCombos(File archivoCombos) 
	{
		
	}
	
	
}

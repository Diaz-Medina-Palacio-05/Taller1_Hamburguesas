package taller1_hamburguesasCorral.logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import taller1_hamburguesasCorral.modelo.Combo;
import taller1_hamburguesasCorral.modelo.Ingrediente;
import taller1_hamburguesasCorral.modelo.Pedido;
import taller1_hamburguesasCorral.modelo.Producto;
import taller1_hamburguesasCorral.modelo.ProductoAjustado;
import taller1_hamburguesasCorral.modelo.ProductoMenu;


public class Restaurante 
{
	///Attributes
	public ArrayList<Combo> Combos;
	public ArrayList<Pedido> Pedidos;
	public ArrayList<Ingrediente> Ingredientes;
	public ArrayList<ProductoMenu> menuBase;
	public int cantPedidos;
	public HashMap<Integer, String> todosPedidos;
	
	///Constructor
	
	public Restaurante() 
	{
		Combos = new ArrayList<>();
		Pedidos = new ArrayList<>();
		Ingredientes = new ArrayList<>();
		menuBase = new ArrayList<>();
		cantPedidos = 0;
		todosPedidos = new HashMap<>();
	}
	///Models
	public void iniciarPedido(String nombreCliente, String direccionCliente) 
	{
		cantPedidos++;
		Pedido pedido = new Pedido(nombreCliente, direccionCliente, cantPedidos);
		Pedidos.add(pedido);
	}
	
	public void cerrarGuardarPedido() throws IOException 
	{
		int iD = getPedidoEnCurso().getIDPedido();
		String facturaFinalizada = getPedidoEnCurso().guardarFactura();
		todosPedidos.put(iD, facturaFinalizada);
	}
	
	public Pedido getPedidoEnCurso() 
	{
		return Pedidos.get(Pedidos.size() - 1);
	}
	
	public ArrayList<ProductoMenu> getMenuBase() 
	{
		return menuBase;
	}
	
	public ArrayList<Ingrediente> getIngredientes()
	{
		return Ingredientes;
	}
	
	public void cargarInfoRestaurante() 
	{
		try 
		{
			cargarInfoMenu();
			cargarInfoIngredientes();
			cargarInfoCombos();
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
			System.out.println("\n===== Combos =====\n");
			for (Combo c: Combos) 
			{
				System.out.println(c.generarTextoFactura());  ; 
			}
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		} 
		
	}
	
	private void cargarInfoIngredientes() throws IOException 
	{ 
		File anadidos = new File("./data/ingredientes.txt");
        BufferedReader br = new BufferedReader(new FileReader(anadidos));
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
	
	private void cargarInfoCombos() throws IOException 
	{
		File menuCombos = new File("./data/combos.txt");
        BufferedReader br = new BufferedReader(new FileReader(menuCombos));
        String line = br.readLine();
        String[] info;
        Combo Comb0;
        ProductoMenu productoMenu;

        while (line != null) {
            info = line.split(";");
            String nombre = info[0];
            double descuento = 0.07; 
            if (info[1] == "10%")
            	descuento = 0.10;
            Comb0 = new Combo(nombre, descuento);
            for (int l = 2; l < 5; l++) 
            {
            	productoMenu = buscarProducto(info[l]);
            	Comb0.agregarCombo(productoMenu);
            }
            Combos.add(Comb0);
            line = br.readLine();
        }
        br.close(); 
	}
	
	///Generar un metodo de busqueda de productos en menu base
	
	private ProductoMenu buscarProducto(String nombreProducto)
	{
		ProductoMenu theProducto = null;
		for (int i = 0; i < menuBase.size() && theProducto == null; i++)
		{
			if (menuBase.get(i).getNombre().equals(nombreProducto))
				theProducto = menuBase.get(i);
		}
		return theProducto;
	}
	
	private Producto buscarItemTipoProducto(int iDProducto, boolean tipo)
	{
		Producto elProducto = null;
		if (tipo == true)
		{
			elProducto = Combos.get(iDProducto - 1);
		}
		else if (tipo == false)
		{
			elProducto = menuBase.get(iDProducto - 1);
		}
		else
			System.out.println("No se Encontro producto");
		
		return elProducto;
	}
	
	private Ingrediente buscarIngrediente(int idIngrediente)
	{
		return Ingredientes.get(idIngrediente - 1);
	}
	
	public void anadirProductoAPedido(int iDProducto, Pedido elPedido, boolean esCombo) 
	{
		Producto elProducto = buscarItemTipoProducto(iDProducto, esCombo);
		elPedido.anadirPedido(elProducto);
		
	}
	
	public void anadirQuitarIngredienteProducto(Pedido elPedido, int iDIngrediente, boolean quitar)
	{
		Producto aEditar = elPedido.getLastProducto();
		elPedido.itemsPedido.remove(aEditar);
		ProductoAjustado productoEditado = new ProductoAjustado(aEditar);
		Ingrediente elIngrediente = buscarIngrediente(iDIngrediente);
		if (quitar == true) 
		{
			productoEditado.eliminarIngrediente(elIngrediente);;
		}
		else 
		{
			productoEditado.agregarIngrediente(elIngrediente);
		}
		elPedido.anadirPedido(productoEditado);
			
	}
	
	public String encontrarPedido(int codigo)
	{
		return todosPedidos.get(codigo);
	}
	
}

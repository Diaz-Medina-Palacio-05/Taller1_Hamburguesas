package taller1_hamburguesasCorral.logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import taller1_hamburguesasCorral.modelo.Bebida;
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
	public ArrayList<Bebida> Bebidas;
	public ArrayList<ProductoMenu> menuBase;
	public int cantPedidos;
	public HashMap<Integer, Pedido> todosPedidos;
	
	///Constructor
	
	public Restaurante() 
	{
		Combos = new ArrayList<>();
		Pedidos = new ArrayList<>();
		Ingredientes = new ArrayList<>();
		Bebidas = new ArrayList<>();
		menuBase = new ArrayList<>();
		cantPedidos = darCantPedidos();
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
		Pedido pedidoEnCurso = getPedidoEnCurso();
		int iD = pedidoEnCurso.getIDPedido();
		
		
		
		// revisar si el pedido tiene otro identico a este
		
		ArrayList<Pedido> pedidosIdenticos = darPedidosIdenticos(pedidoEnCurso);
		
		if (pedidosIdenticos.size() > 0)
		{
			imprimirPedidosIdenticos(pedidosIdenticos);
		}
		else
		{
			System.out.println("Nota: No hay pedidos identicos");
		}
		
		todosPedidos.put(iD, pedidoEnCurso);
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
			cargarInfoBebidas();
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
			
			System.out.println("\n===== Bebidas =====\n");
			for (Bebida a: Bebidas) 
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
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		
	}
	
	private void imprimirPedidosIdenticos(ArrayList<Pedido> pedidosIdenticos) {
		System.out.println("Lista de pedidos identicos:");
		
		for (Pedido pedido : pedidosIdenticos)
		{
			System.out.println("ID: " + pedido.getIDPedido());
		}
	}

	
	private ArrayList<Pedido> darPedidosIdenticos(Pedido elPedidoActual)
	{
		
		ArrayList<Pedido> pedidosIdenticos = new ArrayList<Pedido>();
		
		for(HashMap.Entry<Integer, Pedido> llaveValor: todosPedidos.entrySet()) 
		{
			Pedido pedidoRecorrido = llaveValor.getValue();
			
			Boolean esIdentico = pedidoRecorrido.comparar(elPedidoActual);
			
			if(esIdentico == true)
			{
				pedidosIdenticos.add(pedidoRecorrido);
			}	
		}
		
		return pedidosIdenticos;
	}
	
	private int darCantPedidos() 
	{
		int cantPedidos = 0;
		File pedidosDir = new File("./facturas");
		
		String facturas[] = pedidosDir.list();
		
		for(int i=0; i<facturas.length; i++) 
		{
			cantPedidos++;
		  }
		
		return cantPedidos;
		
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
            int calorias = Integer.parseInt(info[2]);
            prodIngrediente = new Ingrediente(nombre, precio, calorias);
            Ingredientes.add(prodIngrediente);
            line = br.readLine();
        }
        br.close(); 
	}
	
	private void cargarInfoBebidas() throws IOException 
	{ 
		File bebidas = new File("./data/bebidas.txt");
        BufferedReader br = new BufferedReader(new FileReader(bebidas));
        String line = br.readLine();
        String[] info;
        Bebida prodBebida;

        while (line != null) {
            info = line.split(";");
            String nombre = info[0];
            int precio = Integer.parseInt(info[1]);
            int calorias = Integer.parseInt(info[2]);
            prodBebida = new Bebida(nombre, precio, calorias);
            Bebidas.add(prodBebida);
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
            int calorias = Integer.parseInt(info[2]);
            productoMenu = new ProductoMenu(nombre, precio, calorias);
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
        Bebida bebidaCombo;

        while (line != null) {
            info = line.split(";");
            String nombre = info[0];
            int calorias = Integer.parseInt(info[5]);
            double descuento = 0.07; 
            
            if (info[1] == "10%")
            	descuento = 0.10;
            Comb0 = new Combo(nombre, descuento, calorias);
            for (int i = 2; i < 5; i++) 
            {
            	productoMenu = buscarProducto(info[i]);
            	if (productoMenu != null)
            	{
            		Comb0.agregarCombo(productoMenu);	
            	}
            	
            	bebidaCombo = buscarBebida(info[i]);
            	if (bebidaCombo != null)
            	{
            		Comb0.agregarBebida(bebidaCombo);
            	}
            	
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
	
	 
	
	
	private Bebida buscarBebida(String nombreBebida)
	{
		Bebida theBbebida = null;
		for (int i = 0; i < Bebidas.size() && theBbebida == null; i++)
		{
			if (Bebidas.get(i).getNombre().equals(nombreBebida))
				theBbebida = Bebidas.get(i);
		}
		return theBbebida;
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
		System.out.println("hola");
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
		Ingrediente elIngrediente = buscarIngrediente(iDIngrediente);
		int caloriasIngrediente = elIngrediente.getCalorias();
		ProductoAjustado productoEditado;
		
		if (quitar == true) 
		{
			productoEditado = new ProductoAjustado(aEditar, -caloriasIngrediente);
			productoEditado.eliminarIngrediente(elIngrediente);
		}
		else 
		{
			productoEditado = new ProductoAjustado(aEditar, caloriasIngrediente);
			productoEditado.agregarIngrediente(elIngrediente);
		}
		elPedido.anadirPedido(productoEditado);
			
	}
	
	public Pedido encontrarPedido(int codigo)
	{
		return todosPedidos.get(codigo);
	}
	
}

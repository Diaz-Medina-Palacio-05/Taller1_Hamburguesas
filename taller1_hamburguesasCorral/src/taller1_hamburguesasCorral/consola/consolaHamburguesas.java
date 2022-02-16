package taller1_hamburguesasCorral.consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import taller1_hamburguesasCorral.logica.Restaurante;
import taller1_hamburguesasCorral.modelo.Combo;
import taller1_hamburguesasCorral.modelo.Ingrediente;
import taller1_hamburguesasCorral.modelo.Pedido;
import taller1_hamburguesasCorral.modelo.ProductoMenu;

public class consolaHamburguesas 
{
	private Restaurante corral = new Restaurante();
	
	public void ejecutarOpcion() throws IOException 
	{
		System.out.println("Bienvenido a Hamburgesas EL CORRAL\n");

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opcion"));
				switch(opcion_seleccionada) {
					case 1 -> corral.cargarInfoRestaurante();
					case 2 -> nuevoPedido();
					case 3 -> agregarComida();
					case 4 -> {System.out.println("Guardando Pedido... "); corral.cerrarGuardarPedido();}
					case 5 -> buscarPedidoPorID();
					case 6 -> {System.out.println("\n Saliendo de la aplicacion ...");}
					default -> System.out.println("Seleccione una opcion valida");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println(e);
			}
		}
		
	}
	
	public void mostrarMenu() 
	{
		System.out.println("\n Opciones de la aplicacion\n");
		System.out.println("1. CARGAR RESTAURANTE / Mostrar Menu");
		System.out.println("2. Hacer un NUEVO Pedido");
		System.out.println("3. Agregar un elemento a un pedido");
		System.out.println("4. Cerrar un pedido y guardar la factura");
		System.out.println("5. Consultar la informacion de un pedido dado su id");
		System.out.println("6. Salir de la aplicacion\n");
	}
	
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
	public void nuevoPedido() 
	{
		String Nombre = input("\nPor favor ingrese su nombre");
		String Direccion = input("\nPor favor ingrese su direccion");
		corral.iniciarPedido(Nombre, Direccion);
	}
	
	public void agregarComida() 
	{
		boolean esCombo;
		Pedido theOrder = corral.getPedidoEnCurso();
		System.out.println("\n***** Agregale Comida a tu Pedido! *****\n");
		int respuesta =  Integer.parseInt(input("\nQuieres 1. Sencillo o 2. Combo"));
		if (respuesta == 1) 
		{
			imprimirMenu();
			esCombo = false;
			int opcion = Integer.parseInt(input("\nSeleccione un Producto..."));
			corral.anadirProductoAPedido(opcion, theOrder, esCombo);
			adiciones(theOrder);
		}
		else if(respuesta == 2)
		{
			imprimirMenuCombos();
			esCombo = true;
			int opcion = Integer.parseInt(input("\nSeleccione un Producto..."));
			corral.anadirProductoAPedido(opcion, theOrder, esCombo);
		}
		else
			System.out.println("Escoja una opcion valida");
	}
	
	public void imprimirMenu()
	{
		System.out.println("\n===== Nuestro Menu =====\n");
		int iD = 0;
		for (ProductoMenu i: corral.menuBase) 
		{
			iD++;
			System.out.println(iD + ". " + i.generarTextoFactura()); 
		}
		
	}
	
	public void imprimirMenuCombos()
	{
		int iD = 0;
		System.out.println("\n===== Nuestros Combos =====\n");
		for (Combo c: corral.Combos) 
		{
			iD++;
			System.out.println(iD + ". " + c.generarTextoFactura());
		}
	}
	
	public void imprimirIngredientes()
	{
		int iD = 0;
		System.out.println("\n===== Adiciones o Ingredientes a no incluir =====\n");
		for (Ingrediente a: corral.Ingredientes) 
		{
			iD++;
			String nombre = a.getNombre();
			int precio = a.getCostoAdicional();
			System.out.println(iD + ". " + nombre + ": " + precio);
		}
	}
	
	public void adiciones(Pedido theOrder)
	{
		boolean continuar = true;
		while (continuar) 
		{
			int respuesta = Integer.parseInt(input("\nQuieres adicionar o quitarle algo a tu pedido? \n1. Agregar     2. Quitar     3. No"));
			switch(respuesta) 
			{
			case 1 -> 
			{
				imprimirIngredientes(); 
				int ingred = Integer.parseInt(input("\nQue ingrediente quieres adicionar?"));
				corral.anadirQuitarIngredienteProducto(theOrder, ingred, false);
			
			}
			case 2 -> 
			{
				imprimirIngredientes(); 
				int ingred = Integer.parseInt(input("\nQue ingrediente quieres quitar?"));
				corral.anadirQuitarIngredienteProducto(theOrder, ingred, true);
			
			}
			case 3 -> continuar = false;
			default -> System.out.println("Seleccione una opcion valida");
			}
			
		}
		
	}
	
	public void buscarPedidoPorID()
	{
		int respuesta = Integer.parseInt(input("Por favor ingrese el iD del Pedido"));
		System.out.println(corral.encontrarPedido(respuesta));
	}
	
	public static void main(String[] args) throws IOException 
	{
		consolaHamburguesas console = new consolaHamburguesas();
		console.ejecutarOpcion();
	}

}

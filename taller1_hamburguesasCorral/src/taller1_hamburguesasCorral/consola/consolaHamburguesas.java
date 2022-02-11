package taller1_hamburguesasCorral.consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import taller1_hamburguesasCorral.logica.Restaurante;

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
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				
				switch(opcion_seleccionada) {
					case 1 -> corral.cargarInfoRestaurante();
					case 2 -> System.out.println("\nEsta es la opción 1 esta en TODO");
					case 3 -> System.out.println("\nEsta es la opción 2 esta en TODO");
					case 4 -> System.out.println("\nEsta es la opcion 3 esta en TODO");
					case 5 -> System.out.println("\nEsta es la opción 4 esta en TODO");
					case 6 -> System.out.println("\nEsta es la opción 5 esta en TODO");
					case 7 -> {System.out.println("Saliendo de la aplicación ...");
					continuar = false;}
					default -> System.out.println("Seleccione una opcion valida");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
		
	}
	
	public void mostrarMenu() 
	{
		System.out.println("\n Opciones de la aplicación\n");
		System.out.println("1. CARGAR RESTAURANTE");
		System.out.println("2. Mostrar el Menú");
		System.out.println("3. Iniciar un nuevo pedido");
		System.out.println("4. Agregar un elemento a un pedido");
		System.out.println("5. Cerrar un pedido y guardar la facturao");
		System.out.println("6. Consultar la información de un pedido dado su id");
		System.out.println("7. Salir de la aplicación\n");
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
	 
	public static void main(String[] args) throws IOException 
	{
		consolaHamburguesas console = new consolaHamburguesas();
		console.ejecutarOpcion();
	}

}

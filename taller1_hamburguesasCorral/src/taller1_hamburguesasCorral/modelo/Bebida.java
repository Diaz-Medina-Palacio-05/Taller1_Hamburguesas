package taller1_hamburguesasCorral.modelo;

public class Bebida {

		///Attributes
		private String nombre;
		private int costoAdicional;
		
		///Constructor
		
		public Bebida(String iNombre, int iCosto) 
		{
			nombre = iNombre;
			costoAdicional = iCosto;
		}
		
		///Models
		
		public String getNombre()
		{
			return nombre;
		}
		
		public int getCostoAdicional()
		{
			return costoAdicional;
		}
		
	}

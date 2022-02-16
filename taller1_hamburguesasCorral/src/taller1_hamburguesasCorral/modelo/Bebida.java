package taller1_hamburguesasCorral.modelo;

public class Bebida {

		///Attributes
		private String nombre;
		private int costoAdicional;
		private int calorias;
		///Constructor
		
		public Bebida(String iNombre, int iCosto, int icalorias) 
		{
			nombre = iNombre;
			costoAdicional = iCosto;
			calorias = icalorias;
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
		
		public int getCalorias()
		{
			return calorias;
		}
		
	}

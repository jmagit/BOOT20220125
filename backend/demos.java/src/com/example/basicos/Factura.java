package com.example.basicos;

import java.io.Serializable;

import com.example.exceptions.DemosException;

public class Factura implements Grafico, Persistente, Serializable {
	private static final long serialVersionUID = 1L;

	public static enum Estado { PENDIENTE, PAGADA, CANCELADA }
	public class Linea {
		private String concepto;
		private int cantidad;
		private double precio;
		
		public Linea(String concepto, int cantidad, double precio) throws DemosException {
			super();
			setConcepto(concepto);
			setCantidad(cantidad);
			this.precio = precio;
		}

		public String getConcepto() {
			return concepto;
		}

		public void setConcepto(String concepto) throws DemosException {
			if(concepto == null)
				throw new DemosException("El concepto no puede estar vacio");
			this.concepto = concepto;
		}

		public int getCantidad() {
			return cantidad;
		}

		public void setCantidad(int cantidad) {
			if(this.cantidad == cantidad) return;
			if(cantidad < 1) throw new IllegalArgumentException("La cantidad debe ser mayor que 0.");
			this.cantidad = cantidad;
		}

		public double getPrecio() {
			return precio;
		}

		public void setPrecio(double precio) {
			this.precio = precio;
		}
		
		public double getTotal() {
			return cantidad * precio;
		}

		public double getNumeroFactura() {
			return numero;
		}

		
	}
	private int numero;
	private String direccion;
	private Estado estado;
	private Linea[] lineas;
	
	public Factura(int numero) {
		super();
		this.numero = numero;
		lineas = new Linea[100];
		estado = Estado.PENDIENTE;
	}

	public double getNumero() {
		return numero;
	}
	
	public void addLinea(Linea linea) {
		
	}
	
	public void addLinea(String concepto, int cantidad, double precio) throws Exception {
		try {
			addLinea(new Linea(concepto, cantidad, precio));
		} catch (DemosException e) {
			try {
				addLinea(new Linea("Sin concepto", cantidad, precio));
			} catch (DemosException e1) {
				throw new Exception("No tengo ni idea", e1);
			}
		}
	}
	
	public Estado getEstado() { return estado; }
	
	public void pagada() {
		estado = Estado.PAGADA;
	}
	
	public void cancelar() {
		estado = Estado.CANCELADA;
	}
	// ...

	@Override
	public void pintate() {
		System.out.println("Soy la factura " + numero);
	}

	@Override
	public void guardar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saluda() {
		// TODO Auto-generated method stub
		Persistente.super.saluda();
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
}

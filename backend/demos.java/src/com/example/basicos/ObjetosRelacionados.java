package com.example.basicos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ObjetosRelacionados {
	class Linea {
		int num;
		double cantidad;
		Pedido pedido;
		
		public Linea(int num, double cantidad) {
			super();
			this.num = num;
			this.cantidad = cantidad;
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

		public double getCantidad() {
			return cantidad;
		}

		public void setCantidad(double cantidad) {
			this.cantidad = cantidad;
		}

		public Pedido getPedido() {
			return pedido;
		}

		public void setPedido(Pedido pedido) {
			this.pedido = pedido;
		}
		
	}
	class Pedido {
		int num;
		LocalDateTime fecha;
		List<Linea> lineas = new ArrayList<ObjetosRelacionados.Linea>();
		
		public Pedido(int num) {
			super();
			this.num = num;
		}
		public void addLinea(Linea nueva) {
			lineas.add(nueva);
			nueva.setPedido(this);
		}
		public void addLinea(int num, double cantidad) {
			lineas.add(new Linea(num, cantidad));
		}
		public List<Linea> getLineas() {
			return lineas;
		}
	}

	public void ejemplo() {
		var pedido = new Pedido(1);
		pedido.addLinea(new Linea(1, 4));
		pedido.addLinea(2, 4);
		var pedido2 = new Pedido(2);
		pedido2.addLinea(pedido.getLineas().get(0));
		
		var linea = pedido.getLineas().get(0);
		linea.getPedido();
	}
}

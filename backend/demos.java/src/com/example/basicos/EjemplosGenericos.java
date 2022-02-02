package com.example.basicos;

import java.util.Objects;

public class EjemplosGenericos {

	public static record PuntoReg(double x, double y, double z) {};
	
	public static class Punto {
		public final double x, y;

		public Punto(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Punto other = (Punto) obj;
			return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
					&& Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
		}
		
	}
	public static class Elemento<K, V> {
		private K key;
		private V value;

		public Elemento(K key, V value) {
			super();
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}

	}

/*
	public static class Elemento {
		private Object key;
		private String value;

		public Elemento(Object key, String value) {
			super();
			this.key = key;
			this.value = value;
		}

		public Object getKey() {
			return key;
		}

		public void setKey(Object key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

	public static class Elemento {
		private int key;
		private String value;

		public Elemento(int key, String value) {
			super();
			this.key = key;
			this.value = value;
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}
	public static class ElementoChar {
		private char key;
		private String value;

		public ElementoChar(char key, String value) {
			super();
			this.key = key;
			this.value = value;
		}

		public char getKey() {
			return key;
		}

		public void setKey(char key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}
*/
}

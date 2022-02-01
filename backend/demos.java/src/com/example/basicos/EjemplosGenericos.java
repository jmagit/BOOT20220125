package com.example.basicos;

public class EjemplosGenericos {

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

package com.example.basicos;

public class Direccion {
	private String calle, cp, localidadString;

	public Direccion() {
		// TODO Auto-generated constructor stub
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getLocalidadString() {
		return localidadString;
	}

	public void setLocalidadString(String localidadString) {
		this.localidadString = localidadString;
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return new Direccion();
	}
}

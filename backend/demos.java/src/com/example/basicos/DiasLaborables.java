package com.example.basicos;

public enum DiasLaborables {
	LUNES(1), MARTES(2), MIERCOLES(3), JUEVES(4), VIERNES(5), DESCONOCIDO(0);

	private int value;
	private DiasLaborables(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	public static DiasLaborables getEnum(int value) {
		switch (value) {
		case 1: return DiasLaborables.LUNES;
		case 2: return DiasLaborables.MARTES;
		case 3: return DiasLaborables.MIERCOLES;
		case 4: return DiasLaborables.JUEVES;
		case 5: return DiasLaborables.VIERNES;
		default:
			throw new IllegalArgumentException("Unexpected value: " + value);
		}
	}
}

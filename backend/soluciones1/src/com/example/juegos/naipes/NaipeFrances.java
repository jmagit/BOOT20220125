package com.example.juegos.naipes;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.juegos.JuegoException;

public class NaipeFrances extends Naipe<NaipeFrances.Palos> {
	public static enum Palos { CORAZONES, DIAMANTES, TREBOLES, PICAS }
	public static final int CARTASxPALO = ValorNaipe.values().length - 1;
	
	private ValorNaipe valor;
	
	public NaipeFrances(Palos palo, ValorNaipe valor) throws JuegoException {
		super(palo, (byte)valor.valorNumerico);
		this.valor = valor;
	}
	public NaipeFrances(Palos palo, byte valor) throws JuegoException {
		super(palo, validaValor(valor));
		this.valor = ValorNaipe.toEnum(valor);
	}
	
	@Override
	protected String[] getLiterales() {
		var rslt = Stream.of(ValorNaipe.values()).map(item -> item.toString()).collect(Collectors.toList());
		return rslt.toArray(new String[1]); //;
	}
	
	private static byte validaValor(byte valor) throws JuegoException {
		if(valor < 0 || valor > 13)
			throw new JuegoException("El valor debe estar 1 y 13");
		return valor;
	}
	
	@Override
	public String getLiteral() {
		return valor.toString();
	}
	
	@Override
	public byte getValor() {
		return (byte)valor.valorNumerico;
	}
}

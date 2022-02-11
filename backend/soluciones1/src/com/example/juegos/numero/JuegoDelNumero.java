package com.example.juegos.numero;

import java.util.Random;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.example.juegos.Juego;
import com.example.juegos.JuegoException;

/**
 * Juego de adivinar numeros
 * @author Javier
 * @version 1.0
 */
public class JuegoDelNumero implements Juego<String> {
	public static class NotificaEventArgs {
		private String msg;
		private boolean cancel = false;
		public NotificaEventArgs(String msg) {
			super();
			this.msg = msg;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public boolean isCancel() {
			return cancel;
		}
		public void setCancel(boolean cancel) {
			this.cancel = cancel;
		}
		
	}
	private int numeroBuscado;
    private int intentos;
    private boolean encontrado;
    private String resultado;
    
    private Consumer<NotificaEventArgs> notifica;

    public JuegoDelNumero() {
		inicializar();
	}
    
    public Consumer<NotificaEventArgs> getNotifica() {
		return notifica;
	}

	public void setNotifica(Consumer<NotificaEventArgs> notifica) {
		this.notifica = notifica;
	}
	
	protected void onNotifica(NotificaEventArgs arg) {
		if(notifica != null)
			notifica.accept(arg);
	}

	/**
     * Inicializa el juego
     */
	@Override
	public void inicializar() {
//     numeroBuscado = (new Random()).nextInt(100) + 1;
     numeroBuscado = (int) (Math.random() * 100 + 1);
     intentos = 0;
     encontrado = false;
     resultado = "Pendiente de empezar";
     onNotifica(new NotificaEventArgs("Inicializado"));
	}
	
	@Override
	public void jugada(String movimiento) throws JuegoException {
		try {
			jugada(Integer.parseInt(movimiento));
		} catch (NumberFormatException e) {
			throw new JuegoException("No es un número.", e);
		}
	}

	public void jugada(int numeroIntroducido) throws JuegoException {
		if(getFinalizado()) {
			throw new JuegoException("El juego a finalizado");
		}
        intentos += 1;
        if (numeroBuscado == numeroIntroducido) {
            encontrado = true;
            resultado = "Bieeen!!! Acertaste.";
        } else if (intentos >= 10) {
        	resultado = "Upsss! Se acabaron los intentos, el número era el " + numeroBuscado;
        } else if (numeroBuscado > numeroIntroducido) {
        	resultado = "Mi número es mayor.";
        } else {
        	resultado = "Mi número es menor.";
        }
		var arg = new NotificaEventArgs(resultado);
		onNotifica(arg);
        if(arg.isCancel()) {
            encontrado = true;
            resultado = "CANCELADO: " + arg.getMsg();
        }
	}

	/**
	 * Cadena con el mensaje de la ultima jugada
	 */
	@Override
	public String getResultado() {
		return resultado;
	}

	@Override
	public boolean getFinalizado() {
		return intentos >= 10 || encontrado;
	}

	@Override
	public int getJugada() {
		return intentos;
	}

}

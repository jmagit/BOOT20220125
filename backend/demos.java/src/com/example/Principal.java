/**
 * 
 */
package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Javier
 *
 */
public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] t1 = { 1, 2, 3 };
		int[][] t2 = { {1, 2}, {3, 3, 3} };
		if (t2[0].length == t2[1].length) {}
		t2[0][2] = t2[1][2];
		t2[0] = t2[1];
		t2[0][0] = 0;
		t2[1] = new int[10];
		int[] aux = t2[0];
		t2[0] = t2[1];
		t2[1] = aux;
		
		int i = 3 / 2, j;
		i = i + 2;
		i += 2;
		
		++i;
		i += 1;
		i = i + 1;
		t2[i++] = aux;
		
		j = i++;
		
		String string = "Alumno" + (i > 1 ? "s" : "");
		string = (string == null ? "" : (string + "s")) + " algo ";
		double r = 3 / 2;
		byte b;
		b = (byte)i;
		
		i = (b = 0) + 1;
		
		Object o = "";
		
		if(o instanceof String) {
			String s = (String)o;
			
		}
		if(o instanceof String s) {
		
		}
		var l = false;
		if(l == true) {}
		calc((int)4);
		
	}

	static void calc(int op) {
		
	}
	static void calc(double op) {
		
	}
}

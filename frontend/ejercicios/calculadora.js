// 'use strict';
function Calculadora(fnPantalla, fnResumen) {
	if (fnPantalla && typeof (fnPantalla) !== 'function')
		throw new Error('Falta la función para pintar en la pantalla')
	if (fnResumen && typeof (fnResumen) !== 'function')
		throw new Error('Falta la función para pintar el resumen')
	let ref = this;
	let acumulado = 0;
	let operador = '+';
	let limpiar = true;
	let miPantalla = '0'
	let miResumen = '';

	ref.pantalla = '0';
	ref.resumen = '';

	ref.onPantallaChange = fnPantalla;
	ref.onResumenChange = fnResumen;
	
	function pintaPantalla() {
		ref.pantalla = miPantalla;
		if (typeof(ref.onPantallaChange) !== 'function') 
			throw new Error('Falta la función para pintar en la pantalla');
		ref.onPantallaChange(miPantalla);
	}
	function pintaResumen() {
		ref.resumen = miResumen;
		if (typeof (ref.onResumenChange) !== 'function') 
			throw new Error('Falta la función para pintar el resumen');
		ref.onResumenChange(miResumen);
	}

	ref.inicia = function () {
		acumulado = 0;
		operador = '+';
		miPantalla = '0';
		miResumen = '';
		limpiar = true;
		pintaPantalla();
		pintaResumen();
	};
	ref.inicia();

	ref.ponDigito = function (value) {
		if (typeof (value) !== 'string')
			value = value.toString();
		if (value.length != 1 || value < '0' || value > '9') {
			console.error('No es un valor numérico.');
			return;
		}
		if (limpiar || miPantalla == '0') {
			miPantalla = value;
			limpiar = false;
		} else
			miPantalla += value;
		pintaPantalla();
	};
	ref.ponOperando = function (value) {
		if (!Number.isNaN(parseFloat(value)) && parseFloat(value) == value) {
			miPantalla = value;
			limpiar = false;
			pintaPantalla();
		} else {
			console.error('No es un valor numérico.');
		}
	};
	ref.ponComa = function () {
		if (limpiar) {
			miPantalla = '0.';
			limpiar = false;
		} else if (miPantalla.indexOf('.') === -1) {
			miPantalla += '.';
		} else
			console.warn('Ya está la coma');
		pintaPantalla();
	};
	ref.borrar = function () {
		if (limpiar || miPantalla.length == 1 || (miPantalla.length == 2 && miPantalla.startsWith('-'))) {
			miPantalla = '0';
			limpiar = true;
		} else
			miPantalla = miPantalla.slice(0, -1);
		pintaPantalla();
	};
	ref.cambiaSigno = function () {
		miPantalla = (-miPantalla).toString();
		if (limpiar) {
			acumulado = -acumulado;
		}
		pintaPantalla();
	};
	ref.calcula = function (value) {
		if ('+-*/='.indexOf(value) == -1) return;

		let operando = parseFloat(miPantalla);
		switch (operador) {
			case '+':
				acumulado += operando;
				break;
			case '-':
				acumulado -= operando;
				break;
			case '*':
				acumulado *= operando;
				break;
			case '/':
				acumulado /= operando;
				break;
		}
		// Con eval()
		// acumulado = eval (acumulado + operador + miPantalla);
		miResumen = value == '=' ? '' : (`${acumulado} ${value}`);
		// Number: double-precision IEEE 754 floating point.
		// 9.9 + 1.3, 0.1 + 0.2, 1.0 - 0.9
		miPantalla = parseFloat(acumulado.toPrecision(15)).toString();
		// miPantalla = acumulado.toString();
		pintaPantalla();
		pintaResumen();
		operador = value;
		limpiar = true;
	};
}

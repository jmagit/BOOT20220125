// import { Calculadora } from '../app/js/calculadora'

describe('Pruebas de la calculadora', () => {
	let pantalla, pantallaHasCalled = false;
	let resumen, resumenHasCalled = false;
	
	let nop = () => { };
	let fnPantalla = v => { pantalla = v; pantallaHasCalled = true; };
	let fnResumen = v => { resumen = v; resumenHasCalled = true; }
	let calc = undefined;

	function limpia() {
		pantallaHasCalled = false;
		pantalla = undefined;
		resumenHasCalled = false;
		resumen = undefined;
	}

	beforeAll(() => {
		calc = new Calculadora(fnPantalla, fnResumen)
	});

	beforeEach(() => {
		calc.inicia();
		limpia();
	});

	afterEach(() => {
		limpia();
	});

	describe('Controladores de eventos', () => {
		it('Controladores: por constructor', () => {
			let calc = new Calculadora(fnPantalla, fnResumen)
			expect(pantallaHasCalled).toBeTrue()
			expect(pantalla).toBe('0')
			expect(resumenHasCalled).toBeTrue()
			expect(resumen).toBe('')
		});

		it('Invalidas', () => {
			expect(() => new Calculadora('mala')).toThrow()
			expect(() => new Calculadora(nop, 'no es una funcion')).toThrow()
		});
	});

	describe('Método: inicia', () => {
		it('Inicializa la calculadora', () => {
			limpia();
			calc.inicia()
			expect(pantallaHasCalled).toBeTrue()
			expect(resumenHasCalled).toBeTrue()
			expect(pantalla).toBe('0')
			expect(resumen).toBe('')
		})
	});

	describe('Método: ponDigito', () => {
		'0123456789'.split('').forEach(digito => {
			it(`ponDigito ${digito} como string`, () => {
				calc.ponDigito(digito)
				expect(pantalla).toBe(digito)
			})
		});
		'a-,'.split('').forEach(digito => {
			it(`ponDigito ${digito} como error`, () => {
				calc.ponDigito(digito)
				expect(pantallaHasCalled).toBeFalse()
				expect(pantalla).toBeUndefined()
			})
		});
		for (let digito = 0; digito <= 9; digito++) {
			it(`ponDigito ${digito} como number`, () => {
				calc.ponDigito(digito)
				expect(pantalla).toBe(digito.toString())
			})
		}
		['1234567890', '9876543210', '666'].forEach(caso => {
			it(`Secuencia ${caso}`, () => {
				caso.split('').forEach(digito => calc.ponDigito(digito));
				expect(pantalla).toBe(caso)
			})
		});
	});

	describe('Método: ponOperando', () => {
		['1234', '98765', '6.66', Number.POSITIVE_INFINITY].forEach(caso => {
			it(`Operando validos ${caso}`, () => {
				calc.ponOperando(caso)
				expect(pantalla).toBe(caso)
			})
		});
		['$98765', '1234$', null, undefined, ''].forEach(caso => {
			it(`Operando invalidos ${caso}`, () => {
				calc.ponOperando(caso)
				expect(pantalla).toBeUndefined()
			})
		});
	});

	describe('Método: ponComa', () => {
		it('Pone la coma', () => {
			calc.ponDigito(1)
			calc.ponComa()
			calc.ponDigito(2)
			expect(pantalla).toBe('1.2')
		})

		it('Repite la coma', () => {
			calc.ponOperando('0.1')
			calc.ponComa()
			calc.ponDigito(2)
			expect(pantalla).toBe('0.12')
		})

		it('Empieza por la coma', () => {
			calc.ponComa()
			calc.ponDigito(2)
			expect(pantalla).toBe('0.2')
		})
	});

	describe('Método: borrar', () => {
		it('Borra positivo', () => {
			calc.ponOperando('321')
			calc.borrar()
			expect(pantalla).toBe('32')
			calc.borrar()
			expect(pantalla).toBe('3')
			calc.borrar()
			expect(pantalla).toBe('0')
		})

		it('Borra negativo', () => {
			calc.ponOperando('-123')
			calc.borrar()
			expect(pantalla).toBe('-12')
			calc.borrar()
			expect(pantalla).toBe('-1')
			calc.borrar()
			expect(pantalla).toBe('0')
		})
	});

	describe('Método: cambiaSigno', () => {
		it('Cambia positivo', () => {
			calc.ponOperando('555')
			calc.cambiaSigno()
			expect(pantalla).toBe('-555')
		})

		it('Cambia negativo', () => {
			calc.ponOperando('-7032.333')
			calc.cambiaSigno()
			expect(pantalla).toBe('7032.333')
		})

		it('Cambia infinito', () => {
			calc.ponOperando(Number.POSITIVE_INFINITY)
			calc.cambiaSigno()
			expect(pantalla).toBe('-Infinity')
			calc.ponOperando(Number.NEGATIVE_INFINITY)
			calc.cambiaSigno()
			expect(pantalla).toBe('Infinity')
		})
	});

	describe('Método: calcula', () => {
		describe('Operadores desconocidos', function () {
			'%&$^a9:'.split('').forEach(operador => {
				it(`Operador ${operador} desconocido`, () => {
					calc.calcula(operador)
					expect(pantallaHasCalled).toBeFalse()
					expect(pantalla).toBeUndefined()
				})
			});
		});

		describe('Calcula sumas', function () {
			[[22222, 22222, 44444], [-1, 2, 1], [2, -1, 1], [0, 0, 0],
			[0.1, 0.2, 0.3], [9.9, 1.3, 11.2]].forEach(caso => {
				it(`Suma: ${caso[0]} + ${caso[1]} = ${caso[2]}`, function () {
					calc.ponOperando(caso[0])
					calc.calcula('+')
					calc.ponOperando(caso[1])
					calc.calcula('=')
					expect(pantalla).toBe(caso[2].toString())
				})
			});
		});

		describe('Calcula sustracciones', function () {
			[[22222, 33333, -11111], [-1, 2, -3], [0, 0, 0],
			[1, 0.9, 0.1]].forEach(caso => {
				it(`Resta: ${caso[0]} - ${caso[1]} = ${caso[2]}`, function () {
					calc.ponOperando(caso[0])
					calc.calcula('-')
					calc.ponOperando(caso[1])
					calc.calcula('=')
					expect(pantalla).toBe(caso[2].toString())
				})
			});
		});

		describe('Calcula productos', function () {
			[[10, 5, 50], [1.5, 2, 3], [0, 0, 0], [2, 0, 0],
			['Infinity', 0, 'NaN'], ['Infinity', 'NaN', 'Infinity'], ['Infinity', '-Infinity', '-Infinity'],].forEach(caso => {
				it(`Multiplica: ${caso[0]} * ${caso[1]} = ${caso[2]}`, function () {
					calc.ponOperando(caso[0])
					calc.calcula('*')
					calc.ponOperando(caso[1])
					calc.calcula('=')
					expect(pantalla).toBe(caso[2].toString())
				})
			});
		});

		describe('Calcula divisiones', function () {
			[[10, 5, 2], [1, 3, 0.333333333333333], [0, 0, 'NaN'], [2, 0, 'Infinity'],
			['Infinity', 'Infinity', 'NaN']].forEach(caso => {
				it(`Divide: ${caso[0]} / ${caso[1]} = ${caso[2]}`, function () {
					calc.ponOperando(caso[0])
					calc.calcula('/')
					calc.ponOperando(caso[1])
					calc.calcula('=')
					expect(pantalla).toBe(caso[2].toString())
				})
			});
		});
		[
			[22, '+', 5, '-', 0.75, '*', 3, '/', 2, '=', 39.375],
			[10, '*', 0.5, '/', 0.2, '+', -5, '=', 20]
		].forEach(secuencia => {
			it('Secuencia', () => {
				for (let i = 0; i < secuencia.length - 1; i++)
					if (i % 2)
						calc.calcula(secuencia[i])
					else
						calc.ponOperando(secuencia[i])
				expect(pantalla).toBe(secuencia[secuencia.length - 1].toString())
			});
		});
	});
});


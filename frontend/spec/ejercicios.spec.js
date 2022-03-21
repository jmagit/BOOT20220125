describe('Verificación de los ejercicios de JavaScript', () => {
    describe('Ejercicio 1', () => {
        describe('OK', () => {
            it('Genera aleatorio', () => {
                let spy = spyOn(Math, "random").and.returnValue(0.496003)
                let num = aleatorio(1, 100);
                expect(spy).toHaveBeenCalled()
                expect(num).toBeGreaterThanOrEqual(1)
                expect(num).toBeLessThanOrEqual(100)
                expect(num).toBe(50)
            })
        });
    
        describe('KO', () => {
            it('Falta parámetro', () => {
                expect(() => aleatorio(1)).toThrow(); 
                pending('esto es por lo que está pendiente');
            })
        });
           
    });

    describe('Ejercicio 1: Crear una función que devuelva un numero aleatorio', () => {
        [[1, 100], [-10, -1] , [0, 10]].forEach(caso => {
            it(`Valor entre ${caso[0]} mas ${caso[1]}`, () => {
                let rslt = aleatorio(caso[0], caso[1])

                expect(rslt).toBeGreaterThanOrEqual(caso[0])
                expect(rslt).toBeLessThanOrEqual(caso[1])
                expect(Number.isInteger(rslt)).toBeTrue()
            })
        });

        it('Excepción por falta de argumentos', () => {
            expect(() => aleatorio()).toThrow()
        });

        [
            { inicio: 1, fin: 'a', msg: 'Falta el valor final' },
            { inicio: 'b', fin: 0, msg: 'Falta el valor inicial' },
            { inicio: true, fin: 'cadena', msg: 'Falta el valor inicial' },
            { inicio: 10, fin: 1, msg: 'El valor final debe ser mayor que el valor inicial' },
        ].forEach(caso => {
            it(`Excepción por argumentos erróneos: ${caso.inicio} ${caso.fin} -> ${caso.msg}`, () => {
                expect(() => aleatorio(caso.inicio, caso.fin)).toThrowError(Error, caso.msg);
            })
        });

        describe('Ejemplos de espías', () => {
            beforeAll(() => {
                spyOn(Math, 'random').and.callThrough()
            })

            it('Espía', () => {
                const INICIO=0, FIN=100;
                let rslt = aleatorio(INICIO, FIN)
                
                expect(Math.random).toHaveBeenCalledWith()
                expect(rslt).toBeGreaterThanOrEqual(INICIO)
                expect(rslt).toBeLessThanOrEqual(FIN)
                expect(Number.isInteger(rslt)).toBeTrue()
            })
        })

    });

    describe(`Ejercicio 2: Adivina el Número con interfaz`, () => {
        it('Menor Mayor Igual.', () => {
            spyOn(Math, 'random').and.returnValue(0.49)
            const promptSpy = spyOn(window, 'prompt').and.returnValues('49', '51', '50' )
            const alertSpy = spyOn(window, 'alert').and.stub()

            JuegoDelNumero();

            expect(promptSpy.calls.count()).toBe(3)
            expect(alertSpy.calls.count()).toBe(3)
            expect(promptSpy.calls.all()[0].args[0]).toBe('Dame tu numero (1 de 10) [50]:')
            expect(alertSpy.calls.all()[0].args[0]).toBe('Mi número es mayor.')
            expect(promptSpy.calls.all()[1].args[0]).toBe('Dame tu numero (2 de 10) [50]:')
            expect(alertSpy.calls.all()[1].args[0]).toBe('Mi número es menor.')
            expect(promptSpy.calls.all()[2].args[0]).toBe('Dame tu numero (3 de 10) [50]:')
            expect(alertSpy.calls.all()[2].args[0]).toBe('Bieeen!!! Acertaste.')
        });

        it('Mayor Menor Igual.', () => {
            const promptSpy = spyOn(window, 'prompt').and.returnValues('3', '1', '2' )
            const alertSpy = spyOn(window, 'alert').and.stub()
            spyOn(Math, 'random').and.returnValue(0.01)

            JuegoDelNumero();

            expect(promptSpy.calls.count()).toBe(3)
            expect(alertSpy.calls.count()).toBe(3)
            expect(alertSpy.calls.all()[0].args[0]).toBe('Mi número es menor.')
            expect(alertSpy.calls.all()[1].args[0]).toBe('Mi número es mayor.')
            expect(alertSpy.calls.all()[2].args[0]).toBe('Bieeen!!! Acertaste.')
        });

        it('No acierta', () => {
            const promptSpy = spyOn(window, 'prompt').and.returnValues(0)
            const alertSpy = spyOn(window, 'alert').and.stub()
            spyOn(Math, 'random').and.returnValue(0.01)

            JuegoDelNumero();

            expect(promptSpy.calls.count()).toBe(10)
            expect(alertSpy.calls.count()).toBe(11)
            expect(promptSpy.calls.mostRecent().args[0]).toBe('Dame tu numero (10 de 10) [2]:')
            expect(alertSpy.calls.mostRecent().args[0]).toBe('Upsss! Se acabaron los intentos, el número era el 2')
        });

    });

    [JuegoConClase, Juego].forEach(caso => {
        describe(`Ejercicio 2: Adivina el Número con ${caso.name}`, () => {
            let juego = null;
            const NUM_INTENTOS = 10, VALORES = 100;

            beforeAll(() => {
                spyOn(Math, 'random').and.returnValue(0.82435)
            });

            beforeEach(() => {
                juego = new caso(NUM_INTENTOS, VALORES);
            });

            it('Mi número es mayor.', () => {
                juego.PruebaCon(82)

                expect(juego.mensaje).toBe('Mi número es mayor.')
                expect(juego.intentos).toBe(1)
                expect(juego.encontrado).toBeFalse()
            });

            it('Mi número es menor.', () => {
                juego.PruebaCon(84)
                juego.PruebaCon(84)

                expect(juego.mensaje).toBe('Mi número es menor.')
                expect(juego.intentos).toBe(2)
                expect(juego.encontrado).toBeFalse()
            });

            it('Intentos', () => {
                for (let i = 1; i < NUM_INTENTOS; i++) {
                    juego.PruebaCon(1)

                    expect(juego.intentos).toBe(i)
                }
                juego.PruebaCon(1)

                expect(juego.mensaje).toBe('Upsss! Se acabaron los intentos, el número era el 83')
                expect(juego.intentos).toBe(NUM_INTENTOS)
                expect(juego.encontrado).toBeFalse()
            });

            it('Bieeen!!! Acertaste.', () => {
                juego.PruebaCon(83)
                
                expect(juego.mensaje).toBe('Bieeen!!! Acertaste.')
                expect(juego.encontrado).toBeTrue()
            });

            it('Excedido el número de intentos', () => {
                for (let i = 0; i < NUM_INTENTOS; i++) {
                    juego.PruebaCon(1)
                }

                expect(() => juego.PruebaCon(1)).toThrow()
                expect(juego.intentos).toBe(NUM_INTENTOS)
                expect(juego.encontrado).toBeFalse()
            });

            it('No es un número', () => {
                expect(() => juego.PruebaCon('otra cosa')).toThrow()
                expect(() => juego.PruebaCon('100$')).toThrow()
            });

        });
    });

    describe('Ejercicio 3: Crear una función que devuelva un array con el numero de elementos indicado, inicializados al valor suministrado.', () => {
        [
            { elementos: 10, valor: '' },
            { elementos: 5, valor: 0 },
            { elementos: 0, valor: true },
        ].forEach(caso => {
            it(`${caso.elementos} elementos con valor ${caso.valor}`, () => {
                let rslt = dameArray(caso.elementos, caso.valor)

                expect(rslt.length).toBe(caso.elementos)
                for (let i = 0; i < caso.elementos; i++)
                    expect(rslt[i]).toBe(caso.valor)
            })
        });

        it('Solo número de elementos', () => {
            const numElementos = 7;
            let rslt = dameArray(numElementos)

            expect(rslt.length).toBe(numElementos)
            for (let i = 0; i < numElementos; i++)
                expect(rslt[i]).toBe("")

        });

        it('Con argumentos variables', () => {
            const numElementos = 7;
            let rslt = dameArray(numElementos, true, 1, 2)

            expect(rslt.length).toBe(numElementos)
            expect(rslt[0]).toBe(1)
            expect(rslt[1]).toBe(2)
            for (let i = 2; i < numElementos; i++)
                expect(rslt[i]).toBe(true)
        });
    });

    describe('Ejercicio 4: Crear una función que devuelva un determinado número de números primos.', () => {
        it('Números primos del 1 al 100', () => {
            const primos = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97];
            const numElementos = primos.length;
            let rslt = damePrimos(numElementos)
            
            expect(rslt.length).toBe(numElementos)
            for (let i = 0; i < numElementos; i++)
                expect(rslt[i]).toBe(primos[i])
        });

        const NUM_PRIMOS = 1000
        function evalua(primos, tipo) {
            let rslt = 0, count = 0;
            for (let p of primos) {
                // console.log(p)
                count++;
                rslt += p;
            }
            console.log(`Suma ${tipo}-> sum: ${rslt} count: ${count}`)

            expect(count).toBe(NUM_PRIMOS)
        }
        it('Rendimiento Iterator', () => {
            let iterador = primosIterator(NUM_PRIMOS);

            evalua(iterador, 'Iterator')
            evalua(iterador, 'Iterator')
        })

        it('Rendimiento Generator', () => {
            evalua(primosGenerator(NUM_PRIMOS), 'Generator')
        })
    });

    describe('Ejercicio 6: Crear una función que valide un NIF.', () => {
        describe('NIF OK', () => {
            ['12345678z', '12345678Z', '1234S', '4g'].forEach(caso => {
                it(`NIF: ${caso}`, () => expect(esNIF(caso)).toBeTrue())
            })
        });
    
        describe('NIF KO', () => {
            ['1234J', '12345678', 'Z', 'Z12345678'].forEach(caso => {
                it(`NIF: ${caso}`, () => expect(esNIF(caso)).toBeFalse())
            })
        });
    });

    describe('Ejercicio 7: Palíndromos.', () => {
        ['ana', 'reconocer', 'La ruta nos aporto otro paso natural', 'SOMOS O NO SOMOS',
            'Dábale arroz a la zorra el abad', 'áéíóúüuuoiea', 'a¿¡+-^[]*!?\'"a',
            'No subas, abusón', '¿Será lodo o dólares?'].forEach(caso => {
                it(`Palíndromo valido: '${caso}'`, () => {
                    expect(esPalindromo(caso)).toBeTrue()
                })
            });

        [1, 'no lo es', '', null, undefined, '    '].forEach(caso => {
            it(`Palíndromo invalido: '${caso}'`, () => {
                expect(esPalindromo(caso)).toBeFalse()
            })
        });
    });

})

describe('Ejemplos Asincronos', () => {
    it('async fetch', async () => {
        const page = 1
        try {
            const response = await fetch(`https://picsum.photos/v2/list?page=${page}&limit=10`);
            if (response.ok) {
                const lst = await response.json();
                expect(10).toBe(lst.length);
            } else {
                fail('Error ' + response.status + ': ' + response.statusText)
            }
        } catch (error) {
            fail('Reject la promesa')
        }
    })
    
    it('asincrono fetch', done => {
        const page = 1
        fetch(`https://picsum.photos/v2/list?page=${page}&limit=10`).then(response => {
            if (response.ok) {
                response.json().then(lst => {
                    expect(10).toBe(lst.length);
                    done();
                }
                ).catch(() => done.fail());
            } else {
                done.fail()
            }
        }).catch(() => done.fail());

    })
})
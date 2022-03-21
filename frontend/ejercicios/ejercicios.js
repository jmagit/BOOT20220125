function Ejercicio0() {
    let cad = prompt("Dame el nombre")
    alert(`La cadena es ${cad}`)
}
function dameValores() {
    return [1, 2, 3];
}

function aleatorio(inicio, fin) {
    if (!inicio || isNaN(parseInt(inicio.toString()))) throw new Error('Falta el valor inicial')
    if (!fin || isNaN(parseInt(fin.toString()))) throw new Error('Falta el valor final')
    if (inicio >= fin) throw new Error('El valor final debe ser mayor que el valor inicial')

    fin = fin - inicio + 1;
    return Math.floor(Math.random() * fin) + inicio;
}

function JuegoDelNumero() {
    var numeroBuscado = aleatorio(1, 100);
    var numeroIntroducido;
    var intentos = 0;
    var encontrado = false; 
    do {
        intentos += 1;
        // while (true) {
            numeroIntroducido = +prompt(`Dame tu numero (${intentos} de 10) [${numeroBuscado}]:`);
        //     // numeroIntroducido = +prompt(`Dame tu numero (${intentos} de 10):`);
        //     if (!isNaN(numeroIntroducido)) break;
        //     alert('No es un número')
        // }
        if (numeroBuscado === numeroIntroducido) {
            encontrado = true;
            break;
        } else if (numeroBuscado > numeroIntroducido) {
            alert('Mi número es mayor.');
        } else {
            alert('Mi número es menor.');
        }
    } while (intentos < 10);
    if (encontrado) {
        alert('Bieeen!!! Acertaste.');
    } else {
        alert('Upsss! Se acabaron los intentos, el número era el ' + numeroBuscado);
    }
    return encontrado;
}

function dameArray(numeroElementos, valorPorDefecto = "", ...resto) {
    var resultado = [];
    for (var ind = 0; ind < numeroElementos; ind++) {
        if (resto.length > ind)
            resultado[ind] = resto[ind];
        else
            resultado[ind] = valorPorDefecto;
    }
    return resultado;
}

function damePrimos(num) {
    var calculos = 0;
    var cuantos = +num;
    var resultado = [];
    var candidato = 2;
    while (cuantos) {
        while (true) {
            var esPrimo = true;
            for (var indice in resultado) {
                calculos++;
                if (candidato % resultado[indice] == 0) {
                    esPrimo = false;
                    break;
                }
            }

            candidato++;
            if (esPrimo) {
                resultado.push(candidato - 1);
                break;
            }
        }
        cuantos--;
    }
    console.log('Coste función: ' + calculos);
    return resultado;
}

function* primosGenerator(num) {
    var calculos = 0;
    var cuantos = +num;
    var resultado = [];
    var candidato = 1;
    while (true) {
        var esPrimo = true;
        candidato++;
        for (var indice in resultado) {
            calculos++;
            if (candidato % resultado[indice] == 0) {
                esPrimo = false;
                break;
            }
        }
        if (esPrimo) {
            resultado.push(candidato)
            if (cuantos-- == 0) {
                console.log(`Coste Generator: ${new Intl.NumberFormat('es-ES').format(calculos)}`)
                return candidato
            }
            yield candidato;
        }
    }
}
function damePrimosGenerator(num) {
    var resultado = [];
    for (let p of primosGenerator(num)) {
        if(p > 100) break;
        resultado.push(p)
    }
    return resultado;
}

function primosIterator(num) {
    return {
        resultado: [],
        next: function () {
            if (this.limite <= this.actual) {
                this.calculos++;
                console.log(`Coste Iterator: ${new Intl.NumberFormat('es-ES').format(this.calculos)}`)
                return { done: true }
            }
            while (true) {
                var esPrimo = true;
                this.candidato++;
                if (this.actual < this.resultado.length) {
                    this.calculos++;
                    this.candidato = this.resultado[this.actual++]
                    return { done: false, value: this.candidato }
                }
                for (var indice in this.resultado) {
                    this.calculos++;
                    if (this.candidato % this.resultado[indice] == 0) {
                        esPrimo = false;
                        break;
                    }
                }
                if (esPrimo) {
                    this.resultado.push(this.candidato);
                    this.actual++;
                    return { done: false, value: this.candidato }
                }
            }
        },
        [Symbol.iterator]: function () {
            this.calculos = 0
            this.actual = 0;
            this.limite = num;
            this.candidato = 1
            return this;
        }
    }
}

function damePrimosIterator(num) {
    var resultado = [];
    for (let p of primosIterator(num)) {
        if(p > 100) break;
        resultado.push(p)
    }
    return resultado;
}

function esNIF(nif) {
    if (!/^\d{1,8}[A-Za-z]$/.test(nif))
        return false;
    const letterValue = nif.substr(nif.length - 1);
    const numberValue = nif.substr(0, nif.length - 1);
    return letterValue.toUpperCase() === 'TRWAGMYFPDXBNJZSQVHLCKE'.charAt(numberValue % 23);
}

function esMayusculas(valor) {
    if(!valor) return true;
    return valor.toString().toUpperCase() == valor;
}

function esPalindromo(cadena) {
    if (typeof (cadena) != "string" || cadena.trim().length == 0) return false;
    cadena = cadena.replace(/[ .,;:_¿?¡!()[\]{}=+\-*/_`~$%^&'"]/g, '').normalize("NFD").replace(/[\u0300-\u036f]/g, '').toLowerCase();
    for (let i = 0; i < cadena.length - i; i++) {
        if (cadena.charAt(i) !== cadena.charAt(cadena.length - 1 - i)) return false;
    }
    return true;
}

function Juego(maxIntentos, valores) {
    var numeroBuscado;
    this.Inicializa = function () {
        numeroBuscado = aleatorio(1, valores);
        // debugger;
        console.debug(`Número generado: ${numeroBuscado}`)
        this.intentos = 0;
        this.encontrado = false;
        this.mensaje = 'Listo para jugar';
    }

    this.Inicializa();

    this.PruebaCon = function (numeroIntroducido) {
        if (this.intentos >= maxIntentos)
            throw new Error("Excedido el numero de intentos");
        if (!Number.isInteger(+numeroIntroducido))
            throw new Error("No es un numero correcto");
        this.intentos += 1;
        if (numeroBuscado == numeroIntroducido) {
            this.encontrado = true;
            this.mensaje = 'Bieeen!!! Acertaste.';
            return this.mensaje;
        }
        if (this.intentos >= maxIntentos) {
            this.mensaje = 'Upsss! Se acabaron los intentos, el número era el ' + numeroBuscado;
            return this.mensaje;
        }
        if (numeroBuscado > numeroIntroducido) {
            this.mensaje = 'Mi número es mayor.';
            return this.mensaje;
        }
        this.mensaje = 'Mi número es menor.';
        return this.mensaje;
    }
    this.DameMaxIntentos = function () { return maxIntentos; }
}

//Juego.prototype.DameMaxIntentos = function() { return maxIntentos; }
Juego.prototype.DameIntento = function () { return this.intentos + 1; }

class JuegoConClase {
    constructor(maxIntentos, valores) {
        this._maxIntentos = maxIntentos;
        this._valores = valores;
        this.Inicializa();
    }
    Inicializa() {
        this._numeroBuscado = aleatorio(1, this._valores);
        this.intentos = 0;
        this.encontrado = false;
        this.mensaje = 'Listo para jugar';
    }
    PruebaCon(numeroIntroducido) {
        if (this.intentos >= this._maxIntentos)
            throw new Error("Excedido el numero de intentos");
        if (!Number.isInteger(+numeroIntroducido))
            throw new Error("No es un numero correcto");
        this.intentos += 1;
        if (this._numeroBuscado == numeroIntroducido) {
            this.encontrado = true;
            this.mensaje = 'Bieeen!!! Acertaste.';
            return this.mensaje;
        }
        if (this.intentos >= this._maxIntentos) {
            this.mensaje = 'Upsss! Se acabaron los intentos, el número era el ' + this._numeroBuscado;
            return this.mensaje;
        }
        if (this._numeroBuscado > numeroIntroducido) {
            this.mensaje = 'Mi número es mayor.';
            return this.mensaje;
        }
        this.mensaje = 'Mi número es menor.';
        return this.mensaje;
    }

    DameMaxIntentos() { return this._maxIntentos; }

    get maxIntentos() { return this._maxIntentos; }

    get intento() { return this.intentos + 1; }
}
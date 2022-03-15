import {pinta} from './comun.js';

let msg = "soy la de uno"

function choca() {
    pinta(msg)
}

function suma(a, b) {
    return parseFloat(a) + parseFloat(b)
}

export { choca as miChoca, suma }

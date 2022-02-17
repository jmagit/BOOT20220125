# BootCamp 5

## Back End

### Ejercicios de Iniciación a la Programación Java

- Juego de “Adivina el número que estoy pensando”, un número del 0 al 100, ya te diré si es mayor o menor que el mío, pero tienes 10 intentos como mucho.

        Scanner teclado = new Scanner(System.in); cad = teclado.nextLine();  
        num = Integer.parseInt(cad);
        var rnd = new Random(); num = rnd.nextInt(10);  

- *Opcional:* Decodificar las cadenas con el siguiente formato:  

        3+4+3,4-7*1=

    en los siguientes componentes:  

        3 +  
        4 +  
        3,4 -  
        7 *  
        1 =  
    mostrando el resultado en la consola.

- Convertir el juego “Adivina el número que estoy pensando” en la clase JuegoDelNumero sin interfaz de usuario. Debe contar con los métodos inicializar y jugada, así como informar del número de intentos y el resultado de la última jugada. No debe dejar continuar si el juego ha terminado. Para probar el juego se debe implementar el interfaz de usuario.

- *Opcional:* Crear la clase Calculadora que acumule y permita obtener los resultados parciales de las operaciones obtenidas en la decodificación anterior.

- Crear las clases para implementar juegos que utilizan una baraja de naipes. Los naipes tienen dos propiedades distintivas: valor y palo. Probar la funcionalidad de barajar, repartir, comparar, … (Utilizar Enumeraciones, genéricos, colecciones …)

- Ficheros: Leer un fichero de entrada y generar un fichero de salida, realizando los cálculos necesarios, con los siguientes formatos:

    **FileIN: Entrada.txt**

        3+4+3,4-7*1=

    **FileOUT: Salida.txt**

        3  
        + 4  
        + 3,4  
        - 7  
        * 1  
        ----------  
        3,400000

  - *Ampliaciones (ficheros):*

    + Fichero de entrada con múltiples líneas.
    + Fichero de salida comprimido
    + Descompresor del fichero de salida: zip -> txt
    + Compresor del fichero de entrada: txt -> zip
    + Procesar entrada/salida comprimida

#### Ejercicios de refuerzo

- Iniciación:
    1. <http://puntocomnoesunlenguaje.blogspot.com/p/ejercicios.html>
    2. <https://tutobasico.com/basicos-java/>
    3. <https://tutobasico.com/basicos2-java/>
    4. <https://www.discoduroderoer.es/ejercicios-propuestos-y-resueltos-basicos-java/>
    5. <https://www.discoduroderoer.es/ejercicios-propuestos-y-resueltos-metodos-y-funciones-de-java/>
- Intermedio:
    1. <https://tutobasico.com/basicos3-java/>
    2. <http://ejerciciosresueltosprogramacion.blogspot.com/>
    3. <https://www.discoduroderoer.es/ejercicios-propuestos-y-resueltos-programacion-orientado-a-objetos-java/>

### Ejercicios de bases de datos

- Obtener todos los actores de nombre ‘NICK’.
- Obtener una lista con todos los distritos distintos (district en address).
- Obtener las películas sobre agentes secretos (description contiene ‘Secret Agent’).
- Mostrar la lista de las películas mas caras (coste por minuto)
- Obtener los códigos y medias de gasto de los clientes que han gastado mas de 100 en menos de 25 operaciones.
- Obtener los 5 nombres de actor más repetidos (aprox).
- Para felicitar el año nuevo chino se necesita el listado con los datos postales de los clientes residentes en China y Taiwan
- Mostrar los datos de las tiendas, conocidas por la ciudad y país donde están ubicadas, indicando el nombre del gerente, el numero de películas en inventario, el numero de títulos diferentes y el número de clientes atendidos.
- Obtener el listado detallado de alquileres indicando el identificador de alquiler, el titulo alquilado, las fechas de alquiler, devolución y tiempo transcurrido, nombres del cliente (nombre con apellidos), empleado (nombre con apellidos) y tienda (ciudad, país).
- Generar la lista diaria de alquileres vencidos no devueltos para poder llamar a los clientes y pedirles que los devuelvan, para ello debe mostrar las fechas de alquiler y vencimiento, el teléfono y nombre del cliente, así como el titulo de la película, priorizando los que mas tiempo llevan vencidos.
- Elaborar el ranking de los países que más alquilan las películas de GINA DEGENERES.

#### Ejercicios de refuerzo

- Iniciación
    1. https://www.discoduroderoer.es/ejercicios-propuestos-y-resueltos-sakila-mysql/

## Front End

## Proyecto

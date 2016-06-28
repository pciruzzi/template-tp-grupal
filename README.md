# TP Grupal - Tercera Entrega
![Build Status](https://travis-ci.org/pciruzzi/template-tp-grupal.svg)

## Uso

Para el uso del TP, los pasos a seguir son:
 1. Ejecutar el comando `gradlew build`, que se encargará de compilar, generar los archivos _.jars_ necesarios y correr tests y chequeos (CPD, CheckStyle, FindBugs, etc.).
 2. Abrir una terminal en la raíz del proyecto y ejecutar `./run.sh --server` para levantar un servidor.
 3. Dentro del servidor, ejecutar comandos del tipo `load game ar/fiuba/tdd/tp/model/XXXConfiguration.jar`, donde XXX será el nombre de algún juego.
 4. Abrir otras terminales en la raíz del proyecto y ejecutar `./run.sh --client` para levantar distintos clientes. Para conectarse al juego deseado, se debe saber en qué puerto fue creado dentro del servidor.

## Enunciado ##

Se pide agregar las siguientes funcionalidades al motor de juegos:

 1. Multiplayer.

    Mas de un player se puede conectar al juego, y comparten la misma instancia del juego.
Si un jugador hace algo, la respuesta del comando se le notifica a todos los players para que se enteren de la acción realizada. La respuesta del comando la ve solo quien la ejecuta.

    Ejemplo en los clientes:


| cliente 1                               | cliente 2                             |
| ----------------------------------------|---------------------------------------|
| ` > connect 127.0.0.1:8081 [Enter]`   | `> connect 127.0.0.1:8081 [Enter]`  |
| `> Welcome to game1, you are player 1!`   | `> Welcome to game1, you are player 2!` |
| `> Player 1 join the game`                |                                       |
| `> look around [Enter]`                 |                                       |
| `> There’s a key and a door in the room.` | `> Player 1 execute: look around`        |
|                                         | `> pick key [Enter]`                  |
| `> Player 2 execute: pick key`              | `> There you go!`                       |
| `> pick key [Enter]`                    |                                       |
| `> I’m sorry, no key in the room`          |                                       |
| `> open door [Enter]`                   |                                       |
| `> Ey! Where do you go?! Room 2 is locked.`|                                      |
|                                          | `> open door`                          |
| `> Player 2 execute: open door`            | `> You enter room 2. You won the game!`|
| `> Player 2 has WIN. The game is over.`    | `> bye!`                               |
| `> Player 2 has exited`                    |                                      |
| `> exit game [Enter]`                      |                                      |
| `> bye!`                                |                                      |

   Cada juego podrá configurarse si es multiplayer o no. Si es multiplayer podrá configurarse la condición de win y lost de cada uno, logrando que cada jugador tenga una misión distinta en el juego. Si se indica solo 1, se aplica la misma a todos los players.
   No nos lo piden ahora pero nos dicen que a futuro se podría querer configurar condiciones de win y lost para un tipo de player, y que el player elija que quiere ser al loguearse, por ejemplo el player 1 se suma como policía, y el player 2 como ladrón al igual que el player 3, y los policías tienen ciertos objetivos y condiciones de Win y Lost, distintas que los ladrones.

 2. Agregar al motor el concepto de **"paso del tiempo"**, y que ciertas acciones se ejecuten automáticamente en cierto momento, por ejemplo:
    - Cada 6 minutos el reloj se abre y sale el pajaro cucu sonando una campana.
    - El bibliotecario se despierta a los 10 minutos de haberse dormido.
    - A los 30 minutos de comenzado el juego se larga a llover.
    - A romper una ventana el perro se despierta y ladra durante los siguientes 10 minutos
    - Se pierde el juego si no se sale de la habitación antes de los 30 minutos de comenzado el juego o de haber entrado a la habitación por ejemplo.
    - Que cada 5 minutos el bibliotecario o una araña cambie su posición a otra habitación de forma **random** de las que están conectadas a la suya.

    El resultado de la acción se notifica al player o a todos los players:
     - Pasaron 10 minutos y el bibliotecario se despertó:
     - Notificacion: “El bibliotecario se ha desperado, ten cuidado si te encuentra!!”

3. Concepto **Random**, algunas condiciones pueden basarse en un factor random para ver si se cumplen o no, para darle más incertidumbre al juego. Por ejemplo:
     - Cada 5 minutos el humor del portero cambia en forma random entre buen humor o mal humor.
     Si le pido una llave al portero si está de buen humor me la da sino no.
     - Cada 5 minutos la araña cambia su posición a otra habitación de las conectadas a la habitación actual, elige en forma random a cual ir si hay mas de una posible elección.


4. Actividad para validar en clase: "EL ESCAPE 2"

Se pide modificar el juego del escape, para incorporar los siguientes escenarios. Subirlo a un branch llamado ejercicio-it3 y llevarlo a la entrega 3, para validarlo con los ayudantes. El mismo se usara para validar los nuevos requerimientos. 

"EL ESCAPE 2"
Pueden ingresar hasta 4 jugadores.
El objetivo del Juego es que el primer personaje que salga del edificio donde se encuentra gana. 
El juego se basa en el mismo del escape pero con los siguientes cambios:

- El bibliotecario se despierta a los 2 minutos de haberse dormido.
- Una vez despierto, cada 4 minutos el bibliotecario cambia su posición a otra habitación (elije de forma random entre las que están conectadas a la suya). Si cuando un jugador quiere ingresar a la biblioteca no está el bibliotecario entonces el jugador puede ingresar directamente.
- Si el jugador ingresó a la biblioteca sin haberse autenticado con la credencial correcta, si el bibliotecario se cruza en alguna habitación con el jugador, el jugador pierde.




>**Los requerimientos pueden (y van a) cambiar en cualquier momento.**

## Condiciones ##
 - Para las consultas generales se va a utilizar el channel `#tp-grupal-channel`.
 - Cada grupo va a tener su channel para consultas particulares y comunicación con su ayudante.
 - Se debe usar el mismos toolset que para el TP0 (java 8, IntelliJ, gradle, pmd, cpd, checkstyle, findbugs).
 - Se supone que todos están al tanto de las notificaciones en Slack. Esto incluye cambios de alcance, cambios en los requerimientos, restricciones adicionales, etc.
 - Se podrán modificar la configuración del toolset sólo bajo aprobación de los ayudantes (`#quejas-tooling`).
 - Cada grupo debe crear un repositorio en GitHub y agregar a los ayudantes de la materia al mismo.
 - Se debe integrar el repositorio con Travis-CI.
 - La documentación se realizará en la misma Wiki del repositorio en GitHub.
 - En la fecha de entrega se realizará una demo del TP a uno o dos ayudantes. Esto implica que el TP se debe poder utilizar dentro de las restricciones de la entrega.
Durante la demo y posterior corrección se cargarán issues en GitHub que deben estar solucionados para la siguiente entrega.
 - **No se aceptarán TPs:**
   - **con warnings.**
   - **que no compilen en Travis-CI.**
   - **con issues abiertos.**
   - **Que no se puedan utilizar**
   - **Que no tengan documentación de uso del sistema**
   - **Que no tengan documentación del diseño del sistema**

 - **No hay re-entrega.** Es responsabilidad del grupo realizar las consultas durante las 2-3 semanas disponibles para realizar el TP.
 - Si un alumno no puede concurrir a la demo, se aceptará la entrega pero dicho alumno figurará como ausente. Esto puede repercutir en la nota final. El alumno debe avisar con anticipación que no estará presente y podrá ser evaluado posteriormente con preguntas sobre la entrega.


## Calendario tentativo ##

| Fecha       |                  |
|-----------  | -----------------|
| 26 de Mayo  | Publicación 3ra Entrega |
| 2 de junio  |  |
| 9 de Junio  | **Tercera Entrega** |
| 16 de Junio | Revisión |
| 23 de Junio | Cierre de notas |


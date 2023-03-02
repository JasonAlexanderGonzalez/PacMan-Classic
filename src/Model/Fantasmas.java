package Model;

import View.GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;

/**
 * Clase que cumple la funcion de crear fantasmas y que estos mismos se muevan
 * solos.
 *
 * @author Tristan Lopez-Jason
 */
public class Fantasmas {

    /**
     * Atributos de clase para para darle funcionalidad a los movimientos de los
     * fantasmas.
     */
    private int fantasmaPosX;
    private int fantasmaPosY;
    private Timer timer;
    private Random aleatorio;
    private int direccion;
    private GUI gui;

    /**
     * Constructor de la clase. Este cumple la funcion de tomar un numero
     * aleatorio entre el 0 y 4 (0 es a la izquierda, 1 es a la derecha, 2 es
     * arriba y 3 es abajo), esto para determinar la direccion en la que se vaya
     * a mover el fantasma. Asi mismo con los parametros se establece la
     * posicion en la que va a estar el fantasma.
     *
     * @param posX posicion en filas del fantasma dentro del tablero
     * @param posY posicion en columnas del fantasma dentro del tablero
     */
    public Fantasmas(int posX, int posY) {
        this.aleatorio = new Random();
        this.fantasmaPosX = posX;
        this.fantasmaPosY = posY;
        Tablero.matriz[fantasmaPosX][fantasmaPosY] = 7;
        this.direccion = aleatorio.nextInt(4);
        this.movimientoFantasmas();
    }

    /**
     * @return Devuelve el atributo de clase timer.
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * Metodo que tiene el hilo para que los fantasmas se mueven de forma
     * simultanea donde primero se valida el numero aletorio de la direccion y
     * de esta forma llamar a los respectivos metodos de movimiento.
     */
    public void movimientoFantasmas() {

        timer = new Timer(200, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // izquierda
                if (fantasmaPosX > 0 && direccion == 0) {
                    izquierda();
                }
                //derecha                    izquierda();

                if (direccion == 1) {
                    derecha();
                }
                //arriba
                if (fantasmaPosY > 0 && direccion == 2) {
                    arriba();
                }
                //abajo
                if (direccion == 3) {
                    abajo();
                }
            }
        });
        timer.start();
    }

    /**
     * Metodo donde primero se valida que la posicion en la que este el fantasma
     * no sea un muro y que sea un espacio vacio o que sea un espacio donde se
     * encuentre una pastilla, de esta forma se va a restar en 1 la posicion en
     * x, esto para que se mueva a la izquierda, luego se inicializa la posicion
     * del fantasma y se pinta la matriz. Caso contrario, si el fantasma choca
     * con una pared o con otro fantasma se escoge una direccion al azar.
     */
    public void izquierda() {
        System.out.println("aqui " + direccion);
        //camina
        if (Tablero.matriz[fantasmaPosX - 1][fantasmaPosY] != 2
                && (Tablero.matriz[fantasmaPosX - 1][fantasmaPosY] == 0
                || Tablero.matriz[fantasmaPosX - 1][fantasmaPosY] == 1)) {
            Tablero.matriz[fantasmaPosX][fantasmaPosY] = Tablero.matAux[fantasmaPosX][fantasmaPosY];
            fantasmaPosX -= 1;
            Tablero.matriz[fantasmaPosX][fantasmaPosY] = 7;
            GUI.pintarMatriz();
        } else //choca con la pared
        if (fantasmaPosX > 0 && Tablero.matriz[fantasmaPosX - 1][fantasmaPosY] == 2) {
            direccion = aleatorio.nextInt(4);
        } else //choca con otro fantasm
        if (Tablero.matriz[fantasmaPosX - 1][fantasmaPosY] == 7) {
            direccion = aleatorio.nextInt(4);
        }
    }

    /**
     * Metodo donde primero se valida que la posicion en la que este el fantasma
     * no sea un muro y que sea un espacio vacio o que sea un espacio donde se
     * encuentre una pastilla, de esta forma se va a sumar en 1 la posicion en
     * x, esto para que se mueva a la derecha, luego se inicializa la posicion
     * del fantasma y se pinta la matriz. Caso contrario, si el fantasma choca
     * con una pared o con otro fantasma se escoge una direccion al azar.
     */
    public void derecha() {
        System.out.println("aqui" + direccion);
        if (Tablero.matriz[fantasmaPosX + 1][fantasmaPosY] != 2
                && (Tablero.matriz[fantasmaPosX + 1][fantasmaPosY] == 0
                || Tablero.matriz[fantasmaPosX + 1][fantasmaPosY] == 1)) {
            Tablero.matriz[fantasmaPosX][fantasmaPosY] = Tablero.matAux[fantasmaPosX][fantasmaPosY];
            fantasmaPosX += 1;
            Tablero.matriz[fantasmaPosX][fantasmaPosY] = 7;
            GUI.pintarMatriz();
        } else if (fantasmaPosX < 21 && Tablero.matriz[fantasmaPosX + 1][fantasmaPosY] == 2) {
            direccion = aleatorio.nextInt(4);
        } else if (Tablero.matriz[fantasmaPosX + 1][fantasmaPosY] == 7) {
            direccion = aleatorio.nextInt(4);
        }
    }

    /**
     * Metodo donde primero se valida que la posicion en la que este el fantasma
     * no sea un muro y que sea un espacio vacio o que sea un espacio donde se
     * encuentre una pastilla, de esta forma se va a restar en 1 la posicion en
     * y, esto para que se mueva hacia arriba, luego se inicializa la posicion
     * del fantasma y se pinta la matriz. Caso contrario, si el fantasma choca
     * con una pared o con otro fantasma se escoge una direccion al azar.
     */
    public void arriba() {
        System.out.println("aqui" + direccion);
        if (Tablero.matriz[fantasmaPosX][fantasmaPosY - 1] != 2
                && (Tablero.matriz[fantasmaPosX][fantasmaPosY - 1] == 0
                || Tablero.matriz[fantasmaPosX][fantasmaPosY - 1] == 1)) {
            Tablero.matriz[fantasmaPosX][fantasmaPosY] = Tablero.matAux[fantasmaPosX][fantasmaPosY];
            fantasmaPosY -= 1;
            Tablero.matriz[fantasmaPosX][fantasmaPosY] = 7;
            GUI.pintarMatriz();
        } else if (fantasmaPosY > 0 && Tablero.matriz[fantasmaPosX][fantasmaPosY - 1] == 2) {
            direccion = aleatorio.nextInt(4);
        } else if (Tablero.matriz[fantasmaPosX][fantasmaPosY - 1] == 7) {
            direccion = aleatorio.nextInt(4);
        }
    }

    /**
     * Metodo donde primero se valida que la posicion en la que este el fantasma
     * no sea un muro y que sea un espacio vacio o que sea un espacio donde se
     * encuentre una pastilla, de esta forma se va a sumar en 1 la posicion en
     * y, esto para que se mueva hacia arriba, luego se inicializa la posicion
     * del fantasma y se pinta la matriz. Caso contrario, si el fantasma choca
     * con una pared o con otro fantasma se escoge una direccion al azar.
     */
    public void abajo() {
        System.out.println("aqui" + direccion);
        if (Tablero.matriz[fantasmaPosX][fantasmaPosY + 1] != 2
                && (Tablero.matriz[fantasmaPosX][fantasmaPosY + 1] == 0
                || Tablero.matriz[fantasmaPosX][fantasmaPosY + 1] == 1)) {
            Tablero.matriz[fantasmaPosX][fantasmaPosY] = Tablero.matAux[fantasmaPosX][fantasmaPosY];
            fantasmaPosY += 1;
            Tablero.matriz[fantasmaPosX][fantasmaPosY] = 7;
            GUI.pintarMatriz();
        } else if (fantasmaPosY < 21 && Tablero.matriz[fantasmaPosX][fantasmaPosY + 1] == 2) {
            direccion = aleatorio.nextInt(4);
        } else if (Tablero.matriz[fantasmaPosX][fantasmaPosY + 1] == 7) {
            direccion = aleatorio.nextInt(4);
        }
    }

}

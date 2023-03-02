package View;

import Model.Fantasmas;
import Model.Tablero;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

/**
 * Interfaz grafica del juego pac-man
 *
 * @author Tristan Lopez-Jason
 */
public class GUI {

    /**
     * variables para la creacion de la interfaz grafica
     */
    private JFrame ventana;

    //presentacion
    private JPanel panelPresentacion;
    private JButton iniciar;
    private JLabel fondoPresentacion;
    private ImageIcon imagenFondoPres;

    //menu
    private JPanel panelMenu;
    private JButton botones[];
    private JLabel fondoMenu;
    private ImageIcon imagenFondoMenu;

    //juego
    static JPanel panelJuego;
    private JLabel fondoJuego;
    private ImageIcon imagenFondoJuego;
    static JLabel[][] matrizLabel;
    private int px;
    private int py;
    private String jugador;
    private JLabel nombre;
    private int puntos;
    private JLabel puntaje;
    private int abajo;
    private int arriba;
    private int izquierda;
    private int derecha;
    public Timer timer;

    //fantasmas
    Fantasmas fantasma1;
    Fantasmas fantasma2;
    Fantasmas fantasma3;

    /**
     * Metodo constructor de la clase, donde se crea el frame, los paneles de
     * presentacion, y se inicializa la matriz del juego
     */
    public GUI() {

        ventana = new JFrame("JUEGO PAC-MAN");
        ventana.setSize(1200, 1200);
        ventana.setLayout(null);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelPresentacion = new JPanel();
        panelPresentacion.setLayout(null);
        panelPresentacion.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
        panelPresentacion.setVisible(true);
        panelPresentacion.setBackground(Color.red);

        iniciar = new JButton("Iniciar");
        iniciar.setBounds(ventana.getWidth() - 120, 20, 100, 30);
        iniciar.setVisible(true);
        iniciar.setBackground(Color.white);
        panelPresentacion.add(iniciar, 0);

        fondoPresentacion = new JLabel();
        fondoPresentacion.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
        imagenFondoPres = new ImageIcon("imagenes/tttt.png");
        imagenFondoPres = new ImageIcon(imagenFondoPres.getImage().getScaledInstance(ventana.getWidth(), ventana.getHeight(), Image.SCALE_DEFAULT));
        fondoPresentacion.setIcon(imagenFondoPres);
        fondoPresentacion.setVisible(true);
        panelPresentacion.add(fondoPresentacion, 0);

        //menu
        botones = new JButton[2];
        for (int i = 0; i < botones.length; i++) {
            botones[i] = new JButton();
        }

        //juego
        inicializar();
        px = 1;
        py = 1;
        Tablero.matriz[px][py] = 3;

        abajo = 0;
        arriba = 0;
        izquierda = 0;
        derecha = 0;

        ventana.add(panelPresentacion);

        ventana.setVisible(true);

    }

    /**
     * Metodo para poder usarlo en la controladora
     *
     * @return devuelve el boton iniciar
     */
    public JButton getIniciar() {
        return iniciar;
    }

    /**
     * Metodo principal, ya que en este se crea el panel del juego donde se va
     * ver la matriz, el nombre del jugador, el puntaje y donde se establece las
     * posiciones de los fantasmas
     */
    public void jugar() {

        panelMenu.setVisible(false);
        panelJuego = new JPanel();
        panelJuego.setLayout(null);
        panelJuego.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
        panelJuego.setVisible(true);

        fondoJuego = new JLabel();
        fondoJuego.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
//        imagenFondoJuego = new ImageIcon("imagenes/fondoJugar.png");
        imagenFondoJuego = new ImageIcon("imagenes/aaa.png");
        imagenFondoJuego = new ImageIcon(imagenFondoMenu.getImage().getScaledInstance(ventana.getWidth(), ventana.getHeight(), Image.SCALE_DEFAULT));
        fondoJuego.setIcon(imagenFondoJuego);
        fondoJuego.setVisible(true);
        panelJuego.add(fondoJuego, 0);

        pintarMatriz();
        nombre = new JLabel("JUGADOR: " + jugador);
        nombre.setBounds(20, 20, 150, 30);
        nombre.setForeground(Color.white);
        nombre.setVisible(true);
        panelJuego.add(nombre, 0);

        puntos = 0;
        puntaje = new JLabel("Puntos: " + puntos);
        puntaje.setBounds(ventana.getWidth() - (150 + 20), 20, 150, 30);
        puntaje.setVisible(true);
        puntaje.setForeground(Color.white);
        panelJuego.add(puntaje, 0);
        mover();
        fantasma1 = new Fantasmas(9, 10);
        fantasma2 = new Fantasmas(10, 10);
        fantasma3 = new Fantasmas(10, 11);
        ventana.add(panelJuego);

    }

    /**
     * Metodo que hace un recorrido por la matriz y va pintando cada campo con
     * imagenes y por ultimo se añade al panel de juego
     */
    public static void pintarMatriz() {
        for (int i = 0; i < Tablero.matriz.length; i++) {
            for (int j = 0; j < Tablero.matriz.length; j++) {
                if (Tablero.matriz[i][j] == 0) {
//                    System.out.println("camino");
                    matrizLabel[i][j].setIcon(new ImageIcon("imagenes/0.png"));
                }
                if (Tablero.matriz[i][j] == 1) {
//                    System.out.println("pastillas");
                    matrizLabel[i][j].setIcon(new ImageIcon("imagenes/1.png"));
                }
                if (Tablero.matriz[i][j] == 2) {
//                    System.out.println("muro");
                    matrizLabel[i][j].setIcon(new ImageIcon("imagenes/2.png"));
                }
                if (Tablero.matriz[i][j] == 3) {
//                    System.out.println("Pacma");
                    matrizLabel[i][j].setIcon(new ImageIcon("imagenes/3.png"));
                }
                if (Tablero.matriz[i][j] == 4) {
//                    System.out.println("fantasma");
                    matrizLabel[i][j].setIcon(new ImageIcon("imagenes/4.png"));
                }
                if (Tablero.matriz[i][j] == 5) {
//                    System.out.println("fantasma");
                    matrizLabel[i][j].setIcon(new ImageIcon("imagenes/5.png"));
                }
                if (Tablero.matriz[i][j] == 7) {
//                    System.out.println("fantasma");
                    matrizLabel[i][j].setIcon(new ImageIcon("imagenes/7.png"));
                }
                if (Tablero.matriz[i][j] == 8) {
//                    System.out.println("fantasma");
                    matrizLabel[i][j].setIcon(new ImageIcon("imagenes/8.png"));
                }
                matrizLabel[i][j].setBounds(280 + (i * 30), 20 + (j * 30), 30, 30);
                matrizLabel[i][j].setVisible(true);
                panelJuego.add(matrizLabel[i][j], 0);
            }
        }
    }

    /**
     * Metodo que inicializa las matrices del juego
     */
    public void inicializar() {
        Tablero.matriz = new int[22][22];
//        Tablero.matriz = tablero(1);
        Tablero.matriz = Tablero.tablero();
        matrizLabel = new JLabel[22][22];
        Tablero.matAux = new int[22][22];
        for (int i = 0; i < Tablero.matriz.length; i++) {
            for (int j = 0; j < Tablero.matriz.length; j++) {
                matrizLabel[i][j] = new JLabel();
                Tablero.matAux[i][j] = Tablero.matriz[i][j];
            }
        }
    }

    /**
     * Metodo que tiene el hilo, los eventos del teclado, cuando se gane el
     * juego y cuando se pierda el juego. Donde primero se establece la
     * validacion de la posicion del pac-man sea igual a una pastilla o un
     * espacio vacio, esto para que se pueda mover. Cuando se logre la cantidad
     * maxima de puntos el juego acabra con un ganador. Si en la posicion en la
     * que esta el pac-man hay un fantasma se procede a parar los hilos de los
     * fantasmas ya que el pac-man estaria muerto
     */
    public void mover() {

        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (derecha == 1 && (Tablero.matriz[px + 1][py] == 1 || Tablero.matriz[px + 1][py] == 0)) {
                    derecha();
                }
                if (izquierda == 1 && (Tablero.matriz[px - 1][py] == 1 || Tablero.matriz[px - 1][py] == 0)) {
                    izquierda();
                }
                if (arriba == 1 && (Tablero.matriz[px][py - 1] == 1 || Tablero.matriz[px][py - 1] == 0)) {
                    arriba();
                }
                if (abajo == 1 && (Tablero.matriz[px][py + 1] == 1 || Tablero.matriz[px][py + 1] == 0)) {
                    abajo();
                }
                if (puntos == 1485) {
                    timer.stop();
                    JOptionPane.showMessageDialog(ventana, "FELICITACIONES GANO");
                    panelJuego.setVisible(false);
                    System.exit(0);
//                    panelMenu.setVisible(true);
                }
                int vidas = 3;
                if (Tablero.matriz[px][py + 1] == 7 || Tablero.matriz[px][py - 1] == 7 || Tablero.matriz[px - 1][py] == 7 || Tablero.matriz[px + 1][py] == 7) {
                    fantasma1.getTimer().stop();
                    fantasma2.getTimer().stop();
                    fantasma3.getTimer().stop();
                    JOptionPane.showMessageDialog(ventana, "ESTAS MUERTO");
                    panelJuego.setVisible(false);
                    panelMenu.setVisible(true);
                    timer.stop();
                    System.exit(0);
                }
            }
        });
        timer.start();
        ventana.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_W) {
                    System.out.println("tecla hacia arriba");
                    irArriba();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    System.out.println("tecla hacia abajo");
                    irAbajo();
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    System.out.println("tecla hacia izquierda");
                    irIzquierda();

                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    System.out.println("tecla hacia derecha");
                    irDerecha();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

    }

    /**
     * Panel del menu del juego, el cual contendra el boton de inicio del juego
     * y el boton de salir
     */
    public void menu() {

        panelPresentacion.setVisible(false);
        panelMenu = new JPanel();
        panelMenu.setLayout(null);
        panelMenu.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
        panelMenu.setVisible(true);

        fondoMenu = new JLabel();
        fondoMenu.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
//        imagenFondoMenu = new ImageIcon("imagenes/fondoMenu.png");
        imagenFondoMenu = new ImageIcon("imagenes/aaa.png");
        imagenFondoMenu = new ImageIcon(imagenFondoMenu.getImage().getScaledInstance(ventana.getWidth(), ventana.getHeight(), Image.SCALE_DEFAULT));
        fondoMenu.setIcon(imagenFondoMenu);
        fondoMenu.setVisible(true);
        panelMenu.add(fondoMenu, 0);

        botones[0].setText("JUGAR");
        botones[1].setText("SALIR");

        for (int i = 0; i < botones.length; i++) {
            botones[i].setBounds(ventana.getWidth() - (200 + 50), (i + 1) * 50, 200, 40);
            botones[i].setVisible(true);
            botones[i].setBackground(Color.WHITE);
            panelMenu.add(botones[i], 0);
        }

        ventana.add(panelMenu);

    }

    /**
     * Escuchadores de los botones que tendra el menu del juego, donde el primer
     * boton dara inicio al juego y el segundo es para salir
     */
    public void eventoMenu() {

        //boton jugar
        botones[0].addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                System.out.println("jugar");
                //pedir nombre
                jugador = JOptionPane.showInputDialog(ventana, "Nombre del jugador", "Escribe aqui");
                while (jugador == null || jugador.compareTo("Escribe aqui") == 0 || jugador.compareTo("") == 0) {
                    jugador = JOptionPane.showInputDialog(ventana, "Debes ingresar usuario", "Escribe aqui");
                }
                jugar();

            }
        });

        //salir
        botones[1].addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                System.out.println("SALIR");
                System.exit(0);
            }
        });
    }

    /**
     * Metodo que primero valida si la posicion en la que esta el pac-man es una
     * pastilla, si es asi el puntaje va a incrementar en 5, y de esta forma la
     * posicion en la que estaba antes el pac-man se inicializa en 0 ya que es
     * un espacio vacio, aumenta en 1 la posicion en x, esto, para poder moverlo
     * hacia la derecha, ahora la nueva posicion en la que va a estar el pac-man
     * se inicializa en 3 y por ultimo se vuelve a pintar la matriz
     */
    public void derecha() {
        if (Tablero.matriz[px + 1][py] == 1) {
            puntos += 5;
            puntaje.setText("Puntos: " + puntos);
        }
        Tablero.matriz[px][py] = 0;
        Tablero.matAux[px][py] = Tablero.matriz[px][py]; //esto es nuevo
        px = px + 1;
        Tablero.matriz[px][py] = 3;
        GUI.pintarMatriz();
    }

    /**
     * Metodo que primero valida si la posicion en la que esta el pac-man es una
     * pastilla, si es asi el puntaje va a incrementar en 5, y de esta forma la
     * posicion en la que estaba antes el pac-man se inicializa en 0 ya que es
     * un espacio vacio, decrementa en 1 la posicion en x, esto, para poder
     * moverlo hacia la izquierda, ahora la nueva posicion en la que va a estar
     * el pac-man se inicializa en 3 y por ultimo se vuelve a pintar la matriz
     */
    public void izquierda() {
        if (Tablero.matriz[px - 1][py] == 1) {
            puntos += 5;
            puntaje.setText("Puntos: " + puntos);
        }
        Tablero.matriz[px][py] = 0;
        Tablero.matAux[px][py] = Tablero.matriz[px][py]; //esto es nuevo
        px = px - 1;
        Tablero.matriz[px][py] = 4;
        GUI.pintarMatriz();
    }

    /**
     * Metodo que primero valida si la posicion en la que esta el pac-man es una
     * pastilla, si es asi el puntaje va a incrementar en 5, y de esta forma la
     * posicion en la que estaba antes el pac-man se inicializa en 0 ya que es
     * un espacio vacio, decrementa en 1 la posicion en y, esto, para poder
     * moverlo hacia arriba, ahora la nueva posicion en la que va a estar el
     * pac-man se inicializa en 3 y por ultimo se vuelve a pintar la matriz
     */
    public void arriba() {
        if (Tablero.matriz[px][py - 1] == 1) {
            puntos += 5;
            puntaje.setText("Puntos: " + puntos);
        }
        Tablero.matriz[px][py] = 0;
        Tablero.matAux[px][py] = Tablero.matriz[px][py]; //esto es nuevo
        py = py - 1;
        Tablero.matriz[px][py] = 5;
        GUI.pintarMatriz();
    }

    /**
     * Metodo que primero valida si la posicion en la que esta el pac-man es una
     * pastilla, si es asi el puntaje va a incrementar en 5, y de esta forma la
     * posicion en la que estaba antes el pac-man se inicializa en 0 ya que es
     * un espacio vacio, aumenta en 1 la posicion en y, esto, para poder moverlo
     * hacia bajo, ahora la nueva posicion en la que va a estar el pac-man se
     * inicializa en 3 y por ultimo se vuelve a pintar la matriz
     */
    public void abajo() {
        if (Tablero.matriz[px][py + 1] == 1) {
            puntos += 5;
            puntaje.setText("Puntos: " + puntos);
        }
        Tablero.matriz[px][py] = 0;
        Tablero.matAux[px][py] = Tablero.matriz[px][py]; //esto es nuevo
        py = py + 1;
        Tablero.matriz[px][py] = 8;
        GUI.pintarMatriz();
    }

    /**
     * Metodo que indica a que lado quiere mover el pac-man, en este caso es
     * hacia la derecha
     */
    public void irDerecha() {
        if (Tablero.matriz[px + 1][py] == 1 || Tablero.matriz[px + 1][py] == 0) {
            arriba = 0;
            abajo = 0;
            izquierda = 0;
            derecha = 1;
        }
    }

    /**
     * Metodo que indica a que lado quiere mover el pac-man, en este caso es
     * hacia la izquierda
     */
    public void irIzquierda() {
        if (Tablero.matriz[px - 1][py] == 1 || Tablero.matriz[px - 1][py] == 0) {
            arriba = 0;
            abajo = 0;
            izquierda = 1;
            derecha = 0;
        }
    }

    /**
     * Metodo que indica a que lado quiere mover el pac-man, en este caso es
     * hacia arriba
     */
    public void irArriba() {
        if (Tablero.matriz[px][py - 1] == 1 || Tablero.matriz[px][py - 1] == 0) {
            arriba = 1;
            abajo = 0;
            izquierda = 0;
            derecha = 0;
        }
    }

    /**
     * Metodo que indica a que lado quiere mover el pac-man, en este caso es
     * hacia abajo
     */
    public void irAbajo() {
        if (Tablero.matriz[px][py + 1] == 1 || Tablero.matriz[px][py + 1] == 0) {
            arriba = 0;
            abajo = 1;
            izquierda = 0;
            derecha = 0;
        }
    }
}

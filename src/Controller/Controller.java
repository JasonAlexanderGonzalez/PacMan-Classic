package Controller;

import View.GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase oontroladora, basicamente es la que va a controlar los eventos de la GUI
 * @author Tristan Lopez-Jason
 */
public class Controller implements ActionListener {

    /**
     * Llamada de la clase GUI para poder usar los elementos de esta
     */
    private GUI gui;
    
    /**
     * Metodo contructor de la clase, inicializa la interfaz del juego y
     * activa el evento de botones
     */
    public Controller(){
        gui = new GUI();
        initEvents();
    }
    
    /**
     * Le añade el escuchador al boton iniciar
     */
    public void initEvents(){
        gui.getIniciar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gui.getIniciar()) {
            gui.menu();
            gui.eventoMenu();
        }
    }

}

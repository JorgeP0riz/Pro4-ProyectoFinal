package menuBar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelBarraDeHerramientas extends JPanel implements ActionListener {
    private JMenu archivo;

    private JMenu ayuda;

    private JMenu chat;

    private JMenuBar barra;

    private JMenuItem nuevapartida;
    private JMenuItem salir;
    private JMenuItem acerca;
    private JMenuItem cambiar_modo;

    private JMenuItem chatJuego;


    public PanelBarraDeHerramientas() {
        iniciar();
        alinear();
    }

    private void alinear() {
        //setDefaultLocale(null);
        setLayout(null);
        //setSize(650,50);
        add(barra);
        setVisible(true);
    }

    private void iniciar() {
        nuevapartida = new JMenuItem("Nueva partida");
        cambiar_modo = new JMenuItem("Cambiar Modo");

        salir = new JMenuItem("Salir");
        acerca = new JMenuItem("Acerca de...");

        chatJuego = new JMenuItem("CHAT");

        archivo = new JMenu("Archivo");
        ayuda = new JMenu("Ayuda");
        chat = new JMenu("Otro");

        archivo.add(nuevapartida);
        ayuda.add(acerca);
        archivo.add(cambiar_modo);
        archivo.add(salir);
        chat.add(chatJuego);

        cambiar_modo.addActionListener(this);
        nuevapartida.addActionListener(this);
        salir.addActionListener(this);
        acerca.addActionListener(this);
        chatJuego.addActionListener(this);
        barra = new JMenuBar();
        barra.add(archivo);
        barra.add(ayuda);
        barra.add(chat);
        //barra.setVisible(true);
        barra.setBounds(0,0,650,50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nuevapartida) {
            JOptionPane.showMessageDialog(null, "En proceso....");
        }
        if (e.getSource() == salir) {
            JOptionPane.showMessageDialog(null, "En proceso....");
        }
        if (e.getSource() == acerca) {
            new AcercaDe();
        }
        if (e.getSource() == cambiar_modo) {
            JOptionPane.showMessageDialog(null, "En proceso....");
        }
        if (e.getSource() == chatJuego) {
            JOptionPane.showMessageDialog(null, "En proceso....");
        }
    }
}

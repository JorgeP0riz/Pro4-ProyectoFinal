package menuBar;

import javax.swing.*;
import java.awt.*;

public class AcercaDe extends JFrame {
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;

    private JTextArea area;

    private JScrollPane panel;

    private Hipervinculo label4;
    private Hipervinculo label5;

    public AcercaDe() {

        iniciar();
        alinear();
        setSize(500,350);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void iniciar() {
        label1 = new JLabel("Juego Ludo");
        label1.setFont(new Font("1942 report", Font.CENTER_BASELINE, 18));
        label2 = new JLabel("Version 1.0");
        label2.setFont(new Font("1942 report", Font.CENTER_BASELINE, 13));
        label3 = new JLabel("Fuentes:");
        label2.setFont(new Font("1942 report", Font.CENTER_BASELINE, 13));
        label4 = new Hipervinculo("Creador", "");
        label5 = new Hipervinculo("Pagina De La Universidad NUR", "https://www.nur.edu/");
        area = new JTextArea();
        area.setEditable(false);
        area.setText("Proyecto Personal, Recreando el popular juego de Ludo en el entorno de desarrollo java, \nHecho por: Jorge Poriz Rojas \nEstudiante De: "
                + "Ingenieria En Sistemas Cursando cuarto Semestre en la Universidad NUR\nEl Programa aun esta en fase beta ya que aun no tiene implementadas \n"
                + "algunas reglas basicas del juego de Ludo original, los movimientos de las piezas en ludo son de un sentido,\n"
                + "si vas a eliminar a una pieza diferente simplemente tomas su lugar\n"
                + "Regla: Enter para lanzar dado y click izquierdo para mover ficha\n"
                + "modos de juegos: *En desarroll*\n\n"
                + "Proyecto hecho de estudiante para estudiantes\n"
                + "publicado el ** de Noviembre de 2022");
        panel = new JScrollPane(area);
    }

    public void alinear() {
        setLayout(null);
        label1.setBounds(160,0,500,50);
        getContentPane().add(label1);
        label2.setBounds(0,250,500,50);
        getContentPane().add(label2);
        label3.setBounds(150,250,500,50);
        getContentPane().add(label3);
        label4.setBounds(220,265,500,20);
        getContentPane().add(label4);
        label5.setBounds(220,285,500,20);
        getContentPane().add(label5);
        panel.setBounds(0,50,485,200);
        getContentPane().add(panel);
    }
}

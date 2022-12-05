package game;

import menuBar.PanelBarraDeHerramientas;

import javax.swing.*;


public class FrameGame extends JFrame{

    static GameMoves gm;
    static PanelBarraDeHerramientas panelBarra;


    //static JFrame jframe = new JFrame();

    public FrameGame(String nombre) {


            this.setTitle(nombre);
            this.setSize(650,750);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            init();

            this.setVisible(true);


    }

    public void init(){
        panelBarra = new PanelBarraDeHerramientas();
        panelBarra.setBounds(0,0,650,50);
        this.add(panelBarra);
        gm = new GameMoves();
        gm.setFocusable(true);
        gm.addKeyListener(gm);
        gm.addMouseListener(gm);
        this.add(gm);

    }



//    public static void main(String[] args) {
//        new FrameGame().setVisible(true);
//    }

//    public static void main(String[] args)  {
//
//        panelBarra = new PanelBarraDeHerramientas();
//
//        //jframe.setBounds(0,0,1200,600);
//        jframe.setSize(650,750);
//        jframe.setLocationRelativeTo(null);
//        jframe.setTitle("LUDO");
//        jframe.setResizable(false);
//        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//
//        panelBarra.setBounds(0,0,650,50);
//        jframe.add(panelBarra);
//
//        gm = new GameMoves();
//        gm.setFocusable(true);
//        gm.addKeyListener(gm);
//        gm.addMouseListener(gm);
//        jframe.add(gm);
//
//        jframe.setVisible(true);
//    }


}

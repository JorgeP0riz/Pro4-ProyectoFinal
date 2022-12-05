package lobby;

import chatGlobal.FrameChatG;
import game.FrameGame;
import socket.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

public class FrameOpciones extends JFrame {


    private JButton btnCrearPartida;
    private JButton btnCargarPartida;
    private JButton btnChatGlobal;
    private JButton btnSalir;
    private JPanel panelOpciones;

    private JLabel lbUsuarios;
    private JLabel lbPartidas;



    private String socketId;
    private Socket socket;
    private Client user;
    private String nickName;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    JList listUser = new JList();
    DefaultListModel dmlUser;


    JList listPartida = new JList();
    DefaultListModel dmlPartida;

    private String nombre;



    public FrameOpciones()  {
        this.setSize(600,300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        init();
        this.setVisible(true);
    }

    public FrameOpciones(String nickName, Socket socket){
        this.socket = socket;
        this.nickName = nickName;
        try {
            this.setSize(600,300);
            this.setResizable(false);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            init();
            dmlUser = new DefaultListModel();
            listUser.setModel(dmlUser);

            dmlPartida = new DefaultListModel();
            listPartida.setModel(dmlPartida);

            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            new ReadNewUser().start();
            this.setVisible(true);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }


    private void init(){
        panelOpciones = new JPanel();
        panelOpciones.setLayout(null);
        panelOpciones.setBackground(Color.WHITE);

        btnCrearPartida = new JButton("Crear Partida");
        btnCrearPartida.setFont((new Font("serif", Font.BOLD, 15)));
        btnCrearPartida.setForeground(Color.BLACK);
        btnCrearPartida.setBounds(new Rectangle(50,30,150,50));
        btnCrearPartida.setFocusPainted(false);
        btnCrearPartida.setBorderPainted(false);
        btnCrearPartida.setContentAreaFilled(false);
        btnCrearPartida.addActionListener(crear);
        panelOpciones.add(btnCrearPartida);

        btnCargarPartida = new JButton("Cargar Partida");
        btnCargarPartida.setFont((new Font("serif", Font.BOLD, 15)));
        btnCargarPartida.setForeground(Color.BLACK);
        btnCargarPartida.setBounds(new Rectangle(50,80,150,50));
        btnCargarPartida.setFocusPainted(false);
        btnCargarPartida.setBorderPainted(false);
        btnCargarPartida.setContentAreaFilled(false);
        btnCargarPartida.addActionListener(cargar);
        panelOpciones.add(btnCargarPartida);

        btnChatGlobal = new JButton("Chat Global");
        btnChatGlobal.setFont((new Font("serif", Font.BOLD, 15)));
        btnChatGlobal.setForeground(Color.BLACK);
        btnChatGlobal.setBounds(new Rectangle(50,130,150,50));
        btnChatGlobal.setFocusPainted(false);
        btnChatGlobal.setBorderPainted(false);
        btnChatGlobal.setContentAreaFilled(false);
        btnChatGlobal.addActionListener(chatWorld);
        panelOpciones.add(btnChatGlobal);

        btnSalir = new JButton("Salir");
        btnSalir.setFont((new Font("serif", Font.BOLD, 15)));
        btnSalir.setForeground(Color.BLACK);
        btnSalir.setBounds(new Rectangle(50,190,150,50));
        btnSalir.setFocusPainted(false);
        btnSalir.setBorderPainted(false);
        btnSalir.setContentAreaFilled(false);
        btnSalir.addActionListener(salir);
        panelOpciones.add(btnSalir);

        lbUsuarios = new JLabel("Usuarios");
        lbUsuarios.setFont((new Font("serif", Font.BOLD, 14)));
        lbUsuarios.setForeground(new Color(231, 76, 60));
        lbUsuarios.setBounds(450, 0, 100, 30);
        panelOpciones.add(lbUsuarios);

        listUser.setBounds(450,35,120,200);
        listUser.setBackground(Color.GRAY);
        listUser.getSelectionModel().addListSelectionListener(e -> {
            JOptionPane.showMessageDialog(null, "Funciona");
        });
        panelOpciones.add(listUser);


        lbPartidas = new JLabel("Partidas");
        lbPartidas.setFont((new Font("serif", Font.BOLD, 14)));
        lbPartidas.setForeground(new Color(231, 76, 60));
        lbPartidas.setBounds(250, 0, 100, 30);
        panelOpciones.add(lbPartidas);

        listPartida.setBounds(250,35,120,200);
        listPartida.setBackground(Color.GRAY);
        panelOpciones.add(listPartida);


        this.add(panelOpciones);
    }

    ActionListener crear = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            nombre = JOptionPane.showInputDialog("Ingrese el nombre de la Sala");
            String[] arreglo = {"Single Player", "Multi Player"};
            int opcion = JOptionPane.showOptionDialog(null, "Escoge tu Modo de Juego",
                    "Modo", 0, JOptionPane.QUESTION_MESSAGE, null, arreglo,
                    "Single Player");
            String opcionAux = arreglo[opcion];
            String resultadoAux = "Sala: "+nombre;
            String resultado = "Sala: "+ nombre+ "\nModo: "+ opcionAux;
            JOptionPane.showMessageDialog(null, resultado);

            //System.out.println("Antes de enviar");
            user = new Client();
            user.ConectarAServidor();
            user.EnviarDato(resultadoAux);
            String aux = user.EsperarDato();
            //System.out.println("Recibiendo datos");
            if (aux.equals("Ya esta creado")) {
                JOptionPane.showMessageDialog(null, "Ya esta creado");
            }else if (opcionAux.equals("Single Player")){
                new FrameGame(resultadoAux).setVisible(true);
                dispose();

            }else {
                JOptionPane.showMessageDialog(null, "iniciando partida multijugador");
            }


        }
    };

    ActionListener cargar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //new FrameCargarPartida(socket).setVisible(true);
        }
    };

    ActionListener chatWorld = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new FrameChatG().setVisible(true);
//            Usuario usuario = new Usuario();
//            JOptionPane.showMessageDialog(null, "Usuario: "+ usuario.getUsuario());
        }
    };

    ActionListener salir = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Juego Cerrandose");
            System.exit(0);
        }
    };

//    public static void main(String[] args) {
//        String aux = "Hola Mundo;";
//
//        if (aux.contains(":;.,/=")){
//            aux = aux.substring(6);
//            System.out.println(aux);
//        }
//    }

    class ReadNewUser extends Thread{
        public void run(){
            while (true){
                try {
                    String m = inputStream.readUTF();
                    System.out.println(m);
                    if (m.contains(":;.,/=")){
                        m=m.substring(6);
                        dmlUser.clear();
                        StringTokenizer st = new StringTokenizer(m, ",");
                        while (st.hasMoreTokens()){
                            String u = st.nextToken();

                            // Condicional para mostrar los demas usuarios menos el tuyo
                            if (!nickName.equals(u)){
                                dmlUser.addElement(u);
                            }
                        }
                    }else if (m.contains("123456")){
                        m=m.substring(6);
                        dmlPartida.clear();
                        StringTokenizer st = new StringTokenizer(m, ",");
                        while (st.hasMoreTokens()){
                            String u = st.nextToken();
                            dmlPartida.addElement(u);
                        }
                    }
                }catch (Exception ex){
                    break;
                }
            }
        }
    }

    class ReadNewGames extends Thread{
        public void run(){
            while (true){
                try {
                    String m = inputStream.readUTF();

                    if (m.contains("123456")){
                        //System.out.println("paso la creacion");
                        m=m.substring(6);
                        dmlPartida.clear();
                        StringTokenizer st = new StringTokenizer(m, ",");
                        while (st.hasMoreTokens()){
                            String u = st.nextToken();
                            dmlPartida.addElement(u);
                        }
                    }
                }catch (Exception ex){
                    break;
                }
            }
        }
    }
}

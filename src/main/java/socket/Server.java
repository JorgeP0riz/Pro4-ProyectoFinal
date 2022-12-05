package socket;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends JFrame {

    private JLabel lbTitulo;
    private JLabel lbEstado;
    private JTextArea hisEstado;
    private JPanel panelServer;
    private JScrollPane scroll;

    private ServerSocket serverSocket;

    DataOutputStream outputStream;
    DataInputStream inputStream;
    HashMap clientColl = new HashMap();

    HashMap gameColl = new HashMap();


    public Server() {
        try {
            this.setSize(360, 450);
            this.setResizable(false);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            init();

            serverSocket = new ServerSocket(2089);
            this.lbEstado.setText("Server Started");

            new ClientAccept().start();

            // new GameAceptar().start();

            this.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void init() {
        panelServer = new JPanel();
        panelServer.setLayout(null);
        panelServer.setBackground(Color.WHITE);

        lbTitulo = new JLabel("ServerEstatus");
        lbTitulo.setFont((new Font("serif", Font.BOLD, 14)));
        lbTitulo.setForeground(new Color(231, 76, 60));
        lbTitulo.setBounds(10, 35, 100, 35);
        panelServer.add(lbTitulo);

        lbEstado = new JLabel(".................");
        lbEstado.setFont((new Font("serif", Font.BOLD, 14)));
        lbEstado.setForeground(new Color(231, 76, 60));
        lbEstado.setBounds(150, 35, 120, 35);
        panelServer.add(lbEstado);

        hisEstado = new JTextArea();
        hisEstado.setBackground(new Color(236, 240, 241));
        hisEstado.setFont((new Font("serif", Font.BOLD, 12)));
        hisEstado.setForeground(new Color(0, 0, 0));
        //hisMessage.setBounds(2,100,100,300);
        hisEstado.setEditable(false);
        //panel.add(hisMessage);

        scroll = new JScrollPane(hisEstado);
        scroll.setBounds(10, 80, 300, 300);
        scroll.setVisible(true);
        panelServer.add(scroll);
        add(panelServer);

    }

    public static void main(String[] args) {
        new Server().setVisible(true);
    }

    public String esperarDato() {
        try {
            return inputStream.readUTF();
        } catch (Exception ex) {
            return "@CerrarConexion@";
        }
    }

    public void enviarDato(String Dato) {
        try {
            outputStream.writeUTF(Dato);
        } catch (Exception ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    class ClientAccept extends Thread {
        public void run() {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    String i = new DataInputStream(socket.getInputStream()).readUTF();

                    if (i.contains("Sala: ")) {
                        if (clientColl.containsKey(i)) {
                            outputStream = new DataOutputStream(socket.getOutputStream());
                            enviarDato("Ya esta creada");
                        } else {
                            clientColl.put(i, socket);
                            hisEstado.append(i + " Created! \n");
                            outputStream = new DataOutputStream(socket.getOutputStream());
                            //outputStream.writeUTF("");
                            enviarDato("");
                            new PrepareGameList().start();
                        }
                    } else {
                        if (clientColl.containsKey(i)) {
                            outputStream = new DataOutputStream(socket.getOutputStream());
                            //outputStream.writeUTF("Ya estas registrado");
                            enviarDato("Ya estas registrado");
                        } else {
                            clientColl.put(i, socket);
                            hisEstado.append(i + " Joined! \n");
                            outputStream = new DataOutputStream(socket.getOutputStream());
                            //outputStream.writeUTF("");
                            enviarDato("");
                            //new MsgRead(socket, i).start();
                            new PrepareClientList().start();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    class PrepareClientList extends Thread {
        public void run() {
            try {
                String ids = "";
                Set k = clientColl.keySet();
                Iterator itr = k.iterator();
                while (itr.hasNext()) {
                    String key = (String) itr.next();
                    if (!key.contains("Sala: ")) {
                        ids += key + ",";
                    }
                }
                if (ids.length() != 0) {
                    ids = ids.substring(0, ids.length() - 1);
                }
                itr = k.iterator();

                while (itr.hasNext()) {
                    String key = (String) itr.next();
                    try {

                        new DataOutputStream(((Socket) clientColl.get(key)).getOutputStream()).writeUTF(":;.,/=" + ids);

                    } catch (Exception ex) {
                        clientColl.remove(key);
                        hisEstado.append(key + ": removed! \n");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class MsgRead extends Thread {
        Socket socket;
        String nickName;

        public MsgRead(Socket socket, String nickName) {
            this.socket = socket;
            this.nickName = nickName;
        }

        public void run() {
            while (!clientColl.isEmpty()) {
                try {
                    String i = new DataInputStream(socket.getInputStream()).readUTF();
                    if (i.equals("shdfhasdhsod48344348SJSDSAXYT")) {
                        clientColl.remove(nickName);
                        hisEstado.append(nickName + ": removed! \n");
                        new PrepareClientList().start();
                        Set<String> k = clientColl.keySet();
                        Iterator itr = k.iterator();
                        while (itr.hasNext()) {
                            String key = (String) itr.next();
                            if (!key.equalsIgnoreCase(nickName)) {
                                try {
                                    new DataOutputStream(((Socket) clientColl.get(key)).getOutputStream()).writeUTF("< " + nickName + " to " + key + " >" + i);
                                } catch (Exception ex) {
                                    clientColl.remove(key);
                                    hisEstado.append(key + ": removed! \n");
                                    new PrepareClientList().start();
                                }
                            } else if (i.contains("#44443@@@@456456@@")) {
                                i = i.substring(20);
                                StringTokenizer st = new StringTokenizer(i, ":");
                                String id = st.nextToken();
                                i = st.nextToken();
                                try {
                                    new DataOutputStream(((Socket) clientColl.get(key)).getOutputStream()).writeUTF("< " + nickName + " to " + key + " >" + i);
                                } catch (Exception ex) {
                                    clientColl.remove(id);
                                    hisEstado.append(id + ": removed! \n");
                                    new PrepareClientList().start();
                                }

                            }
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


    class GameAceptar extends Thread {
        public void run() {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    String i = new DataInputStream(socket.getInputStream()).readUTF();
                    if (gameColl.containsKey(i)) {
                        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                        outputStream.writeUTF("Ya esta creado");
                    } else {
                        gameColl.put(i, socket);
                        hisEstado.append(i + " Created! \n");
                        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                        outputStream.writeUTF("");
                        new PrepareGameList().start();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    class PrepareGameList extends Thread {
        public void run() {
            try {
                String ids = "";
                Set k = clientColl.keySet();
                Iterator itr = k.iterator();
                while (itr.hasNext()) {
                    String key = (String) itr.next();
                    if (key.contains("Sala: ")) {
                        ids += key + ",";
                    }
                }
                if (ids.length() != 0) {
                    ids = ids.substring(0, ids.length() - 1);

                }
                itr = k.iterator();

                while (itr.hasNext()) {
                    String key = (String) itr.next();
                    try {

                        new DataOutputStream(((Socket) clientColl.get(key)).getOutputStream()).writeUTF("123456" + ids);

                    } catch (Exception ex) {
                        clientColl.remove(key);
                        hisEstado.append(key + ": deleted! \n");
                    }

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

package lobby;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

public class FrameCargarPartida extends JFrame {

    private JPanel panelPartidas;

    private JLabel lbPartidas;
    JList listPartida = new JList();
    DefaultListModel dmlPartida;

    private Socket socket;
    private String nombre;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public FrameCargarPartida(Socket socket) {
        try {
            this.setSize(400,300);
            this.setResizable(false);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            init();

            dmlPartida = new DefaultListModel();
            listPartida.setModel(dmlPartida);

            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            new ReadNewGames().start();
            this.setVisible(true);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void init() {
        panelPartidas = new JPanel();
        panelPartidas.setLayout(null);
        panelPartidas.setBackground(Color.WHITE);

        lbPartidas = new JLabel("Partidas");
        lbPartidas.setFont((new Font("serif", Font.BOLD, 14)));
        lbPartidas.setForeground(new Color(231, 76, 60));
        lbPartidas.setBounds(100, 0, 100, 30);
        panelPartidas.add(lbPartidas);

        listPartida.setBounds(100,35,150,200);
        listPartida.setBackground(Color.GRAY);
        panelPartidas.add(listPartida);

        this.add(panelPartidas);
    }

//    public static void main(String[] args) {
//        new FrameCargarPartida().setVisible(true);
//    }

    class ReadNewGames extends Thread{
        public void run(){
            while (true){
                try {
                    String m = inputStream.readUTF();

                    if (m.contains("123456")){
                        System.out.println("paso la creacion");
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

package chatGlobal;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import lobby.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatGlobal extends JPanel{
    private JButton btnConection;
    private JButton btnSend;

    private JTextField txtUser;
    private JTextField txtMessage;

    private JTextArea hisMessage;

    private JScrollPane scroll;

    private JLabel lbEstado;

    private Socket s;
    private String userName;



    public ChatGlobal(){
        init();
    }

    private void init() {
        setLayout(null);
        setBackground(new Color(18,140,126));

        txtUser = new JTextField();
        txtUser.setBounds(35,2,100,35);
        txtUser.setFont((new Font("serif", Font.BOLD, 13)));
        Usuario usuario = new Usuario();
        txtUser.setText(usuario.getUsuario());
        txtUser.setEditable(false);
        //txtUser.setHorizontalAlignment(JTextField.EAST);
        add(txtUser);

        btnConection = new JButton("Connect");
        btnConection.setBackground(new Color(7,94,84));
        btnConection.setFont((new Font("serif", Font.BOLD, 12)));
        btnConection.setForeground(new Color(52,183,241));
        btnConection.setBounds(new Rectangle(155,2,80,35));
        btnConection.addActionListener(conection);
        add(btnConection);

        lbEstado = new JLabel("offline");
        lbEstado.setFont((new Font("serif", Font.BOLD, 14)));
        lbEstado.setForeground(new Color(231,76,60));
        lbEstado.setBounds(15,35,80,35);
        add(lbEstado);

        hisMessage = new JTextArea();
        hisMessage.setBackground(new Color(236,240,241));
        hisMessage.setFont((new Font("serif", Font.BOLD, 12)));
        hisMessage.setForeground(new Color(0,0,0));
        //hisMessage.setBounds(2,100,100,300);
        hisMessage.setEditable(false);
        //panel.add(hisMessage);

        scroll = new JScrollPane(hisMessage);
        scroll.setBounds(35,70,200,150);
        scroll.setVisible(true);
        add(scroll);

        txtMessage = new JTextField();
        txtMessage.setBounds(35,225,200,35);
        txtMessage.setFont((new Font("serif", Font.BOLD, 13)));
        //txtUser.setHorizontalAlignment(JTextField.EAST);
        add(txtMessage);

        btnSend = new JButton("Send");
        btnSend.setBackground(new Color(7,94,84));
        btnSend.setFont((new Font("serif", Font.BOLD, 12)));
        btnSend.setForeground(new Color(52,183,241));
        btnSend.setBounds(new Rectangle(100,265,80,35));
        btnSend.addActionListener(sendMessage);
        btnSend.setEnabled(false);
        add(btnSend);
    }

    ActionListener conection = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                userName = txtUser.getText().trim();
                s= IO.socket("http://localhost:3000");
                s.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                    @Override
                    public void call(Object... objects) {
                        lbEstado.setText("Online");
                        lbEstado.setForeground(new Color(46,204,113));
                        btnSend.setEnabled(true);
                    }
                });
                s.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                    @Override
                    public void call(Object... objects) {
                        lbEstado.setText("Offline");
                        lbEstado.setForeground(new Color(231,76,60));
                    }
                });
                s.on("message", new Emitter.Listener() {
                    @Override
                    public void call(Object... objects) {
                        //hisMessage.append(Arrays.toString(objects) +"\n");
                        hisMessage.append(objects[0] +"\n");
                    }
                });
                s.open();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    };

    ActionListener sendMessage = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            s.emit("message", userName+" : "+txtMessage.getText().trim());
            txtMessage.setText("");
            txtMessage.grabFocus();
        }
    };
}

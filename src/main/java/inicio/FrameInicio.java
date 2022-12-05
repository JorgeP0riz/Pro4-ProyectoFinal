package inicio;

import lobby.FrameOpciones;
import lobby.Usuario;
import socket.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class FrameInicio extends JFrame {

    InicioPanel inicioPanel;

    private JButton btnIniciar;

    private JLabel lbNick;

    private JTextField txtNick;

    Usuario usuario = new Usuario();


    public FrameInicio() {
        this.setSize(500, 500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();

        Container contenedor = this.getContentPane();

        inicioPanel = new InicioPanel();
        contenedor.add(inicioPanel);
        this.setVisible(true);
    }

    private void initComponents() {

        btnIniciar = new JButton("Iniciar");
        //btnIniciar.setBackground(Color.WHITE);
        btnIniciar.setFont((new Font("serif", Font.BOLD, 18)));
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.setBounds(new Rectangle(200, 390, 120, 50));
        btnIniciar.setFocusPainted(false);
        btnIniciar.setBorderPainted(false);
        btnIniciar.setContentAreaFilled(false);
        btnIniciar.addActionListener(inicar);
        add(btnIniciar);

        lbNick = new JLabel("Nick Name");
        lbNick.setFont((new Font("serif", Font.BOLD, 20)));
        lbNick.setForeground(Color.WHITE);
        //lbNick.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lbNick.setBounds(90, 350, 300, 50);
        add(lbNick);

        txtNick = new JTextField();
        txtNick.setFont((new Font("serif", Font.BOLD, 13)));
        txtNick.setBounds(90, 400, 120, 35);
        //txtUser.setHorizontalAlignment(JTextField.EAST);
        add(txtNick);

    }

    ActionListener inicar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (txtNick.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese NickName");
            } else if (txtNick.getText().length() > 10) {
                JOptionPane.showMessageDialog(null, "Excede Limite De Caracteres");
                txtNick.setText("");
            } else {
//                String nickName = txtNick.getText();
//                JOptionPane.showMessageDialog(null, "Usuario: [" +
//                        nickName+"] Iniciado");
//                usuario.setUsuario(nickName);
//                new FrameOpciones().setVisible(true);
//                dispose();

//                try {
//                    String nickName = txtNick.getText();
//
//                    Socket socket = new Socket("localhost", 2089);
//                    DataInputStream inputStream = new DataInputStream(socket.getInputStream());
//                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
//
//                    outputStream.writeUTF(nickName);
//
//                    String aux = new DataInputStream(socket.getInputStream()).readUTF();
//
//                    if (aux.equals("Ya estas registrado")) {
//                        JOptionPane.showMessageDialog(null, "Ya estas registrado");
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Usuario: [" +
//                                nickName + "] Iniciado");
//                        usuario.setUsuario(nickName);
//
//                        new FrameOpciones(nickName, socket).setVisible(true);
//
//                        dispose();
//                    }
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
                String nickName = txtNick.getText();
                Client user = new Client();

                user.ConectarAServidor();

                user.EnviarDato(nickName);

                String aux = user.EsperarDato();

                if (aux.equals("Ya estas registrado")) {
                    JOptionPane.showMessageDialog(null, "Ya estas registrado");
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario: [" +
                            nickName + "] Iniciado");
                    usuario.setUsuario(nickName);

                    Socket socket = user.socket();

                    new FrameOpciones(nickName, socket).setVisible(true);

                    dispose();
                }
            }
        }
    };


    public static void main(String[] args) {
        FrameInicio v = new FrameInicio();
    }
}

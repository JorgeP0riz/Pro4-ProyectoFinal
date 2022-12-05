package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    private int puerto=2089;
    private String host="localhost";
    private Socket socket;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;

    public Client(){

    }

    public boolean ConectarAServidor(){
        try {
            socket = new Socket(host,puerto);
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public Socket socket(){
        return this.socket;
    }

    public String IPConectado(){
        return socket.getInetAddress().toString();
    }

    public String PuertoConectado(){
        return String.valueOf(socket.getPort());
    }

    public String EsperarDato(){
        try {
            return inputStream.readUTF();
        } catch (IOException ex) {
            return "@CerrarConexion@";
        }
    }

    public void EnviarDato(String Dato){
        try {
            outputStream.writeUTF(Dato);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void CerrarConexion(){
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

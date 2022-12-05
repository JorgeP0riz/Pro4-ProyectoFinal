package inicio;

import javax.swing.*;
import java.awt.*;


public class InicioPanel extends JPanel {

    private Image fondo;

    public InicioPanel(){
        preInit();
    }

    private void preInit() {
        fondo = new ImageIcon("img.png").getImage();
    }


    public void paint(Graphics g) {
        g.drawImage(fondo,0,0,getWidth(),getHeight(),this);
    }
}

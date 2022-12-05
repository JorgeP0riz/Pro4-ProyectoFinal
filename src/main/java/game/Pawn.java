package game;

import java.awt.*;

public class Pawn {
    int x,y;
    int actual;
    int height,width;
    public Pawn(int h,int w){
        actual =-1;
        x=-1;
        y=-1;
        height=h;
        width=w;
    }
    public void draw(Graphics2D g, int i, int j, int play)  {

        if(actual ==-1) { // Si esta dentro de su Tablero de
            // fichas
            int temp1=80+(height/2),temp2=150+(width/2);
            x=i;
            y=j;
            if(play==0) {
                g.setColor(Color.RED);
            }
            else if(play==1) {
                g.setColor(Color.GREEN);
            }
            else if(play==2) {
                g.setColor(Color.YELLOW);
            }
            else if(play==3) {
                g.setColor(Color.BLUE);
            }
            g.fillOval(temp1+5+(i*width),temp2+5+(j*height),width-10,height-10);
            g.setStroke(new BasicStroke(2));
            g.setColor(Color.BLACK);
            g.drawOval(temp1+5+(i*width),temp2+5+(j*height),width-10,height-10);
        }
        else
        { // Si esta fuera de su Tablero de fichas
            int temp1=80,temp2=150;
            x=Path.ax[play][actual];
            y=Path.ay[play][actual];
            if(play==0) {
                g.setColor(Color.RED);
            }
            else if(play==1) {
                g.setColor(Color.GREEN);
            }
            else if(play==2) {
                g.setColor(Color.YELLOW);
            }
            else if(play==3) {
                g.setColor(Color.BLUE);
            }
            g.fillOval(temp1+5+(x*width),temp2+5+(y*height),width-10,height-10);
            g.setStroke(new BasicStroke(2));
            g.setColor(Color.BLACK);
            g.drawOval(temp1+5+(x*width),temp2+5+(y*height),width-10,height-10);
        }
    }
}

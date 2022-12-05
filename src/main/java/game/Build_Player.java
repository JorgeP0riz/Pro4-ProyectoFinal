package game;

import java.awt.*;

public class Build_Player {

    Player[] pl=new Player[4];
    int[][] initialx= {
            {1,1,3,3}, // Rojas
            {10,10,12,12}, // Verdes
            {10,10,12,12}, // Amarillos
            {1,1,3,3} // Azules
    };
    int[][] initialy= {
            {1,3,1,3},
            {1,3,1,3},
            {10,12,10,12},
            {10,12,10,12}
    };
    public Build_Player(int height, int width) {
        for(int i=0;i<4;i++) {
            pl[i]=new Player(height,width);
        }
    }
    public void draw(Graphics2D g) {

        for(int i=0;i<4;i++) {
            for(int j=0;j<4;j++) {
                pl[i].pa[j].draw(g,initialx[i][j],initialy[i][j],i);
            }
        }
    }
}

package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameMoves extends JPanel implements KeyListener, ActionListener, MouseListener {

    private static final long serialVersionUID = 1L;
    Layout la;
    Build_Player bp;
    Timer time;
    int delay = 10;
    int jugadorActual, dadoDice;
    int flag = 0, roll, kill = 0;


    public GameMoves() {

        setLayout(null);
        setFocusTraversalKeysEnabled(false);
        requestFocus();
        jugadorActual = 0;
        la = new Layout(80, 150);
        bp = new Build_Player(la.height, la.width);
        dadoDice = 0;
        flag = 0;
        roll = 0;
        kill = 0;

    }

    @Override
    public void paint(Graphics g) {
        la.draw((Graphics2D) g);
        bp.draw((Graphics2D) g);
        if (bp.pl[jugadorActual].coin == 4) {
            g.setColor(Color.GRAY);
            g.fillRect(250, 640, 100, 60);
            if (jugadorActual == 0) {
                g.setColor(Color.RED);
            } else if (jugadorActual == 1) {
                g.setColor(Color.GREEN);
            } else if (jugadorActual == 2) {
                g.setColor(Color.YELLOW);
            } else if (jugadorActual == 3) {
                g.setColor(Color.BLUE);
            }
            g.setFont(new Font("serif", Font.BOLD, 15));
            g.drawString("game.Player " + (jugadorActual + 1) + " Gano.", 250, 660);
            g.drawString("Felicidades.", 250, 680);
            jugadorActual = 0;
            la = new Layout(80, 50);
            bp = new Build_Player(la.height, la.width);
            dadoDice = 0;
            flag = 0;
            roll = 0;
            kill = 0;
        } else if (dadoDice != 0) {
            // Color del texto que da el numero
            g.setColor(Color.GRAY);
            g.fillRect(80, 640, 100, 60);
            if (jugadorActual == 0) {
                g.setColor(Color.RED);
            } else if (jugadorActual == 1) {
                g.setColor(Color.GREEN);
            } else if (jugadorActual == 2) {
                g.setColor(Color.YELLOW);
            } else if (jugadorActual == 3) {
                g.setColor(Color.BLUE);
            }
            //texto que da el numero del dado
            g.setFont(new Font("serif", Font.BOLD, 15));
            g.drawString("Jugador " + (jugadorActual + 1), 80, 660);
            g.drawString("Dado " + dadoDice, 80, 680);
        }
        if (flag == 0 && dadoDice != 0 && dadoDice != 6 && kill == 0) {
            jugadorActual = (jugadorActual + 1) % 4;
        }
        kill = 0;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER && flag == 0) {
            roll = 0;
            dadoDice = 1 + (int) (Math.random() * 6);
            repaint();
            for (int i = 0; i < 4; i++) {
                if (bp.pl[jugadorActual].pa[i].actual != -1 && bp.pl[jugadorActual].pa[i].actual != 56 && (bp.pl[jugadorActual].pa[i].actual + dadoDice) <= 56) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0 && dadoDice == 6) {
                for (int i = 0; i < 4; i++) {
                    if (bp.pl[jugadorActual].pa[i].actual == -1) {
                        flag = 1;
                        break;
                    }
                }
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (flag == 1) {
            int x = e.getX();
            int y = e.getY();
            x = x - 80;
            y = y - 150;
            x = x / 30;
            y = y / 30;
            int value = -1;
            //System.out.println(x+" "+y);
            if (dadoDice == 6) {
                for (int i = 0; i < 4; i++) {
                    if (bp.pl[jugadorActual].pa[i].x == x && bp.pl[jugadorActual].pa[i].y == y && (bp.pl[jugadorActual].pa[i].actual + dadoDice) <= 56) {
                        value = i;
                        flag = 0;
                        break;
                    }
                }
                if (value != -1) {
                    bp.pl[jugadorActual].pa[value].actual += dadoDice;
                    if (bp.pl[jugadorActual].pa[value].actual == 56) {
                        bp.pl[jugadorActual].coin++;
                    }
                    int k = 0;
                    int hou = bp.pl[jugadorActual].pa[value].actual;
                    if ((hou % 13) != 0 && (hou % 13) != 8 && hou < 51) {
                        for (int i = 0; i < 4; i++) {
                            if (i != jugadorActual) {
                                for (int j = 0; j < 4; j++) {
                                    int tem1 = Path.ax[jugadorActual][bp.pl[jugadorActual].pa[value].actual], tem2 = Path.ay[jugadorActual][bp.pl[jugadorActual].pa[value].actual];
                                    if (bp.pl[i].pa[j].x == tem1 && bp.pl[i].pa[j].y == tem2) {
                                        bp.pl[i].pa[j].actual = -1;
                                        kill = 1;
                                        k = 1;
                                        break;
                                    }
                                }
                            }
                            if (k == 1)
                                break;
                        }
                    }
                } else {
                    for (int i = 0; i < 4; i++) {
                        if (bp.pl[jugadorActual].pa[i].actual == -1) {
                            bp.pl[jugadorActual].pa[i].actual = 0;
                            flag = 0;
                            break;
                        }
                    }
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    if (bp.pl[jugadorActual].pa[i].x == x && bp.pl[jugadorActual].pa[i].y == y && (bp.pl[jugadorActual].pa[i].actual + dadoDice) <= 56) {
                        value = i;
                        flag = 0;
                        break;
                    }
                }
                if (value != -1) {
                    bp.pl[jugadorActual].pa[value].actual += dadoDice;
                    if (bp.pl[jugadorActual].pa[value].actual == 56) {
                        bp.pl[jugadorActual].coin++;
                    }
                    int k = 0;
                    int hou = bp.pl[jugadorActual].pa[value].actual;
                    if ((hou % 13) != 0 && (hou % 13) != 8 && hou < 51) {
                        for (int i = 0; i < 4; i++) {
                            if (i != jugadorActual) {
                                for (int j = 0; j < 4; j++) {
                                    int tem1 = Path.ax[jugadorActual][bp.pl[jugadorActual].pa[value].actual], tem2 = Path.ay[jugadorActual][bp.pl[jugadorActual].pa[value].actual];
                                    if (bp.pl[i].pa[j].x == tem1 && bp.pl[i].pa[j].y == tem2) {
                                        bp.pl[i].pa[j].actual = -1;
                                        kill = 1;
                                        k = 1;
                                        break;
                                    }
                                }
                            }
                            if (k == 1)
                                break;
                        }
                    }
                }
            }
            repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }

    @Override
    public void keyReleased(KeyEvent arg0) {


    }

    @Override
    public void keyTyped(KeyEvent arg0) {


    }

    @Override
    public void mouseEntered(MouseEvent arg0) {


    }

    @Override
    public void mouseExited(MouseEvent arg0) {


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {


    }
}

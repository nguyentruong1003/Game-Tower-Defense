import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {

    private Screen screen = new Screen();

    public Main() {
        setTitle("Tower Defense");
        //setSize(1000, 1000);
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        //setLocationRelativeTo(null);

        add(screen);

        // Key Event
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if(screen.getScene() == 0){
                        screen.startGame();
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    screen.stopGame();
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!screen.getWave().waveStart) screen.getWave().nextWave();
                }
            }
        });

        //Mouse Event
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                screen.mouseMoved(e);
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                screen.mouseMoved(e);
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                screen.mouseDowned(e);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
               //if(screen.getScene() == 1) screen.clickTowerOnMap(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
            }
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
            }
        });
    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}

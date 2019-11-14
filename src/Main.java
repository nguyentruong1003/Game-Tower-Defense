import javax.swing.*;
import java.awt.event.*;

public class Main extends JFrame {

    private Painter painter = new Painter();

    public Main() {
        setTitle("Tower Defense");
        //setSize(1000, 1000);
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        //setLocationRelativeTo(null);

        add(painter);

        // Key Event
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_P) {
                    if (Painter.scene == 1) {
                        Painter.audioPause++;
                        if(Painter.audioPause % 2 == 1) Painter.audio.pause();
                        if (Painter.audioPause % 2 == 0) Painter.audio.play();
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    GameController.stopGame();
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!Painter.wave.waveStart) Painter.wave.nextWave();
                }
            }
        });

        //Mouse Event
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Painter.handle.mouseMoved(e);
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                Painter.handle.mouseMoved(e);
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Painter.handle.mouseDowned(e);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getXOnScreen() >= 540 && e.getXOnScreen() <= 900 && Painter.scene == 0) {
                    // main menu
                    if (e.getYOnScreen() >= 455 && e.getYOnScreen() <= 542) {
                        Painter.selectLevel = 1; // easy level
                        System.out.println(Painter.selectLevel);
                        GameController.startGame();
                    }
                    if (e.getYOnScreen() >= 574 && e.getYOnScreen() <= 654) {
                        Painter.selectLevel = 2; // medium level
                        GameController.startGame();
                    }
                    if (e.getYOnScreen() >= 681 && e.getYOnScreen() <= 762) {
                        Painter.selectLevel = 3; // hard level
                        GameController.startGame();
                    }
                }
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

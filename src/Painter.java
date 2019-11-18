import entity.bullet.MachineGunBullet;
import entity.bullet.NormalBullet;
import entity.bullet.SniperBullet;
import entity.enemy.AbstractEnemy;
import entity.tile.tower.AbstractTower;
import game.GameField;
import game.Player;
import jaco.mp3.player.MP3Player;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Painter extends JPanel implements Runnable {

    static Thread thread;
    static int scene;
    static boolean running = false;
    static Player player;
    static Image menu = new ImageIcon("res/menu/menu.png").getImage();
    static Image background = new ImageIcon("res/background/background.png").getImage();
    static Image loseGame = new ImageIcon("res/menu/loseGame.png").getImage();
    static AbstractTower[] towerList = new AbstractTower[4];
    static GameField gameField;
    static int map[][];
    static AbstractTower towerMap[][];
    static Font font;
    static Wave wave;
    static MP3Player audio = new MP3Player(new File("res/audio/bgmusic.mp3")); // background music

    static int hand = 0;
    static int handXPos;
    static int handYPos;
    static boolean mouseDown = false;
    static boolean isTowerOnMap;
    static int towerChoosedX;
    static int towerChoosedY;
    static Handle handle;
    static GameController gameController;
    static int selectLevel = 0;
    static int audioPause;

    public Painter() {
        thread = new Thread(this);
        thread.start();
        handle = new Handle();
        gameController = new GameController();
        scene = 0;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        // clear screen
        graphics.clearRect(0, 0, getWidth(), getHeight());
        // screen start
        if (scene == 0) {
            graphics.drawImage(menu, 40, 20, null);

        } else if (scene == 1) {
            //background
            graphics.drawImage(background, 0, 0, 1920, 1080, null);
            //grid
            for (int i=0; i<gameField.getWidth()*gameField.getHeight(); i++) {
                gameField.getEntities().get(i).paint(graphics);
            }
            // health and money
            graphics.setColor(Color.black);
            graphics.drawLine((int)gameField.getWidth()*50, 0, (int)gameField.getWidth()*50, getHeight());
            font = new Font("Serif", Font.BOLD, 50);
            graphics.setFont(font);
            graphics.drawString("Level: "+ wave.getWaveNumber(), 50, 15*50);
            if (player.getHealth() < 0) player.setHealth(0);
            graphics.drawString("Health: " + player.getHealth(), 350, 15*50);
            graphics.drawString("Money: " + player.getMoney(), 750, 15*50);

            //tower list
            for (int i=0; i<3; i++) {
                graphics.drawRect(50*23-10, 100*i+10, 55, 50);
                if (towerList[i].getCost() > player.getMoney()) {
                    graphics.setColor(new Color(255, 0, 0, 125));
                    graphics.fillRect(50*23-10, 100*i+10, 55, 50);
                }
                graphics.setColor(Color.black);
                graphics.drawImage(towerList[i].getTexture(), 50*23-10, 100*i +10, null);
                font = new Font("Serif", Font.ITALIC, 20);
                graphics.setFont(font);
                graphics.drawString("Cost:"+towerList[i].getCost()+"   Attack Speed:"+towerList[i].getAttackSpeed()+"   Range:"+towerList[i].getRange(),50*25-40, i*100+25);
                graphics.drawString("Damage:"+towerList[i].getDamage()+"   Sell:"+towerList[i].getSell(), 50*25-20, i*100+50);
            }

            //tower on grid
            for (int i=0; i<22; i++) {
                for (int j=0; j<12; j++) {
                    if (towerMap[i][j] != null) {
                        if(wave.waveStart) {
                            Attack.towerAttack(i, j);
                            if (towerMap[i][j].target != null) { // tower attack
                                //graphics.setColor(Color.red);
                                //graphics.drawLine(i*50+25, j*50+25, towerMap[i][j].target.getPosX()+25, towerMap[i][j].target.getPosY()+25);
                                graphics.drawImage(towerMap[i][j].bullet.getTexture(), towerMap[i][j].bullet.getPosX(), towerMap[i][j].bullet.getPosY(), null);
                                towerMap[i][j].bullet.fly(i, j);
                            }
                        }

                        graphics.drawImage(towerMap[i][j].getTexture(), i*50, j*50, null);
                        graphics.setColor(Color.GRAY);
                        if (towerMap[towerChoosedX][towerChoosedY] != null) { // tower is choosed
                            graphics.drawOval(towerChoosedX*50+25-towerMap[towerChoosedX][towerChoosedY].getRange()*50,towerChoosedY*50+25-towerMap[towerChoosedX][towerChoosedY].getRange()*50,towerMap[towerChoosedX][towerChoosedY].getRange()*2*50,towerMap[towerChoosedX][towerChoosedY].getRange()*2*50);
                            graphics.setColor(new Color(64, 64, 64, 40));
                            graphics.fillOval(towerChoosedX*50+25-towerMap[towerChoosedX][towerChoosedY].getRange()*50,towerChoosedY*50+25-towerMap[towerChoosedX][towerChoosedY].getRange()*50,towerMap[towerChoosedX][towerChoosedY].getRange()*2*50,towerMap[towerChoosedX][towerChoosedY].getRange()*2*50);
                            graphics.setColor(new Color(255, 255, 0, 50));
                            graphics.drawImage(towerMap[towerChoosedX][towerChoosedY].getTexture(),towerChoosedX*50, towerChoosedY*50, null);
                        }
                    }
                }
            }

            // sell or update tower
            if (isTowerOnMap) {
                graphics.setFont(new Font("Serif", Font.BOLD, 20));
                graphics.setColor(Color.black);
                graphics.drawImage(towerMap[towerChoosedX][towerChoosedY].getTexture(), 1120, 320, null);
                graphics.drawString("Damage: "+towerMap[towerChoosedX][towerChoosedY].getDamage(), 1120,400);
                graphics.drawString("Attack Speed: "+towerMap[towerChoosedX][towerChoosedY].getAttackSpeed(), 1250, 400);
                graphics.drawString("Range: "+towerMap[towerChoosedX][towerChoosedY].getRange(), 1420,400);
                graphics.drawRect(1180, 320, 140, 50);
                graphics.drawRect(1340, 320, 140, 50);
                graphics.drawString("       UPDATE: 75"+"                "+"SELL:  "+towerMap[towerChoosedX][towerChoosedY].getSell(), 1150, 350);
                if (towerMap[towerChoosedX][towerChoosedY].isUpdated()) {
                    graphics.setColor(Color.red);
                    graphics.drawLine(1180, 320, 1320, 370);
                    graphics.drawLine(1180, 370, 1320, 320);
                }
            }

            //enemy
            if (wave.isWaveStart()) {
                graphics.setColor(Color.blue);
                graphics.setFont(new Font("Serif", Font.BOLD, 20));
                wave.getListEnemy().paint(graphics);
                for (int i=0; i<wave.getListEnemy().getEnemyList().size(); i++) {
                    wave.getListEnemy().enemyMove(i);
                }
            }

            //handler
            if (hand != 0 && towerList[hand-1] != null) {
                graphics.drawImage(towerList[hand-1].getTexture(), this.handXPos-25, this.handYPos-25, null);
            }

            //next wave
            graphics.setColor(Color.black);
            if (!Painter.wave.waveStart) graphics.setColor(Color.green);
            graphics.setFont(new Font("Serif", Font.BOLD, 20));
            graphics.drawRect(1180, 420, 250, 50);
            graphics.drawString("ENTER TO NEXT WAVE", 1190, 450);

            //back to main menu
            graphics.setColor(Color.black);
            graphics.drawRect(1180, 520, 250, 50);
            graphics.drawString("MAIN MENU", 1240, 550);

            //pause game
            graphics.drawRect(1180, 620, 250, 50);
            graphics.drawString("PAUSE SOUND", 1230, 650);
            if (Painter.audioPause % 2 == 1) {
                graphics.setColor(Color.red);
                graphics.drawLine(1180, 620, 1430, 670);
                graphics.drawLine(1180, 670, 1430, 620);
            }

            //quit game
            graphics.setColor(Color.red);
            graphics.drawRect(1180, 720, 250, 50);
            graphics.drawString("QUIT GAME", 1250, 750);

            //play again
            if (GameController.endGame()) {
                graphics.drawImage(loseGame, 400, 100, null);
            }
        }
    }

    @Override
    public void run() {

        long FPS = 80;
        long period = 1000*1000000/FPS; //chu ki theo nanotime
        long beginTime = System.nanoTime();
        long sleepTime;

        GameController.loadGame();

        while(running) {
            repaint();

            long deltaTime = System.nanoTime() - beginTime;
            sleepTime = period - deltaTime;

            if (wave.isWaveStart()) {
                wave.addEnemy();
            }

            try {
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime/1000000);
                } else Thread.sleep(period/2000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}

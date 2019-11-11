import entity.bullet.AbstractBullet;
import entity.bullet.NormalBullet;
import entity.enemy.AbstractEnemy;
import entity.tile.tower.AbstractTower;
import entity.tile.tower.MachineGunTower;
import entity.tile.tower.NormalTower;
import entity.tile.tower.SniperTower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Screen extends JPanel implements Runnable {

    private Thread thread;
    private int scene;
    public boolean running = false;
    private Player player;
    private Image menu = new ImageIcon("res/menu/test.png").getImage();
    private Image background = new ImageIcon("res/background/background.png").getImage();
    private Image playagain = new ImageIcon("res/menu/playagain.png").getImage();
    private AbstractTower[] towerList = new AbstractTower[5];
    private GameField gameField;
    private int map[][];
    private AbstractTower towerMap[][];
    private Font font;
    private Wave wave;

    private int hand = 0;
    private int handXPos;
    private int handYPos;
    private static boolean mouseDown = false;
    boolean isTowerOnMap;
    private int numberTower;
    int towerChoosedX;
    int towerChoosedY;

    public Screen() {
        thread = new Thread(this);
        thread.start();
        this.scene = 0;
    }

    public Wave getWave() {
        return wave;
    }

    public int getScene() {
        return scene;
    }

    public Player getPlayer() {
        return player;
    }

    public int[][] getMap() {
        return map;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        // clear screen
        graphics.clearRect(0, 0, getWidth(), getHeight());
        // screen start
        if (scene == 0) {
            graphics.drawImage(menu, 0, 0, 1920, 1080, null);
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
            graphics.drawString("Health: " + player.getHealth(), 350, 15*50);
            graphics.drawString("Money: " + player.getMoney(), 750, 15*50);

            //tower list
            for (int i=0; i<3; i++) {
                graphics.drawImage(towerList[i].getTexture(), 50*23, 100*i +10, 50, 50, null);
                font = new Font("Serif", Font.ITALIC, 20);
                graphics.setFont(font);
                graphics.drawString("Cost:"+towerList[i].getCost()+"   Attack Speed:"+towerList[i].getAttackSpeed()+"   Range:"+towerList[i].getRange(),50*25-40, i*100+25);
                graphics.drawString("Damage:"+towerList[i].getDamage()+"   Sell:"+towerList[i].getSell(), 50*25-20, i*100+50);
            }

            //tower on grid
            for (int i=0; i<22; i++) {
                for (int j=0; j<12; j++) {
                    if (towerMap[i][j] != null) {
                        if(wave.waveStart) towerAttack(i, j);
                        if (towerMap[i][j].target != null) { // tower attack
                            graphics.setColor(Color.red);
                            graphics.drawLine(i*50+25, j*50+25, towerMap[i][j].target.getPosX()+25, towerMap[i][j].target.getPosY()+25);
                            for (int k=0; k<towerMap[i][j].listBullet.size(); k++) {
                                if (towerMap[i][j].listBullet.get(k) != null) {
                                    towerMap[i][j].listBullet.get(k).paint(graphics);
                                    towerMap[i][j].listBullet.get(k).fly(i, j);
                                    //System.out.println("Bullet fly");
                                }
                            }
                        }
                        graphics.drawImage(towerMap[i][j].getTexture(), i*50, j*50, 50, 50, null);
                        if (towerMap[towerChoosedX][towerChoosedY] != null) { // tower is choosed
                            graphics.setColor(Color.GRAY);
                            graphics.drawOval(towerChoosedX*50+25-towerMap[towerChoosedX][towerChoosedY].getRange()*50,towerChoosedY*50+25-towerMap[towerChoosedX][towerChoosedY].getRange()*50,towerMap[towerChoosedX][towerChoosedY].getRange()*2*50,towerMap[towerChoosedX][towerChoosedY].getRange()*2*50);
                            graphics.setColor(new Color(64, 64, 64, 40));
                            graphics.fillOval(towerChoosedX*50+25-towerMap[towerChoosedX][towerChoosedY].getRange()*50,towerChoosedY*50+25-towerMap[towerChoosedX][towerChoosedY].getRange()*50,towerMap[towerChoosedX][towerChoosedY].getRange()*2*50,towerMap[towerChoosedX][towerChoosedY].getRange()*2*50);
                            graphics.setColor(new Color(255, 255, 0, 50));
                            graphics.drawImage(towerMap[towerChoosedX][towerChoosedY].getTexture(),towerChoosedX*50, towerChoosedY*50, 50, 50, null);
                        }
                    }
                }
            }

            // sell or update tower
            if (isTowerOnMap) {
                graphics.setFont(new Font("Serif", Font.BOLD, 20));
                graphics.setColor(Color.black);
                graphics.drawImage(towerMap[towerChoosedX][towerChoosedY].getTexture(), 1120, 320, 50, 50, null);
                graphics.drawString("Damage: "+towerMap[towerChoosedX][towerChoosedY].getDamage(), 1120,400);
                graphics.drawRect(1180, 320, 140, 50);
                graphics.drawRect(1340, 320, 140, 50);
                graphics.drawString("       UPDATE: 50"+"                "+"SELL:  "+towerMap[towerChoosedX][towerChoosedY].getSell(), 1150, 350);
            }

            //enemy
            if (wave.isWaveStart()) {
                wave.getListEnemy().paint(graphics);
                for (int i=0; i<wave.getListEnemy().getEnemyList().size(); i++) {
                    wave.getListEnemy().enemyMove(i);
                }
            }

            //handler
            if (hand != 0 && towerList[hand-1] != null) {
                graphics.drawImage(towerList[hand-1].getTexture(), this.handXPos-25, this.handYPos-25, 50, 50, null);
            }

            //play again
            if (endGame()) {
                graphics.drawImage(playagain, 500, 500, null);
            }
        }
    }

    @Override
    public void run() {

        long FPS = 80;
        long period = 1000*1000000/FPS; //chu ki theo nanotime
        long beginTime = System.nanoTime();
        long sleepTime;

        loadGame();

        while(running) {
            repaint();

            long deltaTime = System.nanoTime() - beginTime;
            sleepTime = period - deltaTime;

            update();

            try {
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime/1000000);
                } else Thread.sleep(period/2000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadGame() {
        wave = new Wave(this);
        running = true;
    }

    public void startGame(){
        this.scene = 1;
        this.player = new Player();
        AbstractTower normalTower = new NormalTower(10, 10, 3, 3, 5, 1D, 1D, "res/tower/tower1.png");
        AbstractTower machineGunTower = new MachineGunTower(15, 100, 2, 2, 10, 1D, 1D, "res/tower/tower3.png");
        AbstractTower sniperTower = new SniperTower(20, 1, 4, 10, 15, 1D, 1D, "res/tower/tower4.png");
        towerList[0] = normalTower;
        towerList[1] = machineGunTower;
        towerList[2] = sniperTower;
        gameField = new GameField(GameStage.load("res/stage/stage1.txt"));
        map = gameField.getMap();
        towerMap = new AbstractTower[(int) gameField.getWidth()][(int) gameField.getHeight()];
    }

    public void stopGame() {
        running = false;
        System.exit(0);
    }

    public boolean endGame() {
        if (scene != 0){
            if (player.getHealth() <= 0) {
                wave.waveStart = false;
                return true;
            }
            return false;
        }
        return false;
    }

    public void update() {
        if (wave.isWaveStart()) {
            wave.addEnemy();
        }
    }

    public void towerAttack(int x, int y) {
        if (towerMap[x][y].target == null) {
            if(towerMap[x][y].attackDelay > towerMap[x][y].maxAttackDelay){
                AbstractEnemy currentEnemy = towerMap[x][y].enemiesAttacked(wave.getListEnemy().getEnemyList(), x, y);
                int i = 0;
                if (currentEnemy != null) {
                    currentEnemy.setHealth(currentEnemy.getHealth()-towerMap[x][y].getDamage());
                    towerMap[x][y].listBullet.add(new NormalBullet(towerMap[x][y], currentEnemy, x*50, y*50));
                    towerMap[x][y].bulletArrayDeque.push(i);
                    towerMap[x][y].target = currentEnemy;
                    towerMap[x][y].attackTime = 0;
                    towerMap[x][y].attackDelay = 0;
                    i++;

                    System.out.println("[Tower] Enemy attacked! health: "+ currentEnemy.getHealth()+" x: "+x+" y: "+y);
                }
            } else {
                towerMap[x][y].attackDelay += towerMap[x][y].getAttackSpeed()/1000;
            }
        } else {
            if (towerMap[x][y].attackTime < towerMap[x][y].maxAttackTime) {
                towerMap[x][y].attackTime += towerMap[x][y].getAttackSpeed()/1000;
            } else {
                towerMap[x][y].target = null;
                if(!towerMap[x][y].bulletArrayDeque.isEmpty()) {
                    towerMap[x][y].listBullet.remove(towerMap[x][y].listBullet.get(towerMap[x][y].bulletArrayDeque.pop()));
                }
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        handXPos = e.getXOnScreen();
        handYPos = e.getYOnScreen();
    }

    public void mouseDowned(MouseEvent e) {
        mouseDown = true;
        System.out.println("X:"+e.getXOnScreen()+" Y:"+e.getYOnScreen());
        if (hand != 0) {
            placeTower(e.getXOnScreen(), e.getYOnScreen());
            hand = 0;
        }
        updateMouse(e);
    }

    public void placeTower(int x, int y) {
        int xPos = x/50;
        int yPos = y/50;

        if (xPos >= 22 || yPos >= 12) {
            hand = 0;
        }
        else if (towerMap[xPos][yPos] == null && map[xPos][yPos] == 5) {
            player.setMoney(player.getMoney() - towerList[hand-1].getCost());
            towerMap[xPos][yPos] = (AbstractTower) towerList[hand-1].clone();
        }
    }

    public void updateMouse(MouseEvent e) {
        if (scene == 1) {
            if (mouseDown && hand == 0 && !endGame()) {
                if (e.getXOnScreen() >= 50 * 23 && e.getXOnScreen() <= 50 * 24) {
                    towerChoosedX = 0;
                    towerChoosedY = 1;
                    isTowerOnMap = false;
                    if (e.getYOnScreen() >= 10 && e.getYOnScreen() <= 60 || e.getYOnScreen() >= 110 && e.getYOnScreen() <= 160 || e.getYOnScreen() >= 210 && e.getYOnScreen() <= 260) {
                        // Tower 1
                        if (e.getXOnScreen() >= 50 * 23 && e.getXOnScreen() <= 50 * 24 && e.getYOnScreen() >= 0 && e.getYOnScreen() <= 50) {
                            int tmp = player.getMoney();
                            if (tmp >= towerList[0].getCost()) {
                                System.out.println("[Shop] You bought a normal tower cost: " + towerList[0].getCost());
                                hand = 1;
                            }
                        }
                        // Tower 2
                        if (e.getXOnScreen() >= 50 * 23 && e.getXOnScreen() <= 50 * 24 && e.getYOnScreen() >= 100 && e.getYOnScreen() <= 150) {
                            int tmp = player.getMoney();
                            if (tmp >= towerList[1].getCost()) {
                                System.out.println("[Shop] You bought a machine gun tower cost: " + towerList[1].getCost());
                                hand = 2;
                            }
                        }
                        // Tower 3
                        if (e.getXOnScreen() >= 50 * 23 && e.getXOnScreen() <= 50 * 24 && e.getYOnScreen() >= 200 && e.getYOnScreen() <= 250) {
                            int tmp = player.getMoney();
                            if (tmp >= towerList[2].getCost()) {
                                System.out.println("[Shop] You bought a sniper tower cost: " + towerList[2].getCost());
                                hand = 3;
                            }
                        }
                    }
                } else if (e.getXOnScreen() >= 0 && e.getXOnScreen() <= 50 * 22) {
                    if (e.getYOnScreen() >= 0 && e.getYOnScreen() <= 50 * 12 && towerMap[e.getXOnScreen() / 50][e.getYOnScreen() / 50] != null) {
                        // tower is choosed
                        if (towerMap[e.getXOnScreen() / 50][e.getYOnScreen() / 50].equals(towerList[0])) {
                            towerChoosedX = e.getXOnScreen()/50;
                            towerChoosedY = e.getYOnScreen()/50;
                            numberTower = 0;
                        }
                        if (towerMap[e.getXOnScreen() / 50][e.getYOnScreen() / 50].equals(towerList[1])) {
                            towerChoosedX = e.getXOnScreen()/50;
                            towerChoosedY = e.getYOnScreen()/50;
                            numberTower = 1;
                        }
                        if (towerMap[e.getXOnScreen() / 50][e.getYOnScreen() / 50].equals(towerList[2])) {
                            towerChoosedX = e.getXOnScreen()/50;
                            towerChoosedY = e.getYOnScreen()/50;
                            numberTower = 2;
                        }
                        isTowerOnMap = true;
                    } else {
                        towerChoosedX = 0;
                        towerChoosedY = 1;
                        isTowerOnMap = false;
                    }
                } else if (e.getYOnScreen() >= 320 && e.getYOnScreen() <= 370) {
                    // update tower
                    if (e.getXOnScreen() >= 1180 && e.getXOnScreen() <= 1320) {
                        if (player.getMoney() >= 50) {
                            towerMap[towerChoosedX][towerChoosedY].updateTower();
                            if (towerMap[towerChoosedX][towerChoosedY].equals(towerList[0])) {
                                System.out.println("number update: " + towerMap[towerChoosedX][towerChoosedY].getNumberUpdate());
                                player.setMoney(player.getMoney() - 50); // money for update
                                if (towerMap[towerChoosedX][towerChoosedY].getNumberUpdate() == 1) { // update 1st
                                    towerMap[towerChoosedX][towerChoosedY] = new NormalTower(10, 10, 3, 6, 15, 1D, 1D, "res/tower/tower2.png");
                                }
                            }
                            if (towerMap[towerChoosedX][towerChoosedY].equals(towerList[1])) {
                                System.out.println("number update: " + towerMap[towerChoosedX][towerChoosedY].getNumberUpdate());
                                player.setMoney(player.getMoney() - 50); // money for update
                                if (towerMap[towerChoosedX][towerChoosedY].getNumberUpdate() == 1) { // update 1st
                                    towerMap[towerChoosedX][towerChoosedY] = new MachineGunTower(15, 100, 2, 4, 15, 1D, 1D, "res/tower/tower2.png");
                                }
                            }
                            if (towerMap[towerChoosedX][towerChoosedY].equals(towerList[2])) {
                                System.out.println("number update: " + towerMap[towerChoosedX][towerChoosedY].getNumberUpdate());
                                player.setMoney(player.getMoney() - 50); // money for update
                                if (towerMap[towerChoosedX][towerChoosedY].getNumberUpdate() == 1) { //update 1st
                                    towerMap[towerChoosedX][towerChoosedY] = new SniperTower(20, 1, 6, 10, 15, 1D, 1D, "res/tower/tower2.png");
                                }
                            }
                        }
                    }
                    // sell tower
                    if (e.getXOnScreen() >= 1340 && e.getXOnScreen() <= 1480) {
                        player.setMoney(player.getMoney() + towerMap[towerChoosedX][towerChoosedY].getSell());
                        towerMap[towerChoosedX][towerChoosedY] = null;
                        isTowerOnMap = false;
                    }
                } else {
                    towerChoosedX = 0;
                    towerChoosedY = 1;
                    isTowerOnMap = false;
                }
            }
            if (endGame()) {
                if (e.getXOnScreen() >= 500 && e.getXOnScreen() <=745) {
                    if (e.getYOnScreen() >=500 && e.getYOnScreen() <= 571) {
                        loadGame();
                        startGame();
                    }
                }
            }
        }
    }

    //end
}

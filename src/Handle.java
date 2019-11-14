import entity.tile.tower.AbstractTower;
import entity.tile.tower.MachineGunTower;
import entity.tile.tower.NormalTower;
import entity.tile.tower.SniperTower;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Handle {
    public void mouseMoved(MouseEvent e) {
        Painter.handXPos = e.getXOnScreen();
        Painter.handYPos = e.getYOnScreen();
    }

    public void mouseDowned(MouseEvent e) {
        Painter.mouseDown = true;
        System.out.println("X:"+e.getXOnScreen()+" Y:"+e.getYOnScreen());
        if (Painter.hand != 0) {
            placeTower(e.getXOnScreen(), e.getYOnScreen());
            Painter.hand = 0;
        }
        updateMouse(e);
    }

    public void placeTower(int x, int y) {
        int xPos = x/50;
        int yPos = y/50;

        if (xPos >= 22 || yPos >= 12) {
            Painter.hand = 0;
        }
        else if (Painter.towerMap[xPos][yPos] == null && Painter.map[xPos][yPos] == 5) {
            Painter.player.setMoney(Painter.player.getMoney() - Painter.towerList[Painter.hand-1].getCost());
            Painter.towerMap[xPos][yPos] = (AbstractTower) Painter.towerList[Painter.hand-1].clone();
        }
    }

    public void updateMouse(MouseEvent e) {
        if (Painter.scene == 1) {
            if (Painter.mouseDown && Painter.hand == 0 && !GameController.endGame()) {
                if (e.getXOnScreen() >= 50 * 23 && e.getXOnScreen() <= 50 * 24) {
                    Painter.towerChoosedX = 0;
                    Painter.towerChoosedY = 1;
                    Painter.isTowerOnMap = false;
                    if (e.getYOnScreen() >= 10 && e.getYOnScreen() <= 60 || e.getYOnScreen() >= 110 && e.getYOnScreen() <= 160 || e.getYOnScreen() >= 210 && e.getYOnScreen() <= 260) {
                        // Tower 1
                        if (e.getXOnScreen() >= 50 * 23 && e.getXOnScreen() <= 50 * 24 && e.getYOnScreen() >= 0 && e.getYOnScreen() <= 50) {
                            int tmp = Painter.player.getMoney();
                            if (tmp >= Painter.towerList[0].getCost()) {
                                System.out.println("[Shop] You bought a normal tower cost: " + Painter.towerList[0].getCost());
                                Painter.hand = 1;
                            }
                        }
                        // Tower 2
                        if (e.getXOnScreen() >= 50 * 23 && e.getXOnScreen() <= 50 * 24 && e.getYOnScreen() >= 100 && e.getYOnScreen() <= 150) {
                            int tmp = Painter.player.getMoney();
                            if (tmp >= Painter.towerList[1].getCost()) {
                                System.out.println("[Shop] You bought a machine gun tower cost: " + Painter.towerList[1].getCost());
                                Painter.hand = 2;
                            }
                        }
                        // Tower 3
                        if (e.getXOnScreen() >= 50 * 23 && e.getXOnScreen() <= 50 * 24 && e.getYOnScreen() >= 200 && e.getYOnScreen() <= 250) {
                            int tmp = Painter.player.getMoney();
                            if (tmp >= Painter.towerList[2].getCost()) {
                                System.out.println("[Shop] You bought a sniper tower cost: " + Painter.towerList[2].getCost());
                                Painter.hand = 3;
                            }
                        }
                    }
                } else if (e.getXOnScreen() >= 0 && e.getXOnScreen() <= 50 * 22) {
                    if (e.getYOnScreen() >= 0 && e.getYOnScreen() <= 50 * 12 && Painter.towerMap[e.getXOnScreen() / 50][e.getYOnScreen() / 50] != null) {
                        // tower is choosed
                        if (Painter.towerMap[e.getXOnScreen() / 50][e.getYOnScreen() / 50].equals(Painter.towerList[0])) {
                            Painter.towerChoosedX = e.getXOnScreen()/50;
                            Painter.towerChoosedY = e.getYOnScreen()/50;
                        }
                        if (Painter.towerMap[e.getXOnScreen() / 50][e.getYOnScreen() / 50].equals(Painter.towerList[1])) {
                            Painter.towerChoosedX = e.getXOnScreen()/50;
                            Painter.towerChoosedY = e.getYOnScreen()/50;
                        }
                        if (Painter.towerMap[e.getXOnScreen() / 50][e.getYOnScreen() / 50].equals(Painter.towerList[2])) {
                            Painter.towerChoosedX = e.getXOnScreen()/50;
                            Painter.towerChoosedY = e.getYOnScreen()/50;
                        }
                        Painter.isTowerOnMap = true;
                    } else {
                        Painter.towerChoosedX = 0;
                        Painter.towerChoosedY = 1;
                        Painter.isTowerOnMap = false;
                    }
                } else if (e.getYOnScreen() >= 320 && e.getYOnScreen() <= 370) {
                    // update tower
                    if (e.getXOnScreen() >= 1180 && e.getXOnScreen() <= 1320) {
                        if (Painter.player.getMoney() >= 50 && !Painter.towerMap[Painter.towerChoosedX][Painter.towerChoosedY].isUpdated()) {
                            Painter.towerMap[Painter.towerChoosedX][Painter.towerChoosedY].setUpdated(true);
                            if (Painter.towerMap[Painter.towerChoosedX][Painter.towerChoosedY].equals(Painter.towerList[0])) {
                                Painter.player.setMoney(Painter.player.getMoney() - 50); // money for update
                                Painter.towerMap[Painter.towerChoosedX][Painter.towerChoosedY] = new NormalTower(10, 10, 3, 6, 20, 1D, 1D, true, "res/tower/tower2.png");
                            }
                            if (Painter.towerMap[Painter.towerChoosedX][Painter.towerChoosedY].equals(Painter.towerList[1])) {
                                Painter.player.setMoney(Painter.player.getMoney() - 50); // money for update
                                Painter.towerMap[Painter.towerChoosedX][Painter.towerChoosedY] = new MachineGunTower(15, 100, 2, 4, 25, 1D, 1D, true, "res/tower/tower2.png");
                            }
                            if (Painter.towerMap[Painter.towerChoosedX][Painter.towerChoosedY].equals(Painter.towerList[2])) {
                                Painter.player.setMoney(Painter.player.getMoney() - 50); // money for update
                                Painter.towerMap[Painter.towerChoosedX][Painter.towerChoosedY] = new SniperTower(20, 1, 6, 10, 30, 1D, 1D, true, "res/tower/tower2.png");
                            }
                        }
                    }
                    // sell tower
                    if (e.getXOnScreen() >= 1340 && e.getXOnScreen() <= 1480) {
                        Painter.player.setMoney(Painter.player.getMoney() + Painter.towerMap[Painter.towerChoosedX][Painter.towerChoosedY].getSell());
                        Painter.towerMap[Painter.towerChoosedX][Painter.towerChoosedY] = null;
                        Painter.isTowerOnMap = false;
                    }
                } else if(e.getXOnScreen() >= 1180 && e.getXOnScreen() <= 1430){
                    if (Painter.hand == 0) {
                        // next wave
                        if (e.getYOnScreen() >= 420 && e.getYOnScreen() <= 470) {
                            if(!Painter.wave.waveStart) Painter.wave.nextWave();
                        }
                        // main menu
                        if (e.getYOnScreen() >= 520 && e.getYOnScreen() <= 570) {
                            Painter.scene = 0;
                            Painter.audio.stop();
                            GameController.loadGame();
                        }
                        // pause
                        if (e.getYOnScreen() >= 620 && e.getYOnScreen() <= 670) {
                            Painter.audioPause++;
                            if(Painter.audioPause % 2 == 1) Painter.audio.pause();
                            if (Painter.audioPause % 2 == 0) Painter.audio.play();
                        }
                        //quit
                        if (e.getYOnScreen() >= 720 && e.getYOnScreen() <= 770) {
                            GameController.stopGame();
                        }
                    }
                } else {
                    Painter.towerChoosedX = 0;
                    Painter.towerChoosedY = 1;
                    Painter.isTowerOnMap = false;
                }
            }
            if (GameController.endGame()) {
                if (e.getXOnScreen() >= 625 && e.getXOnScreen() <= 842) {
                    // play again back to main menu
                    if (e.getYOnScreen() >= 358 && e.getYOnScreen() <= 422) {
//                        GameController.loadGame();
//                        GameController.startGame();
                        Painter.scene = 0;
                        Painter.audio.stop();
                        GameController.loadGame();
                    }
                }
            }
        }
    }
}

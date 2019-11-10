import entity.enemy.AbstractEnemy;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class ListEnemy extends JPanel {
    private List<AbstractEnemy> enemyList = new ArrayList<>();
    private int enemyMap[][];
    private ArrayDeque<Integer> array = new ArrayDeque<>();

    Screen screen;

    public ListEnemy(Screen screen) {
        this.screen = screen;
        enemyMap = screen.getMap();

    }

    public List<AbstractEnemy> getEnemyList() {
        return enemyList;
    }

    @Override
    public void paint(Graphics graphics) {
        for (int i=0; i < enemyList.size(); i++) {
            if (!enemyDeath(i)) {
                AbstractEnemy enemy = enemyList.get(i);
                graphics.drawImage(enemy.getTexture(), enemy.getPosX(), enemy.getPosY(), 50, 50, null);
            } else {
                array.push(i);
            }
        }
        remove();
    }

    public void remove() {
        while (!array.isEmpty()) {
            enemyList.remove(enemyList.get(array.pop()));
        }
    }

    public void enemyMove(int i) {
        AbstractEnemy enemy = enemyList.get(i);
        enemy.move(enemyMap);
    }

    public boolean enemyDeath(int i) {
        AbstractEnemy enemy = enemyList.get(i);
        int x = enemy.getPosX()/50;
        int y = enemy.getPosY()/50;
        if (enemyMap[x][y] == 10) {
            screen.getPlayer().setHealth(screen.getPlayer().getHealth() - enemy.getHealth());
            System.out.println(i + "   move to target");
            return true;
        }
        else if (enemy.getHealth() <= 0) {
            screen.getPlayer().setMoney(screen.getPlayer().getMoney() + enemy.getReward());
            System.out.println(i + " dead");
            return true;
        }
        else return false;
    }
}

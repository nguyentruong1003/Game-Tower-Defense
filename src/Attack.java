import entity.bullet.MachineGunBullet;
import entity.bullet.NormalBullet;
import entity.bullet.SniperBullet;
import entity.enemy.AbstractEnemy;

public class Attack {
    public static void towerAttack(int x, int y) {
        if (Painter.towerMap[x][y].target == null) {
            if(Painter.towerMap[x][y].attackDelay > Painter.towerMap[x][y].maxAttackDelay){
                AbstractEnemy currentEnemy = Painter.towerMap[x][y].enemiesAttacked(Painter.wave.getListEnemy().getEnemyList(), x, y);

                if (currentEnemy != null) {
                    if (Painter.towerMap[x][y].equals(Painter.towerList[0])) Painter.towerMap[x][y].bullet = new NormalBullet(Painter.towerMap[x][y], currentEnemy, x*50+25, y*50+25);
                    if (Painter.towerMap[x][y].equals(Painter.towerList[1])) Painter.towerMap[x][y].bullet = new MachineGunBullet(Painter.towerMap[x][y], currentEnemy, x*50+25, y*50+25);
                    if (Painter.towerMap[x][y].equals(Painter.towerList[2])) Painter.towerMap[x][y].bullet = new SniperBullet(Painter.towerMap[x][y], currentEnemy, x*50+25, y*50+25);
                    //towerMap[x][y].getShootSound().play();
                    currentEnemy.setHealth(currentEnemy.getHealth() - Painter.towerMap[x][y].getDamage());
                    Painter.towerMap[x][y].target = currentEnemy;
                    Painter.towerMap[x][y].attackTime = 0;
                    Painter.towerMap[x][y].attackDelay = 0;

                    System.out.println("[Tower] Enemy attacked! health: "+ currentEnemy.getHealth()+" x: "+x+" y: "+y);
                }
            } else {
                if (Painter.towerMap[x][y].equals(Painter.towerList[0])) Painter.towerMap[x][y].attackDelay += Painter.towerMap[x][y].getAttackSpeed()/30;
                if (Painter.towerMap[x][y].equals(Painter.towerList[1])) Painter.towerMap[x][y].attackDelay += Painter.towerMap[x][y].getAttackSpeed()/30;
                if (Painter.towerMap[x][y].equals(Painter.towerList[2])) Painter.towerMap[x][y].attackDelay += Painter.towerMap[x][y].getAttackSpeed()/30;
            }
        } else {
            if (Painter.towerMap[x][y].attackTime < Painter.towerMap[x][y].maxAttackTime) {
                if (Painter.towerMap[x][y].equals(Painter.towerList[0])) Painter.towerMap[x][y].attackTime += 0.05;
                if (Painter.towerMap[x][y].equals(Painter.towerList[1])) Painter.towerMap[x][y].attackTime += 0.08;
                if (Painter.towerMap[x][y].equals(Painter.towerList[2])) Painter.towerMap[x][y].attackTime += 0.05;
            } else {
                Painter.towerMap[x][y].target = null;
                //towerMap[x][y].getShootSound().stop();
            }
        }
    }
}

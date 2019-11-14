import entity.enemy.*;

public class Wave {
    private ListEnemy listEnemy;

    int waveNumber;
    boolean waveStart;

    int delayTime;
    int nextEnemyTime;
    int numberCurrentEnemy;
    int numberEnemyPerWave ;
    boolean fullEnemySpawn;

    public Wave() {
        this.waveStart = false;
    }

    public ListEnemy getListEnemy() {
        return listEnemy;
    }

    public boolean isWaveStart() {
        return waveStart;
    }

    public int getWaveNumber() {
        return waveNumber;
    }

    public void nextWave() {
        waveNumber++;
        listEnemy = new ListEnemy();
        delayTime = 0;
        nextEnemyTime = 1000 - 200*waveNumber;
        numberCurrentEnemy = 1;
        numberEnemyPerWave = waveNumber*10;
        fullEnemySpawn = false;
        waveStart = true;
    }

    public void addEnemy() {

        if (!fullEnemySpawn) {
            if (numberCurrentEnemy <= numberEnemyPerWave) {
                if (delayTime < nextEnemyTime) {
                    delayTime++;
                } else {
                    delayTime = 0;
                    System.out.println(numberCurrentEnemy);
                    if (numberCurrentEnemy % 10 == 1) listEnemy.getEnemyList().add((AbstractEnemy) new NormalEnemy(20, 2, 5, 0, 50).clone());
                    if (numberCurrentEnemy % 10 == 2) listEnemy.getEnemyList().add((AbstractEnemy) new NormalEnemy(20, 2, 5, 0, 50).clone());
                    if (numberCurrentEnemy % 10 == 3) listEnemy.getEnemyList().add((AbstractEnemy) new SmallerEnemy(15, 4, 15, 0, 50).clone());
                    if (numberCurrentEnemy % 10 == 4) listEnemy.getEnemyList().add((AbstractEnemy) new NormalEnemy(20, 2, 5, 0, 50).clone());
                    if (numberCurrentEnemy % 10 == 5) listEnemy.getEnemyList().add((AbstractEnemy) new NormalEnemy(20, 2, 5, 0, 50).clone());
                    if (numberCurrentEnemy % 10 == 6) listEnemy.getEnemyList().add((AbstractEnemy) new NormalEnemy(20, 2, 5, 0, 50).clone());
                    if (numberCurrentEnemy % 10 == 7) listEnemy.getEnemyList().add((AbstractEnemy) new TankerEnemy(150, 1, 30, 0, 50).clone());
                    if (numberCurrentEnemy % 10 == 8) listEnemy.getEnemyList().add((AbstractEnemy) new NormalEnemy(20, 2, 5, 0, 50).clone());
                    if (numberCurrentEnemy % 10 == 9) listEnemy.getEnemyList().add((AbstractEnemy) new NormalEnemy(20, 2, 5, 0, 50).clone());
                    if (numberCurrentEnemy % 10 == 0) listEnemy.getEnemyList().add((AbstractEnemy) new BossEnemy(100, 2, 50, 0, 50).clone());

                    numberCurrentEnemy++;
                }
            } else {
                fullEnemySpawn = true;
            }
        } else {
            if (listEnemy.getEnemyList().size() <= 0) {
                waveStart = false;
            }
        }
    }
}

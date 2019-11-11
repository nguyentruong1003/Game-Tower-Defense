import entity.enemy.AbstractEnemy;
import entity.enemy.NormalEnemy;

public class Wave {
    private Screen screen;
    private ListEnemy listEnemy;

    int waveNumber;
    boolean waveStart;

    int delayTime;
    int nextEnemyTime;
    int numberCurrentEnemy;
    int numberEnemyPerWave ;
    boolean fullEnemySpawn;

    public Wave(Screen screen) {
        this.screen = screen;
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
        listEnemy = new ListEnemy(screen);
        delayTime = 0;
        nextEnemyTime = 1000 - 150*waveNumber;
        numberCurrentEnemy = 0;
        numberEnemyPerWave = waveNumber*10;
        fullEnemySpawn = false;
        waveStart = true;
    }

    public void addEnemy() {

        if (!fullEnemySpawn) {
            if (numberCurrentEnemy < numberEnemyPerWave) {
                if (delayTime < nextEnemyTime) {
                    delayTime++;
                } else {
                    delayTime = 0;
                    System.out.println(numberCurrentEnemy);
                    listEnemy.getEnemyList().add((AbstractEnemy) new NormalEnemy(10, 10, 2, 5, 0, 50).clone());

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

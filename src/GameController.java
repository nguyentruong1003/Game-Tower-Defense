import entity.tile.tower.AbstractTower;
import entity.tile.tower.MachineGunTower;
import entity.tile.tower.NormalTower;
import entity.tile.tower.SniperTower;
import game.GameField;
import game.GameStage;
import game.Player;

public class GameController {

    public static void loadGame() {
        Painter.wave = new Wave();
        Painter.running = true;
    }

    public static void startGame(){
        Painter.audio.play();
        Painter.audio.setRepeat(true);
        Painter.audioPause = 0;
        Painter.player = new Player();
        AbstractTower normalTower = new NormalTower(15, 5, 3, 3, 5, 1, 1, false);
        AbstractTower machineGunTower = new MachineGunTower(50, 10, 2, 2, 35, 1, 1, false);
        AbstractTower sniperTower = new SniperTower(45, 1, 4, 10, 30, 1, 1, false);
        Painter.towerList[0] = normalTower;
        Painter.towerList[1] = machineGunTower;
        Painter.towerList[2] = sniperTower;
        if (Painter.selectLevel == 1) Painter.gameField = new GameField(GameStage.load("res/stage/stage1.txt"));
        if (Painter.selectLevel == 2) Painter.gameField = new GameField(GameStage.load("res/stage/stage2.txt"));
        if (Painter.selectLevel == 3) Painter.gameField = new GameField(GameStage.load("res/stage/stage3.txt"));
        Painter.map = Painter.gameField.getMap();
        Painter.towerMap = new AbstractTower[(int) Painter.gameField.getWidth()][(int) Painter.gameField.getHeight()];
        Painter.scene = 1;
    }

    public static void stopGame() {
        Painter.running = false;
        System.exit(0);
    }

    public static boolean endGame() {
        if (Painter.scene != 0){
            if (Painter.player.getHealth() <= 0) {
                Painter.wave.waveStart = false;
                return true;
            }
            return false;
        }
        return false;
    }
}

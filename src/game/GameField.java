package game;

import entity.GameEntity;

import java.util.ArrayList;
import java.util.List;

public class GameField{

    private final double width;
    private final double height;
    private List<GameEntity> entities = new ArrayList<GameEntity>();
    private int map[][];

    public List<GameEntity> getEntities() {
        return entities;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public int[][] getMap() {
        return map;
    }

    public GameField(GameStage gameStage) {
        this.width = gameStage.getWidth();
        this.height = gameStage.getHeight();
        entities.addAll(gameStage.getEntities());
        this.map = gameStage.getMap();
    }
}


import entity.GameEntity;
import entity.tile.Mountain;
import entity.tile.Road;
import entity.tile.Spawner;
import entity.tile.Target;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public final class GameStage {
    private final double width;
    private final double height;
    private final List<GameEntity> entities;
    private final int map[][];

    public GameStage(double width, double height, List<GameEntity> entities, int map[][]) {
        this.width = width;
        this.height = height;
        this.entities = List.copyOf(entities);
        this.map = map;
    }

    public static GameStage load(String name) {
        FileInputStream file;
        InputStreamReader reader;
        Scanner scanner;
        try {
            file = new FileInputStream(name);
            reader = new InputStreamReader(file);
            scanner = new Scanner(reader);
            final int width = scanner.nextInt(); //22
            final int height = scanner.nextInt(); //12
            final List<GameEntity> entities = new ArrayList<>(width * height);
            final int map[][] = new int[width][height];
            for (int y =  0; y <height; y++) {
                for (int x = 0; x< width; x++) {
                    final int value = scanner.nextInt();
                    map[x][y] = value;
                    if (value == 5) {
                        entities.add(new Mountain(x*50, y*50, 50, 50, "res/tile/Grass.png"));
                    } else if (value == 1 || value == 2 || value == 3 || value == 4) {
                        entities.add(new Road(x*50, y*50, 50, 50, "res/tile/StoneRoad2.png"));
                    } else if (value == 0) {
                        entities.add(new Road(x*50, y*50, 50, 50, "res/tile/StoneRoad.png"));
                    } else if (value == 6){
                        entities.add(new Mountain(x*50, y*50, 50, 50, "res/tile/BrownRock.png"));
                    } else if (value == 7){
                        entities.add(new Mountain(x*50, y*50, 50, 50, "res/tile/GreyMoutain.png"));
                    } else if (value == 8) {
                        entities.add(new Mountain(x*50, y*50, 50, 50, "res/tile/GrassHole.png"));
                    } else if (value == 9) {
                        entities.add(new Spawner(x*50, y*50, 50, 50, ""));
                    } else if (value == 10){
                        entities.add(new Target(x*50, y*50, 50, 50, "res/tile/YellowRock.png"));
                    }
                }
            }
            return new GameStage(width, height, entities, map);

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public List<GameEntity> getEntities() {
        return entities;
    }

    public int[][] getMap() {
        return map;
    }
}

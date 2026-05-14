package domain;
import domain.gameObjects.*;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelLoader {

    public static Board loadFromFile(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));

        String line = reader.readLine().trim();
        String[] dims = line.split(" ");
        int width = Integer.parseInt(dims[0]);
        int height = Integer.parseInt(dims[1]);

        List<String> lines = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) lines.add(line);
        }
        reader.close();

        Board board = new Board(width, height);

        for (String l : lines) {
            String[] parts = l.split(" ");
            switch (parts[0]) {
                case "START": {
                    double x = Double.parseDouble(parts[1]);
                    double y = Double.parseDouble(parts[2]);
                    double w = Double.parseDouble(parts[3]);
                    double h = Double.parseDouble(parts[4]);
                    board.addStartZone(new StartZone(x, y, w, h, new Color(181,254,180)));
                    break;
                }
                case "END": {
                    double x = Double.parseDouble(parts[1]);
                    double y = Double.parseDouble(parts[2]);
                    double w = Double.parseDouble(parts[3]);
                    double h = Double.parseDouble(parts[4]);
                    board.addEndZone(new EndZone(x, y, w, h, new Color(181,254,180)));
                    break;
                }
                case "CORRIDOR": {
                    int x = Integer.parseInt(parts[1]);
                    int y = Integer.parseInt(parts[2]);
                    int w = Integer.parseInt(parts[3]);
                    int h = Integer.parseInt(parts[4]);
                    board.addCorridor(x, y, w, h);
                    break;
                }
                case "PLAYER": {
                    double x = Double.parseDouble(parts[1]);
                    double y = Double.parseDouble(parts[2]);
                    double w = Double.parseDouble(parts[3]);
                    double h = Double.parseDouble(parts[4]);
                    double speed = Double.parseDouble(parts[5]);
                    board.addPlayer(new Player(x, y, w, h, speed, Color.RED, "Player"));
                    break;
                }
                case "COIN": {
                    double x = Double.parseDouble(parts[1]);
                    double y = Double.parseDouble(parts[2]);
                    double w = Double.parseDouble(parts[3]);
                    double h = Double.parseDouble(parts[4]);
                    board.addCollectionable(new Coin(x, y, w, h, 0, Color.YELLOW));
                    break;
                }
                case "ENEMY": {
                    double x = Double.parseDouble(parts[1]);
                    double y = Double.parseDouble(parts[2]);
                    double w = Double.parseDouble(parts[3]);
                    double h = Double.parseDouble(parts[4]);
                    double speed = Double.parseDouble(parts[5]);
                    int dx = Integer.parseInt(parts[6]);
                    int dy = Integer.parseInt(parts[7]);
                    int minX = Integer.parseInt(parts[8]);
                    int maxX = Integer.parseInt(parts[9]);
                    int minY = Integer.parseInt(parts[10]);
                    int maxY = Integer.parseInt(parts[11]);
                    board.addEnemy(new MovingEnemy(x, y, w, h, speed, Color.BLUE, dx, dy, minX, maxX, minY, maxY));
                    break;
                }
                case "WALL": {
                    double x = Double.parseDouble(parts[1]);
                    double y = Double.parseDouble(parts[2]);
                    double w = Double.parseDouble(parts[3]);
                    double h = Double.parseDouble(parts[4]);
                    board.addWall(new StaticWall(x, y, w, h, new Color(247,247,255)));
                    break;
                }
            }
        }

        return board;
    }
}
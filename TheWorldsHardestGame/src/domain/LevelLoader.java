package domain;

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

        StartZone startZone = null;
        EndZone endZone = null;

        for (String l : lines) {
            String[] parts = l.split(" ");
            if (parts[0].equals("START")) {
                double x = Double.parseDouble(parts[1]);
                double y = Double.parseDouble(parts[2]);
                double w = Double.parseDouble(parts[3]);
                double h = Double.parseDouble(parts[4]);
                startZone = new StartZone(x, y, w, h, Color.GREEN);
            } else if (parts[0].equals("END")) {
                double x = Double.parseDouble(parts[1]);
                double y = Double.parseDouble(parts[2]);
                double w = Double.parseDouble(parts[3]);
                double h = Double.parseDouble(parts[4]);
                endZone = new EndZone(x, y, w, h, Color.GREEN);
            }
        }

        Board board = new Board(width, height, startZone, endZone);

        for (String l : lines) {
            String[] parts = l.split(" ");
            switch (parts[0]) {
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
                    board.addCollectionable(new Collectionable(x, y, w, h, 0, Color.YELLOW));
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
                    board.addEnemy(new MovingEnemy(x, y, w, h, speed, Color.BLUE, dx, dy));
                    break;
                }
                case "WALL": {
                    double x = Double.parseDouble(parts[1]);
                    double y = Double.parseDouble(parts[2]);
                    double w = Double.parseDouble(parts[3]);
                    double h = Double.parseDouble(parts[4]);
                    board.addWall(new StaticWall(x, y, w, h, Color.DARK_GRAY));
                    break;
                }
            }
        }

        return board;
    }
}

package domain;

import java.awt.Color;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domain.players.Player;
import domain.players.Blinky;
import domain.players.Inky;
import domain.players.Clyde;
import domain.enemies.BasicEnemy;
import domain.enemies.VerticalEnemy;
import domain.enemies.AcceleratedEnemy;
import domain.enemies.PatrolEnemy;
import domain.collectables.Coin;
import domain.collectables.SkinChanger;
import domain.walls.StaticWall;
import domain.collectables.Bomb;
import domain.collectables.ExtraLife;
import domain.zones.StartZone;
import domain.zones.EndZone;
import domain.zones.RespawnZone;

/**
 * Clase encargada de cargar configuraciones de nivel desde archivos .txt.
 *
 * Formato del archivo:
 *   Primera linea: ancho alto
 *   Siguientes lineas: TIPO parametros...
 *
 * Tipos soportados:
 *   START x y w h
 *   END x y w h
 *   RESPAWN x y w h
 *   CORRIDOR x y w h
 *   WALL x y w h
 *   COIN x y w h
 *   BOMB x y w h
 *   EXTRALIFE x y w h
 *   SKINCOIN x y w h color(RED|BLUE|GREEN)
 *   ENEMY_BASIC x y w h speed dx dy minX maxX minY maxY
 *   ENEMY_VERTICAL x y w h speed minY maxY
 *   ENEMY_ACCELERATED x y w h speed dx dy minX maxX minY maxY
 *   ENEMY_PATROL centerX centerY radius w h speed
 *   PLAYER x y type(BLINKY|INKY|CLYDE) borderColor(RED|BLUE|GREEN|BLACK|WHITE)
 *
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */
public class LevelLoader {

    public static Board loadFromFile(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));

        String line = reader.readLine().trim();
        String[] dims = line.split(" ");
        int width  = Integer.parseInt(dims[0]);
        int height = Integer.parseInt(dims[1]);

        List<String> lines = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty() && !line.startsWith("#")) lines.add(line);
        }
        reader.close();

        Board board = new Board(width, height);

        for (String l : lines) {
            String[] p = l.split(" ");
            switch (p[0]) {

                case "START": {
                    double x = d(p[1]), y = d(p[2]), w = d(p[3]), h = d(p[4]);
                    board.addStartZone(new StartZone(x, y, w, h, new Color(181, 254, 180)));
                    break;
                }
                case "END": {
                    double x = d(p[1]), y = d(p[2]), w = d(p[3]), h = d(p[4]);
                    board.addEndZone(new EndZone(x, y, w, h, new Color(181, 254, 180)));
                    break;
                }
                case "RESPAWN": {
                    double x = d(p[1]), y = d(p[2]), w = d(p[3]), h = d(p[4]);
                    board.addRespawnZone(new RespawnZone(x, y, w, h, new Color(150, 220, 150)));
                    break;
                }
                case "CORRIDOR": {
                    board.addCorridor(i(p[1]), i(p[2]), i(p[3]), i(p[4]));
                    break;
                }
                case "WALL": {
                    double x = d(p[1]), y = d(p[2]), w = d(p[3]), h = d(p[4]);
                    board.addWall(new StaticWall(x, y, w, h, new Color(80, 80, 120)));
                    break;
                }
                case "COIN": {
                    double x = d(p[1]), y = d(p[2]), w = d(p[3]), h = d(p[4]);
                    board.addCollectionable(new Coin(x, y, w, h, 0, Color.YELLOW));
                    break;
                }
                case "BOMB": {
                    double x = d(p[1]), y = d(p[2]), w = d(p[3]), h = d(p[4]);
                    board.addCollectionable(new Bomb(x, y, w, h, 0, Color.BLACK));
                    break;
                }
                case "EXTRALIFE": {
                    double x = d(p[1]), y = d(p[2]), w = d(p[3]), h = d(p[4]);
                    board.addCollectionable(new ExtraLife(x, y, w, h, 0, new Color(255, 100, 100)));
                    break;
                }
                case "SKINCOIN": {
                    double x = d(p[1]), y = d(p[2]), w = d(p[3]), h = d(p[4]);
                    Color skinColor = parseColor(p[5]);
                    board.addCollectionable(new SkinChanger(x, y, w, h, 0, skinColor));
                    break;
                }
                case "ENEMY_BASIC": {
                    double x = d(p[1]), y = d(p[2]), w = d(p[3]), h = d(p[4]);
                    double speed = d(p[5]);
                    int dx = i(p[6]), dy = i(p[7]);
                    double minX = d(p[8]), maxX = d(p[9]), minY = d(p[10]), maxY = d(p[11]);
                    board.addEnemy(new BasicEnemy(x, y, w, h, speed, dx, dy, minX, maxX, minY, maxY));
                    break;
                }
                case "ENEMY_VERTICAL": {
                    double x = d(p[1]), y = d(p[2]), w = d(p[3]), h = d(p[4]);
                    double speed = d(p[5]);
                    double minY = d(p[6]), maxY = d(p[7]);
                    board.addEnemy(new VerticalEnemy(x, y, w, h, speed, minY, maxY));
                    break;
                }
                case "ENEMY_ACCELERATED": {
                    double x = d(p[1]), y = d(p[2]), w = d(p[3]), h = d(p[4]);
                    double speed = d(p[5]);
                    int dx = i(p[6]), dy = i(p[7]);
                    double minX = d(p[8]), maxX = d(p[9]), minY = d(p[10]), maxY = d(p[11]);
                    board.addEnemy(new AcceleratedEnemy(x, y, w, h, speed, dx, dy, minX, maxX, minY, maxY));
                    break;
                }
                case "ENEMY_PATROL": {
                    double cx = d(p[1]), cy = d(p[2]), radius = d(p[3]);
                    double w = d(p[4]), h = d(p[5]), speed = d(p[6]);
                    board.addEnemy(new PatrolEnemy(cx, cy, radius, w, h, speed));
                    break;
                }
                case "PLAYER": {
                    double x = d(p[1]), y = d(p[2]);
                    String type = p[3];
                    Color borderColor = p.length > 4 ? parseColor(p[4]) : Color.BLACK;
                    Player player = createPlayer(type, x, y, borderColor);
                    board.addPlayer(player);
                    break;
                }
            }
        }
        return board;
    }

    private static Player createPlayer(String type, double x, double y, Color borderColor) {
        switch (type.toUpperCase()) {
            case "INKY":  return new Inky(x, y, borderColor);
            case "CLYDE": return new Clyde(x, y, borderColor);
            default:      return new Blinky(x, y, borderColor);
        }
    }

    private static Color parseColor(String name) {
        switch (name.toUpperCase()) {
            case "RED":    return Color.RED;
            case "BLUE":   return Color.BLUE;
            case "GREEN":  return Color.GREEN;
            case "WHITE":  return Color.WHITE;
            case "YELLOW": return Color.YELLOW;
            default:       return Color.BLACK;
        }
    }

    private static double d(String s) { return Double.parseDouble(s); }
    private static int    i(String s) { return Integer.parseInt(s); }
}
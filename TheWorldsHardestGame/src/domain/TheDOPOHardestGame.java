package domain;

import java.awt.Graphics;

import java.io.IOException;
import java.util.ArrayList;

import domain.players.Player;
import domain.walls.StaticWall;

/**
 * Clase principal del modelo. Gestiona el tablero y expone sus acciones.
 *
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */
public class TheDOPOHardestGame {

    public Board board;

    /**
     * @param levelPath Ruta del archivo .txt del nivel a cargar.
     */
    public TheDOPOHardestGame(String levelPath) throws TWHGException {
        try {
            board = LevelLoader.loadFromFile(levelPath);
            ArrayList<int[]> validCells = board.getAllValidCells();
            for (Player p : board.getPlayers()) {
                p.setValidCells(validCells);
            }
        } catch (IOException e) {
            throw new TWHGException(TWHGException.ERROR_LOADING_THE_LEVEL);
        }
    }

    public void drawAllElements(Graphics g) {
        board.drawAllElements(g);
    }

    public void update() {
        board.update();
    }

    public ArrayList<int[]> getCorridors() {
        return board.getCorridors();
    }

    public ArrayList<Player> getPlayers() {
        return board.getPlayers();
    }

    public ArrayList<StaticWall> getWalls() {
        return board.getWalls();
    }

    public void setEventListener(CollisionController.GameEventListener listener) {
        board.setEventListener(listener);
    }

    public int amountOfPlayers() {
        return board.getAmountOfPlayers();
    }

    public int getBoardWidth() {
        return board.getWidth();
    }

    public int getBoardHeight() {
        return board.getHeight();
    }

    public int getTotalCoins() {
        return board.totalCoins();
    }

    public int getCollectedCoins() {
        return board.getAmountCollectedCoins();
    }
}
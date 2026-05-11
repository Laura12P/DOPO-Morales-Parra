package presentation;

import java.awt.Color;

public class GameConfig {
	
	private static GameConfig singleton;
	private GameMode gameMode;
    private String namePlayer1;
    private Color colorPlayer1;
    private MachineDifficulty machineDifficulty;
    private String namePlayer2;
    private Color colorPlayer2;
    
	private GameConfig() {}
	
	public static final void initSinglePlayer(String name, Color color, MachineDifficulty machineDifficulty) {
		if (machineDifficulty == MachineDifficulty.NONE) {
			singleton.gameMode = GameMode.SINGLE_PLAYER;
		} else {
			singleton.gameMode = GameMode.PLAYER_VS_MACHINE;
		}
        singleton.namePlayer1 = name;
        singleton.colorPlayer1 = color;
        singleton.machineDifficulty = machineDifficulty;
	}
	
	public static final void initDoublePlayer(String namePO, Color colorPO, String namePT, Color colorPT) {
		singleton.gameMode = GameMode.DOUBLE_PLAYER;
        singleton.namePlayer1 = namePO;
        singleton.colorPlayer1 = colorPO;
        singleton.machineDifficulty = MachineDifficulty.NONE;
        singleton.namePlayer2 = namePT;
        singleton.colorPlayer2 = colorPT;
	}
	
	public static final GameConfig getInstance() {
		if (singleton == null) {
			singleton = new GameConfig();
		}
		return singleton;
	}

	public GameMode getGameMode() {
		return gameMode;
	}

	public String getNamePlayer1() {
		return namePlayer1;
	}

	public Color getColorPlayer1() {
		return colorPlayer1;
	}

	public MachineDifficulty getMachineDifficulty() {
		return machineDifficulty;
	}

	public String getNamePlayer2() {
		return namePlayer2;
	}

	public Color getColorPlayer2() {
		return colorPlayer2;
	}
}
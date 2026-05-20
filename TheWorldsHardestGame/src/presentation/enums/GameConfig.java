package presentation.enums;

public class GameConfig {
	
	private static GameConfig singleton;
	private GameMode gameMode;
    private String namePlayerOne;
    private PlayerType typePlayerOne;
    private MachineDifficulty machineDifficulty;
    private String namePlayerTwo;
    private PlayerType typePlayerTwo;
    
	private GameConfig() {}
	
	public static final void initSinglePlayer(String name, PlayerType playerType, MachineDifficulty machineDifficulty) {
		if (machineDifficulty == MachineDifficulty.NONE) {
			singleton.gameMode = GameMode.SINGLE_PLAYER;
		} else {
			singleton.gameMode = GameMode.PLAYER_VS_MACHINE;
		}
        singleton.namePlayerOne = name;
        singleton.typePlayerOne = playerType;
        singleton.machineDifficulty = machineDifficulty;
	}
	
	public static final void initDoublePlayer(String namePO, PlayerType playerTypePO, String namePT, PlayerType playerTypePT) {
		singleton.gameMode = GameMode.DOUBLE_PLAYER;
        singleton.namePlayerOne = namePO;
        singleton.typePlayerOne = playerTypePO;
        singleton.machineDifficulty = MachineDifficulty.NONE;
        singleton.namePlayerTwo = namePT;
        singleton.typePlayerTwo = playerTypePT;
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

	public String getNamePlayerOne() {
		return namePlayerOne;
	}

	public PlayerType getTypePlayerOne() {
		return typePlayerOne;
	}

	public MachineDifficulty getMachineDifficulty() {
		return machineDifficulty;
	}

	public String getNamePlayerTwo() {
		return namePlayerTwo;
	}

	public PlayerType getTypePlayerTwo() {
		return typePlayerTwo;
	}
}
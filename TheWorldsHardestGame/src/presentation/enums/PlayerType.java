package presentation.enums;

import java.awt.Color;

public enum PlayerType {
	BLINKY(Color.RED,1.0f, "Basic Skin"),
	INKY(Color.BLUE,1.5f, "<html><center>The Fastest,<br>but Biggest</center></html>"),
	CLYDE(Color.GREEN,1.0f, "<html><center>Slower If Lose<br>Its ExtraLife</center></html>");
	
	private final Color color;
	private final float sizeMultiplier;
	private final String skill;
	
	PlayerType(Color color, float size, String skill) {
		if (skill == null || skill.length() > 75) {
			throw new IllegalArgumentException("La Habilidad no puede ser nula o tener mas de 75 caracateres.");
		}
		this.color = color;
		this.sizeMultiplier = size;
		this.skill = skill;
	}
	
	public static PlayerType getPlayerTypeFromColor(Color color) {
        if (color == null) {
        	return null;
        }
        for (PlayerType type : PlayerType.values()) {
            if (type.getColor().equals(color)) {
                return type;
            }
        }
        return null;
    }
	
	public Color getColor() {
		return color;
	}
	
	public float getSizeMultiplier() {
		return sizeMultiplier;
	}
	
	public String getSkillDescription() {
		return skill;
	}
}
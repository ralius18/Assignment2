package game;

import java.awt.Graphics;

/**
 * Represents a character in the game (not a player)
 * 
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
public abstract class Character implements Card{
	
	private boolean isSelected = false;
	private boolean isHovering = false;
	
	public boolean isSelected(){
		return isSelected;
	}
	
	public void select(){
		isSelected = true;
	}
	
	public String cardName(){
		return getName();
	}
	
	public abstract String getName();
	
	public abstract char toChar();

	public void setHovering(boolean hovering) {
		this.isHovering = hovering;
	}
	
	/**
	 * Draws the character icon on the board.
	 * @param g
	 * @param x
	 * @param y
	 */
	public abstract void draw(Graphics g, int x, int y);
	
	/**
	 * Returns whether the mouse is hovering over the character icon on the board.
	 * @return
	 */
	public boolean isHovering(){
		return isHovering;
	}

}

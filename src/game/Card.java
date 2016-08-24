package game;

import java.awt.Graphics;

/**
 * Represents a Card that can be in a player's hand or in the solution
 * 
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
public interface Card {
	
	public String cardName();
	
	/**
	 * Draws card image on CardPanel
	 * @param g
	 * @param x
	 * @param y
	 */
	public void drawCard(Graphics g, int x, int y);
}

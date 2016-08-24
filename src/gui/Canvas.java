package gui;

import javax.swing.JPanel;

import game.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Biggest part of main window, onto which the board (with squares and characters) is drawn.
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
@SuppressWarnings("serial")
public class Canvas extends JPanel {

	private Board board;
	private GUI gui;
	
	/**
	 * Sets up basic canvas with mouse motion listener used for listening to clicks and hovering.
	 * @param width
	 * @param height
	 * @param board
	 * @param parent
	 */
	public Canvas(int width, int height, Board board, GUI parent){
		this.gui = parent;
		this.board = board;
		this.setSize(width, height);
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.WHITE);
		this.addMouseMotionListener(new MouseMotionListener(){
			@Override
			public void mouseMoved(MouseEvent e){
				canvasMouseMoved(e);
			}
			
			@Override
			public void mouseDragged(MouseEvent e){
				//No implementation
			}
		});
		this.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				canvasMouseClicked(e);
			}
		});
	}

	/**
	 * Listens to mouse click, calls click method in board class.
	 * @param e
	 */
	private void canvasMouseClicked(MouseEvent e) {
		board.boardMouseClicked(e.getX(), e.getY());
		gui.repaint();
	}

	/**
	 * Listens to mouse movement, calls hover method in board class.
	 * @param e
	 */
	private void canvasMouseMoved(MouseEvent e) {
		board.mouseHover(e.getX(), e.getY());
		gui.repaint();
	}
	
	public void paint(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		g.fillRect(1, 1, getWidth(), getHeight()-2);
		board.draw(g, getWidth(), getHeight());
	}
}

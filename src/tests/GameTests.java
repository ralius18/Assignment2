package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import characters.*;
import control.Dice;
import control.Player;
import game.Board;
import game.Card;
import game.Character;
import game.Weapon;
import locations.Location;
import locations.Room;
import locations.Stairwell;
import locations.StarterSquare;
import rooms.*;
import weapons.Candlestick;

public class GameTests {
	
	@Test
	public void boardTest01(){
		//creates a board with one character (Scarlett)
		Scarlett scarlett = new Scarlett();
		Character[] chars = new Character[1];
		chars[0] = scarlett;
		
		Room[] rooms = createRooms();
		Board board = new Board(chars, rooms);
		Player player = new Player(scarlett);
		Dice dice = board.getDice();
		Weapon weapon = new Candlestick();
		
		//Scarlett should be on a StarterSquare
		Point p = board.getPosition(scarlett);
		Location loc = board.getLocationAtPoint(p);
		assertTrue(loc instanceof StarterSquare);
		
		//should return null as X is not a valid char for the board parser.
		loc = board.parseLocation('X');
		assertTrue(loc == null);
		
		//should give a Stairwell
		loc = board.getSquareFromPoint(new Point(5,1));
		assertTrue(loc instanceof Stairwell);
	}
	
	/**
	 * Helper method.
	 * @return an array of rooms that the game uses.
	 */
	public Room[] createRooms(){
		Room[] rooms = new Room[9];
		rooms[0] = new Kitchen();
		rooms[1] = new Ballroom();
		rooms[2] = new Conservatory();
		rooms[3] = new BilliardRoom();
		rooms[4] = new Library();
		rooms[5] = new Study();
		rooms[6] = new Hall();
		rooms[7] = new Lounge();
		rooms[8] = new DiningRoom();
		return rooms;
	}
}

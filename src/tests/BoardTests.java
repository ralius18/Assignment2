package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import characters.*;
import control.Dice;
import control.Player;
import game.Board;
import game.Character;
import game.Weapon;
import locations.Location;
import locations.Room;
import locations.Stairwell;
import locations.StarterSquare;
import rooms.*;
import weapons.Candlestick;

public class BoardTests{
	
	Scarlett scarlett;
	Character[] chars;
	Board board;
	Player player;
	Dice dice;
	Weapon candlestick;
	
	public BoardTests(){
		scarlett = new Scarlett();
		
		chars = new Character[1];
		chars[0] = scarlett;
		Room[] rooms = createRooms();
		board = new Board(chars, rooms);
		
		dice = board.getDice();
		player = new Player(scarlett);
		candlestick = new Candlestick();
	}
	
	@Test
	public void scarlettStarterSquareTest(){
		//Scarlett should be on a StarterSquare
		Point p = board.getPosition(scarlett);
		Location loc = board.getLocationAtPoint(p);
		assertTrue(loc instanceof StarterSquare);
	}
	
	@Test
	public void parseLocationTest01(){
		Location loc = board.parseLocation('k');
		assertTrue(loc instanceof Kitchen);
	}
	
	@Test
	public void parseLocationTest02(){
		Location loc = board.parseLocation('n');
		assertTrue(loc == null);
	}
	
	@Test
	public void invalidParseLocationTest(){
		boolean caughtSomeError = false;
		//giving this method a 'n' will return, but so will any other key.
		try{
			@SuppressWarnings("unused")
			Location loc = board.parseLocation('X');
		}
		catch(Error e){
			caughtSomeError = true;
			//pass
		}
		if(!caughtSomeError)
			fail("Maybe we should return something else with a invalid char.");
	}
	
	@Test
	public void getSquareFromPointTest(){
		//should give a Stairwell
		Location loc = board.getSquareFromPoint(new Point(5,1));
		assertTrue(loc instanceof Stairwell);
	}
	
	@Test
	public void invalidGetSquareFromPointTest(){
		boolean caughtSomeError = false;
		try{
			Point p = new Point(-1, -1);
			//should break.
			@SuppressWarnings("unused")
			Location loc = board.getSquareFromPoint(p);
		}
		catch(Error e){
			caughtSomeError = true;
			//pass
		}
		if(!caughtSomeError)
			fail("Didn't catch an error, got an object that is out of bounds of the array.");
	}
	
	@Test
	public void setPositionTest(){
		//change scarlett's position and see if it has actually changed.
		Point point = new Point(1,1);
		Point scarlettOriginalPoint = board.getPosition(scarlett);
		board.setPosition(scarlett, point);
		Point scarlettNewPoint = board.getPosition(scarlett);
		//original point should not equal new point
		assertTrue(!scarlettOriginalPoint.equals(scarlettNewPoint));
		//the point scarlett it at should be point (1,1)
		assertEquals(point, scarlettNewPoint);
	}
	
	@Test
	public void invalidSetPositionTest(){
		//try change scarlett's position and see if it has actually changed.
		Point point = new Point(-1,-1);
		Point scarlettOriginalPoint = board.getPosition(scarlett);
		board.setPosition(scarlett, point);
		Point scarlettNewPoint = board.getPosition(scarlett);
		//scarlett should not have moves (original point should equal new point)
		assertTrue(scarlettOriginalPoint.equals(scarlettNewPoint));
		//the point scarlett it at should be point (1,1)
		//assertEquals(point, scarlettNewPoint);
	}
	
	@Test
	public void getLocationAtPointTest(){
		Point p = new Point(1,1);
		Location loc = board.getLocationAtPoint(p);
		//loc should be a Kitchen
		assertTrue(loc instanceof Kitchen);
	}
	
	@Test
	public void invalidGetLocationAtPointTest(){
		Point p = new Point(-1,-1);
		Location loc = board.getLocationAtPoint(p);
		//loc should be a Kitchen
		assertTrue(loc == null);
	}
	
	@Test
	public void getCharactersTest(){
		Character[] boardChars = board.getCharacters();
		assertTrue(boardChars.equals(chars));
		assertTrue(boardChars == chars);
	}
	
	@Test
	public void printBoardTest(){
		board.printBoard();
	}
	
	@Test
	public void getWidthTest(){
		int bWidth = board.getWidth();
		assertEquals(24, bWidth);
	}
	
	@Test
	public void getHeightTest(){
		int bHeight = board.getHeight();
		assertEquals(25, bHeight);
	}
	
	/**
	 * Helper method.
	 * @return an array of rooms that the board uses.
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

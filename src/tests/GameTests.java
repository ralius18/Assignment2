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
import locations.StarterSquare;
import rooms.*;
import weapons.Candlestick;

public class GameTests {
	
	/**
	 * Just leave this for now, will be using it later...
	 */
	@Test
	public void boardTestPrototype(){
		//creates a board with one character (Scarlett)
		Scarlett scarlett = new Scarlett();
		Character[] chars = new Character[1];
		chars[0] = scarlett;
		
		Room[] rooms = createRooms();
		Board board = new Board(chars, rooms);
		Player player = new Player(scarlett);
		Dice dice = board.getDice();
		Weapon weapon = new Candlestick();
		
		// TODO: more testing.
	}
	
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

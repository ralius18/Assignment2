package tests;

import java.awt.Point;

import org.junit.Test;

import characters.Scarlett;
import control.Player;
import game.Board;
import game.Character;
import game.Weapon;
import locations.Location;
import locations.Room;
import rooms.*;

public class GameTests {
	
	@Test
	public void test1(){
		//just create a board.
		setupNewBoard_1();
	}
	
	@Test
	public void test2(){
		//see how many methods work.
		Board b = setupNewBoard_1();
		Location loc =  b.getSquareFromPoint(new Point(1,1));
		assert loc instanceof Room : "This exact location should be a room";
		Room room = (Room)loc;
		assert room.cardName().equals("Kitchen");
	}
	
	@Test
	public void test3(){
		Board b = setupNewBoard_1();
		Point p = b.getPosition(b.getCharacters()[1]);
		assert p.equals(new Point(14,0)); //Mustard's start point
	}
	
	@Test
	public void test4(){
		Board b = setupNewBoard_1();
		Location l1 = b.getLocationAtPoint(new Point(1,1));
		Location l2 = b.getLocationAtPoint(new Point(-1,1));
		Location l3 = b.getLocationAtPoint(new Point(1,-1));
		Location l4 = b.getLocationAtPoint(new Point(-1,-1));
		assert (l1 instanceof Room);
		Room r = (Room) l1;
		assert r.cardName().equals("Kitchen");
		assert l2 == null;
		assert l3 == null;
		assert l4 == null;
	}

	public Board setupNewBoard_1(){
		//Initialising characters
//		Character[] characters = new Character[6];
//		characters[0] = new Character("Scarlett");
//		characters[1] = new Character("Mustard");
//		characters[2] = new Character("White");
//		characters[3] = new Character("Green");
//		characters[4] = new Character("Peacock");
//		characters[5] = new Character("Plum");
//		
//		//Initialising rooms
//		Room[] rooms = new Room[9];
//		rooms[0] = new Room("Kitchen");
//		rooms[1] = new Room("Ball Room");
//		rooms[2] = new Room("Conservatory");
//		rooms[3] = new Room("Billiard Room");
//		rooms[4] = new Room("Library");
//		rooms[5] = new Room("Study");
//		rooms[6] = new Room("Hall");
//		rooms[7] = new Room("Lounge");
//		rooms[8] = new Room("Dining Room");
//		
//		//Initialising weapons
//		Weapon[] weapons = new Weapon[6];
//		weapons[0] = new Weapon("Candlestick");
//		weapons[1] = new Weapon("Dagger");
//		weapons[2] = new Weapon("Lead Pipe");
//		weapons[3] = new Weapon("Revolver");
//		weapons[4] = new Weapon("Rope");
//		weapons[5] = new Weapon("Spanner");
		
		//Create new board
//		return new Board(characters, rooms);
		return null;
	}
	
	public void Test01(){
		//creates a board with one character (Scarlett)
		Scarlett sc = new Scarlett();
		Character[] chars = new Character[1];
		chars[0] = sc;
		Board b = new Board(chars, createRooms());
		Player p = new Player(sc);
		
		//TODO: more testing. May trash the older tests...
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

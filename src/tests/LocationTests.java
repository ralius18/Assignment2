package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import characters.Scarlett;
import control.Player;
import locations.*;
import rooms.Ballroom;

public class LocationTests {
	
	@Test
	public void locationTest01(){
		Location loc = new Middle();
		assertTrue(loc.getValidMoves().isEmpty());
	}

	@Test
	public void locationTest02(){
		Location loc = new Middle();
		Location mid = new Middle();
		loc.actualAddLocation(mid);
		assertFalse(loc.getValidMoves().isEmpty());
		loc.actualAddLocation(mid);
		assertEquals(1, loc.getValidMoves().size());
	}
	
	@Test
	public void locationTest03(){
		Location loc = new Middle();
		Location mid1 = new Middle();
		Location mid2 = new Middle(); //mid 1 and 2 should be equal
		loc.actualAddLocation(mid1);
		assertFalse(loc.getValidMoves().isEmpty());
		loc.actualAddLocation(mid2);
		//should recognize both are the same and not add to list.
		//Could use a Set instead of List.
		assertEquals(1, loc.getValidMoves().size());
	}
	
	@Test
	public void middleTest(){
		Middle m = new Middle();
		m.addLocation(new Middle());
		m.addLocation(null);
		m.print();
	}
	
	@Test
	public void SquareTest(){
		Square s = new Square(false);
		assertTrue(s.getPlayer() == null);
		Scarlett scarlett = new Scarlett();
		Player player = new Player(scarlett);
		s.setPlayer(player);
		assertEquals(player, s.getPlayer());
		assertFalse(s.isEntrance());
	}
	
	@Test
	public void squareAddLocationTest(){
		Square s1 = new Square(true);
		Square s2 = new Square(false);
		s1.addLocation(null);
		s1.addLocation(new Ballroom());
		s2.addLocation(new Ballroom());
		//s2.addLocation(new Ballroom()) should not do anything.
		s2.addLocation(s1);
		s2.addLocation(s2);
		//should not be able to add itself to list of moves?
		assertEquals(1, s2.getValidMoves().size());
		s2.addLocation(new Middle());
		//should not have added a Middle to list of moves.
		assertEquals(1, s2.getValidMoves().size());
	}
	
	@Test
	public void squarePrintTest(){
		Square s1 = new Square(true);
		Square s2 = new Square(false);
		s1.print();
		s2.print();
	}
	
	@Test
	public void stairWellTest(){
		Room br = new Ballroom();
		Stairwell sw = new Stairwell(br);
		assertEquals(br, sw.getOther());
		sw.addLocation(null);
		sw.print();
	}
	
	@Test
	public void starterSquareTest(){
		StarterSquare ss = new StarterSquare();
		ss.addLocation(null);
		ss.addLocation(new Middle());
		ss.addLocation(new Square(false));
		
		ss.print();
	}
}

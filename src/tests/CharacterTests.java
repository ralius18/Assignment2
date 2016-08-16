package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import characters.*;
import game.Character;

public class CharacterTests {
	
	@Test
	public void characterTest01(){
		//Runs all the methods in Character.java (not Green.java!)
		Character ch = new Green();
		
		assertFalse(ch.isSelected());
		ch.select();
		assertTrue(ch.isSelected());
		
		ch.setHovering(true);
		assertTrue(ch.isHovering());
	}
	
	//The rest of the tests run pretty much the same methods.
	//They do not run the draw() methods!!
	
	@Test
	public void characterGreenTest(){
		Green g = new Green();
		assertEquals('G', g.toChar());
		assertEquals("Green", g.getName());
	}
	
	@Test
	public void characterMustardTest(){
		Mustard g = new Mustard();
		assertEquals('M', g.toChar());
		assertEquals("Mustard", g.getName());
	}
	
	@Test
	public void characterPeacockTest(){
		Peacock g = new Peacock();
		assertEquals('P', g.toChar());
		assertEquals("Peacock", g.getName());
	}
	
	@Test
	public void characterPlumTest(){
		Plum g = new Plum();
		assertEquals('L', g.toChar());
		assertEquals("Plum", g.getName());
	}
	
	@Test
	public void characterScarlettTest(){
		Scarlett g = new Scarlett();
		assertEquals('S', g.toChar());
		assertEquals("Scarlett", g.getName());
	}	
	
	@Test
	public void characterWhiteTest(){
		White g = new White();
		assertEquals('W', g.toChar());
		assertEquals("White", g.getName());
	}
}

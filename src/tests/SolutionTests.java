package tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import java.lang.reflect.*;

import game.Solution;
import characters.*;
import rooms.*;
import weapons.*;
import game.Character;
import locations.Room;
import game.Weapon;

public class SolutionTests {
	
	Room room = new Ballroom();
	Character character = new Green();
	Weapon weapon = new Candlestick();
	Solution solution = new Solution(character, room, weapon);
	
	@Test
	public void checkGuessTest(){
		assertTrue(solution.checkGuess(character, weapon, room));
	}
	
	@Test
	public void invalidCheckGuessTest(){
		Room invalidRoom = new BilliardRoom();
		assertFalse(solution.checkGuess(character, weapon, invalidRoom));
	}
	
	@Test
	public void printTest01(){
		solution.print();
	}
	
	@Test
	public void printTest02(){
		try {
			//Change isSolved field of solution to true using reflection
			 Field solved = solution.getClass().getDeclaredField("isSolved");
			 solved.setAccessible(true);
			 solved.set(solution, true);
			 //Should now print character, room and weapon...
			 solution.print();
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			fail("An error occured either during reflection or the print method.");
		}
	}

}

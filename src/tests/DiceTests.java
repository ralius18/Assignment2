package tests;

import static org.junit.Assert.*;

import org.junit.*;

import control.Dice;

/**
 * Dice tests may not work! The only case that it will fail is if all possible roll
 * values are never reached! (Having done 50 rolls and never roll a 1 for example).
 * Tests do pass given that it rolls every possible number that it can.
 * 
 * @author Jarvis Dunn & Brad Stone
 *
 */
public class DiceTests {

	@Test
	public void test1(){
		Dice dice = new Dice(6);
		rollDice(dice, 50, 6);
	}
	
	@Test
	public void test2(){
		Dice dice = new Dice(3);
		rollDice(dice, 50, 3);
	}
	
	@Test
	public void test3(){
		Dice dice = new Dice(12);
		rollDice(dice, 50, 12);
	}
	
	@Test
	public void test4(){
		Dice dice;
		try{
			dice = new Dice(2);
			fail("Error should have been thrown.");
		}
		catch(AssertionError e){
		}
	}
	
	/**
	 * @param dice the dice you want to test.
	 * @param times the amount of rolls you want to do.
	 * @param max the highest number that can be rolled with dice.
	 */
	public void rollDice(Dice dice, int times, int max){
		
		//minimum will always be 1.
		int min = 1;
		
		//all elements in array are null on initialization
		Integer[] uniqueNumbers = new Integer[max];

		for(int i = 1; i <= times; i++){
			int result = dice.roll();
			//System.out.println("Roll: "+i+", Got: "+result);
			if(result < min || result > max)
				fail("With "+min+", "+max+" sided dice, managed to roll: "+result+"\n"
						+ "Highest should be "+max+" and lowest should be "+min+".");
			//add result to uniqueNumbers array
			uniqueNumbers[result - 1] = result;
		}
		//check if all numbers that can be rolled by dice have been rolled.
		for(int i = 0; i < max; i++){
			if(uniqueNumbers[i] == null)
				fail("After "+times+" rolls, never got a result of "+(i+1));
		}
	}
	
}

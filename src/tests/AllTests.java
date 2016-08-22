package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * These tests will just go over coverage of the entire program.
 * There are some specific things that I can't test like moving a character (using an
 * instance of the player class itself) because I can't give it input, it relies on the GUI
 * which is will not be used by these tests.
 * 
 * I got help using this website.
 * http://www.tutorialspoint.com/junit/junit_suite_test.htm
 * 
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
	
	@RunWith(Suite.class)

	@Suite.SuiteClasses({
	   tests.CharacterTests.class,
	   tests.DiceTests.class,
	   tests.PlayerTests.class,
	   tests.BoardTests.class,
	   tests.SolutionTests.class,
	   tests.LocationTests.class,
	   tests.RoomTests.class,
	   tests.WeaponTests.class
	})

	public class AllTests {   
	}  
	
	//also got help using a old swen 221 assignment (assi 2)
	
//	package swen221.assignment2.tests;
//
//	import org.junit.runner.RunWith;
//	import org.junit.runners.Suite;
//
//	@RunWith(Suite.class)
//	@Suite.SuiteClasses({
//	        swen221.assignment2.tests.part1.Part1TestSuite.class,
//	        swen221.assignment2.tests.part2.Part2TestSuite.class,
//	        swen221.assignment2.tests.part3.Part3TestSuite.class
//	})
//	public class CompleteTestSuite {
//	}

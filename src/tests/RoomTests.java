package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import rooms.*;

public class RoomTests {

	@Test
	public void ballroomTest(){
		Ballroom br = new Ballroom();
		assertEquals("Ballroom", br.cardName());
	}
	
	@Test
	public void billiardRoomTest(){
		BilliardRoom br = new BilliardRoom();
		assertEquals("Billard Room", br.cardName());
	}
	
	//TODO: will finnish this off tomorow, too tired and laggy atm...
}

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
	
	@Test
	public void conservatoryTest(){
		Conservatory c = new Conservatory();
		assertEquals("Conservatory", c.cardName());
	}
	
	@Test
	public void diningRoomTest(){
		DiningRoom dr = new DiningRoom();
		assertEquals("Dining Room", dr.cardName());
	}
	
	@Test
	public void hallTest(){
		Hall h = new Hall();
		assertEquals("Hall", h.cardName());
	}
	
	@Test
	public void kitchenTest(){
		Kitchen k = new Kitchen();
		assertEquals("Kitchen", k.cardName());
	}
	
	@Test
	public void libraryTest(){
		Library l = new Library();
		assertEquals("Library", l.cardName());
	}
	
	@Test
	public void loungeTest(){
		Lounge l = new Lounge();
		assertEquals("Lounge", l.cardName());
	}
	
	@Test
	public void studyTest(){
		Study s = new Study();
		assertEquals("Study", s.cardName());
	}
}

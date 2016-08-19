package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import characters.Scarlett;
import control.Player;
import game.Character;
import game.Weapon;
import locations.Room;
import rooms.Hall;
import weapons.Candlestick;

public class PlayerTests {

	@Test
	public void playerTest01(){
		Scarlett scarlett = new Scarlett();
		Player player = new Player(scarlett);
		Weapon weapon = new Candlestick();
		Room hall = new Hall();
		
		player.giveCard(weapon);
		assertFalse(player.getHand().isEmpty());
		assertEquals("[Candlestick]", player.handToString());
		player.giveCard(hall);
		assertEquals("[Candlestick][Hall]", player.handToString());

		assertEquals("Scarlett", player.name()); //not player.getName()?

		//comment this out whenever...
		System.out.println("player.name() null?: "+player.name() == null);
		
		assertEquals(scarlett, player.getCharacter());
		assertTrue(player.getCharacter() instanceof Character);
		assertTrue(player.getCharacter() instanceof Scarlett);
		
		//not sure about how player.getCard method works...
		try{
			//should give null because plyaer does not have scarlett card.
			assertEquals(null, player.getCard(scarlett, null, null));
			player.giveCard(scarlett);
			assertEquals(weapon, player.getCard(null, weapon, null));
			assertEquals(hall, player.getCard(null, null, hall));
		}
		catch(NullPointerException e){
			e.printStackTrace();
			fail();
		}
	}
}

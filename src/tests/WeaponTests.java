package tests;

import static org.junit.Assert.*;
import weapons.*;
import org.junit.Test;

public class WeaponTests {

	@Test
	public void Candlestick(){
		Candlestick cs = new Candlestick();
		assertEquals("Candlestick", cs.cardName());
	}
	
	//TODO: will finnish this off tomorow, too tired and laggy atm...
}

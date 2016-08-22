package tests;

import static org.junit.Assert.*;
import weapons.*;
import org.junit.Test;

public class WeaponTests {

	@Test
	public void candlestickTest(){
		Candlestick cs = new Candlestick();
		assertEquals("Candlestick", cs.cardName());
	}
	
	@Test
	public void knifeTest(){
		Knife kn = new Knife();
		assertEquals("Knife", kn.cardName());
	}
	
	@Test
	public void leadPipeTest(){
		LeadPipe lp = new LeadPipe();
		assertEquals("Lead Pipe", lp.cardName());
	}
	
	@Test
	public void revolverTest(){
		Revolver r = new Revolver();
		assertEquals("Revolver", r.cardName());
	}
	
	@Test
	public void ropeTest(){
		Rope r = new Rope();
		assertEquals("Rope", r.cardName());
	}
	
	@Test
	public void spannerTest(){
		Spanner s = new Spanner();
		assertEquals("Spanner", s.cardName());
	}
}

package gameTests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import clue.ClueGame;


public class GameSetupTests {
	
	ClueGame game;
	
	@BeforeClass
	public void setup(){ 
		game = new ClueGame();
		game.loadConfigFiles();
	}
	
	@Test
	public void testLoadPeople() {
		assertTrue(game.getPlayers().size() == 6);
	}
	
	@Test
	public void testLoadCards() {
		assertTrue(game.getCards().size() == 21);
	}
	
	@Test
	public void testDealCards() {
		game.deal();
	}

}

package gameTests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import clue.*;

public class GameActionTests {
	
static ClueGame game;
	
	// Makes a new game and loads configuration files
	@BeforeClass
	public static void setup(){ 
		game = new ClueGame("componentConfig.csv");
	}
	
	@Test
	public void testAccusation() {
		game.deal();
		game.setSolution("Frodo", "Dagger", "The Shire");
		assertFalse(game.checkAccusation(new Solution("Gollum", "The Ring", "Ash Mountains")));
		assertTrue(game.checkAccusation(new Solution("Frodo", "Dagger", "The Shire")));
	}

}

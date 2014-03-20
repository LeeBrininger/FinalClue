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

		// Sets the solution to a discrete set of values
		game.setSolution("Frodo", "Dagger", "The Shire");
		
		// Tests to make sure that the function won't return true when the solution does
		// not match the correct values
		assertFalse(game.checkAccusation(new Solution("Gollum", "The Ring", "Ash Mountains")));
		
		// Verifies that the function will return true when the correct values are put in
		assertTrue(game.checkAccusation(new Solution("Frodo", "Dagger", "The Shire")));
	}

}

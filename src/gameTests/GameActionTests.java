package gameTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import clue.*;
import clue.Card.cardType;

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
		
		// Tests to make sure that the function won't return true person isn't correct
		assertFalse(game.checkAccusation(new Solution("Gollum", "Dagger", "The Shire")));
		
		// Tests to make sure that the function won't return true weapon isn't correct
		assertFalse(game.checkAccusation(new Solution("Frodo", "Wizard Staff", "The Shire")));
				
		// Tests to make sure that the function won't return true room isn't correct
		assertFalse(game.checkAccusation(new Solution("Frodo", "Dagger", "Rivendell")));
		
		// Verifies that the function will return true when the correct values are put in
		assertTrue(game.checkAccusation(new Solution("Frodo", "Dagger", "The Shire")));
	}
	
	
	// Tests to make sure each type of card can be disproved when there is only one available card to be used
	@Test
	public void testDisproveOnlyCard() {
		Random rand = new Random();
		int index = rand.nextInt(6);
		int accusingIndex = rand.nextInt(6);
		while (index == accusingIndex) accusingIndex = rand.nextInt(6);
		game.getPlayers().get(index).giveCard(new Card (cardType.PLAYER, "Gandalf"));
		game.getPlayers().get(index).giveCard(new Card(cardType.WEAPON, "Longsword"));
		game.getPlayers().get(index).giveCard(new Card(cardType.ROOM, "The Shire"));
		
		// Tests that a player suggestion can be disproved when only one can be shown
		assertEquals(new Card(cardType.PLAYER, "Gandalf"), game.handleSuggestion("Gandalf", "Bow", "Dunland", game.getPlayers().get(accusingIndex)));
		// Tests that a weapon suggestion can be disproved when only one can be shown
		assertEquals(new Card(cardType.WEAPON, "Longsword"), game.handleSuggestion("Gollum", "Longsword", "Rivendell", game.getPlayers().get(accusingIndex)));
		// Tests that a room suggestion can be disproved when only one can be shown
		assertEquals(new Card(cardType.ROOM, "The Shire"), game.handleSuggestion("Frodo", "Wizard Staff", "The Shire", game.getPlayers().get(accusingIndex)));
	}
	
	
	// Tests that if a player has two cards that can be shown that it will return either one randomly
	@Test
	public void testDisproveTwoCards() {
		Random rand = new Random();
		int index = rand.nextInt(6);
		int accusingIndex = rand.nextInt(6);
		while (index == accusingIndex) accusingIndex = rand.nextInt(6);
		game.getPlayers().get(index).giveCard(new Card (cardType.PLAYER, "Gandalf"));
		game.getPlayers().get(index).giveCard(new Card(cardType.WEAPON, "Longsword"));
		game.getPlayers().get(index).giveCard(new Card(cardType.ROOM, "The Shire"));
		
		Card testPlayerWeapon = game.handleSuggestion("Gandalf", "Longsword", "Rhun", game.getPlayers().get(accusingIndex));
		Card testPlayerRoom = game.handleSuggestion("Gandalf", "The Ring", "The Shire", game.getPlayers().get(accusingIndex));
		Card testWeaponRoom = game.handleSuggestion("Sam", "Longsword", "The Shire", game.getPlayers().get(accusingIndex));
		
		// Tests if a player has a player card or a weapon card that it will return one of them randomly 
		assertTrue(testPlayerWeapon == new Card(cardType.PLAYER, "Gandalf") || testPlayerWeapon == new Card(cardType.WEAPON, "Longsword"));
		// Tests if a player has a player card or a room card that it will return one of them randomly 
		assertTrue(testPlayerRoom == new Card(cardType.PLAYER, "Gandalf") || testPlayerWeapon == new Card(cardType.ROOM, "The Shire"));
		// Tests if a player has a weapon card or a room card that it will return one of them randomly 
		assertTrue(testWeaponRoom == new Card(cardType.WEAPON, "Longsword") || testPlayerWeapon == new Card(cardType.ROOM, "The Shire"));
		
	}
	
	// Tests to make sure that if the accusingPlayer is the only player that can disprove his suggestion
	// it returns null 
	@Test
	public void testDisproveAccusingPlayer() {
		Random rand = new Random();
		int index = rand.nextInt(6);
		
		game.getPlayers().get(index).giveCard(new Card(cardType.PLAYER, "Aragorn"));
		game.getPlayers().get(index).giveCard(new Card(cardType.WEAPON, "Battleaxe"));
		game.getPlayers().get(index).giveCard(new Card (cardType.ROOM, "Mirkwood"));
		
		assertEquals(null, game.handleSuggestion("Aragorn", "Battleaxe", "Mirkwood", game.getPlayers().get(index)));
	}
	
	// Tests to make sure that the human player disproves suggestions no differently than computer players
	@Test
	public void testDisproveHumanPlayerOneCard() {
		Random rand = new Random();
		int index = rand.nextInt(6);
		while (index == game.getHumanPlayerIndex()) index = rand.nextInt(6);
		game.getPlayers().get(game.getHumanPlayerIndex()).giveCard(new Card (cardType.PLAYER, "Gandalf"));
		game.getPlayers().get(game.getHumanPlayerIndex()).giveCard(new Card(cardType.WEAPON, "Longsword"));
		game.getPlayers().get(game.getHumanPlayerIndex()).giveCard(new Card(cardType.ROOM, "The Shire"));
		
		// Tests that a player suggestion can be disproved when only one can be shown
		assertEquals(new Card(cardType.PLAYER, "Gandalf"), game.handleSuggestion("Gandalf", "Bow", "Dunland", game.getPlayers().get(index)));
		// Tests that a weapon suggestion can be disproved when only one can be shown
		assertEquals(new Card(cardType.WEAPON, "Longsword"), game.handleSuggestion("Gollum", "Longsword", "Rivendell", game.getPlayers().get(index)));
		// Tests that a room suggestion can be disproved when only one can be shown
		assertEquals(new Card(cardType.ROOM, "The Shire"), game.handleSuggestion("Frodo", "Wizard Staff", "The Shire", game.getPlayers().get(index)));
	
	}
	
	// Tests to make sure that the human player disproves suggestions when it has two cards to show
	// no differently than computer players
	@Test
	public void testDisproveHumanPlayerTwoCards() {
		Random rand = new Random();
		int index = rand.nextInt(6);
		while (index == game.getHumanPlayerIndex()) index = rand.nextInt(6);
		game.getPlayers().get(game.getHumanPlayerIndex()).giveCard(new Card(cardType.PLAYER, "Legolas"));
		game.getPlayers().get(game.getHumanPlayerIndex()).giveCard(new Card(cardType.ROOM, "Rhun"));
		
		Card test = game.handleSuggestion("Legolas", "Dagger", "Rhun", game.getPlayers().get(index));
		// Tests the human player will return one of its cards randomly 
		assertTrue(test == new Card(cardType.PLAYER, "Legolas") || test == new Card(cardType.ROOM, "Rhun"));
	}
	
	// Tests to make sure all players are queried when disproving a suggestion
	@Test
	public void testDisproveQueryInOrder() {
		int index = 0;
		if (index == game.getHumanPlayerIndex()) index++;
		game.getPlayers().get(2).giveCard(new Card(cardType.PLAYER, "Sam"));
		game.getPlayers().get(5).giveCard(new Card(cardType.ROOM, "Ash Mountains"));
		game.getPlayers().get(game.getHumanPlayerIndex()).giveCard(new Card(cardType.PLAYER, "Aragorn"));
		
		// Tests to make sure null is returned when a suggestion is made that no player can disprove
		assertEquals(null, game.handleSuggestion("Legolas", "Longsword", "Mirkwood", game.getPlayers().get(3)));
		// Tests if two players can disprove the suggestion, the player who is first in the list shows its card
		assertEquals(new Card(cardType.PLAYER, "Sam"), game.handleSuggestion("Sam", "Dagger", "Ash Mountains", game.getPlayers().get(4)));
		// Tests if the human player will show its card if it is the only one that can disprove the suggestion
		assertEquals(new Card(cardType.PLAYER, "Aragorn"), game.handleSuggestion("Aragorn", "The Ring", "Rivendell", game.getPlayers().get(game.getHumanPlayerIndex())));
		// Tests if only the accusing player can disprove his suggestion, null is returned
		assertEquals(null, game.handleSuggestion("Gollum", "Bow", "Ash Mountains", game.getPlayers().get(5)));
		// Tests if the last player in the list can disprove a suggestion when the accusing player is the first or second player in the list
		assertEquals(new Card(cardType.ROOM, "Ash Mountains"), game.handleSuggestion("Gandalf", "Wizard Staff", "Aragorn", game.getPlayers().get(index)));
	}
	
	// Tests to make sure that if a room is in the set of targets, it is always chosen
	@Test
	public void testTargetRoom() {
		game.getBoard().findTargets(game.getBoard().getCellAt(game.getBoard().calcIndex(13,9)), 3);
		// for loop to make sure that the room isn't chosen randomly on the first try
		for (int i = 0; i < 3; i++) {
			ComputerPlayer player = new ComputerPlayer("Gandalf", "WHITE", 10);
			BoardCell selected = player.pickLocation(game.getBoard().getTargets());
			assertEquals(game.getBoard().getCellAt(game.getBoard().calcIndex(15,9)), selected);
		}
	}
	
	// Test to make sure the room last visited isn't chosen as a target
	@Test
	public void testConsiderLastRoom() {
		game.getBoard().findTargets(game.getBoard().getCellAt(game.getBoard().calcIndex(13,9)), 3);
		ComputerPlayer player = new ComputerPlayer("Gandalf", "WHITE", 10);
		player.pickLocation(game.getBoard().getTargets());
		// for loop to make sure that a target that isn't the room isn't chosen randomly on the first try
		for (int i = 0; i < 3; i++) {
			BoardCell selected = player.pickLocation(game.getBoard().getTargets());
			assertFalse(game.getBoard().getCellAt(game.getBoard().calcIndex(15,9)) == selected);
		}
	}
	
	// Tests for random target selection
	@Test
	public void testTargetRandomSelection() {
		ComputerPlayer player = new ComputerPlayer("Frodo", "PURPLE", 92);
		// Pick a location with no rooms in target, just four targets
		game.getBoard().calcTargets(14,15, 2);
		int loc_16_15Tot = 0;
		int loc_14_17Tot = 0;
		int loc_14_13Tot = 0;
		int loc_13_14Tot = 0;
		// Run the test 100 times
		for (int i=0; i<100; i++) {
			BoardCell selected = player.pickLocation(game.getBoard().getTargets());
			if (selected == game.getBoard().getCellAt(game.getBoard().calcIndex(16, 15)))
				loc_16_15Tot++;
			else if (selected == game.getBoard().getCellAt(game.getBoard().calcIndex(14, 17)))
				loc_14_17Tot++;
			else if (selected == game.getBoard().getCellAt(game.getBoard().calcIndex(14, 13)))
				loc_14_13Tot++;
			else if (selected == game.getBoard().getCellAt(game.getBoard().calcIndex(13, 14)))
				loc_13_14Tot++;
			else
				fail("Invalid target selected");
		}
		// Ensure we have 100 total selections (fail should also ensure)
		assertEquals(100, loc_16_15Tot + loc_14_17Tot + loc_13_14Tot + loc_14_13Tot);
		// Ensure each target was selected more than once
		assertTrue(loc_16_15Tot > 10);
		assertTrue(loc_14_17Tot > 10);
		assertTrue(loc_14_13Tot > 10);
		assertTrue(loc_13_14Tot > 10);
	}
	
	// Test for when computer makes a suggestion and only one is possible
	@Test
	public void testComputerSuggestionOnePossible() {
		ComputerPlayer player = new ComputerPlayer("Frodo", "PURPLE", 92);
		// Update the player's seen cards with every single one except for the two that he will use
		player.updateSeen(new Card(cardType.PLAYER, "Frodo"));
		player.updateSeen(new Card(cardType.PLAYER, "Sam"));
		player.updateSeen(new Card(cardType.PLAYER, "Gollum"));
		player.updateSeen(new Card(cardType.PLAYER, "Gandalf"));
		player.updateSeen(new Card(cardType.PLAYER, "Legolas"));
		player.updateSeen(new Card(cardType.WEAPON, "Wizard Staff"));
		player.updateSeen(new Card(cardType.WEAPON, "The Ring"));
		player.updateSeen(new Card(cardType.WEAPON, "Longsword"));
		player.updateSeen(new Card(cardType.WEAPON, "Dagger"));
		player.updateSeen(new Card(cardType.WEAPON, "Battleaxe"));
		
		// Sets the player's current location to Mirkwood
		player.setCurrentLocation(game.getBoard().getCellAt(game.getBoard().calcIndex(9, 15)));
		
		assertEquals(new Solution("Aragorn", "Bow", "Mirkwood"), player.createSuggestion());
	}
	
	// Test for when computer makes a suggestion and only one is possible
		@Test
		public void testComputerSuggestionMultiplePossible() {
			ComputerPlayer player = new ComputerPlayer("Frodo", "PURPLE", 92);
			// Update the player's seen cards with every single one except for the two that he will use
			player.updateSeen(new Card(cardType.PLAYER, "Frodo"));
			player.updateSeen(new Card(cardType.PLAYER, "Sam"));
			player.updateSeen(new Card(cardType.PLAYER, "Gollum"));
			player.updateSeen(new Card(cardType.PLAYER, "Gandalf"));
			player.updateSeen(new Card(cardType.WEAPON, "Wizard Staff"));
			player.updateSeen(new Card(cardType.WEAPON, "The Ring"));
			player.updateSeen(new Card(cardType.WEAPON, "Dagger"));
			player.updateSeen(new Card(cardType.WEAPON, "Battleaxe"));
			
			// Sets the player's current location to Mirkwood
			player.setCurrentLocation(game.getBoard().getCellAt(game.getBoard().calcIndex(9, 15)));
			
			int sol_Aragorn_Bow = 0;
			int sol_Aragorn_Longsword = 0;
			int sol_Legolas_Bow = 0;
			int sol_Legolas_Longsword = 0;
			// Run the test 100 times
			for (int i = 0; i<100; i++) {
				Solution solution = player.createSuggestion();
				if (solution == new Solution ("Aragorn", "Bow", "Mirkwood")) sol_Aragorn_Bow++;
				else if (solution == new Solution ("Aragorn", "Longsword", "Mirkwood")) sol_Aragorn_Longsword++;
				else if (solution == new Solution ("Legolas", "Bow", "Mirkwood")) sol_Legolas_Bow++;
				else if (solution == new Solution ("Legolas", "Longsword", "Mirkwood")) sol_Legolas_Longsword++;
				else fail("Invalid suggestion.");
			}
			// Ensure we have 100 total selections (fail should also ensure)
			assertEquals(100, sol_Aragorn_Bow + sol_Aragorn_Longsword + sol_Legolas_Bow + sol_Legolas_Longsword);
			// Ensure each suggestion was selected more than once
			assertTrue(sol_Aragorn_Bow > 10);
			assertTrue(sol_Aragorn_Longsword > 10);
			assertTrue(sol_Legolas_Bow > 10);
			assertTrue(sol_Legolas_Longsword > 10);
		}
	
}

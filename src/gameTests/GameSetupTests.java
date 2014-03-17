package gameTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import clue.*;
import clue.Card.cardType;


public class GameSetupTests {
	
	static ClueGame game;
	
	//Makes a new game and loads configuration files
	@BeforeClass
	public static void setup(){ 
		game = new ClueGame();
		game.loadConfigFiles();
	}
	
	/*
	 Tests loading the people into the game. It first finds the human player and stores that in a list, and then
	 takes two other random players from the list and tests whether or not the name, color, and starting location
	 is correct.
	 */
	@Test
	public void testLoadPeople() {
		assertTrue(game.getPlayers().size() == 6);
		
		ArrayList<Player> testList = new ArrayList<Player>();
		testList.add(game.getPlayers().get(game.getHumanPlayerIndex()));
		Random rand = new Random();
		int x = -1;
		while (x!=game.getHumanPlayerIndex() && x != -1) x = rand.nextInt(game.getPlayers().size());
		testList.add(game.getPlayers().get(x));
		x = -1;
		while (x!=game.getHumanPlayerIndex() && x != -1) x = rand.nextInt(game.getPlayers().size());
		testList.add(game.getPlayers().get(x));
		
		for (int i = 0; i < 3; i++) {
			Player player = testList.get(i);
			switch (game.getPlayers().indexOf(testList.get(i))) {
				case 0:
					assertEquals("Professor Plum", player.getName());
					assertEquals(new Color(102, 0, 102), player.getColor());
					// TODO starting Location (need excel spreadsheet)
					break;
				case 1:
					assertEquals("Mr. Green", player.getName());
					assertEquals(Color.GREEN, player.getColor());
					// TODO starting Location (need excel spreadsheet)
					break;
				case 2:
					assertEquals("Ms. Scarlett", player.getName());
					assertEquals(Color.RED, player.getColor());
					// TODO starting Location (need excel spreadsheet)
					break;
				case 3:
					assertEquals("Mrs. White", player.getName());
					assertEquals(Color.WHITE, player.getColor());
					// TODO starting Location (need excel spreadsheet)
					break;
				case 4:
					assertEquals("Colonel Mustard", player.getName());
					assertEquals(Color.YELLOW, player.getColor());
					// TODO starting Location (need excel spreadsheet)
					break;
				case 5:
					assertEquals("Ms. Peacock", player.getName());
					assertEquals(Color.BLUE, player.getColor());
					// TODO starting Location (need excel spreadsheet)
					break;
			}
		}
		
		
	}
	
	
	/*Tests that the deck has the right amount of cards, the right amount of cards, and if a selection of each
	type of card is in the deck*/
	@Test
	public void testLoadCards() {
		assertTrue(game.getCards().size() == 21);
		int players = 0;
		int weapons = 0;
		int rooms = 0;
		for (int i = 0; i < game.getCards().size(); i++) {
			switch (game.getCards().get(i).getCardType()) {
				case WEAPON:
					weapons++;
					break;
				case ROOM:
					rooms++;
					break;
				case PLAYER: 
					players++;
					break;
			}
		}
		assertEquals(9, rooms);
		assertEquals(6, players);
		assertEquals(6, weapons);
		Card player = new Card(cardType.PLAYER, "Ms. Scarlett");
		Card weapon = new Card(cardType.WEAPON, "Lead Pipe");
		Card room = new Card(cardType.ROOM, "Lounge");
		assertTrue(game.getCards().contains(player));
		assertTrue(game.getCards().contains(weapon));
		assertTrue(game.getCards().contains(room));
	}
	
	@Test
	public void testDealCards() {
		game.deal();
	}

}

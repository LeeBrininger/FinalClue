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
					assertEquals("Frodo", player.getName());
					assertEquals(new Color(102, 0, 102), player.getColor());
					assertEquals(92, player.getStartLocation());
					break;
				case 1:
					assertEquals("Sam", player.getName());
					assertEquals(Color.GREEN, player.getColor());
					assertEquals(253, player.getStartLocation());
					break;
				case 2:
					assertEquals("Aragorn", player.getName());
					assertEquals(Color.RED, player.getColor());
					assertEquals(190, player.getStartLocation());
					break;
				case 3:
					assertEquals("Gandalf", player.getName());
					assertEquals(Color.WHITE, player.getColor());
					assertEquals(10, player.getStartLocation());
					break;
				case 4:
					assertEquals("Legolas", player.getName());
					assertEquals(Color.YELLOW, player.getColor());
					assertEquals(229, player.getStartLocation());
					break;
				case 5:
					assertEquals("Gollum", player.getName());
					assertEquals(Color.BLUE, player.getColor());
					assertEquals(457, player.getStartLocation());
					break;
			}
		}
		
		
	}
	
	
	/*Tests that the deck has the right amount of cards, the right amount of cards, and if a selection of each
	type of card is in the deck*/
	@Test
	public void testLoadCards() {
		assertTrue(game.getCards().size() == 22);
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
		assertEquals(10, rooms);
		assertEquals(6, players);
		assertEquals(6, weapons);
		Card player = new Card(cardType.PLAYER, "Sam");
		Card weapon = new Card(cardType.WEAPON, "Bow");
		Card room = new Card(cardType.ROOM, "Rivendell");
		assertTrue(game.getCards().contains(player));
		assertTrue(game.getCards().contains(weapon));
		assertTrue(game.getCards().contains(room));
	}
	
	/*Tests that there are 22 cards dealt, that a card is not dealt twice, and that each player has between 2 and 6 cards*/
	@Test
	public void testDealCards() {
		game.deal();
		ArrayList<Card> existing = new ArrayList<Card>();
		int cardsDealt = 0;
		for (int i = 0; i < 6; i++) {
			ArrayList<Card> cards = game.getPlayers().get(i).getCards();
			assertTrue(cards.size() < 6 && cards.size() > 2);
			for (int j = 0; j < cards.size(); j++) {
				assertFalse(existing.contains(cards.get(j)));
				existing.add(cards.get(j));
			}
			cardsDealt += cards.size();
		}
		assertTrue(cardsDealt == 22);
	}

}

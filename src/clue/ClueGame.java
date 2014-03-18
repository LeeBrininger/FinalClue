package clue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import clue.Card.cardType;


public class ClueGame {
	private ArrayList<Card> cards;
	private ArrayList<Player> players;
	private int humanPlayerIndex;
	private Solution solution;
	private Board board;
	private File componentConfig;
	
	public ClueGame(String componentFile) {
		componentConfig = new File(componentFile);
		cards = new ArrayList<Card>();
		players = new ArrayList<Player>();
		loadConfig();
	}
	
	public void deal() {
		
	}
	
	public void loadConfig() {
		try {
			Scanner scan = new Scanner(componentConfig);
			while (scan.hasNextLine()) {
				String next = scan.nextLine();
				String[] separated = next.split(",");
				if (separated[0].equals("PLAYER")) players.add(new Player (separated[1], separated[2],Integer.parseInt(separated[3])));
				cards.add(new Card(cardType.valueOf(separated[0]),separated[1]));
			}
			//FIXME set to actual human player, currently hardcoded
			
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void makePlayer(String name, String color, int startLoc) {
		
	}
	
	public void selectAnswer() {
		
	}
	
	public void handleSuggestion(String person, String room, String weapon, Player accusingPerson) {
		
	}
	
	public boolean checkAccusation(Solution solution) {
		return false;
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public int getHumanPlayerIndex() {
		return humanPlayerIndex;
	}
}

package clue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
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
		Collections.shuffle(cards);
		int nextCard = 0, nextPlayer = 0;
		Random rand = new Random();
		String room = "", weapon = "", person = "";
		int roomInd, weaponInd, personInd;
		int r = rand.nextInt(cards.size());
		while (cards.get(r).getCardType() != cardType.PLAYER) r=rand.nextInt(cards.size());
		person = cards.get(r).name;
		personInd=r;
		while (cards.get(r).getCardType() != cardType.ROOM) r=rand.nextInt(cards.size());
		room = cards.get(r).name;
		roomInd = r;
		while (cards.get(r).getCardType() != cardType.WEAPON) r=rand.nextInt(cards.size());
		weapon = cards.get(r).name;
		weaponInd = r;
		
		setSolution(person, weapon, room);
		
		while (nextCard < cards.size()) {
			if (nextCard != roomInd && nextCard != weaponInd && nextCard != personInd) {
				players.get(nextPlayer).giveCard(cards.get(nextCard));
				
				if (nextPlayer == players.size()-1) nextPlayer = 0;
				else nextPlayer++;
			}
			nextCard++;
		}
	}
	
	public void loadConfig() {
		try {
			Scanner scan = new Scanner(componentConfig);
			Random rand = new Random();
			int character = rand.nextInt(6);
			for (int i=0; scan.hasNextLine(); i++) {
				String next = scan.nextLine();
				String[] separated = next.split(",");
				if (separated[0].equals("PLAYER")) {
					if (i == character) {
						players.add(new HumanPlayer (separated[1], separated[2],Integer.parseInt(separated[3])));
						humanPlayerIndex = i;
					}
					else players.add(new ComputerPlayer (separated[1], separated[2],Integer.parseInt(separated[3])));
				}
				cards.add(new Card(cardType.valueOf(separated[0]),separated[1]));
			}
			
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void setSolution(String person, String weapon, String room) {
		solution = new Solution(person, weapon, room);
	}
	
	public void makePlayer(String name, String color, int startLoc) {
		
	}
	
	public void selectAnswer() {
		
	}
	
	public Card handleSuggestion(String person, String weapon, String room, Player accusingPerson) {
		return null;
	}
	
	public boolean checkAccusation(Solution solution) {
		if (this.solution.equals(solution)) return true;
		else return false;
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
	
	public Solution getSolution() {
		return solution;
	}
}

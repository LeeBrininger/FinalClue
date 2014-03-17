package clue;
import java.util.ArrayList;


public class ClueGame {
	private ArrayList<Card> cards;
	private ArrayList<Player> players;
	private int humanPlayerIndex;
	private Solution solution;
	private Board board;
	
	public void deal() {
		
	}
	
	public void loadConfigFiles() {
		
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

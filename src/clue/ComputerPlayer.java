package clue;
import java.util.ArrayList;
import java.util.Set;


public class ComputerPlayer extends Player {

	private ArrayList<Card> unseenCards;
	
	public ComputerPlayer(String name, String color, int startLocation) {
		super(name, color, startLocation);
		unseenCards = new ArrayList<Card>();
	}

	private char lastRoomVisited;
	
	
	public BoardCell pickLocation(Set<BoardCell> targets) {
		return null;
	}
	
	public Solution createSuggestion() {
		return null;
	}
	
	public void updateSeen(Card seen) {
		
	}
	
}

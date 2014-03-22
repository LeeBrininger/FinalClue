package clue;
import java.util.Set;


public class ComputerPlayer extends Player {

	public ComputerPlayer(String name, String color, int startLocation) {
		super(name, color, startLocation);
	}

	private char lastRoomVisited;
	
	
	public BoardCell pickLocation(Set<BoardCell> targets) {
		return null;
	}
	
	public void createSuggestion() {
		
	}
	
	public void updateSeen(Card seen) {
		
	}
	
}

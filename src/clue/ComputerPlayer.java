package clue;
import java.util.ArrayList;
import java.util.Set;


public class ComputerPlayer extends Player {

	private ArrayList<Card> unseenCards;
	private char lastRoomVisited;
	private BoardCell target;
	
	public ComputerPlayer(String name, String color, int startLocation) {
		super(name, color, startLocation);
		unseenCards = new ArrayList<Card>();
	}
	
	
	public BoardCell pickLocation(Set<BoardCell> targets) {
		// In case of no room targets
		target = targets[0];
		
		for (targets : i) {
			i.next();
			if (i.isRoom()) {
				if (lastRoomVisited.equals(i.getCellCode)) {
					// Do nothing if it's the last visited room
					continue;
				} else {
					target = i;
				}
			}
		}
		return target;
	}
	
	public Solution createSuggestion() {
		return null;
	}
	
	public void updateSeen(Card seen) {
		
	}
	
}

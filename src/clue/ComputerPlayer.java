package clue;
import java.util.ArrayList;
import java.util.Set;
import java.util.Random;


public class ComputerPlayer extends Player {

	private ArrayList<Card> unseenCards;
	private char lastRoomVisited;
	private BoardCell target;
	
	public ComputerPlayer(String name, String color, int startLocation) {
		super(name, color, startLocation);
		unseenCards = new ArrayList<Card>();
	}
	
	
	public BoardCell pickLocation(Set<BoardCell> targets) {
		boolean foundRoom = false;
		
		for (int i = 0; i < targets.length(); i++) {
			if (targets[i].isRoom()) {
				if (lastRoomVisited.equals(targets[i].getCellCode)) {
					// Do nothing if it's the last visited room
					continue;
				} else {
					foundRoom = true;
					target = targets[i];
				}
			}
			if (!foundRoom) {
				Random random = new Random();
				int randomTarget = random.nextInt(targets.length()) - 1;
				target = targets[randomTarget];
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

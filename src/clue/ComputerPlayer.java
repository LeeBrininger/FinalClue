package clue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Random;

import clue.Card.cardType;


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
		Random rand = new Random();
		Card player = unseenCards.get(rand.nextInt(unseenCards.size()));
		while (player.getCardType() != cardType.PLAYER) player = unseenCards.get(rand.nextInt(unseenCards.size()));
		Card weapon = unseenCards.get(rand.nextInt(unseenCards.size()));
		while (weapon.getCardType() != cardType.WEAPON) weapon = unseenCards.get(rand.nextInt(unseenCards.size()));
		return new Solution(player.getName(), weapon.getName(), ((RoomCell) getCurrentLocation()).decodeRoomInitial(((RoomCell) getCurrentLocation()).getInitial()));
	}
	
	public void updateSeen(Card seen, ArrayList<Card> deck) {
		if (unseenCards.size() == 0) {
			unseenCards = deck;
			for (int i = 0; i<9; i++) unseenCards.remove(unseenCards.size()-1);
		}
		unseenCards.remove(seen);
	}
	
}

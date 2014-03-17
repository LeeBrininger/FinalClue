package clue;

public class Card {
	public enum cardType {ROOM, WEAPON, PLAYER};
	
	public String name;
	private cardType type;
	
	
	public Card(cardType type, String name) {
		this.name = name;
		this.type = type;
	}
	
	public cardType getCardType() {
		return type;
	}
	
	
}

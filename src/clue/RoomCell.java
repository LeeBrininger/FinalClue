package clue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

//An enumerated type named DoorDirection that specifies the location of the door relative to the room. 
//Values will be UP, DOWN, LEFT, RIGHT and NONE.
//Door direction is explained in more detail below.

public class RoomCell extends BoardCell{
	 enum DoorDirection{
		UP, DOWN, LEFT, RIGHT, NONE
	};
	private DoorDirection direction;
	private char doorCode;

	//ArrayList<>
	public RoomCell(int row, int column, String C, String D){
		super(row, column);
		cellCode = C.charAt(0);
		decodeDirection(D);
		doorCode = D.charAt(0);
	}
	
	public RoomCell() {
		
	}
	public DoorDirection getDirection(){
		return direction;
	}
	
	public String decodeRoomInitial(char code) {
		String roomName= "";
		switch (code) {
			case 'R': 
				roomName = "Rohan";
				break;
			case 'D':
				roomName = "Dunland";
				break;
			case 'I':
				roomName = "Mirkwood";
				break;
			case 'S': 
				roomName = "The Shire";
				break;
			case 'N': 
				roomName = "Rivendell";
				break;
			case 'G': 
				roomName = "Gondor";
				break;
			case 'A': 
				roomName = "Ash Mountains";
				break;
			case 'M':
				roomName = "Mordor";
				break;
			case 'U': 
				roomName = "Rhun";
				break;
		}
		
		return roomName;
	}
	
	public void decodeDirection(String D){
        char d = D.charAt(0);
		
		switch(d){
	
			case('U'): direction = DoorDirection.UP;
						break;
			case('L'): direction = DoorDirection.LEFT;
						break;
			case('R'): direction = DoorDirection.RIGHT;
						break;
			case('D'): direction = DoorDirection.DOWN;
						break;
			
			default: direction = DoorDirection.NONE;
					break;
				
		
		}
	}

	
//	A variable of type DoorDirection named doorDirection.
//	A variable of type char to hold the room initial
//	(we'll need this later, when drawing the room name on the screen).
//	Override the method isRoom to return true.
//	We will also override the draw method, when we do the GUI
	// DoorDirection doorDirection;

	public boolean isRoom(char room){
		return true;
	}

	public char getInitial() {
		return cellCode;
	}
	public String toString (){
		String roomCellInfo =super.toString();
		 roomCellInfo += " Room: ";
		if(!(this.direction==DoorDirection.NONE)){
			roomCellInfo += "Direction: " + this.direction;
		}
		return roomCellInfo;
			
	}
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle rect = new Rectangle(getColumn()*25, getRow()*25, 25, 25);
		
		
		switch (cellCode) {
		case 'M':
			g2.setColor(Color.RED);
			break;
		case 'R':
			g2.setColor(Color.ORANGE);
			break;
		case 'S':
			g2.setColor(Color.GREEN);
			break;
		case 'I':
			g2.setColor(Color.CYAN);
			break;
		case 'N':
			g2.setColor(Color.WHITE);
			break;
		case 'D':
			g2.setColor(Color.BLUE);
			break;
		case 'G':
			g2.setColor(Color.PINK);
			break;
		case 'A':
			g2.setColor(Color.GRAY);
			break;
		case 'U':
			g2.setColor(Color.LIGHT_GRAY);
			break;
		}
		g2.fill(rect);
		g2.draw(rect);

		if (isDoorway("" + cellCode+doorCode)) {
			g2.setColor(Color.MAGENTA);
			Rectangle door = null;
			switch (direction) {
				case UP:
					door = new Rectangle(getColumn()*25, getRow()*25, 25, 25/4);
					break;
				case DOWN:
					door = new Rectangle(getColumn()*25, getRow()*25+25*3/4, 25, 25/4);
					break;
				case RIGHT:
					door = new Rectangle(getColumn()*25+25*3/4, getRow()*25, 25/4, 25);
					break;
				case LEFT:
					door = new Rectangle(getColumn()*25, getRow()*25, 25/4, 25);
					break;
				case NONE:
					break;
			}
			
			g2.fill(door);
		}
	}
	

}
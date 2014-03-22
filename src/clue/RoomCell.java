package clue;
import java.util.HashMap;
import java.util.Map;

//An enumerated type named DoorDirection that specifies the location of the door relative to the room. 
//Values will be UP, DOWN, LEFT, RIGHT and NONE.
//Door direction is explained in more detail below.

public class RoomCell extends BoardCell{
	 enum DoorDirection{
		UP, DOWN, LEFT, RIGHT, NONE
	};
	private DoorDirection direction;

	//ArrayList<>
	public RoomCell(int row, int column, String C, String D){
		super(row, column);
		cellCode = C.charAt(0);
		decodeDirection(D);
	
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
	

}

public class WalkwayCell extends BoardCell {
	public static int numWalkWay;
	public WalkwayCell(int row, int column){
		super(row, column);
		cellCode = 'W';
	}
	public static boolean isWalkway(char walkway){
		return true;
	}
	public String toString (){
		
		return super.toString()+" Walkway";
	}
	

}
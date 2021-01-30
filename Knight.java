import java.awt.Toolkit;

public class Knight extends Piece {

	boolean canMove;
	
	public Knight(int x, int y, boolean color,Board brd) {
		super(x,y,color,brd);
		if(color)
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_nlt60.png"));
		else
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_ndt60.png"));
			
	}

	public boolean move(int newx, int newy){
		canMove = false;
		if(brd.getpiece(newx, newy) == null || brd.getpiece(newx, newy).color != color)
			canMove = true;

		if(newx == x && Math.abs(newy- y) > 1 && Math.abs(newy - y) <5)
			canMove = true;
		else if(newy == y && Math.abs(newx- x) > 1 && Math.abs(newx - x) <5)
			canMove = true;
		else if(Math.abs(newy - y)== Math.abs(newx - x)&& Math.abs(newx- x) >1 && Math.abs(newx - x) <5)
			canMove = true;

		return canMove;
	}
	
}

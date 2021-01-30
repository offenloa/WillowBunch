import java.awt.Toolkit;

public class King extends Piece {

	boolean canMove;
	
	public King(int x, int y, boolean color, Board brd) {
		super(x,y,color,brd);
		if(color)
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_klt60.png"));
		else
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_kdt60.png"));
			
	}
 
	public boolean move(int newx, int newy){
		canMove = false;
		if(Math.abs(newx - x) < 2 && Math.abs(newy - y) < 2)
			canMove = true;
		
		if(brd.getpiece(newx,newy).color == color)
			canMove = false;
		return canMove;
		
	}

	
}

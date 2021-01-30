import java.awt.Toolkit;
public class Bishop extends Piece {

	boolean canMove;
	int directionX, directionY;
	
	public Bishop(int x, int y, boolean color, Board brd) {
		super(x,y,color,brd);
		if(color)
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_blt60.png"));
		else
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_bdt60.png"));
			
	}

	public boolean move(int newx, int newy){
		canMove = true;

		if(Math.abs(newx - x) != Math.abs(newy - y))
			canMove = false;

		if (newx > x)
			directionX = 1;
		else
			directionX = -1;
		
		if (newy > y)
			directionY = 1;
		else
			directionY = -1;

		for(int i = 1; i < Math.abs(newx - x) - 1; i++){
			if(brd.getpiece(x+i*directionX, y+i*directionY) != null)
				canMove = false;
		}

		if(brd.getpiece(newx, newy) != null && brd.getpiece(x+Math.abs(newx - x) * directionX, y+Math.abs(newy - y) * directionY).color == color)
			canMove = false;

		return canMove;

	}

	
	
}

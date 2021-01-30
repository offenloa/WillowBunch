import java.awt.Toolkit;

public class Queen extends Piece {

	boolean canMove;
	int directionX, directionY;
	
	public Queen(int x, int y, boolean color, Board brd) {
		super(x,y,color,brd);
		if(color)
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_qlt60.png"));
		else
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_qdt60.png"));
			
	}

	public boolean move(int newx, int newy){
		canMove = true;

		if(brd.getpiece(newx, newy) != null && brd.getpiece(newx,newy).color == color)
				canMove = false;
		if (newx > x)
			directionX = 1;
		else
			directionX = -1;
		
		if (newy > y)
			directionY = 1;
		else
			directionY = -1;
		
		if(newx == x){

			for(int i = 1;i < Math.abs(newy - y);i++){
				if(brd.getpiece(x,y+i*directionY)!= null)
					canMove = false;
			}	
		}
		
		else if(newy == y){

			for(int i = 1;i < Math.abs(newx - x);i++){
				if(brd.getpiece(x+i*directionX,y)!= null)
					canMove = false;
			}
		}

		else{
			if(Math.abs(newx - x) != Math.abs(newy - y))
			canMove = false;

		for(int i = 1; i < Math.abs(newx - x); i++){
			if(brd.getpiece(x+i*directionX, y+i*directionY) != null)
				canMove = false;
		}

		if(brd.getpiece(newx, newy) != null && brd.getpiece(newx, newy).color == color)
			canMove = false;
		}

		return canMove;
	}
	
}

import java.awt.Toolkit;
public class Bishop extends Piece {

	int highX,highY,lowX,lowY;
	boolean canMove;
	
	public Bishop(int x, int y, boolean color, Board brd) {
		super(x,y,color,brd);
		if(color)
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_blt60.png"));
		else
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_bdt60.png"));
			
	}

	public boolean move(int newx, int newy){
		canMove = true;

		highX = Math.max(newx, x);
		highY = Math.min(newy, y);
		lowX = Math.max(newx, x);
		lowY = Math.min(newy, y);

		if(lowX == x || lowY == y){
			for(int i = 1; i < newx - x;i++){
				if(brd.getpiece(lowX+i, lowY+i) != null)
					canMove = false;
			}
			if(x == lowX && brd.getpiece(highX, highY).color == color)
				canMove = false;
			if(x == highX && brd.getpiece(lowX, lowY).color == color)
				canMove = false;
			return canMove;
		}

		else{
			for(int i = 1; i < newx - x;i++){
				if(brd.getpiece(lowX+i, highY+i) != null)
					canMove = false;
			}
			if(x == lowX && brd.getpiece(highX, lowY).color == color)
				canMove = false;
			if(x == highX && brd.getpiece(lowX, highY).color == color)
				canMove = false;
			return canMove;
		}
	}

	
	
}

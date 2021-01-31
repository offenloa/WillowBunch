import java.awt.Toolkit;

public class Pawn extends Piece {
	boolean canMove;
	boolean front;
	int direction;
	
	public Pawn(int x, int y, boolean color, Board brd) {
		super(x,y,color,brd);
		if(color)
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_plt60.png"));
		else
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_pdt60.png"));
			
	}

	public boolean move(int newx, int newy){
		canMove = true;
		front = false;
		if(color)
			direction = 1;
		else
			direction = -1;
		if(newx == x)
			canMove = false;
		if(newy - y == 0){
			for(int i = 1; i<newx;i++){
				if(brd.getpiece(x+i*direction,y) != null)
					canMove = false;
			}
		}
		else if(Math.abs(newy - y) == 1){
			for(int i = 1; i<newx;i++){
				if(brd.getpiece(x+i*direction,y) != null)
					front = true;
			}
		}
		else 
			canMove = false;
		if(newy - y == 1 && !front)
			canMove = false;
		if(Math.abs(newx - x) > 3)
			canMove = false;

		return canMove;
	}
	
}

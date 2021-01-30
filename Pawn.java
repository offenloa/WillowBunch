import java.awt.Toolkit;

public class Pawn extends Piece {
	boolean canMove;
	
	public Pawn(int x, int y, boolean color, Board brd) {
		super(x,y,color,brd);
		if(color)
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_plt60.png"));
		else
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_pdt60.png"));
			
	}

	public boolean move(int newx, int newy){
		canMove = true;
		if(newx != x)
			canMove = false;
		for(int i = y; i<=newy;i++){
			if(brd.getpiece(x,i) != null)
				canMove = false;
		}
		return canMove;
	}
	
}

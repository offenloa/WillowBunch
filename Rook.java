import java.awt.Toolkit;

public class Rook extends Piece {
	int high, low;
	boolean canMove;
	
	public Rook(int x, int y, boolean color, Board brd) {
		super(x,y,color,brd);
		if(color)
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_rlt60.png"));
		else
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_rdt60.png"));
			
	}

	public boolean move(int newx, int newy){
		canMove = true;
		if(brd.getpiece(newx, newy) != null && brd.getpiece(newx,newy).color == color)
				canMove = false;
		if(newx == x){
			high = Math.max(newy, y);
			low = Math.min(newy, y);

			for(int i = low+1;i < high;i++){
				if(brd.getpiece(x,i)!= null)
					canMove = false;
			}	
		}

		else if(newy == y){
			high = Math.max(newx, x);
			low = Math.min(newx, x);

			for(int i = low+1;i < high;i++){
				if(brd.getpiece(i,y)!= null)
					canMove = false;
			}
			
		}

		else
			canMove = false;

		return canMove;
	}

}

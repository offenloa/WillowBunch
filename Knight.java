import java.awt.Toolkit;

public class Knight extends Piece {
	
	public Knight(int x, int y, boolean color,Board brd) {
		super(x,y,color,brd);
		if(color)
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_nlt60.png"));
		else
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_ndt60.png"));
			
	}
	
}

import java.awt.Toolkit;

public class King extends Piece {
	
	public King(int x, int y, boolean color, Board brd) {
		super(x,y,color,brd);
		if(color)
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_klt60.png"));
		else
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_kdt60.png"));
			
	}
 
	
}

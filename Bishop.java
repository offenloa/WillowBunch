import java.awt.Toolkit;

public class Bishop extends Piece {
	
	public Bishop(int x, int y, boolean color) {
		super(x,y,color);
		if(color)
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_blt60.png"));
		else
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_bdt60.png"));
			
	}
	
}

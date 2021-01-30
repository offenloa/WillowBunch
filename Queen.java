import java.awt.Toolkit;

public class Queen extends Piece {
	
	public Queen(int x, int y, boolean color) {
		super(x,y,color);
		if(color)
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_qlt60.png"));
		else
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_qdt60.png"));
			
	}
	
}

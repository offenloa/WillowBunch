import java.awt.Toolkit;

public class Rook extends Piece {
	
	public Rook(int x, int y, boolean color) {
		super(x,y,color);
		if(color)
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_rlt60.png"));
		else
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_rdt60.png"));
			
	}

}

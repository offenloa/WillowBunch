import java.awt.Toolkit;

public class Pawn extends Piece {
	
	public Pawn(int x, int y, boolean color) {
		super(x,y,color);
		if(color)
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_plt60.png"));
		else
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_pdt60.png"));
			
	}
	
}

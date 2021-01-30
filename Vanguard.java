import java.awt.Toolkit;

public class Vanguard extends Piece{

    boolean path1, path2, canMove;
    int directionX, directionY;
    
    public Vanguard(int x, int y, boolean color, Board brd) {
		super(x,y,color,brd);
		if(color)
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_vlt60.png"));
		else
			super.setSprite(Toolkit.getDefaultToolkit().getImage("Chess_Pieces/Chess_vdt60.png"));
			
    }
    
    public boolean move(int newx, int newy){
        path1 = true;
        path2 = true;
        canMove = true;
        
        if (newx > x)
			directionX = 1;
		else
			directionX = -1;
		
		if (newy > y)
			directionY = 1;
		else
            directionY = -1;
            
        for(int i = 1; i <= Math.abs(newx - x); i++){
            if(brd.getpiece(x+i*directionX, y) != null)
                path1 = false;
        }
        for(int i = 0; i < Math.abs(newy - y); i++){
            if(brd.getpiece(newx, y+i*directionY) != null)
                path1 = false;
        }
        for(int i = 1; i <= Math.abs(newy - y); i++){
            if(brd.getpiece(x, y+i*directionY) != null)
                path2 = false;
        }
        for(int i = 0; i < Math.abs(newx - x); i++){
            if(brd.getpiece(x+i*directionX, newy) != null)
                path2 = false;
        }

        if(brd.getpiece(newx, newy) != null && brd.getpiece(newx, newy).color == color)
            canMove = false;

        if(newx == x || newy == y)
            canMove = false;
        return ((path1 || path2) && canMove);

    }
}

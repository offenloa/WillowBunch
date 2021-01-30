import java.awt.Image;
abstract class Piece {
	int x;
	int y;
	boolean color;
    Image sprite;
    Board brd;
	
	Piece(int x, int y,boolean color, Board brd) {
		this.x = x;
		this.y = y;
		this.color = color;
        this.sprite = null;
        this.brd = brd;
	}
	
	public void setSprite(Image image) {
		sprite = image;
	}
	
	public boolean move(int newx, int newy) {
		return false;
	}
	
}


import java.awt.Image;
abstract class Piece {
	int x;
	int y;
	boolean color;
	Image sprite;
	
	Piece(int x, int y,boolean color) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.sprite = null;
	}
	
	public void setSprite(Image image) {
		sprite = image;
	}
	
	public boolean move(int newx, int newy) {
		return false;
	}
	
}


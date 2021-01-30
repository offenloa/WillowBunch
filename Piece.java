import java.awt.Image;
public class Piece {
	int x;
	int y;
	boolean color;
	Image sprite;
	
	public Piece(int x, int y,boolean color) {
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


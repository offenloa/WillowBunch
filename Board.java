import java.awt.Image;

public class Board {
    Piece[][] board = new Piece[8][8];

    public Board() {
		board = new Piece[8][8];
	}

    public Image getSprite(int i, int j) {
		Image img = null;
		if(board[i][j]!=null)
			img = board[i][j].sprite;
		return img;
	}
	
	public Piece getpiece(int i, int j) {
		Piece p = null;
		if(board[i][j]!=null)
			p = board[i][j];
		return p;
    }

    public void setPiece(int i, int j, Piece p) {
		board[i][j] = p;
	}
    
}
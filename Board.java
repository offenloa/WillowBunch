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
    
    public void move(Piece p, int i, int j) {
		setPiece(p.x, p.y, null);
		if(p.move(i, j)) {
			setPiece(i, j, null);
			setPiece(i, j, p);
		}
		else
			setPiece(p.x, p.y, p);
	}
    
    public void reset() {
        String[] rowW = {"r","kn","b","q","k","b","kn","r"};
        String[] rowB = {"r","kn","b","k","q","b","kn","r"};
        for(int i = 0; i<8; i++) {
			board[6][i] = new Pawn(6,i,false, this);
			board[5][i] = null;
			board[4][i] = null;
			board[3][i] = null;
			board[2][i] = null;
            board[1][i] = new Pawn(1,i,true, this);
            switch(rowW[i]) {
                case "r":
                    board[0][i] = new Rook(0,i,true, this);
                    break;
                case "kn":
                    board[0][i] = new Knight(0,i,true, this);
                    break;
                case "b":
                    board[0][i] = new Bishop(0,i,true, this);
                    break;
                case "q":
                    board[0][i] = new Queen(0,i,true, this);
                    break;
                case "k":
                    board[0][i] = new King(0,i,true, this);
                    break;
                default:
                    break;
            }
                
            switch(rowB[i]) {
                case "r":
                    board[7][i] = new Rook(7,i,false, this);
                    break;
                case "kn":
                    board[7][i] = new Knight(7,i,false, this);
                    break;
                case "b":
                    board[7][i] = new Bishop(7,i,false, this);
                    break;
                case "q":
                    board[7][i] = new Queen(7,i,false, this);
                    break;
                case "k":
                    board[7][i] = new King(7,i,false, this);
                    break;
                default:
                    break;
            }
        }
            
    }

}
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class GUI implements Runnable{
    JFrame f;
    VisualBoard vb;
    Board b;
    test myMain;
    
    public void update(){
        vb.repaint();
        vb.turn = true;
    }

    public void forceMove(int oldx, int oldy, int newx, int newy) {
        Piece p = b.getpiece(oldx, oldy);
        b.setPiece(newx, newy, p);
        b.setPiece(oldx, oldy, null);
        update();
    }

    public GUI(Board b) {
        this.b = b;
        //this.myMain = myMain;
    }

    public void run() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                openGUI(b); 
            }
        });
    }

    public void openGUI(Board b) {
        f = new JFrame("New Chess");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        vb = new VisualBoard(f, b, this);
        f.add(vb);
        f.pack();
        f.setVisible(true); 
    }

}

class VisualBoard extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int SQUARE_SIZE = 60;
    private static final int SIZE = 8;
    transient Board b;
    JFrame f;
    JLabel label;
    boolean held = false;
    transient Piece heldPiece;
    int oldx;
    int oldy;
    int currentx;
    int currenty;
    int boxheight;
    int boxwidth;

    boolean isHost;
    boolean turn;

    GUI myUI;

    public VisualBoard(JFrame f, Board b, GUI myUI) {
        this.f = f;
        this.b = b;
        this.myUI = myUI;

        String[] options = {"HOST", "JOIN", "CANCEL"};
        int x = JOptionPane.showOptionDialog(this, "Host Game or Join?", "New Game", 
        2, 3, null, options , 0);

        if(x == 0){//HOST GAME
            test.startHosting(myUI);
            isHost = true;
            turn = true;
        }
        else if(x == 1){//JOIN GAME
            test.joinGame(myUI);
            isHost = false;
            turn = false;
        }
        else{
            isHost = false;
            System.exit(0);
        }


        
        JToolBar toolbar = new JToolBar("Connection");

        JButton cWhite = new JButton("Connect as White");
        cWhite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cWhitePressed();
            }
        });
        toolbar.add(cWhite);

        JButton cBlack = new JButton("Connect as Black");
        cWhite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cBlackPressed();
            }
        });
        toolbar.add(cBlack);

        toolbar.add(new JLabel("test"));

        add(toolbar);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if(turn) {
                    int x = (SIZE-1) - e.getY()/SQUARE_SIZE;
                    int y = e.getX()/SQUARE_SIZE;
                    if(!((x>=0)&&(x<SIZE)&&(y>=0)&&(y<SIZE))){
                        return;
                    }
                    if (held) {
                        if(b.move(heldPiece, x,y)) {
                            b.setPiece(oldx, oldy, null);
                            turn = false;
                            test.sendMove(isHost, oldx, oldy, x, y);
                        }
                        heldPiece = null;
                        held = false;
                        
                    }
                    else {
                        
                        if ((b.getpiece(x,y) != null)&&(b.getpiece(x,y).color == isHost)){
                            held = true;
                            oldx = x;
                            oldy = y;
                            heldPiece = b.getpiece(x,y);
                            
                        }
                    }
                

                    repaint();
                    System.out.println( x +" "+ y);
                    if(heldPiece != null){
                        System.out.println( heldPiece.x +" "+ heldPiece.y);
                    }
                }
            }
        });

        Container pane = f.getContentPane();
        pane.add(toolbar, BorderLayout.NORTH);
        pane.add(this, BorderLayout.CENTER);
        Dimension d = f.getSize();
        boxheight = (int) d.getHeight();
        boxwidth = (int) d.getWidth();
    }

    public Dimension getPreferredSize() {
        return new Dimension((SIZE) * SQUARE_SIZE +10, (SIZE) * SQUARE_SIZE + 50);
    }

    public void cWhitePressed() {
        //test.
        return;
    }

    public void cBlackPressed() {
        return;
    }

    protected void paintComponent(Graphics g) {
        g.setColor(new Color(255,255,255));
		g.fillRect(0, 0, SQUARE_SIZE * 8, SQUARE_SIZE * 8);
		g.setColor(new Color(192,192,192));
        
		for(int i = 0; i<8; i+=2) {
			for(int j = 1; j<8; j+=2) {
				g.fillRect(j*SQUARE_SIZE, i*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
			}
		}
		
		for(int i = 0; i<8; i+=2) {
			for(int j = 1; j<8; j+=2) {
				g.fillRect(i*SQUARE_SIZE, j*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
			}
		}
        if(held) {
            g.setColor(Color.GREEN);
            g.fillRect(oldy*SQUARE_SIZE, (SIZE-1-oldx)*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        }
		
		for(int i = 0; i<8; i++) {
			for(int j = 0; j<8; j++) {
				if(b.getSprite(j,i)!=null) {
					g.drawImage(b.getSprite(j,i), i*SQUARE_SIZE, SQUARE_SIZE*(7 - j), this);
				}
			}
        }

    }


}

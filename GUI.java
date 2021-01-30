import java.awt.*;
import javax.swing.*;
public class GUI implements Runnable{
    JFrame f;
    VisualBoard vb;
    Board b;
    
    public GUI(Board b) {
        this.b = b;
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
        vb = new VisualBoard(f, b);
        f.add(vb);
        f.pack();
        f.setVisible(true); 
    }

}

class VisualBoard extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int SQUARE_SIZE = 60;
    
    transient Board b;
    JFrame f;
    int boxheight;
    int boxwidth;

    public VisualBoard(JFrame f, Board b) {
        this.f = f;
        this.b = b;

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

        add(toolbar);

        Container pane = f.getContentPane();
        pane.add(toolbar, BorderLayout.NORTH);
        pane.add(this, BorderLayout.CENTER);
        Dimension d = f.getSize();
        boxheight = (int) d.getHeight();
        boxwidth = (int) d.getWidth();

    }

    public void cWhitePressed() {
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
		
		for(int i = 0; i<8; i++) {
			for(int j = 0; j<8; j++) {
				if(b.getSprite(j,i)!=null) {
					g.drawImage(b.getSprite(j,i), i*SQUARE_SIZE, SQUARE_SIZE*(7 - j), this);
				}
			}
		}
    }


}

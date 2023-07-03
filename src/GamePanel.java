import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener {
	final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
	int currentState = MENU;
	
	Font titleFont = new Font("Arial", Font.PLAIN, 48);
    Font subtitleFont = new Font("Arial", Font.PLAIN, 24);
    Tank tank = new Tank(175, 225, 50, 50);
    Tank2 tank2 = new Tank2(575, 225, 50, 50);
	
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU) {
		    drawMenuState(g);
		}
		else if(currentState == GAME) {
		    drawGameState(g);
		}
		else if(currentState == END) {
		    drawEndState(g);
		}
		System.out.println(currentState);
	}
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, Main.HEIGHT, Main.LENGTH);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("TANKS GAME", 225, 125);
		g.setFont(subtitleFont);
		g.drawString("Press ENTER to start", 275, 219);
		g.drawString("Press SPACE for instructions", 245, 344);
	}
	void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Main.HEIGHT, Main.LENGTH);
		g.setColor(Color.WHITE);
		g.drawLine(400, 0, 400, 500);
		tank.draw(g);
		tank2.draw(g);
	}
	void drawEndState(Graphics g)  {
		g.setColor(Color.RED);
		g.fillRect(0, 0, Main.HEIGHT, Main.LENGTH);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("Player _ wins!!", 225, 150);
		g.setFont(subtitleFont);
		g.drawString("Press ENTER to play again", 243, 325);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			currentState++;
			if (currentState==3) {
				currentState=MENU;
			}
		}
		if (currentState==MENU) {
			if (e.getKeyCode()==KeyEvent.VK_SPACE) {
					JOptionPane.showMessageDialog(null, "This is a 2-player game where each player controls a rocket using either the arrow keys or wasd, and they try to kill the other player's tank by shooting it down while dodging their shots. You cannot cross the center line");
			}
		}
		if (currentState==GAME) {
			if (e.getKeyCode()==KeyEvent.VK_UP) {
				tank2.up();
			}
			if (e.getKeyCode()==KeyEvent.VK_DOWN) {
				tank2.down();
			}			
			if (e.getKeyCode()==KeyEvent.VK_LEFT) {
				tank2.left();
			}			
			if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
				tank2.right();
			}
			if (e.getKeyCode() == KeyEvent.VK_W) {
				tank.up();
			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				tank.left();
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				tank.down();
			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				tank.right();
			}
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}

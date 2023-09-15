import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
	final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
	int currentState = MENU;
	Timer timer = new Timer(1000/30, this);
	Font titleFont = new Font("Arial", Font.PLAIN, 48);
    Font subtitleFont = new Font("Arial", Font.PLAIN, 24);
    Tank tank = new Tank(175, 225, 50, 50);
    Tank2 tank2 = new Tank2(575, 225, 50, 50);
    ObjectManager objectManager = new ObjectManager(tank, tank2);
    public static BufferedImage image;
    public static boolean needImage = true;
    public static boolean gotImage = false;	
    public GamePanel() {
	    if (needImage) {
	        loadImage ("field.jpeg");
	    }
    }
	
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU) {
		    drawMenuState(g);
		}
		else if(currentState == GAME) {
		    drawGameState(g);
		    updateGameState();
		}
		else if(currentState == END) {
		    drawEndState(g);
		}
	}
	void updateGameState() {
		objectManager.update();
		
		if (objectManager.tank.isActive == false) {
			currentState = END;
		}
		if (objectManager.tank2.isActive == false) {
			currentState = END;
		}
	}
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("JTG", 360, 125);
		g.setFont(subtitleFont);
		g.drawString("Press ENTER to start", 275, 219);
		g.drawString("Press SPACE for instructions", 245, 344);
	}
	void drawGameState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, Main.WIDTH, Main.HEIGHT, null);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		}
		g.setColor(Color.WHITE);
		g.drawLine(400, 0, 400, 500);
		objectManager.draw(g);
		g.setColor(Color.BLACK);
	}
	void drawEndState(Graphics g)  {
		g.setColor(Color.RED);
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		if (objectManager.tank2.isActive==false) {
			g.drawString("Player 1 wins!!", 225, 150);
		}
		else if (objectManager.tank.isActive==false) {
			g.drawString("Player 2 wins!!", 225, 150);
		}
		else {
			g.drawString("Nobody Wins!!", 225, 150);
		}
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
				objectManager = new ObjectManager(tank, tank2);
				objectManager.tank.isActive=true;
				objectManager.tank2.isActive=true;
	    		objectManager.tank = new Tank(175, 225, 50, 50);
	    		objectManager.tank2 = new Tank2(575, 225, 50, 50);
	    		Main game = new Main();
	    		game.setup();
			}

		}
		if (currentState==MENU) {
			if (e.getKeyCode()==KeyEvent.VK_SPACE) {
					JOptionPane.showMessageDialog(null, "This is a 2-player game where each player controls a rocket using either the arrow keys or wasd, and they try to kill the opposing player's tank by shooting it down while dodging the opposing player's shots.");
					JOptionPane.showMessageDialog(null, "The button to shoot is either Q or /, and you cannot cross the center line.");
			}
		}
		if (currentState==GAME) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				timer.start();
			}
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
			
			if (e.getKeyCode() == KeyEvent.VK_Q) {
				objectManager.addProjectile(tank.getProjectile());
			}
			
			if (e.getKeyCode() == KeyEvent.VK_SLASH) {
				objectManager.addProjectile2(tank2.getProjectile2());
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_DOWN) {
			tank2.ySpeed = 0;
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_RIGHT) {
			tank2.xSpeed = 0;
		}
		if (e.getKeyCode()==KeyEvent.VK_W || e.getKeyCode()==KeyEvent.VK_S) {
			tank.ySpeed = 0;
		}
		if (e.getKeyCode()==KeyEvent.VK_A || e.getKeyCode()==KeyEvent.VK_D) {
			tank.xSpeed = 0;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}
	
}

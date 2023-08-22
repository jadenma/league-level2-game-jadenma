import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
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
    ObjectManager objectmanager = new ObjectManager(tank, tank2);
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
		}
		else if(currentState == END) {
		    drawEndState(g);
		}
		System.out.println(currentState);
	}
	void updateGameState() {
		objectmanager.update();
		if (objectmanager.tank.isActive == false) {
			currentState = END;
		}
		if (objectmanager.tank2.isActive == false) {
			currentState = END;
		}
	}
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("TANKS GAME", 225, 125);
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
		objectmanager.draw(g);
	}
	void drawEndState(Graphics g)  {
		g.setColor(Color.RED);
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		if (objectmanager.getTank1Score() == 1) {
			g.drawString("Player 1 wins!!", 225, 150);
		}
		if (objectmanager.getTank2Score()==1) {
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
			}
		}
		if (currentState==MENU) {
			if (e.getKeyCode()==KeyEvent.VK_SPACE) {
					JOptionPane.showMessageDialog(null, "This is a 2-player game where each player controls a rocket using either the arrow keys or wasd, and they try to kill the opposing player's tank by shooting it down while dodging the opposing player's shots. The button to shoot is either shift or SPACE, and you cannot cross the center line");
			}
		}
		if (currentState==GAME) {
			if (e.getKeyCode()==KeyEvent.VK_UP) {
				if (tank2.y<0) {
					tank2.y=0;
				}
				tank2.up();
			}
			if (e.getKeyCode()==KeyEvent.VK_DOWN) {
				if (tank2.y>450) {
					System.out.println("out");
					tank2.y=450;
				}
				tank2.down();
			}			
			if (e.getKeyCode()==KeyEvent.VK_LEFT) {
				if (tank2.x<400) {
					tank2.x=400;
				}
				tank2.left();
			}			
			if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
				if (tank2.x>750) {
					tank2.x=750;
				}
				tank2.right();
			}
			if (e.getKeyCode() == KeyEvent.VK_W) {
					if (tank.y<0) {
						tank.y=0;
					}
				tank.up();
			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				if (tank.x<0) {
					tank.x=0;
				}
				tank.left();
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				if (tank.y>450) {
					tank.y=450;
				}
				tank.down();
			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				if (tank.x>350) {
					tank.x=350;
				}
				tank.right();
			}
			
			if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
				objectmanager.addProjectile(tank.getProjectile());
			}
			
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				objectmanager.addProjectile2(tank2.getProjectile2());
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
	
}

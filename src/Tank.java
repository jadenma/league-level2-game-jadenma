import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public class Tank extends GameObject{
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	

	public Tank(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed=10;
		// TODO Auto-generated constructor stub
		if (needImage) {
		    loadImage ("tank.png");
		}
	}
	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
		update();
	}
	void update() {
		if (x+xSpeed<=0) {
			x=0;
		}
		else if (x-xSpeed>=350) {
			x=350;
		}
		else if (y+ySpeed<=0) {
			y=0;
		}
		else if (y-ySpeed>=420) {
			y=420;
		}
		else {
			super.update();
		}
	}
	public void up() {
        ySpeed=-speed;
    }
	public void down() {
        ySpeed=speed;
    }
	public void left() {
        xSpeed=-speed;
    }
	public void right() {
        xSpeed=speed;
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
	public Projectile getProjectile() {
        return new Projectile(x+width/2, y+height/2, 10, 10);
	}

}

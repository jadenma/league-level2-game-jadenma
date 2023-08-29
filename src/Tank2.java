import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Tank2 extends GameObject{

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	public Tank2(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed=10;
		// TODO Auto-generated constructor stub
		if (needImage) {
		    loadImage ("tank2.png");
		}
	}

	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.RED);
			g.fillRect(x, y, width, height);
		}
		update();
	}
	public void up() {
        y-=speed;
    }
	public void down() {
        y+=speed;
    }
	public void left() {
        x-=speed;
    }
	public void right() {
        x+=speed;
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
	public Projectile2 getProjectile2() {
        return new Projectile2(x+width/2, y+height/2, 10, 10);
	}
}

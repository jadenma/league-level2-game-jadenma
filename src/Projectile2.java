import java.awt.Color;
import java.awt.Graphics;

public class Projectile2  extends GameObject{

	public Projectile2(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed=10;
	}
	void draw(Graphics g) {
		g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
	}

	void update() {
		x-=speed;
	}

}

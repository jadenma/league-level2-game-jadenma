import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ObjectManager implements ActionListener{

	Tank tank;
	Tank2 tank2;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Projectile2> projectiles2 = new ArrayList<Projectile2>();
	
	public ObjectManager(Tank tank, Tank2 tank2) {
		this.tank=tank;
		this.tank2=tank2;
		
	}
	
	void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}
	void addProjectile2(Projectile2 projectile2) {
		projectiles2.add(projectile2);
	}
	
	void update() {
		for (Projectile eachProjectile : projectiles) {
			eachProjectile.update();
			if (eachProjectile.x>Main.WIDTH) {
				eachProjectile.isActive = false;
			}
		}
		for (Projectile2 eachProjectile2 : projectiles2) {
			eachProjectile2.update();
			if (eachProjectile2.x<0) {
				eachProjectile2.isActive = false;
			}
		}
		checkCollision();
		purgeObjects();
		
	}
	void draw(Graphics g) {
		tank.draw(g);
		tank2.draw(g);
		for (Projectile i : projectiles) {
			i.draw(g);
		}
		for (Projectile2 i : projectiles2) {
			
			i.draw(g);
		}
	}
	void purgeObjects() {
		int projectilelength = projectiles.size()-1;
		while(projectilelength >= 0) {
			if (!projectiles.get(projectilelength).isActive) {
				projectiles.remove(projectilelength);
			}
			projectilelength--;
		}
		int projectile2length = projectiles2.size()-1;
		while(projectile2length >= 0) {
			if (!projectiles2.get(projectile2length).isActive) {
				projectiles2.remove(projectile2length);
			}
			projectile2length--;
		}
	}
	
	void checkCollision() {
		for (int i = 0; i < projectiles2.size(); i++) {
			if (tank.collisionBox.intersects(projectiles2.get(i).collisionBox)) {
				tank.isActive = false;
				projectiles2.get(i).isActive = false;
			}
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (tank2.collisionBox.intersects(projectiles.get(i).collisionBox)) {
				tank2.isActive = false;
				projectiles.get(i).isActive = false;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

package Character;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class Entity {
	
	protected float x,y,width,height;
	protected Rectangle2D.Float hitbox;
	
	public Entity(float x, float y,float width,float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	
	}
	
	public void drawHitBox(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect((int) hitbox.x , (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
	}
	
	protected void createHitbox(float x, float y, float width, float height) {
		hitbox = new Rectangle2D.Float(x, y, width, height);
	}


	public Rectangle2D.Float getHitbox() {
		return hitbox;
	}

}
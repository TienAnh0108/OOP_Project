package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import Character.Player;

public class Ball {

	public float x,y;
	private Rectangle2D.Float hitbox;
	private boolean active = true;

	public Ball(float x, float y) {
		this.x = x;
		this.y = y;
		createHitbox();
	
	}
	
	
	public void update(int Offset, Player player) {
		x -=1.5;
		hitbox.x -=1.5;

		int dx = (int) ( (x-Offset) - Player.PlayerX_RightPos);
		int dy = (int) (Player.PlayerY_UpPos - (y + 10));
		
		if((Math.abs(dx) <= 23) && (-27 > dy && dy > -95))
           player.updateHealth(3);

		if((x < 10) ) 
		    active = false;
		else
			active = true;
	}
	

	
	public boolean getActive() {
		return active;
	}
	
	public void drawHitBox(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect((int) hitbox.x , (int) hitbox.y, 20, 20);
	}
	
	public void createHitbox() {
		hitbox = new Rectangle2D.Float(x, y + 10, 20, 20);
	}


	public Rectangle2D.Float getHitbox() {
		return hitbox;
	}
	
	
}

package Objects;

import Character.Player;

public class NPC {
	public float x,y;
	public boolean interaction = false;
	
	
	
	public NPC(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void update(int Offset) {
		int dx = (int) ((x-Offset) - Player.PlayerX_RightPos);
		int dy = (int) (y - Player.PlayerY_UpPos);
		if(Math.abs(dx) < 28 && dy > 45 && dy < 250) {
			interaction = true;
		}
		else {
			interaction = false;
		}
	}

}

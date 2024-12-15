package Objects;

import Character.Player;

public class HealthPotion {
	public float x,y;
	public boolean getPotion = false;
	
	public HealthPotion(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void update(int Offset) {
		int dx = (int) ((x-Offset) - Player.PlayerX_RightPos);
		int dy = (int) (y - Player.PlayerY_UpPos);
		if(Math.abs(dx) < 25 && dy > 45 && dy < 250 && !getPotion) {
			getPotion = true;
			Player.PlayerGainHealth = true;
		}

	}
	

}

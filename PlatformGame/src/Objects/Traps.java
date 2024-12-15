package Objects;


import Character.Player;

public class Traps {
   
	float x,y;

	
	public Traps(float x, float y) {
		this.x = x;
		this.y = y;
		
	}

	public void update(int offSetX) {
		int dx = (int) ( (x-offSetX) - Player.PlayerX_RightPos);
		int dy = (int) (Player.PlayerY_UpPos - (y + 25));
		if(Math.abs(dy) > 100)
			return;
		if(dx < 20 && dx > -52)
			Player.PlayerGetHit = true;
	}



	

}

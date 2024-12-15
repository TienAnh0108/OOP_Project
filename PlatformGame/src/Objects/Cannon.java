package Objects;

import Character.Player;

public class Cannon {
	
    public float x,y;
    private boolean checkInrange;
    private boolean active = true;
    public static boolean GetHit;
	public Cannon(float x, float y) {
		this.x = x;
		this.y = y + 10;

	}
	
	public void update(int Offset) {
		int dx = (int) ( (x-Offset) - Player.PlayerX_RightPos);
		int dy = (int) (Player.PlayerY_UpPos - (y + 10));
        float distance = (float) Math.sqrt(dx*dx + dy*dy);
        

        
        if(distance <= 500)
        	checkInrange = true;
        else
        	checkInrange = false;
        
        if(Player.PlayerX_RightPos > (x-Offset))
        	checkInrange = false;
        
        if(GetHit)
        	checkGettingHit(Offset);
        else
        	GetHit = false;
        
	}
	
	private void checkGettingHit(int Offset) {
		int dx = (int) ((Player.Player_AttackRange) - (x-Offset));
		if(dx > -17 && dx < 44) 
			active = false;
		else
			active = true;
	}

		
	
	
	
	public boolean getActive() {
		return active;
	}
	
	public boolean getCheck() {
		return checkInrange;
	}
	
}

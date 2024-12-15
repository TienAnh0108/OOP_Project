package Character;



import main.MainGame;

public class Enemy extends Entity {
	
	public   static int EnemyX_LeftPos;
	public   static int Offset;
	private  int enemyDir = 1;
	private  float enemySpeed = (float) 0.75;
	private  boolean inAir = false;
	private  boolean firstUpdate = true;
	private  float airSpeed = 0f;
	private  float gravity = 0.09f * MainGame.SCALE;
	public static boolean checkGetHitFromPlayer = false;
	protected boolean active = true; 
	protected int enemyState = 1;
	protected int deadAnimationTick = 0;
	long lastCheck = System.currentTimeMillis();
    
	private int numOfHit = 0;
	private int checkGetStuck = 0;
	
	public Enemy(float x, float y, float width, float height) {
		super(x, y, width, height);
		createHitbox(x - Offset ,y , width, height);
	}
	
	
	
	protected void checkHitBox_withEnv(int [][] levelData) {
		
		if (firstUpdate) {
			if (!CheckHitBox.IsEntityOnFloor(hitbox, levelData))
				inAir = true;
			firstUpdate = false;
		}
		
		if (inAir) {
			if (CheckHitBox.CanMoveHere(x,y + airSpeed,  width, height, levelData)) {
				y += airSpeed;
				airSpeed += gravity;
			
			}else {
				inAir = false;
			}
		}else {
			

			
			if(enemyDir == 1)
				enemySpeed = (float) -0.75;
			else
				enemySpeed = (float) 0.75;
			
			EnemyX_LeftPos = (int) (x + 30 - Offset);
			
			
	        if(checkGetHitFromPlayer) 
	            checkGettingHit();  
	           
	        
			if (CheckHitBox.CanMoveHere(x + 30  + enemySpeed ,y , width, height, levelData)) {
				if(CheckHitBox.CheckEgde(enemySpeed,x + 30  + enemySpeed ,y , width, height ,levelData)) {
					if(checkInRange()) 
				    	 turnTowardsPlayer();
					else
						 changeDirAni();
					checkGetStuck--;
				    x += enemySpeed;
				    return;		
				}
			}		
            changeDir();
            active = true;
            getStuck();

			
			
		}
		
	}

	private void changeDir() {
		if(enemyDir == 1) {
			changeDirAni();
			x += enemySpeed;
			enemyDir = 0;
		}else {
			changeDirAni();
			x -= enemySpeed;
			enemyDir = 1;
		}checkGetStuck++;
	}
	
	private void turnTowardsPlayer() {
		if (Player.PlayerX_RightPos > EnemyX_LeftPos) 
			enemySpeed = (float) 1.5; // move to right
		else 
			enemySpeed = (float) -1.5;
		
	}

	private void getStuck() {
        if(checkGetStuck < -200)
        	checkGetStuck = 0;
        else if(checkGetStuck > 100)
        	active = false;
	}

	
	
	private boolean checkInRange() {
		   
	        // Calculate distance between player and enemy
	        int dx = (int) ((Player.PlayerX_LeftPos) - EnemyX_LeftPos);
	        int dy = (int) (Player.PlayerY_UpPos - y);
            
	        if(Math.abs(dy) >= 102)
	        	return false;
            if(dy > -20)
            	return false;
            	
            if(dx > -70 && dx < -14 )
            	Player.PlayerGetHit = true;

            
	        if(Player.PlayerX_RightPos > EnemyX_LeftPos) {
	        	enemyState = 1;
			   return Math.abs(dx) < 87;
	        }else {
	        	enemyState = 0;
	           return Math.abs(dx) < 200;
	        }
	        
	        
	    }
	
	
	
	private void checkGettingHit() {
		int dx = (int) ((Player.Player_AttackRange) - EnemyX_LeftPos);
		int dy = (int) (Player.PlayerY_UpPos - y);
		if(dx > -20 && dx < 44 && Math.abs (dy) < 100) {
			if(Player.PlayerX_RightPos > EnemyX_LeftPos)
				x -= 20;
			else
				x += 20;
			
			numOfHit++;
			if(numOfHit > 2) {
			   active = false;
			   Crab_Spawn.numCrabs--;
			   numOfHit = 0;
			}
		}
		
	}

	private void changeDirAni() {
		if(enemySpeed < 0)
			enemyState = 0;
		else
			enemyState = 1;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public int getDeadAniTick() {
		return deadAnimationTick;
	}
	
	public int getEnemyState() {
		return enemyState;
	}
	


}

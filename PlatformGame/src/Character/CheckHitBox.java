package Character;

import java.awt.geom.Rectangle2D;

import main.MainGame;

public class CheckHitBox {
    
	public static boolean CanMoveHere(float x, float y, float width, float height, int[][] levelData) {
		if (!IsCollided(x, y, levelData))
			if (!IsCollided(x, y + height, levelData))
				if (!IsCollided(x + width, y, levelData))
					 if (!IsCollided(x + width, y + height, levelData)) 
						return true;
		
		return false;
	}
	
	
	public static boolean IsCollided(float x, float y,int[][] levelData) {
		if(x < 0 || x >= levelData[0].length*MainGame.TILES_SIZE) return true;
		if(y < 0 || y >= MainGame.GAME_HEIGHT) return true;
		
		
		
		float xIndex = x / MainGame.TILES_SIZE;
		float yIndex = y / MainGame.TILES_SIZE;
        

		
		int value = levelData[(int)yIndex][(int)xIndex];

		if (value >= 48 || value < 0 || value != 11)
			return true;
		return false;
	}
	


	public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] levelData) {
		if (!IsCollided(hitbox.x, hitbox.y + hitbox.height + 1, levelData))
			if (!IsCollided(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, levelData))
				return false;

		return true;

	}
	
	public static boolean CheckEgde(float xSpeed, float x, float y,float width,float height, int[][] levelData) {
	    if(xSpeed > 0)		
		  return (IsCollided(x + width, y + height + 10, levelData));
	    else
	      return (IsCollided(x, y + height + 10, levelData));
	}
	



	
	
}

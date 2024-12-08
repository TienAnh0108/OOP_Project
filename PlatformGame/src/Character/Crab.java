package Character;

import main.MainGame;

public class Crab extends Enemy {
	
	public static final int CRABBY_WIDTH_DEFAULT = 72;
	public static final int CRABBY_HEIGHT_DEFAULT = 35;
	public static final int CRABBY_WIDTH = (int) (CRABBY_WIDTH_DEFAULT * MainGame.SCALE);
	public static final int CRABBY_HEIGHT = (int) (CRABBY_HEIGHT_DEFAULT * MainGame.SCALE);
	public int firstCheck = 0;

	public Crab(float x, float y) {
		super(x + 30 ,y + 5 , CRABBY_WIDTH - 57, CRABBY_HEIGHT-10);

	}



	public void updateMove(int[][] levelData) {
		checkHitBox_withEnv(levelData);

	}

	
	
}

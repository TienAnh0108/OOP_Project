package Objects;

import Character.Crab_Spawn;
import Character.Player;
import main.Game_JPanel;
import main.MainGame;

public class Door {

	
    public static int x;
    public static int y;
    
    public static int KeyX;
    public static int KeyY;
    
    
    public static boolean getKey = false;
    
    public static void checkRange(int offSet) {
    	
    	double range = x - Player.PlayerX_RightPos - offSet;
    	if(range <= 27 && !getKey )
    		Game_JPanel.displayNotification(1);
    	else
    		Game_JPanel.displayNotification(0);

    	if(range <= 27) 
    		if(getKey && !Crab_Spawn.clearCrab)
    		    System.out.println("Kill all enemies");
    		else
    			MainGame.nextMap = true;

    }
    
    public static void checkKeyRange(int offSet) {
    	double rangeX = KeyX - Player.PlayerX_RightPos - offSet;
    	double rangeY = KeyY - Player.PlayerY_UpPos;
    	
    	if(Math.abs(rangeX) < 25 && rangeY > 45) 
    		getKey = true;

    }
    
    
    
    public static void reset() {
         x = 0;
         y = 0;       
         KeyX = 0;
         KeyY = 0;
         getKey = false;
    }

	

	
}

package KeyBoardInput;

public class SwitchAction {
    private static final int STANDING_RIGHT = 0;
    private static final int STANDING_LEFT = 1;
    private static final int RUNING_RIGHT = 2;
    private static final int RUNING_LEFT = 3;
    public static int action;
    public static int attack;
    
    public static int GetFramesAction(int action) {
    	
    	if(attack > 0) return 6;
    		
    	switch(action) {
    	   case STANDING_RIGHT:
    		   return 6;
    	   case STANDING_LEFT:
    		   return 6;
    	   case RUNING_RIGHT:
    		   return 6;
    	   case RUNING_LEFT:
    		   return 6;
    	  }return 6;

    }
    
    
}

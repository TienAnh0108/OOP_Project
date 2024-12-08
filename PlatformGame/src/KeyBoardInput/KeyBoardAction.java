package KeyBoardInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import main.Game_JPanel;
import main.MainGame;



public class KeyBoardAction implements KeyListener{
	
    private Game_JPanel jpanel;
	public KeyBoardAction(Game_JPanel panel) {
          this.jpanel = panel;

	}

	@Override
	public void keyTyped(KeyEvent e) {
       
        }
		
	

	@Override
	public void keyPressed(KeyEvent e) {
		 int keyCode = e.getKeyCode();
	        switch (keyCode) {
               
	            case KeyEvent.VK_LEFT:
	            	
	                jpanel.getGame().getPlayer().state_ani = 3;
	                jpanel.getGame().getPlayer().setLeft(true);
	                SwitchAction.action = 3;
	            	
	                break;
	            case KeyEvent.VK_RIGHT:
	            	
	            	
	                jpanel.getGame().getPlayer().state_ani = 2;
	                jpanel.getGame().getPlayer().setRight(true);
	                SwitchAction.action = 2;
	            	
	                break;
	                
	            case 90:

	                jpanel.getGame().getPlayer().setAttack(true);
	                SwitchAction.attack = 2;
	            	break;
	            	
	            	
	    		case KeyEvent.VK_SPACE:
	    			jpanel.getGame().getPlayer().setJump(true);
	    			break;
	    			
	     		case KeyEvent.VK_P:
	     			if(Game_JPanel.selectionMenu == 0) {
	     			    if(!MainGame.pause)
	     				   MainGame.pause = true;
	     			    else
	     				   MainGame.pause = false;
	     			
	     			    jpanel.pauseMenu();
	     			}    
	    			break;

	 
	        }
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		 int keyCode = e.getKeyCode();
	        
	        switch (keyCode) {
	       
                
	            case KeyEvent.VK_LEFT:
	            	
	            	SwitchAction.attack = 0;
	            	jpanel.getGame().getPlayer().setLeft(false);
	            	SwitchAction.action = 1;
	            	
	                break;
	            case KeyEvent.VK_RIGHT:

                    SwitchAction.attack = 0;
	            	jpanel.getGame().getPlayer().setRight(false);
	            	SwitchAction.action = 0;
	            	
	                break;
	            case 90:
//	               	jpanel.getGame().getPlayer().setAttack(false);
//	            	SwitchAction.attack = 0;

	                break;
	                
	    		case KeyEvent.VK_SPACE:
	    			jpanel.getGame().getPlayer().setJump(false);
	    			break;

	        }   
		
	}




}

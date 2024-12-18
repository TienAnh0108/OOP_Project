package main;

import java.awt.Graphics;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

import Character.Crab_Spawn;
import Character.Player;

import KeyBoardInput.Sound;
import KeyBoardInput.SwitchAction;
import LoadMap.Load;
import LoadMap.MapManager;
import Objects.Cannon_Spawn;
import Objects.Door;
import Objects.HealthPotion_Spawn;
import Objects.NPC_Spawn;
import Objects.Trap_Spawn;

public class MainGame implements Runnable{
	
	Game_JFrame_Window window_game;
	Game_JPanel panel_game;
    PauseMenu pauseMenu;
	Player player ;
	Crab_Spawn crab;
	Trap_Spawn trap;
	Cannon_Spawn cannon;
	MapManager map;
	HealthPotion_Spawn potion; 
	NPC_Spawn npc;
	
	public final static float SCALE = 1.5f;
	public final static int TILES_DEFAULT_SIZE = 32;
	public final static int TOTAL_TILES_IN_WIDTH = 26;
	public final static int TOTAL_TILES_IN_HEIGHT = 14;
	public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TOTAL_TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TOTAL_TILES_IN_HEIGHT;
	
	public static int ChangeVolume = -23;
	public static boolean resetSong = false;
	public static boolean nextSong = false;
	private int songIndex = 2;
	
	public static boolean winning = false;
	
	private Sound sound;
	private Thread thread;
	private final int FPS = 120;
	private int takeAction;

	public static boolean pause = true;
	public static boolean restart = false;
	public static boolean die = false;
	
	public MainGame() {	
        sound = new Sound();
        getSound(songIndex);        
		initializePlayer();
		panel_game = new Game_JPanel(this);
		window_game = new Game_JFrame_Window(panel_game);
		GameRepaintThread();
		
	}
	
	public void warning() {
		
	}
	
	public void initializePlayer() {
		Load.mapControler();
		map = new MapManager();
		potion = new HealthPotion_Spawn();
		npc = new NPC_Spawn();
		trap = new Trap_Spawn();
		cannon = new Cannon_Spawn();
		player = new Player(30,300, (int) (96*SCALE), (int) (84*SCALE) );
		crab = new Crab_Spawn(); 
	}
	
	public void render(Graphics g) {

		map.player_xPos = player.xPos;
		map.get_Map_Level(g);
	
	    if(!pause) {
		   player.updateBigMap = map.xLvlOffset;
		   player.renderPlayer(g);
		
		   crab.Offset =  map.xLvlOffset;
		   crab.renderCrabs(g);
		   
		   trap.Offset =  map.xLvlOffset;
		   trap.renderTraps(g);
		   
		   potion.Offset = map.xLvlOffset;
		   potion.renderPotion(g);
		   
		   npc.Offset = map.xLvlOffset;
		   npc.renderNPC(g);
		   
		   
		   cannon.Offset =  map.xLvlOffset;
		   cannon.renderGeneral(g,player);
	    }
	    
	  Door.checkRange(map.xLvlOffset);
	  Door.checkKeyRange(map.xLvlOffset);

	}
	
   public Player getPlayer() {   
	   return player;
   }

   public void update() {
	  
	   takeAction = SwitchAction.action;
	   player.frame1 = (SwitchAction.GetFramesAction(takeAction));
	   npc.updateNPCState();
	   crab.updateCrabState();
	   player.updatePlayer();
	   cannon.updateAttackTick();
	  

       if(restart) {
    	   Game_JPanel.selectionMenu = 0;
    	   panel_game.dieMenu();
    	   restart = false;
    	   die = false;
    	   pause = false;
    	   initializePlayer();
       }
       
	   if(die) { 
		 pause = true;
		 panel_game.dieMenu();
	   }

	   
   }
   
	
	public void GameRepaintThread() {
		thread = new Thread(this);
		thread.start();
	}
	
	
	@Override
	public void run() {
		
		double nanoFPS = 1000000000.0  / FPS;
		long lastTime = System.nanoTime();
		long now = System.nanoTime();
		long loadframe = System.currentTimeMillis();
		long nowframe = System.currentTimeMillis();

		while(true) {
			checkSound();
			now = System.nanoTime();
			
			if(now - lastTime >= nanoFPS) {
				panel_game.repaint();
				lastTime = now;
			}

			if(winning && Door.getKey) { 
				if(winningPrompt()) {
					Door.reset();
					initializePlayer();
				}else
					System.exit(0);
					
			}	
			winning = false;	

			nowframe = System.currentTimeMillis();
			if(nowframe - loadframe >= 150) {
			    update();
				loadframe = System.currentTimeMillis();

			}   
		}
		
	}
	
	private void checkSound() {
		long lastCheck = System.currentTimeMillis();
		if (System.currentTimeMillis() - lastCheck >= 1000) {
			if(!sound.checkActive()) getSound(songIndex);
			lastCheck = System.currentTimeMillis();

		}
		
		if(resetSong)
			   sound.reset();
			resetSong = false;
			
			
         if(nextSong) {
         	if(songIndex == 3)
         		songIndex = 2;
         	else
         		songIndex = 3;
         	sound.nextSong(songIndex, ChangeVolume);
         }nextSong = false;
         
         
            
		if(ChangeVolume == -60 ) 
			sound.fc.setValue(-75);
		else 
			sound.fc.setValue(ChangeVolume);
        
		
	}
	
	private boolean winningPrompt() {
		 int result = JOptionPane.showConfirmDialog(
	                null, 
	                "You Win, Wanna play again?", // Message
	                "Confirmation",            // Title
	                JOptionPane.YES_NO_OPTION, // Option type
	                JOptionPane.QUESTION_MESSAGE // Message type
	        );

	        
	        if (result == JOptionPane.YES_OPTION) 
	            return true;
	        else
	        	return false;
	}
	
	private void getSound(int i)  {
		try {
			sound.getSound(i ,ChangeVolume);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	
}

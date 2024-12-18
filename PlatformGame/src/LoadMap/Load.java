package LoadMap;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Character.Crab;
import Objects.Cannon;
import Objects.Door;
import Objects.HealthPotion;
import Objects.NPC;
import Objects.Traps;
import main.MainGame;


public class Load {

	
	public static final String MAP_LEVEL_ONE    = "Map1.png";
	public static final String MAP_LEVEL_TWO    = "Map2.png";
	public static final String MAP_LEVEL_THREE  = "Map3.png";

	public static final String TILE_2D = "2D Tiles.png";
	public static final String HEALTH_BAR = "health_bar2.png";
	
	
	public static ArrayList<NPC> getNPC;
	public static ArrayList<Crab> getCrab;
	public static ArrayList<Traps> getTraps;
	public static ArrayList<Cannon> getCannon;
	public static ArrayList<String> mapControler;
	public static ArrayList<HealthPotion> getHeathPotion;
	
	
	public static int index = 2;
	
	public static BufferedImage LoadImage(String name) {
		BufferedImage image = null;
		InputStream is = Load.class.getResourceAsStream("/" + name);
		
		try {
			image = ImageIO.read(is);
				
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			
				try {
					is.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
		}

		return image;
		
	}
	
	public static void mapControler() {
		getNPC = new ArrayList<>();
		getCrab = new ArrayList<>();
		getTraps = new ArrayList<>();
		getCannon = new ArrayList<>();
		mapControler = new ArrayList<>();
		getHeathPotion = new ArrayList<>();
		
		mapControler.add(MAP_LEVEL_ONE);
		mapControler.add(MAP_LEVEL_TWO);
		mapControler.add(MAP_LEVEL_THREE);
        
	}
	
	
	public static int[][] GetMapLevelData() {
		
		BufferedImage img = LoadImage(mapControler.get(index));
		int[][] levelData = new int[img.getHeight()][img.getWidth()];

        
		for (int j = 0; j < img.getHeight(); j++) {
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value  = color.getRed();
				initializeObjects(i,j,color.getBlue(),color.getGreen());

				if (value >= 48)
                    value = 0;
				
				if(value == 23) {
					Door.x = i*MainGame.TILES_SIZE;
				    Door.y = j*MainGame.TILES_SIZE;
				}    
				
				levelData[j][i] = value;
			}
		
		}
		
		return levelData;

	}
	
	private static void initializeObjects(int i, int j, int value1,int value2) {
		switch (value1) {
	    case 0:
	        getTraps.add(new Traps(i * MainGame.TILES_SIZE, j * MainGame.TILES_SIZE));
	        break;

	    case 5:
	        getCannon.add(new Cannon(i * MainGame.TILES_SIZE, j * MainGame.TILES_SIZE));
	        break;

	    case 6:
	        Door.KeyX = i * MainGame.TILES_SIZE;
	        Door.KeyY = j * MainGame.TILES_SIZE;
	        break;

	    case 7:
	        getHeathPotion.add(new HealthPotion(i * MainGame.TILES_SIZE, j * MainGame.TILES_SIZE));
	        break;

	    case 8:
	        getNPC.add(new NPC(i * MainGame.TILES_SIZE, j * MainGame.TILES_SIZE));
	        break;
	    }
		
		if (value2 == 0) 
            getCrab.add(new Crab(i*MainGame.TILES_SIZE, j*MainGame.TILES_SIZE));

	}

}

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
	public static ArrayList<String> mapControler;
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
		mapControler = new ArrayList<>();
		mapControler.add(MAP_LEVEL_ONE);
		mapControler.add(MAP_LEVEL_TWO);
		mapControler.add(MAP_LEVEL_THREE);
        
	}
	
	public static ArrayList<Crab> GetCrabs(){
		ArrayList<Crab> list = new ArrayList<>();	
		BufferedImage img = LoadImage(mapControler.get(index));
		
		for (int j = 0; j < img.getHeight(); j++) {
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getGreen();
				
				if (value == 0) 
                   list.add(new Crab(i*MainGame.TILES_SIZE, j*MainGame.TILES_SIZE));
				
			}		
		}

		return list;
	}
	
	public static ArrayList<NPC> GetNPC(){
		ArrayList<NPC> list = new ArrayList<>();	
		BufferedImage img = LoadImage(mapControler.get(index));
		
		for (int j = 0; j < img.getHeight(); j++) {
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getBlue();
				
				if (value == 8) 
                   list.add(new NPC(i*MainGame.TILES_SIZE, j*MainGame.TILES_SIZE));
				
			}		
		}

		return list;
	}
	
	
	public static ArrayList<Traps> Gettraps(){
		ArrayList<Traps> trap = new ArrayList<>();	
		BufferedImage img = LoadImage(mapControler.get(index));

		
		for (int j = 0; j < img.getHeight(); j++) {
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getBlue();
				if (value == 0) {
					trap.add(new Traps(i*MainGame.TILES_SIZE, j*MainGame.TILES_SIZE));
				}
			}		
		}
        
		return trap;
	}
	
	public static ArrayList<HealthPotion> GetHeathPotion() {
		ArrayList<HealthPotion> healthPotion = new ArrayList<>();
		BufferedImage img = LoadImage(mapControler.get(index));
		
		for(int j = 0; j < img.getHeight(); j++) {
			for(int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getBlue();
				if(value == 7) {
					healthPotion.add(new HealthPotion(i*MainGame.TILES_SIZE, j* MainGame.TILES_SIZE));
				}
			}
		}
		
		return healthPotion;
	}
	
	
	
	public static int[][] GetMapLevelData() {
		
		BufferedImage img = LoadImage(mapControler.get(index));
		int[][] levelData = new int[img.getHeight()][img.getWidth()];

        
		for (int j = 0; j < img.getHeight(); j++) {
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed();
				
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
	
	
	public static ArrayList<Cannon> Getcannon(){
		ArrayList<Cannon> cannon = new ArrayList<>();
		
		BufferedImage img = LoadImage(mapControler.get(index));

		
		for (int j = 0; j < img.getHeight(); j++) {
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getBlue();

				if (value == 5) 
					cannon.add(new Cannon(i*MainGame.TILES_SIZE, j*MainGame.TILES_SIZE));
				if (value == 6) { 
					Door.KeyX = i*MainGame.TILES_SIZE;
				    Door.KeyY = j*MainGame.TILES_SIZE;
				}
				
			}		
		}
		return cannon;
	}

	
	

}

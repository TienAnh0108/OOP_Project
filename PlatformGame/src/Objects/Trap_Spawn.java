package Objects;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import LoadMap.Load;

public class Trap_Spawn {

	private BufferedImage img;
	private BufferedImage imgKey;
	private ArrayList<Traps> list;
	
	public int Offset;
	
	public Trap_Spawn() {
		list = new ArrayList<>();
		list = Load.getTraps;
		load();
		
		
	}
	

	public void update() {
		for (Traps trap : list) {
			   trap.update(Offset);
		}	
		
	}
	
	public void renderTraps(Graphics g) {
		update();
		if(!Door.getKey)
		   renderKeys(g);
		for(Traps trap : list) {		
			 g.drawImage(img, (int)(trap.x  - Offset),(int) trap.y, 48, 48, null);
		} 
		
	
	}
	
	private void renderKeys(Graphics g) {
		g.drawImage(imgKey, (int)(Door.KeyX  - Offset),(int) Door.KeyY, 25, 40, null);
	}
	
	private void load(){
		InputStream is = getClass().getResourceAsStream("/Trap.png");
		InputStream key = getClass().getResourceAsStream("/Key.png");
		try {
			img = ImageIO.read(is);
			imgKey = ImageIO.read(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

package Objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import LoadMap.Load;

public class HealthPotion_Spawn {
	
	private BufferedImage img;
	private ArrayList<HealthPotion> list;
	public int Offset;
	
	public HealthPotion_Spawn() {
		list = new ArrayList<>();
		list = Load.GetHeathPotion();
		loadHealthPotion();
	}

	public void update() {
		for (HealthPotion hp : list) {
			hp.update(Offset);
		}
	}
	
	public void renderPotion(Graphics g) {
		update();
		for (HealthPotion hp : list ) {
			if(!hp.getPotion) {
				g.drawImage(img, (int) hp.x - Offset,(int) hp.y, 32, 48, null);
			}
		}
	}
	
	private void loadHealthPotion() {
		InputStream is = getClass().getResourceAsStream("/Health potion.png");
		try {
			img = ImageIO.read(is);	
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}

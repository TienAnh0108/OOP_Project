package Objects;



import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Character.Player;
import LoadMap.Load;

public class Cannon_Spawn {

	private BufferedImage img;
	private BufferedImage imgBall;
	private BufferedImage[][] Animation;
	private ArrayList<Cannon> list;
	private ArrayList<Ball> ballList;
	private ArrayList<Ball> removeBalls;
	private int attIndex;
	private int check;
	public int Offset;
	
	public Cannon_Spawn() {
		list = new ArrayList<>();
		ballList = new ArrayList<>();
		removeBalls = new ArrayList<>();
		list = Load.getCannon;
		loadCannon();
	}
	


	private void updateCannon() {
		for (Cannon c : list) {
			   if(c.getActive()) 
			      c.update(Offset);
		}	
		
	}
	
	private void updateBall(Player player) {
		for (Ball b : ballList) {
			   if(b.getActive())
			      b.update(Offset,player);
			   else
				   removeBalls.add(b);
		}ballList.removeAll(removeBalls);	
		
	}
	
	public void renderGeneral(Graphics g, Player player) {	
		renderCannons(g);
		renderBalls(g, player);
		
	}
	
	private void renderCannons(Graphics g) {
		updateCannon();
		for(Cannon c : list) {
			if(c.getActive()) {
				    check++;
			        if(c.getCheck()) 
			            checkConditionCannon(c,g); 
			        else
			            g.drawImage(Animation[0][0], (int)(c.x  - Offset),(int) c.y, 60, 39, null);
			}
			
			
		} 
		
	
	}
	
	
	private void renderBalls(Graphics g,Player player) {
		updateBall(player);
		for (Ball b : ballList) {
			if(b.getActive())
			   g.drawImage(imgBall, (int)(b.x - Offset), (int) b.y + 10, 20, 20, null);
		}
		

	}
	
	private void shoot(Cannon c) {
		ballList.add(new Ball((int) c.x, (int) c.y));
	}
	
	private void checkConditionCannon(Cannon c, Graphics g) {
    	if(attIndex == 4 && check > 200) {
    	    shoot(c);
    	    check = 0;
    	} 
    	 g.drawImage(Animation[0][attIndex], (int)(c.x  - Offset),(int) c.y, 60, 39, null);
	}
	

	
	private void loadCannon() {
		InputStream is = getClass().getResourceAsStream("/cannon.png");
		InputStream ball = getClass().getResourceAsStream("/tinyFace.png");
		try {
			img = ImageIO.read(is);
			imgBall = ImageIO.read(ball);
			Animation = new BufferedImage[2][7];
			
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < Animation[i].length; j++) {
					Animation[i][j] = img.getSubimage(j * 40, i * 26, 40, 26);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			
				try {
					is.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
		}
		
	}
	
	
	public void updateAttackTick() {
		attIndex++;
		if (attIndex >= 6) {
			attIndex = 0;
		}


    }
	
}


package Objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Character.Crab_Spawn;
import LoadMap.Load;

public class NPC_Spawn {
	private BufferedImage[][] Animation;
	private BufferedImage img;
	private BufferedImage imgMsg;
	private BufferedImage imgMsgEnd;
	private int i = 0;
	private int frame;
	public  int Offset;
	
	
	private ArrayList<NPC> list;
	
	public NPC_Spawn() {
		list = new ArrayList<>();
		list = Load.getNPC;
		loadNPC();
	}
	
	public void updateNPCState(){
		if(i > 3) i = 0;
		frame = i++;
	}
	
	public void update() {
		for(NPC npc : list) {
			npc.update(Offset);
		}
	}

	public void renderNPC(Graphics g) {
		update();
		for(NPC npc : list) {
			if(!npc.interaction) {
				g.drawImage(Animation[0][frame], (int)npc.x - Offset,(int)npc.y - 12 , 96, 84, null);
			}
			else {
				if(!Door.getKey) {
					g.drawImage(Animation[0][frame], (int)npc.x - Offset,(int)npc.y - 12 , 96, 84, null);
					g.drawImage(imgMsg, (int) npc.x- Offset,(int) npc.y - 70 ,140 , 80, null);
				}
				else {
					if(Crab_Spawn.clearCrab) {
						g.drawImage(Animation[0][frame], (int)npc.x - Offset,(int)npc.y - 12 , 96, 84, null);
						g.drawImage(imgMsgEnd, (int) npc.x- Offset - 10,(int) npc.y - 70,250 , 160, null);
					}
					else {
						g.drawImage(Animation[0][frame], (int)npc.x - Offset,(int)npc.y - 12 , 96, 84, null);
						g.drawImage(imgMsg, (int) npc.x- Offset - 10,(int) npc.y - 70,140 , 80, null);
					}
				}
			}
		}
	}
	
	private void loadNPC() {
		InputStream is = getClass().getResourceAsStream("/npc.png");
		InputStream msg = getClass().getResourceAsStream("/announcement.png");
		InputStream msgEnd = getClass().getResourceAsStream("/msg_end-removebg-preview.png");

		try {
			img = ImageIO.read(is);
			imgMsg = ImageIO.read(msg);
			imgMsgEnd = ImageIO.read(msgEnd);
			Animation = new BufferedImage[1][4];
			
			for(int i = 0; i < 1; i++) {
				for(int j = 0; j < Animation[i].length; j++) {
					Animation[i][j] = img.getSubimage(j * 96, i * 84, 96, 84);
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
}

package Character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import KeyBoardInput.Sound;
import KeyBoardInput.SwitchAction;
import LoadMap.Load;
import LoadMap.MapManager;
import Objects.Cannon;
import main.Game_JPanel;
import main.MainGame;

public class Player extends Entity {
	
	public int state_ani;
	public int frame1;
	public float xPos;
	public int updateBigMap;
	public static int PlayerX_RightPos;
	public static int PlayerX_LeftPos;
	public static int PlayerY_UpPos;
	public static boolean PlayerGetHit = false;
	public static boolean PlayerGainHealth = false;
	public static int Player_AttackRange;
	

	private int f;

	private int[][] levelData;
    private BufferedImage img;
    private BufferedImage img_healthBar;
    private BufferedImage[][] Animation;
    private boolean left, right, attack, jump;
    
    private int i = 0;
    private int attIndex;

	private float airSpeed = 0f;
	private float gravity = 0.09f * MainGame.SCALE;
	private float jumpSpeed = -3.5f * MainGame.SCALE;
	private boolean inAir = false;
	public boolean checkStandingLeft;

	
	
	private boolean acJumpSound = true;
    private Sound sound;
    public static int ChangeVolume = -40;
    
    private Rectangle2D.Float attackRange;
	public  int healthWidth = (int) (246*MainGame.SCALE);


    
	public Player(float x, float y, float width, float height) {
		super(x, y, width, height);
		loadAni();
		sound = new Sound();
		levelData = MapManager.levelData_Player;
		attackRange = new Rectangle2D.Float(x, y, width, height);
	}
	
	public void updatePlayer() {
		if(i >= frame1) i = 0;
		f = i++;

		if(attack) { 	
			if(attIndex == 3)
			   getSound(1);
			updateAttackTick();
		}	
		
	}	 
	
	

	
	private void updateState() {
		createHitbox(x + 60, y + 40, width-120, height - 74);
		setAttackRange();

		if(SwitchAction.action == 0) state_ani = 0;
		else if (SwitchAction.action == 1)state_ani = 1;

		if (jump) {
			if(acJumpSound) getSound(0);  
			acJumpSound = false;
			jump();

		}
	

		if (left && !right) {
			x -= 2.5;
			state_ani = 3;
		} else if (right && !left) {
			x += 2.5;
			state_ani = 2;
		}
		

		if(attack) { 
			if( (right && !left) || state_ani == 0)
			  state_ani = 4;
			else if((left && !right) || state_ani == 1)
			  state_ani = 5;
		}

		
		if (!inAir)
			if (!CheckHitBox.IsEntityOnFloor(hitbox, levelData))
				inAir = true; 

		if (inAir) {
			if (CheckHitBox.CanMoveHere(hitbox.x, hitbox.y + airSpeed, width-120, height - 74, levelData)) {
				y += airSpeed;
				airSpeed += gravity;
				acJumpSound = false;
			
			}
			else {
				airSpeed += gravity + 0.12;
				if (airSpeed > 0) inAir = false; airSpeed = 0; acJumpSound = true;
			}
		}
		

		if(!CheckHitBox.CanMoveHere(x + 60, y + 40, width-120, height - 74,levelData)) {
			if(right) x -= 2.5;
			if(left) x += 2.5;
		}
		

		
	}
	
	
	
	private void checkAttackingToEnemy() {
	  if(attack) {	
		  if(attIndex == 3){ 
			  Enemy.checkGetHitFromPlayer = true; 
			  Cannon.GetHit = true;
		  }else { 
			  Enemy.checkGetHitFromPlayer = false;
			  Cannon.GetHit = false;
		  }	
		 
	  }else 	
		  Enemy.checkGetHitFromPlayer = false;

	}


	
	private void jump() {	
		if (inAir) 
			return;
		inAir = true;
		airSpeed = jumpSpeed;

	}

	public void renderPlayer(Graphics g) {
		generalUpdate(g);
		if(attack)
           g.drawImage(Animation[state_ani][attIndex],(int) (x) - updateBigMap, (int) (y), (int)width, (int)height, null);
		else
		   g.drawImage(Animation[state_ani][f],(int) (x) - updateBigMap, (int) (y), (int)width, (int)height, null);
		PlayerGetHit      = false;
		PlayerGainHealth  = false;

	}
	
	
	
	private void generalUpdate(Graphics g) {
		updateState();
		getPlayerPosition();
		drawAttackRange(g);
		drawHeathBar(g);
		if(PlayerGetHit) { 
			y -= 2;
			updateHealth(1);
		}
		
		if(PlayerGainHealth) {
			addHealth(50);
		}
			
		

		checkAttackingToEnemy();
		
	}

	private void updateVolume() {
		  if(ChangeVolume == -60) 
		     sound.fc.setValue(-75);
		  else 
			 sound.fc.setValue(ChangeVolume);
	}
	
	public void addHealth(int i) {
		if(healthWidth + i > (int) (246*MainGame.SCALE))
			healthWidth = (int) (246*MainGame.SCALE);
		else
			healthWidth += i;
	}

	public void updateHealth(int i) {
		healthWidth -= (0.25)*i;
		if(healthWidth == 1) {
			Game_JPanel.selectionMenu = 1;
			MainGame.die = true;
			healthWidth = 0;
		}	
	}
	
	
	
	private void drawHeathBar(Graphics g) {
		g.drawImage(img_healthBar,0, 0, (int)(img_healthBar.getWidth()*MainGame.SCALE), (int)(img_healthBar.getHeight()*MainGame.SCALE), null);
		g.setColor(Color.red);
		g.fillRect(51, 21, healthWidth ,(int) (4*MainGame.SCALE));
	}

	
	private void setAttackRange() {
		
		   if((right && !left) || state_ani == 0 || state_ani == 2)
			   attackRange.setRect((int) (x) + 95 , (int) y + 35 , (int) hitbox.width, (int) hitbox.height);	
		   else if((left && !right) || state_ani == 1|| state_ani == 3)
			   attackRange.setRect((int) (x) + 15 , (int) y + 35 , (int) hitbox.width, (int) hitbox.height);

		   
	}
	
	
	private void drawAttackRange(Graphics g) {
		Player_AttackRange = (int) attackRange.x  - updateBigMap;
	}
	
	

	private void getPlayerPosition() {
		xPos = x + 60;
		hitbox.x += updateBigMap;
		PlayerX_RightPos = (int) (x + 60 - updateBigMap);
		PlayerX_LeftPos = (int) (x - updateBigMap);
		PlayerY_UpPos = (int) (y - 3);
	}
	

	private void loadAni() {
		InputStream is = getClass().getResourceAsStream("/knight.png");
		img_healthBar = Load.LoadImage(Load.HEALTH_BAR);
		try {
			img = ImageIO.read(is);
			Animation = new BufferedImage[6][7];
			for(int i = 0; i < 6; i++) {
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
	
    
	private void getSound(int i)  {
		
		try {
			
			sound.getSound(i, -40);
			updateVolume();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setRight(boolean right2) {
		this.right = right2;
	}
	
	public void setLeft(boolean left2) {
		this.left = left2;
	}
	


	public void setAttack(boolean attack2) {
		this.attack = attack2;
	}
	
	public void setJump(boolean jump2) {
		this.jump = jump2;
	}
	
	private void updateAttackTick() {
			attIndex++;
			if (attIndex >= 6) {
				attIndex = 0;
				attack = false;
			}


	}


}
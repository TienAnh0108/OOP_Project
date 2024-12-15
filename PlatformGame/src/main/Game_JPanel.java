package main;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;




public class Game_JPanel extends JPanel{

	private static final long serialVersionUID = 1L;
    private MainGame game;
    private PauseMenu pauseMenu;
    private DieMenu dieMenu;
    private PlayingMenu play;

    private static JLabel notification;
    public static int selectionMenu = 2;
    
    
	public Game_JPanel(MainGame game) {
		this.game = game;	
		

		
		pauseMenu = new PauseMenu();
		pauseMenu.setVisible(false);
		
		dieMenu = new DieMenu();
		dieMenu.setVisible(false);
		
		play = new PlayingMenu();
		play.setVisible(true);
		

		
        add(pauseMenu);
        add(dieMenu);
        add(play);
        doorNotification();
		setPanelSize();
		
     }
	

	private void setPanelSize() {
		Dimension size = new Dimension(MainGame.GAME_WIDTH, MainGame.GAME_HEIGHT);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);

	}
	
	private void doorNotification() {
		notification = new JLabel("You need a key !!");
		notification.setFont(new Font("Tahoma", Font.PLAIN, 150));
		notification.setForeground(Color.black);
		notification.setVisible(false);
		add(notification);
	}
	
	public static void displayNotification(int i) {
		if( i == 0)
		  notification.setVisible(false);
		else
		  notification.setVisible(true);
	}

	public void paintComponent(Graphics g) {	
		super.paintComponent(g);
        game.render(g);
		
	}

	
    public MainGame getGame() {
	 return game;
   }
    
    public void pauseMenu() {
    	if(!pauseMenu.isVisible() && selectionMenu == 0)
    		pauseMenu.setVisible(true);
    	else if(pauseMenu.isVisible() && selectionMenu == 0)
    		pauseMenu.setVisible(false);
    }
    
    public void dieMenu() {
    	if(selectionMenu == 1)
    		dieMenu.setVisible(true);
    	else
    		dieMenu.setVisible(false);
    }



}

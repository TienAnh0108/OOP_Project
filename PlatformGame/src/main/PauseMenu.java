package main;


import java.awt.Color;
import java.awt.Dimension;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.formdev.flatlaf.FlatDarkLaf;

import Character.Player;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PauseMenu extends JPanel {


	private static final long serialVersionUID = 1L;
	private JButton quit1;
	private JButton restart;
	private JButton continueButton;
	private JLabel gamePause;
	private JLabel music;
	private JLabel sound;
	private JSlider sliderMusic;
	private JSlider sliderSound;
	private JButton nextSong;
	private Image pauseImage,musicButImage,continueButImage,restartButImage,quit1ButImage;
	
	
	public PauseMenu() {
		
		 try {
			UIManager.setLookAndFeel(new FlatDarkLaf());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		setPreferredSize(new Dimension(500, 600));
		setOpaque(false);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		pauseImage = new ImageIcon(getClass().getResource("/pause Menu.png")).getImage();
		musicButImage = new ImageIcon(getClass().getResource("/Music Square Button.png")).getImage();
		continueButImage = new ImageIcon(getClass().getResource("/Continue Button.png")).getImage();
		restartButImage = new ImageIcon(getClass().getResource("/New Game Button.png")).getImage();
		quit1ButImage = new ImageIcon(getClass().getResource("/Quit Button.png")).getImage();
		
		continueButton = new JButton(){
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                g.drawImage(continueButImage, 0, 0, getWidth(), getHeight(), this);
                super.paintComponent(g);
            }
        };
        continueButton.setContentAreaFilled(false);
        continueButton.setBorderPainted(false);
		continueButton.setBounds(80, 375, 120, 40);
		continueButton.setFocusable(false);
		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
     			if(!MainGame.pause)
     				MainGame.pause = true;
     			else
     				MainGame.pause = false;
				setVisible(false);
				
			}
		});
		
		add(continueButton);
		
		restart = new JButton() {
	
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
	                g.drawImage(restartButImage, 0, 0, getWidth(), getHeight(), this);
	                super.paintComponent(g);
	            }
		};
		restart.setContentAreaFilled(false);
        restart.setBorderPainted(false);
		restart.setBounds(300, 375, 120, 40);
		restart.setFocusable(false);
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGame.restart = true;
	  			if(!MainGame.pause)
     				MainGame.pause = true;
     			else
     				MainGame.pause = false;
	  			MainGame.resetSong = true;
				setVisible(false);
			}});
		
		add(restart);
		
		gamePause = new JLabel("Game Paused");
		gamePause.setFont(new Font("Tahoma", Font.BOLD, 30));
		gamePause.setForeground(java.awt.Color.BLACK);
		gamePause.setBounds(163, 58, 213, 43);
		add(gamePause);
		
		quit1 = new JButton() {

			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
                g.drawImage(quit1ButImage, 0, 0, getWidth(), getHeight(), this);
                super.paintComponent(g);
            }
		};
		quit1.setContentAreaFilled(false);
        quit1.setBorderPainted(false);
		quit1.setBounds(190, 440, 120, 40);
		quit1.setFocusable(false);
		quit1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                promptPage();
            }
        });
		add(quit1);
		
		
		music = new JLabel("Music");
		music.setFont(new Font("Tahoma", Font.BOLD, 18));
		music.setForeground(java.awt.Color.BLACK);
		music.setBounds(70, 130, 62, 23);
		add(music);
		
		sound = new JLabel("Sound");
		sound.setFont(new Font("Tahoma", Font.BOLD, 18));
		sound.setForeground(java.awt.Color.BLACK);
		sound.setBounds(70, 248, 62, 23);
		add(sound);
		
		sliderMusic = new JSlider(-60,6,-23);
		sliderMusic.setBounds(145, 130, 200, 26);
		sliderMusic.setFocusable(false);
		sliderMusic.setForeground(java.awt.Color.BLACK);
		sliderMusic.setValue(-23);
		sliderMusic.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				changeVolumeMusic();
			}});
		add(sliderMusic);
		
		sliderSound = new JSlider(-60,6,-23);
		sliderSound.setBounds(145, 248, 200, 26);
		sliderSound.setFocusable(false);
		sliderSound.setForeground(java.awt.Color.BLACK);
		sliderSound.setValue(-40);
		sliderSound.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				changeVolumeMusic();			
			}});
		add(sliderSound);
		
		nextSong = new JButton() {

			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                g.drawImage(musicButImage, 0, 0, getWidth(), getHeight(), this);
                super.paintComponent(g);
            }
		};
		nextSong.setContentAreaFilled(false);
        nextSong.setBorderPainted(false);
		nextSong.setBounds(374, 119, 50, 50);
		nextSong.setFocusable(false);
		nextSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextSong();			
			}});
		add(nextSong);
		System.out.println(pauseImage.getClass());
		
	}
	
	private void promptPage() {
        int result = JOptionPane.showConfirmDialog(
            null,
            "A chicken loser?\nWant to give up?",
            "Confirmation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
	
	 protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(pauseImage, 0, 0, getWidth(), getHeight(), this);
	        
	    }

	private void changeVolumeMusic() {
		MainGame.ChangeVolume = sliderMusic.getValue();
		Player.ChangeVolume = sliderSound.getValue();
	}
	
	private void nextSong() {
		MainGame.nextSong = true;
	}
}
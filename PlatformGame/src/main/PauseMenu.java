package main;


import java.awt.Color;
import java.awt.Dimension;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.formdev.flatlaf.FlatDarkLaf;
import Character.Player;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Font;
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

	
	public PauseMenu() {
		
		 try {
			UIManager.setLookAndFeel(new FlatDarkLaf());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setPreferredSize(new Dimension(500, 500));
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		
		
		continueButton = new JButton("Continue");
		continueButton.setBounds(40, 375, 120, 56);
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
		
		restart = new JButton("Restart");
		restart.setBounds(170, 375, 132, 56);
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
		gamePause.setFont(new Font("Tahoma", Font.PLAIN, 30));
		gamePause.setBounds(163, 11, 213, 43);
		add(gamePause);
		
		quit1 = new JButton("Quit");
		quit1.setBounds(310, 375, 132, 56);
		quit1.setFocusable(false);
		quit1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                promptPage();
            }
        });
		add(quit1);
		
		
		music = new JLabel("Music");
		music.setFont(new Font("Tahoma", Font.PLAIN, 20));
		music.setBounds(37, 116, 62, 23);
		add(music);
		
		sound = new JLabel("Sound");
		sound.setFont(new Font("Tahoma", Font.PLAIN, 20));
		sound.setBounds(37, 248, 62, 23);
		add(sound);
		
		sliderMusic = new JSlider(-60,6,-23);
		sliderMusic.setBounds(135, 116, 200, 26);
		sliderMusic.setFocusable(false);
		sliderMusic.setValue(-23);
		sliderMusic.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				changeVolumeMusic();
			}});
		add(sliderMusic);
		
		sliderSound = new JSlider(-60,6,-23);
		sliderSound.setBounds(135, 248, 200, 26);
		sliderSound.setFocusable(false);
		sliderSound.setValue(-40);
		sliderSound.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				changeVolumeMusic();			
			}});
		add(sliderSound);
		
		nextSong = new JButton("Next Song");
		nextSong.setBounds(374, 119, 100, 33);
		nextSong.setFocusable(false);
		nextSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextSong();			
			}});
		add(nextSong);
		
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

	private void changeVolumeMusic() {
		MainGame.ChangeVolume = sliderMusic.getValue();
		Player.ChangeVolume = sliderSound.getValue();
	}
	
	private void nextSong() {
		MainGame.nextSong = true;
	}
}
package main;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.FlatDarkLaf;

import LoadMap.MapManager;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PlayingMenu extends JPanel {


	private static final long serialVersionUID = 1L;
	private JButton play;

	public PlayingMenu() {
		
		 try {
			UIManager.setLookAndFeel(new FlatDarkLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setPreferredSize(new Dimension(1248, 700));
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		play = new JButton("Play");
		play.setFocusable(false);
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playGame();
				setVisible(false);
			}
		});
		play.setFont(new Font("Tahoma", Font.PLAIN, 99));
		play.setBounds(335, 226, 562, 235);
		add(play);

	}
	
	private void playGame() {
		MainGame.pause = false;
		MapManager.firstCheck = false;
		Game_JPanel.selectionMenu = 0;
	}
}

package main;


import java.awt.Color;
import java.awt.Dimension;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DieMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton restart;
	private JLabel diePause;
	private JLabel label; 
	private JButton quit;


	public DieMenu() {
		
		 try {
			UIManager.setLookAndFeel(new FlatDarkLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		

		setPreferredSize(new Dimension(500, 500));
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		restart = new JButton("Restart");
		restart.setBounds(169, 244, 132, 56);
		restart.setFocusable(false);
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGame.restart = true;
     			MainGame.pause = false;
     			MainGame.die = false;
	  			MainGame.resetSong = true;
				setVisible(false);
			}});
		add(restart);
		
		diePause = new JLabel(" You Died");
		diePause.setFont(new Font("Tahoma", Font.PLAIN, 50));
		diePause.setBounds(125, 11, 250, 143);
		add(diePause);
		
		label = new JLabel("Such a chicken !");
		label.setFont(new Font("Tahoma", Font.PLAIN, 50));
		label.setBounds(80, 129, 388, 85);
		add(label);
		
		quit = new JButton("Quit ");
		quit.setFocusable(false);
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				promptPage();         
			}});
		quit.setBounds(169, 376, 132, 56);
		add(quit);
		
		

		
	}
	
	private void promptPage() {
		int result = JOptionPane.showConfirmDialog(null, "A chicken loser ?\nWant to give up ?", "Confirmation",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		  if (result == JOptionPane.YES_OPTION) {
			  System.exit(0);  
		  }
	}
	
	
}

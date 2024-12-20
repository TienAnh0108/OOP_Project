package main;


import java.awt.Color;
import java.awt.Dimension;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DieMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton restart;
	private JLabel diePause;
	private JLabel label; 
	private JButton quit;
	private Image dieBgImage,restartButImage,quit1ButImage;


	public DieMenu() {
		
		 try {
			UIManager.setLookAndFeel(new FlatDarkLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		dieBgImage = new ImageIcon(getClass().getResource("/pause Menu.png")).getImage();
		restartButImage = new ImageIcon(getClass().getResource("/New Game Button.png")).getImage();
		quit1ButImage = new ImageIcon(getClass().getResource("/Quit Button.png")).getImage();
		 
		setPreferredSize(new Dimension(500, 600));
		setOpaque(false);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		restart = new JButton(){
			@Override
			 protected void paintComponent(Graphics g) {
	                g.drawImage(restartButImage, 0, 0, getWidth(), getHeight(), this);
	                super.paintComponent(g);
	            }
		};
		restart.setContentAreaFilled(false);
        restart.setBorderPainted(false);
		restart.setBounds(179, 254, 132, 56);
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
		diePause.setFont(new Font("Tahoma", Font.BOLD, 45));
		diePause.setForeground(java.awt.Color.BLACK);
		diePause.setBounds(135, 13, 250, 143);
		add(diePause);
		
		label = new JLabel("Such a chicken !");
		label.setFont(new Font("Tahoma", Font.BOLD, 45));
		label.setForeground(java.awt.Color.BLACK);
		label.setBounds(80, 129, 388, 85);
		add(label);
		
		quit = new JButton() {
			protected void paintComponent(Graphics g) {
                g.drawImage(quit1ButImage, 0, 0, getWidth(), getHeight(), this);
                super.paintComponent(g);
            }
		};
		quit.setContentAreaFilled(false);
        quit.setBorderPainted(false);
		quit.setFocusable(false);
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				promptPage();         
			}});
		quit.setBounds(179, 376, 132, 56);
		add(quit);	
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(dieBgImage, 0, 0, getWidth(), getHeight(), this);
        
    }
	
	private void promptPage() {
		int result = JOptionPane.showConfirmDialog(null, "A chicken loser ?\nWant to give up ?", "Confirmation",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		  if (result == JOptionPane.YES_OPTION) {
			  System.exit(0);  
		  }
	}
	
	
}

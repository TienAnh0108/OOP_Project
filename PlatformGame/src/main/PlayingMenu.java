package main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarkLaf;

import LoadMap.MapManager;

public class PlayingMenu extends JPanel {

    private static final long serialVersionUID = 1L;
    private JButton play;
    private Image backgroundImage;

    public PlayingMenu() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        setPreferredSize(new Dimension(1248, 700));
        setLayout(null);

        backgroundImage = new ImageIcon(getClass().getResource("/background1.png")).getImage();
        
        
        play = new JButton("Play");
        
        play.setFocusable(false);
        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playGame();
                setVisible(false);
            }
        });
        play.setFont(new Font("Tahoma", Font.PLAIN, 13));
        play.setBounds(550, 540, 120, 50);
        add(play);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        
    }

    private void playGame() {
        MainGame.pause = false;
        MapManager.firstCheck = false;
        Game_JPanel.selectionMenu = 0;
    }
}

package main;



import javax.swing.JFrame;
import KeyBoardInput.KeyBoardAction;




public class Game_JFrame_Window {

	
	private JFrame frame;



	public Game_JFrame_Window(Game_JPanel panel) {
        
		
		frame = new JFrame();	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(new KeyBoardAction(panel));
		frame.requestFocus();
        frame.setResizable(false);
		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	

	
}

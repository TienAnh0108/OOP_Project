package KeyBoardInput;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

	Clip clip;
	public FloatControl fc;
	public URL soundURL[] = new URL[30];
	
	public Sound() {
		soundURL[0] = getClass().getResource("/jump.wav");
		soundURL[1] = getClass().getResource("/attackSound.wav");
		soundURL[2] = getClass().getResource("/Pitifulface.wav");
		soundURL[3] = getClass().getResource("/VuaHanVuaYeu.wav");
		

	}
	
	public void getSound(int i,int volume) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
     	    AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL[i]);
     	    clip = AudioSystem.getClip();
     	    clip.open(audioStream); 
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            fc.setValue(volume);
     	    clip.start();
	}
	
	public void reset() {
		clip.setFramePosition(0);
	}
	
	public void nextSong(int index,int volume) {
		clip.stop();
		clip.setFramePosition(0);
		clip.close();
		try {
			getSound(index,volume);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public boolean checkActive() {
		if(clip.isActive()) return true;
		
		return false;
	}
	


}

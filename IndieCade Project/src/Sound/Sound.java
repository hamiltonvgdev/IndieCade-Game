package Sound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound 
{
	static Clip klip;

	public static void playSound(String ref)
	{
		try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream
	        		(new File(ref).getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	
	public static void infiniteLoopSound(String ref)
	{
		if(klip == null || !klip.isActive())
		{
			try {
		        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream
		        		(new File(ref).getAbsoluteFile());
		        klip = AudioSystem.getClip();
		        klip.open(audioInputStream);
		        klip.start();
		    } catch(Exception ex) {
		        System.out.println("Error with playing sound.");
		        ex.printStackTrace();
		    }
		}
	}
	
	public static void loopSound(String ref, int loops)
	{
		try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream
	        		(new File(ref).getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.loop(loops);;
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
}

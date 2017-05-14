package Render;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class AnimationSet implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6784000698013223393L;
	String ref;
	ArrayList<BasicImage> Animation;
	int frame;
	long delay;
	public long lastTick;
	boolean ended;
	
	int counter;
	
	boolean flip;
	
	public boolean afterImage;
	
	public AnimationSet(String ref, long delay)
	{
		this.ref = ref;
		File folder = new File(ref);
		File[] list = folder.listFiles();
		
		Animation = new ArrayList<BasicImage>();
		
		for(File frame : list)
		{
			BasicImage sprite = new BasicImage(frame.getPath());
			Animation.add(sprite);
		}
		this.delay = delay;
		lastTick = System.currentTimeMillis();
		
		frame = 0;
		counter = 0;
		
		afterImage = false;
		
		ended = false;
	}
	
	public void resetAnimate()
	{
		if(System.currentTimeMillis() - lastTick >= delay)
		{
			if(frame < Animation.size() - 1)
			{
				frame ++;
			}else
			{
				frame = 0;
			}
			
			lastTick = System.currentTimeMillis();
		}
	}
	
	public void loopAnimate()
	{
		if(System.currentTimeMillis() - lastTick >= delay)
		{
			if(counter < Animation.size() - 1)
			{
				frame ++;
				counter ++;
			}else if(counter < Animation.size()*2 - 2)
			{
				frame --;
				counter ++;
			}else
			{
				counter = 0;
			}
			
			lastTick = System.currentTimeMillis();
		}
	}
	
	public void randomAnimate()
	{
		Random gen = new Random();
		
		if(System.currentTimeMillis() - lastTick >= delay)
		{
			int index = gen.nextInt(Animation.size() - 1) + 1;
			
			frame = index;
			
			lastTick = System.currentTimeMillis();
		}
		
	}
	
	public void endAnimate()
	{
		if(System.currentTimeMillis() - lastTick >= delay)
		{
			if(frame < Animation.size() - 1)
			{
				frame ++;
			}else
			{
				ended = true;
			}
			
			lastTick = System.currentTimeMillis();
		}
	}
	
	public void setAfterImage(int num, long delay)
	{
		for(int i = 0; i < Animation.size(); i ++)
		{
			Animation.get(i).setAfterImage(num, delay);
		}
	}
	
	public void setFrame(int frame)
	{
		this.frame = frame;
	}
	
	public void toggleAfterImage(boolean toggle)
	{
		for(int i = 0; i < Animation.size(); i ++)
		{
			Animation.get(i).toggleAfterImage(toggle);
		}
		
		afterImage = toggle;
	}
	
	public void moveAfterImage(float xa, float ya)
	{
		for(int i = 0; i < Animation.size(); i ++)
		{
			Animation.get(i).getAfterImage().move(xa, ya);
		}
	}
	
	public void afterImageQuality(int id, int index, float opacity, float r, float g, float b)
	{
		for(int i = 0; i < Animation.size(); i ++)
		{
			if(id == 0)
			{
				Animation.get(i).getAfterImage().setIndividualOpacity(index, opacity);
			}else if(id == 1)
			{
				Animation.get(i).getAfterImage().toggleDecreasingOpacity();
			}else if(id == 2)
			{
				Animation.get(i).getAfterImage().toggleIncreasingOpacity();
			}else if(id == 3)
			{
				Animation.get(i).getAfterImage().colorAll(r, g, b);
			}else if(id == 4)
			{
				Animation.get(i).getAfterImage().setIndividualColor(index, r, g, b);
			}else if(id == 5)
			{
				Animation.get(i).getAfterImage().shadeAll(r, g, b);
			}
		}
	}
	
	public void render(float x, float xOffset, 
			float y, float yOffset, float width, float height, float rot, Graphics g) throws SlickException 
	{
		Animation.get(frame).render(x, xOffset, y, yOffset, width, height, rot, g);
	}
	
	public void setFlip(boolean Flip)
	{
		for(BasicImage frame : Animation)
		{
			frame.setFlip(Flip);
			if(frame.afterImage != null && frame.afterImage.present)
			{
				frame.afterImage.setFlip(Flip);
			}
		}
		
		flip = Flip;
	}
	
	public void setDelay(long delay)
	{
		this.delay = delay;
	}
	
	public void reset()
	{
		frame = 0;
	}
	
	public String getFolder()
	{
		return ref;
	}
	
	public long getDelay()
	{
		return delay;
	}
	
	public String getRef(int i)
	{
		return Animation.get(i).getPath();
	}
	
	public ArrayList<BasicImage> getSet()
	{
		return Animation;
	}
	
	public int getCurrentIndex()
	{
		return frame;
	}
	
	public boolean ended()
	{
		return ended;
	}
	
	public boolean getFlip()
	{
		return flip;
	}
	
	public BasicImage getCurrentFrame()
	{
		return Animation.get(getCurrentIndex());
	}
}

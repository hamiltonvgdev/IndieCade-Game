package Render;

import java.io.Serializable;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BasicImage implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8601732697410596805L;
	public AfterImage afterImage;
	transient Image sprite;
	boolean flip;
	
	public float x;
	public float y;
	public float width;
	public float height;
	public float rot;
	
	public BasicImage(String ref)
	{
		flip = false;
		
		if(ref.equals("") || ref.equals(null))
		{
			setError();
		}else
		{
			try {
				sprite = new Image(ref);
			} catch (SlickException e) {
				setError();
			}
		}
		
		x = 0;
		y = 0;
		width = 0;
		height = 0;
		rot = 0;
	}

	public String getPath()
	{
		return sprite.getResourceReference();
	}
	
	private void setError()
	{
		try {
			sprite = new Image("res/The Error Picture.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void setFlip(boolean flip)
	{
		this.flip = flip;
	}
	
	public void setAfterImage(int num, long delay)
	{
		afterImage = new AfterImage(getPath(), num, delay, sprite.getWidth(), sprite.getHeight());
	}
	
	public void toggleAfterImage(boolean toggle)
	{
		afterImage.setAfterImage(toggle);
	}
	
	public AfterImage getAfterImage()
	{
		return afterImage;
	}
	
	public Image getImage()
	{
		return sprite;
	}
	
	public void render(float x, float y, float width, float height, float rot, Graphics g) throws SlickException 
	{ 
	    if(afterImage != null && afterImage.present)
	    {
	    	afterImage.setDimensions(width, height);
	    	afterImage.update(x, y, rot);
	       	afterImage.render(g);
	    }
	    
	    this.x = x;
	    this.y = y;
	    this.width = width;
	    this.height = height;
	    this.rot = rot;
	    
		Image image = sprite.copy();
	    image = sprite.getFlippedCopy(this.flip, false);
	    image.setFilter(2);
	    image.setRotation(rot);
	    image.setCenterOfRotation(width / 2.0F, height / 2.0F);
	    image.draw(x - width / 2.0F, y - height / 2.0F , width, height);
	}
}

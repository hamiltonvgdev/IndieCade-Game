package Thing;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Render.AnimationSet;
import Tiles.Tile;

public class Thing 
{
	float x;
	float y;
	float height;
	float width;
	float rot;
	
	AnimationSet sprite;
	Tile tile;
	
	public Thing(String ref, long delay)
	{
		sprite = new AnimationSet(ref, delay);
	}
	
	public Thing setTile(Tile tile)
	{
		this.tile = tile;
		x = tile.getX();
		y = tile.getY();
		return this;
	}
	
	public Thing setDimension(float width, float height, float rot)
	{
		this.height = height;
		this.width = width;
		this.rot = rot;
		return this;
	}
	
	public void update()
	{
		x = tile.getX();
		y = tile.getY();
		
		sprite.resetAnimate();
	}
	
	public void render(Graphics g, float xOffset, float yOffset) throws SlickException
	{
		sprite.render(x, xOffset, y, yOffset, width, height, rot, g);
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public float getHeight()
	{
		return height;
	}
	
	public float getWidth()
	{
		return width;
	}
	
	public float getRot()
	{
		return rot;
	}
}

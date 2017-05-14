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
	boolean collide;
	boolean permanent;
	
	
	long age;
	long tick;
	public boolean ended;
	
	public Thing(String ref, long delay)
	{
		sprite = new AnimationSet(ref, delay);
		age = -1;
		tick = System.currentTimeMillis();
		
		collide = false;
		permanent = false;
		ended = false;
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
	
	public Thing setCollidable(boolean collide, boolean permanent)
	{
		this.collide = collide;
		
		this.permanent = permanent;
		
		return this;
	}
	
	public Thing setAge(long age)
	{
		this.age = age;
		return this;
	}
	
	public void setPosition(float xa, float ya)
	{
		x = xa;
		y = ya;
	}
	
	public void update()
	{
		if(!ended)
		{
			if(!permanent && collide != tile.getOriginalCollidable())
			{
				tile.setCollide(collide);
			}
			x = tile.getX();
			y = tile.getY();
			
			if(age > 0 &&
					System.currentTimeMillis() - tick >= age)
			{
				end();
			}
		}
	}
	
	public void render(Graphics g, float xOffset, float yOffset) throws SlickException
	{
		sprite.render(x, xOffset, y, yOffset, width, height, rot, g);
	}
	
	public void end()
	{
		if(!permanent && collide != tile.getOriginalCollidable())
		{
			tile.setCollide(tile.getOriginalCollidable());
		}
		
		tile.getMap().getLevel().removeThing(this);
		ended = true;
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

	public AnimationSet getSprite() 
	{
		return sprite;
	}
	
	public Thing clone(Tile tile)
	{
		return new Thing(sprite.getFolder(), sprite.getDelay()).
				setDimension(width, height, rot).setTile(tile).setCollidable(collide, permanent).setAge(age);
		
	}
}

package Tiles;

import java.io.Serializable;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Geo.Quad;
import Map.Map;
import Render.AnimationSet;

public class Tile implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6125590096932271699L;

	public String name;
	
	transient Color Id;
	
	boolean collidable;
	Quad hitbox;
	public boolean on;
	public boolean nextTo;
	
	float x;
	float y;
	
	AnimationSet sprite;
	final float width = 64;
	final float height = 64;
	float rot;
	
	boolean sounded;
	String ref;
	
	float friction;
	
	Map map;
	
	int Type;
	
	public Tile(String name, Color Id)
	{
		this.name = name;
		
		collidable = false;
		this.Id = Id;
		
		hitbox = new Quad(x, y, width, height);
		
		friction = 0.075F;
		
		sounded = false;
		ref = null;
		
		on = false;
		nextTo = true;
		
		Type = 0;
		rot = 0;
	}
	
	public void changeCoordinates(float xa, float ya)
	{
		x = xa;
		y = ya;
	}
	
	public void setMap(Map map)
	{
		this.map = map;
	}
	
	public Tile setSound(String ref)
	{
		this.ref = ref;
		return this;
	}
	
	public Tile setAnimation(String ref, long delay)
	{
		sprite = new AnimationSet(ref, delay);
		return this;
	}
	
	public Tile setColidable(boolean collide)
	{
		collidable = collide;
		return this;
	}
	
	public Tile setFriction(float friction)
	{
		this.friction = friction;
		return this;
	}
	
	public void shift(float xa, float ya)
	{
		x += xa;
		y += ya;
	}
	
	public void reset()
	{
		on = false;
		nextTo = false;
	}
	
	public void update()
	{
		hitbox.changeDimensions(x, y, width, height);
		
		sprite.resetAnimate();
		
		if(on && !sounded && ref != null)
		{
			Sound.Sound.infiniteLoopSound(ref);
			sounded = true;
		}else if(!on)
		{
			sounded = false;
		}
	}

	public void render(Graphics g) throws SlickException
	{
		sprite.render(x, y, width, height, rot, g);
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getRef()
	{
		return sprite.getFolder();
	}
	
	public long getDelay()
	{
		return sprite.getDelay();
	}
	
	public String getSoundRef()
	{
		return ref;
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public float getFriction()
	{
		return friction;
	}
	
	public boolean getCollidable()
	{
		return collidable;
	}
	
	public Quad getHitbox()
	{
		return hitbox;
	}
	
	public Color getID()
	{
		return Id;
	}
	
	public Map getMap()
	{
		return map;
	}

	public int getType() 
	{
		return Type;
	}
}

package Tiles;

import java.io.Serializable;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Geo.Quad;
import Map.Map;
import Render.AnimationSet;
import Render.BasicImage;
import Thing.Thing;

public class Tile implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6125590096932271699L;

	public String name;
	
	transient Color Id;
	
	boolean collidable;
	boolean collidableO;
	Quad hitbox;
	public boolean on;
	public boolean nextTo;
	
	float x;
	float y;
	
	AnimationSet Sprite;
	final float width = 64;
	final float height = 64;
	float rot;
	
	boolean sounded;
	String ref;
	
	float friction;
	
	Map map;
	
	int Type;

	float yOffset;
	float xOffset;
	
	ArrayList<Thing> Things;
	
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
		
		xOffset = 0;
		yOffset = 0;
		
		Things = new ArrayList<Thing>();
		
	}
	
	public void changeCoordinates(float xa, float ya)
	{
		x = xa;
		y = ya;
	}
	
	public void setMap(Map map)
	{
		this.map = map;
		
		for(Thing thing: Things)
		{
			map.getLevel().addThing(thing.setTile(this));
		}
	}
	

	public void setCollide(boolean collide)
	{
		collidable = collide;
	}
	
	public void postSetAction()
	{
		
	}
	
	public Tile setSound(String ref)
	{
		this.ref = ref;
		return this;
	}
	
	public Tile setAnimation(String ref, long delay)
	{
		Sprite = new AnimationSet(ref, delay);
		return this;
	}
	
	public Tile setColidable(boolean collide)
	{
		collidable = collide;
		collidableO = collide;
		return this;
	}
	
	public Tile setFriction(float friction)
	{
		this.friction = friction;
		return this;
	}
	
	public Tile addThing(Thing thing)
	{
		Things.add(thing);
		return this;
	}
	
	public Tile addThings(ArrayList<Thing> things)
	{
		for(Thing thing: things)
		{
			Things.add(thing);
		}
		return this;
	}
	
	public void shift(float xa, float ya)
	{
		xOffset += xa;
		yOffset += ya;
	}
	
	public void move(float xa, float ya)
	{
		x += xa;
		y += ya;
	}
	
	public void reset()
	{
		on = false;
		nextTo = false;
		xOffset = 0;
		yOffset = 0;
	}
	
	public void update()
	{
		hitbox.changeDimensions(x, y, width, height);
		
		Sprite.resetAnimate();
		
		
		if(on && !sounded && ref != null)
		{
			Sound.Sound.infiniteLoopSound(ref);
			sounded = true;
		}else if(!on)
		{
			sounded = false;
		}
		
		for(Thing th: Things)
		{
			th.update();
		}
	}

	public void render(Graphics g) throws SlickException
	{
		Sprite.render(x, xOffset, y, yOffset, width, height, rot, g);
		
		
		for(Thing th: Things)
		{
			th.render(g, xOffset, yOffset);
		}
		
		/*if(collidableO)
		{
			hitbox.render(g);
		}*/
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getRef()
	{
		return Sprite.getFolder();
	}
	
	public long getDelay()
	{
		return Sprite.getDelay();
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
	
	public float getXOffset()
	{
		return xOffset;
	}
	
	public float getYOffset()
	{
		return yOffset;
	}
	
	public float getFriction()
	{
		return friction;
	}
	
	public boolean getCollidable()
	{
		return collidable;
	}
	
	public boolean getOriginalCollidable()
	{
		return collidableO;
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
	
	public ArrayList<Thing> getThings()
	{
		return Things;
	}
}

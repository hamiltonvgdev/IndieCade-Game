package Tiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Geo.Quad;
import Render.AnimationSet;

public class Tile 
{
	public String name;
	
	Color Id;
	boolean spawnable;
	
	boolean collidable;
	Quad hitbox;
	float x;
	float y;
	
	AnimationSet sprite;
	final float width = 64;
	final float height = 64;
	
	float friction;
	
	public Tile(String name, Color Id)
	{
		this.name = name;
		
		collidable = false;
		this.Id = Id;
		spawnable = false;
		
		hitbox = new Quad(x, y, width, height);
		
		friction = 0.5F;
	}
	
	public void changeCoordinates(float xa, float ya)
	{
		x = xa;
		y = ya;
	}
	
	public Tile setAnimation(String ref)
	{
		sprite = new AnimationSet(ref, 100);
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
	
	public void update()
	{
		hitbox.changeDimensions(x, y, width, height);
	}

	public void render(Graphics g) throws SlickException
	{hitbox.changeDimensions(x, y, width, height);
		sprite.render(x, y, width, height, 0, g);
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getRef()
	{
		return sprite.getFolder();
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
	
	public boolean getSpawnable()
	{
		return spawnable;
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
}

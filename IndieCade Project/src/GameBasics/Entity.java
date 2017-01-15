package GameBasics;

import java.io.Serializable;

import org.newdawn.slick.Graphics;

import Geo.Quad;
import Render.AnimationSet;
import Render.BasicImage;

public abstract class Entity implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1335939607570570293L;
	float x;
	float y;
	float Vx;
	float Vy;
	float Ax;
	float Ay;
	
	Quad Hitbox;
	
	float height;
	float width;
	AnimationSet Base;
	public Entity()
	{
		
	}
	
	public void init()
	{
		width = ((BasicImage)Base.getSet().get(0)).getImage().getWidth();
		height = ((BasicImage)Base.getSet().get(0)).getImage().getHeight();
		Hitbox = new Quad(x, y, width, height);
		
		//Finish initializing
	}
	
	public Entity setLocation(float xa, float ya)
	{
		x = xa;
		y = ya;
		
		
		
		return this;
	}
	
	public void update()
	{
		
	}
	
	public void render(Graphics g)
	{
		
	}
	
	public Quad getHitbox()
	{
		return Hitbox;
	}
}

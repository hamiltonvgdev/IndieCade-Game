package GameBasics;

import java.io.Serializable;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Geo.Quad;
import Main.Config;
import Player.Player;
import Render.AnimationSet;
import Tiles.Tile;

public class BasicNPC implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5091083775805350494L;
	String name;
	Player player;
	String phrase;
	
	AnimationSet Idle;
	boolean idle;
	AnimationSet Near;
	boolean near;
	AnimationSet Active;
	boolean active;
	
	float x;
	float y;
	float Vx;
	float Vy;
	float Ax;
	float Ay;
	
	float height;
	float width;
	float rot;
	float range;
	Quad hitbox;
	
	public BasicNPC(Player player, String name, float range)
	{
		this.name = name;
		phrase = null;
		
		Idle = null;
		Active = null;
		Near = null;
		
		idle = true;
		near = false;
		active = false;
		
		this.range = range;
		
		this.player = player;
		
		hitbox = new Quad(x, y, width, height);
		
		Vx = 0;
		Vy = 0;
		Ax = 0;
		Ay = 0.5F;
	}
	
	public BasicNPC setCoordinates(float x, float y)
	{
		this.x = x;
		this.y = y;
		return this;
	}
	
	public BasicNPC setDimensions(float width, float height, float rot)
	{
		this.width = width;
		this.height = height;
		this.rot = rot;
		return this;
	}
	
	public BasicNPC setPhrase(String phrase)
	{
		this.phrase = phrase;
		return this;
	}
	
	public BasicNPC setIdleImage(String ref, long delay)
	{
		Idle = new AnimationSet(ref, delay);
		return this;
	}
	
	public BasicNPC setNearImage(String ref, long delay)
	{
		Near = new AnimationSet(ref, delay);
		return this;
	}
	
	public BasicNPC setActiveImage(String ref, long delay)
	{
		Active = new AnimationSet(ref, delay);
		return this;
	}
	
	public void update()
	{
		Physics();
		
		x += Vx;
		y += Vy;
		hitbox.changeDimensions(x, y, width, height);
		
		if(distanceSense(player))
		{
			near = true;
		}else
		{
			near = false;
		}
		
		if(hitbox.checkPoint(Mouse.getX(), Config.HEIGHT - Mouse.getY()) && 
				Mouse.isButtonDown(player.getInput().MOUSE_LEFT_BUTTON) && !active && near)
		{
			active = true;
			action();
		}else if(!near)
		{
			active = false;
		}
	}
	
	public void render(Graphics g) throws SlickException
	{
		if(active && Active != null)
		{
			Active.render(x, y, width, height, rot, g);
		}else if(near && Near != null)
		{
			Near.render(x, y, width, height, rot, g);
		}else if(idle && Idle != null)
		{
			Idle.render(x, y, width, height, rot, g);
		}
		
		if(near)
		{
			speak(g);
		}
		
		g.drawString(name, x - name.length() * 4, y + height / 3 * 2);
	}
	public void Physics()
	{	
		boolean CollisionX = false;
		boolean CollisionY = false;
		
		for(Tile T : player.getMap().getTiles())
		{
			if(T.getCollidable() && T.getHitbox().checkQuad(new Quad(x + Vx + Ax, y, width, height)))
			{
				CollisionX = true;
			}
			
			if(T.getCollidable() && T.getHitbox().checkQuad(new Quad(x, y + Vy + Ay, width, height)))
			{
				CollisionY = true;
			}
		}
		
		if(CollisionX)
		{
			Vx = 0;
		}else
		{
			Vx += Ax;
		}
		
		if(CollisionY)
		{
			Vy = 0;
		}else
		{
			Vy += Ay;
		}
		
	}
	
	public void speak(Graphics g)
	{
		if(phrase != null)
		{
			g. drawString(phrase, x - phrase.length() * 4, y - height);
		}
	}
	
	public boolean distanceSense(Player protag)
	{
		double space = Math.sqrt(Math.pow(x - 
				protag.getX(), 2) + Math.pow(y - protag.getY(), 2));
		
		if(space <= range)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}

	public void action()
	{
		
	}

	public void shift(float xa, float ya) 
	{
		x += xa;
		y += ya;
	}
}

package GameBasics;

import java.io.Serializable;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import BoneStructure.BoneStructure;
import Geo.Quad;
import Main.Config;
import Map.Map;
import Render.AnimationSet;
import Render.BasicImage;
import Tiles.Tile;

public abstract class Entity implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1335939607570570293L;
	
	boolean paused;
	
	//Horizontal movement
	float x;
	float Vx;
	float Vy;
	float Ax;
	float Ay;
	float acceleration;
	
	//Vertical movement
	float y;
	float jumpV;
	int jump;
	int maxjump;
	long jumpTick;
	boolean jumping;
	
	//Combat statistics
	float maxHealth;
	float health;
	float damage;
	float defense;
	float speed;
	float tenacity;
	
	//Combat values
	float Stun;
	float Speed;
	
	//World
	Map map;
	
	//Renders
	float width;
	float height;
	public Quad Hitbox;
	Quad Screen;
	Level level;
	
	public Entity(float x, float y, float speed)
	{
		
		paused = false;
		
		this.x = x;
		this.y = y;
		jumpV = -10F;
		jump = 0;
		maxjump = 2;
		jumpTick = System.currentTimeMillis();
		jumping = false;
		
		Vx = 0;
		Vy = 0;
		
		Ax = 0;
		Ay = 0.5F;
		acceleration = 0.5F;
		
		width = 64;
		height = 64;
		
		Hitbox = new Quad(x, y, width, height);
		Screen = new Quad(x, y, Config.WIDTH, Config.HEIGHT);
		
		maxHealth = 100;
		health = maxHealth;
		defense = 5;
		damage = 5;
		tenacity = 0;
		this.speed = 5;
	}
	public void setMap(Map map)
	{
		this.map = map;
	}
	
	public void Physics()
	{
		Vx += Ax;
		
		boolean CollisionX = false;
		boolean CollisionY = false;
		
		for(Tile T : map.getTiles())
		{
			if(T.getCollidable() && T.getHitbox().checkQuad(new Quad(x + Vx + Ax, y, width, height)))
			{
				CollisionX = true;
				T.nextTo = true;
			}else
			{
				T.nextTo = false;
			}
			
			if(T.getCollidable() && T.getHitbox().checkQuad(new Quad(x, y + Vy + Ay, width, height)))
			{
				CollisionY = true;
				T.on = true;
			}else
			{
				T.on = false;
			}
		}
		
		if(CollisionX)
		{
			Vx = 0;
		}
		
		if(CollisionY)
		{
			jumping = false;
			if(jump < maxjump && Vy > 0)
			{
				jump ++;
			}
			Vy = 0;
		}else
		{
			Vy += Ay;
			if(!jumping)
			{
				jump = 0;
			}
		}
		
	}
	
	
	public void update()
	{
		if(!paused)
		{
			Physics();
			
			if(Math.abs(Vx) < map.getCurrentTile(x, y + 50).getFriction())
			{
				Vx = 0;
			}
			
			if(health <= 0)
			{
				die();
			}
		}
		
		map.shift(-Vx, 0);
		map.shift(0, -Vy);
		
		Hitbox.changeDimensions(x, y, width, height);
		Screen.changeDimensions(x, y, Config.WIDTH, Config.HEIGHT);
	}
	
	public void render(Graphics g) throws SlickException
	{
		Hitbox.render(g);
		Screen.render(g);
	}
	
	public void jump()
	{
		Vy = jumpV;
		jump --;
		jumping = true;
		
		jumpTick = System.currentTimeMillis();
	}
	
	public void pause()
	{
		paused = true;
	}
	
	public void unpause()
	{
		paused = false;
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public float getVx()
	{
		return Vx;
	}
	
	public float getVy()
	{
		return Vy;
	}
	
	public Map getMap()
	{
		return map;
	}
	
	//...
	public void damage(int damage)
	{
		health -= damage;
	}
	
	public void die()
	{
		for(int i = 0; i < level.getEntities().size(); i ++)
		{
			if(level.getEntities().get(i) == this)
			{
				level.getEntities().remove(i);
			}
		}
		
	}
	public Quad getHitbox() 
	{
		return Hitbox;
	}
}

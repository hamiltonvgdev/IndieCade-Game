package GameBasics;

import java.io.Serializable;
import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import BoneStructure.BoneStructure;
import Geo.Quad;
import Main.Config;
import Map.Map;
import Player.Player;
import Render.AnimationSet;
import Render.BasicImage;
import Tiles.Tile;

public class Entity implements Serializable
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
	boolean stun;
	
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
	
	//animation
	AnimationSet sprite;
	
	Random gen = new Random();
	
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
		
		move(-Vx, 0);
		move(0, -Vy);
		
		Hitbox.changeDimensions(x, y, width, height);
		Screen.changeDimensions(x, y, Config.WIDTH, Config.HEIGHT);
		
	}
	
	public void render(Graphics g) throws SlickException
	{
		sprite.render(x, y, width, height, 0, g);
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
	
	
	public Entity AnimationSet(String ref, long delay)
	{
		sprite = new AnimationSet(ref, delay);
		return this;
	}
	
	public Entity setDimensions(float width, float height)
	{
		this.width = width;
		this.height = height;
		return this;
	}
	
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
	
	public void follow(Player player)
	{
		float distX = player.getX() - x;
		float distY = player.getY() - y;
		
		if(distY != 0)
		{
			try
			{
				move(0, (distY) / (Math.abs(distY)));
			}
			catch(Exception e)
			{
				move(0, 0);
			}
		}else
		{
			move(0, 0);
		}
		
		if(distX != 0)
		{
			try
			{
				move((distX) / (Math.abs(distX)) , 0);
			}
			catch(Exception e)
			{
				move(0, 0);
			}
		}else
		{
			move(0, 0);
		}
	}
	
	public void move(float Xa, float Ya)
	{
		if(!stun)
		{
			x += Xa * speed;
			y += Ya * speed;
		}
	}
	
	public void Move(float Xa, float Ya)
	{
		x += Xa;
		y += Ya;
	}
	
	public void follow(Entity patient)
	{
		float distX = patient.getX() - x;
		float distY = patient.getY() - y;
		
		if(distY != 0)
		{
			try
			{
				move(0, (distY) / (Math.abs(distY)));
			}
			catch(Exception e)
			{
				move(0, 0);
			}
		}else
		{
			move(0, 0);
		}
		
		if(distX != 0)
		{
			try
			{
				move((distX) / (Math.abs(distX)) , 0);
			}
			catch(Exception e)
			{
				move(0, 0);
			}
		}else
		{
			move(0, 0);
		}
	}
	
	public boolean distanceSense(float distance, Entity patient)
	{
		double space = Math.sqrt(Math.pow(x -
				patient.getX(), 2) + Math.pow(y - patient.getY(), 2));
		
		if(space <= distance)
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	public void wander()
	{
		int movement = gen.nextInt(8) + 1;
		
		switch(movement)
		{
			default:
			{
				move(0, 0);
				break;
			}
			
			case 1:
			{
				move(speed, 0);
				break;
			}
			
			case 2:
			{
				move(0, jump);
				break;
			}
			
			case 3:
			{
				move(-speed, 0);
				break;
			}
			
			
			case 4:
			{
				move(speed, jump);
				break;
			}
			
			case 5:
			{
				move(-speed, jump);
				break;
			}
		}
	}
}
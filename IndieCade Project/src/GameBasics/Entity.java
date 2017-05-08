package GameBasics;

import java.io.Serializable;
import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import BoneStructure.BoneStructure;
import Geo.Quad;
import Geo.QuadR;
import Main.Config;
import Map.Map;
import Player.Player;
import Render.AnimationSet;
import Render.BasicImage;
import Tiles.Tile;

public class Entity implements Serializable
{
	//An enemy of the player that interacts with the player in a hostile way
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1335939607570570293L;
	
	protected Player player;
	protected boolean paused;
	public boolean dead;
	public boolean passive;
	public boolean permanent;
	
	protected String name;
	
	// Movement
	protected float x;
	protected float y;
	protected float Vx;
	protected float Vy;
	protected float Ax;
	protected float Ay;
	protected float acceleration;
	protected boolean moving;
	
	//Combat statistics
	protected float maxHealth;
	protected float health;
	protected 	float damage;
	protected float speed;
	protected boolean stun;
	
	//Combat values
	protected float Stun;
	protected long atkSpeed;
	protected long atkTick;
	
	//World
	protected Map map;
	
	//Renders
	protected float width;
	protected float height;
	protected float rot;
	public Quad Hitbox;
	protected Level level;
	
	//animation
	protected AnimationSet sprite;
	
	protected Random gen = new Random();
	protected int id;
	
	public Entity(String name, Player player, float health, float damage)
	{
		this.name = name;
		
		this.player = player;
		
		dead = false;
		passive = false;
		
		paused = false;
		stun = false;
		moving = false;
		
		
		Vx = 0;
		Vy = 0;
		
		Ax = 0F;
		Ay = 0F;
		acceleration = 0.5F;

		rot = 0;
		Hitbox = new Quad(x, y, width, height);
		
		maxHealth = health;
		this.health = maxHealth;
		this.damage = damage;
		
		id = 0;

		permanent = false;
	}
	public void setMap(Map map)
	{
		this.map = map;
		level = map.getLevel();
	}
	
	public void setPosition(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setVelocity(float xa, float ya)
	{
		Vx = xa;
		Vy = ya;
	}
	
	public void setAcceleration(float xa, float ya)
	{
		Ax = xa;
		Ay = ya;
	}
	
	public Entity setAnimationSet(String ref, long delay)
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
	

	public Entity setAtkSpeed(long atkSpeed) 
	{
		this.atkSpeed = atkSpeed;
		atkSpeed = System.currentTimeMillis();
		return this;
	}
	
	public Entity setMove(float speed, float accel)
	{
		this.speed = speed;
		acceleration = accel;
		return this;
	}
	
	public void Physics()
	{	
		//The physics engine that handles gravity and collisions
		
		if(health <= 0)
		{
			die();
		}
		
		Vx += Ax;
		Vy += Ay;
		
		for(int i = -1; i <= 1; i ++)
		{
			if(map.getTile(x + i * (width / 2 + Vx + Ax), y).getCollidable())
			{
				if(Hitbox.checkQuad(map.getTile(x + i * (width / 2 + Vx + Ax), y + height / 4).getHitbox()))
				{
					if(Vx * i >= 0)
					{
						Vx = 0;
					}
				}
			}
			
			if(map.getTile(x, y + i * (height / 2 + Vy + Ay)).getCollidable())
			{
				if(Hitbox.checkQuad(map.getTile(x, y + i * (height / 2 + Vy + Ay)).getHitbox()))
				{
					if(Vy * i >= 0)
					{
						Vy = 0;
					}
				}
			}
		}
		
		
		Move(Vx, Vy);
	}
	
	
	public void update()
	{		
		if(!paused)
		{
			setMap(player.getMap());
			sprite.resetAnimate();
			
			
			if(Math.abs(Vx) < acceleration)
			{
				Vx = 0;
			}
			
			if(Math.abs(Vy) < acceleration)
			{
				Vy = 0;
			}
			
			if(player.getX() > x)
			{
				sprite.setFlip(true);
			}else
			{
				sprite.setFlip(false);
			}
			
			if(System.currentTimeMillis() - atkTick >= atkSpeed)
			{
				if(player.getHitbox().check(Hitbox))
				{
					atk();
					atkTick = System.currentTimeMillis();
				}
			}
		}
		
		if(rot >= 360)
		{
			rot = 0;
		}
		
		if(!moving)
		{
			if(Vx != 0)
			{
				Ax = map.getTile(x, y).getFriction() * Vx / Math.abs(Vx) * -1;
			}
			
			if(Vy != 0)
			{
				Ay = map.getTile(x, y).getFriction() * Vy / Math.abs(Vy) * -1;
			}
		}
		
		if(Math.abs(Vx) < map.getTile(x, y).getFriction())
		{
			Vx = 0;
			Ax = 0;
		}
		
		if(Math.abs(Vy) < map.getTile(x, y).getFriction() )
		{
			Vy = 0;
			Ay = 0;
		}
		
		Hitbox.changeDimensions(x, y, width, height);
	}
	
	public void render(Graphics g, float xOffset, float yOffset) throws SlickException
	{
		sprite.render(x, xOffset, y, yOffset, width, height, rot, g);
	}
	
	protected void atk()
	{
		if(!passive)
		{
			player.damage((int) damage);
			
			if(player.getX() != x)
			{
				player.setVx(damage * (player.getX() - x) / (Math.abs(player.getX() - x)));
			}
			
			if(player.getY() != y)
			{
				player.setVy(damage * (player.getY() - y) / (Math.abs(player.getY() - y)));
			}
			
			atkTick = System.currentTimeMillis();
		}
	}
	
	public void pause()
	{
		paused = true;
	}
	
	public void unpause()
	{
		paused = false;
	}
	
	public int getID()
	{
		return id;
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
	
	public float getDamage()
	{
		return damage;
	}
	
	public float getMaxHealth()
	{
		return maxHealth;
	}
	
	public float getSpeed()
	{
		return speed;
	}
	
	public float getHeight()
	{
		return height;
	}

	public float getAcceleration() 
	{
		return acceleration;
	}

	public float getWidth()
	{
		return width;
	}

	public long getAtkSpeed() 
	{
		return atkSpeed;
	}
	public AnimationSet getSprite()
	{
		return sprite;
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public Map getMap()
	{
		return map;
	}
	
	public void damage(float damage)
	{
		health -= damage;
	}
	
	public void die()
	{
		level.getEntities().remove(this);
		
		player.getKilled().add(this);
		
		dead = true;
	}
	public Quad getHitbox() 
	{
		return Hitbox;
	}
	
	public void follow(Player player)
	{
		float distX = player.getX() - x;
		float distY = player.getY() - y;
		
		if(distY != 0 && distX != 0)
		{
			try
			{
				move((distX) / (Math.abs(distX)) , (distY) / (Math.abs(distY)));
			}
			catch(Exception e)
			{
				Ax = 0;
				Ay = 0;
			}
		}else if(distY != 0)
		{
			try
			{
				move(0, (distY) / (Math.abs(distY)));
			}
			catch(Exception e)
			{
				Ay = 0;
			}
		}else if(distX != 0)
		{
			try
			{
				move((distX) / (Math.abs(distX)) , 0);
			}
			catch(Exception e)
			{
				Ax = 0;
			}
		}else
		{
			Ax = 0;
			Ay = 0;
		}
	}
	
	public void move(float xa, float ya)
	{
		if(!stun)
		{
			if((xa != 0 || ya != 0) && speed > 0)
			{
				moving = true;
			}else
			{
				moving = false;
			}
			
			if(xa * Vx >= 0)
			{
				if(xa != 0 && Math.abs(Vx + Ax) < Math.abs(xa) * speed)
				{
					Ax = xa * acceleration;
				}else
				{
					Ax = 0;
				}
			}else
			{
				Ax = xa * acceleration;
			}
			
			if(ya * Vy >= 0 )
			{
				if(ya != 0 && Math.abs(Vy + Ay) < Math.abs(ya) * speed)
				{
					Ay = ya * acceleration;
				}else
				{
					Ay = 0;
				}
			}else
			{
				Ay = ya * acceleration;
			}
		}
	}
	
	public void Move(float xa, float ya)
	{
		x += xa;
		y += ya;
		
		if((Ay == 0 && Ax == 0) || speed <= 0)
		{
			moving = false;
		}
	}
	
	public void follow(Entity patient)
	{
		float distX = patient.getX() - x;
		float distY = patient.getY() - y;
		
		
		if(distY != 0 && distX != 0)
		{
			try
			{
				move((distX) / (Math.abs(distX)) , (distY) / (Math.abs(distY)));
			}
			catch(Exception e)
			{
				move(0, 0);
			}
		}else if(distY != 0)
		{
			try
			{
				move(0, (distY) / (Math.abs(distY)));
			}
			catch(Exception e)
			{
				move(0, 0);
			}
		}else if(distX != 0)
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
	
	public boolean distanceSense(float distance, Player player)
	{
		double space = Math.sqrt(Math.pow(x - 
				player.getX(), 2) + Math.pow(y - player.getY(), 2));
		
		if(space <= distance)
		{
			return true;
		}
		
		else
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
				move(1, 0);
				break;
			}
			
			case 2:
			{
				move(0, 1);
				break;
			}
			
			case 3:
			{
				move(-1, 0);
				break;
			}
			
			
			case 4:
			{
				move(1, 1);
				break;
			}
			
			case 5:
			{
				move(-1, 1);
				break;
			}
			
			case 6:
			{
				move(-1, -1);
				break;
			}
			
			case 7:
			{
				move(0, -1);
				break;
			}
			
			case 8:
			{
				move(1, -1);
				break;
			}
		}
	}
	
	public void reset() 
	{
		health = maxHealth;
		stun = false;
	}
	
	public Entity clone()
	{
		return new Entity(name, player, health, damage).setMove(speed, acceleration).
				setDimensions(width, height).setAnimationSet(sprite.getFolder(), sprite.getDelay()).
				setAtkSpeed(atkSpeed);
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}

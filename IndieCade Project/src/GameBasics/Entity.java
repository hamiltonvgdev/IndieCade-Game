package GameBasics;

import java.io.Serializable;
import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
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
	
	Player player;
	boolean paused;
	
	//Horizontal movement
	protected float x;
	protected float Vx;
	protected float Vy;
	protected float Ax;
	protected float Ay;
	float acceleration;
	
	//Vertical movement
	protected float y;
	float jumpV;
	int jump;
	int maxjump;
	long jumpTick;
	boolean jumping;
	
	//Combat statistics
	float maxHealth;
	float health;
	float damage;
	float speed;
	boolean stun;
	
	//Combat values
	float Stun;
	float Speed;
	protected long atkSpeed;
	long atkTick;
	
	//World
	protected Map map;
	
	//Renders
	protected float width;
	protected float height;
	protected float rot;
	public Quad Hitbox;
	Level level;
	
	//animation
	protected AnimationSet sprite;
	
	Random gen = new Random();
	protected int id;
	
	Geo.Hitbox hit;
	
	public Entity(Player player, float health, float damage, float speed)
	{
		this.player = player;
		
		paused = false;
		stun = false;
		
		jumpV = -15F;
		jump = 0;
		maxjump = 2;
		jumpTick = System.currentTimeMillis();
		jumping = false;
		
		Vx = 0;
		Vy = 0;
		
		Ax = 0F;
		Ay = 1.5F;
		acceleration = 0.5F;
		
		Hitbox = new Quad(x, y, width, height);
		rot = 0;
		
		maxHealth = health;
		this.health = maxHealth;
		this.damage = damage;
		this.speed = speed;
		
		id = 0;

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
	
	public Entity setAnimationSet(String ref, long delay)
	{
		sprite = new AnimationSet(ref, delay);
		hit = new Geo.Hitbox(sprite.getCurrentFrame());
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
	
	public void Physics()
	{
		Vx += Ax;
		Vy += Ay;
		
		for(int i = -1; i <= 1; i ++)
		{
			if(map.getTile(x + i * (width / 2+ Vx + Ax), y + height / 4).getCollidable())
			{
				if(map.getTile(x + i * (width / 2 + Vx + Ax), y + height / 4).getHitbox().checkQuad(Hitbox))
				{
					if(Vx * i >= 0)
					{
						Vx = 0;
					}
				}
			}
			
			if(map.getTile(x, y + i * (height / 2 + Vy + Ay)).getCollidable())
			{
				if(map.getTile(x, y + i * (height / 2 + Vy + Ay)).getHitbox().checkQuad(Hitbox))
				{
					if(Vy * i >= 0)
					{
						Vy = 0;
					}
				}
			}
		}
		
		move(Vx, 0);
		move(0, Vy);
	}
	
	
	public void update()
	{		
		if(!paused)
		{
			setMap(player.getMap());
			sprite.resetAnimate();
			
			if(player.getInput().isKeyPressed(Input.KEY_E))
			{
				jump();
			}
			
			if(Math.abs(Vx) < map.getTile(x, y + 50).getFriction())
			{
				Vx = 0;
			}
			
			if(health <= 0)
			{
				die();
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
				for(Quad hitbox: player.getHitboxes())
				{
					if(hitbox.checkQuad(Hitbox))
					{
						atk();
						atkTick = System.currentTimeMillis();
						break;
					}
				}
			}
		}
		
	/*	rot += 0.1;
		
		if(rot >= 360)
		{
			rot = 0;
		}*/
		
		Hitbox.changeDimensions(x , y , width, height);
		hit.update(sprite.getCurrentFrame());
	}
	
	public void render(Graphics g, float xOffset, float yOffset) throws SlickException
	{
		//sprite.render(x + xOffset, y + yOffset, width, height, rot, g);
		sprite.render(x , y, width, height, rot, g);
		Hitbox.render(g);
		hit.render(g);
	}
	
	protected void atk()
	{
		player.damage((int) damage);
		player.Jump();
		player.setVx(10 * (player.getX() - x) / (Math.abs(player.getX() - x)));
		atkTick = System.currentTimeMillis();
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
		for(int i = 0; i < level.getEntities().size(); i ++)
		{
			if(level.getEntities().get(i) == this)
			{
				level.getEntities().remove(i);
				break;
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
		
		if(distX != 0)
		{
			try
			{
				Vx = (distX) / (Math.abs(distX));
			}
			catch(Exception e)
			{
				Vx = 0;
			}
		}else
		{
			Vx = 0;
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
	
	public void reset() 
	{
		health = maxHealth;
		jump = 0;
		stun = false;
	}
	
}

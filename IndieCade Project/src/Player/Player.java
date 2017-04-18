package Player;

import java.io.Serializable;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.sun.xml.internal.ws.client.sei.ResponseBuilder.Body;

import BoneStructure.BoneStructure;
import Form.Form;
import Form.Triangle;
import GameBasics.Entity;
import Geo.Hitbox;
import Geo.M;
import Geo.Quad;
import Geo.QuadR;
import Main.Config;
import Main.Game;
import Main.MainMenu;
import Map.Map;
import Menus.CodexMenu;
import Render.AnimationSet;
import Render.BasicImage;
import Tiles.Tile;

public class Player implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6654083572168084434L;
	transient Game game;
	boolean paused;
	
	//Vertical Movement
	float y;
	float JumpV;
	int Jumps;
	int MaxJumps;
	long JumpTick;
	public boolean Jumping;
	boolean collidable;
	
	//Horizontal Movement
	float x;
	float Vx;
	float Vy;
	float Ax;
	float Ay;
	float acceleration;
	
	//Input
	transient Input input;
	public boolean rightMove;
	public boolean leftMove;
	public boolean upMove;
	public boolean downMove;
	
	//World
	Map map;
	
	//Renders
	Quad Screen;
	float xOffset;
	float yOffset;
	float rot;
	
	//Gear
	ArrayList<Form> Inventory;
	Form Equiped;
	
	//Combat Statistics
	float maxHealth;
	float health;
	float armor;
	float damage;
	float speed;
	float baseSpeed;
	ArrayList<Entity> Killed;
	
	//Combat Values
	float Stun;
	float Speed;
	public boolean damaged;
	
	Hitbox hitbox;
	
	
	public Player(Game game, float x, float y)
	{
		this.game = game;
		
		Inventory = new ArrayList<Form>();
		Killed = new ArrayList<Entity>();
		Equiped = new Triangle(this);
		
		paused = false;
		
		this.x = x;
		this.y = y;
		JumpV = -15F;
		Jumps = 0;
		setJumps(1);
		JumpTick = System.currentTimeMillis();
		Jumping = false;
		collidable = true;
		
		Vx = 0;
		Vy = 0;
		
		
		input = game.getGc().getInput();
		
		Ax = 0;
		Ay = 0;
		acceleration = 2F;
		
		rot = 0;
		
		Screen = new Quad(x, y, Config.WIDTH, Config.HEIGHT);
		
		maxHealth = 100;
		health = maxHealth;
		armor = 0;
		damage = 5;
		this.baseSpeed = 10;
		speed = 1;
		Speed = baseSpeed * speed;
		Stun = 0;
		
		
		hitbox = new Hitbox(Equiped.getCurrentSprite().getCurrentFrame());
		
		
		xOffset = 0;
		yOffset = 0;
		
		damaged = false;
	}
	
	public void setMap(Map map)
	{
		this.map = map;
	}
	
	public void setJumps(int jumps)
	{
		MaxJumps = jumps;
	}
	
	public void clearJump()
	{
		Jumps = 0;
	}
	
	public void setCollidable(boolean collide)
	{
		collidable = collide;
	}
	
	public void setJumpV(float jumpV)
	{
		JumpV = jumpV;
	}
	
	public void setVx(float Vx)
	{
		this.Vx = Vx;
	}
	
	public void setVy(float Vy)
	{
		this.Vy = Vy; 
	}
	
	public void Physics()
	{
		Vx += Ax;
		Vy += Ay;
		
		boolean CollisionX = false;
		boolean CollisionY = false;
		
		
		for(int i = -1; i <= 1; i ++)
		{
			for(int j = -1; j <= 1; j ++)
			{
				if(map.getTile(x + Vx + Ax, y).getCollidable() 
						&& hitbox.check(map.getTile(x, y).getHitbox()))
				{
					CollisionX = true;
					map.getTile(x + Vx + Ax, y).nextTo = true;
				}else
				{
					map.getTile(x + Vx + Ax, y).nextTo = false;
				}
				
				if(map.getTile(x , y + Vy + Ay).getCollidable() 
						&& hitbox.check(map.getTile(x, y).getHitbox()))
				{
					CollisionY = true;
					map.getTile(x, y + Vy + Ay).on = true;
				}else
				{
					map.getTile(x, y).on = false;
				}
			}
		}
		
		
		if(CollisionX)
		{
			setVx(0);
		}
		if(CollisionY)
		{
			setVy(0);
		}
	}
	
	public void update()
	{	
		if(!paused)
		{
			//hitbox.update(sprites.get(spriteindex).getCurrentFrame());
			hitbox.update(Equiped.getCurrentSprite().getCurrentFrame());
			
			if(health <= 0)
			{
				die();
			}
			
			Physics();
			
			Speed = baseSpeed * speed;
			
			if(Stun == 0)
			{
				if(input.isKeyDown(input.KEY_D) && !input.isKeyDown(input.KEY_A))
				{
					rightMove = true;
					leftMove = false;
				}else
				{
					rightMove = false;
				}
				
				if(input.isKeyDown(input.KEY_A) && !input.isKeyDown(input.KEY_D))
				{
					leftMove = true;
					rightMove = false;
				}else
				{
					leftMove = false;
				}
				
				if(input.isKeyDown(input.KEY_W) && !input.isKeyDown(input.KEY_S))
				{
					upMove = true;
					downMove = false;
				}else
				{
					upMove = false;
				}
				
				if(input.isKeyDown(input.KEY_S) && !input.isKeyDown(input.KEY_W))
				{
					downMove = true;
					upMove = false;
				}else
				{
					downMove = false;
				}
				
			}else if(Stun < 0)
			{
				Stun = 0;
			}else
			{
				Stun -= 1000 / Config.Ticks;
			}
			
			if(leftMove && !rightMove)
			{
				if(Math.abs(Vx) <= Speed - acceleration)
				{
					Ax = -acceleration;
				}else
				{
					Ax = 0;
				}
				if(Math.abs(Vx) >= Speed - acceleration)
				{
					Vx = -Speed;
				}
			}
			
			if(rightMove && !leftMove)
			{
				if(Math.abs(Vx) <= Speed - acceleration)
				{
					Ax = acceleration;
				}else
				{
					Ax = 0;
				}
				
				if(Math.abs(Vx) >= Speed - acceleration)
				{
					Vx = Speed;
				}
			}
			
			if(!rightMove && !leftMove)
			{
				if(Vx < 0)
				{
					Ax = map.getTile(x, y).getFriction();
				}else if(Vx == 0)
				{
					Ax = 0;
				}
				
				if(Vx > 0)
				{
					Ax = -map.getTile(x, y).getFriction();
				}else if(Vx == 0)
				{
					Ax = 0;
				}
			}
			
			if(!downMove && upMove)
			{
				if(Math.abs(Vy) <= Speed - acceleration)
				{
					Ay = -acceleration;
				}else
				{
					Ay = 0;
				}
				if(Math.abs(Vy) >= Speed - acceleration)
				{
					Vy = -Speed;
				}
			}
			
			if(!upMove && downMove)
			{
				if(Math.abs(Vy) <= Speed - acceleration)
				{
					Ay = acceleration;
				}else
				{
					Ay = 0;
				}
				
				if(Math.abs(Vy) >= Speed - acceleration)
				{
					Vy = Speed;
				}
			}
			
			if(!upMove && !downMove)
			{
				if(Vy < 0)
				{
					Ay = map.getTile(x, y).getFriction();
				}else if(Vy == 0)
				{
					Ay = 0;
				}
				
				if(Vy > 0)
				{
					Ay = -map.getTile(x, y).getFriction();
				}else if(Vy == 0)
				{
					Ay = 0;
				}
			}

			if(Math.abs(Vx) < map.getTile(x, y).getFriction())
			{
				setVx(0);
			}
			
			if(Math.abs(Vy) < map.getTile(x, y).getFriction())
			{
				setVy(0);
			}
			
			if(input.isKeyDown(input.KEY_SPACE) 
					&& System.currentTimeMillis() - JumpTick > 500
					&& Jumps != 0
					&& Stun == 0)
			{
				Jump();
			}
			
			move(-Vx, -Vy);
			
			Screen.changeDimensions(x, y, Config.WIDTH, Config.HEIGHT);
			
			Equiped.update();
			
			for(int i = 1; i <= 6; i ++)
			{
				if(input.isKeyDown(M.getNumKey(i)))
				{
					equip(i);
				}
			}
			
			damaged = false;
		}
	}	
	
	public void move(float xa, float ya) 
	{
		x -= xa;
		y -= ya;
		map.shift(xa, 0);
		map.shift(0, ya);
		
		xOffset += xa;
		yOffset += ya;
	}
	
	public void Move(float xa, float ya)
	{
		x += xa;
		y += ya;
	}
	
	public void setOffset(float xa, float ya)
	{
		xOffset = xa;
		yOffset = ya;
	}
	
	public void setSpeed(float speed)
	{
		this.speed = speed;
	}
	
	public void render(Graphics g) throws SlickException
	{
		Equiped.render(x, xOffset, y, yOffset, g);
		//hitbox.render(g);
	}
	
	public void die()
	{
		map.reset();
		health = maxHealth;
		x += xOffset;
		y += yOffset;
		xOffset = 0;
		yOffset = 0;
	}
	
	public void Jump()
	{
		setVy(JumpV);
		Jumps --;
		Jumping = true;
		
		JumpTick = System.currentTimeMillis();
	}
	
	public void giveForm(Form item)
	{
		Inventory.add(item);
	}
	
	public void equip(int index)
	{
		if(index + 1 < Inventory.size())
		{
			Equiped = Inventory.get(index);
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
	
	public void damage(int dmg)
	{
		health -= dmg / (1 + armor);
		damaged = true;
	}
	
	public void Damage(int dmg)
	{
		health -= dmg;
		
		damaged = true;
	}
	
	public void stun(float Stun)
	{
		this.Stun += Stun;
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
	
	public float getVx()
	{
		return Vx;
	}
	
	public float getVy()
	{
		return Vy;
	}
	
	public float getAx()
	{
		return Ax;
	}
	
	public float getAy()
	{
		return Ay;
	}
	
	public float getSpeed()
	{
		return Speed;
	}
	
	public float getspeed()
	{
		return speed;
	}
	
	public float getHealth() 
	{
		return health;
	}

	public float getDamage() 
	{
		return damage;
	}
	
	public Map getMap()
	{
		return map;
	}
	
	public Input getInput()
	{
		return input;
	}
	
	public ArrayList<Form> getInventory()
	{
		return Inventory;
	}
	
	public Form getForm()
	{
		return Equiped;
	}
	
	public ArrayList<Entity> getKilled()
	{
		return Killed;
	}
	
	public Hitbox getHitbox()
	{
		return hitbox;
	}
}

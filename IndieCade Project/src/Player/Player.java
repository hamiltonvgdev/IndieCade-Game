package Player;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import GameBasics.Item;
import Geo.Quad;
import Main.Config;
import Map.Map;
import Render.AnimationSet;
import Tiles.Tile;

public class Player 
{
	boolean paused;
	
	//Vertical Movement
	float y;
	float JumpV;
	int Jumps;
	int MaxJumps;
	long JumpTick;
	boolean Jumping;
	
	//Horizontal Movement
	float x;
	float Vx;
	float Vy;
	float Ax;
	float Ay;
	float acceleration;
	
	//Input
	Input input;
	boolean rightMove;
	boolean leftMove;
	
	//World
	Map map;
	
	//Renders
	float width;
	float height;
	Quad Hitbox;
	Quad Screen;
	
	//Gear
	ArrayList<Item> Inventory;
	AnimationSet Head;
	public float headY;
	AnimationSet Torso;
	public float torsoY;
	AnimationSet Arms;
	public float armsY;
	public float armRot;
	AnimationSet Legs;
	public float legsY;
	
	//Combat
	float maxHealth;
	float health;
	float armor;
	float damage;
	float tenacity;
	float speed;
	
	public Player(float x, float y)
	{
		Inventory = new ArrayList<Item>();
		
		paused = false;
		
		this.x = x;
		this.y = y;
		JumpV = -10F;
		Jumps = 0;
		MaxJumps = 2;
		JumpTick = System.currentTimeMillis();
		Jumping = false;
		
		Vx = 0;
		Vy = 0;
		
		input = new Input(1);
		
		Ax = 0;
		Ay = 0.5F;
		acceleration = 0.5F;
		
		width = 64;
		height = 64;
		
		Hitbox = new Quad(x, y, width, height);
		Screen = new Quad(x, y, Config.WIDTH, Config.HEIGHT);
		
		maxHealth = 100;
		health = maxHealth;
		armor = 0;
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
			}
			
			if(T.getCollidable() && T.getHitbox().checkQuad(new Quad(x, y + Vy + Ay, width, height)))
			{
				CollisionY = true;
			}
		}
		
		if(CollisionX)
		{
			Vx = 0;
		}
		
		if(CollisionY)
		{
			Jumping = false;
			if(Jumps < MaxJumps && Vy >= 0)
			{
				Jumps ++;
			}
			Vy = 0;
		}else
		{
			Vy += Ay;
			if(!Jumping)
			{
				Jumps = 0;
			}
		}
	}
	
	public void update()
	{
		if(!paused)
		{
			Physics();
			
			if(input.isKeyDown(input.KEY_D) && !input.isKeyDown(input.KEY_A))
			{
				rightMove = true;
			}else
			{
				rightMove = false;
			}
			
			if(input.isKeyDown(input.KEY_A) && !input.isKeyDown(input.KEY_D))
			{
				leftMove = true;
			}else
			{
				leftMove = false;
			}
			
			if(leftMove && !rightMove)
			{
				if(Math.abs(Vx) <= speed)
				{
					Ax = -acceleration;
				}else
				{
					Ax = 0;
				}
			}else if(rightMove && !leftMove)
			{
				if(Math.abs(Vx) <= speed)
				{
					Ax = acceleration;
				}else
				{
					Ax = 0;
				}
			}else
			{
				if(Vx < 0)
				{
					Ax = map.getCurrentTile(x, y + 50).getFriction();
				}else if(Vx == 0)
				{
					Ax = 0;
				}
				
				if(Vx > 0)
				{
					Ax = -map.getCurrentTile(x, y + 50).getFriction();
				}else if(Vx == 0)
				{
					Ax = 0;
				}
			}
			
			if(Math.abs(Vx) < map.getCurrentTile(x, y + 50).getFriction() )
			{
				Vx = 0;
			}
			
			if(input.isKeyDown(input.KEY_W) 
					&& System.currentTimeMillis() - JumpTick > 300
					&& Jumps != 0)
			{
				Jump();
			}
			
			
			map.shift(-Vx, 0);
			map.shift(0, -Vy);
			
			Hitbox.changeDimensions(x, y, width, height);
			Screen.changeDimensions(x, y, Config.WIDTH, Config.HEIGHT);
		}
	}	
	
	public void render(Graphics g) throws SlickException
	{
		Hitbox.render(g);
		Screen.render(g);
	/*
		Head.render(x, headY, width, height, 0, g);
		Torso.render(x, torsoY, width, height, 0, g);
		Arms.render(x, armsY, width, height, 0, g);
		Legs.render(x, legsY, width, height, 0, g);
	*/
	}
	
	public void Jump()
	{
		Vy = JumpV;
		Jumps --;
		Jumping = true;
		
		JumpTick = System.currentTimeMillis();
	}
	
	public void giveItem(Item item)
	{
		Inventory.add(item);
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
	
	public ArrayList<Item> getInventory()
	{
		return Inventory;
	}
}

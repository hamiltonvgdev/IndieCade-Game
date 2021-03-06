package Player;

import java.io.Serializable;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.sun.xml.internal.ws.client.sei.ResponseBuilder.Body;

import BoneStructure.BoneStructure;
import Enemy.ReflectEnemy;
import Enemy.RushEnemy;
import Enemy.ShieldEnemy;
import Enemy.ShootEnemy;
import Form.Form;
import Form.Hexagon;
import Form.Pentagon;
import Form.Square;
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
import Projectiles.BasicProjectile;
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
	
	Quad hitbox;
	
	
	public Player(Game game, float x, float y)
	{
		this.game = game;
		
		Inventory = new ArrayList<Form>();
		Killed = new ArrayList<Entity>();
		
		//Equiped = new Triangle(this);
		//Equiped = new Square(this);
		Equiped = new Form(this);
		
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
		damage = 10;
		this.baseSpeed = 10;
		speed = 1;
		Speed = baseSpeed * speed;
		Stun = 0;
		
		
		hitbox = new Quad(x, y, Equiped.width, Equiped.height);
		
		
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
				if(map.getMap().getTile(x + Vx + Ax, y).getCollidable() 
						&& hitbox.checkQuad(map.getMap().getTile(x, y).getHitbox()))
				{
					CollisionX = true;
					map.getMap().getTile(x + Vx + Ax, y).nextTo = true;
				}else
				{
					map.getMap().getTile(x + Vx + Ax, y).nextTo = false;
				}
				
				if(map.getMap().getTile(x , y + Vy + Ay).getCollidable() 
						&& hitbox.checkQuad(map.getMap().getTile(x, y).getHitbox()))
				{
					CollisionY = true;
					map.getMap().getTile(x, y + Vy + Ay).on = true;
				}else
				{
					map.getMap().getTile(x, y).on = false;
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
			hitbox.changeDimensions(x, y, Equiped.width, Equiped.height);;
			
			if(health <= 0)
			{
				die();
				map.getMap().reset();
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
					Ax = map.getMap().getTile(x, y).getFriction();
				}else if(Vx == 0)
				{
					Ax = 0;
				}
				
				if(Vx > 0)
				{
					Ax = -map.getMap().getTile(x, y).getFriction();
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
					Ay = map.getMap().getTile(x, y).getFriction();
				}else if(Vy == 0)
				{
					Ay = 0;
				}
				
				if(Vy > 0)
				{
					Ay = -map.getMap().getTile(x, y).getFriction();
				}else if(Vy == 0)
				{
					Ay = 0;
				}
			}

			if(Math.abs(Vx) < map.getMap().getTile(x, y).getFriction())
			{
				setVx(0);
			}
			
			if(Math.abs(Vy) < map.getMap().getTile(x, y).getFriction())
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
			
			for(int i = 0; i < Inventory.size(); i ++)
			{
				Inventory.get(i).idle();
			}
			
			
			for(int i = 1; i <= 6; i ++)
			{
				if(input.isKeyDown(M.getNumKey(i)))
				{
					equip(i);
				}
			}
			
			damaged = false;
			
			if(input.isKeyPressed(input.KEY_0))
			{
				godpowerz101();
			}
		}
	}	
	
	public void move(float xa, float ya) 
	{
		if(!map.getMap().getTile(x - xa, y - ya).getCollidable())
		{
			x -= xa;
			y -= ya;
			map.getMap().shift(xa, 0);
			map.getMap().shift(0, ya);
			
			xOffset += xa;
			yOffset += ya;
		}
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
	
	public void setPosition(float xa, float ya)
	{
		move(xa - x, ya - y);
	}
	
	public void setSpeed(float speed)
	{
		this.speed = speed;
	}
	
	public void setArmor(float armor)
	{
		this.armor = armor;
	}
	
	public void setDamage(float damage)
	{
		this.damage = damage;
	}
	
	public void render(Graphics g) throws SlickException
	{
		for(int i = 0; i < Inventory.size(); i ++)
		{
			Inventory.get(i).brender(xOffset, yOffset, g);
			
		}
		
		Equiped.render(x, xOffset, y, yOffset, g);
		
		for(int i = 0; i < Inventory.size(); i ++)
		{
			Inventory.get(i).arender(xOffset, yOffset, g);
			
		}
		
		hitbox.render(g);
	}
	
	public void die()
	{
		map.getMap().reset();
		health = maxHealth;
		x += xOffset;
		y += yOffset;
		xOffset = 0;
		yOffset = 0;
		Vx = 0;
		Vy = 0;
		Ax = 0;
		Ay = 0;
		
		setMap(map);
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
		if(index <= Inventory.size())
		{
			Equiped = Inventory.get(index - 1);
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
	
	public void damage(float dmg)
	{
		health -= dmg * (1 - armor / 100.0);
		
		if(dmg > 0)
		{
			damaged = true;
		}
	}
	
	public void Damage(float dmg)
	{
		health -= dmg;
		
		if(dmg > 0)
		{
			damaged = true;
		}
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

	public float getMaxHealth()
	{
		return maxHealth;
	}
	
	public float getDamage() 
	{
		return damage;
	}
	
	public float getArmor()
	{
		return armor;
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
	
	public Quad getHitbox()
	{
		return hitbox;
	}
	
	public Game getGame()
	{
		return game;
	}
	
	public void godpowerz101()
	{
		/*RushEnemy derp = (RushEnemy) new RushEnemy("derp", this, 100, 10).
				setRange(500).setAtkSpeed(100).setDimensions(64, 64).setMove(10, 2).
				setAnimationSet("res/Forms/Point/Idle", 100);
		derp.setPosition(Mouse.getX() - xOffset, M.toRightHandY(Mouse.getY()) + yOffset);*/
		
	/*	ShootEnemy derp = (ShootEnemy) new ShootEnemy("derp", this, 100, 10).
				setProjectile(new BasicProjectile(this, 0).setDimensions(32, 32, 0).
						setSprite("res/Forms/Point/Idle", 100), 10).
				setRange(500).setAtkSpeed(100).setDimensions(64, 64).setMove(10, 2).
				setAnimationSet("res/Forms/Point/Idle", 100).setMove(10, 2).setAtkSpeed(1000);
		derp.setPosition(Mouse.getX() - xOffset, M.toRightHandY(Mouse.getY()) + yOffset);*/
		
	/*	ShieldEnemy derp = (ShieldEnemy) new ShieldEnemy("derp", this, 100, 10).setShieldDimensions(32, 128).
				setShieldSprite("res/Forms/Point/Idle", 100).setShieldStats(100, 50, 2).
				setRange(500).setAtkSpeed(100).setDimensions(64, 64).setMove(10, 2).
				setAnimationSet("res/Forms/Point/Idle", 100);
		derp.setPosition(Mouse.getX() - xOffset, M.toRightHandY(Mouse.getY()) + yOffset);*/
		
	/*	ReflectEnemy derp = (ReflectEnemy) new ReflectEnemy("derp", this, 100, 10).setShieldDimensions(32, 128).
				setShieldSprite("res/Forms/Point/Idle", 100).setShieldStats(100, 50, 2).
				setRange(500).setAtkSpeed(100).setDimensions(64, 64).setMove(10, 2).
				setAnimationSet("res/Forms/Point/Idle", 100);
		derp.setPosition(Mouse.getX() - xOffset, M.toRightHandY(Mouse.getY()) + yOffset);*/
		
		//map.getMap().getLevel().addEntity(derp);
		
		giveForm(new Triangle(this));
	}
}
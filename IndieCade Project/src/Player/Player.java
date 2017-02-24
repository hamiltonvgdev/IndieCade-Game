package Player;

import java.io.Serializable;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.sun.xml.internal.ws.client.sei.ResponseBuilder.Body;

import BoneStructure.BoneStructure;
import GameBasics.Entity;
import GameBasics.Item;
import Geo.Quad;
import Main.Config;
import Main.Game;
import Main.MainMenu;
import Map.Map;
import Menus.CodexItemEntry;
import Menus.CodexMenu;
import Render.AnimationSet;
import Render.BasicImage;
import Stance.Action;
import Stance.Stance;
import Tiles.Tile;
import Weapons.MeleeWeapon;
import Weapons.RangedWeapon;
import Weapons.Weapon;

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
	
	//World
	Map map;
	
	//Renders
	float width;
	float height;
	Quad Screen;
	
	//Gear
	ArrayList<Item> Inventory;
	Item Helmet;
	Item Chestplate;
	Item Pants;
	Item Gauntlet;
	Weapon Wpn;
	
	//Combat Statistics
	BoneStructure Body;
	float maxHealth;
	float health;
	float armor;
	float damage;
	float tenacity;
	float speed;
	float baseSpeed;
	
	//Combat Values
	float Stun;
	float Speed;
	
	Stance walk;
	Stance startWalk;
	Stance stopWalk;
	
	ArrayList<BasicImage> Model;
	ArrayList<Quad> Hitboxes;
	
	public Player(Game game, float x, float y)
	{
		this.game = game;
		
		Inventory = new ArrayList<Item>();
		
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
		Ay = 1.5F;
		acceleration = 5F;
		
		Body = new BoneStructure(this, 0.9F);
		
		width = 110 * Body.getSize();
		height = 150 * Body.getSize();
		
		Screen = new Quad(x, y, Config.WIDTH, Config.HEIGHT);
		
		maxHealth = 100;
		health = maxHealth;
		armor = 0;
		damage = 5;
		tenacity = 0;
		this.baseSpeed = 15;
		speed = 1;
		Speed = baseSpeed * speed;
		Stun = 0;
		
		walk = new Stance("Derp", Body, 1);
		walk.addAction(new Action("Pelvic", 0, 10, 100), 0);
		
		Wpn = ((RangedWeapon) new RangedWeapon("Bow", this).
				setAtkStats(1000, 500, 50).setChance(50, 2, 0, 0).
				setDimensions(16 * 2.5F, 16 * 2.5F).setSprite("res/Gear/Weapons/BasicBow/Bow", 100)).
				setProjectile("res/Gear/Weapons/BasicBow/Arrow", 100, 10 * 5, 10 * 5, -45);
		
		Model = new ArrayList<BasicImage>();
		Model.add(new BasicImage("res/Player/Head/Head.png"));
		Model.add(new BasicImage("res/Player/Neck/Neck.png"));
		Model.add(new BasicImage("res/Player/Torso/Shoulders.png"));
		Model.add(new BasicImage("res/Player/Torso/Shoulders.png"));
		Model.add(new BasicImage("res/Player/Torso/Lower Torso.png"));
		Model.add(new BasicImage("res/Player/Torso/Upper Torso.png"));
		Model.add(new BasicImage("res/Player/Leg/Upper Left Leg.png"));
		Model.add(new BasicImage("res/Player/Leg/Upper Right Leg.png"));
		Model.add(new BasicImage("res/Player/Arm/Upper Arm.png"));
		Model.add(new BasicImage("res/Player/Arm/Lower Arm.png"));
		Model.add(new BasicImage("res/Player/Arm/Hand.png"));
		Model.add(new BasicImage("res/Player/Arm/Upper Arm.png"));
		Model.add(new BasicImage("res/Player/Arm/Lower Arm.png"));
		Model.add(new BasicImage("res/Player/Arm/Hand.png"));
		Model.add(new BasicImage("res/Player/Leg/Lower Left Leg.png"));
		Model.add(new BasicImage("res/Player/Leg/Foot.png"));
		Model.add(new BasicImage("res/Player/Leg/Lower Right Leg.png"));
		Model.add(new BasicImage("res/Player/Leg/Foot.png"));
		
		Hitboxes = new ArrayList<Quad>();
		
		for(int i = 0; i < Model.size(); i ++)
		{
			Hitboxes.add(new Quad(0, 0, 
					Model.get(i).getImage().getWidth(), Model.get(i).getImage().getHeight()));
		}
		
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
		
		boolean CollisionX = false;
		boolean CollisionY = false;
		
		
		for(int k = Hitboxes.size() - 3; k < Hitboxes.size(); k += 2)
		{
			for(int i = -1; i <= 1; i ++)
			{
				for(int j = -1; j <= 1; j ++)
				{
					if(map.getTile(Hitboxes.get(k).x + i * (Hitboxes.get(k).width + 5), Hitboxes.get(k).y + j * (Hitboxes.get(k).height + 5)).getCollidable() 
							&& map.getTile(Hitboxes.get(k).x + i * (Hitboxes.get(k).width + 5), Hitboxes.get(k).y + j * (Hitboxes.get(k).height + 5)).getHitbox().
							checkQuad(new Quad(Hitboxes.get(k).x + Hitboxes.get(k).width / 2 + Vx + Ax, Hitboxes.get(k).y + Hitboxes.get(k).height / 2, Hitboxes.get(k).width, Hitboxes.get(k).height)))
					{
						CollisionX = true;
						map.getTile(Hitboxes.get(k).x + i * (Hitboxes.get(k).width + 5), Hitboxes.get(k).y + j * (Hitboxes.get(k).height + 5)).nextTo = true;
					}else
					{
						map.getTile(Hitboxes.get(k).x + i * (Hitboxes.get(k).width + 5), Hitboxes.get(k).y + j * (Hitboxes.get(k).height + 5)).nextTo = false;
					}
					
					if(map.getTile(Hitboxes.get(k).x + i * (Hitboxes.get(k).width + 5), Hitboxes.get(k).y + j * (Hitboxes.get(k).height + 5)).getCollidable() 
							&& map.getTile(Hitboxes.get(k).x + i * (Hitboxes.get(k).width + 5), Hitboxes.get(k).y + j * (Hitboxes.get(k).height + 5)).getHitbox().
							checkQuad(new Quad(Hitboxes.get(k).x + Hitboxes.get(k).width / 2, Hitboxes.get(k).y + Hitboxes.get(k).height / 2 + Vy + Ay, Hitboxes.get(k).width, Hitboxes.get(k).height)))
					{
						CollisionY = true;
						map.getTile(Hitboxes.get(k).x + i * (Hitboxes.get(k).width + 5), Hitboxes.get(k).y + j * (Hitboxes.get(k).height + 5)).on = true;
					}else
					{
						map.getTile(Hitboxes.get(k).x + i * (Hitboxes.get(k).width + 5), Hitboxes.get(k).y + j * (Hitboxes.get(k).height + 5)).on = false;
					}
				}
			}
		}
		
		if(CollisionX)
		{
			Vx = 0;
		}
		
		if(CollisionY)
		{
			if(collidable)
			{
				Jumping = false;
				if(Jumps < MaxJumps && Vy >= 0)
				{
					Jumps ++;
				}
				System.out.println("derp");
				setVy(0);
			}
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
					
					if(Body.flip)
					{
						Body.flip();
					}
				}else
				{
					rightMove = false;
				}
				
				if(input.isKeyDown(input.KEY_A) && !input.isKeyDown(input.KEY_D))
				{
					leftMove = true;
					rightMove = false;
					
					if(!Body.flip)
					{
						Body.flip();
					}
				}else
				{
					leftMove = false;
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
				if(Math.abs(Vx) < Speed - acceleration)
				{
					Ax = -acceleration;
				}else
				{
					Ax = 0;
				}
			}
			
			if(rightMove && !leftMove)
			{
				if(Math.abs(Vx) < Speed - acceleration)
				{
					Ax = acceleration;
				}else
				{
					Ax = 0;
				}
			}
			
			if(!rightMove && !leftMove)
			{
				if(Vx < 0)
				{
					Ax = map.getTile(x, y + 50 + 64 * 2 * Body.getSize()).getFriction();
				}else if(Vx == 0)
				{
					Ax = 0;
				}
				
				if(Vx > 0)
				{
					Ax = -map.getTile(x, y + 64 * 2 * Body.getSize()).getFriction();
				}else if(Vx == 0)
				{
					Ax = 0;
				}
			}
			
			if(Math.abs(Vx) < map.getTile(x, y + 50 + 64 * 2 * Body.getSize()).getFriction())
			{
				setVx(0);
			}
			
			if(input.isKeyDown(input.KEY_W) 
					&& System.currentTimeMillis() - JumpTick > 500
					&& Jumps != 0
					&& Stun == 0)
			{
				Jump();
			}
			
			map.shift(-Vx, 0);
			map.shift(0, -Vy);
			
			Screen.changeDimensions(x, y, Config.WIDTH, Config.HEIGHT);
			
			Body.update();

			Wpn.update();
			/*	
			Helmet.update();
			Chestplate.update();
			Pants.update();
			Gauntlet.update();
			
			maxHealth = 1 * (100 + Helmet.getHealth() + Chestplate.getHealth() + Pants.getHealth() + Gauntlet.getHealth()) / 100;
			armor = 1 * (100 + Helmet.getArmor() + Chestplate.getArmor() + Pants.getArmor() + Gauntlet.getArmor()) / 100;
			damage = 1 * (100 + Helmet.getDamage() + Chestplate.getDamage() + Pants.getDamage() + Gauntlet.getDamage()) / 100;
			tenacity = 1 * (100 + Helmet.getTenacity() + Chestplate.getTenacity() + Pants.getTenacity() + Gauntlet.getTenacity()) / 100;
			speed = 1 * (100 + Helmet.getSpeed() + Chestplate.getSpeed() + Pants.getSpeed() + Gauntlet.getSpeed()) / 100;
			*/
			
		}
	}	
	
	public void render(Graphics g) throws SlickException
	{
		Screen.render(g);
		
		for(int i = 0; i < Model.size(); i ++)
		{
			Model.get(i).setFlip(!Body.getBones().get(i).getFlip());
			Model.get(i).render(Body.getBones().get(i).getX(), Body.getBones().get(i).getY(), 
					Model.get(i).getImage().getWidth() / 4 * Body.getSize(), 
					Model.get(i).getImage().getHeight() / 4 * Body.getSize(), 
					Body.getBones().get(i).getRenderRot(), g);
			
			Hitboxes.get(i).changeDimensions(Body.getBones().get(i).getX(), 
					Body.getBones().get(i).getY(), 
					Model.get(i).getImage().getWidth() / 4 * Body.getSize(), 
					Model.get(i).getImage().getHeight() / 4 * Body.getSize());
		}
		
		for(int k = Hitboxes.size() - 3; k < Hitboxes.size(); k += 2)
		{
			Hitboxes.get(k).render(g);
			new Quad(Hitboxes.get(k).x + Hitboxes.get(k).width / 2, Hitboxes.get(k).y +  Hitboxes.get(k).height / 2 + Vy + Ay, 
					Hitboxes.get(k).width, Hitboxes.get(k).height).render(g);
		}
		
		Wpn.render(g);
}
	
	public void die()
	{
		map.reset();
		health = maxHealth;
	}
	
	public void Jump()
	{
		setVy(JumpV);
		Jumps --;
		Jumping = true;
		
		JumpTick = System.currentTimeMillis();
	}
	
	public void giveItem(Item item)
	{
		Inventory.add(item);
		
		((CodexMenu) ((MainMenu) game.getSbg().getState(1)).getMenus().get(2)).
		addItemEntry(new CodexItemEntry(((CodexMenu) ((MainMenu) game.getSbg().getState(1)).getMenus().get(2)), item));;
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
	}
	
	public void Damage(int dmg)
	{
		health -= dmg;
	}
	
	public void DOT(int dmg)
	{
		health = dmg / (1 + tenacity);
	}
	
	public void stun(float Stun)
	{
		this.Stun += Stun / (1 + tenacity);
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
	
	public Map getMap()
	{
		return map;
	}
	
	public Input getInput()
	{
		return input;
	}
	
	public BoneStructure getBody()
	{
		return Body;
	}
	
	public ArrayList<Item> getInventory()
	{
		return Inventory;
	}
	
	public Item getHelmet()
	{
		return Helmet;
	}
	
	public Item getChestplate()
	{
		return Chestplate;
	}
	
	public Item getPants()
	{
		return Pants;
	}
	
	public Item getGaunlets()
	{
		return Gauntlet;
	}
	
	public Weapon getWeapon()
	{
		return Wpn;
	}
	
	public ArrayList<Quad> getHitboxes()
	{
		return Hitboxes;
	}
	
	public Quad getHitbox(String name)
	{
		if(name.equals("Head"))
		{
			return Hitboxes.get(0);
		}else if(name.equals("Neck"))
		{
			return Hitboxes.get(1);
		}else if(name.equals("Shoulder 1"))
		{
			return Hitboxes.get(2);
		}else if(name.equals("Shoulder 2"))
		{
			return Hitboxes.get(3);
		}else if(name.equals("Lower Spine"))
		{
			return Hitboxes.get(4);
		}else if(name.equals("Upper Spine"))
		{
			return Hitboxes.get(5);
		}else if(name.equals("Upper Leg 1"))
		{
			return Hitboxes.get(6);
		}else if(name.equals("Upper Leg 2"))
		{
			return Hitboxes.get(7);
		}else if(name.equals("Upper Arm 1"))
		{
			return Hitboxes.get(8);
		}else if(name.equals("Lower Arm 1"))
		{
			return Hitboxes.get(9);
		}else if(name.equals("Hand 1"))
		{
			return Hitboxes.get(10);
		}else if(name.equals("Upper Arm 2"))
		{
			return Hitboxes.get(11);
		}else if(name.equals("Lower Arm 2"))
		{
			return Hitboxes.get(12);
		}else if(name.equals("Hand 2"))
		{
			return Hitboxes.get(13);
		}else if(name.equals("Lower Leg  1"))
		{
			return Hitboxes.get(14);
		}else if(name.equals("Foot 1"))
		{
			return Hitboxes.get(15);
		}else if(name.equals("Lower Leg 2"))
		{
			return Hitboxes.get(16);
		}else if(name.equals("Foot 2"))
		{
			return Hitboxes.get(17);
		}else
		{
			return null;
		}
	}
}

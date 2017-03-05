	package GameBasics;

import java.io.Serializable;
import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import BoneStructure.Bone;
import Menus.CodexItemEntry;
import Player.Player;
import Render.AnimationSet;
import Render.BasicImage;
import Weapons.Weapon;

public abstract class Item implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4271756499787690439L;
	protected Player player;
	int code;
	protected int type;
	
	String name;
	String description;
	protected ArrayList<AnimationSet> Sprites;
	int healthBoost;
	int armorBoost;
	int damageBoost;
	int tenacityBoost;
	int speedBoost;
	
	CodexItemEntry entry;
	
	protected ArrayList<Bone> Bones;
	protected float width;
	protected float height;
	
	public Item(String name)
	{
		this.name = name;
		
		Bones = new ArrayList<Bone>();
		Sprites = new ArrayList<AnimationSet>();
	}

	protected void setInput(int code)
	{
		this.code = code;
	}
	
	public Item setPlayer(Player player)
	{
		this.player = player;
		return this;
	}
	
	public Item setSprite(String ref, long delay)
	{
		Sprites.add(new AnimationSet(ref, delay));
		return this;
	}
	
	public Item setDes(String des)
	{
		description = des;
		return this;
	}
	
	public Item setHealth(int health)
	{
		healthBoost = health;
		return this;
	}
	
	public Item setArmor(int armor)
	{
		armorBoost = armor;
		return this;
	}
	
	public Item setDamage(int damage)
	{
		damageBoost = damage;
		return this;
	}
	
	public Item setTenacity(int tenacity)
	{
		tenacityBoost = tenacity;
		return this;
	}
	
	public Item setSpeed(int speed)
	{
		speedBoost = speed;
		return this;
	}
	
	public Item setDimensions(float width, float height)
	{
		this.width = width;
		this.height = height;
		return this;
	}
	
	
	public void update()
	{
		if(player.getInput().isKeyDown(code))
		{
			Ability();
		}
	}
	
	public void render(Graphics g) throws SlickException
	{
		for(int i = 0; i < Sprites.size(); i ++)
		{
			Sprites.get(i).render(Bones.get(i).getX(), Bones.get(i).getY(), 
					width, height, Bones.get(i).getRot(), g);
		}
	}
	
	public void Ability()
	{
		
	}
	
	public void equip() 
	{
		player.equip(this, type);
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getHealth()
	{
		return healthBoost;
	}
	
	public int getArmor()
	{
		return armorBoost;
	}
	
	public int getTenacity()
	{
		return tenacityBoost;
	}
	
	public int getDamage()
	{
		return damageBoost;
	}
	
	public int getSpeed()
	{
		return speedBoost;
	}
	
	public float getWidth()
	{
		return width;
	}
	
	public float getHeight()
	{
		return height;
	}
	
	public AnimationSet getSprite()
	{
		return Sprites.get(0);
	}
}

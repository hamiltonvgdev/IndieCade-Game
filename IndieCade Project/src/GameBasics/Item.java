package GameBasics;

import java.io.Serializable;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

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
	
	String name;
	protected AnimationSet sprite;
	int healthBoost;
	int armorBoost;
	int damageBoost;
	int tenacityBoost;
	int speedBoost;
	
	CodexItemEntry entry;
	
	public Item(String name)
	{
		this.name = name;
		
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
		sprite = new AnimationSet(ref, delay);
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
	
	public void update()
	{
		if(player.getInput().isKeyDown(code))
		{
			Ability();
		}
	}
	
	public void render(Graphics g) throws SlickException
	{
		
	}
	
	public void Ability()
	{
		
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
}

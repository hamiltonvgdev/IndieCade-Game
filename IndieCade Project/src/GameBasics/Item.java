package GameBasics;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Player.Player;
import Render.AnimationSet;
import Render.BasicImage;
import Weapons.Weapon;

public abstract class Item 
{
	String name;
	public AnimationSet sprite;
	int healthBoost;
	int armorBoost;
	int damageBoost;
	int tenacityBoost;
	int speedBoost;
	
	Input input;
	
	public Item(String name, Player player)
	{
		this.name = name;
		
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
		
	}
	
	public void render(Graphics g) throws SlickException
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

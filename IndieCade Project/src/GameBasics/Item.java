package GameBasics;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Render.BasicImage;

public class Item 
{
	String name;
	BasicImage Icon;
	int healthBoost;
	int armorBoost;
	int damageBoost;
	int tenacityBoost;
	int speedBoost;
	
	public Item(String name)
	{
		this.name = name;
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

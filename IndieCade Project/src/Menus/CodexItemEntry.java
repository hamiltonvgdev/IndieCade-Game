package Menus;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import GameBasics.Item;

public class CodexItemEntry 
{
	String name;
	String des;
	int health;
	int armor;
	int damage;
	int tenacity;
	int speed;
	
	public CodexItemEntry(CodexMenu codex, Item item)
	{
		name = item.getName();
		health = item.getHealth();
		armor = item.getArmor();
		damage = item.getDamage();
		tenacity = item.getTenacity();
		speed = item.getSpeed();
	}
	
	public void update()
	{
		
	}
	
	public void render(Graphics g) throws SlickException
	{
		
	}
	
	public void setDescription(String des)
	{
		this.des = des;
	}
}

package Weapons;

import java.io.Serializable;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import GameBasics.Entity;
import Geo.Quad;
import Map.Map;
import Map.World;
import Player.Player;
import Render.AnimationSet;

public abstract class Weapon implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2981180336430241954L;
	String name;
	Player player;
	Map map;
	
	AnimationSet sprite;
	float xOffset;
	
	float damage;
	float atkSpeed;
	float Range;
	
	float critChance;
	float critMultiplier;
	float statusChance;
	int statusType;
	
	long atkTick;
	ArrayList<Entity> Hit;
	
	float width;
	float height;
	float rot;
	
	public Weapon(String name, Player player) 
	{
		this.name = name;
		this.player = player;
		map = player.getMap();
		
		atkTick = System.currentTimeMillis();
		
		Hit = new ArrayList<Entity>();
	}
	
	public Weapon setSprite(String ref, long delay)
	{
		sprite = new AnimationSet(ref, delay);
		return this;
	}
	
	public Weapon setDimensions(float width, float height)
	{
		this.width = width;
		this.height = height;
		xOffset = width / 2;
		return this;
	}
	
	public Weapon setAtkStats(float damage, float atkSpeed, float Range)
	{
		this.damage = damage;
		this.atkSpeed = atkSpeed;
		this.Range = Range;
		return this;
	}
	
	public Weapon setChance(float crit, float multi, float status, int statType)
	{
		this.critChance = crit;
		this.critMultiplier = multi;
		this.statusChance = status;
		this.statusType = statType;
		return this;
	}
	
	public void update()
	{
		if(Mouse.isButtonDown(player.getInput().MOUSE_LEFT_BUTTON))
		{
			if(System.currentTimeMillis() - atkTick >= atkSpeed)
			{
				attack();
				atkTick = System.currentTimeMillis();
			}
		}
		
		if(player.getVx() > 0)
		{
			sprite.setFlip(false);
			if(xOffset < 0)
			{
				xOffset = -xOffset;
				rot = -rot;
			}
		}
		
		if(player.getVx() < 0)
		{
			sprite.setFlip(true);
			
			if(xOffset > 0)
			{
				xOffset = -xOffset;
				rot = -rot;
			}
		}
		
		
	}
	
	public void render(Graphics g) throws SlickException
	{
		sprite.render(player.getX(), player.getY(), width, height, rot, g);
	}
	
	public void updateMap(Map map)
	{
		this.map = map;
	}
	
	public AnimationSet getSprite()
	{
		return sprite;
	}
	
	public abstract void attack();
	
	public abstract void affect();
}

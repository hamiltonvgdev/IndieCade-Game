package Weapons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

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
	
	float damage;
	float atkSpeed;
	float Range;
	
	Random gen;
	float critChance;
	float critMultiplier;
	float statusChance;
	int statusType;
	
	long atkTick;
	ArrayList<Entity> Hit;
	
	float x;
	float y;
	
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
		
		x = player.getBody().getJoint("Wrist 2").getBone2().getX();
		y = player.getBody().getJoint("Wrist 2").getBone2().getY();
		rot = player.getBody().getJoint("Wrist 2").getBone2().getRot();
		
		gen = new Random();
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
		
		x = player.getBody().getJoint("Wrist 2").getBone2().getX();
		y = player.getBody().getJoint("Wrist 2").getBone2().getY();
		rot = player.getBody().getJoint("Wrist 2").getBone2().getRot();
	}
	
	public void render(Graphics g) throws SlickException
	{
		sprite.render(x, y, width, height, rot, g);
	}
	
	public void updateMap(Map map)
	{
		this.map = map;
	}
	
	public AnimationSet getSprite()
	{
		return sprite;
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public float getDamage()
	{
		if((gen.nextInt(100) + 1) <= critChance)
		{
			return damage * critMultiplier;
		}else
		{
			return damage;
		}
	}
	
	public abstract void attack();
	
	public abstract void affect();
}

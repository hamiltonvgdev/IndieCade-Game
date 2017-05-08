package Tiles;

import org.newdawn.slick.Color;

import Enemy.Enemy;
import Enemy.RushEnemy;
import Enemy.ShootEnemy;
import Enemy.SpawnEnemy;
import GameBasics.Entity;
import Map.Map;

public class SpawnTile extends Tile
{
	Entity shrine;
	Entity mob;
	String spawnRef;
	
	long CD;
	long tick;
	
	public SpawnTile(String name, Entity mob, long CD, Color Id) 
	{
		super(name, Id);
		
		this.mob = mob.clone();
		
		shrine = new Entity(mob.toString() + " Shrine", mob.getPlayer(),
				mob.getMaxHealth() * 5, 0).setAtkSpeed(10000).
				setDimensions(width, height).
				setAnimationSet("res/Entities/Shrines/" + name, 100);
		shrine.setPosition(x, y - height / 2);
		shrine.passive = true;
		shrine.permanent = true;
		
		
		this.CD = CD;
		
		tick = 0;
		
		Type = 2;
	}
	
	@Override
	public void postSetAction()
	{
		setAnimation(map.getTiles().get(map.getTiles().indexOf(this) - 1).getRef(),
				map.getTiles().get(map.getTiles().indexOf(this) - 1).getDelay());
	}
	
	@Override
	public void setMap(Map map)
	{
		super.setMap(map);
		this.map.getLevel().addEntity(shrine);
	}
	
	public SpawnTile setSpawnSound(String ref)
	{
		this.spawnRef = ref;
		return this;
	}
	
	public void changeCoordinates(float xa, float ya)
	{
		super.changeCoordinates(xa, ya);
		
		shrine.setPosition(xa, ya - height / 2);
	}
	
	public void update()
	{
		super.update();
		
		boolean empty = false;
		
		for(int i = 0; i < map.getLevel().getEntities().size(); i ++)
		{
			if(map.getLevel().getEntities().get(i) == mob)
			{
				empty = true;
				break;
			}
		}
		
		if(!empty)
		{
			if(System.currentTimeMillis() - tick > CD)
			{
				spawn();
			}
		}else
		{
			tick = System.currentTimeMillis();
		}
	}
	
	public void reset()
	{
		super.reset();
		
		tick = 0;
	}
	
	public void spawn()
	{
		if(!shrine.dead)
		{
			mob.setPosition(shrine.getX(), shrine.getY());
			mob.reset();
			map.getLevel().addEntity(mob);
			
			if(spawnRef != null)
			{
				Sound.Sound.playSound(spawnRef);
			}
			
		}
	}
	
	public Entity getEnt()
	{
		return mob;
	}
	
	public long getCD()
	{
		return CD;
	}
}

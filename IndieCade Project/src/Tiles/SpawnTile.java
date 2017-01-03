package Tiles;

import org.newdawn.slick.Color;

import GameBasics.Entity;

public class SpawnTile extends Tile
{
	Entity mob;
	String spawnRef;
	
	long CD;
	long tick;
	
	public SpawnTile(String name, Entity mob, long CD, Color Id) 
	{
		super(name, Id);
		
		this.mob = mob;
		
		this.CD = CD;
		
		tick = 0;
	}
	
	public Tile setSpawnSound(String ref)
	{
		this.spawnRef = ref;
		return this;
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
		
		if(empty)
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
	
	public void spawn()
	{
		map.getLevel().addEntity(mob);
		
		if(spawnRef != null)
		{
			Sound.Sound.playSound(spawnRef);
		}
	}
}

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
		
		this.mob = new Entity(mob.getPlayer(), mob.getDamage(), mob.getSpeed()).
		setAnimationSet(mob.getSprite().getFolder(), mob.getSprite().getDelay()).
		setDimensions(mob.getWidth(), mob.getHeight());
		
		this.CD = CD;
		
		tick = 0;
		
		Type = 2;
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
	
	public void spawn()
	{
		mob.setPosition(x, y - mob.getHeight() / 2 - 32);
		map.getLevel().addEntity(mob);
		
		if(spawnRef != null)
		{
			Sound.Sound.playSound(spawnRef);
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

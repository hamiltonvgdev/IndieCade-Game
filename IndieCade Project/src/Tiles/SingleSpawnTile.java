package Tiles;

import org.newdawn.slick.Color;

import GameBasics.Entity;

public class SingleSpawnTile extends SpawnTile
{
	boolean spent;
	
	public SingleSpawnTile(String name, Entity mob, long CD, Color Id) 
	{
		super(name, mob, CD, Id);
		spent = false;
		Type = 4;
	}

	@Override
	public void spawn()
	{
		if(!spent)
		{
			super.spawn();
			spent = true;
			map.getLevel().removeEntity(shrine);
		}
	}
	
	@Override
	public void reset()
	{
		super.reset();
		spent = false;
	}
}

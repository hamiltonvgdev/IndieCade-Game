package Tiles;

import org.newdawn.slick.Color;

import Enemy.Enemy;
import Enemy.RushEnemy;
import Enemy.ShootEnemy;
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
		
		if(mob.getID() == 0)
		{
			this.mob = new Entity(mob.getPlayer(), mob.getMaxHealth(), mob.getDamage(), mob.getSpeed()).
					setAnimationSet(mob.getSprite().getFolder(), mob.getSprite().getDelay()).
					setDimensions(mob.getWidth(), mob.getHeight()).
					setAtkSpeed(mob.getAtkSpeed());
		}else if(mob.getID() == 1)
		{
			this.mob = ((RushEnemy) new RushEnemy(mob.getPlayer(), mob.getMaxHealth(), mob.getDamage(), mob.getSpeed()).
					setAnimationSet(mob.getSprite().getFolder(), mob.getSprite().getDelay()).
					setDimensions(mob.getWidth(), mob.getHeight()).
					setAtkSpeed(mob.getAtkSpeed())).setRange(((Enemy) mob).getRange()).
					setTriggeredAnimation(((Enemy) mob).getTriggeredSprite().getFolder(), ((Enemy) mob).getTriggeredSprite().getDelay());
		}else if(mob.getID() == 2)
		{
			//Set Projectile
			this.mob = ((ShootEnemy) ((Enemy) new ShootEnemy(mob.getPlayer(), mob.getMaxHealth(), mob.getDamage(), mob.getSpeed()).
					setAnimationSet(mob.getSprite().getFolder(), mob.getSprite().getDelay()).
					setDimensions(mob.getWidth(), mob.getHeight()).
					setAtkSpeed(mob.getAtkSpeed())).setRange(((Enemy) mob).getRange()).
					setTriggeredAnimation(((Enemy) mob).getTriggeredSprite().getFolder(), ((Enemy) mob).getTriggeredSprite().getDelay())).
					setProjectile(((ShootEnemy) mob).getProjectile(), ((ShootEnemy) mob).getXa());
		}
		
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
	
	public void reset()
	{
		super.reset();
		
		tick = 0;
	}
	
	public void spawn()
	{
		mob.setPosition(x, y - mob.getHeight() / 2 - 32);
		mob.reset();
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

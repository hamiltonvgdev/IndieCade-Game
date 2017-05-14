package Enemy;

import GameBasics.Entity;
import Player.Player;

public class SpawnEnemy extends Enemy
{
	Entity minion;
	long cd;
	long tick;
	String spawnRef;
	
	public SpawnEnemy(String name, Player player, float health, float damage) 
	{
		super(name, player, health, damage);
		tick = System.currentTimeMillis();
	}
	
	public SpawnEnemy setEntity(Entity minion, long cd)
	{
		this.minion = minion.clone();
		this.cd = cd;
		return this;
	}
	
	public SpawnEnemy setSpawnSound(String ref)
	{
		spawnRef = ref;
		return this;
	}
	
	@Override
	public void action()
	{
		if(System.currentTimeMillis() - tick >= cd)
		{
			minion = minion.clone();
			minion.setPosition(x, y - minion.getHeight() / 2 - 32);
			minion.reset();
			map.getLevel().addEntity(minion);
			
			if(spawnRef != null)
			{
				Sound.Sound.playSound(spawnRef);
			}
			
			tick = System.currentTimeMillis();
		}
	}
	
	public SpawnEnemy clone()
	{
		return (SpawnEnemy) new SpawnEnemy(name, player, health, damage).
				setEntity(minion, cd).setSpawnSound(spawnRef).
				setRange(range).setTriggeredAnimation(triggered.getFolder(), triggered.getDelay()).
				setDimensions(width, height).setAnimationSet(sprite.getFolder(), sprite.getDelay()).
				setAtkSpeed(atkSpeed).setMove(speed, acceleration);
	}
}

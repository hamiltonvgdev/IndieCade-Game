package Enemy;

import Player.Player;

public class RushEnemy extends Enemy
{
	//An enemy that Rushed at player then player is within a certain range
	
	public RushEnemy(String name, Player player, float health, float damage)
	{
		super(name, player, health, damage);
		
		id = 1;
	}
	
	@Override
	protected void action()
	{
		follow(player);
	}
	
	@Override
	protected void deaction()
	{
		moving = false;
	}
	
	public RushEnemy clone()
	{
		
		return (RushEnemy) new RushEnemy(name, player, health, damage).
				setRange(range).setTriggeredAnimation(triggered.getFolder(), triggered.getDelay()).
				setDimensions(width, height).setAnimationSet(sprite.getFolder(), sprite.getDelay()).
				setAtkSpeed(atkSpeed).setMove(speed, acceleration);
	}
}

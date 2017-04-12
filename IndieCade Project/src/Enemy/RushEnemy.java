package Enemy;

import Player.Player;

public class RushEnemy extends Enemy
{
	//An enemy that Rushed at player then player is within a certain range
	
	public RushEnemy(String name, Player player, float health, float damage, float speed)
	{
		super(name, player, health, damage, speed);
		
		id = 1;
	}
	
	@Override
	protected void action()
	{
		follow(player);System.out.println(speed);
	}
	
	@Override
	protected void deaction()
	{
		Vx = 0;
	}
	
	public RushEnemy clone()
	{
		
		return (RushEnemy) new RushEnemy(name, player, health, damage, speed).
				setRange(range).setTriggeredAnimation(triggered.getFolder(), triggered.getDelay()).
				setDimensions(width, height).setAnimationSet(sprite.getFolder(), sprite.getDelay()).
				setAtkSpeed(atkSpeed);
	}
}

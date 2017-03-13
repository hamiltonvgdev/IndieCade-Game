package Enemy;

import Player.Player;

public class RushEnemy extends Enemy
{

	public RushEnemy(Player player, float health, float damage, float speed)
	{
		super(player, health, damage, speed);
		
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
		Vx = 0;
	}
	
}

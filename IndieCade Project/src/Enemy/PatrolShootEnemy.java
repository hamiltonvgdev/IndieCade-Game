package Enemy;

import Player.Player;

public class PatrolShootEnemy extends ShootEnemy
{

	long cd;
	
	public PatrolShootEnemy(String name, Player player, float health,
			float damage)
	{
		super(name, player, health, damage);
	}
	
	public PatrolShootEnemy setWanderCD(long cd)
	{
		this.cd = cd;
		wanderTick = System.currentTimeMillis() - cd;
		return this;
	}

	@Override
	public void update()
	{
		super.update();
		
		wander(cd);
	}

	@Override
	public ShootEnemy clone()
	{
		if(triggered == null)
		{
			triggered = sprite;
		}
		
		return (PatrolShootEnemy) new PatrolShootEnemy(name, player, health, damage).setWanderCD(cd).
				setProjectile(proj, xa).setRange(range).
				setTriggeredAnimation(triggered.getFolder(), triggered.getDelay()).
				setDimensions(width, height).setAnimationSet(sprite.getFolder(), sprite.getDelay()).
				setAtkSpeed(atkSpeed).setMove(speed, acceleration);
	}
}

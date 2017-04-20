package Enemy;

import GameBasics.Entity;
import Player.Player;
import Projectiles.BasicProjectile;

public class ReflectEnemy extends ShieldEnemy
{

	public ReflectEnemy(String name, Player player, float health, float damage,
			float speed)
	{
		super(name, player, health, damage, speed);
	}
	
	@Override
	public void shield(BasicProjectile blocked)
	{
		Entity derp = new Entity("dummy", player, 0, damage, 0);
		derp.setPosition(blocked.getX(), blocked.getY());
		
		blocked.setType(0);
		blocked.setShooter(derp);
		blocked.setVelocity(-blocked.getVx(), -blocked.getVy());
	}
}

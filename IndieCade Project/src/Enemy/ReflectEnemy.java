package Enemy;

import GameBasics.Entity;
import Player.Player;
import Projectiles.BasicProjectile;

public class ReflectEnemy extends ShieldEnemy
{

	public ReflectEnemy(String name, Player player, float health, float damage)
	{
		super(name, player, health, damage);
	}
	
	@Override
	public void shield(BasicProjectile blocked)
	{
		Entity derp = new Entity("dummy", player, 0, damage);
		derp.setPosition(blocked.getX(), blocked.getY());
		
		blocked.setType(0);
		blocked.setShooter(derp);
		blocked.shoot(-blocked.getVx(), -blocked.getVy());
	}
}

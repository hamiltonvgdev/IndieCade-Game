package Projectiles;

import Player.Player;

public class SpinShot extends BasicProjectile
{

	public SpinShot(Player player, float damage, int type) 
	{
		super(player, damage, type);
	}
	
	public void update()
	{
		super.update();
		
		oRot += Math.sqrt(Vx * Vx + Vy * Vy);
	}

}

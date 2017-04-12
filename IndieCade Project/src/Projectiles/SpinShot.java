package Projectiles;

import Player.Player;

public class SpinShot extends BasicProjectile
{

	public SpinShot(Player player, int type) 
	{
		super(player, type);
	}
	
	public void update()
	{
		super.update();
		
		oRot += Math.sqrt(Vx * Vx + Vy * Vy);
	}

}

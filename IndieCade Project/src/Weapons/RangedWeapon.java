package Weapons;

import Map.World;
import Player.Player;
import Projectiles.BasicProjectile;

public class RangedWeapon extends Weapon
{
	BasicProjectile shot;
	String Pref;
	long Pdelay;
	float Pwidth;
	float Pheight;
	float Prot;
	
	public RangedWeapon(String name, Player player)
	{
		super(name, player);
	}
	
	public RangedWeapon setProjectile(String ref, long delay, float width, float height, float rot)
	{
		Pref = ref;
		Pdelay = delay;
		Pwidth = width;
		Pheight = height;
		Prot = rot;
		return this;
	}

	@Override
	public void attack() 
	{
		shot = new BasicProjectile(player, null, this, 1).setSprite(Pref, Pdelay).
				setDimensions(Pwidth, Pheight, -Prot);
		shot.shoot(Range * factor);
		
		if(factor < 0)
		{
			shot.getSprite().setFlip(true);
			shot = shot.setDimensions(Pwidth, Pheight, Prot);
		}
	}

	@Override
	public void affect() 
	{
		
	}
	
}

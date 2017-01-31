package Weapons;

import Map.World;
import Player.Player;
import Projectiles.BasicProjectile;

public class RangedWeapon extends Weapon
{
	BasicProjectile shot;
	
	public RangedWeapon(String name, Player player, World world)
	{
		super(name, player);
	}

	@Override
	public void attack() 
	{
		
	}

	@Override
	public void affect() 
	{
		
	}
	
}

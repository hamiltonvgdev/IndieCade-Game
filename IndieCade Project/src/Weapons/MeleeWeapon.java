package Weapons;

import Player.Player;

public class MeleeWeapon extends Weapon
{
	
	public MeleeWeapon(String name, Player player) 
	{
		super(name, player);
	}

	public Weapon setStance()
	{
		 
		return this;
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

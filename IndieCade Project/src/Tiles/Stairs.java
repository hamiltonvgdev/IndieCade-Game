package Tiles;

import org.newdawn.slick.Color;

import Player.Player;

public class Stairs extends InteractTile
{
	int direction;
	
	public Stairs(String name, Player player, Color Id) 
	{
		super(name, player, Id);
		direction = 0;
		
		Type = 4;
	}

	public void directionalize()
	{
		for(int i = -1; i <= 1; i ++)
		{
			if(map.getTile(x + i * 64, y).collidable)
			{
				if(map.getTile(x, y + 64).collidable)
				{
					direction = i;
				}
			}
		}
	}
	
	public void update()
	{
		derp
		//nextTo and on is never changed
		if(nextTo)
		{
			if(player.getVx() * direction > 0)
			{
				player.setVy(-Math.abs(player.getVx()));
			}else if(player.getVx() * direction < 0)
			{
				player.setVy(Math.abs(player.getVx()));
			}
		}
		
		if(on)
		{
			collidable = true;
		}else
		{
			collidable = false;
		}
	}
}

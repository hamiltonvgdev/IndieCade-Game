package Projectiles;

import Geo.M;
import Player.Player;

public class SurroundShot extends ThingShot
{

	public SurroundShot(Player player, float damage, int type) 
	{
		super(player, damage, type);
	}

	@Override
	public void burst()
	{
		for(int i = 0; i < player.getMap().getTiles().size(); i ++)
		{
			if(M.distance(x, y, player.getMap().getTiles().get(i).getX(), player.getMap().getTiles().get(i).getY())
					< radius * 64 && 
					M.distance(x, y, player.getMap().getTiles().get(i).getX(), player.getMap().getTiles().get(i).getY())
					> (radius - 1) * 64)
			{
				player.getMap().getLevel().addThing(thing.clone(player.getMap().getTiles().get(i)));
			}
		}
	}
}

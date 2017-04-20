package Projectiles;

import Player.Player;

public class SpreadShot extends SplitShot
{

	public SpreadShot(Player player, int type)
	{
		super(player, type);
	}
	
	public void update()
	{
		if(ended)
		{
			
		}
		
		super.update();
	}

}

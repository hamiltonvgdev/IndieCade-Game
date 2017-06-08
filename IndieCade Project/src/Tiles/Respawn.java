package Tiles;

import org.newdawn.slick.Color;

public class Respawn extends Tile
{
	public Respawn() 
	{
		super("Respawn", new Color(105 / 255F, 0 / 255F, 105 / 255F));
		Type = 100;
	}
	
	@Override
	public void postSetAction()
	{
		setAnimation(map.getTiles().get(map.getTiles().indexOf(this) + 1).getRef(),
				map.getTiles().get(map.getTiles().indexOf(this) + 1).getDelay());
		setSound(map.getTiles().get(map.getTiles().indexOf(this) + 1).getSoundRef());
		setFriction(map.getTiles().get(map.getTiles().indexOf(this) + 1).getFriction());
	}
}

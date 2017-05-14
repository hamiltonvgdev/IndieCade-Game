package Thing;

import Player.Player;
import Tiles.Tile;

public class Frost extends ActThing
{
	public Frost(String ref, long delay, Player player, int type) 
	{
		super(ref, delay, player, type);
	}
	
	public void update()
	{
		if(!ended)
		{
			super.update();
			
			for(int i = 0; i < tile.getMap().getLevel().getEntities().size(); i ++)
			{
				if(tile.getMap().getLevel().getEntities().get(i).getSpeed() >= 0 &&
						tile.getHitbox().checkQuad(tile.getMap().getLevel().getEntities().get(i).Hitbox))
				{
					tile.getMap().getLevel().getEntities().get(i).setMove(	
							tile.getMap().getLevel().getEntities().get(i).getSpeed() - 0.1F, 
							tile.getMap().getLevel().getEntities().get(i).getAcceleration());
				}
			}
		}
	}
	
	@Override
	public Frost clone(Tile tile)
	{
		return (Frost) new Frost(sprite.getFolder(), sprite.getDelay(), player, type).
				setDimension(width, height, rot).setTile(tile).setCollidable(tile.getCollidable(),
						permanent).setAge(age);
	}
}

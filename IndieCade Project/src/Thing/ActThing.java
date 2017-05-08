package Thing;

import Map.Map;
import Player.Player;
import Tiles.Tile;

public class ActThing extends Thing
{
	Player player;
	Map map;
	int type;
	
	public ActThing(String ref, long delay, Player player, int type) 
	{
		super(ref, delay);
		
		this.player = player;
		map = player.getMap();
		
		this.type = type;
	}
	
	@Override
	public ActThing clone(Tile tile)
	{
		return (ActThing) new ActThing(sprite.getFolder(), sprite.getDelay(), player, type).
				setDimension(width, height, rot).setTile(tile).setCollidable(collide, permanent).setAge(age);
	}

}

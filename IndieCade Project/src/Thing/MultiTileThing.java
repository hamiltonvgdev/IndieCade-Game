package Thing;

import java.util.ArrayList;

import Tiles.Tile;

public class MultiTileThing extends Thing
{
	ArrayList<Tile> Tiles;
	
	public MultiTileThing(String ref, long delay) 
	{
		super(ref, delay);
		
		Tiles = new ArrayList<Tile>();
	}
	
	@Override
	public MultiTileThing setTile(Tile tile)
	{
		super.setTile(tile);
		
		for(int i = -1; i <= 1; i += 2)
		{
			for(int j = -1; j <= 1; j += 2)
			{
				Tiles.add(tile.getMap().getTile(width / 2 * i, height / 2 * j));
			}
		}
		
		return this;
	}
	
	@Override
	public void update()
	{
		super.update();
		
		for(Tile tile: Tiles)
		{
			if(!permanent && collide != tile.getOriginalCollidable())
			{
				tile.setCollide(collide);
			}
		}
	}
	
	public void end()
	{
		for(Tile tile: Tiles)
		{
			if(!permanent && collide != tile.getOriginalCollidable())
			{
				tile.setCollide(tile.getOriginalCollidable());
			}
		}
		
		super.end();

	}
}

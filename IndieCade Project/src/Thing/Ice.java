package Thing;

import Tiles.Tile;

public class Ice extends Thing
{
	float frictiono;
	
	public Ice(String ref, long delay) 
	{
		super(ref, delay);
	}
	
	@Override
	public Ice setTile(Tile tile)
	{
		super.setTile(tile);
		frictiono = tile.getFriction();
		tile.setFriction(0.01F);
		return this;
	}
	
	@Override
	public void end()
	{
		super.end();

		tile.setFriction(frictiono);
	}
	
	@Override
	public Ice clone(Tile tile)
	{
		return (Ice) new Ice(sprite.getFolder(), sprite.getDelay()).
				setDimension(width, height, rot).setTile(tile).setCollidable(tile.getOriginalCollidable(),
						permanent).setAge(age);
	}

}

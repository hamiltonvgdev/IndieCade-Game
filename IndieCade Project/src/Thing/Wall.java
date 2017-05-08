package Thing;

import Tiles.Tile;

public class Wall extends Thing
{
	public Wall(String ref, long delay) 
	{
		super(ref, delay);
		collide = true;
	}
	
	@Override
	public Wall setTile(Tile tile)
	{
		super.setTile(tile);
		return this;
	}
	
	public void update()
	{
		if(!ended)
		{
			super.update();
			
			for(int i = 0; i < tile.getMap().getLevel().getProjectiles().size(); i ++)
			{
				if(tile.getMap().getLevel().getProjectiles().get(i).getHitbox().check(tile.getHitbox()))
				{
					tile.getMap().getLevel().getProjectiles().get(i).end();
				}
			}
		}
	}
	
	@Override
	public Wall clone(Tile tile)
	{
		return (Wall) new Wall(sprite.getFolder(), sprite.getDelay()).
				setDimension(width, height, rot).setTile(tile).setCollidable(collide, permanent).setAge(age);
	}
}

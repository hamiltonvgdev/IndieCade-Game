package Projectiles;

import Geo.M;
import Player.Player;
import Thing.Thing;

public class ThingShot extends BasicProjectile
{
	boolean burst;
	
	Thing thing;
	float radius;
	
	public ThingShot(Player player, float damage, int type) 
	{
		super(player, damage, type);
		
		burst = false;
	}
	
	public ThingShot setThing(Thing thing, float radius)
	{
		this.thing = thing;
		this.radius = radius;
		return this;
	}
	
	public void update()
	{
		super.update();
		
		if(ended && !burst)
		{
			burst();
			burst = true;
		}
	}
	
	public void burst()
	{
		for(int i = 0; i < player.getMap().getTiles().size(); i ++)
		{
			if(M.distance(x, y, player.getMap().getTiles().get(i).getX(), player.getMap().getTiles().get(i).getY())
					< radius * 64)
			{
				player.getMap().getLevel().addThing(thing.clone(player.getMap().getTiles().get(i)));
			}
		}
	}
	
	@Override
	public ThingShot clone()
	{
		return (ThingShot) new ThingShot(player, damage, type).setThing(thing, radius).setDimensions(width, height, oRot).
				setDimensions(width, height, oRot).setSprite(sprite.getFolder(), sprite.getDelay()).
				setGravity(Ay).setLimit(limit);
	}
}

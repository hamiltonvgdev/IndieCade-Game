package Tiles;

import org.newdawn.slick.Color;

import Geo.M;
import Main.Config;
import Map.World;
import Player.Player;
import Thing.Thing;

public class PortalTile extends InteractTile
{
	World world;
	int xa;
	int ya;
	boolean teleported;
	
	public PortalTile(String name, World world, Color Id)
	{
		super(name, world.getPlayer(), Id);

		this.world = world;
		
		Type = 1;
		
		teleported = false;
		
		addThing(new Thing("res/Things/Portal", 200).setDimension(11 * 5, 13 * 5, 0));
	}
	
	@Override
	public void postSetAction()
	{
		setAnimation(map.getTiles().get(map.getTiles().indexOf(this) + 1).getRef(),
				map.getTiles().get(map.getTiles().indexOf(this) + 1).getDelay());
	}
	
	public PortalTile setDirection(int xa, int ya)
	{
		this.xa = xa;
		this.ya = ya;
		
		return this;
	}
	
	public void update()
	{
		super.update();
		
		for(Thing thing: Things)
		{
			thing.setDimension(thing.getWidth(), thing.getHeight(), 
					M.GetAngleOfLineBetweenTwoPoints(x, y, player.getX(), player.getY()));
		}
	}
	
	public void action()
	{
		if(!teleported)
		{
			teleported = true;
			world.move(xa, ya);
			world.getCurrentMap().shift(Config.WIDTH/ 2 - 
					world.getCurrentMap().getTile(new Color(1 - Id.r, 1 - Id.g, 1 - Id.b, Id.a)).x,
					Config.HEIGHT / 2 - 
					world.getCurrentMap().getTile(new Color(1 - Id.r, 1 - Id.g, 1 - Id.b, Id.a)).y +
					1);
			player.Move(world.getCurrentMap().getTile(new Color(1 - Id.r, 1 - Id.g, 1 - Id.b, Id.a)).x - player.getX(),
					world.getCurrentMap().getTile(new Color(1 - Id.r, 1 - Id.g, 1 - Id.b, Id.a)).y - player.getY() - 
					1);
			
			player.setOffset(Config.WIDTH/ 2 - 
					world.getCurrentMap().getTile(new Color(1 - Id.r, 1 - Id.g, 1 - Id.b, Id.a)).x,
					Config.HEIGHT / 2 - 
					world.getCurrentMap().getTile(new Color(1 - Id.r, 1 - Id.g, 1 - Id.b, Id.a)).y + 
					1);
		}
	}
	
	public World getWorld()
	{
		return world;
	}
	
	public void reset()
	{
		super.reset();
		teleported = false;
	}
	
	public int getXa()
	{
		return xa;
	}
	
	public int getYa()
	{
		return ya;
	}
}

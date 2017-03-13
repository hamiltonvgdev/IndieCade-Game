package Tiles;

import org.newdawn.slick.Color;

import Main.Config;
import Map.World;
import Player.Player;

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
					player.getBody().getSize() * 32);
			player.Move(world.getCurrentMap().getTile(new Color(1 - Id.r, 1 - Id.g, 1 - Id.b, Id.a)).x - player.getX(),
					world.getCurrentMap().getTile(new Color(1 - Id.r, 1 - Id.g, 1 - Id.b, Id.a)).y - player.getY() - 
					player.getBody().getSize() * 32);
			
			player.setOffset(Config.WIDTH/ 2 - 
					world.getCurrentMap().getTile(new Color(1 - Id.r, 1 - Id.g, 1 - Id.b, Id.a)).x,
					Config.HEIGHT / 2 - 
					world.getCurrentMap().getTile(new Color(1 - Id.r, 1 - Id.g, 1 - Id.b, Id.a)).y + 
					player.getBody().getSize() * 32);
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

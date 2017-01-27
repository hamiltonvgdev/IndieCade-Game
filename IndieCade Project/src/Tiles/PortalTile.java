package Tiles;

import org.newdawn.slick.Color;

import Map.World;
import Player.Player;

public class PortalTile extends InteractTile
{
	World world;
	int xa;
	int ya;
	
	public PortalTile(String name, World world, Color Id)
	{
		super(name, world.getPlayer(), Id);

		this.world = world;
		
		Type = 1;
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
		world.move(xa, ya);
	}
	
	public World getWorld()
	{
		return world;
	}
}

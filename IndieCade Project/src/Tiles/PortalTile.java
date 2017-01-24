package Tiles;

import org.newdawn.slick.Color;

import Map.World;
import Player.Player;

public class PortalTile extends InteractTile
{
	World world;
	int xa;
	int ya;
	
	public PortalTile(String name, Color Id)
	{
		super(name, Id);
	}
	
	public PortalTile setDirection(int xa, int ya)
	{
		this.xa = xa;
		this.ya = ya;
		
		return this;
	}
	
	public PortalTile setWorld(World world)
	{
		this.world = world;
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

}

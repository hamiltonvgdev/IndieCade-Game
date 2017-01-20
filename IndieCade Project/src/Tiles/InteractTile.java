package Tiles;

import org.newdawn.slick.Color;

import Player.Player;

public class InteractTile extends Tile
{
	Player player;
	boolean interact;
	
	public InteractTile(String name, Color Id) 
	{
		super(name, Id);
		
		interact = false;
	}
	
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	public void update()
	{
		super.update();
		
		if(player.getInput().isKeyDown(player.getInput().KEY_LALT))
		{
			interact = true;
		}
		
		if(interact)
		{
			action();
		}
	}
	
	public void action()
	{
		
	}

}

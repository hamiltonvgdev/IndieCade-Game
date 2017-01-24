package Tiles;

import org.newdawn.slick.Color;

import Player.Player;

public abstract class InteractTile extends Tile
{
	Player player;
	boolean interact;
	
	public InteractTile(String name, Player player, Color Id) 
	{
		super(name, Id);
		
		interact = false;
		
		this.player = player;
	}
	
	@Override
	public void update()
	{	
		super.update();
		
		if(player.getInput().isKeyDown(player.getInput().KEY_LALT) && hitbox.checkQuad(player.Hitbox))
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
	
	public Player getPlayer()
	{
		return player;
	}

}

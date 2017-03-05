package Tiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

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

		for(int i = 0; i < player.getHitboxes().size(); i ++)
		{
			if(player.getHitboxes().get(i).checkQuad(hitbox) && 
					player.getInput().isKeyPressed(player.getInput().KEY_BACKSLASH))
			{
				interact = true;
				break;
			}
			
		}
		
		if(interact)
		{
			action();
		}
	}
	
	public void reset()
	{
		super.reset();
		
		interact = false;
	}
	
	public void action()
	{
		
	}
	
	public Player getPlayer()
	{
		return player;
	}

}

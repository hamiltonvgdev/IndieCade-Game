package Tiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Player.Player;

public class Ladder extends InteractTile
{
	boolean left;
	boolean right;
	
	public Ladder(String name, Player player, Color Id) 
	{
		super(name, player, Id);
		setColidable(false);
		
		Type = 3;
	}
	
	@Override
	public void update()
	{
		super.update();
		
		if(player.Hitbox.checkQuad(hitbox))
		{
			if(player.getInput().isKeyDown(Input.KEY_W))
			{
				Action(-1);
			}else if(player.getInput().isKeyDown(Input.KEY_S))
			{
				Action(1);
			}else
			{
				action();
			}
		}
		
		if(player.Hitbox.checkQuad(hitbox))
		{
			if(map.getTile(x + 64, y).collidable)
			{
				
			}
			
			if(map.getTile(x - 64, y).collidable)
			{
				
			}
		}
			
	}
	
	
	
	public void Action(int factor)
	{
		player.clearJump();
		player.setVy(factor * player.getSpeed());
		player.setVx(0);
	}
	
	public void action()
	{
		player.setVy(-player.getAy());
	}
}

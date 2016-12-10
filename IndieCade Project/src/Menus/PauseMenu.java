package Menus;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Map.World;
import Player.Player;

public class PauseMenu extends BasicMenu
{
	boolean paused;
	
	public PauseMenu()
	{
		paused = false;
	}

	@Override
	public void update()
	{
		if(paused)
		{
			
		}
	}

	@Override
	public void render(Graphics g) throws SlickException 
	{
		if(paused)
		{
			
		}
	}
	
	public void pause(World world, Player player)
	{
		world.pause();
		player.pause();
	}
	
	public void unpause(World world, Player player)
	{
		world.unpause();
		player.unpause();
	}
}

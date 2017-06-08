package Map;

import java.io.Serializable;
import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Player.Player;

public class World implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1240449199258534855L;

	boolean paused;
	
	int mapX;
	int mapY;
	int factor;
	
	ArrayList<Map> world;
	
	Player player;
	
	public World(Player player)
	{
		paused = false;
		
		this.player = player;
	}
	
	public void initMaps(int id)
	{

		try {
			world = Converter.convertMap(
					MapReader.readTileMap(new Image("res/World " + id + ".png")),
					MapList.getMaps());
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		factor = 20;
		
		mapX = 6;
		mapY = 10;
		
		for(Map map: world)
		{
			map.reset();
		}
		
		player.setMap(world.get(mapX + mapY * factor));
	}
	
	public void update()
	{
		if(!paused)
		{
			player.setMap(world.get(mapX + mapY * factor));
			world.get(mapX + mapY * factor).update();
			
			//System.out.println(world.get(mapX + mapY * 2).tilemap.getResourceReference());
			//System.out.println(mapX + " " + mapY);
		}
	}
	
	public void render(Graphics g) throws SlickException
	{
		world.get(mapX + mapY * factor).render(g);
	}
	
	public void move(int xa, int ya)
	{
		mapX += xa;
		mapY += ya;
		world.get(mapX + mapY * factor).reset();
	}
	
	public void pause()
	{
		paused = true;
	}
	
	public void unpause()
	{
		paused = false;
	}
	
	public Player getPlayer()
	{
		return player;
	}

	public Map getCurrentMap()
	{
		return world.get(mapX + mapY * factor);
	}
	
	public int getXa() 
	{
		return mapX;
	}
	
	public int getYa()
	{
		return mapY;
	}
}

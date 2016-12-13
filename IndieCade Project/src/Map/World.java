package Map;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Player.Player;

public class World 
{
	int mapX;
	int mapY;
	
	ArrayList<Map> world;
	
	Player player;
	
	public World(Player player)
	{
		try {
			world = Converter.convertMap(MapReader.readTileMap(new Image("res/World.png")), MapList.getMaps());
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		mapX = (world.size() % 20) / 2;
		mapY = (world.size() / 20) / 2;
		
		this.player = player;
		player.setMap(world.get(mapX + mapY * 20));
	}
	
	public void update()
	{
		player.setMap(world.get(mapX + mapY * 20));
		world.get(mapX + mapY * 20).update();
	}
	
	public void render(Graphics g) throws SlickException
	{
		world.get(mapX + mapY * 20).render(g);
	}
	
	public void move(int xa, int ya)
	{
		mapX += xa;
		mapY += ya;
	}
}

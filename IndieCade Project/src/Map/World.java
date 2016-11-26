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
	
	public World()
	{
		mapX = 10;
		mapY = 8;
		
		try {
			world = Converter.convertMap(MapReader.readTileMap(new Image("res/World.png")), MapList.getMaps());
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void update()
	{
		world.get(mapX + mapY * 20).update();
	}
	
	public void render(Graphics g)
	{
		world.get(mapX + mapY * 20).render(g);;
	}
	
	public void move(int xa, int ya)
	{
		mapX += xa;
		mapY += ya;
	}
}

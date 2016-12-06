package Map;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import GameBasics.Entity;
import GameBasics.Level;
import Geo.Quad;
import Player.Player;
import Tiles.Tile;

public class Map 
{
	Level level;
	ArrayList<Tile> TileMap;
	float height;
	float width;
	Image BackGround;
	
	public Quad Hitbox;
	float x;
	float y;
	
	Color ID;
	
	long respawnTick;
	
	Random gen;
	ArrayList<Entity> mobs;
	int spawnSize;
	
	public Map(Player player, Image tilemap, Image BackGround, Color ID)
	{
		this.ID = ID;
		level = new Level(player);
		this.BackGround = BackGround;
		
		TileMap = Converter.convertTile(MapReader.readTileMap(tilemap), TileList.getTiles());
		width = tilemap.getWidth();
		height = tilemap.getHeight();
		
		respawnTick = System.currentTimeMillis();
		
		gen = new Random();
		
		for(int i = 0; i < TileMap.size(); i ++)
		{
			TileMap.get(i).changeCoordinates(i % tilemap.getWidth() * 64 + 64 / 2,
					i / tilemap.getWidth() * 64 + 64 / 2);
		}

		x = width * 64 / 2; 
		y = height * 64 / 2;
		Hitbox = new Quad(x, y, width * 64, height * 64);
	}
	
	public Map setSpawn(int SpawnSize)
	{
		spawnSize = SpawnSize;
		return this;
	}
	
	public void update()
	{
		if(System.currentTimeMillis() - respawnTick >= 500)
		{
			if(level.getEntities().size() < 10)
			{
				while(level.getEntities().size() < spawnSize)
				{
					spawn();
				}
			}
		}
		
		level.update();
		
		for(Tile T: TileMap)
		{
			T.update();
		}
		
		Hitbox.changeDimensions(x, y,  width * 64, height * 64);
	}

	public void render(Graphics g) throws SlickException
	{
		g.drawImage(BackGround, 0, 0);
		
		for(Tile T: TileMap)
		{
			T.render(g);
		}
		
		Hitbox.render(g);
	}
	
	public void shift(float xa, float ya)
	{
		for(int i = 0; i < TileMap.size(); i ++)
		{
			TileMap.get(i).shift(xa, ya);
		}
		x += xa;
		y += ya;
	}
	
	private void spawn() 
	{
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		
		for(Tile T: TileMap)
		{
			if(T.getSpawnable())
			{
				tiles.add(T);
			}
		}
		
		int mobIndex = gen.nextInt(mobs.size());
		int tileIndex = gen.nextInt(tiles.size());
		
		try {
			level.addEntity(mobs.get(mobIndex).getClass().newInstance().
					setLocation(tiles.get(tileIndex).getX(), tiles.get(tileIndex).getY()));
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addSpawnRates(Entity mob, int rate)
	{
		for(int i = 0; i < rate; i ++)
		{
			mobs.add(mob);
		}
	}
	
	public Color getID()
	{
		return ID;
	}
	
	public ArrayList<Tile> getTiles()
	{
		return TileMap;
	}
	
	//Direct Map Customizations
	
	public Map directSpawn(Entity e)
	{
		level.addEntity(e);
		
		return this;
	}
}

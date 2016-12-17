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
	Image tilemap;
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
		
		this.tilemap = tilemap;
		TileMap = Converter.convertTile(MapReader.readTileMap(tilemap), TileList.getTiles());
		width = tilemap.getWidth();
		height = tilemap.getHeight();
		
		respawnTick = System.currentTimeMillis();
		mobs = new ArrayList<Entity>();
		
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
				if(mobs.size() > 0)
				{
					while(level.getEntities().size() < spawnSize)
					{
						spawn();
					}
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
	
	public void reset()
	{
		mobs.clear();
		
		for(int i = 0; i < TileMap.size(); i ++)
		{
			TileMap.get(i).changeCoordinates(i % tilemap.getWidth() * 64 + 64 / 2,
					i / tilemap.getWidth() * 64 + 64 / 2);
		}

		x = width * 64 / 2; 
		y = height * 64 / 2;
		
		respawnTick = System.currentTimeMillis();
	}
	
	public Color getID()
	{
		return ID;
	}
	
	public ArrayList<Tile> getTiles()
	{
		return TileMap;
	}
	
	public Tile getCurrentTile(float x, float y)
	{
		Tile derp = new Tile(null, null);
		
		for(int i = 0; i < TileMap.size(); i ++)
		{
			if(TileMap.get(i).getHitbox().checkQuad(new Quad(x, y, 5, 5)))
			{
				derp = TileMap.get(i);
				
				break;
			}
		}
		
		return derp;
	}
	
	//Direct Map Customizations
	
	public Map directSpawn(Entity e)
	{
		level.addEntity(e);
		
		return this;
	}
}

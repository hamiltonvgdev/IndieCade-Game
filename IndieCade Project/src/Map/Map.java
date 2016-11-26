package Map;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import GameBasics.Entity;
import GameBasics.Level;
import Player.Player;
import Tiles.Tile;

public class Map 
{
	Level level;
	ArrayList<Tile> TileMap;
	Image BackGround;
	
	Color ID;
	
	long respawnTick;
	
	Random gen;
	ArrayList<Entity> mobs;
	ArrayList<Integer> rates;
	int spawnSize;
	
	public Map(Player player, Image tilemap, Image BackGround, int SpawnSize, Color ID)
	{
		this.ID = ID;
		level = new Level(player);
		this.BackGround = BackGround;
		
		TileMap = Converter.convertTile(MapReader.readTileMap(tilemap), TileList.getTiles());
		
		respawnTick = System.currentTimeMillis();
		
		gen = new Random();
		
		spawnSize = SpawnSize;
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
	}

	public void render(Graphics g)
	{
		g.drawImage(BackGround, 0, 0);
		
		for(Tile T: TileMap)
		{
			T.render(g);
		}
			
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
		
		
	}
	
	public void addSpawnRates(Entity mob, int rate)
	{
		mobs.add(mob);
		rates.add(rate);
	}
	
	public Color getID()
	{
		return ID;
	}
}

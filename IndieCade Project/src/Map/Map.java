package Map;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import GameBasics.BasicNPC;
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
	String themeMusic;
	
	public Quad Hitbox;
	float x;
	float y;
	
	Color ID;
	
	public Map(Player player, Image tilemap, Image BackGround, Color ID)
	{	
		this.ID = ID;
		level = new Level(player);
		this.BackGround = BackGround;
		
		this.tilemap = tilemap;
		TileMap = Converter.convertTile(MapReader.readTileMap(tilemap), TileList.getTiles());
		width = tilemap.getWidth();
		height = tilemap.getHeight();
		
		for(int i = 0; i < TileMap.size(); i ++)
		{
			TileMap.get(i).changeCoordinates(i % tilemap.getWidth() * 64 + 64 / 2,
					i / tilemap.getWidth() * 64 + 64 / 2);
			TileMap.get(i).setMap(this);
		}

		x = width * 64 / 2; 
		y = height * 64 / 2;
		Hitbox = new Quad(x, y, width * 64, height * 64);
		
		themeMusic = null;
	}
	
	public Map spawnNPC(BasicNPC npc)
	{
		level.addNPC(npc);
		return this;
	}
	
	public Map setBackGroundMusic(String ref)
	{
		themeMusic = ref;
		return this;
	}
	
	public void update()
	{	
		level.update();
		
		for(Tile T: TileMap)
		{
			T.update();
		}
		
		for(int i = 0; i < level.getNPCs().size(); i ++)
		{
			level.getNPCs().get(i).update();
		}
		
		Hitbox.changeDimensions(x, y,  width * 64, height * 64);
		
		if(themeMusic != null)
		{
			Sound.Sound.infiniteLoopSound(themeMusic);
		}
	}

	public void render(Graphics g) throws SlickException
	{
		g.drawImage(BackGround, 0, 0);
		
		for(Tile T: TileMap)
		{
			T.render(g);
		}
		
		for(int i = 0; i < level.getNPCs().size(); i ++)
		{
			level.getNPCs().get(i).render(g);
		}
	}
	
	public void shift(float xa, float ya)
	{
		for(int i = 0; i < TileMap.size(); i ++)
		{
			TileMap.get(i).shift(xa, ya);
		}
		x += xa;
		y += ya;
		
		level.shift(xa, ya);
	}
	
	public void reset()
	{
		for(int i = 0; i < TileMap.size(); i ++)
		{
			TileMap.get(i).changeCoordinates(i % tilemap.getWidth() * 64 + 64 / 2,
					i / tilemap.getWidth() * 64 + 64 / 2);
		}

		x = width * 64 / 2; 
		y = height * 64 / 2;
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
	
	public Level getLevel()
	{
		return level;
	}
	
	public Map directSpawn(Entity e)
	{
		level.addEntity(e);
		
		return this;
	}
}

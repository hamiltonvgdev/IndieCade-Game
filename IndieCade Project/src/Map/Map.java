package Map;

import java.io.Serializable;
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
import Main.Config;
import Player.Player;
import Sound.Sound;
import Tiles.Tile;
import Tiles.InteractTile;
import Tiles.PortalTile;
import Tiles.SpawnTile;
import Tiles.TileList;

public class Map implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7167480686083434369L;
	Level level;
	ArrayList<Tile> TileMap;
	transient Image tilemap;
	float height;
	float width;
	
	transient Image BackGround;
	String themeMusic;
	
	public Quad Hitbox;
	float x;
	float y;
	float xOffset;
	float yOffset;
	
	transient Color ID;
	
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
			
			TileMap.get(i).postSetAction();
		}

		x = 0; 
		y = 0;
		xOffset = 0;
		yOffset = 0;
		Hitbox = new Quad(x, y, width * 64, height * 64);
		
		themeMusic = null;
	}
	
	public Map setBackGroundMusic(String ref)
	{
		themeMusic = ref;
		return this;
	}
	
	public void update()
	{	
		level.update();
		
		for(int i = 0; i < TileMap.size(); i ++ )
		{
			TileMap.get(i).update();
		}
		
		for(int i = 0; i < level.getThings().size(); i ++)
		{
			level.getThings().get(i).update();
		}
		
		Hitbox.changeDimensions(x, y,  width * 64, height * 64);
		
		if(themeMusic != null)
		{
			Sound.infiniteLoopSound(themeMusic);
			//Sound.playSound(themeMusic);
		}
	}

	public void render(Graphics g) throws SlickException
	{
		if(BackGround != null)
		{
			g.drawImage(BackGround, x + xOffset, y + yOffset);
		}
		
		for(Tile T: TileMap)
		{
			T.render(g);
		}
		
		level.render(g);
	}
	
	public void shift(float xa, float ya)
	{
		level.shift(xa, ya);
		
		for(int i = 0; i < TileMap.size(); i ++)
		{
			TileMap.get(i).shift(xa, ya);
		}
		
		xOffset += xa;
		yOffset += ya;
	}
	
	public void reset()
	{
		for(int i = 0; i < TileMap.size(); i ++)
		{
			TileMap.get(i).changeCoordinates(i % tilemap.getWidth() * 64 + 64 / 2,
					i / tilemap.getWidth() * 64 + 64 / 2);
			TileMap.get(i).reset();
		}
		
		level.reset();

		x = 0; 
		y = 0;
		
		xOffset = 0;
		yOffset = 0;
	}
	
	public Color getID()
	{
		return ID;
	}
	
	public ArrayList<Tile> getTiles()
	{
		return TileMap;
	}
	
	public Tile getTile(float x, float y)
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
	
	public Tile getTile(Color ID)
	{
		Tile derp = new Tile(null, null);
		
		for(int i = 0; i < TileMap.size(); i ++)
		{
			if(Math.abs(TileMap.get(i).getID().a - ID.a) <= 0.00)
			{
				if(Math.abs(TileMap.get(i).getID().r - ID.r) <= 0.04)
				{
					if(Math.abs(TileMap.get(i).getID().g - ID.g) <= 0.04)
					{
						if(Math.abs(TileMap.get(i).getID().b - ID.b) <= 0.04)
						{
							derp = TileMap.get(i);
							
							break;
						}
					}
				}
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

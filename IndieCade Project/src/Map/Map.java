package Map;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Form.Form;
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
	Player player;
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
	
	Tile Respawn;
	float resx;
	float resy;
	
	public Map(Player player, Image tilemap, Image BackGround, Color ID)
	{	
		this.ID = ID;
		level = new Level(player);
		this.player = player;
		player.setMap(this);
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
			
			if(TileMap.get(i).getName().equals("Respawn"))
			{
				Respawn = TileMap.get(i);
			}
		}

		x = 0; 
		y = 0;
		xOffset = 0;
		yOffset = 0;
		Hitbox = new Quad(x, y, width * 64, height * 64);
		resx = Respawn.getX() + Respawn.getXOffset();
		resy = Respawn.getY() + Respawn.getYOffset();
		
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
			if(Math.abs(TileMap.get(i).getX() - player.getX()) < Config.WIDTH / 2 && 
					Math.abs(TileMap.get(i).getY() - player.getY()) < Config.HEIGHT / 2)
			{
				TileMap.get(i).update();
			}
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
			if(Math.abs(T.getX() - player.getX()) < Config.WIDTH / 2 + 64 && 
					Math.abs(T.getY() - player.getY()) < Config.HEIGHT / 2 + 64)
			{
				T.render(g);
			}
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
		
		if(player.getMap() != null)
		{
			shift((player.getX() + player.getXOffset()) - resx, (player.getY() + player.getYOffset()) - resy);
		}

		level.reset(); 
		
		for(int i = 0; i < TileMap.size(); i ++)
		{
			TileMap.get(i).changeCoordinates(i % tilemap.getWidth() * 64 + 64 / 2,
					i / tilemap.getWidth() * 64 + 64 / 2);
			
			TileMap.get(i).move((player.getX() + player.getXOffset()) - resx, 
					(player.getY() + player.getYOffset()) - resy);
			
			TileMap.get(i).reset();
		}
		
		for(Form f: player.getInventory())
		{
			f.reset();
		}
	}
	
	public void setTile(Tile tile, float xa, float ya)
	{
		int index = TileMap.indexOf(getTile(xa, ya));
		TileMap.remove(getTile(xa, ya));
		TileMap.add(index, tile);
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
		Entity derp = e.clone();
		
		derp.setPosition(x, y);
		level.addEntity(e);

		return this;
	}
	
	public Map getMap()
	{
		return this;
	}
}

package Map;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Form.Form;
import GameBasics.Entity;
import GameBasics.Level;
import Geo.Quad;
import Player.Player;
import Tiles.Tile;
import Tiles.TileList;

public class CenterRoom extends Map
{

	ArrayList<Tile> Version1;
	ArrayList<Tile> Version2;
	ArrayList<Tile> Version3;
	ArrayList<Tile> Version4;
	ArrayList<Tile> Version5;
	
	ArrayList<Map> Version;
	
	public CenterRoom(Player player, Image tilemap, Image BackGround, Color ID) 
	{
		super(player, tilemap, BackGround, ID);
		
		Version = new ArrayList<Map>();
		
		try {
			Version.add(new Map(player, new Image("res/Maps/Center Room/1.png"), BackGround, ID));
			Version.add(new Map(player, new Image("res/Maps/Center Room/2.png"), BackGround, ID));
			Version.add(new Map(player, new Image("res/Maps/Center Room/3.png"), BackGround, ID));
			Version.add(new Map(player, new Image("res/Maps/Center Room/4.png"), BackGround, ID));
			Version.add(new Map(player, new Image("res/Maps/Center Room/5.png"), BackGround, ID));
		} catch (SlickException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update()
	{
		Version.get(player.getInventory().size()).update();
	}
	
	public void render(Graphics g) throws SlickException
	{
		Version.get(player.getInventory().size()).render(g);
	}
	
	public void shift(float xa, float ya)
	{
		for(Map m: Version)
		{
			m.shift(xa, ya);
		}
	}
	
	public void reset()
	{
		
		if(player.getMap() != null)
		{
			Version.get(player.getInventory().size()).shift((player.getX() + player.getXOffset()) - resx, (player.getY() + player.getYOffset()) - resy);
		}

		Version.get(player.getInventory().size()).level.reset(); 
		
		for(int i = 0; i < TileMap.size(); i ++)
		{
			Version.get(player.getInventory().size()).TileMap.get(i).changeCoordinates(i % tilemap.getWidth() * 64 + 64 / 2,
					i / tilemap.getWidth() * 64 + 64 / 2);
			
			Version.get(player.getInventory().size()).TileMap.get(i).move((player.getX() + player.getXOffset()) - resx, 
					(player.getY() + player.getYOffset()) - resy);
			
			Version.get(player.getInventory().size()).TileMap.get(i).reset();
		}
		
		for(Form f: player.getInventory())
		{
			f.reset();
		}
	}
	
	public Color getID()
	{
		return Version.get(player.getInventory().size()).ID;
	}
	
	public ArrayList<Tile> getTiles()
	{	
		if(Version != null)
		{
			return Version.get(player.getInventory().size()).TileMap;
		}else
		{
			return TileMap;
		}
	}
	
	public Tile getTile(float x, float y)
	{
		Tile derp = new Tile(null, null);
		
		for(int i = 0; i < Version.get(player.getInventory().size()).TileMap.size(); i ++)
		{
			if(Version.get(player.getInventory().size()).TileMap.get(i).getHitbox().checkQuad(new Quad(x, y, 5, 5)))
			{
				derp = Version.get(player.getInventory().size()).TileMap.get(i);
				
				break;
			}
		}
		
		return derp;
	}
	
	public Tile getTile(Color ID)
	{
		Tile derp = new Tile(null, null);
		
		for(int i = 0; i < Version.get(player.getInventory().size()).TileMap.size(); i ++)
		{
			if(Math.abs(Version.get(player.getInventory().size()).TileMap.get(i).getID().a - ID.a) <= 0.00)
			{
				if(Math.abs(Version.get(player.getInventory().size()).TileMap.get(i).getID().r - ID.r) <= 0.04)
				{
					if(Math.abs(Version.get(player.getInventory().size()).TileMap.get(i).getID().g - ID.g) <= 0.04)
					{
						if(Math.abs(Version.get(player.getInventory().size()).TileMap.get(i).getID().b - ID.b) <= 0.04)
						{
							derp = Version.get(player.getInventory().size()).TileMap.get(i);
							
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
		return Version.get(player.getInventory().size()).level;
	}
	
	public Map directSpawn(Entity e)
	{
		Version.get(player.getInventory().size()).level.addEntity(e);

		return this;
	}
	
	public Map getMap()
	{
		return Version.get(player.getInventory().size());
	}

}

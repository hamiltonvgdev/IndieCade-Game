package Map;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Enemy.ShieldEnemy;
import GameBasics.BasicNPC;
import GameBasics.Entity;
import NPCs.AudioNPC;
import NPCs.ImageNPC;
import Player.Player;
import Tiles.Tile;

public class MapList 
{
	public static ArrayList<Map> Maps;

	public static void init(Player player) 
	{
		Maps = new ArrayList<Map>();
		
		try {
			Map Void = new Map(player, new Image("res/Maps/Void.png"), 
					null, new Color(0, 0, 0, 0));
			Maps.add(Void);
			
			Map Room = new Map(player, new Image("res/Maps/Center Room.png"),
					null, new Color(255 / 255F, 255 / 255F, 255 / 255F));
			Maps.add(Room);
		} catch (SlickException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Map> getMaps()
	{
		return Maps;
	}

	public static void directSpawn(Entity Enemy, int index) 
	{
		Maps.get(index).directSpawn(Enemy);
	}
}

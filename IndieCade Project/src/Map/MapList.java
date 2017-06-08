package Map;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Bosses.Boss;
import Enemy.ShieldEnemy;
import Form.Triangle;
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
			
			Map Room = new CenterRoom(player, new Image("res/Maps/Center Room/5.png"),
					null, new Color(255 / 255F, 255 / 255F, 255 / 255F));
			Maps.add(Room);
			
			Map Meadow1 = new Map(player, new Image("res/Maps/Meadow 1.png"),
					null, new Color(0 / 255F, 255 / 255F, 0 / 255F));
			Maps.add(Meadow1);
			
			Map Meadow2 = new Map(player, new Image("res/Maps/Meadow 2.png"),
					null, new Color(1 / 255F, 255 / 255F, 1 / 255F));
			Maps.add(Meadow2);
			
			Map Meadow3 = new Map(player, new Image("res/Maps/Meadow 3.png"),
					null, new Color(2 / 255F, 255 / 255F, 2 / 255F));
			Maps.add(Meadow3);
			
			Map MeadowBoss = new Map(player, new Image("res/Maps/Meadow Boss.png"),
					null, new Color(3 / 255F, 255 / 255F, 3 / 255F));
			Maps.add(MeadowBoss);
			
			Map Forest1 = new Map(player, new Image("res/Maps/Forest 1.png"),
					null, new Color(34 / 255F, 139 / 255F, 34 / 255F));
			Maps.add(Forest1);
			
			Map Forest2 = new Map(player, new Image("res/Maps/Forest 2.png"),
					null, new Color(36 / 255F, 141 / 255F, 36 / 255F));
			Maps.add(Forest2);
			
			Map Forest3 = new Map(player, new Image("res/Maps/Forest 3.png"),
					null, new Color(38 / 255F, 143 / 255F, 38 / 255F));
			Maps.add(Forest3);
			
			Map ForestBoss = new Map(player, new Image("res/Maps/Forest Boss.png"),
					null, new Color(40 / 255F, 145 / 255F, 40 / 255F));
			Maps.add(ForestBoss);
			
			Map IceField = new Map(player, new Image("res/Maps/Ice 1.png"),
					null, new Color(0 / 255F, 255 / 255F, 255 / 255F));
			Maps.add(IceField);
			
			
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

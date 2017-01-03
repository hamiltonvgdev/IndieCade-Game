package Map;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import GameBasics.BasicNPC;
import NPCs.AudioNPC;
import Player.Player;
import Tiles.Tile;

public class MapList 
{
	public static ArrayList<Map> Maps;
	
	public static void init(Player player) 
	{
		Maps = new ArrayList<Map>();
		
		try {
			Map test = new Map(player, new Image("res/Maps/Test Map.png"),  new Image("res/Maps/Test Map.png"), 
					new Color(8 / 255, 10 / 255, 0 / 255));
			Maps.add(test);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Map> getMaps()
	{
		return Maps;
	}
}

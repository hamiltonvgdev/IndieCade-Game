package Map;

import java.util.ArrayList;

import Tiles.Tile;

public class MapList 
{
	public static ArrayList<Map> Maps;
	
	public static void init()
	{
		Maps = new ArrayList<Map>();
	}
	
	public static ArrayList<Map> getMaps()
	{
		return Maps;
	}
}

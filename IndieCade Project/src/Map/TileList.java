package Map;

import java.util.ArrayList;

import Tiles.Tile;

public class TileList
{
	public static ArrayList<Tile> Tiles;
	
	public static void init()
	{
		Tiles = new ArrayList<Tile>();
	}
	
	public static ArrayList<Tile> getTiles()
	{
		return Tiles;
	}
}

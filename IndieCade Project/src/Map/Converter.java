package Map;

import java.util.ArrayList;

import org.newdawn.slick.Color;

import Tiles.Tile;

public class Converter 
{
	public static ArrayList<Tile> convertTile(ArrayList<Color> Id, ArrayList<Tile> PossibleTiles)
	{
		ArrayList<Tile> Tiles = new ArrayList<Tile>();
		
		for(int i = 0; i < Id.size(); i ++)
		{
			Color ID = Id.get(i);
			
			for(int j = 0; j < PossibleTiles.size(); j ++)
			{
				if(PossibleTiles.get(j).getID() == ID)
				{
					Tiles.add(PossibleTiles.get(j));
					break;
				}
			}
		}
		
		return Tiles;
	}
	
	public static ArrayList<Map> convertMap(ArrayList<Color> Id, ArrayList<Map> PossibleMaps)
	{
		ArrayList<Map> Maps = new ArrayList<Map>();
		
		for(int i = 0; i < Id.size(); i ++)
		{
			Color ID = Id.get(i);
			
			for(int j = 0; j < PossibleMaps.size(); j ++)
			{
				if(Math.abs(ID.g - PossibleMaps.get(j).getID().g) < 0.5 &&
						Math.abs(ID.r - PossibleMaps.get(j).getID().r) < 0.5)
				{
					Maps.add(PossibleMaps.get(j));
					break;
				}
			}
		}
		return Maps;
	}
}

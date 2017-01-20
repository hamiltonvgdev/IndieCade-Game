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
				if(i == 2)
				{
					System.out.println(PossibleTiles.get(2).getID());
				}
				
				if(Math.abs(PossibleTiles.get(j).getID().a - ID.a) <= 0.04)
				{
					if(Math.abs(PossibleTiles.get(j).getID().r - ID.r) <= 0.04)
					{
						if(Math.abs(PossibleTiles.get(j).getID().g - ID.g) <= 0.04)
						{
							if(Math.abs(PossibleTiles.get(j).getID().b - ID.b) <= 0.04)
							{
								Tiles.add(new Tile(PossibleTiles.get(j).getName(), PossibleTiles.get(j).getID()).
										setAnimation(PossibleTiles.get(j).getRef(), PossibleTiles.get(j).getDelay()).
										setColidable(PossibleTiles.get(j).getCollidable()).
										setFriction(PossibleTiles.get(j).getFriction()).
										setSound(PossibleTiles.get(j).getSoundRef()));
								
								break;
							}
						}
					}
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
				if(Math.abs(ID.g - PossibleMaps.get(j).getID().g) < 0.001 &&
						Math.abs(ID.r - PossibleMaps.get(j).getID().r) < 0.001)
				{
					Maps.add(PossibleMaps.get(j));
					break;
				}
			}
		}
		return Maps;
	}
}

package Map;

import java.util.ArrayList;

import org.newdawn.slick.Color;

import Tiles.InteractTile;
import Tiles.PortalTile;
import Tiles.SpawnTile;
import Tiles.Tile;

public class Converter 
{
	public static ArrayList<Tile> convertTile(ArrayList<Color> Id, ArrayList<Tile> PossibleTiles) 
	{
		ArrayList<Tile> Tiles = new ArrayList<Tile>();
		for(int i = 0; i < Id.size(); i ++)
		{
			Color ID = Id.get(i);
			
			if(ID.a > 0 && ID.a < 1)
			{
				
			}
			for(int j = 0; j < PossibleTiles.size(); j ++) 
			{	
				if(Math.abs(PossibleTiles.get(j).getID().a - ID.a) <= 0.04)
				{
					if(Math.abs(PossibleTiles.get(j).getID().r - ID.r) <= 0.04)
					{
						if(Math.abs(PossibleTiles.get(j).getID().g - ID.g) <= 0.04)
						{
							if(Math.abs(PossibleTiles.get(j).getID().b - ID.b) <= 0.04)
							{
								if(PossibleTiles.get(j).getType() == 0)
								{
									Tiles.add(new Tile(PossibleTiles.get(j).getName(), PossibleTiles.get(j).getID()).
											setAnimation(PossibleTiles.get(j).getRef(), PossibleTiles.get(j).getDelay()).
											setColidable(PossibleTiles.get(j).getCollidable()).
											setFriction(PossibleTiles.get(j).getFriction()).
											setSound(PossibleTiles.get(j).getSoundRef()).
											addThings(PossibleTiles.get(j).getThings()));
								}else if(PossibleTiles.get(j).getType() == 1)
								{
									Tiles.add(new PortalTile(PossibleTiles.get(j).getName(), ((PortalTile) PossibleTiles.get(j)).getWorld(),
											PossibleTiles.get(j).getID()).setDirection(((PortalTile) PossibleTiles.get(j)).getXa(), ((PortalTile) PossibleTiles.get(j)).getYa()).
											setAnimation(PossibleTiles.get(j).getRef(), PossibleTiles.get(j).getDelay()).
											setColidable(PossibleTiles.get(j).getCollidable()).
											setFriction(PossibleTiles.get(j).getFriction()).
											setSound(PossibleTiles.get(j).getSoundRef()).
											addThings(PossibleTiles.get(j).getThings()));
								}else if(PossibleTiles.get(j).getType() == 2)
								{
									Tiles.add(new SpawnTile(PossibleTiles.get(j).getName(), ((SpawnTile) PossibleTiles.get(j)).getEnt(), 
											((SpawnTile) PossibleTiles.get(j)).getCD(), PossibleTiles.get(j).getID()).
											setAnimation(PossibleTiles.get(j).getRef(), PossibleTiles.get(j).getDelay()).
											setColidable(PossibleTiles.get(j).getCollidable()).
											setFriction(PossibleTiles.get(j).getFriction()).
											setSound(PossibleTiles.get(j).getSoundRef()).
											addThings(PossibleTiles.get(j).getThings()));
								}
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
						Math.abs(ID.r - PossibleMaps.get(j).getID().r) < 0.001 && 
						Math.abs(ID.b - PossibleMaps.get(j).getID().b) < 0.001)
				{
					Maps.add(PossibleMaps.get(j));
					break;
				}
			}
		}
		return Maps;
	}
}

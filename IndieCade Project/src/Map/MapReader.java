package Map;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

public class MapReader
{
	public static ArrayList<Color> readTileMap(Image tilemap)
	{
		Image tiles;
		ArrayList<Color> tileId = new ArrayList<Color>();
		
		tiles = tilemap;
		
		for(int y = 0; y < tiles.getHeight(); y ++)
		{
			for(int x = 0; x < tiles.getWidth(); x ++)
			{
				tileId.add(tiles.getColor(x, y));
			}
		}
		return tileId;
	}
	
	
}

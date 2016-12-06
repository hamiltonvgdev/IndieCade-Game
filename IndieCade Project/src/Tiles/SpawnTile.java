package Tiles;

import org.newdawn.slick.Color;

public class SpawnTile extends Tile
{

	public SpawnTile(String name, Color Id)
	{
		super(name, Id);
		
		spawnable = true;
	}
	
}

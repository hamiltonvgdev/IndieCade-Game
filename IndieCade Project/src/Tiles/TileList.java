package Tiles;

import java.util.ArrayList;

import org.newdawn.slick.Color;

public class TileList
{
	public static ArrayList<Tile> Tiles;
	
	public static void init()
	{
		Tiles = new ArrayList<Tile>();
		
		Tile Air = new Tile("Air", new Color(255, 255, 255)).
				setAnimation("res/Tiles/Air", 100).
				setColidable(false);
		Tiles.add(Air);
		
		Tile Stone = new Tile("Stone", new Color(0 / 255, 0 / 255, 0 / 255)).
				setAnimation("res/Tiles/Stone/Images", 100).
				setColidable(true).setFriction(0.5F).
				setSound("res/Tiles/Stone/Sound/Stone Walk 4.wav");
		Tiles.add(Stone);
		
		Tile GearBox = new Tile("Gear Box", new Color(204 / 255F, 204 / 255F, 204 / 255F)).
				setAnimation("res/Tiles/SpawnPraxDummy/Images", 100).
				setColidable(true).setFriction(0.5F);
		Tiles.add(GearBox);
	}
	
	public static ArrayList<Tile> getTiles()
	{
		return Tiles;
	}
}

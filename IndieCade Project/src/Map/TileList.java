package Map;

import java.util.ArrayList;

import org.newdawn.slick.Color;

import Tiles.Tile;

public class TileList
{
	public static ArrayList<Tile> Tiles;
	
	public static void init()
	{
		Tiles = new ArrayList<Tile>();
		
		Tile Air = new Tile("Air", new Color(255, 255, 255)).
				setAnimation("res/Tiles/Air").
				setColidable(false);
		Tiles.add(Air);
		
		Tile Stone = new Tile("Stone", new Color(0 / 255, 0 / 255, 0 / 255)).
				setAnimation("res/Tiles/Stone/Images").
				setColidable(true).setFriction(0.5F).
				setSound("res/Tiles/Stone/Sound/Stone Walk Final.wav");
		Tiles.add(Stone);
	}
	
	public static ArrayList<Tile> getTiles()
	{
		return Tiles;
	}
}

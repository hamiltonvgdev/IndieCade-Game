package Tiles;

import java.util.ArrayList;

import org.newdawn.slick.Color;

import Map.World;

public class TileList
{
	public static ArrayList<Tile> Tiles;
	
	public static void init()
	{
		//MAKE SURE THE COLOR ID HAS A F FOR FLOAT BEHIND IT
		
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
		
		Tile Brick = new Tile("Brick", new Color(87 / 255F, 50 / 255F, 17 / 255F)).
				setAnimation("res/Tiles/Brick/Images", 100).
				setColidable(true).setFriction(0.5F).
				setSound("res/Tiles/Stone/Sound/Stone Walk 4.wav");
		Tiles.add(Brick); 
		
		Tile GearBox = new Tile("Gear Box", new Color(204 / 255F, 204 / 255F, 204 / 255F)).
				setAnimation("res/Tiles/SpawnPraxDummy/Images", 100).
				setColidable(true).setFriction(0.5F);
		Tiles.add(GearBox);
	}
	
	public static void initPortals(World world)
	{
		PortalTile Up = new PortalTile("Up", new Color(1, 0, 0, 0.25F)).setWorld(world).
				setDirection(0, -1);
		Tiles.add(Up);

		PortalTile Left = new PortalTile("Up", new Color(0, 1, 0, 0.25F)).setWorld(world).
				setDirection(-1, 0);
		Tiles.add(Left);
		
		PortalTile Right = new PortalTile("Up", new Color(0, 0, 1, 0.25F)).setWorld(world).
				setDirection(1, 0);
		Tiles.add(Right);
		
		PortalTile Down = new PortalTile("Up", new Color(0, 0, 0, 0.25F)).setWorld(world).
				setDirection(0, 1);
		Tiles.add(Down);
	}
	
	public static ArrayList<Tile> getTiles()
	{
		return Tiles;
	}
}

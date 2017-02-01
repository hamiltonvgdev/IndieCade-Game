package Tiles;

import java.util.ArrayList;

import org.newdawn.slick.Color;

import GameBasics.Entity;
import Map.World;
import Player.Player;

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
		
		Tile AirBlock = new Tile("AirBlock", new Color(0 / 255, 0 / 255, 0 / 255)).
				setAnimation("res/Tiles/Air/", 100).
				setColidable(true).setFriction(0.5F);
		Tiles.add(AirBlock);
		
		Tile Brick = new Tile("Brick", new Color(87 / 255F, 50 / 255F, 17 / 255F)).
				setAnimation("res/Tiles/Brick/Images", 100).
				setColidable(true).setFriction(0.5F);
		Tiles.add(Brick); 
		
		Tile Wood = new Tile("Wood", new Color(168 / 255F, 127 / 255F, 90 / 255F)).
				setAnimation("res/Tiles/Wood/Images", 100).
				setColidable(true).setFriction(0.5F);
		Tiles.add(Wood);
		
	}
	
	public static void initPortals(World world)
	{
		PortalTile Up = new PortalTile("Up", world, new Color(1, 0, 0, 0.25F)).
				setDirection(0, -1);
		Tiles.add(Up);

		PortalTile Left = new PortalTile("Left", world, new Color(0, 1, 0, 0.25F)).
				setDirection(-1, 0);
		Tiles.add(Left);
		
		PortalTile Right = new PortalTile("Right", world,  new Color(0, 0, 1, 0.25F)).
				setDirection(1, 0);
		Tiles.add(Right);
		
		PortalTile Down = new PortalTile("Down", world, new Color(0, 0, 0, 0.25F)).
				setDirection(0, 1);
		Tiles.add(Down);
	}
	
	public static void initInteractTiles(Player player)
	{
		Ladder ladder = (Ladder) new Ladder("Ladder", player, new Color(153 / 255F, 116 / 255F, 82 / 255F)).
				setAnimation("res/Tiles/Ladders/Wood/Images", 100).
				setFriction(0);
		Tiles.add(ladder);
		
		///////////////////////////////////////////////////////////////////	

		SpawnTile GearBox = (SpawnTile) new SpawnTile("GearBox", 
				new Entity(player, 5, 0).setAnimationSet("res/Entities/Scarecrow/Images", 300).setDimensions(19 * 5, 23 * 5),
				100, new Color(204 / 255F, 204 / 255F, 204 / 255F)).
				setAnimation("res/Tiles/SpawnPraxDummy/Images", 100).
				setColidable(true).
				setFriction(0.5F);
		Tiles.add(GearBox);
		
		SpawnTile grid = (SpawnTile) new SpawnTile("grid",
				new Entity(player, 5, 0).setAnimationSet("res/Entities/test/Images", 300).setDimensions(19 * 5, 23 * 5),
				100, new Color(255 / 255F, 0 / 255F, 0 / 255F)).
				setAnimation("res/Tiles/Spawntest/Images", 100).
				setColidable(true).
				setFriction(0.1F);
		Tiles.add(grid);
	}
	
	public static ArrayList<Tile> getTiles()
	{
		return Tiles;
	}
}

package Tiles;

import java.util.ArrayList;

import org.newdawn.slick.Color;

import GameBasics.Entity;
import Map.World;
import Player.Player;
import Weapons.RangedWeapon;

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
				setColidable(true).setFriction(1F);
		Tiles.add(AirBlock);
		
		Tile Brick = new Tile("Brick", new Color(87 / 255F, 50 / 255F, 17 / 255F)).
				setAnimation("res/Tiles/Brick/Images", 100).
				setColidable(true).setFriction(1F);
		Tiles.add(Brick); 
		
		Tile Wood = new Tile("Wood", new Color(168 / 255F, 127 / 255F, 90 / 255F)).
				setAnimation("res/Tiles/Wood/Images", 100).
				setColidable(true).setFriction(1F);
		Tiles.add(Wood);

		Tile Stone = new Tile("Stone", new Color(105 / 255F, 105 / 255F, 105 / 255F)).
				setAnimation("res/Tiles/Stone/Images", 100).
				setColidable(true).setFriction(1F);
		Tiles.add(Stone);

		Tile dirt= new Tile("dirt", new Color(0 / 255F, 200 / 255F, 0 / 255F)).
				setAnimation("res/Tiles/Dirt/Images", 100).
				setColidable(true).setFriction(1F);
		Tiles.add(dirt);
		
	}
	
	public static void initPortals(World world)
	{
		PortalTile Up = (PortalTile) new PortalTile("Up", world, new Color(1, 1, 1, 0.25F)).
				setDirection(0, -1).
				setAnimation("res/Tiles/Air", 100);
		Tiles.add(Up);

		PortalTile Left = (PortalTile) new PortalTile("Left", world, new Color(1, 0, 0, 0.25F)).
				setDirection(-1, 0).
				setAnimation("res/Tiles/Air", 100);
		Tiles.add(Left);
		
		PortalTile Right = (PortalTile) new PortalTile("Right", world,  new Color(0, 1, 1, 0.25F)).
				setDirection(1, 0).
				setAnimation("res/Tiles/Air", 100);
		Tiles.add(Right);
		
		PortalTile Down = (PortalTile) new PortalTile("Down", world, new Color(0, 0, 0, 0.25F)).
				setDirection(0, 1).
				setAnimation("res/Tiles/Air", 100);
		Tiles.add(Down);
	}
	
	public static void initInteractTiles(Player player)
	{
		Ladder ladder = (Ladder) new Ladder("Ladder", player, new Color(153 / 255F, 116 / 255F, 82 / 255F)).
				setAnimation("res/Tiles/Ladders/Wood/Images", 100).
				setFriction(0);
		Tiles.add(ladder);
		
		///////////////////////////////////////////////////////////////////	
		
		/*Stairs WoodenStairs = (Stairs) new Stairs("Wooden Stairs", player, new Color(255 / 255F, 0 / 255F, 0 / 255F)).
				setAnimation("res/Tiles/Ladders/Wood/Images", 100).
				setFriction(0);
		Tiles.add(WoodenStairs);//Incorporate stairs*/
		
		///////////////////////////////////////////////////////////////////	
		
		ItemTile BowTile = (ItemTile) new ItemTile("Bow Tile", player, new Color(255 / 255F, 0 / 255F, 0 / 255F)).
				setItem(((RangedWeapon) new RangedWeapon("Bow", player).
						setAtkStats(5, 500, 50).setChance(50, 2, 0, 0).
						setDimensions(16 * 2.5F, 16 * 2.5F).setSprite("res/Gear/Weapons/BasicBow/Bow", 100).setDimensions(64, 64)).
						setProjectile("res/Gear/Weapons/BasicBow/Arrow", 100, 10 * 5, 10 * 5, -45)).
						setAnimation("res/Tiles/Air", 100).
						setColidable(false).setFriction(1F);
		Tiles.add(BowTile);
		
		ItemTile LBowTile = (ItemTile) new ItemTile("Legendary Bow Tile", player, new Color(0 / 255F, 0 / 255F, 255 / 255F)).
				setItem(((RangedWeapon) new RangedWeapon("Legendary Bow", player).
						setAtkStats(1, 100, 50).setChance(50, 2, 0, 0).
						setDimensions(16 * 2.5F, 16 * 2.5F).setSprite("res/Gear/Weapons/LegendBow/Bow", 100).setDimensions(64, 64)).
						setProjectile("res/Gear/Weapons/LegendBow/Arrow", 100, 10 * 5, 10 * 5, -45)).
						setAnimation("res/Tiles/Air", 100).
						setColidable(false).setFriction(1F);
		Tiles.add(LBowTile);
		
		///////////////////////////////////////////////////////////////////	

		SpawnTile GearBox = (SpawnTile) new SpawnTile("GearBox",
				new Entity(player, 10, 5, 0).setAnimationSet("res/Entities/Scarecrow/Images", 300).setDimensions(19 * 5, 23 * 5),
				4000, new Color(204 / 255F, 204 / 255F, 204 / 255F)).
				setAnimation("res/Tiles/SpawnPraxDummy/Images", 100).
				setColidable(true).
				setFriction(1F);
		Tiles.add(GearBox);
		
		SpawnTile dirtSpawn = (SpawnTile) new SpawnTile("dirtSpawn",
				new Entity(player, 15, 5, 0).setAnimationSet("res/Entities/goblin/Images/Stale", 800).setDimensions(19 * 5, 23 * 5),
				4000, new Color(0 / 255F, 255 / 255F, 0 / 255F)).
				setAnimation("res/Tiles/Dirt/Images", 100).
				setColidable(true).
				setFriction(1F);
		Tiles.add(dirtSpawn);
	}
	
	public static ArrayList<Tile> getTiles()
	{
		return Tiles;	
	}
}

package Tiles;

import java.util.ArrayList;

import org.newdawn.slick.Color;

import Enemy.Enemy;
import Enemy.RushEnemy;
import Enemy.ShootEnemy;
import GameBasics.Entity;
import Map.World;
import Player.Player;
import Projectiles.BasicProjectile;
import Projectiles.SplitShot;
import Projectiles.WaveProjectile;
import Thing.Thing;

public class TileList
{
	public static ArrayList<Tile> Tiles;
	
	public static void init()
	{
		//MAKE SURE THE COLOR ID HAS A F FOR FLOAT BEHIND ITr
		
		Tiles = new ArrayList<Tile>();
		
		Tiles.add(new Respawn());
		
		Tile AirBlock = new Tile("Air Block", new Color(0, 0, 0)).
				setAnimation("res/Tiles/Air", 100).
				setColidable(true);
		Tiles.add(AirBlock);

		Tile Stone = new Tile("Stone", new Color(105 / 255F, 105 / 255F, 105 / 255F)).
				setAnimation("res/Tiles/Stone/Images", 100).
				setColidable(false).setFriction(2F);
		Tiles.add(Stone);
		
		//Meadow----------------------------------------------------------------------------------
		
		
		//Forest----------------------------------------------------------------------------------
		Tile Leaf1 = new Tile("Leaf 1", new Color(32 / 255F, 87 / 255F, 47 / 255F)).
				setAnimation("res/Tiles/Forest/Leaf 1/", 100).
				setColidable(false).setFriction(2F); 
		Tiles.add(Leaf1);

		Tile Leaf2 = new Tile("Leaf 2", new Color(41 / 255F, 132 / 255F, 74 / 255F)).
				setAnimation("res/Tiles/Forest/Leaf 2/", 100).
				setColidable(false).setFriction(2F); 
		Tiles.add(Leaf2);

		Tile Leaf3 = new Tile("Leaf 3", new Color(41 / 255F, 156 / 255F, 99 / 255F)).
				setAnimation("res/Tiles/Forest/Leaf 3/", 100).
				setColidable(false).setFriction(2F); 
		Tiles.add(Leaf3);

		Tile Leaf4 = new Tile("Leaf 4", new Color(66 / 255F, 181 / 255F, 107 / 255F)).
				setAnimation("res/Tiles/Forest/Leaf 4/", 100).
				setColidable(false).setFriction(2F); 
		Tiles.add(Leaf4);
		
		//Desert----------------------------------------------------------------------------------
		Tile Sand1 = new Tile("Sand 1", new Color(250 / 255F, 211 / 255F, 128 / 255F)).
				setAnimation("res/Tiles/Desert/Sand 1/", 100).
				setColidable(false).setFriction(2F); 
		Tiles.add(Sand1);
		
		Tile Sand2 = new Tile("Sand 2", new Color(242 / 255F, 198 / 255F, 126 / 255F)).
				setAnimation("res/Tiles/Desert/Sand 2/", 100).
				setColidable(false).setFriction(2F); 
		Tiles.add(Sand2);
		
		Tile Sand3 = new Tile("Sand 3", new Color(194 / 255F, 151 / 255F, 105 / 255F)).
				setAnimation("res/Tiles/Desert/Sand 3/", 100).
				setColidable(false).setFriction(2F); 
		Tiles.add(Sand3);
		
		Tile Sand4 = new Tile("Sand 4", new Color(216 / 255F, 209 / 255F, 195 / 255F)).
				setAnimation("res/Tiles/Desert/Sand 4/", 100).
				setColidable(false).setFriction(2F); 
		Tiles.add(Sand4);
		
		//Ice----------------------------------------------------------------------------------
		Tile SlipIce = new Tile("Slippery Ice", new Color(198 / 255F, 218 / 255F, 255 / 255F)).
				setAnimation("res/Tiles/Ice/Slippery Ice/", 100).
				setColidable(false).setFriction(0.01F);
		Tiles.add(SlipIce); 
		
		Tile FrostPath = new Tile("Frost Path", new Color(155 / 255F, 163 / 255F, 209 / 255F)).
				setAnimation("res/Tiles/Ice/Snow Path/", 100).
				setColidable(false).setFriction(2F);
		Tiles.add(FrostPath); 
		
		Tile Ice = new Tile("Ice", new Color(143 / 255F, 173 / 255F, 243 / 255F)).
				setAnimation("res/Tiles/Ice/Ice/", 100).
				setColidable(false).setFriction(1F);
		Tiles.add(Ice);
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
				setAnimation("res/Tiles/Air", 200);
		Tiles.add(Right);
		
		PortalTile Down = (PortalTile) new PortalTile("Down", world, new Color(0, 0, 0, 0.25F)).
				setDirection(0, 1).
				setAnimation("res/Tiles/Air", 100).
				addThing(new Thing("res/Things/Portal", 200).setDimension(13 * 5, 11 * 5, 0));
		Tiles.add(Down);
		
		PortalTile UpLeft = (PortalTile) new PortalTile("Up Left", world, new Color(0, 1, 0, 0.25F)).
				setDirection(1, -1).
				setAnimation("res/Tiles/Air", 100);
		Tiles.add(UpLeft);
		
		PortalTile  DownRight = (PortalTile) new PortalTile("Down Right", world, new Color(1, 0, 1, 0.25F)).
				setDirection(-1, 1).
				setAnimation("res/Tiles/Air", 100);
		Tiles.add(DownRight);
		
		PortalTile  UpRight = (PortalTile) new PortalTile("Up Right", world, new Color(0, 0, 1, 0.25F)).
				setDirection(1, 1).
				setAnimation("res/Tiles/Air", 100);
		Tiles.add(UpRight);
		
		PortalTile DownLeft = (PortalTile) new PortalTile("Down Left", world, new Color(1, 1, 0, 0.25F)).
				setDirection(-1, -1).
				setAnimation("res/Tiles/Air", 100);
		Tiles.add(DownLeft);
	}
	
	public static void initInteractTiles(Player player)
	{
		
	}
	
	public static ArrayList<Tile> getTiles()
	{
		return Tiles;	
	}
}

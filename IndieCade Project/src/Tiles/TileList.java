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
		//MAKE SURE THE COLOR ID HAS A F FOR FLOAT BEHIND IT
		
		Tiles = new ArrayList<Tile>();
		
		Tile AirBlock = new Tile("Air Block", new Color(0, 0, 0)).
				setAnimation("res/Tiles/Air", 100).
				setColidable(true);
		Tiles.add(AirBlock);

		Tile Stone = new Tile("Stone", new Color(105 / 255F, 105 / 255F, 105 / 255F)).
				setAnimation("res/Tiles/Stone/Images", 100).
				setColidable(false).setFriction(2F);
		Tiles.add(Stone);
		
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

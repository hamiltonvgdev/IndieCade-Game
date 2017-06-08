package Tiles;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;

import Bosses.Boss;
import Enemy.Enemy;
import Enemy.Guardian;
import Enemy.PatrolShootEnemy;
import Enemy.RushEnemy;
import Enemy.ShieldEnemy;
import Enemy.ShootEnemy;
import Form.Triangle;
import GameBasics.Entity;
import Geo.M;
import Map.World;
import Player.Player;
import Projectiles.BasicProjectile;
import Projectiles.SplitShot;
import Projectiles.WaveProjectile;
import Thing.Thing;
import Thing.Wall;

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
		Tile Meadow1 = new Tile("Meadow 1", new Color(255 / 255F, 232 / 255F, 6 / 255F)).
				setAnimation("res/Tiles/Meadow/Meadow 1/", 100).
				setColidable(false).setFriction(2F); 
		Tiles.add(Meadow1);
		
		Tile Meadow2 = new Tile("Meadow 1", new Color(194 / 255F, 182 / 255F, 65 / 255F)).
				setAnimation("res/Tiles/Meadow/Meadow 2/", 100).
				setColidable(false).setFriction(2F); 
		Tiles.add(Meadow2);
		
		Tile Meadow3 = new Tile("Meadow 1", new Color(196 / 255F, 170 / 255F, 25 / 255F)).
				setAnimation("res/Tiles/Meadow/Meadow 3/", 100).
				setColidable(false).setFriction(2F); 
		Tiles.add(Meadow3);
		
		Tile ShortGrass = new ThingTile("Short Grass", new Color(213 / 255F, 198 / 255F, 50 / 255F), 
				new Thing("res/Things/Short Grass/", 100).setDimension(48, 48, 0).setCollidable(false, false)).
				setAnimation("res/Tiles/Meadow/Meadow 3/", 100).
				setColidable(false).setFriction(2F);
		Tiles.add(ShortGrass);
		
		Tile TallGrass = new ThingTile("Tall Grass", new Color(255 / 255F, 245 / 255F, 151 / 255F), 
				new Thing("res/Things/Tall Grass/", 100).setDimension(31 * 2, 55 * 2, 0).setCollidable(true, false)).
				setAnimation("res/Tiles/Meadow/Meadow 3/", 100).
				setColidable(false).setFriction(2F);
		Tiles.add(TallGrass);
		
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
		
		Tile BushLeaf = new ThingTile("Bush Leaf", new Color(45 / 255F, 159 / 255F, 23 / 255F), 
				new Thing("res/Things/Bush", 100).setDimension(48, 48, 0).setCollidable(true, false)).
				setAnimation("res/Tiles/Forest/Leaf 4/", 100).
				setColidable(false).setFriction(2F);
		Tiles.add(BushLeaf);
		
		Tile StumpLeaf = new ThingTile("Stump Leaf", new Color(176 / 255F, 121 / 255F, 89 / 255F), 
				new Wall("res/Things/Stump", 100).setDimension(64, 64, 0).setCollidable(true, false)).
				setAnimation("res/Tiles/Forest/Leaf 4/", 100).
				setColidable(false).setFriction(2F);
		Tiles.add(StumpLeaf);
		
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
		Tile Snow1 = new Tile("Snow 1", new Color(155 / 255F, 163 / 255F, 209 / 255F)).
				setAnimation("res/Tiles/Ice/Snow 1/", 100).
				setColidable(false).setFriction(2F);
		Tiles.add(Snow1); 
		
		Tile Snow2 = new Tile("Snow 2", new Color(190 / 255F, 230 / 255F, 227 / 255F)).
				setAnimation("res/Tiles/Ice/Snow 2/", 100).
				setColidable(false).setFriction(2F);
		Tiles.add(Snow2); 
		
		Tile Ice = new Tile("Ice", new Color(143 / 255F, 173 / 255F, 243 / 255F)).
				setAnimation("res/Tiles/Ice/Ice/", 100).
				setColidable(false).setFriction(1F);
		Tiles.add(Ice);
		
		Tile SlipIce = new Tile("Slippery Ice", new Color(198 / 255F, 218 / 255F, 255 / 255F)).
				setAnimation("res/Tiles/Ice/Slippery Ice/", 100).
				setColidable(false).setFriction(0.01F);
		Tiles.add(SlipIce); 
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
				setDirection(-1, -1).
				setAnimation("res/Tiles/Air", 100);
		Tiles.add(UpLeft);
		
		PortalTile  DownRight = (PortalTile) new PortalTile("Down Right", world, new Color(1, 0, 1, 0.25F)).
				setDirection(1, 1).
				setAnimation("res/Tiles/Air", 100);
		Tiles.add(DownRight);
		
		PortalTile  UpRight = (PortalTile) new PortalTile("Up Right", world, new Color(0, 0, 1, 0.25F)).
				setDirection(1, -1).
				setAnimation("res/Tiles/Air", 100);
		Tiles.add(UpRight);
		
		PortalTile DownLeft = (PortalTile) new PortalTile("Down Left", world, new Color(1, 1, 0, 0.25F)).
				setDirection(-1, 1).
				setAnimation("res/Tiles/Air", 100);
		Tiles.add(DownLeft);
	}
	
	public static void initInteractTiles(Player player)
	{
		SpawnTile TrigShoot = (SpawnTile) new SpawnTile("TrigShoot", 
				
				new PatrolShootEnemy("TrigShoot", player, 30, 10).setWanderCD(500).
				setProjectile(new BasicProjectile(player, 1, 0).setDimensions(32, 32, 0).
						setSprite("res/Entities/TrigShoot/Shot", 100), 10).
				setRange(500).setAtkSpeed(100).setDimensions(64, 64).setMove(10, 2).
				setAnimationSet("res/Entities/TrigShoot/Body", 100).setMove(10, 2).setAtkSpeed(1000)
				
				, 7500, new Color(198 / 255F, 210 / 255F, 0 / 255F)).
				setAnimation("res/Tiles/Desert/Sand 1/", 100);
		Tiles.add(TrigShoot);
		
		SpawnTile TrigMelee = (SpawnTile) new SpawnTile("TrigMelee", 
					
				new RushEnemy("TrigMelee", player, 40, 10).
				setRange(500).setAtkSpeed(100).setDimensions(64, 64).setMove(10, 2).
				setAnimationSet("res/Entities/TrigMelee", 100)
				
				, 7500, new Color(224 / 255F, 158 / 255F, 0 / 255F)).
				setAnimation("res/Tiles/Desert/Sand 1/", 100);
		Tiles.add(TrigMelee);
		
		SingleSpawnTile TrigBoss = (SingleSpawnTile) new SingleSpawnTile("Boss", 
				
				new Bosses.TrigBoss(player)
				
				, 0, new Color(250 / 255F, 250 / 255F, 250 / 255F)).
				setAnimation("res/Tiles/Desert/Sand 1/", 100);
		Tiles.add(TrigBoss);
		
		SpawnTile SquareShield = (SpawnTile) new SpawnTile("SquareShield", 
				
				new Guardian("Square Shield", player, 40, 10).setShieldStats(100, 75, 2).
				setShieldSprite("res/Entities/SquareShield/Shield", 100).
				setShieldDimensions(64, 160).
				setRange(500).setAtkSpeed(100).setDimensions(64, 64).setMove(10, 2).
				setAnimationSet("res/Entities/SquareShield/Body", 100)
				
				, 7500, new Color(139 / 255F, 90 / 255F, 65 / 255F)).
				setAnimation("res/Tiles/Desert/Sand 1/", 100);
		Tiles.add(SquareShield);
		
		SingleSpawnTile SquareBoss = (SingleSpawnTile) new SingleSpawnTile("Boss", 
				
				new Bosses.SquareBoss(player)
				
				, 0, new Color(200 / 255F, 200 / 255F, 200 / 255F)).
				setAnimation("res/Tiles/Desert/Sand 1/", 100);
		Tiles.add(SquareBoss);
	}
	
	public static ArrayList<Tile> getTiles()
	{
		return Tiles;	
	}
}

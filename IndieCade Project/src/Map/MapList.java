package Map;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import GameBasics.BasicNPC;
import NPCs.AudioNPC;
import NPCs.ImageNPC;
import Player.Player;
import Tiles.Tile;

public class MapList 
{
	public static ArrayList<Map> Maps;

	public static void init(Player player) 
	{
		Maps = new ArrayList<Map>();
		
		try {
			Map TrainingHall = new Map(player, new Image("res/Maps/Training Hall/Training Hall.png"),
					new Image("res/Maps/Training Hall/Training Hall Back.png"), 
					new Color(8 / 255F, 10 / 255F, 0 / 255F)).
					setBackGroundMusic("res/Maps/Training Hall/beat man 1.wav").
					spawnNPC(
							new AudioNPC("res/NPC/Test/Sound/Asian Voice File.wav", player, "derp", 100).
							setIdleImage("res/NPC/Test/Image", 100).setDimensions(50, 100, 0).
							setCoordinates(300, 300).
							setPhrase("Please listen to my music!!"));
			Maps.add(TrainingHall);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Map> getMaps()
	{
		return Maps;
	}
}

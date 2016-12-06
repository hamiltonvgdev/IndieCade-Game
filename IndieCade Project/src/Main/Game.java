package Main;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Map.MapList;
import Map.TileList;
import Map.World;
import Player.Player;

public class Game extends BasicGameState
{
	Player player;
	World world;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		TileList.init();
		MapList.init(player);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{	
		player.render(g);
		world.render(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		player.update();
		
		world.update();
	}

	@Override
	public int getID() 
	{
		return 2;
	}

	public void loadFiles(String ref)
	{
		player = (Player) Load.Load.load(ref + "/Player");
		
		world = (World) Load.Load.load(ref + "/World");
	}
	
	public void newGame()
	{
		player = new Player(Config.WIDTH / 2, Config.HEIGHT / 2);
		
		world = new World(player);
	}
}

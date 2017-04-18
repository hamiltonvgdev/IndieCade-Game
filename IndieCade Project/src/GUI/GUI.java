package GUI;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import Main.Config;
import Map.World;
import Menus.PauseMenu;
import Player.Player;
import Render.AnimationSet;
import Util.Button;

public class GUI 
{
	//Graphical User Interface. It handles all the buttons displayed when the player is actually in the game, 
	//but not the associated menus
	
	PauseMenu Pause;
	Button pause;
	//HealthBar health;
	Abilities abilities;
	
	public GUI()
	{
		pause = new Button("", Config.WIDTH - 16, 16, 0).setDimensions(32, 32).
				setImage(new AnimationSet("res/Buttons/GUI/Pause/Idle", 100), 
						new AnimationSet("res/Buttons/GUI/Pause/Select", 100));
		
		Pause = new PauseMenu();
	}
	
	public void init(Player player, World world)
	{
		//health = new HealthBar(player);
		abilities = new Abilities(player);
	}
	
	public void update(StateBasedGame sbg, World world, Player player)
	{
		Pause.update(sbg, world, player);
		
		if(!Pause.pausing)
		{
			pause.update();
		}
		
		if(pause.clicked)
		{
			Pause.pause(world, player);
		}
	}
	
	public void render(Graphics g, Player player) throws SlickException
	{
		Pause.render(g, player);
		
		pause.render(g);
		
		//health.render(g);
		
		abilities.render(g);
	}
}

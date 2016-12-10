package GUI;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Main.Config;
import Map.World;
import Menus.PauseMenu;
import Player.Player;
import Render.AnimationSet;
import Render.BasicImage;
import Util.Button;

public class GUI 
{
	BasicImage HealthBar;
	BasicImage Stat;
	
	PauseMenu Pause;
	Button pause;
	Button Inventory;
	
	public GUI()
	{
		Pause = new PauseMenu();
		pause = new Button("", Config.WIDTH - 16, 16, 0).setDimensions(32, 32).
				setImage(new AnimationSet("res/Buttons/GUI/Pause/Idle", 0), 
						new AnimationSet("res/Buttons/GUI/Pause/Selected", 0));
	}
	
	public void update(World world, Player player)
	{
		pause.update();
		
		if(pause.clicked)
		{
			Pause.pause(world, player);
		}
	}
	
	public void render(Graphics g) throws SlickException
	{
		pause.render(g);
	}
}

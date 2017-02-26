package GUI;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import Main.Config;
import Map.World;
import Menus.InventoryMenu;
import Menus.PauseMenu;
import Player.Player;
import Render.AnimationSet;
import Util.Button;

public class GUI 
{
	PauseMenu Pause;
	InventoryMenu Inventory;
	Button pause;
	Button Inv;
	
	public GUI()
	{
		pause = new Button("", Config.WIDTH - 16, 16, 0).setDimensions(32, 32).
				setImage(new AnimationSet("res/Buttons/GUI/Pause/Idle", 100), 
						new AnimationSet("res/Buttons/GUI/Pause/Select", 100));
		
		Inv = new Button("", Config.WIDTH - 56, 16, 0).setDimensions(32, 32).
				setImage(new AnimationSet("res/Buttons/GUI/Inventory", 100), null);
		
		Pause = new PauseMenu();
		Inventory = new InventoryMenu();
	}
	
	public void update(StateBasedGame sbg, World world, Player player)
	{
		Pause.update(sbg, world, player);
		Inventory.update(world, player);
		
		if(!Pause.pausing && !Inventory.active)
		{
			pause.update();
			Inv.update();
		}
		
		if(pause.clicked)
		{
			Pause.pause(world, player);
		}
		
		if(Inv.clicked)
		{
			Inventory.activate(world, player);
		}
	}
	
	public void render(Graphics g, Player player) throws SlickException
	{
		Pause.render(g, player);
		Inventory.render(g, player);
		
		pause.render(g);
		Inv.render(g);
	}
}

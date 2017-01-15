package Menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import Main.Config;
import Map.World;
import Player.Player;
import Render.AnimationSet;
import Util.Button;

public class PauseMenu extends BasicMenu
{
	boolean pausing;
	boolean saving;
	boolean hoarding;
	
	Button resume;
	Button inventory;
	Button save;
	Button exit;
	
	Button save1;
	Button save2;
	Button save3;
	Button back;
	
	InventoryMenu Invent;
	Button InvBack;
	
	public PauseMenu()
	{
		pausing = false;
		saving = false;
		hoarding = false;
		
		resume = new Button("Resume", Config.WIDTH / 2, Config.HEIGHT / 2 - 150, 0).setDimensions(138, 50).
				setImage(new AnimationSet("res/Buttons/Base/Idle", 100), new AnimationSet("res/Buttons/Base/Select", 100));
		
		inventory = new Button("Inventory", Config.WIDTH / 2, Config.HEIGHT / 2 - 50, 0).setDimensions(138, 50).
				setImage(new AnimationSet("res/Buttons/Base/Idle", 100), new AnimationSet("res/Buttons/Base/Select", 100));
		
		save = new Button("Save", Config.WIDTH / 2, Config.HEIGHT / 2 + 50, 0).setDimensions(138, 50).
				setImage(new AnimationSet("res/Buttons/Base/Idle", 100), new AnimationSet("res/Buttons/Base/Select", 100));
		
		exit = new Button("Exit", Config.WIDTH / 2, Config.HEIGHT / 2 + 150, 0).setDimensions(138, 50).
				setImage(new AnimationSet("res/Buttons/Base/Idle", 100), new AnimationSet("res/Buttons/Base/Select", 100));
		
		save1 = new Button("Save to Save 1", Config.WIDTH / 2, Config.HEIGHT / 4, 1).setDimensions(150, 50).
				setImage(new AnimationSet("res/Buttons/Base/Idle", 100), new AnimationSet("res/Buttons/Base/Select", 100));
		save2 = new Button("Save to Save 2", Config.WIDTH / 4 , Config.HEIGHT / 2, 1).setDimensions(150, 50).
				setImage(new AnimationSet("res/Buttons/Base/Idle", 100), new AnimationSet("res/Buttons/Base/Select", 100));
		save3 = new Button("Save to Save 3", Config.WIDTH / 4 * 3, Config.HEIGHT / 2, 1).setDimensions(150, 50).
				setImage(new AnimationSet("res/Buttons/Base/Idle", 100), new AnimationSet("res/Buttons/Base/Select", 100));
		back = new Button("Back", Config.WIDTH / 2, Config.HEIGHT / 4 * 3, 1).setDimensions(150, 50).
				setImage(new AnimationSet("res/Buttons/Base/Idle", 100), new AnimationSet("res/Buttons/Base/Select", 100));
		
		Invent = new InventoryMenu();
		InvBack = new Button("Back", Config.WIDTH - 75, 167, 1).setDimensions(150, 50).
				setImage(new AnimationSet("res/Buttons/Base/Idle", 100), new AnimationSet("res/Buttons/Base/Select", 100));
	}

	public void update(StateBasedGame sbg, World world, Player player)
	{
		if(pausing)
		{
			if(saving)
			{
				save1.update();
				save2.update();
				save3.update();
				back.update();
				
				if(save1.clicked)
				{
					Save.Save.save("saves/Save 1/Player.ply", player);
					Save.Save.save("saves/Save 1/World.wrld", world);
				}
				
				if(save2.clicked)
				{
					Save.Save.save("saves/Save 2/Player.ply", player);
					Save.Save.save("saves/Save 2/World.wrld", world);
				}
				
				if(save3.clicked)
				{
					Save.Save.save("saves/Save 3/Player.ply", player);
					Save.Save.save("saves/Save 3/World.wrld", world);
				}
				
				if(back.clicked)
				{
					saving = false;
				}
			}else if(hoarding)
			{
				Invent.update(player.getInventory());
				InvBack.update();
				
				if(InvBack.clicked)
				{
					hoarding = false;
				}
			}else
			{
				resume.update();
				save.update();
				exit.update();
				inventory.update();
				
				if(resume.clicked)
				{
					unpause(world, player);
				}
				
				if(inventory.clicked)
				{
					hoarding = true;
				}
				
				if(save.clicked)
				{
					saving = true;
				}
				
				if(exit.clicked)
				{
					pausing = false;
					sbg.enterState(1);
				}
			}
		}
	}

	public void render(Graphics g, Player player) throws SlickException 
	{
		if(pausing)
		{
			Rectangle rec = new Rectangle(0, 0, Config.WIDTH, Config.HEIGHT);
			g.setColor(new Color(1f, 1f, 1f, 0.5f));
			g.fillRect(0, 0, Config.WIDTH, Config.HEIGHT);
			
			g.draw(rec);
			
			if(saving)
			{
				save1.render(g);
				save2.render(g);
				save3.render(g);
				back.render(g);
			}else if(hoarding)
			{
				Invent.render(g, player.getInventory());
				InvBack.render(g);
			}else	
			{
				resume.render(g);
				inventory.render(g);
				save.render(g);
				exit.render(g);
				g.setBackground(Color.black);
			}
		}
	}
	
	public void pause(World world, Player player)
	{
		pausing = true;
		
		world.pause();
		player.pause();
	}
	
	public void unpause(World world, Player player)
	{
		pausing = false;
		
		world.unpause();
		player.unpause();
	}

	
	//Useless
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		
	}
}

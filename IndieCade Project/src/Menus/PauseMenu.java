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
	public boolean pausing;
	
	Button resume;
	Button exit;
	
	ConfirmMenu confirm;
	
	public PauseMenu()
	{
		pausing = false;
		
		resume = new Button("Resume", Config.WIDTH / 2, Config.HEIGHT / 2 - 150, 0).setDimensions(138, 50).
				setImage(new AnimationSet("res/Buttons/Base/Idle", 100), new AnimationSet("res/Buttons/Base/Select", 100));
		
		exit = new Button("Exit", Config.WIDTH / 2, Config.HEIGHT / 2 + 150, 0).setDimensions(138, 50).
				setImage(new AnimationSet("res/Buttons/Base/Idle", 100), new AnimationSet("res/Buttons/Base/Select", 100));
		
		confirm = new ConfirmMenu();
	}

	public void update(StateBasedGame sbg, World world, Player player)
	{
		if(pausing && !confirm.active)
		{
			resume.update();
			exit.update();
			
			if(resume.clicked)
			{
				unpause(world, player);
			}
				
			if(exit.clicked)
			{
				confirm.active = true;
			}
		}
		
		confirm.update();
		
		if(confirm.getConfirm())
		{
			pausing = false;
			sbg.enterState(1);
		}
	}

	public void render(Graphics g, Player player) throws SlickException 
	{
		if(pausing)
		{
			Rectangle rec = new Rectangle(0, 0, Config.WIDTH, Config.HEIGHT);
			g.setColor(new Color(1f, 1f, 1f, 0.75f));
			g.fillRect(0, 0, Config.WIDTH, Config.HEIGHT);
			
			g.draw(rec);
			
			confirm.render(g);
			if(!confirm.active)
			{
				resume.render(g);
				exit.render(g);
			}
		}else
		{
			g.setBackground(Color.black);
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

	public void reset(World world, Player player)
	{
		unpause(world, player);
		
		confirm.confirmed = false;
		confirm.active = false;
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

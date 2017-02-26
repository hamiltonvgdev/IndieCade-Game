package Menus;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import GameBasics.Item;
import Geo.Quad;
import Main.Config;
import Map.World;
import Player.Player;
import Render.AnimationSet;
import Render.BasicImage;
import Util.Button;

public class InventoryMenu
{
	Image grid;
	ArrayList<Quad> Hitboxes;
	Button Back;
	
	public boolean active;
	
	public InventoryMenu()
	{
		Hitboxes = new ArrayList<Quad>();
		try {
			grid = new Image("res/Misc/Grid.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int y = 3; y < Config.HEIGHT / 64; y ++)
		{
			for(int x = 0; x < Config.WIDTH / 64; x ++)
			{
				Hitboxes.add(new Quad(x * 64 + 32, y * 64 + 32, 64, 64));
			}
		}
		
		active = false;
		
		Back = new Button("Back", Config.WIDTH - 75, 64 * 3 - 25, 1).setDimensions(150, 50).
				setImage(new AnimationSet("res/Buttons/Base/Idle", 100), 
						new AnimationSet("res/Buttons/Base/Select", 100));
	}
	
	public void update(World world, Player player)
	{
		if(active)
		{
			Back.update();
			
			if(Back.clicked)
			{
				deactivate(world, player);
			}
			
			for(int i = 0; i < Hitboxes.size(); i ++)
			{
				if(i < player.getInventory().size())
				{
					if(Hitboxes.get(i).checkPoint(Mouse.getX(), Config.HEIGHT - Mouse.getY()) &&
							player.getInput().isMouseButtonDown(player.getInput().MOUSE_LEFT_BUTTON))
					{
						player.getInventory().get(i).equip();
						break;
					}
				}else
				{
					break;
				}
			}
		}
	}

	public void render(Graphics g, Player player) throws SlickException
	{
		if(active)
		{
			for(int y = 3; y < Config.HEIGHT / 64; y ++)
			{
				for(int x = 0; x < Config.WIDTH / 64; x ++)
				{
					g.drawImage(grid, x * 64, y * 64);
				}
			}
			
			for(int i = 0; i < player.getInventory().size(); i ++)
			{
				player.getInventory().get(i).getSprite().render((i % (Config.WIDTH / 64)) * 64 + 32, 
						((i) / (Config.HEIGHT / 64) + 3) * 64 + 32,
						64, 64, 0, g);
			}
			
			for(int i = 0; i < Hitboxes.size(); i ++)
			{
				if(Hitboxes.get(i).checkPoint(Mouse.getX(), Config.HEIGHT - Mouse.getY()))
				{
					Hitboxes.get(i).render(g);
				}
			}
			
			Back.render(g);
		} 
	}
	
	public void activate(World world, Player player)
	{
		active = true;
		
		world.pause();
		player.pause();
	}
	
	private void deactivate(World world, Player player) 
	{
		active = false;
		
		world.unpause();
		player.unpause();
		
	}
}

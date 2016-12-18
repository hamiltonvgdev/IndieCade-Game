package Menus;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import GameBasics.Item;
import Main.Config;
import Render.BasicImage;

public class InventoryMenu
{
	Image grid;
	
	public InventoryMenu()
	{
		try {
			grid = new Image("res/Misc/Grid.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(ArrayList<Item> inventory)
	{
		
	}
	
	public void render(Graphics g, ArrayList<Item> inventory)
	{
		for(int y = 3; y < Config.HEIGHT / 64; y ++)
		{
			for(int x = 0; x < Config.WIDTH / 64; x ++)
			{
				g.drawImage(grid, x * 64, y * 64);
			}
		}
	}
}

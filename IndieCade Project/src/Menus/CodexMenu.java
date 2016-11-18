package Menus;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import Main.MainMenu;
import Util.Button;

public class CodexMenu extends BasicMenu
{
	MainMenu menu;
	
	ArrayList<CodexItemEntry> Items;
	ArrayList<CodexMobEntry> Mobs;
	int index;
	
	int state;
	
	Button Item;
	Button Mob;
	
	Button Exit;
	
	public CodexMenu(MainMenu menu)
	{
		this.menu = menu;
		
		index = 0;
		state = 0;
	}
	
	@Override
	public void update() 
	{
		if(state == 0)
		{
			
			
			Exit.update();
			
			if(Exit.clicked)
			{
				menu.setIndex(0);
			}
		}else if(state == 1)
		{
			Items.get(index).update();
		}else if(state == 2)
		{
			Mobs.get(index).update();
		}
	}

	@Override
	public void render(Graphics g) throws SlickException 
	{
		if(state == 0)
		{
			Item.render(g);
			Mob.render(g);
		}else if(state == 1)
		{
			Items.get(index).render(g);
		}else if(state == 2)
		{
			Mobs.get(index).render(g);
		}
		
		Exit.render(g);
	}
	
	public void addMobEntry(CodexMobEntry mob)
	{
		Mobs.add(mob);
	}
	
	public void addItemEntry(CodexItemEntry item)
	{
		Items.add(item);
	}
	
	public void changeIndex(int index)
	{
		this.index += index;
	}

}

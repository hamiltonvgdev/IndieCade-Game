package Menus;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import Main.Config;
import Main.MainMenu;
import Render.AnimationSet;
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
		
		Item = new Button("Item", Config.WIDTH / 2 - 100, Config.HEIGHT / 2, 0);
		Item = Item.setDimensions(100, 50).setImage(new AnimationSet("res/Buttons/Test", 100), null);
		
		Mob = new Button("Enemies",Config.WIDTH / 2 + 100, Config.HEIGHT / 2, 0);
		Mob = Mob.setDimensions(100, 50).setImage(new AnimationSet("res/Buttons/Test", 100), null);
		
		Exit = new Button("Exit", Config.WIDTH / 2, Config.HEIGHT - 25, 0);
		Exit = Exit.setDimensions(100, 50).setImage(new AnimationSet("res/Buttons/Test", 100), null);	
		
		Items = new ArrayList<CodexItemEntry>();
		CodexItemEntry ItemDef = new CodexItemEntry(this);
		Items.add(ItemDef);
		for(int i = 0; i < Items.size(); i ++)
		{
			Items.get(i).setID(i);
		}
		
		Mobs = new ArrayList<CodexMobEntry>();
		CodexMobEntry MobDef = new CodexMobEntry(this);
		Mobs.add(MobDef);
		for(int i = 0; i < Mobs.size(); i ++)
		{
			Mobs.get(i).setID(i);
		}
	}
	
	@Override
	public void update() 
	{
		if(state == 0)
		{
			
			Item.update();
			Mob.update();
			
			if(Item.clicked)
			{
				state = 1;
			}
			
			if(Mob.clicked)
			{
				state = 2;
			}
			
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
		
		Exit.update();
		
		if(Exit.clicked)
		{
			if(state == 0)
			{
				menu.setIndex(0);
			}else
			{
				state = 0;
			}
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

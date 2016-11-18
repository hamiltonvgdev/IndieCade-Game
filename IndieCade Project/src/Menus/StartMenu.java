package Menus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Main.MainMenu;
import Util.Button;

public class StartMenu extends BasicMenu
{
	MainMenu menu;
	GameContainer gc;
	
	Button Play;
	Button Codex;
	Button Exit;
	
	public StartMenu(GameContainer gc, MainMenu menu)
	{
		this.menu = menu;
		this.gc = gc;
	}
	
	public void update()
	{
		Play.update();
		Codex.update();
		Exit.update();
		
		if(Play.clicked)
		{
			menu.setIndex(1);
		}else if(Codex.clicked)
		{
			menu.setIndex(2);
		}else if(Exit.clicked)
		{
			gc.exit();
		}
	}
	
	public void render(Graphics g) throws SlickException
	{
		Play.render(g);
		Codex.render(g);
		Exit.render(g);
	}
}

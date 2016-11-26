package Menus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Main.Config;
import Main.MainMenu;
import Render.AnimationSet;
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
		
		Play = new Button("Play", Config.WIDTH / 2, Config.HEIGHT / 2, 0);
		Play = Play.setDimensions(100, 50).setImage(new AnimationSet("res/Buttons/Test", 100), null);	
		
		Codex = new Button("Codex",Config.WIDTH / 2, Config.HEIGHT / 2 + 75, 0);
		Codex = Codex.setDimensions(100, 50).setImage(new AnimationSet("res/Buttons/Test", 100), null);	
		
		Exit = new Button("Exit", Config.WIDTH / 2, Config.HEIGHT / 2 + 150, 0);
		Exit = Exit.setDimensions(100, 50).setImage(new AnimationSet("res/Buttons/Test", 100), null);	
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

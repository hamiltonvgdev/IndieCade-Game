package Menus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import Main.Config;
import Main.Game;
import Main.MainMenu;
import Render.AnimationSet;
import Util.Button;

public class StartMenu extends BasicMenu
{
	MainMenu menu;
	GameContainer gc;
	Game game;
	StateBasedGame sbg;
	
	Button Play;
	Button Exit;
	
	public StartMenu(GameContainer gc, Game game, StateBasedGame sbg, MainMenu menu)
	{
		this.menu = menu;
		this.gc = gc;
		
		this.game = game;
		this.sbg = sbg;
		
		Play = new Button("Play", Config.WIDTH / 2, Config.HEIGHT / 2, 0);
		Play = Play.setDimensions(150, 100).setImage(new AnimationSet("res/Buttons/Base/Idle", 100), new AnimationSet("res/Buttons/Base/Select", 100));
		
		Exit = new Button("Exit", Config.WIDTH / 2, Config.HEIGHT / 2 + 150, 0);
		Exit = Exit.setDimensions(150, 100).setImage(new AnimationSet("res/Buttons/Base/Idle", 100), new AnimationSet("res/Buttons/Base/Select", 100));
	}
	
	public void update()
	{
		Play.update();
		Exit.update();
		
		if(Play.clicked)
		{
			game.newGame();
			sbg.enterState(2);
		}else if(Exit.clicked)
		{
			gc.exit();
		}
	}
	
	public void render(Graphics g) throws SlickException
	{
		Play.render(g);
		Exit.render(g);
	}
}

package Menus;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import Main.Config;
import Main.Game;
import Main.MainMenu;
import Render.AnimationSet;
import Util.Button;

public class PlayMenu extends BasicMenu
{
	Button save1;
	Button save2;
	Button save3;
	Button newGame;
	Button exit;
	
	MainMenu menu;
	Game game;
	StateBasedGame sbg;
	
	public PlayMenu(MainMenu menu, Game game, StateBasedGame sbg)
	{
		save1 = new Button("Load Save 1", Config.WIDTH / 2, Config.HEIGHT / 4, 1);
		save1 = save1.setDimensions(150, 50).setImage(new AnimationSet("res/Buttons/Test", 100), null);
		save2 = new Button("Load Save 2", Config.WIDTH / 4 , Config.HEIGHT / 2, 1);
		save2 = save2.setDimensions(150, 50).setImage(new AnimationSet("res/Buttons/Test", 100), null);
		save3 = new Button("Load Save 3", Config.WIDTH / 4 * 3, Config.HEIGHT / 2, 1);
		save3 = save3.setDimensions(150, 50).setImage(new AnimationSet("res/Buttons/Test", 100), null);
		newGame = new Button("New Game", Config.WIDTH / 2, Config.HEIGHT / 4 * 3, 1);
		newGame = newGame.setDimensions(150, 50).setImage(new AnimationSet("res/Buttons/Test", 100), null);
		exit = new Button("Exit", Config.WIDTH / 2, Config.HEIGHT - 30, 1);
		exit = exit.setDimensions(100, 50).setImage(new AnimationSet("res/Buttons/Test", 100), null);
		
		this.menu = menu;
		this.game = game;
		this.sbg = sbg;
	}
	
	@Override
	public void update()
	{
		save1.update();
		save2.update();
		save3.update();
		newGame.update();
		exit.update();
		
		if(save1.clicked)
		{
			game.loadFiles("saves/Save 1/");
			sbg.enterState(2);
		}
		
		if(save2.clicked)
		{
			game.loadFiles("saves/Save 2/");
			sbg.enterState(2);
		}
		
		if(save3.clicked)
		{
			game.loadFiles("saves/Save 3/");
			sbg.enterState(2);
		}
		
		if(newGame.clicked)
		{
			game.newGame();
			sbg.enterState(2);
		}
		
		if(exit.clicked)
		{
			menu.setIndex(0);
		}
	}

	@Override
	public void render(Graphics g) throws SlickException 
	{
		save1.render(g);
		save2.render(g);
		save3.render(g);
		newGame.render(g);
		exit.render(g);
	}
	
}

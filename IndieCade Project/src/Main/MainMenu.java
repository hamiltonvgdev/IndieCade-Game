package Main;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Menus.BasicMenu;
import Menus.CodexMenu;
import Menus.PlayMenu;
import Menus.StartMenu;

public class MainMenu extends BasicGameState
{
	ArrayList<BasicMenu> Menus;
	volatile int index;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		Menus = new ArrayList<BasicMenu>();
		Menus.add(new StartMenu(gc, this));
		Menus.add(new PlayMenu(this, (Game) sbg.getState(2), sbg));
		Menus.add(new CodexMenu(this));
		
		index = 0;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		Menus.get(index).render(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		Menus.get(index).update();
	}

	@Override
	public int getID() 
	{
		return 1;
	}
	
	public void setIndex(int index)
	{
		this.index = index;
	}
	
}

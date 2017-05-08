package Main;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Menus.BasicMenu;
import Menus.StartMenu;

public class MainMenu extends BasicGameState
{
	ArrayList<BasicMenu> Menus;
	volatile int index;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		Menus = new ArrayList<BasicMenu>();
		Menus.add(new StartMenu(gc,(Game) sbg.getState(2), sbg, this));
		
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
	
	public ArrayList<BasicMenu> getMenus()
	{
		return Menus;
	}
	
	public void setIndex(int index)
	{
		this.index = index;
	}
	
}

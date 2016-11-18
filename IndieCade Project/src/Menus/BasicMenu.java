package Menus;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class BasicMenu 
{
	public BasicMenu()
	{
		
	}
	
	public abstract void update();
	public abstract void render(Graphics g) throws SlickException;
}

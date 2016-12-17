package Util;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Geo.Quad;
import Main.Config;
import Render.AnimationSet;

public class Button 
{
	AnimationSet Normal;
	AnimationSet Selected;
	String Phrase;
	
	float x;
	float y;
	float width;
	float height;
	
	Quad hitbox;
	public boolean clicked;
	int delay;
	
	int id;
	
	boolean down;
	
	public Button(String phrase, float x, float y, int id)
	{
		Phrase = phrase;
		
		this.x = x;
		this.y = y;
		
		width = 0;
		height = 0;
		
		hitbox = new Quad(x, y, width, height);
		
		this.id = id;
		
		delay = 0;
		
		down = false;
	}
	
	public Button setImage(AnimationSet normal, AnimationSet selected)
	{
		Normal = normal;
		Selected = selected;
		
		return this;
	}
	
	public Button setDimensions(float width, float height)
	{
		this.width = width;
		this.height = height;
		
		return this;
	}
	
	public void update()
	{
		hitbox.changeDimensions(x, y, width, height);
		if(hitbox.checkPoint(Mouse.getX(), Config.HEIGHT - Mouse.getY()))
		{
			if(Mouse.isButtonDown(Input.MOUSE_LEFT_BUTTON))
			{
				down = true;
			}
		}else
		{
			down = false;
		}
		
		if(down)
		{
			if(!Mouse.isButtonDown(Input.MOUSE_LEFT_BUTTON))
			{
				clicked = true;
			}else
			{
				clicked = false;
			}
		}else
		{
			clicked = false;
		}
		
		if(Selected != null)
		{
			Selected.resetAnimate();
		}
		
		Normal.resetAnimate();
	}
	
	public void render(Graphics g) throws SlickException
	{
		if(hitbox.checkPoint(Mouse.getX(),Config.HEIGHT -  Mouse.getY()) && Selected != null)
		{
			Selected.render(x, y, width, height, 0, g);
		}else
		{
			Normal.render(x, y, width, height, 0, g);
		}

		g.drawString(Phrase, x - Phrase.length() / 2 * 10, y - 10);
		
	}
	
	public int getID()
	{
		return id;
	}
}

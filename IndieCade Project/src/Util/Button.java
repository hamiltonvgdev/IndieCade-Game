package Util;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Geo.Quad;
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
	
	int id;
	
	public Button(String phrase, float x, float y, int id)
	{
		Phrase = phrase;
		
		this.x = x;
		this.y = y;
		
		width = 0;
		height = 0;
		
		hitbox = new Quad(x, y, width, height);
		
		this.id = id;
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
		
		if(hitbox.checkPoint(Mouse.getDX(), Mouse.getDY()))
		{
			if(Mouse.isButtonDown(Input.MOUSE_LEFT_BUTTON))
			{
				clicked = true;
			}
		}else
		{
			clicked = false;
		}
	}
	
	public void render(Graphics g) throws SlickException
	{
		if(hitbox.checkPoint(Mouse.getDX(), Mouse.getDY()) && Selected != null)
		{
			Selected.render(x, y, width, height, 0, g);
		}else
		{
			Normal.render(x, y, width, height, 0, g);
		}
		
		g.drawString(Phrase, x - width/2, y);
	}
	
	public int getID()
	{
		return id;
	}
}

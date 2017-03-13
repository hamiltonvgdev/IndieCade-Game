package Geo;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Render.BasicImage;

public class Hitbox
{
	ArrayList<Quad> Quads;
	float factorHeight;
	float factorWidth;
	
	public Hitbox(BasicImage sprite)
	{
		Quads = new ArrayList<Quad>();
		
		factorWidth = sprite.width / sprite.getImage().getWidth();
		factorHeight = sprite.height / sprite.getImage().getHeight();
		
		for(int x = 0; x < sprite.getImage().getWidth(); x ++)
		{
			for(int y = 0; y < sprite.getImage().getHeight(); y ++)
			{
				if(sprite.getImage().getColor(x, y).a > 0)
				{
					Quads.add(new Quad(x * factorWidth  - sprite.getImage().getWidth() / 2 * factorWidth + sprite.x, 
							y * factorHeight - sprite.getImage().getHeight() / 2 * factorHeight
							+ sprite.y, factorWidth, factorHeight));
				}else
				{
					Quads.add(new Quad(x * factorWidth - sprite.getImage().getWidth() / 2 * factorWidth + sprite.x, 
							y * factorHeight - sprite.getImage().getHeight() / 2 * factorHeight
							+ sprite.y, factorWidth, factorHeight).
							setPhased(true));
				}
			}
		}
	}
	
	public void update(BasicImage sprite)
	{
		Quads.clear();
		
		factorWidth = sprite.width / sprite.getImage().getWidth();
		factorHeight = sprite.height / sprite.getImage().getHeight();
		
		for(int x = 0; x < sprite.getImage().getWidth(); x ++)
		{
			for(int y = 0; y < sprite.getImage().getHeight(); y ++)
			{
				if(sprite.getImage().getColor(x, y).a > 0)
				{
					Quads.add(new Quad(x * factorWidth  - sprite.getImage().getWidth() / 2 * factorWidth + sprite.x, 
							y * factorHeight - sprite.getImage().getHeight() / 2 * factorHeight
							+ sprite.y, factorWidth, factorHeight));
				}else
				{
					Quads.add(new Quad(x * factorWidth - sprite.getImage().getWidth() / 2 * factorWidth + sprite.x, 
							y * factorHeight - sprite.getImage().getHeight() / 2 * factorHeight
							+ sprite.y, factorWidth, factorHeight).
							setPhased(true));
				}
			}
		}
	}
	
	public boolean check(Point point)
	{
		boolean derp = false;
		
		for(int i = 0; i < Quads.size(); i ++)
		{
			if(Quads.get(i).checkPoint(point.x, point.y))
			{
				derp = true;
				break;
			}
		}
		
		return derp;
	}
	
	public boolean check(Quad quad)
	{
		boolean derp = false;
		
		for(int i = 0; i < Quads.size(); i ++)
		{
			if(Quads.get(i).checkQuad(quad))
			{
				derp = true;
				break;
			}
		}
		
		return derp;
	}
	
	public boolean check(Hitbox hitbox)
	{
		boolean derp = false;
		
		for(int i = 0; i < Quads.size(); i ++)
		{
			if(hitbox.check(Quads.get(i)))
			{
				derp = true;
				break;
			}
		}
		
		return derp;
	}
	
	public void render(Graphics g) throws SlickException
	{
		for(int i = 0; i < Quads.size(); i ++)
		{
			if(!Quads.get(i).phased)
			{
				Quads.get(i).render(g);
			}
		}
	}
	
	public ArrayList<Quad> getHitbox()
	{
		return Quads;
	}
}

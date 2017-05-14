package Geo;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Render.BasicImage;

public class Hitbox
{
	//Image analysis that assigns every pixel in the ORIGINAL image a 1x1 quad, then stretches and shifts it to 
	//Accommodate for width, height, and rotational differences
	
	ArrayList<Quad> Quads;
	float factorHeight;
	float factorWidth;
	
	public Hitbox(BasicImage sprite)
	{
		Quads = new ArrayList<Quad>();
		
		//The ratio between the original image's dimensions and the actual rendered version's dimensions
		factorWidth = sprite.width / sprite.getImage().getWidth();
		factorHeight = sprite.height / sprite.getImage().getHeight();
		
		//The factor that adjusts for image flip
		float factor = 1;
		
		if(sprite.flip)
		{
			factor = -1;
		}
		
		//Adjusts for dimensional differences
		for(int x = 0; x < sprite.getImage().getWidth(); x ++)
		{
			for(int y = 0; y < sprite.getImage().getHeight(); y ++)
			{
				if(sprite.getImage().getColor(x, y).a > 0)
				{
					Quads.add(new Quad(factor * (x * factorWidth  - sprite.getImage().getWidth() / 2 * factorWidth), 
							y * factorHeight - sprite.getImage().getHeight() / 2 * factorHeight,
							factorWidth, factorHeight));
				}else
				{
					Quads.add(new Quad(factor * (x * factorWidth - sprite.getImage().getWidth() / 2 * factorWidth), 
							y * factorHeight - sprite.getImage().getHeight() / 2 * factorHeight,
							factorWidth, factorHeight).
							setPhased(true));
				}
			}
		}
		
		//Adjusts for rotational and positional differences
		for(int i = 0; i < Quads.size(); i ++)
		{
			Quads.get(i).changeDimensions((float) (Quads.get(i).x * Math.cos(Math.toRadians(sprite.rot)) - 
					Quads.get(i).y * Math.sin(Math.toRadians(sprite.rot))) + sprite.x, 
					(float) (Quads.get(i).y * Math.cos(Math.toRadians(sprite.rot)) + 
							Quads.get(i).x * Math.sin(Math.toRadians(sprite.rot))) + sprite.y, 
					Quads.get(i).width, Quads.get(i).height);
		}
	}
	
	public void update(BasicImage sprite)
	{
		Quads.clear();
		
		factorWidth = sprite.width / sprite.getImage().getWidth();
		factorHeight = sprite.height / sprite.getImage().getHeight();

		float factor = 1;
		
		if(sprite.flip)
		{
			factor = -1;
		}
		
		for(int x = 0; x < sprite.getImage().getWidth(); x ++)
		{
			for(int y = 0; y < sprite.getImage().getHeight(); y ++)
			{
				if(sprite.getImage().getColor(x, y).a > 0)
				{
					Quads.add(new Quad(factor * (x * factorWidth  - sprite.getImage().getWidth() / 2 * factorWidth), 
							(y * factorHeight - sprite.getImage().getHeight() / 2 * factorHeight), 
							factorWidth, factorHeight));
				}else
				{
					Quads.add(new Quad(factor * (x * factorWidth - sprite.getImage().getWidth() / 2 * factorWidth), 
							(y * factorHeight - sprite.getImage().getHeight() / 2 * factorHeight), 
							factorWidth, factorHeight).
							setPhased(true));
				}
			}
		}
		
		for(int i = 0; i < Quads.size(); i ++)
		{
			Quads.get(i).changeDimensions((float) (Quads.get(i).x * Math.cos(Math.toRadians(sprite.rot)) - 
					Quads.get(i).y * Math.sin(Math.toRadians(sprite.rot))) + sprite.x, 
					(float) (Quads.get(i).y * Math.cos(Math.toRadians(sprite.rot)) 
							+ Quads.get(i).x * Math.sin(Math.toRadians(sprite.rot))) + sprite.y, 
					Quads.get(i).width, Quads.get(i).height);
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
	
	public boolean check(QuadR quadr)
	{
		boolean derp = false;
		
		for(int i = 0; i < Quads.size(); i ++)
		{
			if(quadr.check(Quads.get(i)))
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

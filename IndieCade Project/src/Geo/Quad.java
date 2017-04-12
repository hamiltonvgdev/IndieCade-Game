package Geo;

import java.io.Serializable;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Quad implements Serializable
{
	//A Rectangular space that has methods that allows it to interact with other rectangular spaces.
	//Cannot respond to rotations tho
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6083904781356000620L;
	public float width, height, x, y;
	
	public boolean phased;

	public Quad(float x, float y, float width, float height) {
		this.x = x - width / 2;
		this.y = y - height / 2;
		this.width = width;
		this.height = height;
		
		
		phased = false;
	}
	
	public Quad setPhased(boolean phased)
	{
		this.phased = phased;
		return this;
	}

	public boolean checkPoint(float x, float y)
	{
		if(phased)
		{
			return false;
		}else
		{
			boolean derp = false;
			
			if (x >= this.x)
				if (y >= this.y)
					if (x <= this.x + this.width)
						if (y <= this.y + this.height)
							derp = true;
			return derp;
		}
	}
	
	public boolean checkQuad(Quad quad)
	{
		if(phased)
		{
			return false;
		}else
		{
			boolean intersect = false;
			
			for(float x = quad.x ; x < quad.x + quad.width; x ++)
			{
				for(float y = quad.y ; y < quad.y + quad.height; y ++)
				{
					if(checkPoint(x,y))
					{
						intersect = true;
						break;
					}
				}
				
				if(intersect)
				{
					break;
				}
			}
			
			return intersect;
		}
	}
	
	public boolean checkQuadR(QuadR quadr)
	{
		return quadr.check(this);
	}
	
	public boolean checkWithin(Quad quad)
	{
		if(phased)
		{
			return false;
		}else
		{
			boolean contained = true;
			
			for(float x = quad.x ; x < quad.x + quad.width; x ++)
			{
				for(float y = quad.y - quad.height; y < quad.y + quad.height; y ++)
				{
					if(!checkPoint(x,y))
					{
						contained = false;
						break;
					}
				}
				
				if(!contained)
				{
					break;
				}
			}
			
			return contained;
		}
	}
	
	public void changeDimensions(float x, float y, float width, float height)
	{
		this.x = x- width / 2;
		this.y = y - height / 2;
		this.width = width;
		this.height = height;
	}
	
	public void render(Graphics g) throws SlickException
	{
		g.drawRect(x, y, width, height);
	}
}

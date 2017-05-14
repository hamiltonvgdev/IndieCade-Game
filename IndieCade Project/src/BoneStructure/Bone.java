package BoneStructure;

import java.io.Serializable;

import org.newdawn.slick.Graphics;

import Main.Config;

public class Bone implements Serializable
{
	//Basic structure that comprises of the Bone Structure. It's sle purpose is to track some x, y, an rot values
	/**
	 * 
	 */
	private static final long serialVersionUID = 8465400098824391038L;
	float x;
	float y;
	float length;
	float rot;
	
	boolean flip;
	
	public Bone(float length)
	{
		x = 0;
		y = 0;
		rot = 0;
		this.length = length;
		
		flip = false;
	}
	
	public void setRot(float rot)
	{	
		this.rot = (float) Math.toRadians(rot);
	}
	
	public void setX(float x)
	{
		this.x = x;
	}
	
	public void setY(float y)
	{
		this.y = y;
	}
	
	public void setFlip(boolean flip)
	{
		if(this.flip != flip)
		{
			setRot(180 - getDegRot());
		}
		
		this.flip = flip;
	}
	
	public void render(Graphics g)
	{
		//Basic Trignometry
		g.drawLine((float)(x + length / 2 * Math.cos(rot)), 
				(float) (y + length / 2 * Math.sin(rot)),
				(float) (x - length / 2 * Math.cos(rot)), 
				(float) (y - length / 2 * Math.sin(rot)));
	}
	
	public float getRot()
	{
		return rot;
	}
	
	public float getDegRot()
	{
		//Rot is in Radians, for some degree, so this is to convert it to Degree for better usage
		return (float) Math.toDegrees(rot);
	}
	
	public float getPureRot()
	{
		//Gives the Smaller rot that is sensitive to the flip of the bone
		if(!flip)
		{
			return 180 - ((float) Math.toDegrees(rot));
		}else
		{
			return (float) Math.toDegrees(rot);
		}
	}
	
	public float getRenderRot()
	{
		//Uh...... I dont know why I coded this, but hey, it works, soo.....
		if(flip)
		{
			return getPureRot();
		}else
		{
		 return -getPureRot();
		}
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public boolean getFlip()
	{
		return flip;
	}
	
	public float getLength()
	{
		return length;
	}

	public void move(float xa, float ya) 
	{
		x += xa;
		y += ya;
	}
}

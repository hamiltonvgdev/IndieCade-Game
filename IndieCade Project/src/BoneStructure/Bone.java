package BoneStructure;

import java.io.Serializable;

import org.newdawn.slick.Graphics;

import Main.Config;

public class Bone implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8465400098824391038L;
	float x;
	float y;
	float length;
	float rot;
	
	public Bone(float length)
	{
		x = 0;
		y = 0;
		rot = 0;
		this.length = length;
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
	
	public void render(Graphics g)
	{
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
		return (float) Math.toDegrees(rot);
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public float getLength()
	{
		return length;
	}
}

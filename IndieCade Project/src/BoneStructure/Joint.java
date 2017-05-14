package BoneStructure;

import java.io.Serializable;

import org.newdawn.slick.Graphics;

public class Joint implements Serializable
{
	//The link between 2 bones. It's sole purpose it to keep two bones touching each other at one end, 
	//thus, holding them together, and serving as a center of rotation
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5618349032689064504L;
	Bone bone1;
	Bone bone2;
	
	float x;
	float y;
	
	protected int constant;
	
	public Joint(Bone bone1, Bone bone2)
	{
		this.bone1 = bone1;
		this.bone2 = bone2;
		
		if(bone1.getRot() < 0)
		{
			constant = -1;
		}else 
		{
			constant = 1;
		}
		
		x = (float) (bone1.getX() + bone1.getLength() / 2 * Math.cos(bone1.getRot()));
		y = (float) (bone1.getY() + constant * bone1.getLength() / 2 * Math.sin(bone1.getRot()));
		
		bone2.setRot(bone1.getPureRot());
		bone2.setFlip(!bone1.getFlip());
		
	}
	
	public void update()
	{
		//Some Trigonometry and math
		x = (float) (bone1.getX() + bone1.getLength() / 2 * Math.cos(bone1.getRot()));
		y = (float) (bone1.getY() + constant * bone1.getLength() / 2 * Math.sin(bone1.getRot()));
		
		bone2.setX((float) (x + bone2.getLength() / 2 * Math.cos(bone2.getRot())));
		bone2.setY((float) (y + constant * bone2.getLength() / 2 * Math.sin(bone2.getRot())));
	}
	
	public void render(Graphics g)
	{
		//Super ghetto, but it shows approximately where the joint it
		g.drawString("o", x - 4, y - 10);
	}
	
	public void rotBone2(float rot)
	{
		if(bone2.flip)
		{
			bone2.setRot(bone2.getDegRot() - rot);
		}else
		{
			bone2.setRot(bone2.getDegRot() + rot);
		}
	}
	
	public void setBone2Rot(float rot)
	{
		bone2.setRot(rot);
	}
	
	public Bone getBone1()
	{
		return bone1;
	}
	
	public Bone getBone2()
	{
		return bone2;
	}
	
	public int getConstant()
	{
		return constant;
	}
}

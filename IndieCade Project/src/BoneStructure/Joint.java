package BoneStructure;

import org.newdawn.slick.Graphics;

public class Joint 
{
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
		
		bone2.setRot((float) Math.toDegrees((bone1.getRot() - constant *  Math.toRadians(0))));
		
	}
	
	public void update()
	{
		x = (float) (bone1.getX() + bone1.getLength() / 2 * Math.cos(bone1.getRot()));
		y = (float) (bone1.getY() + constant * bone1.getLength() / 2 * Math.sin(bone1.getRot()));
		
		bone2.setX((float) (x + bone2.getLength() / 2 * Math.cos(bone2.getRot())));
		bone2.setY((float) (y + constant * bone2.getLength() / 2 * Math.sin(bone2.getRot())));
	}
	
	public void render(Graphics g)
	{
		g.drawString("o", x - 4, y - 10);
	}
	
	public void rotBone2(float rot)
	{
		bone2.setRot(bone2.getDegRot() + rot);
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

package BoneStructure;

import java.io.Serializable;

public class FourWayJoint extends ThreeWayJoint implements Serializable
{
	//Extension of the Joint class, see that for more analysis. Basically same thing with 4 bones instead of 2
	/**
	 * 
	 */
	private static final long serialVersionUID = 3192954145125606726L;
	Bone bone4;
	
	public FourWayJoint(Bone bone1, Bone bone2, Bone bone3, Bone bone4) 
	{
		super(bone1, bone2, bone3);
		this.bone4 = bone4;
		
		bone1.setFlip(false);	
		bone2.setFlip(false);
		bone3.setFlip(false);
		bone4.setFlip(false);
		
		bone2.setRot((float) Math.toDegrees((Math.toRadians(bone1.getPureRot()) + constant *  Math.toRadians(90))));
		bone3.setRot((float) Math.toDegrees((Math.toRadians(bone1.getPureRot()) + constant *  Math.toRadians(90))));
		bone3.setFlip(true);
		bone4.setRot((float) Math.toDegrees((Math.toRadians(bone1.getPureRot()) + constant *  Math.toRadians(0))));
	}
	
	public void update()
	{
		super.update();

		bone4.setX((float) (x + bone4.getLength() / 2 * Math.cos(bone4.getRot())));
		bone4.setY((float) (y + constant * bone4.getLength() / 2 * Math.sin(bone4.getRot())));
	}
	
	public void rotBone4(float rot)
	{
		bone4.setRot(bone4.getDegRot() + rot);
	}
	
	public void setBone4Rot(float rot)
	{
		bone4.setRot(rot);
	}
	
	public Bone getBone4()
	{
		return bone4;
	}
}

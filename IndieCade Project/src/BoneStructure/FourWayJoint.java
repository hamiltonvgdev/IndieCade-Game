package BoneStructure;

public class FourWayJoint extends ThreeWayJoint
{
	Bone bone4;
	
	public FourWayJoint(Bone bone1, Bone bone2, Bone bone3, Bone bone4) 
	{
		super(bone1, bone2, bone3);
		this.bone4 = bone4;
		
		bone2.setRot((float) Math.toDegrees((bone1.getRot() + constant *  Math.toRadians(90))));
		bone3.setRot((float) Math.toDegrees((bone1.getRot() - constant *  Math.toRadians(90))));
		bone4.setRot((float) Math.toDegrees((bone1.getRot() + constant *  Math.toRadians(0))));
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
	
	public Bone getBone4()
	{
		return bone4;
	}
}

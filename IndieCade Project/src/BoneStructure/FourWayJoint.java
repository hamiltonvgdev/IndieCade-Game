package BoneStructure;

public class FourWayJoint extends Joint
{
	Bone bone3;
	Bone bone4;
	
	public FourWayJoint(Bone bone1, Bone bone2, Bone bone3, Bone bone4) {
		super(bone1, bone2);
		this.bone3 = bone3;
		this.bone4 = bone4;
	}

}

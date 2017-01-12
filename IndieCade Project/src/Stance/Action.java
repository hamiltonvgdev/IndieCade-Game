package Stance;

import BoneStructure.BoneStructure;
import BoneStructure.FourWayJoint;
import BoneStructure.Joint;
import BoneStructure.ThreeWayJoint;

public class Action 
{
	String joint;
	float rot;
	long time;
	int id;
	
	public Action(String joint, int bone,  float rot,  long time)
	{
		this.joint = joint;
		this.time = time;
		this.rot = rot;
		id = bone;
	}
	
	public void update(BoneStructure body, Stance stance)
	{
		if(joint != null)
		{
			Joint Joint = body.getJoint(joint);
			int constant;
			
			if(stance.index == 0)
			{
				constant = 21;
			}else
			{
				constant = 16;
			}
			
			if(id == 0)
			{
				Joint.rotBone2(rot / time * constant); 
			}else if(id == 1)
			{
				((ThreeWayJoint) Joint).rotBone3(rot / time * constant);
			}else if(id == 2)
			{
				((FourWayJoint) Joint).rotBone4(rot / time * constant);
			}
		}
	}
}

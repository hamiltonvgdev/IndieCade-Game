package Stance;

import java.io.Serializable;

import BoneStructure.BoneStructure;
import BoneStructure.FourWayJoint;
import BoneStructure.Joint;
import BoneStructure.ThreeWayJoint;

public class Action implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 192811957066539752L;
	
	BoneStructure body;
	
	String joint;
	float rot;
	float cRot;
	long time;
	int id;
	
	public Action(BoneStructure body, String joint, int bone,  float rot,  long time)
	{
		this.body = body;
		this.joint = joint;
		this.time = time;
		this.rot = rot;
		id = bone;
	}
	
	public void reset()
	{
		if(body != null && joint != null)
		{
			if(id == 0)
			{
				cRot = - body.getJoint(joint).getBone2().getPureRot();
			}else if(id == 1)
			{
				cRot = - ((ThreeWayJoint) body.getJoint(joint)).getBone3().getPureRot();
			}else if(id == 2)
			{
				cRot = - ((FourWayJoint) body.getJoint(joint)).getBone4().getPureRot();
			}
		}
	}
	
	public void update(Stance stance)
	{
		if(joint != null && body != null)
		{
			Joint Joint = body.getJoint(joint);
			int constant;
			
			
			if(stance.index == 0)
			{
				constant = 40;
			}else
			{
				constant = 40;
			}
			
			
			if(id == 0)
			{
				Joint.rotBone2((rot - cRot) / time * constant); 
			}else if(id == 1)
			{
				((ThreeWayJoint) Joint).rotBone3((rot - cRot) / time * constant);
			}else if(id == 2)
			{
				((FourWayJoint) Joint).rotBone4((rot - cRot) / time * constant);
			}
			
		}
	}
}

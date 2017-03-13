package Stance;

import BoneStructure.BoneStructure;

public class Pause extends Action
{

	public Pause(BoneStructure body, String joint, int bone, float rot, long time) 
	{
		super(body, joint, bone, rot, time);
	}
	
	@Override
	public void update(Stance stance)
	{
		
	}
}

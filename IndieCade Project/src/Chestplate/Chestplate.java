package Chestplate;

import BoneStructure.FourWayJoint;
import GameBasics.Item;

public class Chestplate extends Item
{
	
	public Chestplate(String name) 
	{
		super(name);
		
		setInput(player.getInput().KEY_O);
		
		Bones.add(player.getBody().getJoint("Torso").getBone2());
		Bones.add(((FourWayJoint) player.getBody().getJoint("Torso")).getBone3());
		Bones.add(((FourWayJoint) player.getBody().getJoint("Torso")).getBone4());
		Bones.add(player.getBody().getJoint("Spine").getBone2());
	}

}

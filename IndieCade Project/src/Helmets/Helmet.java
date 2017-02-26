package Helmets;

import GameBasics.Item;

public class Helmet extends Item
{
	public Helmet(String name) 
	{
		super(name);
		
		setInput(player.getInput().KEY_I);
		
		Bones.add(player.getBody().getJoint("Neck").getBone1());
		
		type = 0;
	}

	
}

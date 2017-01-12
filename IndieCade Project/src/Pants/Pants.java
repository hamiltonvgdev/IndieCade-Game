package Pants;

import GameBasics.Item;

public class Pants extends Item
{

	public Pants(String name) 
	{
		super(name);
		
		setInput(player.getInput().KEY_SPACE);
	}

}

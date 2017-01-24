package Chestplate;

import GameBasics.Item;

public class Chestplate extends Item
{

	public Chestplate(String name) 
	{
		super(name);
		
		setInput(player.getInput().KEY_E);
	}

}

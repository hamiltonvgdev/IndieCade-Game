package Gauntlets;

import GameBasics.Item;

public class Gauntlet extends Item
{

	public Gauntlet(String name) 
	{
		super(name);
		
		setInput(player.getInput().KEY_K);
		
		type = 2;
	}

}

package Tiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import GameBasics.Item;
import Player.Player;

public class ItemTile extends InteractTile
{
	Item item;
	boolean given;
	
	int angle;
	
	public ItemTile(String name, Player player, Color Id) 
	{
		super(name, player, Id);
		given = false;
		
		Type = 5;
		
		angle = 0;
	}
	
	public ItemTile setItem(Item item)
	{
		this.item = item;
		return this;
	}
	
	@Override
	public void update()
	{
		super.update();
		
		angle += 6;
		
		if(angle >= 360)
		{
			angle = 0;
		}
	}
	
	@Override
	public void render(Graphics g) throws SlickException
	{
		super.render(g);
		
		if(!given)
		{
			item.getSprite().render(x, (float) (y - 10 -  item.getHeight() / 2 - 10 * Math.sin(Math.toRadians(angle)))
					, item.getWidth(), item.getHeight(), 0, g);
		}
	}
	
	@Override
	public void action()
	{
		if(!given)
		{
			player.giveItem(item);
			given = true;
		}
	}
	
	public Item getItem()
	{
		return item;
	}
}

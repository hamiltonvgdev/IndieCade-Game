package Helmets;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import GameBasics.Item;
import Player.Player;
import Render.AnimationSet;

public class PureStatHelmet extends Item
{
	Player player;
	
	float height;
	float width;
	
	public PureStatHelmet(String name, Player player) 
	{
		super(name, player);
		
		
		this.player = player;
	}
	
	public void update()
	{
		super.update();
	}
	
	public void render(float x, float y, float width, float height, float rot, Graphics g) throws SlickException
	{
		sprite.render(x, y, width, height, rot, g);
	}
	
	public PureStatHelmet changeDimensions(float width, float height)
	{
		this.height = height;
		this.width = width;
		return this;
	}
}

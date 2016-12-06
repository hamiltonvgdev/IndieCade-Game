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
	
	public PureStatHelmet(Player player, String name, String ref, int delay) 
	{
		super(name);
		
		sprite = new AnimationSet(ref, delay);
		
		this.player = player;
	}
	
	public void update()
	{
		super.update();
	}
	
	public void render(Graphics g) throws SlickException
	{
		sprite.render(player.getX(), player.headY, width, height, 0, g);
	}
	
	public PureStatHelmet changeDimensions(float width, float height)
	{
		this.height = height;
		this.width = width;
		return this;
	}
}

package Enemy;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import GameBasics.Entity;
import Map.Map;
import Player.Player;
import Render.AnimationSet;

public class Enemy extends Entity
{
	
	Player player;
	AnimationSet triggered;
	
	float range;
	
	boolean near;
	
	public Enemy(Player player, float health, float damage, float speed)
	{
		super(player, health, damage, speed);
		
		this.player = player;
		
		range = 100;
		
		near = false;
	}
	
	public Enemy setRange(float range)
	{
		this.range = range;
		return this;
	}
	
	public Enemy setTriggeredAnimation(String res, long delay)
	{
		triggered = new AnimationSet(res, delay);
		return this;
	}
	
	public void update()
	{
		super.update();
		
		if(distanceSense(range, player))
		{
			action();
			
			near = true;
		}else
		{
			deaction();
			
			near = false;
		}
	}
	
	protected void action() 
	{
		
	}

	protected void deaction()
	{
		
	}
	
	public void render(Graphics g, float xOffset, float yOffset) throws SlickException
	{
		if(near)
		{
			triggered.render(x + xOffset, y + yOffset, width, height, 0, g);
		}else
		{
			super.render(g, xOffset, yOffset);
		}
		
	}
	
	public float getRange()
	{
		return range;
	}
	
	public AnimationSet getTriggeredSprite()
	{
		return triggered;
	}
}

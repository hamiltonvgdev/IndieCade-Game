package Enemy;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import GameBasics.Entity;
import Map.Map;
import Player.Player;
import Render.AnimationSet;

public class Enemy extends Entity
{
	//An entity with AI programming, so it will react to out side stimulus instead of standing around
	
	protected Player player;
	protected AnimationSet triggered;
	
	protected float range;
	
	protected boolean near;
	
	public Enemy(String name, Player player, float health, float damage)
	{
		super(name, player, health, damage);
		
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
	
	//The next two methods are left blank so they can be programmed in later extensions of Enemy
	protected void action() 
	{
		//The thing that the AI will do if player is near it
	}

	protected void deaction()
	{
		//The reversion of action that returns the Enemy to its idle state
	}
	
	public void render(Graphics g, float xOffset, float yOffset) throws SlickException
	{
		if(near && triggered != null)
		{
			triggered.render(x, xOffset, y, yOffset, width, height, 0, g);
		}else
		{
			super.render(g, xOffset, yOffset);
		}

		//Hitbox.render(g);
	}
	
	public float getRange()
	{
		return range;
	}
	
	public AnimationSet getTriggeredSprite()
	{
		return triggered;
	}
	
	public Enemy clone()
	{
		return (Enemy) new Enemy(name, player, health, damage).
				setRange(range).setTriggeredAnimation(triggered.getFolder(), triggered.getDelay()).
				setDimensions(width, height).setAnimationSet(sprite.getFolder(), sprite.getDelay()).
				setAtkSpeed(atkSpeed).setMove(speed, acceleration);
	}
}

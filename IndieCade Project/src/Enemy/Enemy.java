package Enemy;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import GameBasics.Entity;
import Map.Map;
import Player.Player;

public class Enemy extends Entity
{

	Player player;
	
	public Enemy(Player player, float health, float damage, float speed)
	{
		super(player, health, damage, speed);
		
		this.player = player;
	}
	
	public void update()
	{
		super.update();
		
		if(distanceSense(400, player))
		{
			action();
			
			if(!sprite.getFolder().equals("res/Entities/goblin/Images/SideL"))
			{
				setAnimationSet("res/Entities/goblin/Images/SideL", 200);
			}
		}else
		{
			
			if(!sprite.getFolder().equals("res/Entities/goblin/Images/Stale"))
			{
				setAnimationSet("res/Entities/goblin/Images/Stale", 800);
				System.out.println("derp");
			}
		}
	}
	
	private void action() 
	{
		follow(player);
	}

	public void render(Graphics g) throws SlickException
	{
		super.render(g);
	}
}

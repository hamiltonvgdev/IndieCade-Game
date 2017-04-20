package GUI;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Main.Config;
import Player.Player;
import Render.BasicImage;

public class HealthBar
{
	Player player;
	
	BasicImage green;
	BasicImage yellow;
	BasicImage red;
	BasicImage normal;
	BasicImage hitted;
	BasicImage outline;
	
	float x;
	float y;
	float height;
	float maxHealth;
	float factor;
	
	public HealthBar(Player player)
	{
		this.player = player;
		
		green = new BasicImage("res/GUI/Health/green.png");
		yellow = new BasicImage("res/GUI/Health/yellow.png");
		red = new BasicImage("res/GUI/Health/red.png");
		normal = new BasicImage("res/GUI/Health/normal.png");
		hitted = new BasicImage("res/GUI/Health/hitted.png");
		outline = new BasicImage("res/GUI/Health/outline.png");
		
		maxHealth = player.getHealth();

		factor = 2F;
		height = 22;
		
		x = maxHealth * (factor * 0.536F);
		y = height * factor / 2;
	}
	
	public void render(Graphics g) throws SlickException
	{
		if(player.damaged)
		{
			hitted.render(x, 0, y, 0, maxHealth * factor, height, 0, g);
		}else
		{
			normal.render(x, 0, y, 0, maxHealth * factor, height, 0, g);
		}
		
		if(player.getHealth() / maxHealth >= 0.75)
		{
			green.render(x - (maxHealth - player.getHealth()) * factor / 2, 0, y, 0, player.getHealth() * factor, 
					height * factor, 0, g);
		}else if(player.getHealth() / maxHealth >= 0.25)
		{
			yellow.render(x - (maxHealth - player.getHealth()) * factor / 2, 0, y, 0, player.getHealth() * factor,
					height * factor, 0, g);
		}else if(player.getHealth() / maxHealth < 0.25)
		{
			red.render(x - (maxHealth - player.getHealth()) * factor / 2, 0, y, 0, player.getHealth() * factor,
					height * factor, 0, g);
		}
		
		outline.render(x + maxHealth * (factor * .009F), 0, y, 0, maxHealth * (factor * 1.09F), height * factor, 0, g);
		
	}
}

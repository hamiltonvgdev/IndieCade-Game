package GUI;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

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
	
	float x;
	float y;
	float height;
	float maxHealth;
	
	public HealthBar(Player player)
	{
		this.player = player;
		
		green = new BasicImage("res/GUI/Health/green.png");
		yellow = new BasicImage("res/GUI/Health/yellow.png");
		red = new BasicImage("res/GUI/Health/red.png");
		normal = new BasicImage("res/GUI/Health/normal.png");
		hitted = new BasicImage("res/GUI/Health/hitted.png");
		
		maxHealth = player.getHealth();
	}
	
	public void render(Graphics g) throws SlickException
	{
		if(player.getHealth() / maxHealth >= 0.75)
		{
			green.render(x, 0, y, 0, player.getHealth(), height, 0, g);
		}else if(player.getHealth() / maxHealth >= 0.5)
		{
			yellow.render(x, 0, y, 0, player.getHealth(), height, 0, g);
		}else if(player.getHealth() / maxHealth >= 0.25)
		{
			red.render(x, 0, y, 0, player.getHealth(), height, 0, g);
		}
		
		if(player.damaged)
		{
			hitted.render(x, 0, y, 0, maxHealth, height, 0, g);
		}else
		{
			normal.render(x, 0, y, 0, maxHealth, height, 0, g);
		}
	}
}

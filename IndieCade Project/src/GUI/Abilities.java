package GUI;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import Player.Player;

public class Abilities 
{
	
	Player player;
	float x;
	float y;
	float width;
	float height;
	float factor;
	float Stringfactor;

	public Abilities(Player player)
	{
		this.player = player;
		x = 25;
		y = 25;
		factor = 50;
		Stringfactor = 10;
		width = 48;
		height = 48;
	}
	
	public void render(Graphics g) throws SlickException
	{
		if(player.getForm().getIcon1() != null)
		{
			player.getForm().getIcon1().render(x, 0, y, 0, width, height, 0, g);
			
			if(System.currentTimeMillis() - player.getForm().getTick1() < player.getForm().getCD1())
			{
				if((player.getForm().getCD1() + player.getForm().getTick1() - System.currentTimeMillis()) / 1000 < 10)
				{
					Stringfactor = 5;
				}else
				{
					Stringfactor = 10;
				}
				
				g.drawImage(new Image("res/Shade.png"), x - width / 2, y - height / 2);
				
				g.drawString(Integer.toString((int) 
						(player.getForm().getCD1() + player.getForm().getTick1() - System.currentTimeMillis()) / 1000)
						, x - Stringfactor, y - 8);
			}
		}
		
		if(player.getForm().getIcon2() != null)
		{
			player.getForm().getIcon2().render(x + factor, 0, y, 0, width, height, 0, g);
			
			if(System.currentTimeMillis() - player.getForm().getTick2() < player.getForm().getCD2())
			{
				if((player.getForm().getCD2() + player.getForm().getTick2() - System.currentTimeMillis()) / 1000 < 10)
				{
					Stringfactor = 5;
				}else
				{
					Stringfactor = 10;
				}
				
				g.drawImage(new Image("res/Shade.png"), x + factor - width / 2, y - height / 2);
				
				g.drawString(Integer.toString((int) 
						(player.getForm().getCD2() + player.getForm().getTick2() - System.currentTimeMillis()) / 1000)
						, x + factor - Stringfactor, y - 8);
			}
		}
		
		if(player.getForm().getIcon3() != null)
		{
			player.getForm().getIcon3().render(x + factor * 2, 0, y, 0, width, height, 0, g);
			
			if(System.currentTimeMillis() - player.getForm().getTick3() < player.getForm().getCD3())
			{
				if((player.getForm().getCD3() + player.getForm().getTick3() - System.currentTimeMillis()) / 1000 < 10)
				{
					Stringfactor = 5;
				}else
				{
					Stringfactor = 10;
				}
				
				g.drawImage(new Image("res/Shade.png"), x + factor * 2 - width / 2, y - height / 2);
				
				g.drawString(Integer.toString((int) 
						(player.getForm().getCD3() + player.getForm().getTick3() - System.currentTimeMillis()) / 1000)
						, x + factor * 2 - Stringfactor, y - 8);
			}
		}
	}
}

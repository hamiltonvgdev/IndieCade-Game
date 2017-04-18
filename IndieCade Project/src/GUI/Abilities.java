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

	public Abilities(Player player)
	{
		this.player = player;
	}
	
	public void render(Graphics g) throws SlickException
	{
		if(player.getForm().getIcon1() != null)
		{
			player.getForm().getIcon1().render(10, 0, 10, 0, 16, 16, 0, g);
			
			if(System.currentTimeMillis() - player.getForm().getTick1() < player.getForm().getCD1())
			{
				g.drawString(Integer.toString((int) 
						( player.getForm().getCD1() + player.getForm().getTick1() - System.currentTimeMillis()))
						, 10, 10);
				g.drawImage(new Image("res/Shade.png"), 2, 2);
			}
		}
		
		if(player.getForm().getIcon2() != null)
		{
			player.getForm().getIcon1().render(30, 0, 10, 0, 16, 16, 0, g);
			
			if(System.currentTimeMillis() - player.getForm().getTick2() < player.getForm().getCD2())
			{
				g.drawString(Integer.toString((int) 
						( player.getForm().getCD2() + player.getForm().getTick2() - System.currentTimeMillis()))
						, 30, 30);
				g.drawImage(new Image("res/Shade.png"), 22, 2);
			}
		}
		
		if(player.getForm().getIcon3() != null)
		{
			player.getForm().getIcon1().render(50, 0, 10, 0, 16, 16, 0, g);
			
			if(System.currentTimeMillis() - player.getForm().getTick3() < player.getForm().getCD3())
			{
				g.drawString(Integer.toString((int) 
						( player.getForm().getCD3() + player.getForm().getTick3() - System.currentTimeMillis()))
						, 50, 50);
				g.drawImage(new Image("res/Shade.png"), 42, 2);
			}
		}
	}
}

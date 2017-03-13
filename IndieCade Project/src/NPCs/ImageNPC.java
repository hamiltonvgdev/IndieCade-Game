package NPCs;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import GameBasics.BasicNPC;
import Player.Player;
import Render.AnimationSet;

public class ImageNPC extends BasicNPC
{
	AnimationSet show;
	
	public ImageNPC(String ref, Player player, String name, float range) 
	{	
		super(player, name, range);
		
		show = new AnimationSet(ref, 100);
	}
	
	public void update()
	{
		super.update();
		
		if(active)
		{
			show.resetAnimate();
		}else
		{
			show.reset();
		}
	}
	
	public void render(Graphics g, float xOffset, float yOffset) throws SlickException
	{
		if(active && Active != null)
		{
			Active.render(x + xOffset, y + yOffset, width, height, rot, g);
		}else if(near && Near != null)
		{
			Near.render(x + xOffset, y + yOffset, width, height, rot, g);
		}else if(idle && Idle != null)
		{
			Idle.render(x + xOffset, y + yOffset, width, height, rot, g);
		}
		
		if(near && !active)
		{
			speak(g, xOffset, yOffset);
		}
		
		if(active)
		{
			show.render(x, y - 100, show.getSet().get(0).getImage().getWidth(),
					 show.getSet().get(0).getImage().getHeight(), rot, g);
		}
		
		g.drawString(name, x - name.length() * 4, y + height / 3 * 2);
	}
}

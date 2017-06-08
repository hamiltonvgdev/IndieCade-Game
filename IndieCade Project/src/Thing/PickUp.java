package Thing;

import Form.Form;
import Geo.M;
import Player.Player;

public class PickUp extends ActThing
{
	Form form;
	boolean interact;
	boolean given;
	
	public PickUp(String ref, long delay, Player player, Form form, int type)
	{
		super(ref, delay, player, type);
		this.form = form;
		interact = false;
		given = false;
	}
	
	public void update()
	{
		if(M.distance(player.getX(), player.getY(), x, y) <= 32 && 
				player.getInput().isKeyPressed(player.getInput().KEY_BACKSLASH))
			{
				interact = true;
			}
		
		if(interact && !given)
		{
			given = true;
			player.giveForm(form);
			end();
		}
	}
}

package Menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Main.Config;
import Render.AnimationSet;
import Render.BasicImage;
import Util.Button;

public class ConfirmMenu extends BasicMenu
{
	public boolean active;
	boolean confirmed;

	BasicImage phrase;
	Button yes;
	Button no;
	
	public ConfirmMenu()
	{
		active = false;
		confirmed = false;
		
		this.phrase = new BasicImage("res/Confirm Message.png");
		
		yes = new Button("YES", Config.WIDTH / 2 - 100, Config.HEIGHT / 2 + 100, 0).setDimensions(150, 100).
				setImage(new AnimationSet("res/Buttons/Base/Idle", 100), 
						new AnimationSet("res/Buttons/Base/Select", 100)); 
		
		no = new Button("NO", Config.WIDTH / 2 + 100, Config.HEIGHT / 2 + 100, 0).setDimensions(150, 100).
				setImage(new AnimationSet("res/Buttons/Base/Idle", 100),
						new AnimationSet("res/Buttons/Base/Select", 100));
	}
	
	@Override
	public void update() 
	{
		if(active)
		{
			yes.update();
			no.update();
			
			if(yes.clicked)
			{
				confirmed = true;
			}
			
			if(no.clicked)
			{
				active = false;
			}
		}
	}

	@Override
	public void render(Graphics g) throws SlickException
	{
		if(active)
		{
			phrase.render(Config.WIDTH / 2, 0, Config.HEIGHT / 2 - 100, 0, 629, 69, 0, g);
			
			yes.render(g);
			no.render(g);
		}
	}
	
	public boolean getConfirm()
	{
		return confirmed;
	}
}

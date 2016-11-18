package Main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Render.AnimationSet;

public class SplashScreen extends BasicGameState
{
	AnimationSet Splash;
	Input input;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		Splash = new AnimationSet("", 100);
		
		input = new Input(1);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		Splash.render(Config.WIDTH / 2, Config.HEIGHT / 2, Config.WIDTH, Config.HEIGHT, 0, g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		Splash.endAnimate();
		
		if(input.isKeyDown(input.KEY_SPACE) || Splash.ended())
		{
			sbg.enterState(1);
		}
	}

	@Override
	public int getID() 
	{
		return 0;
	}

}

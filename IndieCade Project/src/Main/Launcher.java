package Main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Launcher extends StateBasedGame
{
	
	public Launcher() 
	{
		
		super(Config.NAME);
		
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException 
	{
		
		//this.addState(new SplashScreen());
		this.addState(new MainMenu());
		this.addState(new Game());
		this.enterState(1);
		
	}
	
	public static void main(String[] args)
	{
		try 
		{
			AppGameContainer appc = new AppGameContainer(new Launcher());
			appc.setDisplayMode(Config.WIDTH, Config.HEIGHT, false);
			appc.setShowFPS(Config.SHOW_FPS);
			appc.setVSync(Config.VSYNC);
			appc.start();
		}catch (SlickException e) 
		{
			e.printStackTrace();
		}
	}
}

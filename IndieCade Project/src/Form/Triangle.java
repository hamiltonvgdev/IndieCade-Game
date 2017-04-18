package Form;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Geo.M;
import Player.Player;
import Projectiles.BasicProjectile;
import Projectiles.SpinShot;

public class Triangle extends Form
{
	

	float Rot;
	float xOffset;
	float yOffset;

	//Ability 2
	boolean boost;
	float oSpeed;
	
	//Ability 3
	float shadowX;
	float shadowY;
	boolean shadowed;
	
	public Triangle(Player player)
	{
		super(player);
		
		cd1 = 3000;
		cd2 = 10000;
		cd3 = 0000;
		
		
		boost = false;
		shadowed = false;
		oSpeed = 1;
	}
	
	public void update()
	{
		super.update();
		
		if(boost)
		{
			player.setSpeed(3);
		}else
		{
			player.setSpeed(oSpeed);
		}
		
		if(System.currentTimeMillis() - lastTick2 >= 2000)
		{
			boost = false;
		}
		
		if(shadowed)
		{
			lastTick3 = System.currentTimeMillis();
			
			Rot = M.GetAngleOfLineBetweenTwoPoints
					(shadowX + player.getXOffset(), shadowY + player.getYOffset()
							, Mouse.getX(), M.toRightHandY(Mouse.getY()));
			
			if(player.getInput().isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON))
			{
				subAtk();
			}
			
			if(player.getInput().isKeyDown(Input.KEY_X))
			{
				sub3();
			}
		}
	}
	
	public void idle()
	{
		super.idle();
	}
	
	@Override
	public void render(float x, float xOffset, float y, float yOffset, Graphics g) throws SlickException
	{
		if(shadowed)
		{
			getCurrentSprite().render(shadowX, xOffset, shadowY, yOffset, width, height, Rot, g);
		}
		
		super.render(x, xOffset, y, yOffset, g);
		
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	@Override
	public void ability1()
	{
		super.ability1();
		
		player.move((float) (-200 * M.cos(rot)), (float) (-200 * M.sin(rot)));
	}
	
	@Override
	public void ability2()
	{
		super.ability2();
		oSpeed = player.getspeed();
		boost = true;
	}
	
	@Override
	public void ability3()
	{
		super.ability3();
		shadowed = true;
		shadowX = Mouse.getX();
		shadowY = M.toRightHandY(Mouse.getY());
		
		System.out.println(Mouse.getX() + " " + M.toRightHandY(Mouse.getY()));
		System.out.println(shadowX + " " + shadowY);
	}
	
	public void sub3()
	{
		float xDiff = player.getX() - shadowX;
		float yDiff = player.getY() - shadowY;
		
		player.move(xDiff, yDiff);
		System.out.println(shadowX + " " + shadowY);
		shadowed = false;
	}
	
	public void subAtk()
	{
		if(atk.getCurrentIndex() == atk.getSet().size() - 1)
		{
			BasicProjectile shot = new SpinShot(player, 1).setSprite("res/Forms/Point/Projectile", 100).
					setDimensions(40, 40, -Rot).setLimit(1000);
			
			shot.setPosition(shadowX, shadowY);
			
			shot.shoot((float) (M.cos(Rot) * speed), (float) (M.sin(Rot) * speed));
			atk.reset();
		}
	}

}

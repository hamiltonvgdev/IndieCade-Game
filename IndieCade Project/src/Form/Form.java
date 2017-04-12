package Form;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Geo.M;
import Player.Player;
import Projectiles.BasicProjectile;
import Projectiles.SpinShot;
import Render.AnimationSet;

public class Form 
{
	Player player;
	
	boolean attacking;
	float speed;
	float atkSpeed;
	
	AnimationSet idle;
	AnimationSet atk;
	public float width;
	public float height;
	public float rot;
	
	//Ability 1
	float lastTick1;
	long cd1;
	boolean available1;
	
	//Ability 2
	float lastTick2;
	long cd2;
	boolean available2;
	
	//Ability 3
	float lastTick3;
	long cd3;
	boolean available3;
	
	public Form(Player player)
	{
		this.player = player;
		
		atkSpeed = 100;
		idle = new AnimationSet("res/Forms/Point/Idle", 100);
		atk = new AnimationSet("res/Forms/Point/Attacking", (long) atkSpeed);
		width = 70;
		height = 70;
		
		lastTick1 = System.currentTimeMillis();
		available1 = false;

		lastTick2 = System.currentTimeMillis();
		available2 = false;

		lastTick3 = System.currentTimeMillis();
		available3 = false;
		
		attacking = false;
		
		speed = 50;
	}
	
	public void update()
	{
		idle();
		
		if(player.getInput().isKeyDown(player.getInput().KEY_LSHIFT))
		{
			ability1();
		}
		
		if(player.getInput().isKeyDown(player.getInput().KEY_Z))
		{
			ability2();
		}
		
		if(player.getInput().isKeyDown(player.getInput().KEY_X))
		{
			ability3();
		}
		
		rot = M.GetAngleOfLineBetweenTwoPoints
				(player.getX() + player.getXOffset(), player.getY() + player.getYOffset()
						, Mouse.getX(), M.toRightHandY(Mouse.getY()));
		
		if(attacking)
		{
			attack();
		}
		
		if(player.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
		{
			attacking = true;
		}else if(atk.getCurrentIndex() == 0)
		{
			attacking = false;
			atk.reset();
		}
		
		getCurrentSprite().resetAnimate();
	}
	
	public void idle()
	{
		if(System.currentTimeMillis() - lastTick1 >= cd1)
		{
			available1 = true;
		}
		
		if(System.currentTimeMillis() - lastTick2 >= cd2)
		{
			available2 = true;
		}
		
		if(System.currentTimeMillis() - lastTick3 >= cd3)
		{
			available3 = true;
		}
	}
	
	public void render(float x, float xOffset, float y, float yOffset, Graphics g) throws SlickException
	{
		getCurrentSprite().render(x, xOffset, y, yOffset, width, height, rot, g);
	}
	
	public void attack()
	{
		if(atk.getCurrentIndex() == atk.getSet().size() - 1)
		{
			BasicProjectile shot = new SpinShot(player, 1).setSprite("res/Forms/Point/Projectile", 100).
					setDimensions(40, 40, -rot).setLimit(1000);
			
			shot.shoot((float) (M.cos(rot) * speed), (float) (M.sin(rot) * speed));
			atk.reset();
		}
	}
	
	public void ability1()
	{
		available1 = false;
		lastTick1 = System.currentTimeMillis();
	}
	
	public void ability2()
	{
		available2 = false;
		lastTick2 = System.currentTimeMillis();
	}
	
	public void ability3()
	{
		available3 = false;
		lastTick3 = System.currentTimeMillis();
	}
	
	public AnimationSet getCurrentSprite()
	{
		if(attacking)
		{
			return atk;
		}else
		{
			return idle;
		}
	}
}

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
import Render.BasicImage;

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
	long lastTick1;
	long cd1;
	boolean available1;
	BasicImage Icon1;
	
	//Ability 2
	long lastTick2;
	long cd2;
	boolean available2;
	BasicImage Icon2;
	
	//Ability 3
	long lastTick3;
	long cd3;
	boolean available3;
	BasicImage Icon3;
	
	public Form(Player player)
	{
		this.player = player;
		
		atkSpeed = 200;
		idle = new AnimationSet("res/Forms/Point/Idle", 100);
		atk = new AnimationSet("res/Forms/Point/Attacking", (long) atkSpeed / 4);
		width = 70;
		height = 70;
		
		lastTick1 = 0;
		available1 = false;

		lastTick2 = 0;
		available2 = false;

		lastTick3 = 0;
		available3 = false;
		
		attacking = false;
		
		speed = 40;
	}
	
	public void update()
	{
		if(player.getHealth() < player.getMaxHealth())
		{
			player.Damage(-0.2F);
		}
		
		
		if(player.getInput().isKeyPressed(player.getInput().KEY_LSHIFT) && available1)
		{
			ability1();
		}
		
		if(player.getInput().isKeyPressed(player.getInput().KEY_Z) && available2)
		{
			ability2();
		}
		
		if(player.getInput().isKeyPressed(player.getInput().KEY_X) && available3)
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
	
	public void reset()
	{
		
	}
	
	public void arender(float xOffset, float yOffset, Graphics g) throws SlickException
	{
		
	}
	
	public void render(float x, float xOffset, float y, float yOffset, Graphics g) throws SlickException
	{
		if(idle.afterImage)
		{
			idle.moveAfterImage(-player.getVx(), -player.getVy());
		}
		
		if(atk.afterImage)
		{
			atk.moveAfterImage(-player.getVx(), -player.getVy());
		}
		
		getCurrentSprite().render(x, xOffset, y, yOffset, width, height, rot, g);
	}
	
	public void brender(float xOffset, float yOffset, Graphics g) throws SlickException
	{
		
	}
	
	public void attack()
	{
		if(atk.getCurrentIndex() == atk.getSet().size() - 1)
		{
			BasicProjectile shot = new SpinShot(player, 1, 1).setSprite("res/Forms/Point/Projectile", 100).
					setDimensions(40, 40, -rot).setLimit(1000);
			shot.setShooter(null);
			
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
	
	public long getCD1()
	{
		return cd1;
	}
	
	public long getCD2()
	{
		return cd2;
	}
	
	public long getCD3()
	{
		return cd3;
	}
	
	public long getTick1()
	{
		return lastTick1;
	}
	
	public long getTick2()
	{
		return lastTick2;
	}
	
	public long getTick3()
	{
		return lastTick3;
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
	
	public BasicImage getIcon1()
	{
		return Icon1;
	}

	public BasicImage getIcon2()
	{
		return Icon2;
	}

	public BasicImage getIcon3()
	{
		return Icon3;
	}
}

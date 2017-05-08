package Form;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Enemy.ReflectEnemy;
import Enemy.ShieldEnemy;
import Geo.M;
import Player.Player;
import Projectiles.BasicProjectile;
import Projectiles.HomingProjectile;
import Projectiles.SpinShot;
import Projectiles.SplitShot;
import Projectiles.WaveProjectile;
import Render.AnimationSet;
import Render.BasicImage;

public class Triangle extends Form
{
	

	float Rot;
	float xOffset;
	float yOffset;

	//Ability 1
	AnimationSet blink;
	
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
		
		atkSpeed = 100;
		speed = 50;
		
		cd1 = 1000;
		cd2 = 7000;
		cd3 = 10000;
		
		width = 35 * 3;
		height = 24 * 3;

		blink = new AnimationSet("res/Forms/Triangle/Blink", 0);
		boost = false;
		shadowed = false;
		oSpeed = 1;
		
		idle = new AnimationSet("res/Forms/Triangle/Idle", 100);
		atk = new AnimationSet("res/Forms/Triangle/Attacking", (long) atkSpeed / 7);
		
		idle.setAfterImage(5, 5);
		idle.afterImageQuality(1, 0, 0, 0, 0, 0);
		
		atk.setAfterImage(5, 5);
		atk.afterImageQuality(1, 0, 0, 0, 0, 0);
		
		Icon1 = new BasicImage("res/Forms/Triangle/ability 1.png");
		Icon2 = new BasicImage("res/Forms/Triangle/ability 2.png");
		Icon3 = new BasicImage("res/Forms/Triangle/ability 3.png");
		
	}
	
	public void update()
	{
		if(!available1)
		{
			blink.endAnimate();
		}else
		{
			blink.reset();
		}
		
		if(boost)
		{
			player.setSpeed(3);
			idle.toggleAfterImage(true);
			atk.toggleAfterImage(true);
		}else
		{
			player.setSpeed(oSpeed);
			idle.toggleAfterImage(false);
			atk.toggleAfterImage(false);
		}
		
		if(System.currentTimeMillis() - lastTick2 >= 1000)
		{
			boost = false;
		}
		
		if(shadowed)
		{
			lastTick3 = System.currentTimeMillis();
			
			Rot = M.GetAngleOfLineBetweenTwoPoints
					(shadowX + player.getXOffset(), shadowY + player.getYOffset()
							, Mouse.getX(), M.toRightHandY(Mouse.getY()));
			
			if(player.getInput().isKeyPressed(player.getInput().KEY_X))
			{
				sub3();
			}
		}
		
		super.update();
	}
	
	public void idle()
	{
		super.idle();
		
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
	}
	
	@Override 
	public void arender(float xOffset, float yOffset, Graphics g) throws SlickException
	{
		if(!available1)
		{
			blink.render(player.getX(), xOffset, player.getY(), yOffset, width, height, rot, g);
		}
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
		
		if(player.getVx() != 0 && player.getVy() != 0)
		{
			player.move((float) (-200 * player.getVx() / Math.abs(player.getVx())), 
					(float) (-200 * player.getVy() / Math.abs(player.getVy())));
		}else if(player.getVx() == 0 && player.getVy() != 0)
		{
			player.move(0, (float) (-200 * player.getVy() / Math.abs(player.getVy())));
		}else if(player.getVy() == 0 && player.getVx() != 0)
		{
			player.move((float) (-200 * player.getVx() / Math.abs(player.getVx())),0);
		}else
		{
			player.move(0, 0);
		}
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
		shadowX = Mouse.getX() - player.getXOffset();
		shadowY = M.toRightHandY(Mouse.getY()) - player.getYOffset();
	}
	
	public void sub3()
	{
		float xDiff = player.getX() - shadowX;
		float yDiff = player.getY() - shadowY;
		
		player.move(xDiff, yDiff);
		shadowed = false;
	}
	
	public void subAtk()
	{
		if(atk.getCurrentIndex() == atk.getSet().size() - 1 && shadowed)
		{
			BasicProjectile shot = new BasicProjectile(player, 0.75F, 1).
					setSprite("res/Forms/Triangle/Projectile", 100).
					setDimensions(40, 40, 0).setLimit(1000);
			
			shot.setPosition(shadowX, shadowY);
			
			shot.shoot((float) (M.cos(Rot) * speed), (float) (M.sin(Rot) * speed));
		}
	}
	
	@Override
	public void attack()
	{
		subAtk();
		
		float factor = 1;
		
		if(shadowed)
		{
			factor = 0.75F;
		}
		
		if(atk.getCurrentIndex() == atk.getSet().size() - 1)
		{
			BasicProjectile shot = new BasicProjectile(player, factor, 1).
					setSprite("res/Forms/Triangle/Projectile", 100).
					setDimensions(40, 40, 0).setLimit(1000);
					
			shot.setShooter(null);
			
			
			shot.shoot((float) (M.cos(rot) * speed), (float) (M.sin(rot) * speed));
			atk.reset();
		}
	}

}

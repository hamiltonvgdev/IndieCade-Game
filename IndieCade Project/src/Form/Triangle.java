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
import Projectiles.SpinShot;
import Render.AnimationSet;

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
		
		atkSpeed = 100;
		
		cd1 = 3000;
		cd2 = 10000;
		cd3 = 20000;
		
		width = 35 * 3;
		height = 24 * 3;
		
		boost = false;
		shadowed = false;
		oSpeed = 1;
		
		idle = new AnimationSet("res/Forms/Triangle/Idle", 100);
		atk = new AnimationSet("res/Forms/Triangle/Attacking", (long) atkSpeed / 7);
	}
	
	public void update()
	{
		if(boost)
		{
			player.setSpeed(3);
		}else
		{
			player.setSpeed(oSpeed);
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
			
			if(player.getInput().isMouseButtonDown(player.getInput().MOUSE_RIGHT_BUTTON))
			{
				subAtk();
			}
			
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
		
		ReflectEnemy derp = (ReflectEnemy) new ReflectEnemy("derp", player, 100, 10, 0).
				setShieldDimensions(32, 128).setShieldSprite("res/Forms/Point/Idle", 100).
				setShieldStats(100, 200, 3).setRange(1000).
				setAnimationSet("res/Forms/Point/Idle", 100).setDimensions(64, 64).
				setAtkSpeed(100);
		
		derp.setPosition(player.getX(), player.getY());
		
		player.getMap().getLevel().addEntity(derp);
		
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
		if(atk.getCurrentIndex() == atk.getSet().size() - 1)
		{
			BasicProjectile shot = new BasicProjectile(player, 1).setSprite("res/Forms/Triangle/Projectile", 100).
					setDimensions(40, 40, 0).setLimit(1000);
			
			shot.setPosition(shadowX, shadowY);
			
			if(Math.abs(Rot) > 90)
			{
				shot.getSprite().setFlip(true);
			}
			
			shot.shoot((float) (M.cos(Rot) * speed), (float) (M.sin(Rot) * speed));
			atk.reset();
		}
	}
	
	@Override
	public void attack()
	{
		if(atk.getCurrentIndex() == atk.getSet().size() - 1)
		{
			BasicProjectile shot = new BasicProjectile(player, 1).setSprite("res/Forms/Triangle/Projectile", 100).
					setDimensions(40, 40, 0).setLimit(1000);
			
			if(Math.abs(rot) > 90)
			{
				shot.getSprite().setFlip(true);
			}
			
			shot.shoot((float) (M.cos(rot) * speed), (float) (M.sin(rot) * speed));
			atk.reset();
		}
	}

}

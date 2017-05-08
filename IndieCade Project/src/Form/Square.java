package Form;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Geo.M;
import Player.Player;
import Projectiles.BasicProjectile;
import Projectiles.SpinShot;
import Projectiles.SpreadShot;
import Render.AnimationSet;
import Render.BasicImage;

public class Square extends Form
{
	float range;
	
	BasicImage deflect;
	int factor1;
	
	boolean immortal;
	BasicImage shield;
	float armoro;
	
	BasicImage shockwave;
	int factor3;
	
	public Square(Player player) 
	{
		super(player);
		cd1 = 1000;
		cd2 = 6000;
		cd3 = 2000;
		range = 200;
		
		atkSpeed = 1000;
		
		height = 70  * 1.5F;
		width = 70  * 1.5F;
		
		factor1 = 0;
		deflect = new BasicImage("res/Forms/Square/Proj Deflect.png");
		
		armoro = player.getArmor();
		immortal = false;
		shield = new BasicImage("res/Forms/Square/Shield.png");
		
		factor3 = 0;
		shockwave = new BasicImage("res/Forms/Square/Shock Wave.png");
		
		idle = new AnimationSet("res/Forms/Square/Idle", 100);
		atk = new AnimationSet("res/Forms/Square/Attacking", (long) atkSpeed / 7);
		
		Icon1 = new BasicImage("res/Forms/Square/ability 1.png");
		Icon2 = new BasicImage("res/Forms/Square/ability 2.png");
		Icon3 = new BasicImage("res/Forms/Square/ability 3.png");
		
		speed = 30;
	}
	
	public void update()
	{
		super.update();
		
		if(immortal)
		{
			player.setArmor(100);
		}else
		{
			player.setArmor(armoro);
		}
		
		if(System.currentTimeMillis() - lastTick2 >= 2000)
		{
			immortal = false;
		}
	}
	
	@Override
	public void arender(float xOffset, float yOffset, Graphics g) throws SlickException
	{
		if(System.currentTimeMillis() - lastTick1 <= 100)
		{
			deflect.render(player.getX(), xOffset, player.getY(), yOffset,
					width + 20 + factor1, height + 20 + factor1, rot, g);
			factor1 += 50;
		}else
		{
			factor1 = 0;
		}
		
		if(immortal)
		{
			shield.render(player.getX(), xOffset, player.getY(), yOffset,
					width + 25, height + 25, rot, g);
		}
		
		if(System.currentTimeMillis() - lastTick3 <= 100)
		{
			shockwave.render(player.getX(), xOffset, player.getY(), yOffset,
					width + factor3, height + factor3, rot, g);
			factor3 += 50;
		}else
		{
			factor3 = 0;
		}
	}
	
	@Override
	public void idle()
	{
		super.idle();
		
		if(immortal)
		{
			player.setArmor(100);
		}else
		{
			player.setArmor(armoro);
		}
		
		if(System.currentTimeMillis() - lastTick2 >= 1000)
		{
			immortal = false;
		}
	}
	
	@Override
	public void ability1()
	{
		super.ability1();
		
		for(int i = 0; i < player.getMap().getLevel().getProjectiles().size(); i ++)
		{
			if(player.getMap().getLevel().getProjectiles().get(i).type ==  0 && 
					M.distance(player.getMap().getLevel().getProjectiles().get(i).getX(),
							player.getMap().getLevel().getProjectiles().get(i).getY(),
							player.getX(), player.getY()) <= range)
			{
				BasicProjectile shot = player.getMap().getLevel().getProjectiles().get(i).clone();
				float shpeed = (float) Math.sqrt(Math.pow(player.getMap().getLevel().getProjectiles().get(i).getVx(), 2)
						+Math.pow(player.getMap().getLevel().getProjectiles().get(i).getVy(), 2));
				float srot =  M.GetAngleOfLineBetweenTwoPoints(player.getX(), player.getY(), 
						player.getMap().getLevel().getProjectiles().get(i).getX(), 
						player.getMap().getLevel().getProjectiles().get(i).getY());
				shot.type = 1;
				shot.setShooter(null);
				shot.setPosition(player.getMap().getLevel().getProjectiles().get(i).getX(), 
						player.getMap().getLevel().getProjectiles().get(i).getY());
				shot.shoot((float) (shpeed *M.cos(srot)), (float) (shpeed * M.sin(srot)));
				player.getMap().getLevel().getProjectiles().get(i).end();
				
			}
		}
		
	}
	
	@Override
	public void ability2()
	{
		super.ability2();
		
		armoro = player.getArmor();
		immortal = true;
	}
	
	@Override
	public void ability3()
	{
		super.ability3();
		
		for(int i = 0; i < player.getMap().getLevel().getEntities().size(); i ++)
		{
			if(player.getMap().getLevel().getEntities().get(i).distanceSense(range, player))
			{
				player.getMap().getLevel().getEntities().get(i).setVelocity(
						- player.getMap().getLevel().getEntities().get(i).getVx() * 2, 
						- player.getMap().getLevel().getEntities().get(i).getVy() * 2);
			}
		}
	}
	
	@Override
	public void attack()
	{
		if(atk.getCurrentIndex() == atk.getSet().size() - 1)
		{
			if(atk.getCurrentIndex() == atk.getSet().size() - 1)
			{
				BasicProjectile shot = new SpreadShot(player, 2, 1).setSpread(90).
						setSplit(new BasicProjectile(player, 0.5F, 1).
								setSprite("res/Forms/Square/Projectile", 100).
								setDimensions(40 * 1.5F, 40 * 1.5F, -rot).setLimit(1000), 4, 10).
						setSprite("res/Forms/Square/Projectile", 100).
						setDimensions(40 * 1.5F, 40 * 1.5F, -rot).setLimit(250);
				shot.setShooter(null);
				
				
				shot.shoot((float) (M.cos(rot) * speed), (float) (M.sin(rot) * speed));
				atk.reset();
			}
		}
	}
}

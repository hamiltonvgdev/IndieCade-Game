package Bosses;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Form.Form;
import Form.Triangle;
import Geo.M;
import Player.Player;
import Projectiles.BasicProjectile;
import Render.AnimationSet;

public class TrigBoss extends Boss
{
	AnimationSet body;
	
	long actionTick;
	long shootTick;
	
	BasicProjectile shot1;
	float factor;
	BasicProjectile shot2;
	float shootSpeed;
	boolean alternate;
	
	BasicProjectile Translocator;
	long transCD;
	float transSpeed;
	boolean teleporting;
	
	public TrigBoss(Player player) 
	{
		super("Triangle Boss", player, 1000, 10, new Triangle(player));	
		
		atkSpeed = 100;
		
		width = 54 * 5;
		height = 64 * 5;
		
		body = new AnimationSet("res/Entities/TrigBoss/Body", 100);
		
		shot1 = new BasicProjectile(player, 1F, 0).
				setDimensions(8 * 5, 8 * 5, 0).
				setSprite("res/Entities/TrigBoss/Shot/Shot 1", 100);
		
		shot2 = new BasicProjectile(player, 1F, 0).
				setDimensions(8 * 5, 8 * 5, 0).
				setSprite("res/Entities/TrigBoss/Shot/Shot 2", 100);
		
		Translocator = new BasicProjectile(player, 0, 0).
				setDimensions(8 * 5, 8 * 5, 0).
				setSprite("res/Entities/TrigBoss/Translocator", 100).setLimit(1000); 
		teleporting = false;
		transSpeed = 15;
		transCD = 1500;
		actionTick = System.currentTimeMillis();
		
		shootSpeed = 20;
		
		alternate = false;
	}
	
	public void update()
	{
		super.update();
		
		if(System.currentTimeMillis() - actionTick >= transCD)
		{
			actionTick = System.currentTimeMillis();
			action();
		}
		
		if(System.currentTimeMillis() - atkTick >= 400)
		{
			if(alternate)
			{
				shoot1();
				alternate = false;
			}else
			{
				shoot2();
				alternate = true;
				
			}
			
			atkTick = System.currentTimeMillis();
		}
		
		
		if(player.getX() > x)
		{
			factor = 1;
		}
		
		if(player.getX() < x)
		{
			factor = -1;
		}
		
		if(teleporting && Translocator.getEnded() &&
				!map.getTile(Translocator.getX(), Translocator.getY()).getCollidable())
		{
			setPosition(Translocator.getX(), Translocator.getY());
		}
		
		rot = M.GetAngleOfLineBetweenTwoPoints(x, y, player.getX(), player.getY());
	}
	
	public void render(Graphics g, float xOffset, float yOffset) throws SlickException
	{
		body.render(x, xOffset, y, yOffset, width, height, rot, g);
	}
	
	public void action()
	{
		Translocator = Translocator.clone();
		
		if(factor == -1)
		{
			Translocator.setDimensions(Translocator.getWidth(), Translocator.getHeight(), -Translocator.getORot());
			Translocator.getSprite().setFlip(true);
		}else
		{
			Translocator.setDimensions(Translocator.getWidth(), Translocator.getHeight(), Translocator.getORot());
		}
		Translocator.setShooter(this);
		Translocator.shoot((float) (transSpeed * M.cos(rot)),
				(float) (transSpeed * M.sin(rot)));
		
		teleporting = true;
	}
	
	public void shoot2()
	{
		shot2 = shot2.clone();
		
		if(factor == -1)
		{
			shot2.setDimensions(shot2.getWidth(), shot2.getHeight(), -shot2.getORot());
			shot2.getSprite().setFlip(true);
		}else
		{
			shot2.setDimensions(shot2.getWidth(), shot2.getHeight(), shot2.getORot());
		}
		shot2.setShooter(this);
		
		shot2.setPosition((float) (x + 16 * 5 * M.cos(rot - 90)), (float) (y + 16 * 5 * M.sin(rot - 90)));
		
		shot2.shoot((float) (shootSpeed * M.cos(rot)),
				(float) (shootSpeed * M.sin(rot)));
		
	}
	
	public void shoot1()
	{
		shot1 = shot1.clone();
		
		if(factor == -1)
		{
			shot1.setDimensions(shot1.getWidth(), shot1.getHeight(), -shot1.getORot());
			shot1.getSprite().setFlip(true);
		}else
		{
			shot1.setDimensions(shot1.getWidth(), shot1.getHeight(), shot1.getORot());
		}
		shot1.setShooter(this);
		
		shot1.setPosition((float) (x + 16 * 5 * M.cos(rot + 90)), (float) (y + 16 * 5 * M.sin(rot + 90)));
		
		shot1.shoot((float) (shootSpeed * M.cos(rot)),
				(float) (shootSpeed * M.sin(rot)));
		
	}
	
	public TrigBoss clone()
	{
		return new TrigBoss(player);
	}
}	

package Bosses;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Form.Form;
import Form.Square;
import GameBasics.Entity;
import Geo.M;
import Player.Player;
import Projectiles.BasicProjectile;
import Projectiles.SpreadShot;
import Render.AnimationSet;

public class SquareBoss extends Boss
{
	ArrayList<AnimationSet> ShieldSprites;
	ArrayList<Geo.Hitbox> Shields;
	ArrayList<Float> ShieldHealths;
	
	AnimationSet body;
	float Brot;
	AnimationSet turret;
	
	BasicProjectile shot;
	float shootSpeed;
	float factor;
	long shootTick;
	
	float Shealth;
	float Sradius;
	float Srot;
	float Swidth;
	float Sheight;
	
	public SquareBoss(Player player) 
	{
		super("Square Boss", player, 1000, 100, new Square(player));
		
		Shealth = 500;
		Sradius = 200;
		Swidth = 8 * 10;
		Sheight = 22 * 10;
		Srot = 0;
		Brot = 0;
		
		shot = new SpreadShot(player, 0.75F, 0).setSpread(90).
				setSplit(new BasicProjectile(player, 1F, 0).
						setSprite("res/Entities/SquareBoss/Shot", 10).
						setDimensions(8 * 7.5F, 5 * 7.5F, 0), 4, 10).
						setDimensions(8 * 10, 5 * 10, 0).
						setSprite("res/Entities/SquareBoss/Shot", 10).setLimit(300);
		shootSpeed = 40;
		
		body = new  AnimationSet("res/Entities/SquareBoss/Body", 100);
		turret = new  AnimationSet("res/Entities/SquareBoss/Turret", 100);
		
		width = 27 * 10;
		height = 27 * 10;
		
		ShieldSprites = new ArrayList<AnimationSet>();
		
		for(int i = 0; i <
				4; i ++)
		{
			ShieldSprites.add(new AnimationSet("res/Entities/SquareBoss/Shield", 100));
		}
		
		Shields = new ArrayList<Geo.Hitbox>();
		
		for(int i = 0; i < ShieldSprites.size(); i ++)
		{
			Shields.add(new Geo.Hitbox(ShieldSprites.get(i).getCurrentFrame()));
		}
		
		ShieldHealths = new ArrayList<Float>();
		
		for(int i = 0; i < ShieldSprites.size(); i ++)
		{
			ShieldHealths.add(Shealth);
		}
		
		setMove(2, 2);
	}
	
	public void update()
	{
		super.update();
		
		Srot ++;
		Brot -= 2;
		
		for(int i = 0; i < Shields.size(); i ++)
		{
			Shields.get(i).update(ShieldSprites.get(i).getCurrentFrame());
		}
		
		for(int i = 0; i < Shields.size(); i ++)
		{
			if(ShieldHealths.get(i) > 0)
			{
				for(int j = 0; j < map.getLevel().getProjectiles().size(); j ++)
				{
					if(map.getLevel().getProjectiles().get(j).type == 1)
					{
						if(Shields.get(i).check(map.getLevel().getProjectiles().get(j).getHitbox()))
						{
							ShieldHealths.set(i, ShieldHealths.get(i) - player.getDamage());
							shield(map.getLevel().getProjectiles().get(j));
						}
					}
				}
				
				if(Shields.get(i).check(player.getHitbox()))
				{
					atk();
				}
			}
		}
		
		if(System.currentTimeMillis() - shootTick > 5000)
		{
			shoot();
			shootTick = System.currentTimeMillis();
		}
		
		follow(player);
		
		if(player.getX() > x)
		{
			factor = 1;
		}
		
		if(player.getX() < x)
		{
			factor = -1;
		}
		
		body.resetAnimate();
		
		rot = M.GetAngleOfLineBetweenTwoPoints(x, y, player.getX(), player.getY());
	}
	
	public void render(Graphics g, float xOffset, float yOffset)throws SlickException
	{
		for (int i = 0; i < ShieldSprites.size(); i++) 
		{
			if(ShieldHealths.get(i) > 0)
			{
				ShieldSprites.get(i).render((float) (x + Sradius * M.cos(Srot + i * 90)), xOffset, 
						(float) (y + Sradius * M.sin(Srot + i * 90)), yOffset, Swidth, Sheight, Srot + i * 90, g);
			}
		}
		
		body.render(x, xOffset, y, yOffset, width, height, Brot, g);
		turret.render(x, xOffset, y, yOffset, width * 29 / 27, height * 29 / 27, rot, g);
	}
	
	public void shield(BasicProjectile blocked)
	{
		Entity derp = new Entity("dummy", player, 0, player.getDamage());
		derp.setPosition(blocked.getX(), blocked.getY());
		
		blocked.setType(0);
		blocked.setShooter(derp);
		blocked.shoot(-blocked.getVx() / 2, -blocked.getVy() / 2);
	}
	
	public void shoot()
	{
		shot = shot.clone();
		
		if(factor == -1)
		{
			shot.setDimensions(shot.getWidth(), shot.getHeight(), -shot.getORot());
			shot.getSprite().setFlip(true);
		}else
		{
			shot.setDimensions(shot.getWidth(), shot.getHeight(), shot.getORot());
		}
		shot.setShooter(this);
		shot.shoot((float) (shootSpeed * M.cos(M.GetAngleOfLineBetweenTwoPoints(x, y, player.getX(), player.getY()))),
				(float) (shootSpeed * M.sin(M.GetAngleOfLineBetweenTwoPoints(x, y, player.getX(), player.getY()))));
		
		shootTick = System.currentTimeMillis();
	}

	public SquareBoss clone()
	{
		return new SquareBoss(player);
	}
}

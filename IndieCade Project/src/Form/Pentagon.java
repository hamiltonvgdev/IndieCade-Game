package Form;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Geo.Hitbox;
import Geo.M;
import Geo.Point;
import Player.Player;
import Projectiles.BasicProjectile;
import Projectiles.HomingProjectile;
import Projectiles.SpinShot;
import Projectiles.SplitShot;
import Projectiles.ThingShot;
import Render.AnimationSet;
import Render.BasicImage;
import Thing.Sentry;
import Thing.Turret;
import Thing.Wall;

public class Pentagon extends Form
{
	ArrayList<Wall> walls;
	long wallTick;
	
	boolean desecrate;
	AnimationSet desecration;
	Hitbox dese;
	float damageo;
	float desx;
	float desy;
	
	float range;
	boolean bh;
	float bx;
	float by;
	float bxOffset;
	float byOffset;
	AnimationSet blackhole;
	Turret turret;
	
	public Pentagon(Player player)
	{
		super(player);
		
		walls = new ArrayList<Wall>();
		wallTick = System.currentTimeMillis();
		
		atkSpeed = 700;
		
		cd1 = 500;
		cd2 = 20000;
		cd3 = 3000;
		//range = 500;
		
		desecrate = false;
		speed = 20;
		
		desecration = new AnimationSet("res/Forms/Pentagon/Desecration", 100);
		dese = new Hitbox(desecration.getCurrentFrame());
		damageo = 10;
		
		blackhole = new AnimationSet("res/Forms/Pentagon/Black Hole/BlackHole", 500 / 8);
		
		idle = new AnimationSet("res/Forms/Pentagon/Idle", 100);
		atk = new AnimationSet("res/Forms/Pentagon/Attacking", (long) atkSpeed / 10);
		
		width = 31 * 4 * 0.75F;
		height = 31 * 4 * 0.75F;
		
		Icon1 = new BasicImage("res/Forms/Pentagon/ability 1.png");
		Icon2 = new BasicImage("res/Forms/Pentagon/ability 2.png");
		Icon3 = new BasicImage("res/Forms/Pentagon/ability 3.png");
	}
	
	public void update()
	{
		super.update();
		
		if(System.currentTimeMillis() - wallTick > 5000)
		{
			if(walls.size() > 0)
			{
				walls.remove(0);
			}
			
			wallTick = System.currentTimeMillis();
		}
		
		for(int i = 0; i < walls.size(); i ++)
		{
			walls.get(i).getSprite().endAnimate();
		}
		
		if(available2)
		{
			desecrate = false;
		}
		
		if(desecrate)
		{
			dese.update(desecration.getCurrentFrame());
			desecration.resetAnimate();
			
			if(dese.check(new Point(player.getX(), player.getY())))
			{
				player.setDamage(damageo * 2);
			}else
			{
				player.setDamage(damageo);
			}
		}
		
		
		if(turret != null)
		{
			turret.getSprite().endAnimate();
		}
		
		
		if(bh && System.currentTimeMillis() - lastTick3 > 500)
		{
			for(int i = 0; i < player.getMap().getLevel().getEntities().size(); i ++)
			{
				if(M.distance(bx + player.getXOffset() - bxOffset, by + player.getYOffset() - byOffset, 
						player.getMap().getLevel().getEntities().get(i).getX(), 
						player.getMap().getLevel().getEntities().get(i).getY()) <= range)
				{
					float Rot = M.GetAngleOfLineBetweenTwoPoints
							(bx + player.getXOffset() - bxOffset, by + player.getYOffset() - byOffset,
									player.getMap().getLevel().getEntities().get(i).getX() + player.getXOffset(),
									player.getMap().getLevel().getEntities().get(i).getY() + player.getYOffset());
					
					player.getMap().getLevel().getEntities().get(i).setVelocity(
							(float) -(M.cos(Rot) * 25), (float) -(M.sin(Rot) * 25));
				}
			}
			
			bh = false;
		}
	}
	
	public void idle()
	{
		super.idle();
		
		if(System.currentTimeMillis() - wallTick > 5000)
		{
			if(walls.size() > 0)
			{
				walls.remove(0);
			}
			
			wallTick = System.currentTimeMillis();
		}
		
		if(walls.size() <= 0)
		{
			wallTick = System.currentTimeMillis();
		}
		
		for(int i = 0; i < walls.size(); i ++)
		{
			walls.get(i).getSprite().endAnimate();
		}
		
		if(available2)
		{
			desecrate = false;
		}
		
		if(desecrate)
		{
			dese.update(desecration.getCurrentFrame());
			desecration.resetAnimate();
			
			if(dese.check(new Point(player.getX(), player.getY())))
			{
				player.setDamage(damageo * 2);
			}else
			{
				player.setDamage(damageo);
			}
		}
	}
	
	@Override
	public void reset()
	{
		desecrate = false;
		for(int i = walls.size() - 1; i >= 0 ; i --)
		{
			player.getMap().getLevel().removeThing(walls.get(i));
			walls.remove(i);
		}
	}
	
	@Override
	public void arender(float xOffset, float yOffset, Graphics g) throws SlickException
	{
		if(!available3)
		{
			blackhole.render(bx, player.getXOffset() - bxOffset, by, player.getYOffset() - byOffset, 
					200, 200, 0, g);
			blackhole.endAnimate();
		}else
		{
			blackhole.reset();
		}
	}
	
	@Override
	public void render(float x, float xOffset, float y, float yOffset, Graphics g) throws SlickException
	{
		super.render(x, xOffset, y, yOffset, g);
	}
	
	@Override
	public void brender(float xOffset, float yOffset, Graphics g) throws SlickException
	{
		if(desecrate)
		{
			desecration.render(desx, xOffset, desy, yOffset, 500, 500, 0, g);
		}
	}
	
	@Override
	public void ability1()
	{
		super.ability1();
		Wall wall = (Wall) new Wall("res/Forms/Pentagon/Wall", 0).setDimension(64, 64, 0).
				setTile(player.getMap().getTile(Mouse.getX() - 
						player.getXOffset(), M.toRightHandY(Mouse.getY()) - player.getYOffset())).
				setDimension(64, 64, 0).setAge(5000).setCollidable(true, false);
		
		player.getMap().getLevel().addThing(wall);
		walls.add(wall);
	}
	
	@Override
	public void ability2()
	{
		super.ability2();
		
		desecrate = true;
		desx = player.getX();
		desy = player.getY();
	}
	
	@Override
	public void ability3()
	{
		super.ability3();
		
		bh = true;
		bx = Mouse.getX();
		by = M.toRightHandY(Mouse.getY());
		
		bxOffset = player.getXOffset();
		byOffset = player.getYOffset();
		
		turret = (Turret) new Turret("res/Forms/Pentagon/Black Hole/Turret/Base", 100, player, 0).
				setProjectile(new SplitShot(player, 0, 1).setOffset(-90).
						setSplit(new BasicProjectile(player, 0.5F, 1).
								setSprite("res/Forms/Pentagon/Black Hole/Turret/Projectile", 100).
								setDimensions(5 * 4, 5 * 4, -rot).setLimit(1000), 5, 10).
						setSprite("res/Forms/Pentagon/Black Hole/Turret/Projectile", 100).
						setDimensions(40, 40, -rot).setLimit(0), 10, 500).
				setDimension(64, 64, 0).
				setTile(player.getMap().getTile(Mouse.getX() - 
						player.getXOffset(), M.toRightHandY(Mouse.getY()) - player.getYOffset())).
				setDimension(32 * 4, 32 * 4, 0).setAge(2000).setCollidable(false, true);
		
		turret.setPosition(bx + player.getXOffset() - bxOffset, by + player.getYOffset() - byOffset);
		player.getMap().getLevel().addThing(turret);
	}
	
	@Override
	public void attack()
	{
		if(atk.getCurrentIndex() == atk.getSet().size() - 1)
		{
			
			ThingShot shot = (ThingShot) new ThingShot(player, 0, 1).
					setThing(new Sentry("res/Forms/Pentagon/Projectile/Sentry", 100, player, 1).
							setProjectile(new BasicProjectile(player, 0.2F, 1).
									setSprite("res/Forms/Pentagon/Projectile/Sentry Atk", 100).
									setDimensions(3 * 4, 3 * 4, 0).setLimit(1000), 60, 1000).
							setDimension(11 * 4, 13 * 4, 90).setAge(5000).setCollidable(false, true)
					, 0.6F).
					setSprite("res/Forms/Pentagon/Projectile/Base", 10).
					setDimensions(10 * 4 * 0.75F, 10 * 4 * 0.75F, 0).setLimit(750);
					
			shot.setShooter(null);
			
			if(Math.abs(rot) > 90)
			{
				shot.getSprite().setFlip(true);
			}
			
			shot.shoot((float) (M.cos(rot) * speed), (float) (M.sin(rot) * speed));
			atk.reset();
		}
	}
}

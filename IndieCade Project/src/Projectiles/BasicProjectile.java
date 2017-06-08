package Projectiles;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import GameBasics.Entity;
import Geo.Hitbox;
import Geo.Point;
import Geo.Quad;
import Geo.QuadR;
import Main.Config;
import Player.Player;
import Render.AnimationSet;

public class BasicProjectile 
{
	Player player;
	Entity entity;
	public int type;
	float damage;
	
	float x;
	float y;
	float Vx;
	float Vy;
	float Ax;
	float Ay;
	
	AnimationSet sprite;
	float width;
	float height;
	float rot;
	float oRot;
	
	QuadR hitbox;
	
	boolean ended;
	long age;
	long limit;
	
	public BasicProjectile(Player player, float damage, int type)
	{
		this.player = player;
		
		rot = 0;
		
		Ax = 0;
		Ay = 0;
		
		ended = false;
		age = System.currentTimeMillis();
		limit = -1;
		
		
		this.type = type;
		
		this.damage = damage;
	}
	
	public BasicProjectile setPosition(float xa, float ya)
	{
		x = xa;
		y = ya;
		return this;
	}
	
	public BasicProjectile setSprite(String ref, long delay)
	{
		sprite = new AnimationSet(ref, delay);
		return this;
	}
	
	public BasicProjectile setDimensions(float width, float height, float rot)
	{
		this.width = width;
		this.height = height;
		this.oRot = rot;
		hitbox = new QuadR(x, y, width, height, rot);
		return this;
	}
	
	public BasicProjectile setLimit(long limit)
	{
		this.limit = limit;
		return this;
	}
	
	public BasicProjectile setGravity(float ya)
	{
		Ay = ya;
		return this;
	}
	
	public void setShooter(Entity ent)
	{
		entity = ent;
		
		if(type == 0)
		{
			x = ent.getX();
			y = ent.getY();
		}else if(type == 1)
		{
			x = player.getX();
			y = player.getY();
		}
	}
	
	public void update()
	{
	 	if(ended)
		{
			end();
		}
		
		hitbox.update(x, y, width, height, rot + oRot);
		
		Vx += Ax;
		Vy += Ay;
		
		x += Vx;
		y += Vy;
		
		if(type == 0)
		{
			if(player.getHitbox().checkQuadR(hitbox))
			{
				player.damage((int) entity.getDamage() * damage);
				ended = true;
			}
		}else if(type == 1)
		{
			for(int i = 0; i < player.getMap().getLevel().getEntities().size(); i ++)
			{
				if(hitbox.check(player.getMap().getLevel().getEntities().get(i).getHitbox()))
				{
					player.getMap().getLevel().getEntities().get(i).damage(player.getDamage() * damage);
					ended = true;
				}
			}
		}
		
		if(limit >= 0 && System.currentTimeMillis() - age >= limit)
		{
			ended = true;
		}
		
		if(Math.abs(x - player.getX()) >= Config.WIDTH / 2 &&
				Math.abs(y - player.getY()) >= Config.HEIGHT / 2)
		{
			end();
		}
		
		rot = (float) Math.toDegrees(Math.atan(Vy / Vx));
		
		if(Vx < 0)
		{
			getSprite().setFlip(true);
		}else
		{
			getSprite().setFlip(false);
		}
		
		sprite.loopAnimate();
	}
	
	public void render(Graphics g, float xOffset, float yOffset) throws SlickException
	{
		sprite.render(x, xOffset, y, yOffset, width, height, oRot + rot, g);
		
		//hitbox.render(g);
	}
	
	public void end()
	{
		player.getMap().getLevel().removeProjectile(this);
	}
	
	public void move(float xa, float ya)
	{
		x += xa;
		y += ya;
	}
	
	public void shoot(float xa, float ya)
	{
		Vx = xa;
		Vy = ya;
		player.getMap().getLevel().addProjectile(this);
	}
	
	public void setType(int type)
	{
		this.type = type;	
	}
	
	public void setVelocity(float xa, float ya)
	{
		Vx = xa;
		Vy = ya;
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public float getVx()
	{
		return Vx;
	}
	
	public float getVy()
	{
		return Vy;
	}
	
	public float getAx()
	{
		return Ax;
	}
	
	public float getAy()
	{
		return Ay;
	}
	
	public float getHeight() 
	{
		return height;
	}
	
	public float getWidth()
	{
		return width;
	}
	
	public float getORot()
	{
		return oRot;
	}
	
	public float getRot()
	{
		return rot;
	}
	
	public float getTotalRot()
	{
		return rot + oRot;
	}
	
	public boolean getEnded()
	{
		return ended;
	}
	
	public AnimationSet getSprite()
	{
		return sprite;
	}
	
	public BasicProjectile clone()
	{
		return new BasicProjectile(player, damage, type).
				setDimensions(width, height, oRot).setSprite(sprite.getFolder(), sprite.getDelay()).
				setGravity(Ay).setLimit(limit);
	}

	public QuadR getHitbox() 
	{
		return hitbox;
	}
}

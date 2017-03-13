package Projectiles;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import GameBasics.Entity;
import Geo.Quad;
import Player.Player;
import Render.AnimationSet;
import Weapons.RangedWeapon;
import Weapons.Weapon;

public class BasicProjectile 
{
	Player player;
	RangedWeapon weapon;
	Entity entity;
	int type;
	
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
	
	Quad hitbox;
	
	public BasicProjectile(Player player, int type)
	{
		this.player = player;
		
		rot = 0;
		
		Ax = 0;
		Ay = 0;
		
		hitbox = new Quad(x, y, width, height);
		
		this.type = type;
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
		this.rot = rot;
		return this;
	}
	
	public void setShooter(Entity ent, RangedWeapon weapon)
	{
		this.weapon = weapon;
		entity = ent;
		
		if(type == 0)
		{
			x = ent.getX();
			y = ent.getY();
		}else if(type == 1)
		{
			x = weapon.getX();
			y = weapon.getY();
		}
	}
	
	public void update()
	{
		hitbox.changeDimensions(x, y, width, height);
		
		Vx += Ax;
		Vy += Ay;
		
		x += Vx;
		y += Vy;
		
		if(type == 0)
		{
			for(Quad hitbox: player.getHitboxes())
			{
				if(hitbox.checkQuad(this.hitbox))
				{
					player.damage((int) entity.getDamage());
					end();
					
					break;
				}
			}
		}else if(type == 1)
		{
			for(int i = 0; i < player.getMap().getLevel().getEntities().size(); i ++)
			{
				if(player.getMap().getLevel().getEntities().get(i).Hitbox.checkQuad(hitbox))
				{
					player.getMap().getLevel().getEntities().get(i).damage(weapon.getDamage());
					weapon.affect();
					end();
				}
			}
		}
	}
	
	public void render(Graphics g, float xOffset, float yOffset) throws SlickException
	{
		sprite.render(x + xOffset, y + yOffset, width, height, rot, g);
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
	
	public void shoot(float xa)
	{
		Vx = xa;
		player.getMap().getLevel().addProjectile(this);
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
	
	public float getRot()
	{
		return rot;
	}
	
	public AnimationSet getSprite()
	{
		return sprite;
	}
}

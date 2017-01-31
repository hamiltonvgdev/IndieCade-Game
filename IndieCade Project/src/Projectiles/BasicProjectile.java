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
	
	public BasicProjectile(Player player, Entity ent, RangedWeapon weapon, int type)
	{
		this.player = player;
		
		this.weapon = weapon;
		
		x = weapon.getX();
		y = weapon.getY();
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
	
	public BasicProjectile setDimensions(float width, float height)
	{
		this.width = width;
		this.height = height;
		return this;
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
			//Code for attacking player
			/*if()
			{
				
			}*/
		}else if(type == 1)
		{
			for(int i = 0; i < player.getMap().getLevel().getEntities().size(); i ++)
			{
				if(player.getMap().getLevel().getEntities().get(i).Hitbox.checkQuad(hitbox))
				{
					player.getMap().getLevel().getEntities().get(i).damage(weapon.getDamage());
				}
			}
		}
	}
	
	public void render(Graphics g) throws SlickException
	{
		sprite.render(x, y, width, height, rot, g);
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
}

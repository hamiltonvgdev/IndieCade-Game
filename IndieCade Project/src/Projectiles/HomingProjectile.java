package Projectiles;

import GameBasics.Entity;
import Geo.M;
import Player.Player;

public class HomingProjectile extends BasicProjectile
{
	Entity target;
	float acceleration;
	
	float Tx = 0;
	float Ty = 0;
	
	public HomingProjectile(Player player, float damage, int type)
	{
		super(player, damage, type);
	}
	
	public HomingProjectile setAcceleration(float xa)
	{
		acceleration = xa;
		
		return this;
	}
	
	@Override
	public void update()
	{	
		if(type == 0)
		{
			Tx = player.getX();
			Ty = player.getY();
		}else if(type == 1)
		{
			Tx = target.getX();
			Ty = target.getY();		
		}
		
		Vx += acceleration * M.cos(M.GetAngleOfLineBetweenTwoPoints(x, y, Tx, Ty));
		Vy += acceleration * M.sin(M.GetAngleOfLineBetweenTwoPoints(x, y, Tx, Ty));
		
		super.update();
		
		if(Vx < 0)
		{
			oRot = 270;
		}else
		{
			oRot = 90;
		}
	}
	
	@Override
	public void shoot(float xa, float ya)
	{
		scan();
		super.shoot(xa, ya);
	}
	
	public void scan()
	{
		if(type == 1)
		{
			target= new Entity("dummy", player, 0, 0);
			target.setPosition(-30000, -30000);
			
			for(int i = 0; i < player.getMap().getLevel().getEntities().size(); i ++)
			{
				if(Math.min(M.distance(x, y, target.getX(), target.getY()), 
						M.distance(x, y, player.getMap().getLevel().getEntities().get(i).getX(), 
								player.getMap().getLevel().getEntities().get(i).getY()))== M.distance(x, y, 
										player.getMap().getLevel().getEntities().get(i).getX(), 
										player.getMap().getLevel().getEntities().get(i).getY()))
										{
											target = player.getMap().getLevel().getEntities().get(i);
										}
			}
		}
	}
	
	@Override
	public HomingProjectile clone()
	{
		return (HomingProjectile) new HomingProjectile(player, damage, type).setAcceleration(acceleration).
				setDimensions(width, height, oRot).setSprite(sprite.getFolder(), sprite.getDelay()).
				setGravity(Ay).setLimit(limit);
	}
}

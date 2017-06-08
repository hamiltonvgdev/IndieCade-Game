package Enemy;

import Geo.M;
import Player.Player;
import Projectiles.BasicProjectile;
import Render.AnimationSet;

public class ShootEnemy extends Enemy
{
	//An enemy that shoot the player if player is within a certain range
	
	BasicProjectile proj;
	float xa;
	int factor;
	long shootTick;
	
	public ShootEnemy(String name, Player player, float health, float damage)
	{
		super(name, player, health, damage);
		
		factor = 1;
		
		shootTick = System.currentTimeMillis();
		
		id = 2;
	}
	
	public void update()
	{
		super.update();
		
		if(player.getX() > x)
		{
			factor = 1;
		}
		
		if(player.getX() < x)
		{
			factor = -1;
		}
	}
	
	public ShootEnemy setProjectile(BasicProjectile proj, float xa)
	{	
		this.proj = proj;
		proj.setShooter(this);
		
		this.xa = xa;
		return this;
	}
	
	@Override
	protected void action()
	{
		if(System.currentTimeMillis() - shootTick > atkSpeed)
		{
			proj = proj.clone();
			
			if(factor == -1)
			{
				proj.setDimensions(proj.getWidth(), proj.getHeight(), -proj.getORot());
				proj.getSprite().setFlip(true);
			}else
			{
				proj.setDimensions(proj.getWidth(), proj.getHeight(), proj.getORot());
			}
			proj.setShooter(this);
			proj.shoot((float) (xa * M.cos(M.GetAngleOfLineBetweenTwoPoints(x, y, player.getX(), player.getY()))),
					(float) (xa * M.sin(M.GetAngleOfLineBetweenTwoPoints(x, y, player.getX(), player.getY()))));
			
			shootTick = System.currentTimeMillis();
		}
	}
	
	public BasicProjectile getProjectile()
	{
		return proj.clone();
	}
	
	public float getPheight()
	{
		return proj.getHeight();
	}
	
	public float getXa()
	{
		return xa;
	}
	
	public ShootEnemy clone()
	{
		if(triggered == null)
		{
			triggered = sprite;
		}
		
		return (ShootEnemy) new ShootEnemy(name, player, health, damage).setProjectile(proj, xa).
				setRange(range).setTriggeredAnimation(triggered.getFolder(), triggered.getDelay()).
				setDimensions(width, height).setAnimationSet(sprite.getFolder(), sprite.getDelay()).
				setAtkSpeed(atkSpeed).setMove(speed, acceleration);
	}
}

package Enemy;

import Player.Player;
import Projectiles.BasicProjectile;
import Render.AnimationSet;

public class ShootEnemy extends Enemy
{

	BasicProjectile proj;
	float xa;
	float pHeight;
	float pWidth;
	float pRot;
	String pRef;
	long pDelay;
	int factor;
	long shootTick;
	
	public ShootEnemy(Player player, float health, float damage, float speed)
	{
		super(player, health, damage, speed);
		
		factor = 1;
		
		shootTick = System.currentTimeMillis();
		
		id = 2;
	}
	
	public ShootEnemy setProjectile(String ref, long delay, float width, float height, float rot, float xa)
	{
		pDelay = delay;
		pHeight = height;
		pRef = ref;
		pRot = rot;
		pWidth = width;
		this.xa = xa;
		
		proj = new BasicProjectile(player, 0).setSprite(pRef, pDelay).
				setDimensions(pWidth, pHeight, pRot);
		proj.setShooter(this, null);
		
		return this;
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
		pRef = proj.getSprite().getFolder();
		pDelay = proj.getSprite().getDelay();
		pHeight = proj.getHeight();
		pWidth = proj.getWidth();
		pRot = proj.getRot();
		
		proj = new BasicProjectile(player, 0).setSprite(pRef, pDelay).
				setDimensions(pWidth, pHeight, pRot);
		proj.setShooter(this, null);
		
		this.xa = xa;
		return this;
	}
	
	@Override
	protected void action()
	{
		if(System.currentTimeMillis() - shootTick > atkSpeed)
		{
			if(factor == -1)
			{
				proj = new BasicProjectile(player, 0).setSprite(pRef, pDelay).
						setDimensions(pWidth, pHeight, -pRot);
				proj.getSprite().setFlip(true);
			}else
			{
				proj = new BasicProjectile(player, 0).setSprite(pRef, pDelay).
						setDimensions(pWidth, pHeight, pRot);
			}
			proj.setShooter(this, null);
			proj.shoot(factor * xa);
			
			shootTick = System.currentTimeMillis();
		}
	}
	
	public BasicProjectile getProjectile()
	{
		proj = new BasicProjectile(player, 0).setSprite(pRef, pDelay).
				setDimensions(pWidth, pHeight, pRot);
		return proj;
	}
	
	public float getPheight()
	{
		return pHeight;
	}
	
	public float getPwidth()
	{
		return pWidth;
	}
	
	public float getProt()
	{
		return pRot;
	}
	
	public AnimationSet getPsprite()
	{
		return new AnimationSet(pRef, pDelay);
	}
	
	public float getXa()
	{
		return xa;
	}
}

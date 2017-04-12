package Projectiles;

import Player.Player;

public class SplitShot extends BasicProjectile
{
	int split;
	float Vsplit;
	BasicProjectile Split;
	
	public SplitShot(Player player, int type) 
	{
		super(player, type);
		limit = 0;
	}
	
	public SplitShot setSplit(BasicProjectile Split, int split, float Vsplit)
	{
		this.Split = Split;
		this.split = split;
		this.Vsplit = Vsplit;
		return this;
	}
	
	public void update()
	{
		super.update();
		if(ended)
		{	
			for(int i = 0; i < split; i ++)
			{	
				BasicProjectile shot;
				if(360 / split * i >= 180)
				{
					shot = new BasicProjectile(player, type).
							setSprite(sprite.getFolder(), sprite.getDelay()).setDimensions(width, height, oRot);
					shot.setShooter(entity);
					shot.setPosition(x, y);
					shot.getSprite().setFlip(true);
				}else
				{
					shot = new BasicProjectile(player, type).
							setSprite(sprite.getFolder(), sprite.getDelay()).setDimensions(width, height, -oRot);
					shot.setShooter(entity);
					shot.setPosition(x, y);
					shot.getSprite().setFlip(false);
				}
				
				shot.shoot((float) (Vsplit * Math.cos(Math.toRadians(360 / split * i))),
						(float) (Vsplit * Math.sin(Math.toRadians(360 / split * i))));
			}
		}
	}
	
	public SplitShot clone()
	{
		return (SplitShot) new SplitShot(player, type).setSplit(Split, split, Vsplit).
				setGravity(Ay).setLimit(limit).setDimensions(width, height, oRot).
				setSprite(sprite.getFolder(), sprite.getDelay());
	}
}

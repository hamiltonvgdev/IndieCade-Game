package Projectiles;

import Geo.M;
import Player.Player;

public class SpreadShot extends SplitShot
{
	float cone;
	
	public SpreadShot(Player player, float damage, int type) 
	{
		super(player, damage, type);
	}

	
	public SpreadShot setSpread(float cone)
	{
		this.cone = cone;
		return this;
	}
	
	@Override
	public void split()
	{
		for(int i = - split / 2; i <= split / 2; i ++)
		{
			BasicProjectile shot = Split.clone();
			
			if(rot + cone / split * i >= 180)
			{
				shot.setShooter(entity);
				shot.setPosition(x, y);
				shot.getSprite().setFlip(true);
			}else
			{
				shot.setShooter(entity);
				shot.setPosition(x, y);
				shot.getSprite().setFlip(false);
			}
			
			shot.shoot((float) (Vsplit * M.cos(rot + cone / split * i) * Vx/ Math.abs(Vx)), 
					(float) (Vsplit * M.sin(rot + cone / split * i)) * Vx/ Math.abs(Vx));
		}
	}
	
	public SpreadShot clone()
	{
		return (SpreadShot) new SpreadShot(player, damage, type).setSpread(cone).setSplit(Split, split, Vsplit).
				setGravity(Ay).setLimit(limit).setDimensions(width, height, oRot).
				setSprite(sprite.getFolder(), sprite.getDelay());
	}
}

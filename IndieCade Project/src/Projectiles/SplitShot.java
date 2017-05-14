package Projectiles;

import org.lwjgl.input.Mouse;

import Geo.M;
import Player.Player;

public class SplitShot extends BasicProjectile
{
	int split;
	float Vsplit;
	BasicProjectile Split;
	boolean splitted;
	
	float offset;
	
	public SplitShot(Player player, float damage, int type) 
	{
		super(player, damage, type);
		limit = 0;
		splitted = false;
	}
	
	public SplitShot setSplit(BasicProjectile Split, int split, float Vsplit)
	{
		this.Split = Split;
		this.split = split;
		this.Vsplit = Vsplit;
		return this;
	}
	
	public SplitShot setOffset(float offset)
	{
		this.offset = offset;
		return this;
	}
	
	public void update()
	{
		super.update();
		
		if(ended && !splitted)
		{	
			split();
			splitted = true;
		}
	}
	
	public void split()
	{
		for(int i =0; i < split; i ++)
		{		
			BasicProjectile shot = Split.clone();
			
			if(360 / split * i >= 180)
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
			
			shot.shoot((float) (Vsplit * Math.cos(Math.toRadians((360) / split * i + offset))),
					(float) (Vsplit * Math.sin(Math.toRadians((360) / split * i + offset))));
		}
	}
	
	public SplitShot clone()
	{
		return (SplitShot) new SplitShot(player, damage, type).setOffset(offset).setSplit(Split, split, Vsplit).
				setGravity(Ay).setLimit(limit).setDimensions(width, height, oRot).
				setSprite(sprite.getFolder(), sprite.getDelay());
	}
}

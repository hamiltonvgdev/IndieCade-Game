package Enemy;

import GameBasics.Entity;
import Geo.M;
import Player.Player;

public class Guardian extends ShieldEnemy
{

	Entity target;
	
	public Guardian(String name, Player player, float health, float damage) 
	{
		super(name, player, health, damage);
	}
	
	public void update()
	{
		super.update();
		
		if(target == null)
		{
			scan();
		}else
		{
			follow(target);
			
			if(target.dead)
			{
				target = null;
			}
		}
	}
	
	public void scan()
	{
		target = new Entity("dummy", null, 0, 0);
		target.setPosition(-3000, -3000);
		
		for(int i = 0; i < player.getMap().getLevel().getEntities().size(); i ++)
		{
			if(Math.min(M.distance(x, y, target.getX(), target.getY()), 
					M.distance(x, y, player.getMap().getLevel().getEntities().get(i).getX(), 
							player.getMap().getLevel().getEntities().get(i).getY()))== M.distance(x, y, 
									player.getMap().getLevel().getEntities().get(i).getX(), 
									player.getMap().getLevel().getEntities().get(i).getY()))
									{
										if(!player.getMap().getLevel().getEntities().get(i).fake &&
												player.getMap().getLevel().getEntities().get(i) != this)
										{
											target = player.getMap().getLevel().getEntities().get(i);
										}
									}
		}
		
		if(target.getX() == -3000 && target.getY() == -3000)
		{
			target = null;
		}
	}

	@Override
	public Guardian clone()
	{
		if(triggered == null)
		{
			triggered = sprite;
		}
		
		return (Guardian) new Guardian(name, player, health, damage).
				setShieldDimensions(Swidth, Sheight).setShieldStats(Shealth, Sradius, Sspeed).
				setShieldSprite(ShieldSprite.getFolder(), ShieldSprite.getDelay()).
				setRange(range).setTriggeredAnimation(triggered.getFolder(), triggered.getDelay()).
				setDimensions(width, height).setAnimationSet(sprite.getFolder(), sprite.getDelay()).
				setAtkSpeed(atkSpeed).setMove(speed, acceleration).setMove(speed, acceleration);
	}
}

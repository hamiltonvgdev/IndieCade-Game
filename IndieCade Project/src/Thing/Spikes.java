package Thing;

import Player.Player;
import Tiles.Tile;

public class Spikes extends ActThing
{
	float damage;
	float atkspeed;
	long atkTick;
	
	public Spikes(String ref, long delay, Player player, int type) 
	{
		super(ref, delay, player, type);
		
		atkTick = System.currentTimeMillis();
	}
	
	public Spikes setAtk(float dmg, float atks)
	{
		damage = dmg;
		atkspeed = atks;
		return this;
	}
	
	@Override
	public void update()
	{
		super.update();
		
		if(System.currentTimeMillis() - atkTick >= atkspeed)
		{
			if(type == 0)
			{
				if(tile.getHitbox().checkPoint(player.getX(), player.getY()))
				{
					player.damage(damage);
				}
			}else if(type == 1)
			{
				for(int i = 0; i < tile.getMap().getLevel().getEntities().size(); i ++)
				{
					if(tile.getMap().getLevel().getEntities().get(i).getSpeed() >= 0 &&
							tile.getHitbox().checkQuad(tile.getMap().getLevel().getEntities().get(i).Hitbox))
					{
						tile.getMap().getLevel().getEntities().get(i).damage(damage);
					}
				}
			}
			
			atkTick = System.currentTimeMillis();
		}
	}
	
	@Override
	public Spikes clone(Tile tile)
	{
		return (Spikes) (new Spikes(sprite.getFolder(), sprite.getDelay(), player, type).setAtk(damage, atkspeed).
				setDimension(width, height, rot).setTile(tile).setCollidable(collide, permanent).setAge(age));
	}

}

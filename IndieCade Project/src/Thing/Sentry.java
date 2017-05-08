package Thing;

import org.lwjgl.input.Mouse;

import GameBasics.Entity;
import Geo.M;
import Player.Player;
import Projectiles.BasicProjectile;
import Tiles.Tile;

public class Sentry extends Turret
{
	Entity target; 
	float Rot;
	
	public Sentry(String ref, long delay, Player player, int type) 
	{
		super(ref, delay, player, type);
		target = scan();
	}

	public void update()
	{
		super.update();
		
		Rot = 0;
		
		if(type == 0)
		{
			Rot = M.GetAngleOfLineBetweenTwoPoints
					(x , y, player.getX(), player.getY());
			
			rot = M.GetAngleOfLineBetweenTwoPoints
					(x , y, player.getX(), player.getY());
		}else if(type == 1 && target != null)
		{
			Rot = M.GetAngleOfLineBetweenTwoPoints
					(x , y, target.getX(), target.getY());
			
			rot =  M.GetAngleOfLineBetweenTwoPoints
					(x , y, target.getX(), target.getY());
		}
	}
	
	public Entity scan()
	{
		Entity target= new Entity("dummy", player, 0, 0);
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
		
		if(target.getX() == -30000 && target.getY() == -30000)
		{
			return null;
		}else
		{
			return target;
		}
	}
	
	@Override
	public void attack()
	{
		BasicProjectile shot = Shot.clone();
		shot.setShooter(null);
		shot.setPosition(x, y);
		
		target = scan();

		
		if(target == null && type == 1)
		{
			
		}else
		{
			shot.shoot((float) (M.cos(Rot) * xa), (float) (M.sin(Rot) * xa));
		}
	}
	
	@Override
	public Sentry clone(Tile tile)
	{
		return (Sentry) new Sentry(sprite.getFolder(), sprite.getDelay(), player, type).
				setProjectile(Shot, xa, atkSpeed).
		setDimension(width, height, rot).setTile(tile).setCollidable(collide, permanent).setAge(age);
	}
}

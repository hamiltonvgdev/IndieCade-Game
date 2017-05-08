package Thing;

import org.lwjgl.input.Mouse;

import Geo.M;
import Player.Player;
import Projectiles.BasicProjectile;
import Tiles.Tile;

public class Turret extends ActThing
{
	BasicProjectile Shot;
	float xa;
	float atkSpeed;
	long atkTick;
	
	public Turret(String ref, long delay, Player player, int type) 
	{
		super(ref, delay, player, type);
		
		atkTick = System.currentTimeMillis();
	}
	
	public Turret setProjectile(BasicProjectile shot, float xa, float atkSpeed)
	{
		Shot = shot;
		this.xa = xa;
		this.atkSpeed = atkSpeed;
		
		return this;
	}

	public void update()
	{
		super.update();
		
		if(System.currentTimeMillis() - atkTick >= atkSpeed)
		{
			attack();
			atkTick = System.currentTimeMillis();
		}
	}
	
	public void attack()
	{
		BasicProjectile shot = Shot.clone();
		shot.setShooter(null);
		shot.setPosition(x, y);
		
		float Rot = M.GetAngleOfLineBetweenTwoPoints
				(x + player.getXOffset(), y + player.getYOffset()
						, Mouse.getX(), M.toRightHandY(Mouse.getY()));
		
		shot.shoot((float) (M.cos(Rot) * xa), (float) (M.sin(Rot) * xa));
	}
	
	@Override
	public Turret clone(Tile tile)
	{
		return (Turret) new Turret(sprite.getFolder(), sprite.getDelay(), player, type).
				setProjectile(Shot, xa, atkSpeed).
		setDimension(width, height, rot).setTile(tile).setCollidable(collide, permanent).setAge(age);
	}
}

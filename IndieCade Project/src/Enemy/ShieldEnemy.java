package Enemy;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Geo.M;
import Player.Player;
import Projectiles.BasicProjectile;
import Render.AnimationSet;

public class ShieldEnemy extends Enemy
{

	Geo.Hitbox shield;
	AnimationSet ShieldSprite;
	
	boolean broken;
	
	float Shealth;
	float Sradius;
	float Sspeed;
	float Srot;
	float Sdrot;
	float Swidth;
	float Sheight;
	
	public ShieldEnemy(String name, Player player, float health, float damage) 
	{
		super(name, player, health, damage);
		
		Srot = 0;
		Sdrot = 0;
		
		broken = false;
	}
	
	@Override
	public void setPosition(float x, float y)
	{
		super.setPosition(x, y);
		
		Srot = M.GetAngleOfLineBetweenTwoPoints(this.x, this.y, player.getX(), player.getY());
	}
	
	public ShieldEnemy setShieldSprite(String ref, long delay)
	{
		ShieldSprite = new AnimationSet(ref, delay);
		shield = new Geo.Hitbox(ShieldSprite.getCurrentFrame());
		
		return this;
	}
	
	public ShieldEnemy setShieldStats(float health, float radius, float speed)
	{
		Shealth = health;
		Sradius = radius;
		Sspeed = speed;
		return this;
	}
	
	public ShieldEnemy setShieldDimensions(float width, float height)
	{
		Swidth = width;
		Sheight = height;
		return this;
	}
	
	public void update()
	{
		super.update();
		
		Srot = Srot % 360;
		
		Sdrot = (M.GetAngleOfLineBetweenTwoPoints(x, y, player.getX(), player.getY()) - Srot) % 360;
		
		if(Math.abs(Sdrot) > 180 && Sdrot > 0)
		{
			Sdrot = -Sdrot;
		}
		
		if(Math.abs(Sdrot) >= Sspeed)
		{
			Srot += Sdrot / Math.abs(Sdrot) * Sspeed;
		}
		
		ShieldSprite.reset();
		
		shield.update(ShieldSprite.getCurrentFrame());
		
		if(!broken)
		{
			for(int i = 0; i < map.getLevel().getProjectiles().size(); i ++)
			{
				if(map.getLevel().getProjectiles().get(i).type == 1)
				{
					if(shield.check(map.getLevel().getProjectiles().get(i).getHitbox()))
					{
						Shealth -= player.getDamage();
						shield(map.getLevel().getProjectiles().get(i));
					}
				}
			}
		}
		
		if(Shealth <= 0)
		{
			broken = true;
		}
	}
	
	public void render(Graphics g, float xOffset, float yOffset) throws SlickException
	{
		super.render(g, xOffset, yOffset);
		
		if(!broken)
		{
			ShieldSprite.render((float) (x + Sradius * M.cos(Srot)), xOffset, 
					(float) (y + Sradius * M.sin(Srot)), yOffset, Swidth, Sheight, Srot, g);
		}
	}
	
	public void shield(BasicProjectile blocked)
	{
		blocked.end();
	}
	
	public ShieldEnemy clone()
	{
		if(triggered == null)
		{
			triggered = sprite;
		}
		
		return (ShieldEnemy) new ShieldEnemy(name, player, health, damage).
				setShieldDimensions(Swidth, Sheight).setShieldStats(Shealth, Sradius, Sspeed).
				setShieldSprite(ShieldSprite.getFolder(), ShieldSprite.getDelay()).
				setRange(range).setTriggeredAnimation(triggered.getFolder(), triggered.getDelay()).
				setDimensions(width, height).setAnimationSet(sprite.getFolder(), sprite.getDelay()).
				setAtkSpeed(atkSpeed).setMove(speed, acceleration).setMove(speed, acceleration);
		
	}
}

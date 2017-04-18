package Enemy;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Geo.M;
import Player.Player;
import Render.AnimationSet;

public class ShieldEnemy extends Enemy
{

	Geo.Hitbox shield;
	AnimationSet ShieldSprite;
	
	boolean broken;
	
	float Shealth;
	float Sradius;
	float Ssize;
	float Sspeed;
	float Srot;
	float Sdrot;
	float Swidth;
	float Sheight;
	
	public ShieldEnemy(String name, Player player, float health, float damage,
			float speed) 
	{
		super(name, player, health, damage, speed);
		
		Srot = 0;
		Sdrot = 0;
		
		broken = false;
	}
	
	public ShieldEnemy setShieldSprite(String ref, long delay)
	{
		ShieldSprite = new AnimationSet(ref, delay);
		shield = new Geo.Hitbox(ShieldSprite.getCurrentFrame());
		
		return this;
	}
	
	@Override
	public void setPosition(float x, float y)
	{
		super.setPosition(x, y);
		
		Srot = M.GetAngleOfLineBetweenTwoPoints(this.x, this.y, player.getX(), player.getY());
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
		
		Sdrot = Srot - M.GetAngleOfLineBetweenTwoPoints(x, y, player.getX(), player.getY());
		
		Srot = Sdrot / Math.abs(Sdrot) * Sspeed;
		
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
						map.getLevel().getProjectiles().get(i).end();
					}
				}
			}
		}
		
		if(Shealth <= 0 )
		{
			broken = true;
		}
	}
	
	public void render(Graphics g, float xOffset, float yOffset) throws SlickException
	{
		super.render(g, xOffset, yOffset);
		
		if(!broken)
		{
			ShieldSprite.render(x, xOffset, y, yOffset, Swidth, Sheight, Srot, g);
		}
	}
}

package Form;

import Geo.M;
import Player.Player;
import Projectiles.BasicProjectile;
import Projectiles.SplitShot;
import Projectiles.SpreadShot;
import Projectiles.SurroundShot;
import Projectiles.ThingShot;
import Render.AnimationSet;
import Render.BasicImage;
import Thing.Frost;
import Thing.Ice;
import Thing.Spikes;
import Thing.Thing;

public class Hexagon extends Form
{

	public Hexagon(Player player) 
	{
		super(player);
		
		cd1 = 5000;
		cd2 = 5000;
		cd3 = 5000;
		
		idle = new AnimationSet("res/Forms/Hexagon/Idle", 100);
		atk = new AnimationSet("res/Forms/Hexagon/Attacking", (long) (atkSpeed / 7));
		height = 30 * 3.5F * 0.75F;
		width = 26 * 3.5F * 0.75F;
		
		speed = 15F;
		
		Icon1 = new BasicImage("res/Forms/Hexagon/ability 1.png");
		Icon2 = new BasicImage("res/Forms/Hexagon/ability 2.png");
		Icon3 = new BasicImage("res/Forms/Hexagon/ability 3.png");
	}

	@Override
	public void ability1()
	{
		super.ability1();
		
		ThingShot shot = (ThingShot) new ThingShot(player, 0, 1).
				setThing(new Frost("res/Forms/Hexagon/Frost/Tile", 100, player, 1).
						setDimension(64, 64, 0).setAge(5000), 3).
						setSprite("res/Forms/Hexagon/Frost/Shot", 10).
						setDimensions(27 * 3 * 0.75F, 28 * 3 * 0.75F, 90).setLimit(750);
		
		shot.setShooter(null);
		
		if(Math.abs(rot) > 90)
		{
			shot.getSprite().setFlip(true);
		}
		
		shot.shoot((float) (M.cos(rot) * 20), (float) (M.sin(rot) * 20));
	}
	
	@Override
	public void ability2()
	{
		super.ability2();
		
		ThingShot shot = (ThingShot) new ThingShot(player, 0, 1).
				setThing(new Spikes("res/Forms/Hexagon/Spikes/Tile", 100, player, 1).setAtk(2, 100).
						setDimension(64, 64, 0).setAge(5000).setCollidable(false, true), 4F).
						setSprite("res/Forms/Hexagon/Spikes/Shot", 10).
						setDimensions(30 * 3 * 0.75F, 5 * 3 * 0.75F, 0).setLimit(750);
		
		shot.setShooter(null);
		
		if(Math.abs(rot) > 90)
		{
			//shot.getSprite().setFlip(true);
		}
		
		shot.shoot((float) (M.cos(rot) * 20), (float) (M.sin(rot) * 20));
	}
	
	@Override
	public void ability3()
	{
		super.ability3();
		
		SurroundShot shot = (SurroundShot) new SurroundShot(player, 0, 1).
				setThing(new Thing("res/Forms/Hexagon/Wall/Tile", 100).setDimension(64, 64, 0).
						setAge(5000).setCollidable(true, false), 3F).
						setSprite("res/Forms/Hexagon/Wall/Shot", 10).
						setDimensions(13 * 4 * 0.75F, 13 * 4 * 0.75F, 90).setLimit(750);
		
		shot.setShooter(null);
		
		if(Math.abs(rot) > 90)
		{
			shot.getSprite().setFlip(true);
		}
		
		shot.shoot((float) (M.cos(rot) * 20), (float) (M.sin(rot) * 20));
	}
	
	public void attack()
	{
		if(atk.getCurrentIndex() == atk.getSet().size() - 1)
		{
			if(atk.getCurrentIndex() == atk.getSet().size() - 1)
			{
				BasicProjectile shot = new SplitShot(player, 1, 1).
						setSplit(new SplitShot(player, 0, 1).
								setSplit(new BasicProjectile(player, 1F, 1).
										setSprite("res/Forms/Hexagon/Projectile/Split", 100).
										setDimensions(6 * 3.5F * 0.75F, 8 * 3.5F * 0.75F, 0).setLimit(1000), 6, 10).
								setSprite("res/Forms/Hexagon/Projectile/Base", 100).
								setDimensions(13 * 3.5F, 13 * 3.5F, 0).setLimit(0), 1, 10).
						setSprite("res/Forms/Hexagon/Projectile/Base", 100).
						setDimensions(13 * 3.5F * 0.75F, 13 * 3.5F * 0.75F, 0).setLimit(3000 / 2);
				shot.setShooter(null);
				
				
				shot.shoot((float) (M.cos(rot) * speed), (float) (M.sin(rot) * speed));
				atk.reset();
			}
		}
	}
}

package Pants;

public class DashPants extends Pants
{
	float speed;
	float multi;
	float time;
	long tick;
	long cooldown;
	
	boolean dashing;
	
	public DashPants(String name, float Multi, long time, long cd) 
	{
		super(name);
		
		dashing = false;
		
		speed = player.getSpeed();
		tick = System.currentTimeMillis();
		
		multi = Multi;
		this.time = time;
		cooldown = cd;
	}
	
	public void update()
	{
		super.update();
		
		speed = player.getSpeed();
		
		if(dashing)
		{
			player.setVx(speed * multi);
		}
		
		if(System.currentTimeMillis() - tick > time)
		{
			dashing = false;
		}
	}
	
	@Override
	public void Ability()
	{
		if(!dashing && System.currentTimeMillis() - (tick + time) > cooldown)
		{
			tick = System.currentTimeMillis();
			
			dashing = true;
		}
	}
}

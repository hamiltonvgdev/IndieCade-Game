package Pants;

public class DashSlicePants extends DashPants
{

	public DashSlicePants(String name, float Multi, long time, long cd) 
	{
		super(name, Multi, time, cd);
	}
	
	public void update()
	{
		super.update();
		
		if(dashing)
		{
			 for(int i = 0; i < player.getMap().getLevel().getEntities().size(); i ++)
			 {
				 //need work
			 }
		}
	}

}

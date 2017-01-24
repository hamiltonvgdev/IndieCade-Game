package Pants;

public class MultiJumpPants extends Pants
{
	int jumps;
	
	public MultiJumpPants(String name, int jumps) 
	{
		super(name);
		
		this.jumps = jumps;
	}
	
	public void update()
	{
		super.update();
		
		player.setJumps(jumps);
	}
	
}

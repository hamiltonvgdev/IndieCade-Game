package Pants;

public class SuperJumpPants extends Pants
{
	float jumpV;
	
	public SuperJumpPants(String name, float JumpV) 
	{
		super(name);
		
		jumpV = JumpV;
	}

	public void update()
	{
		super.update();
		
		player.setJumpV(jumpV);
	}
}

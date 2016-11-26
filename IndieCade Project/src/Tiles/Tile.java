package Tiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Tile 
{
	Color Id;
	boolean spawnable;
	
	public Tile(Color Id)
	{
		this.Id = Id;
		spawnable = false;
	}
	
	public Color getID()
	{
		return Id;
	}

	public void render(Graphics g)
	{
		
	}
	
	public void update()
	{
		
	}
	
	public boolean getSpawnable()
	{
		return spawnable;
	}
}

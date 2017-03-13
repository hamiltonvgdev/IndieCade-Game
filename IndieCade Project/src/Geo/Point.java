package Geo;

public class Point 
{
	public float x, y;

	public Point(float x, float y) 
	{
		this.x = x;
		this.y = y;
	}
	
	public void shift(float xa, float ya)
	{
		x += xa;
		y += ya;
	}
	
	public void set(float xa, float ya)
	{
		x = xa;
		y = ya;
	}
}

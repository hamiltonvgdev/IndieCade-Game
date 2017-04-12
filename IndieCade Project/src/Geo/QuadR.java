package Geo;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;

public class QuadR
{
	public float  x, y, width, height, rot;
	ArrayList<Point> Points;
	float factor;
	
	public boolean phased;
	
	public QuadR(float x, float y, float width, float height, float rot)
	{
		Points = new ArrayList<Point>();
		
		this.rot = (float) (180 - Math.toDegrees(Math.atan(height / width)) - (rot % 360));
		
		for(int j = 1; j >= -1; j -= 2)
		{
			for(int i = -1; i <= 1; i += 2)
			{
				float xt = i * width / 2;
				float yt = j * height / 2;
				Points.add(new Point((float) (xt * Math.cos(Math.toRadians(rot)) - yt * Math.sin(Math.toRadians(rot))), 
						(float) (xt * Math.sin(Math.toRadians(rot)) + yt * Math.cos(Math.toRadians(rot)))));
			}
		}
		
		for(int i = 0; i < Points.size(); i ++)
		{
			Points.get(i).shift(x, y);
		}
		
		this.width = width;
		this.height = height;
		
		this.x = x;
		this.y = y;
		
		phased = false;
	}
	
	public void update(float x, float y, float width, float height, float rot)
	{
		int counter = 0;
		
		this.rot = rot % 360;
		
		for(int i = -1; i <= 1; i += 2)
		{
			for(int j = 1; j >= -1; j -= 2)
			{
				float xt = i * width / 2;
				float yt = j * height / 2;
				Points.get(counter).set((float) (xt * Math.cos(Math.toRadians(rot)) - yt * Math.sin(Math.toRadians(rot))), 
						(float) (xt * Math.sin(Math.toRadians(rot)) + yt * Math.cos(Math.toRadians(rot))));
				counter ++;
			}
		}
		
		for(int i = 0; i < Points.size(); i ++)
		{
			Points.get(i).shift(x, y);
		}
		
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
	}
	
	public void render(Graphics g) throws SlickException
	{
		g.drawLine(Points.get(0).x, Points.get(0).y, Points.get(1).x, Points.get(1).y);
		g.drawLine(Points.get(0).x, Points.get(0).y, Points.get(2).x, Points.get(2).y);
		g.drawLine(Points.get(1).x, Points.get(1).y, Points.get(3).x, Points.get(3).y);
		g.drawLine(Points.get(2).x, Points.get(2).y, Points.get(3).x, Points.get(3).y);	
		
		for(int i = 0; i < Points.size(); i ++)
		{
			g.drawString(Integer.toString(i), Points.get(i).x, Points.get(i).y);
		}
		
		
		for(float i = Points.get(0).x - width* 4; i < Points.get(0).x + width * 4; i ++)
		{
			for(float j = Points.get(0).y - height * 4; j < Points.get(0).y + height * 4; j ++)
			{
				if(check(i, j))
				{
					g.drawString("o", i, j);
				}
			}
		}
	}
	
	public QuadR setPhased(boolean phased)
	{
		this.phased = phased;
		return this;
	}
	
	public boolean check(float x, float y)
	{
		boolean derp = false;
		
		if(Math.abs(rot) == 0 || Math.abs(rot) == 90 || Math.abs(rot) == 180 || Math.abs(rot) == 270)
		{
			if(Math.abs(rot) == 0)
			{
				if (x >= Points.get(1).x)
				{
					if (y >= Points.get(1).y)
					{
						if (x <= Points.get(1).x + this.width)
						{
							if (y <= Points.get(1).y + this.height)
							{
								derp = true;
							}
						}
					}
				}
			}else if(Math.abs(rot) == 180)
			{
				if (x >= Points.get(2).x)
				{
					if (y >= Points.get(2).y)
					{
						if (x <= Points.get(2).x + this.width)
						{
							if (y <= Points.get(2).y + this.height)
							{
								derp = true;
							}
						}
					}
				}
			}else if(rot == 90 || rot == -270)
			{
				if (x >= Points.get(0).x)
				{
					if (y >= Points.get(0).y)
					{
						if (x <= Points.get(0).x + this.width)
						{
							if (y <= Points.get(0).y + this.height)
							{
								derp = true;
							}
						}
					}
				}
			}else
			{
				if (x >= Points.get(3).x)
				{
					if (y >= Points.get(3).y)
					{
						if (x <= Points.get(3).x + this.width)
						{
							if (y <= Points.get(3).y + this.height)
							{
								derp = true;
							}
						}
					}
				}
			}
		}else
		{	
			if(check1(x, y) || check2(x, y) || check3(x, y) || check4(x, y))
			{
				derp = true;
			}
		}
		
		return derp;
	}

	public boolean check(Quad quad)
	{
		boolean derp = false;
		
		for(float x = quad.x; x < quad.x + quad.width; x ++)
		{
			for(float y = quad.y; y < quad.y + quad.height; y ++)
			{
				if(check(x, y))
				{
					derp = true;
					break;
				}
			}
		}
		
		return derp;
	}
	
	public boolean check(QuadR quadr)
	{
		boolean derp = false;
		
		if(M.isLessThan(M.toRightHandY(y), M.getSlope(Points.get(0).x,
				M.toRightHandY(Points.get(0).y), 
				Points.get(1).x, M.toRightHandY(Points.get(1).y)), x, 
				M.getYIntercept(Points.get(0).x, M.toRightHandY(Points.get(0).y),
						Points.get(1).x, M.toRightHandY(Points.get(1).y))))
		{
			if(M.isGreaterThan(M.toRightHandY(y), M.getSlope(Points.get(1).x, 
					M.toRightHandY(Points.get(1).y), 
					Points.get(3).x, M.toRightHandY(Points.get(3).y)), x, 
					M.getYIntercept(Points.get(1).x, M.toRightHandY(Points.get(1).y), 
							Points.get(3).x, M.toRightHandY(Points.get(3).y))))
			{
				if(M.isLessThan(M.toRightHandY(y), M.getSlope(Points.get(0).x,
						M.toRightHandY(Points.get(0).y), Points.get(2).x, M.toRightHandY(Points.get(2).y)),
						x, M.getYIntercept(Points.get(0).x, M.toRightHandY(Points.get(0).y), 
								Points.get(2).x, M.toRightHandY(Points.get(2).y))))
				{
					if(M.isGreaterThan(M.toRightHandY(y), M.getSlope(Points.get(2).x, 
							M.toRightHandY(Points.get(2).y), Points.get(3).x, M.toRightHandY(Points.get(3).y)),
							x, M.getYIntercept(Points.get(2).x, M.toRightHandY(Points.get(2).y), 
									Points.get(3).x, M.toRightHandY(Points.get(3).y))))
					{
						derp = true;
					}
				}
			}
		}
		
		return derp;
	}
	
	public boolean check1(float x, float y)
	{
		boolean derp = false;
		
		if(M.isLessThan(M.toRightHandY(y), M.getSlope(Points.get(0).x,
				M.toRightHandY(Points.get(0).y), 
				Points.get(1).x, M.toRightHandY(Points.get(1).y)), x, 
				M.getYIntercept(Points.get(0).x, M.toRightHandY(Points.get(0).y),
						Points.get(1).x, M.toRightHandY(Points.get(1).y))))
		{
			if(M.isGreaterThan(M.toRightHandY(y), M.getSlope(Points.get(1).x, 
					M.toRightHandY(Points.get(1).y), 
					Points.get(3).x, M.toRightHandY(Points.get(3).y)), x, 
					M.getYIntercept(Points.get(1).x, M.toRightHandY(Points.get(1).y), 
							Points.get(3).x, M.toRightHandY(Points.get(3).y))))
			{
				if(M.isLessThan(M.toRightHandY(y), M.getSlope(Points.get(0).x,
						M.toRightHandY(Points.get(0).y), Points.get(2).x, M.toRightHandY(Points.get(2).y)),
						x, M.getYIntercept(Points.get(0).x, M.toRightHandY(Points.get(0).y), 
								Points.get(2).x, M.toRightHandY(Points.get(2).y))))
				{
					if(M.isGreaterThan(M.toRightHandY(y), M.getSlope(Points.get(2).x, 
							M.toRightHandY(Points.get(2).y), Points.get(3).x, M.toRightHandY(Points.get(3).y)),
							x, M.getYIntercept(Points.get(2).x, M.toRightHandY(Points.get(2).y), 
									Points.get(3).x, M.toRightHandY(Points.get(3).y))))
					{
						derp = true;
					}
				}
			}
		}
		
		return derp;
	}
	
	public boolean check2(float x, float y)
	{
		boolean derp = false;
		
		if(M.isLessThan(M.toRightHandY(y), M.getSlope(Points.get(0).x,
				M.toRightHandY(Points.get(0).y), 
				Points.get(1).x, M.toRightHandY(Points.get(1).y)), x, 
				M.getYIntercept(Points.get(0).x, M.toRightHandY(Points.get(0).y),
						Points.get(1).x, M.toRightHandY(Points.get(1).y))))
		{
			if(M.isLessThan(M.toRightHandY(y), M.getSlope(Points.get(1).x, 
					M.toRightHandY(Points.get(1).y), 
					Points.get(3).x, M.toRightHandY(Points.get(3).y)), x, 
					M.getYIntercept(Points.get(1).x, M.toRightHandY(Points.get(1).y), 
							Points.get(3).x, M.toRightHandY(Points.get(3).y))))
			{
				if(M.isGreaterThan(M.toRightHandY(y), M.getSlope(Points.get(0).x,
						M.toRightHandY(Points.get(0).y), Points.get(2).x, M.toRightHandY(Points.get(2).y)),
						x, M.getYIntercept(Points.get(0).x, M.toRightHandY(Points.get(0).y), 
								Points.get(2).x, M.toRightHandY(Points.get(2).y))))
				{
					if(M.isGreaterThan(M.toRightHandY(y), M.getSlope(Points.get(2).x, 
							M.toRightHandY(Points.get(2).y), Points.get(3).x, M.toRightHandY(Points.get(3).y)),
							x, M.getYIntercept(Points.get(2).x, M.toRightHandY(Points.get(2).y), 
									Points.get(3).x, M.toRightHandY(Points.get(3).y))))
					{
						derp = true;
					}
				}
			}
		}
		
		return derp;
	}
	
	public boolean check3(float x, float y)
	{
		boolean derp = false;
		
		if(M.isGreaterThan(M.toRightHandY(y), M.getSlope(Points.get(0).x,
				M.toRightHandY(Points.get(0).y), 
				Points.get(1).x, M.toRightHandY(Points.get(1).y)), x, 
				M.getYIntercept(Points.get(0).x, M.toRightHandY(Points.get(0).y),
						Points.get(1).x, M.toRightHandY(Points.get(1).y))))
		{
			if(M.isLessThan(M.toRightHandY(y), M.getSlope(Points.get(1).x, 
					M.toRightHandY(Points.get(1).y), 
					Points.get(3).x, M.toRightHandY(Points.get(3).y)), x, 
					M.getYIntercept(Points.get(1).x, M.toRightHandY(Points.get(1).y), 
							Points.get(3).x, M.toRightHandY(Points.get(3).y))))
			{
				if(M.isGreaterThan(M.toRightHandY(y), M.getSlope(Points.get(0).x,
						M.toRightHandY(Points.get(0).y), Points.get(2).x, M.toRightHandY(Points.get(2).y)),
						x, M.getYIntercept(Points.get(0).x, M.toRightHandY(Points.get(0).y), 
								Points.get(2).x, M.toRightHandY(Points.get(2).y))))
				{
					if(M.isLessThan(M.toRightHandY(y), M.getSlope(Points.get(2).x, 
							M.toRightHandY(Points.get(2).y), Points.get(3).x, M.toRightHandY(Points.get(3).y)),
							x, M.getYIntercept(Points.get(2).x, M.toRightHandY(Points.get(2).y), 
									Points.get(3).x, M.toRightHandY(Points.get(3).y))))
					{
						derp = true;
					}
				}
			}
		}
		
		return derp;
	}
	
	public boolean check4(float x, float y)
	{
		boolean derp = false;
		
		if(M.isGreaterThan(M.toRightHandY(y), M.getSlope(Points.get(0).x,
				M.toRightHandY(Points.get(0).y), 
				Points.get(1).x, M.toRightHandY(Points.get(1).y)), x, 
				M.getYIntercept(Points.get(0).x, M.toRightHandY(Points.get(0).y),
						Points.get(1).x, M.toRightHandY(Points.get(1).y))))
		{
			if(M.isGreaterThan(M.toRightHandY(y), M.getSlope(Points.get(1).x, 
					M.toRightHandY(Points.get(1).y), 
					Points.get(3).x, M.toRightHandY(Points.get(3).y)), x, 
					M.getYIntercept(Points.get(1).x, M.toRightHandY(Points.get(1).y), 
							Points.get(3).x, M.toRightHandY(Points.get(3).y))))
			{
				if(M.isLessThan(M.toRightHandY(y), M.getSlope(Points.get(0).x,
						M.toRightHandY(Points.get(0).y), Points.get(2).x, M.toRightHandY(Points.get(2).y)),
						x, M.getYIntercept(Points.get(0).x, M.toRightHandY(Points.get(0).y), 
								Points.get(2).x, M.toRightHandY(Points.get(2).y))))
				{
					if(M.isLessThan(M.toRightHandY(y), M.getSlope(Points.get(2).x, 
							M.toRightHandY(Points.get(2).y), Points.get(3).x, M.toRightHandY(Points.get(3).y)),
							x, M.getYIntercept(Points.get(2).x, M.toRightHandY(Points.get(2).y), 
									Points.get(3).x, M.toRightHandY(Points.get(3).y))))
					{
						derp = true;
					}
				}
			}
		}
		
		return derp;
	}

}

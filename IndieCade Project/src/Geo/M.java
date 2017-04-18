package Geo;

import java.util.Random;
import java.util.Vector;

import javax.swing.JLabel;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;

import Main.Config;

public class M {
	//I honestly don't know why this is here
	public static Random r = new Random();

	public static int r(double d, double e)
	{
		return r.nextInt((int) e - (int) d) + (int) d;
	}

	public static int r(double x2) 
	{
		return r.nextInt((int) x2);
	}

	public static double distance(double x, double y, double x2, double y2) 
	{

		double distance = Math.sqrt(((x - x2) * (x - x2) + (y - y2) * (y - y2)));
		return distance;
	}

	public static float GetAngleOfLineBetweenTwoPoints(float x1, float y1, float x2, float y2) 
	{
		double xDiff = x1 - x2;
		double yDiff = y1 - y2;
		return (float) Math.toDegrees(Math.atan2(-yDiff, -xDiff));
	}

	public static double cos(double num) 
	{
		return Math.cos(Math.toRadians(num));
	}
	
	public static double sin(double num)
	{
		return Math.sin(Math.toRadians(num));
	}
	
	public static double tan(double num)
	{
		return Math.tan(Math.toRadians(num));
	}
	
	public static float getSlope(float x1, float y1, float x2, float y2)
	{
		return (y1 - y2) /( x1 - x2);
	}
	
	public static float getYIntercept(float x1, float y1, float x2, float y2)
	{
		return (y2 * x1 - x2 * y1) / (x1 - x2);
	}
	
	public static float toRightHandY(float y)
	{
		return Config.HEIGHT - y;
	}
	
	public static boolean isGreaterThan(float y, float m, float x, float b)
	{
		boolean derp = false;
		
		if(y > m * x + b)
		{
			derp = true;
		}
		
		return derp;
	}
	
	public static boolean isLessThan(float y, float m, float x, float b)
	{
		boolean derp = false;
		
		if(y < m * x + b)
		{
			derp = true;
		}
		
		return derp;
	}
	
	public static Color getMixedColor(Color color1, Color color2) 
	{

		int red = color1.getRed();
		int green = color1.getGreen();
		int blue = color1.getBlue();

		red -= color2.getRed();
		green -= color2.getGreen();
		blue -= color2.getBlue();

		return new Color(Math.min(255, color1.getRed() - (red / 2)), Math.min(255, color1.getGreen() - (green / 2)),
				color1.getBlue() - (blue / 2));

	}

	public static int getNumKey(int key)
	{
		switch(key)
		{
			default: return 0;
		
			case 1: return Input.KEY_1;
			
			case 2: return Input.KEY_2;
			
			case 3: return Input.KEY_3;
			
			case 4: return Input.KEY_4;
			
			case 5: return Input.KEY_5;
			
			case 6: return Input.KEY_6;
			
			case 7: return Input.KEY_7;
			
			case 8: return Input.KEY_8;
			
			case 9: return Input.KEY_9;
		
		}
	}
}

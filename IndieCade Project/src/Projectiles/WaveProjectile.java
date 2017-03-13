package Projectiles;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import GameBasics.Entity;
import Player.Player;
import Weapons.RangedWeapon;

public class WaveProjectile extends BasicProjectile
{
	float amplitude;
	float waveSpeed;
	float ang;
	
	public WaveProjectile(Player player, int type) 
	{
		super(player, type);
		ang = 0;
	}
	
	public WaveProjectile setWave(float amp, float waveSpeed)
	{
		amplitude = amp;
		this.waveSpeed = waveSpeed;
		return this;
	}
	
	@Override
	public void update()
	{
		super.update();
		ang += waveSpeed;
		
		if(ang >= 360)
		{
			ang = 0;
		}
	}
	
	@Override
	public void render(Graphics g, float xOffset, float yOffset) throws SlickException
	{
		sprite.render(x + xOffset, y + yOffset + (float)(amplitude * Math.sin(ang)), width, height, rot, g);
	}
	
	public float getAmp()
	{
		return amplitude;
	}
	
	public float getWaveSpeed()
	{
		return waveSpeed;
	}
}

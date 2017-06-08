package Tiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Thing.Thing;

public class ThingTile extends Tile
{
	Thing thing;
	
	public ThingTile(String name, Color Id, Thing thing)
	{
		super(name, Id);
		
		this.thing = thing.setTile(this);
		
		Type = 3;
	}
	
	@Override
	public void changeCoordinates(float xa, float ya)
	{
		super.changeCoordinates(xa, ya);
		thing.setPosition(xa, ya);
	}
	
	@Override
	public void postSetAction()
	{
		setAnimation(map.getTiles().get(map.getTiles().indexOf(this) - 1).getRef(),
				map.getTiles().get(map.getTiles().indexOf(this) - 1).getDelay());
		setSound(map.getTiles().get(map.getTiles().indexOf(this) - 1).getSoundRef());
		setFriction(map.getTiles().get(map.getTiles().indexOf(this) - 1).getFriction());
	}
	
	@Override
	public void update()
	{
		super.update();
		
		thing.update();
	}
	
	@Override
	public void render(Graphics g) throws SlickException
	{
		super.render(g);
		
		thing.render(g, xOffset, yOffset);
	}
	
	public Thing getThing()
	{
		return thing;
	}
}

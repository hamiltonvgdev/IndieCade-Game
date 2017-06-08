package Bosses;

import org.newdawn.slick.Color;

import Form.Form;
import GameBasics.Entity;
import Player.Player;
import Thing.PickUp;

public class Boss extends Entity
{
	Form drop;
	
	public Boss(String name, Player player, float health, float damage, Form form) 
	{
		super(name, player, health, damage);
		
		drop = form;
		permanent = true;
		omnipresent = true;
	}

	public void update()
	{
		super.update();
	}
	
	@Override
	public void die()
	{
		super.die();
		
		player.getMap().getLevel().addThing(
				new PickUp(drop.getCurrentSprite().getFolder(), 100, player, drop, 1).
				setDimension(drop.width, drop.height, drop.width).setTile(player.getMap().getTile(x, y)));
	}
	
	public Boss clone()
	{
		return (Boss) new Boss(name, player, health, damage, drop).
				setDimensions(width, height).setAnimationSet(sprite.getFolder(), sprite.getDelay()).
				setAtkSpeed(atkSpeed);
	}

}

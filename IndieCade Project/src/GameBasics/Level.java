package GameBasics;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Geo.Quad;
import Main.Config;
import Player.Player;

public class Level 
{
	ArrayList<Entity> Entities;
	ArrayList<Item> Items;
	
	Player player;
	
	public Level(Player player)
	{
		Entities = new ArrayList<Entity>();
		Items = new ArrayList<Item>();
		
		this.player = player;
	}
	
	public ArrayList<Entity> getEntities()
	{
		return Entities;
	}
	
	public void addEntity(Entity e)
	{
		Entities.add(e);
	}
	
	public void removeEntity(Entity e)
	{
		Entities.remove(e);
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		for(Entity e : Entities)
		{
			if(e.getHitbox().checkQuad(new Quad(Config.WIDTH / 2, Config.HEIGHT / 2, Config.WIDTH, Config.HEIGHT)))
			{
				e.render(g);
			}
		}
	}
	
	public void update()
	{
		for(int i = 0; i < Entities.size(); i ++)
		{
			Entities.get(i).update();
		}
	}
}

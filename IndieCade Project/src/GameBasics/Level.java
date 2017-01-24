package GameBasics;

import java.io.Serializable;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Geo.Quad;
import Main.Config;
import Player.Player;

public class Level implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6513983764060293943L;
	ArrayList<Entity> Entities;
	ArrayList<Item> Items;
	ArrayList<BasicNPC> NPCs;
	
	Player player;
	
	public Level(Player player)
	{
		Entities = new ArrayList<Entity>();
		Items = new ArrayList<Item>();
		NPCs = new ArrayList<BasicNPC>();
		
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

	public ArrayList<BasicNPC> getNPCs()
	{
		return NPCs;
	}
	
	public void addNPC(BasicNPC npc)
	{
		NPCs.add(npc);
	}
	
	public void removeNPC(BasicNPC npc)
	{
		NPCs.remove(npc);
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

	public void shift(float xa, float ya) 
	{
		for(int i = 0; i < Entities.size(); i ++)
		{
			//move each entity
		}
		
		for(int i = 0; i < NPCs.size(); i ++)
		{
			NPCs.get(i).shift(xa, ya);
		}
	}

	public Player getPlayer() 
	{
		return player;
	}
}

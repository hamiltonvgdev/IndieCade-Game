package GameBasics;

import java.io.Serializable;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Geo.Quad;
import Main.Config;
import Player.Player;
import Projectiles.BasicProjectile;

public class Level implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6513983764060293943L;
	ArrayList<Entity> Entities;
	ArrayList<Item> Items;
	ArrayList<BasicProjectile> Projectiles;
	ArrayList<BasicNPC> NPCs;
	
	Player player;
	Quad Screen;
	
	public Level(Player player)
	{
		Entities = new ArrayList<Entity>();
		Items = new ArrayList<Item>();
		NPCs = new ArrayList<BasicNPC>();
		Projectiles = new ArrayList<BasicProjectile>();
		
		this.player = player;
		Screen = new Quad(player.getX(), player.getY(), Config.WIDTH, Config.HEIGHT);
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
	
	public ArrayList<BasicProjectile> getProjectiles()
	{
		return Projectiles;
	}
	
	public void addProjectile(BasicProjectile projectile)
	{
		Projectiles.add(projectile);
	}
	
	public void removeProjectile(BasicProjectile projectile)
	{
		Projectiles.remove(projectile);
	}
	
	public void render(Graphics g) throws SlickException
	{
		for(int i = 0; i < Entities.size(); i ++)
		{
			Entities.get(i).render(g);
		}
		
		for(BasicNPC n : NPCs)
		{
			n.render(g);
		}
		
		for(int i = 0; i < Projectiles.size(); i ++)
		{
			Projectiles.get(i).render(g);
		}
	}
	
	public void update()
	{
		for(int i = 0; i < Entities.size(); i ++)
		{
			Entities.get(i).update();
		}
		
		for(int i = 0; i < NPCs.size(); i ++)
		{
			NPCs.get(i).update();
		}
		
		for(int i = 0; i < Projectiles.size(); i ++)
		{
			Projectiles.get(i).update();
		}
		Screen.changeDimensions(player.getX(), player.getY(), Config.WIDTH, Config.HEIGHT);
	}

	public void shift(float xa, float ya) 
	{
		for(int i = 0; i < Entities.size(); i ++)
		{
			Entities.get(i).Move(xa, ya);
		}
		
		for(int i = 0; i < NPCs.size(); i ++)
		{
			NPCs.get(i).shift(xa, ya);
		}
		
		for(int i = 0; i < Projectiles.size(); i ++)
		{
			Projectiles.get(i).move(xa, ya);
		}
	}

	public Player getPlayer() 
	{
		return player;
	}
}

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
import Thing.Thing;

public class Level implements Serializable
{
	//The level class that handles the management of a map's entities, projectiles, and Things
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6513983764060293943L;
	ArrayList<Entity> Entities;
	ArrayList<Entity> Near;
	ArrayList<BasicProjectile> Projectiles;
	ArrayList<Thing> Things;
	
	Player player;
	Quad Screen;
	
	float xOffset;
	float yOffset;
	
	public Level(Player player)
	{
		Entities = new ArrayList<Entity>();
		Near = new ArrayList<Entity>();
		Things = new ArrayList<Thing>();
		Projectiles = new ArrayList<BasicProjectile>();
		
		this.player = player;
		Screen = new Quad(player.getX(), player.getY(), Config.WIDTH, Config.HEIGHT);
		xOffset = 0;
		yOffset = 0;
	}
	
	public ArrayList<Entity> getEntities()
	{	
		return Near;
	}
	
	public ArrayList<Entity> getAllEntities()
	{
		return Entities;
	}
	
	public void addEntity(Entity e)
	{
		Entities.add(e);
	}
	
	public void near(Entity e)
	{
		Near.add(e);
	}
	
	public void far(Entity e)
	{
		Near.remove(e);
	}
	
	public void removeEntity(Entity e)
	{
		Entities.remove(e);
	}

	public ArrayList<Thing> getThings()
	{
		return Things;
	}
	
	public void removeThing(Thing th)
	{
		Things.remove(th);
	}
	
	public void addThing(Thing th)
	{
		Things.add(th);
		
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
		for(Thing thing : Things)
		{
			thing.render(g, xOffset, yOffset);
		}
		
		for(int i = 0; i < Projectiles.size(); i ++)
		{
			Projectiles.get(i).render(g, xOffset, yOffset);
		}
		
		for(int i = 0; i < Entities.size(); i ++)
		{
			if(Entities.get(i).omnipresent || 
					(Math.abs(Entities.get(i).getX() - player.getX()) < Config.WIDTH / 2 && 
					Math.abs(Entities.get(i).getY() - player.getY()) < Config.HEIGHT / 2))
			{
				Entities.get(i).render(g, xOffset, yOffset);
			}
		}
	}
	
	public void update()
	{
		for(int i = 0; i < Entities.size(); i ++)
		{
			if(Entities.get(i).omnipresent || 
					(Math.abs(Entities.get(i).getX() - player.getX()) < Config.WIDTH / 2 + 32 && 
					Math.abs(Entities.get(i).getY() - player.getY()) < Config.HEIGHT / 2 + 32))
			{
				Entities.get(i).near();
				Entities.get(i).Physics();
				if(i < Entities.size() && Entities.get(i) != null)
				{
					Entities.get(i).update();
				}
			}else
			{
				Entities.get(i).far();
				if(!Entities.get(i).permanent)
				{
					Entities.remove(i);
					
				}
			}
		}
		
		for(int i = 0; i < Things.size(); i ++)
		{
			if(Math.abs(Things.get(i).getX() - player.getX()) < Config.WIDTH / 2 && 
					Math.abs(Things.get(i).getY() - player.getY()) < Config.HEIGHT / 2)
			{
				Things.get(i).update();
			}
		}
		
		for(int i = 0; i < Projectiles.size(); i ++)
		{
			Projectiles.get(i).update();
		}
		
		Screen.changeDimensions(player.getX(), player.getY(), Config.WIDTH, Config.HEIGHT);
	}

	public void shift(float xa, float ya) 
	{
		xOffset += xa;
		yOffset += ya;
	}
	
	public void reset()
	{
		Entities.clear();
		Projectiles.clear();
		Near.clear();
		
		xOffset = 0;
		yOffset = 0;
	}

	public Player getPlayer() 
	{
		return player;
	}
}

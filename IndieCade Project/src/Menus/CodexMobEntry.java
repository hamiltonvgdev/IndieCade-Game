package Menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import GameBasics.Entity;
import Main.Config;
import Render.AnimationSet;
import Render.BasicImage;
import Util.Button;

public class CodexMobEntry 
{
	CodexMenu codex;
	
	String name;
	String des;
	int health;
	int damage;
	int speed;
	int armor;
	int id;
	
	BasicImage Icon;
	BasicImage Bar;
	BasicImage Race;
	BasicImage Environment;
	
	Button nxt;
	Button pre;
	
	public CodexMobEntry(CodexMenu codex, Entity ent)
	{
		this.codex = codex;
		
		nxt = new Button("Next", Config.WIDTH - 50, Config.HEIGHT / 2, 0);
		nxt = nxt.setDimensions(138, 50).setImage(new AnimationSet("res/Buttons/Base/Idle", 100), new AnimationSet("res/Buttons/Base/Select", 100));	
		pre = new Button("Previous", 50, Config.HEIGHT / 2, 0);
		pre = pre.setDimensions(138, 50).setImage(new AnimationSet("res/Buttons/Base/Idle", 100), new AnimationSet("res/Buttons/Base/Select", 100));	
	}
	
	public CodexMobEntry(CodexMenu codex)
	{
		this.codex = codex;
		
		id = 0;
		name = "Unknown";
		des = "UnSpecified";
		
		health = 0;
		damage = 0;
		speed = 0;
		armor = 0;
		
		Icon = new BasicImage("res/Defaults/Mob/Unknown Mob.png");
		Race = new BasicImage("res/Defaults/Mob/Unknown Icon.png");
		Environment = new BasicImage("res/Defaults/Mob/Unknown Environment.png");
		Bar = new BasicImage("res/Defaults/Bar/Default Bar.png");
		
		nxt = new Button("Next", Config.WIDTH - 50, Config.HEIGHT / 2, 0);
		nxt = nxt.setDimensions(138, 50).setImage(new AnimationSet("res/Buttons/Base/Idle", 100), new AnimationSet("res/Buttons/Base/Select", 100));	
		pre = new Button("Previous", 50, Config.HEIGHT / 2, 0);
		pre = pre.setDimensions(138, 50).setImage(new AnimationSet("res/Buttons/Base/Idle", 100), new AnimationSet("res/Buttons/Base/Select", 100));	
	}
	
	public void update()
	{
		if(codex.index < codex.Mobs.size() - 1)
		{
			nxt.update();
		}
		
		if(codex.index > 0)
		{
			pre.update();
		}
		
		if(nxt.clicked && id == codex.index)
		{
			codex.changeIndex(1);
		}else if(pre.clicked && id == codex.index)
		{
			codex.changeIndex(-1);
		}
	}
	
	public void render(Graphics g) throws SlickException
	{	
		Rectangle background = new Rectangle(0, 0, Config.WIDTH, Config.HEIGHT);
		background.setBounds(0, 0, Config.WIDTH, Config.HEIGHT);
		g.setColor( Color.gray);
		g.fill(background);
		g.draw(background);
		
		Icon.render(Config.WIDTH / 2, 0, 300 / 2, 0, 300, 300, 0, g);
		Race.render(Config.WIDTH / 2 + 400, 0, -25 + 300 / 2, 0, 150, 150, 0, g);
		Environment.render(Config.WIDTH / 2 - 400, 0, -25 + 300 / 2, 150, 0, 150, 0, g);
		
		for(int i = 3; i < 13; i ++)
		{
			Bar.render(Config.WIDTH / 2 - 230 + 30 * i, 0, 350, 0, 30, 25, 0, g);
		}
		
		for(int i = 3; i < 13; i ++)
		{
			Bar.render(Config.WIDTH / 2 - 230 + 30 * i, 0, 350 + 40, 0, 30, 25, 0, g);
		}
		
		for(int i = 3; i < 13; i ++)
		{
			Bar.render(Config.WIDTH / 2 - 230 + 30 * i, 0, 350 + 80, 0, 30, 25, 0, g);
		}
		
		for(int i = 3; i < 13; i ++)
		{
			Bar.render(Config.WIDTH / 2 - 230 + 30 * i, 0, 350 + 120, 0, 30, 25, 0, g);
		}
		
		for(int i = 3; i < 13; i ++)
		{
			Bar.render(Config.WIDTH / 2 - 230 + 30 * i, 0, 350 + 160, 0, 30, 25, 0, g);
		}
		
		if(codex.index < codex.Mobs.size() - 1)
		{
			nxt.render(g);
		}
		
		if(codex.index > 0)
		{
			pre.render(g);
		}
	}

	public void setDescription(String des)
	{
		this.des = des;
	}
	
	public void setID(int id)
	{
		this.id = id;
	}
}

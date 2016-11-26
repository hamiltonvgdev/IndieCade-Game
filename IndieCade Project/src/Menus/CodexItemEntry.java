package Menus;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import GameBasics.Item;
import Main.Config;
import Render.AnimationSet;
import Render.BasicImage;
import Util.Button;

public class CodexItemEntry 
{
	CodexMenu codex;
	
	String name;
	String des;
	
	int health;
	int armor;
	int damage;
	int tenacity;
	int speed;
	int id;
	
	AnimationSet Icon;
	BasicImage Bar;
	
	Button nxt;
	Button pre;
	
	public CodexItemEntry(CodexMenu codex, Item item)
	{
		this.codex = codex;
		
		name = item.getName();
		health = item.getHealth();
		armor = item.getArmor();
		damage = item.getDamage();
		tenacity = item.getTenacity();
		speed = item.getSpeed();
		
		nxt = new Button("Next", Config.WIDTH - 50, Config.HEIGHT / 2, 0);
		nxt = nxt.setDimensions(100, 50).setImage(new AnimationSet("res/Buttons/Test", 100), null);	
		pre = new Button("Previous", 50, Config.HEIGHT / 2, 0);
		pre = pre.setDimensions(100, 50).setImage(new AnimationSet("res/Buttons/Test", 100), null);	
	}
	
	public CodexItemEntry(CodexMenu codex)
	{
		this.codex = codex;
		
		name = "Unknown";
		des = "UnSpecified";
		
		health = 0;
		damage = 0;
		speed = 0;
		armor = 0;
		tenacity = 0;
		
		Icon = new AnimationSet("res/Defaults/Items", 750);
		
		Bar = new BasicImage("res/Defaults/Bar/Default Bar.png");
		
		nxt = new Button("Next", Config.WIDTH - 50, Config.HEIGHT / 2, 0);
		nxt = nxt.setDimensions(100, 50).setImage(new AnimationSet("res/Buttons/Test", 100), null);	
		pre = new Button("Previous", 50, Config.HEIGHT / 2, 0);
		pre = pre.setDimensions(100, 50).setImage(new AnimationSet("res/Buttons/Test", 100), null);	
	}
	
	public void update()
	{
		Icon.resetAnimate();
		
		if(codex.index < codex.Items.size() - 1)
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
		Icon.render(300 / 2, -25 + 300 / 2, 300, 300, 0, g);
		g.drawString(name, 400, 0);
		g.drawString(des, 350, 75);


		g.drawString("Health", 0, 290);
		g.drawString("Armor", 0, 330);
		g.drawString("Damage", 0, 370);
		g.drawString("Tenacity", 0, 410);
		g.drawString("Speed", 0, 450);
		
		for(int i = 3; i < 13; i ++)
		{
			Bar.render(15 + 30 * i, 300, 30, 25, 0, g);
		}
		
		for(int i = 3; i < 13; i ++)
		{
			Bar.render(15 + 30 * i, 300 + 40, 30, 25, 0, g);
		}
		
		for(int i = 3; i < 13; i ++)
		{
			Bar.render(15 + 30 * i, 300 + 80, 30, 25, 0, g);
		}
		
		for(int i = 3; i < 13; i ++)
		{
			Bar.render(15 + 30 * i, 300 + 120, 30, 25, 0, g);
		}
		
		for(int i = 3; i < 13; i ++)
		{
			Bar.render(15 + 30 * i, 300 + 160, 30, 25, 0, g);
		}
		
		if(codex.index < codex.Items.size() - 1)
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

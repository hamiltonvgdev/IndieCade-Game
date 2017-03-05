package Quests;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import GameBasics.BasicNPC;
import GameBasics.Entity;
import NPCs.QuestNPC;
import Player.Player;

public class BasicQuest 
{
	Player player;
	String name;
	String des;
	ArrayList<BasicQuest> Quests;
	QuestNPC giver;
	boolean completed;
	boolean finished;
	
	public BasicQuest(String name, String des, QuestNPC npc)
	{
		this.name = name;
		this.des = des;
		giver = npc;
		completed = false;
		finished = false;
	
		player = giver.getPlayer();
	}
	
	public BasicQuest setConditions()
	{
		return this;
	}
	
	public void update()
	{
		check();
	}
	
	public void render(Graphics g)
	{
		g.drawString(name, name.length() * 4, Quests.indexOf(this) * 4);
		g.drawString(des, name.length() * 8 + des.length() * 4 + 5, Quests.indexOf(this) * 4);
	}
	
	public void complete()
	{
		if(finished)
		{
			completed = true;
			Quests.remove(this);
			award();
		}
	}
	
	public void forfeit()
	{
		Quests.remove(this);
		giver.accepted = false;
	}
	
	public void check()
	{
		
	}
	
	public void award()
	{
		
	}
	
	public void give(ArrayList<BasicQuest> Quests)
	{
		this.Quests = Quests;
		Quests.add(this);
	}
	
	public String getName() 
	{
		return name;
	}

	public boolean isComplete() 
	{
		return completed;
	}
}

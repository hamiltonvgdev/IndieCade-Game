package Quests;

import java.util.ArrayList;

import GameBasics.BasicNPC;
import NPCs.QuestNPC;

public class TimeQuest extends BasicQuest
{
	long tick;
	long limit;
	
	public TimeQuest(String name, String des, QuestNPC npc) 
	{
		super(name, des, npc);
	}
	
	public TimeQuest setCondition(long delay)
	{
		this.limit = delay;
		return this;
	}
	
	@Override
	public void give(ArrayList<BasicQuest> Quests)
	{
		tick = System.currentTimeMillis();
		super.give(Quests);
	}
	
	@Override
	public void check()
	{	
		if(System.currentTimeMillis() - tick >= limit)
		{
			finished = true;
		}
	}
}

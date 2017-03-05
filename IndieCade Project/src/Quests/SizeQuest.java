package Quests;

import java.util.ArrayList;

import GameBasics.BasicNPC;
import NPCs.QuestNPC;

public class SizeQuest extends BasicQuest
{
	ArrayList list;
	int size;
	
	public SizeQuest(String name, String des, QuestNPC npc) 
	{
		super(name, des, npc);
	}

	public SizeQuest setCondition(ArrayList list, int size)
	{
		this.list = list;
		this.size = size;
		
		return this;
	}
	
	public void check()
	{
		if(list.size() >= size)
		{
			finished = true;
		}
	}
}

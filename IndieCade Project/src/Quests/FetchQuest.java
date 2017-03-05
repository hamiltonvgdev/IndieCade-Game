package Quests;

import java.util.ArrayList;

import GameBasics.BasicNPC;
import NPCs.QuestNPC;

public class FetchQuest extends BasicQuest
{
	ArrayList list;
	Object thing;
	
	public FetchQuest(String name, String des, QuestNPC npc)
	{
		super(name, des, npc);
	}
	
	public void setCondition(ArrayList list, Object thing)
	{
		this.list = list;
		this.thing = thing;
	}
	
	@Override
	public void check()
	{
		if(list.contains(thing))
		{
			finished = true;
		}
	}
}

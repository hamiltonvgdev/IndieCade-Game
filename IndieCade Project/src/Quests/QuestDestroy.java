package Quests;

import java.util.ArrayList;

import NPCs.QuestNPC;

public class QuestDestroy extends BasicQuest
{
	BasicQuest target;
	
	public QuestDestroy(String name, String des, QuestNPC npc) 
	{
		super(name, des, npc);
	}
	
	public QuestDestroy setCondition(BasicQuest quest)
	{
		target = quest;
		return this;
	}
	
	@Override
	public void give(ArrayList<BasicQuest> quests)
	{
		super.give(quests);
		
		if(Quests.contains(target))
		{
			Quests.get(Quests.indexOf(target)).complete();
			complete();
		}else
		{
			forfeit();
		}
	}
}

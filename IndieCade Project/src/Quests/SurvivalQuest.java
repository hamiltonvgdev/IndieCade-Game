package Quests;

import NPCs.QuestNPC;

public class SurvivalQuest extends TimeQuest
{

	public SurvivalQuest(String name, String des, QuestNPC npc) 
	{
		super(name, des, npc);
	}
	
	public void check()
	{
		if(player.getHealth() <= 0)
		{
			forfeit();
		}
	}
}

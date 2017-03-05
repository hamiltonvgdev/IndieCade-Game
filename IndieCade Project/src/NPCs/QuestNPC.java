package NPCs;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import GameBasics.BasicNPC;
import Player.Player;
import Quests.BasicQuest;
import Util.Button;

public class QuestNPC extends BasicNPC
{
	BasicQuest quest;
	Button yes;
	Button no;
	public boolean accepted; 
	String question;
	
	public QuestNPC(Player player, String name, float range) 
	{
		super(player, name, range);
		accepted = false;
	}
	
	public QuestNPC setQuest(BasicQuest quest)
	{
		this.quest = quest;
		question = "Will you accepted this " + quest.getName() + " Quest from me?";
		return this;
	}
	
	@Override
	public QuestNPC setCoordinates(float x, float y)
	{
		yes = new Button("Sure", x, y - height / 2 - 10, 1).setDimensions(64, 16);
		this.x = x;
		this.y = y;
		return this;
	}
	
	public void update()
	{
		super.update();
		
		if(yes.clicked)
		{
			quest.give(player.getQuests());
			accepted = true;
		}
	}
	
	public void render(Graphics g) throws SlickException
	{
		super.render(g);
	}
	
	public void speak(Graphics g) throws SlickException
	{
		if(accepted)
		{
			super.speak(g);
		}else
		{
			speak(question, g);
			
			yes.update();
			
			yes.render(g);
		}
		
		
	}
	
	@Override
	public void action()
	{
		if(quest.isComplete())
		{
			quest.complete();
		}
	}
}

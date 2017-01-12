package Stance;

import java.util.ArrayList;

import BoneStructure.BoneStructure;

public class Stance 
{
	String name;
	BoneStructure body; 
	
	ArrayList<ArrayList<Action>> ActionLists;
	int[] Index;
	int index;
	long tick;
	
	public Stance(String name, BoneStructure body, int size)
	{
		this.name = name;
		
		this.body = body;
		
		ActionLists = new ArrayList<ArrayList<Action>>();
		
		Index = new int[size];
		
		for(int i = 0; i < size; i ++)
		{
			ActionLists.add(new ArrayList<Action>());
			ActionLists.get(i).add(new Action(null, 0, 0, 1));
			
			Index[i] = 0;
		}
		tick = System.currentTimeMillis();
		index = 0;
	}
	
	public void loop()
	{
		for(int i = 0; i < ActionLists.size(); i ++)
		{
			if(ActionLists.get(i).size() > 1)
			{
				// herp and derp triggers in one go
				if(Index[i] >= ActionLists.get(i).size())
				{
					Index[i] = 0;System.out.println(i + "derp");
					tick = System.currentTimeMillis();
				}
				
				if(System.currentTimeMillis() - tick > ActionLists.get(i).get(Index[i]).time )
				{
					if(Index[i] < ActionLists.get(i).size())
					{
						Index[i] ++;
						tick = System.currentTimeMillis();
					}
				}else
				{
					index = Index[i];
					ActionLists.get(i).get(Index[i]).update(body, this);
				}
			}
		}
	}
	
	public void single()
	{
		for(int i = 0; i < ActionLists.size(); i ++)
		{
			if(ActionLists.get(i).size() > 1)
			{
				if(Index[i] >= ActionLists.get(i).size())
				{
					Index[i] = 0;
					tick = System.currentTimeMillis();
				}
				
				if(System.currentTimeMillis() - tick > ActionLists.get(i).get(Index[i]).time)
				{
					
				}else
				{
					index = Index[i];
					ActionLists.get(i).get(Index[i]).update(body, this);
				}
			}
		}
	}
	
	public Stance addAction(Action action, int Index)
	{
		ActionLists.get(Index).add(action);
		
		return this;
	}
	
	public String getName()
	{
		return name;
	}
}

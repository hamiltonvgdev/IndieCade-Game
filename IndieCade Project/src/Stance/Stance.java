package Stance;

import java.io.Serializable;
import java.util.ArrayList;

import BoneStructure.BoneStructure;

public class Stance implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5981244426559055944L;
	String name;
	BoneStructure body; 
	
	ArrayList<ArrayList<Action>> ActionLists;
	int[] Index;
	long[] Tick;
	int index;
	
	public Stance(String name, BoneStructure body, int size)
	{
		this.name = name;
		
		this.body = body;
		
		ActionLists = new ArrayList<ArrayList<Action>>();
		
		Index = new int[size];
		Tick = new long[size];
		
		for(int i = 0; i < size; i ++)
		{
			ActionLists.add(new ArrayList<Action>());
			
			Index[i] = 0;
			Tick[i] = System.currentTimeMillis();
			
		}
		index = 0;
	}
	
	public void loop()
	{
		for(int i = 0; i < ActionLists.size(); i ++)
		{
			if(Index[i] >= ActionLists.get(i).size())
			{
				Tick[i] = System.currentTimeMillis();
				Index[i] = 0;
				ActionLists.get(i).get(Index[i]).reset();
			}
			
			if(System.currentTimeMillis() - Tick[i] > ActionLists.get(i).get(Index[i]).time)
			{
				if(Index[i] < ActionLists.get(i).size())
				{
					Index[i] ++;
					if(Index[i] < ActionLists.get(i).size())
					{
						ActionLists.get(i).get(Index[i]).reset();
					}
					Tick[i] = System.currentTimeMillis();
				}
			}else
			{
				index = Index[i];
				ActionLists.get(i).get(Index[i]).update(this);
			}
		}
	}
	
	public void single()
	{
		for(int i = 0; i < ActionLists.size(); i ++)
		{
			if(ActionLists.get(i).size() > 1)
			{
				if(Index[i] < ActionLists.get(i).size())
				{
					if(System.currentTimeMillis() - Tick[i] > ActionLists.get(i).get(Index[i]).time)
					{
						if(Index[i] < ActionLists.get(i).size())
						{
							Index[i] ++;
							if(Index[i] < ActionLists.get(i).size())
							{
								ActionLists.get(i).get(Index[i]).reset();
							}else
							{
								ActionLists.get(i).get(0).reset();
							}
							Tick[i] = System.currentTimeMillis();
						}
					}else
					{
						index = Index[i];
						ActionLists.get(i).get(Index[i]).update(this);
					}
				}
				
			}
		}
	}
	
	public Stance addAction(Action action, int Index)
	{
		Action Caction = action;
		Caction.reset();
		ActionLists.get(Index).add(Caction);
		
		return this;
	}
	
	public void reset()
	{
		for(int i = 0; i < ActionLists.size(); i ++)
		{
			for(int j = 0; j < ActionLists.get(i).size(); j ++)
			{
				ActionLists.get(i).get(j).reset();
			}
		}
	}
	
	public String getName()
	{
		return name;
	}
}

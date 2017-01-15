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
			ActionLists.get(i).add(new Action(null, 0, 0, 1));
			
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
			}
			
			if(System.currentTimeMillis() - Tick[i] > ActionLists.get(i).get(Index[i]).time)
			{
				if(Index[i] < ActionLists.get(i).size())
				{
					Index[i] ++;
					Tick[i] = System.currentTimeMillis();
				}
			}else
			{
				index = Index[i];
				ActionLists.get(i).get(Index[i]).update(body, this);
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
							Tick[i] = System.currentTimeMillis();
						}
					}else
					{
						index = Index[i];
						ActionLists.get(i).get(Index[i]).update(body, this);
					}
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

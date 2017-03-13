package Stance;

import java.util.ArrayList;

import BoneStructure.BoneStructure;

public class StanceList 
{
	public static ArrayList<Stance> Stances;
	
	public static void init(BoneStructure body)
	{
		Stances = new ArrayList<Stance>();
		
		Stance walk = new Stance("Walk", body, 4);
		
		walk.addAction(new Action(body, "Pelvic 1", 0, 250, 250), 0);
		walk.addAction(new Action(body, "Pelvic 1", 0, 270, 250), 0);
		walk.addAction(new Action(body, "Pelvic 1", 0, 290, 500), 0);

		walk.addAction(new Action(body, "Pelvic 2", 0, 290, 250), 1);
		walk.addAction(new Action(body, "Pelvic 2", 0, 270, 250), 1);
		walk.addAction(new Action(body, "Pelvic 2", 0, 250, 500), 1);
		

		walk.addAction(new Action(body, "Knee 2", 0, 250, 500), 2);
		walk.addAction(new Pause(body, "Knee 2", 0, 290, 250), 2);
		walk.addAction(new Action(body, "Knee 2", 0, 290, 250), 2);

		walk.addAction(new Action(body, "Knee 1", 0, 290, 250), 3);
		walk.addAction(new Pause(body, "Knee 1", 0, 290, 250), 3);
		walk.addAction(new Action(body, "Knee 1", 0, 250, 500), 3);
		
		Stances.add(walk);
		
		Stance test = new Stance("Test", body, 2);
		test.addAction(new Action(body, "Knee 1", 0, 0, 2000), 0);
		test.addAction(new Action(body, "Knee 1", 0, 180, 2000), 0);
		test.addAction(new Action(body, "Knee 2", 0, 0, 1000), 1);
		test.addAction(new Action(body, "Knee 2", 0, 90, 1000), 1);
		test.addAction(new Action(body, "Knee 2", 0, 180, 1000), 1);
		test.addAction(new Action(body, "Knee 2", 0, 90, 1000), 1);
		Stances.add(test);
	}
	
	public static Stance getStance(String name)
	{
		Stance derp = null;
		
		for(int i = 0; i < Stances.size(); i ++)
		{
			if(Stances.get(i).getName().equals(name))
			{
				derp =  Stances.get(i);
			}
		}
		
		return derp;
	}
}

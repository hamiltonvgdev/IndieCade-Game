package Load;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Load 
{
	public static Object load(String location)
	{
		try
		{
			FileInputStream input=new FileInputStream(location);
			ObjectInputStream inputObject=new ObjectInputStream(input);
		
			return inputObject.readObject();
		
		} catch (IOException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}
}

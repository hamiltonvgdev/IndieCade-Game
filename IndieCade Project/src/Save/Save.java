package Save;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Save 
{
	public static void save(String location, Object obj)
	{
		try 
		{
			FileOutputStream stream=new FileOutputStream(location);
			ObjectOutputStream objOut=new ObjectOutputStream(stream);
		
			objOut.writeObject(obj);
			
			objOut.flush();
			objOut.close();
		
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}

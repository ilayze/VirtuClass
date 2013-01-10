package logicLayer;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

public class ClassroomsManager {
	LinkedList<Classroom> classes;
	
	public LinkedList<Classroom> getClasses()
	{
		return classes;
	}

	public boolean addClass(Classroom c)
	{
		if(c!=null && !classes.contains(c))
		{
			classes.add(c);
			return true;
		}
		return false;
	}
	
	public boolean removeClassroom(Classroom c)
	{
		
		if(classes.remove(c))
		{
			c.deleteData();
			return true;
		}
		return false;
	}
	
}

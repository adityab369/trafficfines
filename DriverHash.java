package traffic;

import java.util.*;
import java.io.*;

class DriverHash
{
	Hashtable<String,Integer> fineTable;

	//Constructor

	public void initializeHash()
	{
		this.fineTable=new Hashtable<>();
	}

	//adding the violation from input file to the hash table

	public void insertHash(String reg)
	{
		int a;
		if(!fineTable.containsKey(reg))
		{
			this.fineTable.put(reg,1);
		}
		else
		{
			a=this.fineTable.get(reg);
			a++;
			this.fineTable.put(reg,a);
		}
	}

	//to display the hashtable

	public void HashDis()
	{
		System.out.println("\nLicense No"+"	"+"No of Violations");
		for (Map.Entry<String, Integer> entry : fineTable.entrySet()) 
		{
    		String key = entry.getKey();
    		Integer value = entry.getValue();
    		System.out.println ( key + "		" + value);
		}
	}

	//to clean up

	public void destroyHash()
	{
		fineTable.clear();
	}

	public void printViolators()
	{
		File vio=new File("C:/Users/aboggara/Desktop/traffic/Violators.txt");
		try
		{
			PrintWriter writer= new PrintWriter(new FileWriter(vio));
			Set<String> keys = fineTable.keySet();
			for(String x:keys)
			{
				int i=fineTable.get(x);
				//System.out.println(i); 
				if(i>=3)
				{
					writer.println(x);
					//System.out.println(x);
				}
			}
			writer.flush();
		}
		catch(Exception e)
		{
			System.out.println("Error:"+e);
		}
	}
}
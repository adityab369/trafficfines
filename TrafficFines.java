package traffic;
import traffic.PoliceTree;
import traffic.DriverHash;
import java.util.*;
import java.io.*;

public class TrafficFines
{
	public static void main(String args[]) 
	{
		BufferedReader reader;
		DriverHash dh=new DriverHash();
		dh.initializeHash();
		PoliceTree pt=new PoliceTree();
		try
		{
			reader=new BufferedReader(new FileReader("YourInputFilePath.txt"));
			String line=reader.readLine();
			while(line!=null)
			{
				String[] arr=line.split(",");
				int pid=Integer.parseInt(arr[0]);
				int amt=Integer.parseInt(arr[2]);
				pt.insertByPoliceId(pid,amt);
				dh.insertHash(arr[1]);
				line=reader.readLine();
			}
			dh.HashDis();
			System.out.println("\nPoliceID	FineAmt");
			pt.printPoliceTree(pt.root);
			dh.printViolators();
			System.out.println("\nPoliceID with bonus:");
			pt.printBonusPolicemen();
			System.out.println();
			dh.destroyHash();
			pt.destroyPoliceTree();
		}
		catch(Exception e)
		{
			System.out.println("Error:"+e);
		}
	}
}

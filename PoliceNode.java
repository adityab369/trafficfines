package traffic;

import java.util.*;
import java.io.*;

class PoliceNode
{
final int policeId;
int fineAmt;
PoliceNode left,right;

//Constructor for creating police node

public PoliceNode(int pid)
{
	this.policeId=pid;
	this.fineAmt=0;
	this.left=null;
	this.right=null;	
}
public PoliceNode(int pid,int a)
{
	this.policeId=pid;
	this.fineAmt=a;
	this.left=null;
	this.right=null;	
}

//adding the fine entry

public void addAmount(int fa)
{
	this.fineAmt+=fa;
}

//to display the police node

public void policeNodeDis()
{
	System.out.println("PoliceId:"+this.policeId);
	System.out.println("Fine Amount:"+this.fineAmt);
}
}
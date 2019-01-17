package traffic;
import traffic.PoliceNode;
import java.util.*;
import java.io.*;

public class PoliceTree
{
	PoliceNode root;
	PoliceNode head; 
    static PoliceNode prev = null;

    //to construct the police tree from the file input recursively

	private PoliceNode addRecursive(PoliceNode current, int value,int amt) 
	{
    	if (current == null) 
    	{
        	return new PoliceNode(value,amt);
    	}
    	if (value < current.policeId)
    	{
        	current.left = addRecursive(current.left,value,amt);
    	} 
    	else if (value > current.policeId) 
    	{
        	current.right = addRecursive(current.right,value,amt);
   		}
   		else 
   		{ 
   			current.addAmount(amt);
        	return current;
    	}
    	return current;
	}

	public void insertByPoliceId(int value,int amt)
	{
    	this.root = addRecursive(this.root,value,amt);
	}

    //to reorder the police tree based on fine amount recursively

    private PoliceNode addRecursiveByAmount(PoliceNode current, int value,int amt) 
    {
        if (current == null) 
        {
            return new PoliceNode(value,amt);
        }
        if (amt <= current.fineAmt)
        {
            current.left = addRecursive(current.left,value,amt);
        } 
        else if (amt > current.fineAmt) 
        {
            current.right = addRecursive(current.right,value,amt);
        }
        return current;
    }

    public void addByAmount(PoliceNode proot)
    {
        this.root = addRecursive(this.root,proot.policeId,proot.fineAmt);
    }

    public void reorderByFineAmount(PoliceTree ppid)
    {
        PoliceTree p;
        ppid.head=Bt2dll(ppid.root);
        do
        {
            addByAmount(ppid.head);
            ppid.head=ppid.head.right;
        }while(ppid.head.right!=null);
    }

    //to convert the binary police tree into doubly linked list

    public PoliceNode Bt2dll(PoliceNode rt)  
    { 
        PoliceNode hd=null;
        // Base case 
        if (rt == null) 
            return rt; 
   
        // Recursively convert left subtree 
        Bt2dll(rt.left); 
   
        // Now convert this node 
        if (prev == null)  
            hd = rt; 
        else
        { 
            rt.left = prev; 
            prev.right = rt; 
        } 
        prev = rt; 
   
        // Finally convert right subtree 
        Bt2dll(rt.right);
        return hd; 
    }
    //displaying the binary tree using the inorder traversal(Increasing order)
    public PoliceNode printPoliceTree(PoliceNode rt)
    {
        if (rt == null)
        { 
            return rt;
        }
        printPoliceTree(rt.left);
        System.out.println(rt.policeId+"                "+rt.fineAmt);
        printPoliceTree(rt.right);
        return rt;
    }
    //finding the maximum amount for the bonus calculation
    //Breadth first search
    public int findmax(PoliceNode rt)
    {
        int max=0;
        Queue<PoliceNode> queue = new LinkedList<PoliceNode>(); 
        queue.add(rt); 
        while (!queue.isEmpty())  
        { 
            //poll is used to pop the head of the queue
            PoliceNode tempNode = queue.poll(); 
            if(max<tempNode.fineAmt)
            {
                max=tempNode.fineAmt;
            }
  
            /*Enqueue left child */
            if (tempNode.left != null) { 
                queue.add(tempNode.left); 
            } 
  
            /*Enqueue right child */
            if (tempNode.right != null) { 
                queue.add(tempNode.right); 
            } 
        } 
        return max;
    }
    //printing the policemen with the bonus amount
    public void printbonus(PoliceNode rt,int max)
    {
        File bon=new File("C:/Users/aboggara/Desktop/traffic/Bonus.txt");
        Queue<PoliceNode> queue = new LinkedList<PoliceNode>(); 
        try
        {
            PrintWriter writer= new PrintWriter(new FileWriter(bon));
            queue.add(rt); 
            while (!queue.isEmpty())  
            { 
            
                 PoliceNode tempNode = queue.poll(); 
                if(tempNode.fineAmt>(0.9*max))
                {
                    System.out.println(tempNode.policeId);
                    writer.println(tempNode.policeId);
                }
  
                /*Enqueue left child */
                if (tempNode.left != null)
                { 
                  queue.add(tempNode.left); 
                } 
  
                /*Enqueue right child */
                if (tempNode.right != null) 
                { 
                    queue.add(tempNode.right); 
                } 
            }
            writer.flush();
        }
        catch(Exception e)
        {
            System.out.println("Error"+e);
        }    
    }

    public void printBonusPolicemen()
    {
        int max=findmax(this.root);
        printbonus(this.root,max);
    }
    public void destroyPoliceTree()
    {
        this.root=destroy(this.root);
    }
    public PoliceNode destroy(PoliceNode p)
    {
        Queue<PoliceNode> queue = new LinkedList<PoliceNode>(); 
        try
        {
            queue.add(p); 
            while (!queue.isEmpty())  
            { 
            
                 PoliceNode tempNode = queue.poll(); 
                /*Enqueue left child */
                if (tempNode.left != null)
                { 
                  queue.add(tempNode.left); 
                } 
  
                /*Enqueue right child */
                if (tempNode.right != null) 
                { 
                    queue.add(tempNode.right); 
                } 
                tempNode=null;
            }
        }
        catch(Exception e)
        {
            System.out.println("Error"+e);
        }
        return null;    
    }
}
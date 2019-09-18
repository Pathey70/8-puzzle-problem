import java.util.LinkedList;
import java.util.Queue;
import java.util.*;
class Node
{
    Node children[]=null;
    Node parent=null;
    int puzzle[][]=null;
     
    Node(int p[][],Node pa)
    {
        puzzle=p;
        parent=pa;
    }
    public boolean equals(Object obj)
    {
        Node n1=(Node )obj;
       // n1.print();
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(this.puzzle[i][j]!=n1.puzzle[i][j])
                {
                    return false;
                }
            }
        }
        return true;
    }
    public void print()
    {
        System.out.println("**********");
        for (int i=0;i<puzzle.length;i++)
        {
            for (int j=0;j<puzzle.length;j++)
            {
                System.out.print(puzzle[i][j]);
                 
            }
            System.out.println("");
        }
        System.out.println("**********");
    }
     
    public void printChildren()
    {
        for (int i=0;i<4;i++)
    {
        if(children[i]!=null)
        {
            (children[i]).print();
            if(!Puzzle8.hm.containsKey(children[i]))
            {
                Puzzle8.hm.put(children[i],0);
                Puzzle8.q.add(children[i]);

            }
        }
    }
    }
     
    public void expand()
    {
        children=new Node[4];
        children[0]=expandDown();
        children[1]=expandUp();
        children[2]=expandLeft();
        children[3]=expandRight();
    }
     
    public Node expandDown()
    {
        Node n=new Node(copy(puzzle),this);
        int zeroXY[]=n.getZero();
        System.out.println(zeroXY[0]+" "+zeroXY[1]);
        if(zeroXY[1]-1>=0)
        {
            int temp=n.puzzle[zeroXY[0]][zeroXY[1]];
            n.puzzle[zeroXY[0]][zeroXY[1]]=n.puzzle[zeroXY[0]][zeroXY[1]-1];
            n.puzzle[zeroXY[0]][zeroXY[1]-1]=temp;
            //n.print();
            System.out.println("down");
            return n;
        }
        else
        {
            return null;
        }
         
         
    }
     
    public Node expandUp()
    {
        Node n=new Node(copy(puzzle),this);
        int zeroXY[]=n.getZero();      
        if(zeroXY[1]+1<puzzle.length)
        {
            int temp=n.puzzle[zeroXY[0]][zeroXY[1]];
            n.puzzle[zeroXY[0]][zeroXY[1]]=n.puzzle[zeroXY[0]][zeroXY[1]+1];
            n.puzzle[zeroXY[0]][zeroXY[1]+1]=temp;
            //n.print();
            System.out.println("up");

            return n;
        }
        else
        {
            return null;
        }
         
         
    }
     
    public Node expandRight()
    {
        Node n=new Node(copy(puzzle),this);
        int zeroXY[]=n.getZero();
        if(zeroXY[0]+1<puzzle.length)
        {
            int temp=n.puzzle[zeroXY[0]][zeroXY[1]];
            n.puzzle[zeroXY[0]][zeroXY[1]]=n.puzzle[zeroXY[0]+1][zeroXY[1]];
            n.puzzle[zeroXY[0]+1][zeroXY[1]]=temp;
            System.out.println("right");

            return n;
        }
        else
        {
            return null;
        }
         
    }
    public int[][] copy(int [][]puzzle)
    {
        int arr[][]=new int[3][3];
        for(int i=0;i<puzzle.length;i++)
        {
            for(int j=0;j<puzzle[i].length;j++)
            {
                arr[i][j]=puzzle[i][j];
            }
        }
        return arr;
    }
    public Node expandLeft()
    {
        Node n=new Node(copy(puzzle),this);
        int zeroXY []=n.getZero();
        if(zeroXY[0]-1>=0)
        {
            int temp=n.puzzle[zeroXY[0]][zeroXY[1]];
            n.puzzle[zeroXY[0]][zeroXY[1]]=n.puzzle[zeroXY[0]-1][zeroXY[1]];
            n.puzzle[zeroXY[0]-1][zeroXY[1]]=temp;
            //n.print();
            System.out.println("left");

            return n;
        }
        else
        {
            return null;
        }
         
    }
     
     
     
    int[] getZero()
    {
        int[] n=new int[2];
        for (int i=0;i<puzzle.length;i++)
        {
            for (int j=0;j<puzzle.length;j++)
            {
                if(puzzle[i][j]==0)
                {
                    n[0]=i;
                    n[1]=j;
                    return n;
                }
            }          
        }
        return null;
    }
}
public class Puzzle8
{
    static Queue<Node> q;
    static HashMap<Node,Integer> hm;
    static Node goal;
    static boolean flag=true;
    final static int GOAL [][]={{1,2,3},{4,5,0},{7,8,6}};
   
       
   
    public static void main(String args[])
    {
        q = new LinkedList<>();
        hm=new HashMap();
        int board[][]={{1,2,3},{4,5,6},{7,0,8}};    
        Node Start=new Node(board,null);
        goal=new Node(GOAL,null);
        q.add(Start);
        while(flag&&q.size()>0)
        {
            Node run=q.remove();
            if(run.equals(goal))
            {
                flag=false;
                run.print();
                break;
            }
            run.expand();
            run.printChildren();
        }
     
    }
}
/* To implement using dfs just change queue with stack*/

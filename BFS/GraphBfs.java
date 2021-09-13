/*
Name -> Shatadru Barua
Roll -> 001911001017
Assignment -> Get path between two nodes by BFS in a graph
Assigned by -> KCM sir
Input file ->
(input.txt)[Should be in same directory]

7 0 4
0 1 1 1 0 0 0
1 0 0 0 0 0 0
1 0 0 0 1 0 0
1 0 0 0 0 0 0
0 0 1 0 0 1 1
0 0 0 0 1 0 0
0 0 0 0 1 0 0

7 - number of nodes
0 - source node
4 - destination node

Then the adjacency matrix

Compile sequence -> javac GraphBfs.java
Execution sequence -> java GraphBfs

Output -> 

The nodes are auto generated from 0 to n-1
0 <= Source
1 2 3 
4 <= Destination

*/

import java.util.*;
import java.io.*;
 
class GraphBfs{

  private static void BFSUtils(int[][] l,int n,int src,int dest) {
    Queue<Integer> q = new LinkedList<Integer>();
    boolean [] visited = new boolean[n]; 
    q.add(src);
    while(q.isEmpty() == false) {
      int sz = q.size();
      
      for(int i=0;i<sz;++i) {
        int top = q.poll();
        if(visited[top]==false) {
          System.out.print(top+" ");
          if(top==src)
           System.out.print("<= Source");

          else if(top==dest)
          {
            System.out.println("<= Destination");
            break;
          }
          
          visited[top] = true;
           for(int j=0;j<n;++j) {
             if(l[top][j]==1 && visited[j]==false) {
               q.add(j);
             }
           }
          }
          
          if(visited[dest] == true)
          break;
       }
        System.out.println("");
        if(visited[dest] == true)
         break;
      }
    }

  public static void main(String args[]) throws Exception {
    File file = new File("input.txt");
    Scanner sc = new Scanner(file);

    System.out.println("The nodes are auto generated from 0 to n-1");
    
    int n = sc.nextInt();
    int src = sc.nextInt();
    int dest = sc.nextInt();

    int l[][] = new int[n][n];

    for(int i=0;i<n;++i) {
      for(int j=0;j<n;++j) {
        l[i][j] = sc.nextInt();
      }
    }

    BFSUtils(l,n,src,dest);
  }
}

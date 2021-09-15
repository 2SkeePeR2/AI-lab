/*
Name -> Shatadru Barua
Roll -> 001911001017
Assignment -> Get all the paths between source and destination node using DFS
Assigned by -> KCM sir
Input file ->
(input.txt)[Should be in same directory]

15
0 1 1 1 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 1 0 0 0 0 0 0 0 0 0 0
1 0 0 0 1 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 1 0 0 0 0 0 0 0
0 1 1 0 0 1 1 0 0 0 0 0 0 0 0
0 0 0 0 1 0 0 1 1 0 0 0 0 0 0
0 0 0 0 1 0 0 0 0 1 0 0 0 0 0
0 0 0 1 0 1 0 0 0 0 0 0 0 0 0
0 0 0 0 0 1 0 0 0 1 0 0 0 0 0
0 0 0 0 0 0 1 0 1 0 1 0 0 0 0
0 0 0 0 0 0 0 0 0 1 0 0 0 0 0
0 0 0 0 0 0 0 1 0 0 0 0 1 0 1
0 0 0 0 0 0 0 0 0 0 0 1 0 1 0
0 0 0 0 0 0 0 0 0 0 0 0 1 0 1
0 0 0 0 0 0 0 0 0 0 0 1 0 1 0
0 10

15 - number of nodes

Then the adjecency matrix

0 - source node
10 - destination node

Compile sequence -> javac GraphBfs.java
Execution sequence -> java GraphBfs

Output -> 

The following graph is->
Adjacent neighbours for node 0
0-1 
0-2 
0-3 

Adjacent neighbours for node 1
1-0 
1-4 

Adjacent neighbours for node 2
2-0 
2-4 

Adjacent neighbours for node 3
3-0 
3-7 

Adjacent neighbours for node 4
4-1 
4-2 
4-5 
4-6 

Adjacent neighbours for node 5
5-4 
5-7 
5-8 

Adjacent neighbours for node 6
6-4 
6-9 

Adjacent neighbours for node 7
7-3 
7-5 

Adjacent neighbours for node 8
8-5 
8-9 

Adjacent neighbours for node 9
9-6 
9-8 
9-10 

Adjacent neighbours for node 10
10-9 

Adjacent neighbours for node 11
11-7 
11-12 
11-14 

Adjacent neighbours for node 12
12-11 
12-13 

Adjacent neighbours for node 13
13-12 
13-14 

Adjacent neighbours for node 14
14-11 
14-13 

-----------------------------------------------------------------------
The paths are =>
0=> 1=> 4=> 5=> 8=> 9=> 10=> 
0=> 1=> 4=> 6=> 9=> 10=> 
0=> 2=> 4=> 5=> 8=> 9=> 10=> 
0=> 2=> 4=> 6=> 9=> 10=> 
0=> 3=> 7=> 5=> 4=> 6=> 9=> 10=> 
0=> 3=> 7=> 5=> 8=> 9=> 10=> 
*/

import java.io.*;
import java.util.*;

class GraphDFS {

    private static void printGraph(int l[][], int n) {
        System.out.println("The following graph is->");
        for (int i = 0; i < n; ++i) {
            System.out.println("Adjacent neighbours for node " + i);
            for (int j = 0; j < n; ++j) {
                if (l[i][j] == 1) {
                    System.out.println(i + "-" + j + " ");
                }
            }
            System.out.println();
        }
    }

    private static void DFSUtils(int l[][], int n, int src, int dest, HashSet<Integer> visited,
            LinkedList<Integer> currentPath, List<List<Integer>> ans) {
        HashSet<Integer> h = (HashSet) visited.clone();
        LinkedList<Integer> ll = (LinkedList) currentPath.clone();
        h.add(src);
        ll.add(src);

        if (src == dest) {
            ans.add(ll);
            return;
        }

        for (int j = 0; j < n; ++j) {
            if (l[src][j] == 1 && h.contains(j) == false) {
                DFSUtils(l, n, j, dest, h, ll, ans);
            }
        }
    }

    private static List<List<Integer>> findPathFromSourceToDestination(int l[][], int n, int src, int dest) {
        HashSet<Integer> h = new HashSet<>();
        LinkedList<Integer> ll = new LinkedList<>();
        List<List<Integer>> ans = new LinkedList<>();

        DFSUtils(l, n, src, dest, h, ll, ans);

        return ans;
    }

    private static boolean isPresent(int s, int n) {
        return (s >= 0 && s < n);
    }

    public static void main(String args[]) {
        try {
            File file = new File("./Input.txt");
            Scanner sc = new Scanner(file);
            int n = sc.nextInt();
            int l[][] = new int[n][n];

            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    l[i][j] = sc.nextInt();
                }
            }

            printGraph(l, n);
            System.out.println("-----------------------------------------------------------------------");
            int src = sc.nextInt();
            int dest = sc.nextInt();

            if (isPresent(src, n) == false || isPresent(dest, n) == false) {
                System.out.println("Invalid node given as input.\n");
            }

            else {
                List<List<Integer>> ans = findPathFromSourceToDestination(l, n, src, dest);
                System.out.println("The paths are =>");
                ans.forEach(items -> {
                    items.forEach(elements -> {
                        System.out.print(elements + "=> ");
                    });
                    System.out.println();
                });
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (Exception err) {
            System.out.println("Exception occured.");
        }
    }
}

/*
Name - Shatadru Barua
RollNo - 001911001017

Assignment - 
1. Implement the iterative deepening algorithm
2. Execute the implementation on the attached graph with depth 5
3. Execute the previously implemented DFS with depth 5 on the same graph presented here.
4. For both points 2 and 3, the graph will be written in a file and not in the program implementation
5. Write a document based on your observations of output 2 and the difference between the outputs of points 2 and 3.

Compilation - javac IDSImplementation.java
Execution - java IDSImplementation

Input - Input.txt

Output - OutputFileIDS.txt
	[Contains IDS traversal output]
	 OutputFileDFS.txt
	[Contains DFS traversal output]
*/
import java.io.*;
import java.util.*;

class DFS {
    private HashMap<String,List<String>> input_matrix;
    private File outputFile;
    private HashSet<String> visited;
    private StringBuffer st;
    private Long timeElapsed;
    private boolean found;

    public DFS(HashMap<String,List<String>> mp,String path) {
        this.input_matrix = mp;
        this.visited = new HashSet<>();
        this.outputFile = new File(path);
        this.st = new StringBuffer();
        this.timeElapsed = (long) 0;
        this.found = false;
    }

    private void DFSUtilsImplementation(String source,String dest) {
        if(visited.contains(source))
         return;
        
        st.append(source+" ");
        if(source.equals(dest))
         {
             this.found = true;
             return;
         }

        visited.add(source);
        if(input_matrix.containsKey(source)==true)
        {
           List<String> adjacents = input_matrix.get(source);
           for(String temp: adjacents) {
               if(!visited.contains(temp) && !this.found) {
                   DFSUtilsImplementation(temp,dest);
               }
            }
        }
    }

    public void DFSUtils(String source,String dest) {
        try {
            BufferedWriter buff = new BufferedWriter(new FileWriter(outputFile));
            this.visited.clear();
            long start = System.nanoTime();
            DFSUtilsImplementation(source,dest);
            long end = System.nanoTime();
            this.timeElapsed = (end-start);
            st.append("\n");
            buff.write(st.toString());
            if(this.found)
             buff.write("Destination Node found.");
            else
             buff.write("Destination Node not found.");

            buff.write("\nTime Elapsed for Depth First Search=> "+timeElapsed.toString());
            buff.close();
        } catch (IOException e) {
            System.out.println("IOException Occured");
        }
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }
}

class IDS {
    private HashMap<String,List<String>> input_matrix;
    private int max_depth;
    private File outputFile;
    private StringBuffer st;
    private Long timeElapsed;
    private boolean found;

    public IDS(HashMap<String,List<String>> mp,int max_depth,String path) {
        this.input_matrix = mp;
        this.max_depth = max_depth;
        this.outputFile = new File(path);
        this.st = new StringBuffer();
        this.timeElapsed = (long) 0;
        this.found = false;
    }

    private void DDFSWithMaxDepth(String source,String dest,int limit) {
        st.append(source+" ");
        if(source.equals(dest))
        {
            this.found = true;
            return;
        }

        if(limit<0)
        	return;
		
		if(input_matrix.containsKey(source)==true)
		{
	        List<String> adjacents = input_matrix.get(source);
			for(String temp: adjacents) {
				DDFSWithMaxDepth(temp,dest,limit-1);
			}
		}
    }

    public void DDFSUtils(String source,String dest) {
        try {
            BufferedWriter buff = new BufferedWriter(new FileWriter(outputFile));
            int i;
            for(i=0;i<=max_depth;++i) {
                String depthLine = "Depth->"+i+"\n";
                buff.write(depthLine);
                st.delete(0,st.length());
                long start = System.nanoTime();
                DDFSWithMaxDepth(source,dest,i);
                long end = System.nanoTime();
                this.timeElapsed = timeElapsed+(end-start);
                st.append("\n");
                buff.write(st.toString());
                if(this.found==true)
                 break;
            }
            st.delete(0, st.length()-1);
            if(this.found==true)
             st.append("Destination node found at Depth "+i+"\n");
            else 
             st.append("Destination node not found.\n");
             
            buff.write(st.toString());
            buff.write("Time Elapsed for Deepening Depth First Search=> "+timeElapsed.toString());
            buff.close();
        }
        catch(IOException exception) {
            System.out.println("IOException found.");
        }
        
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }
}

class IDSImplementation {

	public static void main(String args[]) {
		try {
			File file = new File("./Input.txt");
			Scanner sc = new Scanner(file);
			String t = sc.nextLine();
            		int n = Integer.parseInt(t);
			HashMap<String,List<String>> mp = new HashMap<>();
			for(int i=0;i<n;++i) {
				String temp = sc.nextLine();
				String adjacents[] = temp.split("-",2);
				if(!mp.containsKey(adjacents[0])) {
				    ArrayList<String> arr = new ArrayList<String>();
				    arr.add(adjacents[1]);
				    mp.put(adjacents[0],arr);
				}

				else {
				    mp.get(adjacents[0]).add(adjacents[1]);
				}
			}
			int max_depth = sc.nextInt();
		    	sc.nextLine();
		    	String source = sc.nextLine();
		    	String dest = sc.nextLine();
		    	sc.close();
				IDS g = new IDS(mp,max_depth,"OutputFileIDS.txt");
				g.DDFSUtils(source,dest);
		    	DFS g2 = new DFS(mp,"OutputFileDFS.txt");
		    	g2.DFSUtils(source,dest);
		}
		catch( FileNotFoundException e) {
				System.out.println("File not found");
		}
		catch(Exception err) {
				System.out.println(err);
		}
	}
}

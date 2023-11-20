/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;

class opr {
	public static void main (String[] args) {
        Scanner s=new Scanner(System.in);
        System.out.println("enter no of frames:");
        int noFrames=s.nextInt();
        System.out.println("enter the size of reference string:");
        int sizeRS=s.nextInt();
        int RString[]=new int[sizeRS];
        System.out.println("enter the reference string space separated:");
        for(int i=0;i<sizeRS;i++){
            RString[i]=s.nextInt();
        }
        int frames[]=new int[noFrames];
        boolean hashTable[]=new boolean[100];
        Arrays.fill(hashTable,false);
        Arrays.fill(frames,-1);
        int cur=0;
        int pageFaults=0;
        int pageHit=0;
        int curIndex=0;
        for(int i=0;i<sizeRS;i++){
          if(curIndex<noFrames){
             pageFaults++;
             frames[curIndex]=RString[i];
             hashTable[RString[i]]=true;
             curIndex++;
          }else{
            if(hashTable[RString[i]]){
               pageHit++;
            }else{
                pageFaults++;
               int last=-1;
               int replaceIndex=0;
               for(int j=0;j<noFrames;j++){
                boolean flag=true;
                for(int k=i;k<sizeRS;k++){
                    if(frames[j]==RString[k]){
                        flag=false;
                        if(k>last){
                            last=k;
                            replaceIndex=j;
                        }
                        break;
                    }
                }
                if(flag){
                    replaceIndex=j;
                    break;
                }
               }

                int random=frames[replaceIndex];
                frames[replaceIndex]=RString[i];
                hashTable[RString[i]]=true;
                hashTable[random]=false;
            
            }
          }
          System.out.print(RString[i]+" : ");
          for(int bin:frames){
            System.out.print(bin+" ");
          }
          System.out.println();
        }
        System.out.println("pageHits:"+pageHit+" pageFaults : "+pageFaults);
        
	}
}









import java.util.*;
import java.util.ArrayList;

public class OptimalPageReplacement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of frames
        System.out.println("Enter the number of frames:");
        int no_frames = scanner.nextInt();

        // Input the number of page references
        System.out.println("Enter the number of page references:");
        int no_pagerf = scanner.nextInt();

        // Input page reference string
        System.out.println("Enter space-separated page reference string:");
        ArrayList<Integer> page_rs = new ArrayList<>();

        for (int i = 0; i < no_pagerf; i++) {
            int page = scanner.nextInt();
            page_rs.add(page);
        }

        ArrayList<Integer> frames = new ArrayList<>();
        int pagefaults = 0;

        // Iterate through the page reference string
        for (int i = 0; i < no_pagerf; i++) {
            int page = page_rs.get(i);

            if (!frames.contains(page)) {
                pagefaults++;
                // If there is space in frames, add the page
                if (frames.size() < no_frames) {
                    frames.add(page);
                } else {
                    // Call the function to predict the optimal page
                    int index = predictOptimal(frames, page_rs, i);
                    frames.set(index, page);
                }
            }
        }
        System.out.println("Number of page faults: " + pagefaults);

        scanner.close();
    }

    // Function to predict the optimal page
    private static int predictOptimal(ArrayList<Integer> frames, ArrayList<Integer> page_rs, int curr) {
        int farthest = -1;
        int index = -1;

        // Loop through frames
        for (int i = 0; i < frames.size(); i++) {
            int frame = frames.get(i);
            int j;

            // Loop through page references from the current position
            for (j = curr; j < page_rs.size(); j++) {
                // If the frame is found in future references
                if (frame == page_rs.get(j)) {
                    // Update farthest and index if the current position is farther
                    if (j > farthest) {
                        farthest = j;
                        index = i;
                    }
                    break;
                }
            }

            // If the frame is not found in future references, return the current index
            if (j == page_rs.size()) {
                return i;
            }
        }

        // Return the index if found, otherwise, return 0
        return (index == -1) ? 0 : index;
    }
}

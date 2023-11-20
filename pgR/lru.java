/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;

class lru {
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
        boolean hashTable[]=new boolean[100];
        Arrays.fill(hashTable,false);
        int pageFaults=0;
        int pageHit=0;
        LinkedList<Integer> frames=new LinkedList<>();
        for(int i=0;i<sizeRS;i++){
             if(hashTable[RString[i]]){
               frames.remove(RString[i]);
               frames.addLast(RString[i]);
               pageHit++;
             }else{
               pageFaults++;
               if(frames.size()<noFrames){
                   frames.addLast(RString[i]);
                   hashTable[RString[i]]=true;
               }else{
                   hashTable[frames.removeFirst()]=false;
                   frames.addLast(RString[i]);
                   hashTable[RString[i]]=true;
               }
             }
              for(int j:frames){
                System.out.print(j+" ");
              }
              System.out.println();
        }
        System.out.println("pageHits:"+pageHit+" pageFaults : "+pageFaults);
        
	}
}





























import java.util.*;

class LRUCache {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Input the number of frames
        System.out.println("Enter the number of frames:");
        int numberOfFrames = scanner.nextInt();
        
        // Input the size of the reference string
        System.out.println("Enter the size of the reference string:");
        int referenceStringSize = scanner.nextInt();
        
        int referenceString[] = new int[referenceStringSize];
        
        // Input the reference string space separated
        System.out.println("Enter the reference string (space separated):");
        for (int i = 0; i < referenceStringSize; i++) {
            referenceString[i] = scanner.nextInt();
        }
        

        
        int pageFaults = 0;
        int pageHits = 0;
        
        LinkedList<Integer> frames = new LinkedList<>();
        boolean pageHashTable[] = new boolean[100];
        Arrays.fill(pageHashTable, false);
        
        for (int i = 0; i < referenceStringSize; i++) {
            if (pageHashTable[referenceString[i]]) {
                // Page hit
                pageHits++;
                frames.remove(referenceString[i]);
                frames.addLast(referenceString[i]);
            } else {
                // Page fault
                pageFaults++;
                if (frames.size() < numberOfFrames) {
                    frames.addLast(referenceString[i]);
                    pageHashTable[referenceString[i]] = true;
                } else {
                    pageHashTable[frames.removeFirst()] = false;
                    frames.addLast(referenceString[i]);
                    pageHashTable[referenceString[i]] = true;
                }
            }
            
            // Print the current state of frames
            for (int page : frames) {
                System.out.print(page + " ");
            }
            System.out.println();
        }
        
        System.out.println("Page Hits: " + pageHits + " Page Faults: " + pageFaults);
        scanner.close();
    }
}

/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;

class RR {
	public static void main (String[] args) {
        Scanner s=new Scanner(System.in);
        System.out.println("enter no of process:");
        int no=s.nextInt();
        int wait[]=new int[no];
        int turnAround[]=new int[no];
        int burst[]=new int[no];
        int ogburst[]=new int[no];
        for(int i=0;i<no;i++){
            wait[i]=0;
            System.out.println("Enter burst time for "+(i+1)+":");
            burst[i]=s.nextInt();
            ogburst[i]=burst[i];
        }
        int quantumTime;
        System.out.println("Enter quantum time: ");
        quantumTime=s.nextInt();
        int curTime=0;
        int count=0;
        int buffer=0;
        int i=0;
        while(count!=no){
            if(burst[i]>0){
             burst[i]-=(buffer+quantumTime);
             if(burst[i]==0){
                  wait[i]=curTime-wait[i];
                  count++;
                  curTime+=quantumTime;
                  turnAround[i]=curTime;
             }else if(burst[i]<0){
                  wait[i]=curTime-wait[i];
                  count++;
                  curTime+=burst[i]+buffer+quantumTime;
                  buffer-=burst[i];
                  turnAround[i]=curTime;

             }else{
                  wait[i]=curTime-wait[i];
                  curTime+=quantumTime;

             }
             i++;
             if(i==no){
                i=0;
             }
            }else{
                i++;
                if(i==no){
                    i=0;
                }
            }
        }
        
        for(i=0;i<no;i++){
            System.out.println(i+"  "+ogburst[i]+"  "+turnAround[i]+" "+wait[i]);
        }

	}
}
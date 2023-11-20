import java.util.Scanner;

public class pri {
    // Function to sort based on a specified column using selection sort
    public static void sortByColumn(int arr[][], int start, int end, int col) {
        for (int i = start; i < end - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < end; j++) {
                if (arr[j][col] < arr[minIndex][col]) {
                    minIndex = j;
                }
            }
            swapRows(arr, i, minIndex);
        }
    }

    // Function to swap rows in a 2D array
    private static void swapRows(int arr[][], int row1, int row2) {
        int cols = arr[0].length;
        for (int col = 0; col < cols; col++) {
            int temp = arr[row1][col];
            arr[row1][col] = arr[row2][col];
            arr[row2][col] = temp;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        float averageTurnaroundTime = 0, averageWaitingTime = 0;
        int processes[][] = new int[10][6];

        // Input the number of processes
        System.out.print("Enter the number of processes: ");
        int numberOfProcesses = scanner.nextInt();

        // Input arrival time, burst time, and priority for each process
        for (int i = 0; i < numberOfProcesses; i++) {
            System.out.print("Arrival Time for Process " + (i + 1) + ": ");
            processes[i][0] = scanner.nextInt();
            System.out.print("Burst Time for Process " + (i + 1) + ": ");
            processes[i][1] = scanner.nextInt();
            System.out.print("Priority for Process " + (i + 1) + ": ");
            processes[i][2] = scanner.nextInt();
        }

        // Sort processes based on arrival time using selection sort
        sortByColumn(processes, 0, numberOfProcesses, 0);

        int flag = 0, currentTimePointer = processes[0][1];

        // Priority Scheduling Algorithm
        for (int i = 0; i < numberOfProcesses - 1; i++) {
            int first = i + 1, last = 0;
            for (int j = i + 1; j < numberOfProcesses; j++) {
                if (processes[j][0] <= currentTimePointer) {
                    last = j;
                    flag = 1;
                } else {
                    break;
                }
            }
            if (flag == 1) {
                sortByColumn(processes, first, last + 1, 2); // Sort by priority
                currentTimePointer = currentTimePointer + processes[first][1];
            }
        }

        int temp = 0;

        // Calculate completion time, turnaround time, and waiting time for each process
        for (int k = 0; k < numberOfProcesses; k++) {
            processes[k][3] = temp + processes[k][1];
            temp = processes[k][3];
            processes[k][4] = processes[k][3] - processes[k][0];
            averageTurnaroundTime += processes[k][4];
            processes[k][5] = processes[k][4] - processes[k][1];
            averageWaitingTime += processes[k][5];
        }

        averageTurnaroundTime = averageTurnaroundTime / numberOfProcesses;
        averageWaitingTime = averageWaitingTime / numberOfProcesses;

        // Output the Priority Scheduling table
        System.out.println("\n\tPriority Scheduling");
        System.out.println("Arrival | Burst | Priority | Completion | TurnAround | Waiting");
        System.out.println("--------------------------------------------------------------");
        for (int l = 0; l < numberOfProcesses; l++) {
            System.out.println(processes[l][0] + "\t| " + processes[l][1] + "\t| " + processes[l][2] + "\t | " +
                    processes[l][3] + "\t\t| " + processes[l][4] + "\t | " + processes[l][5]);
        }
        System.out.println("--------------------------------------------------------------");
        System.out.println("Average Turn Around Time: " + averageTurnaroundTime);
        System.out.println("Average Waiting Time: " + averageWaitingTime);

        scanner.close();
    }
}





































import java.util.Scanner;

class PriorityScheduling {
    // Function to find the process with the least priority
    public static int findLeastPriority(int currentTime, int numOfProcesses, int arrivalTime[], int priority[], int flag[]) {
        int index = -1;
        int maxPriority = 0;

        // Iterate through processes to find the one with the least priority
        for (int i = 0; i < numOfProcesses; i++) {
            if (flag[i] == 1 && arrivalTime[i] <= currentTime) {
                if (maxPriority <= priority[i]) {
                    maxPriority = priority[i];
                    index = i;
                }
            }
        }

        // If a process is found, mark it as not available (flag=0)
        if (index != -1) {
            flag[index] = 0;
        }

        return index;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of processes:");
        int numOfProcesses = scanner.nextInt();

        int processId[] = new int[numOfProcesses];
        int arrivalTime[] = new int[numOfProcesses];
        int priority[] = new int[numOfProcesses];
        int burstTime[] = new int[numOfProcesses];

        // Input process details
        for (int i = 0; i < numOfProcesses; i++) {
            processId[i] = i + 1;
            System.out.println("Enter arrival time for Process " + processId[i] + ":");
            arrivalTime[i] = scanner.nextInt();
            System.out.println("Enter burst time for Process " + processId[i] + ":");
            burstTime[i] = scanner.nextInt();
            System.out.println("Enter priority for Process " + processId[i] + ":");
            priority[i] = scanner.nextInt();
        }

        // Sort processes based on arrival time
        for (int i = 0; i < numOfProcesses; i++) {
            for (int j = i; j < numOfProcesses; j++) {
                if (arrivalTime[i] > arrivalTime[j]) {
                    // Swap values for burst time, priority, and arrival time
                    int temp = burstTime[i];
                    burstTime[i] = burstTime[j];
                    burstTime[j] = temp;

                    temp = priority[i];
                    priority[i] = priority[j];
                    priority[j] = temp;

                    temp = arrivalTime[i];
                    arrivalTime[i] = arrivalTime[j];
                    arrivalTime[j] = temp;
                }
            }
        }

        

        // Print the input process details
        System.out.println("AT  BT  PT");
        for (int i = 0; i < numOfProcesses; i++) {
            System.out.println(arrivalTime[i] + "   " + burstTime[i] + "   " + priority[i]);
        }

        System.out.println("After Scheduling:");
        System.out.println("AT  BT  PT  CT  TAT");
        
        int completeTime = 0;
        int count = 0;
        
        // Perform scheduling
        while (count < numOfProcesses) {
            int temp = findLeastPriority(completeTime, numOfProcesses, arrivalTime[count], priority[count], processId[count]);
            if (temp != -1) {
                System.out.println(
                        arrivalTime[temp] + "   " + burstTime[temp] + "   " + priority[temp] + "   " + (completeTime + burstTime[temp]) + "   " + (completeTime + burstTime[temp] - arrivalTime[temp]));
                completeTime += burstTime[temp];
                count++;
            } else {
                completeTime++;
            }
        }

        scanner.close();
    }
}

import java.util.Scanner;

public class firstc {
    // Function to swap elements in arrays
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of processes
        System.out.println("Enter the number of processes:");
        int numberOfProcesses = scanner.nextInt();

        int[] processId = new int[numberOfProcesses];
        int[] arrivalTime = new int[numberOfProcesses];
        int[] burstTime = new int[numberOfProcesses];
        int[] completionTime = new int[numberOfProcesses];
        int[] waitingTime = new int[numberOfProcesses];
        int[] turnaroundTime = new int[numberOfProcesses];

        float averageWaitingTime = 0, averageTurnaroundTime = 0;

        // Input arrival time and burst time for each process
        for (int i = 0; i < numberOfProcesses; i++) {
            processId[i] = i + 1;
            System.out.println("Enter arrival time for process " + processId[i] + ":");
            arrivalTime[i] = scanner.nextInt();
            System.out.println("Enter burst time for process " + processId[i] + ":");
            burstTime[i] = scanner.nextInt();
        }

        // Sort processes based on arrival time using bubble sort
        for (int i = 0; i < numberOfProcesses; i++) {
            for (int j = 0; j < numberOfProcesses - (i + 1); j++) {
                if (arrivalTime[j] > arrivalTime[j + 1]) {
                    // Use the swap function to avoid code repetition
                    swap(arrivalTime, j, j + 1);
                    swap(burstTime, j, j + 1);
                    swap(processId, j, j + 1);
                }
            }
        }

        // Calculate completion time, turnaround time, and waiting time for each process
        for (int i = 0; i < numberOfProcesses; i++) {
            if (i == 0) {
                completionTime[i] = arrivalTime[i] + burstTime[i];
            } else {
                if (arrivalTime[i] > completionTime[i - 1]) {
                    completionTime[i] = arrivalTime[i] + burstTime[i];
                } else {
                    completionTime[i] = burstTime[i] + completionTime[i - 1];
                }
            }
            turnaroundTime[i] = completionTime[i] - arrivalTime[i];
            waitingTime[i] = turnaroundTime[i] - burstTime[i];
            averageTurnaroundTime += turnaroundTime[i];
            averageWaitingTime += waitingTime[i];
        }

        // Output the process details
        System.out.println("\nProcessID  Arrival  Burst  Complete  Turnaround  Waiting");
        for (int i = 0; i < numberOfProcesses; i++) {
            System.out.println(processId[i] + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" +
                    completionTime[i] + "\t\t" + turnaroundTime[i] + "\t\t" + waitingTime[i]);
        }

        scanner.close();

        // Output average waiting time and average turnaround time
        System.out.println("\nAverage Waiting Time: " + averageWaitingTime / numberOfProcesses);
        System.out.println("Average Turnaround Time: " + averageTurnaroundTime / numberOfProcesses);
    }
}

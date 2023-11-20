import java.util.*;

public class FIFO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of frames
        System.out.println("Enter the number of frames:");
        int no_frames = scanner.nextInt();

        int[] frames = new int[no_frames];
        Arrays.fill(frames, -1);

        // Input the number of pages
        System.out.println("Enter the number of pages:");
        int no_pages = scanner.nextInt();

        int[] page_rs = new int[no_pages];
        System.out.print("Enter the page reference string:");

        // Input page reference string
        for (int i = 0; i < no_pages; i++) {
            page_rs[i] = scanner.nextInt();
        }

        

        int pagefault = 0;
        int curr = 0;

        // Iterate through the page reference string
        for (int pages : page_rs) {
            boolean pagehit = false;

            // Check if the page is already in the frames
            for (int frame : frames) {
                if (pages == frame) {
                    pagehit = true;
                    break;
                }
            }

            // If page is not in frames, replace the oldest page
            if (!pagehit) {
                frames[curr] = pages;
                curr = (curr + 1) % no_frames;
                pagefault++;
            }

            // Output the current state of frames
            System.out.print("\nFrames: ");
            for (int f : frames) {
                System.out.print(f + " ");
            }
        }

        // Output the total number of page faults
        System.out.println("\nTotal number of page faults: " + pagefault);

        scanner.close();
    }
}

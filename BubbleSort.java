import java.io.*;
import java.util.*;

public class BubbleSort {
    
    // Method to create an array of random integers
    public static int[] createRandomArray(int arrayLength) {
        Random rand = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = rand.nextInt(101); // Random integers between 0 and 100
        }
        return array;
    }
    
    // Method to write an array to a file
    public static void writeArrayToFile(int[] array, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int num : array) {
                writer.write(num + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    // Method to read an array from a file
    public static int[] readFileToArray(String filename) {
        List<Integer> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
    
    // Method to perform Bubble Sort
    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap elements
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; // Optimization to stop early if no swaps are needed
        }
    }
    
    // Main method to handle user input
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Generate a random array and store it in a file");
            System.out.println("2. Read an array from a file, sort it, and save it");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the array length: ");
                    int length = scanner.nextInt();
                    scanner.nextLine();
                    int[] array = createRandomArray(length);
                    System.out.print("Enter the filename to save the array: ");
                    String filename = scanner.nextLine();
                    writeArrayToFile(array, filename);
                    System.out.println("Array saved to " + filename);
                    break;

                case 2:
                    System.out.print("Enter the filename to read the array: ");
                    String inputFilename = scanner.nextLine();
                    int[] readArray = readFileToArray(inputFilename);
                    bubbleSort(readArray);
                    System.out.print("Enter the filename to save the sorted array: ");
                    String outputFilename = scanner.nextLine();
                    writeArrayToFile(readArray, outputFilename);
                    System.out.println("Sorted array saved to " + outputFilename);
                    break;
                
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}


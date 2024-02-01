import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        int arraySize;
        String sortedArrayFile;
        Scanner input = new Scanner(System.in);
        System.out.println("How long is your array?");
        arraySize = input.nextInt();
        int[] arr = createRandomArray(arraySize);
        writeArrayToFile(arr, "input.txt");
        System.out.println("The Original (unsorted) array: ");
        printArray(arr);
        bubbleSort(arr);
        System.out.println("Please input the file that you want to store the sorted array: ");
        sortedArrayFile = input.next();
        writeArrayToFile(arr, sortedArrayFile);
        System.out.println("The Sorted array: ");
        printArray(arr);
        // the original (unsorted) array is stored in the "input.txt" file
        int[] arrFromFile = readFileToArray("input.txt");
        int[] sortArrFromFile = readFileToArray(sortedArrayFile);
        System.out.println("Array read from file: ");
        printArray(arrFromFile);
        input.close();
    }

    public static int[] createRandomArray(int arrayLength) {
        int[] arr = new int[arrayLength];
        Random rand = new Random();
        for (int i = 0; i < arrayLength; i++) {
            arr[i] = rand.nextInt(101);
        }
        return arr;
    }

    public static void writeArrayToFile(int[] array, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < array.length; i++) {
                try {
                    writer.write(array[i] + "\n");
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int[] readFileToArray(String filename) {
        List<Integer> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileReader(filename))) {
            while (scanner.hasNextInt()) {
                list.add(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        Arrays.sort(arr);
        return arr;
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}

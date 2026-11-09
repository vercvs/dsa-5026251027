package unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void sortCards(int[] arr, int passes) {
        int n = arr.length;

        for (int i = 0; i < passes; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static int searchCard(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("cards.txt"));
        int n = sc.nextInt();
        int[] input = new int[n];

        for (int i = 0; i < n; i++) {
            input[i] = sc.nextInt();
        }

        sortCards(input, 2);

        for (int i = 0; i < n; i++) {
            System.out.print(input[i] + " ");
        }

        int target = sc.nextInt();

        for (int i = 0; i < target; i++) {
            int targets = sc.nextInt();
            int index = searchCard(input, targets);

            if (index >= 0) {
                System.out.println(targets + ": FOUND at index " + index);
            } else {
                System.out.println(targets + ": NOT FOUND");
            }
        }

    }
}

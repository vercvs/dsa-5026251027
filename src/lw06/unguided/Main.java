package unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        BinarySearchTree bst = new BinarySearchTree();

        if (read.hasNextLine()) {
            String[] tokens = read.nextLine().trim().split("\\s+");
            for (String token : tokens) {
                if (!token.isEmpty()) {
                    bst.insert(Integer.parseInt(token));
                }
            }
        }

        int target = 0;
        if (read.hasNextInt()) {
            target = read.nextInt();
        }

        bst.insert(target);

        System.out.print("Preorder : ");
        bst.preOrder();

        System.out.print("Inorder : ");
        bst.inOrder();

        System.out.print("Postorder : ");
        bst.postOrder();

        System.out.println("Search " + target + " : " + bst.search(target));
        System.out.println("Minimum : " + bst.findMin());
        System.out.println("Maximum : " + bst.findMax());

        read.close();
    }
}
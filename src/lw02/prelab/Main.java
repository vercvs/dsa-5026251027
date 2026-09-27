package lw02.prelab;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
       Scanner scanner = new Scanner(
                Main.class.getResourceAsStream("transactions.txt")
        );

        LinkedList<String[]> transactionList = new LinkedList<>();
        LinkedList<String[]> customerList = new LinkedList<>();

        while (scanner.hasNext()) {
            String name = scanner.next();
            String type = scanner.next();
            String amount = scanner.next();

            transactionList.add(new String[]{name, type, amount});

            boolean exists = false;
            for (String[] cust : customerList) {
                if (cust[0].equals(name)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                customerList.add(new String[]{name, "0"});
            }
        }
        scanner.close();

        Queue<String[]> transactionQueue = new LinkedList<>();
        for (String[] tx : transactionList) {
            transactionQueue.add(tx);
        }

        Stack<String[]> failedStack = new Stack<>();

        while (!transactionQueue.isEmpty()) {
            String[] currentTx = transactionQueue.poll();
            String name = currentTx[0];
            String type = currentTx[1];
            int amount = Integer.parseInt(currentTx[2]);

            String[] currentCustomer = null;
            for (String[] cust : customerList) {
                if (cust[0].equals(name)) {
                    currentCustomer = cust;
                    break;
                }
            }

            if (currentCustomer != null) {
                int currentBalance = Integer.parseInt(currentCustomer[1]);

                if (type.equals("DEPOSIT")) {
                    currentBalance += amount;
                    currentCustomer[1] = String.valueOf(currentBalance);
                } else if (type.equals("WITHDRAW")) {
                    if (amount > currentBalance) {
                        failedStack.push(currentTx);
                    } else {
                        currentBalance -= amount;
                        currentCustomer[1] = String.valueOf(currentBalance);
                    }
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] cust : customerList) {
            System.out.println(cust[0] + " : " + cust[1]);
        }

        System.out.println(); 

        System.out.println("=== Failed Transactions ===");
        while (!failedStack.isEmpty()) {
            String[] failedTx = failedStack.pop(); // Ambil transaksi dari atas stack
            System.out.println(failedTx[0] + " " + failedTx[1] + " " + failedTx[2]);
        }
    }
}

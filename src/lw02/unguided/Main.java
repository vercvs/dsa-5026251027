package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(
                Main.class.getResourceAsStream("borrowing.txt")
        );

        LinkedList<String[]> requests = new LinkedList<>();
        LinkedList<String[]> books = new LinkedList<>();
        LinkedList<String[]> members = new LinkedList<>();
        LinkedList<String[]> success = new LinkedList<>();
        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        while (scanner.hasNext()) {
            String[] request = new String[2];
            request[0] = scanner.next();
            request[1] = scanner.next();
            requests.add(request);

            boolean exists = false;
            for (String[] m : members) {
                if (m[0].equals(request[0])) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                members.add(new String[]{request[0], "0"});
            }
        }

        scanner.close();
        queue.addAll(requests);

        books.add(new String[]{"Kalkulus", "2"});
        books.add(new String[]{"Fisika", "1"});
        books.add(new String[]{"Statistika", "2"});

        int max_borrow = 2; 

        while (!queue.isEmpty()) {
            String[] request = queue.poll();
            String name = request[0];
            String bookTitle = request[1];

            String[] book = null;
            for (String[] b : books) {
                if (b[0].equals(bookTitle)) {
                    book = b;
                    break;
                }
            }

            String[] member = null;
            for (String[] m : members) {
                if (m[0].equals(name)) {
                    member = m;
                    break;
                }
            }

            if (book != null && member != null) {
                int stock = Integer.parseInt(book[1]);
                int borrowed = Integer.parseInt(member[1]);

                if (stock > 0 && borrowed < max_borrow) {
                    stock -= 1;
                    borrowed += 1;

                    book[1] = String.valueOf(stock);
                    member[1] = String.valueOf(borrowed);

                    success.add(request);
                } else {
                    failed.push(request); 
                }
            }
        }

        System.out.println("=== Successfully Processed Requests ===");
        for (String[] req : success) {
            System.out.println(req[0] + " " + req[1]);
        }
        System.out.println();
        System.out.println("=== Remaining Book Stocks ===");
        for (String[] b : books) {
            System.out.println(b[0] + " : " + b[1]);
        }
        System.out.println();
        System.out.println("=== Failed Requests ===");
        while (!failed.isEmpty()) {
            String[] req = failed.pop();
            System.out.println(req[0] + " " + req[1]);
               
        }
    }
}
package prelab;

import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
       
        List<String> playlist = new ArrayList<>();
        Set<String> participants = new LinkedHashSet<>();
        Map<String, Integer> inventory = new LinkedHashMap<>();

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(" ", 2);
            String command = parts[0];
            String detail = parts[1];  

            if (command.equals("ADD")) {
                playlist.add(detail);
            } else if (command.equals("INSERT")) {
                String[] parts2 = detail.split(" ", 2);
                int index = Integer.parseInt(parts2[0]);
                String song = parts2[1];
                playlist.add(index,song);
            } else if (command.equals("REMOVE")) {
                playlist.remove(detail);
            }
        }

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
        System.out.println();
        
        sc = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        int duplicateCounts = 0;

        while(sc.hasNextLine()) {
            String name = sc.nextLine();
            participants.add(name);

            if (!participants.add(name))
                duplicateCounts++;
        }

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        int list = 1;
        for (String participant : participants) {
            System.out.println(list + ". " + participant);
            list++;
        }
        System.out.println("Duplicate registrations: " + (duplicateCounts-participants.size()));
        System.out.println();
        
        sc = new Scanner(Main.class.getResourceAsStream("inventory.txt"));

        int failedSales = 0;

        while (sc.hasNext()) {
            String act = sc.next();
            String product = sc.next();
            int quantity = sc.nextInt();
            if (act.equals("ADD")) {
                int currentStock;
                if (inventory.containsKey(product)) {
                    currentStock = inventory.get(product);
                } else {
                    currentStock = 0;
                }
                inventory.put(product, currentStock + quantity);

            } else if (act.equals("SELL")) {
                if (!inventory.containsKey(product)) {
                    failedSales++;
                } else {
                    int currentStock = inventory.get(product);
                    if (currentStock < quantity) {
                        failedSales++;
                    } else {
                        inventory.put(product, currentStock - quantity);
                    }
                }
            }
        }

        System.out.println("===== Problem 3 =====");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("Failed sales: " + failedSales);
        sc.close();
    }
}
package lw01.unguided;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
       Scanner scanner = new Scanner(
                Main.class.getResourceAsStream("rentals.txt")
        );

        int totalRecords = scanner.nextInt();
        Rental[] rentals = new Rental[totalRecords];

        for (int i = 0; i < totalRecords; i++) {
            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();
            int units = scanner.nextInt();

            Rental rental = null;
            if (type.equals("LAPTOP")) {
                rental = new LaptopRental(id, days);
            } else if (type.equals("PROJECTOR")) {
                rental = new ProjectorRental(id, days);
            }

            if (rental != null) {
              
                final Rental currentRental = rental;
                final int calculatedTotal = rental.calculateCharge(units);

                rentals[i] = new Rental(id, days) {
                    @Override
                    public int calculateCharge() {
                        return calculatedTotal;
                    }

                    @Override
                    public String label() {
                        return currentRental.label();
                    }
                };
            }
        }

        scanner.close();
        for (Rental r : rentals) {
            System.out.println(r.summary());
        }
    }
}


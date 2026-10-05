import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));

        Map<String, Integer> enrollmentMap = new LinkedHashMap<>();
        List<String> checkList = new ArrayList<>();

        int rejectedOperations = 0;

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(" ", 2);
            String act = parts[0];
            String detail = parts[1]; 

            if(act.equals("REGISTER")) {
                String[] parts2 = detail.split(" ", 2);
                String course = parts2[0];
                int students = Integer.parseInt(parts2[1]);
                if (students <= 0) {
                    rejectedOperations++;
                } else {
                    int currentStudents = 0;

                    if (enrollmentMap.containsKey(course)) {
                        currentStudents = enrollmentMap.get(course);
                    } else {
                        currentStudents = 0;
                    }
                    enrollmentMap.put(course, currentStudents + students);
                }

            } else if(act.equals("WITHDRAW")) {
                String[] parts2 = detail.split(" ", 2);
                String course = parts2[0];
                int students = Integer.parseInt(parts2[1]);
                if (students <= 0) {
                    rejectedOperations++;
                } else if (!enrollmentMap.containsKey(course)) {
                    rejectedOperations++;
                } else {
                    int currentStudents = enrollmentMap.get(course);

                    if (currentStudents < students) {
                        rejectedOperations++;
                    } else {
                        enrollmentMap.put(course, currentStudents - students);
                    }
                }
            }

             else if(act.equals("CHECK")) {
                String course = detail;

                if (enrollmentMap.containsKey(course)) {
                    checkList.add(course + ": " + enrollmentMap.get(course) + " students");
                } else {
                    checkList.add(course + ": Not found");
                }
            }
        }

        System.out.println("===== Enrollment Checks =====");

        for (String result : checkList) {
            System.out.println(result);
        }

        System.out.println();
        System.out.println("===== Final Enrollment =====");

        for (Map.Entry<String, Integer> entry : enrollmentMap.entrySet()) {
            System.out.println(entry.getKey() + ": "+ entry.getValue() + " students");
        }

        System.out.println();
        System.out.println("Rejected operations: " + rejectedOperations);

        sc.close();
    }
}

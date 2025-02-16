import java.util.Scanner;

public class GradeCalc {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();

        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();

        int[][] grades = new int[numStudents][numSubjects];

        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter grades for student " + (i + 1) + ":");
            for (int j = 0; j < numSubjects; j++) {
                System.out.print("Grade for subject " + (j + 1) + ": ");
                grades[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i < numStudents; i++) {
            int total = 0;
            for (int j = 0; j < numSubjects; j++) {
                total += grades[i][j];
            }
            double average = (double) total / numSubjects;
            System.out.println("Average grade for student " + (i + 1) + ": " + average);
        }

        scanner.close();
    }
}

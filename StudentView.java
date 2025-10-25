import java.util.List;
import java.util.Scanner;

public class StudentView {
    private StudentController controller;
    private Scanner sc = new Scanner(System.in);

    public StudentView(StudentController controller) {
        this.controller = controller;
    }

    public void menu() {
        int choice;
        do {
            System.out.println("\n=== Student Management Menu ===");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {
                case 1: addStudent(); break;
                case 2: viewStudents(); break;
                case 3: updateStudent(); break;
                case 4: deleteStudent(); break;
                case 5: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while(choice != 5);
    }

    private void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        System.out.print("Enter Marks: ");
        double marks = sc.nextDouble();

        Student s = new Student(id, name, dept, marks);
        if(controller.addStudent(s)) System.out.println("Student added successfully!");
        else System.out.println("Failed to add student.");
    }

    private void viewStudents() {
        List<Student> list = controller.getAllStudents();
        System.out.println("ID\tName\tDepartment\tMarks");
        System.out.println("--------------------------------------");
        for(Student s : list) {
            System.out.println(s.getStudentID() + "\t" + s.getName() + "\t" + s.getDepartment() + "\t" + s.getMarks());
        }
    }

    private void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter new Name: ");
        String name = sc.nextLine();
        System.out.print("Enter new Department: ");
        String dept = sc.nextLine();
        System.out.print("Enter new Marks: ");
        double marks = sc.nextDouble();

        Student s = new Student(id, name, dept, marks);
        if(controller.updateStudent(s)) System.out.println("Student updated successfully!");
        else System.out.println("Failed to update student.");
    }

    private void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();
        if(controller.deleteStudent(id)) System.out.println("Student deleted successfully!");
        else System.out.println("Failed to delete student.");
    }
}

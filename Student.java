package pkg1;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Student {
    String studentName;
    String regNum;
    Book[] borrowedBooks=new Book[3];
    int booksCount;

    public Student() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter student name:");
        this.studentName = input.nextLine();
        System.out.println("Enter registration number:");
        this.regNum = input.nextLine();
        saveDetailsToFile();
    }

    private void saveDetailsToFile() {
       try{
            FileWriter writer = new FileWriter("student_details.txt", true);
            writer.write("Student Name: " + studentName + ", Registration Number: " + regNum + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


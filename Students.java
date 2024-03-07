package pkg1;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import pkg1.Student;

public class Students {
    Scanner input = new Scanner(System.in);
    Student theStudents[] = new Student[50];
    public static int count = 0;

    public void addStudent(Student s) {
        for (int i = 0; i < count; i++) {
            if (s.regNum.equalsIgnoreCase(theStudents[i].regNum)) {
                System.out.println("Student of Reg Num " + s.regNum + " is Already Registered.");
                return;
            }
        }
        if (count <= 50) {
            theStudents[count] = s;
            count++;
            
            saveDetailsToFile(s);
        }
    }
private void saveDetailsToFile(Student s) {
        try {
            FileWriter writer = new FileWriter("students_details.txt", true);
            writer.write("Student Name: " + s.studentName + ", Registration Number: " + s.regNum + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAllStudents() {
        System.out.println("Student Name\t\tReg Number");
        for (int i = 0; i < count; i++) {
            System.out.println(theStudents[i].studentName + "\t\t" + theStudents[i].regNum);
        }
    }

    public int isStudent() {
        System.out.println("Enter Reg Number:");
        String regNum = input.nextLine();
        for (int i = 0; i < count; i++) {
            if (theStudents[i].regNum.equalsIgnoreCase(regNum)) {
                return i;
            }
        }
        System.out.println("Student is not Registered.");
        System.out.println("Get Registered First.");
        return -1;
    }

    public void checkOutBook(Books Book) {
        int studentIndex = this.isStudent();
        if (studentIndex != -1) {
            System.out.println("checking out");
            Book.showAllBooks();
            Book b = Book.checkOutBook();
            System.out.println("checking out");
            if (b != null) {
                if (theStudents[studentIndex].booksCount <= 3) {
                    System.out.println("adding book");
                    theStudents[studentIndex].borrowedBooks[theStudents[studentIndex].booksCount] = b;
                    theStudents[studentIndex].booksCount++;
                    return;
                } else {
                    System.out.println("Student Can not Borrow more than 3 Books.");
                    return;
                }
            }
            System.out.println("Book is not Available.");
        }
    }

    public void checkInBook(Books Book) {
        int studentIndex = this.isStudent();
        if (studentIndex != -1) {
            System.out.println("S.No\t\t\tBook Name\t\t\tAuthor Name");
            Student s = theStudents[studentIndex];
            for (int i = 0; i < s.booksCount; i++) {
                System.out.println(s.borrowedBooks[i].sNo + "\t\t\t" + s.borrowedBooks[i].bookName + "\t\t\t"
                        + s.borrowedBooks[i].authorName);
            }
            System.out.println("Enter Serial Number of Book to be Checked In:");
            int sNo = input.nextInt();
            for (int i = 0; i < s.booksCount; i++) {
                if (sNo == s.borrowedBooks[i].sNo) {
                    Book.checkInBook(s.borrowedBooks[i]);
                    s.borrowedBooks[i] = null;
                    return;
                }
            }
            System.out.println("Book of Serial No " + sNo + "not Found");
        }
    }

}

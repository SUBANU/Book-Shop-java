
//Bookshop.JAVA
package College;
import java.util.Scanner;
 class Bookshop {
public static void main(String[] args)
{
Scanner input = new Scanner(System.in);
System.out.println("Hello! Welcome to our Book Shop");
System.out.println("Enter your choice:");
books ob = new books();
students obStudent = new students();
int choice;
int searchChoice;
do {
ob.dispMenu();
choice=input.nextInt();
switch(choice){
case 1:
book b = new book();
ob.addBook(b);
break;
case 2:
ob.searchBySno();
break;
case 3:
ob.showAllBooks();
break;
case 4:
student s = new student();
obStudent.addStudent(s);
break;
case 5:
obStudent.showAllStudents();
break;
case 6:
obStudent.checkOutBook(ob);
break;
case 7:
obStudent.checkInBook(ob);
break;
}
}
while (choice != 0);
}
}


//(BOOK.JAVA)

 class book {
Scanner input = new Scanner(System.in);
public int sNo;
    public String bookName;
    public String authorName;
    public int bookQty;
    public int bookQtyCopy;
    public book()
    {
        System.out.println("Enter Sno:");
        this.sNo = input.nextInt();
        System.out.println("Enter Bk Name:");
        this.bookName = input.next();
        System.out.println("Enter No.of Books:");
        this.bookQty = input.nextInt();
        bookQtyCopy = this.bookQty;
    }
}
//(BOOKS.JAVA)

 class books {
    book[] theBooks=new book[50];
    public static int count;
    Scanner input = new Scanner(System.in);
    public void dispMenu() {
    System.out.println("0->Exit Application");
        System.out.println("1->Add new Book");
        System.out.println("2->Search a Book");
        System.out.println("3->Show All Books");
        System.out.println("4->Register Student");
        System.out.println("5->Show All Registered Students");
        System.out.println("6->Check Out Book");
        System.out.println("7->Check In Book");
    }
    public void addBook(book b) {
        for (int i=0;i<count;i++){
            if (this.compareBookObjects(b,this.theBooks[i])== 0)
                return;
        }
        if (count < 50){
            theBooks[count] = b;
            count++;
        }
        else{
            System.out.println("No Space!!");
        }
    }
    public int compareBookObjects(book b1, book b2) {
        if (b1.bookName.equalsIgnoreCase(b2.bookName)) {
            System.out.println("Book of this Name Already Exists.");
            return 0;
        }
        if (b1.sNo==b2.sNo) {
            System.out.println("Book of this Serial No Already Exists.");
            return 0;
        }
        return 1;
    }
    public void searchBySno()
    {
        int sNo;
        System.out.println("Enter Serial No of Book:");
        sNo = input.nextInt();
        int flag = 0;
        System.out.println("S.No\t\tName\t\tAvailable_Qty\t\tTotal Qty");
        for (int i = 0; i < count; i++) {
            if (sNo == theBooks[i].sNo) {
                System.out.println(theBooks[i].sNo+"\t\t"+theBooks[i].bookName+"\t\t"+theBooks[i].bookQtyCopy+"\t\t"+theBooks[i].bookQty);
                flag++;
                return;
            }
        }
        if (flag == 0)
            System.out.println("No Book for this serialno "+sNo+" Found.");
    }
    public void showAllBooks()
    {
        System.out.println("Available Books :");
        System.out.println("S.No\t\tName\t\tAuthor\t\tAvailable Qty\t\tTotal Qty");
        for (int i=0;i<count;i++){
            System.out.println(theBooks[i].sNo+"\t\t"+theBooks[i].bookName+"\t\t"+theBooks[i].bookQtyCopy+"\t\t"+theBooks[i].bookQty);
        }
    }
    public int isAvailable(int sNo)
    {
        for (int i = 0; i < count; i++) {
            if (sNo == theBooks[i].sNo) {
                if (theBooks[i].bookQtyCopy > 0) {
 
                    System.out.println(
                        "Book is Available.");
                    return i;
                }
                System.out.println("Book is Unavailable");
                return -1;
            }
        }
 
        System.out.println("No Book of Serial Number "
                           + " Available in Library.");
        return -1;
    }
    public book checkOutBook()
    {
 
        System.out.println(
            "Enter Serial No of Book to be Checked Out.");
        int sNo = input.nextInt();
 
        int bookIndex = isAvailable(sNo);
 
        if (bookIndex != -1) {
            theBooks[bookIndex].bookQtyCopy--;
            return theBooks[bookIndex];
        }
        return null;
    }
    public void checkInBook(book b)
    {
        for (int i = 0; i < count; i++) {
            if (b.equals(theBooks[i])) {
                theBooks[i].bookQtyCopy++;
                return;
            }
        }
    }
}
//STUDENT.JAVA
class student {
String studentName;
String regNum;
book borrowedBooks[] = new book[3];
public int booksCount = 0;
Scanner input = new Scanner(System.in);
public student()
{
System.out.println("Enter Student Name:");
this.studentName = input.nextLine();
System.out.println("Enter Registration Number:");
this.regNum = input.nextLine();
}
}
//STUDENTS.JAVA
 class students {
Scanner input = new Scanner(System.in);
student theStudents[] = new student[50];
public static int count = 0;
public void addStudent(student s)
{
for (int i = 0; i < count; i++) {
if (s.regNum.equalsIgnoreCase(theStudents[i].regNum)) {
System.out.println("Student of Reg Num " + s.regNum
+ " is Already Registered.");
return;
}
}
if (count <= 50) {
theStudents[count] = s;
count++;
}
}
public void showAllStudents()
{
System.out.println("Student Name\t\tReg Number");
for (int i = 0; i < count; i++) {
System.out.println(theStudents[i].studentName+"\t"+theStudents[i].regNum);
}
}
public int isStudent()
{
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
public void checkOutBook(books book)
{
int studentIndex = this.isStudent();
if (studentIndex != -1) {
System.out.println("checking out");
book.showAllBooks();
book b = book.checkOutBook();
System.out.println("checking out");
if (b != null) {

if (theStudents[studentIndex].booksCount<= 3) {
System.out.println("adding book");
theStudents[studentIndex].borrowedBooks
[theStudents[studentIndex].booksCount]= b;
theStudents[studentIndex].booksCount++;
return;
}
else {
System.out.println("Student Can not Borrow more than 3 Books.");
return;
}
}
System.out.println("Book is not Available.");
}
}
public void checkInBook(books book)
{
int studentIndex = this.isStudent();
if (studentIndex != -1) {
System.out.println("S.No\t\t\tBook Name\t\t\tAuthor Name");
student s = theStudents[studentIndex];
for (int i = 0; i < s.booksCount; i++) {
System.out.println(
s.borrowedBooks[i].sNo + "\t\t\t"
+ s.borrowedBooks[i].bookName + "\t\t\t");
}
System.out.println("Enter Serial Number of Book to be Checked In:");
int sNo = input.nextInt();
for (int i = 0; i < s.booksCount; i++) {
if (sNo == s.borrowedBooks[i].sNo) {
book.checkInBook(s.borrowedBooks[i]);
s.borrowedBooks[i] = null;
return;
}
}
System.out.println("Book of Serial No "+sNo+"not Found");
}
}
}


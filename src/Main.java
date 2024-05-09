import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Student {
    private String id;
    private String name;
    private Integer day;
    private Integer month;
    private Integer year;
    private String classroom;
    private String subject;
    private String createDate;

    public Student(){}
    public Student(String id, String name, Integer day, Integer month, Integer year, String classroom, String subject, String createDate) {
        this.id = id;
        this.name = name;
        this.day = day;
        this.month = month;
        this.year = year;
        this.classroom = classroom;
        this.subject = subject;
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;

    }

    public Integer getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public String getClassroom() {
        return classroom;
    }
    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", classroom='" + classroom + '\'' +
                ", subject='" + subject + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}


class Menu{
    public static void banner(){

        String banner = """
                    \t\t\t\t\t╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
                    \t\t\t\t\t║                                                                                                                            ║
                    \t\t\t\t\t║                                                                                                                            ║
                    \t\t\t\t\t║\t\t\t       ██╗ █████╗ ██╗   ██╗ █████╗     ██████╗ ██████╗  ██████╗      ██╗███████╗ ██████╗████████╗                ║
                    \t\t\t\t\t║\t\t\t       ██║██╔══██╗██║   ██║██╔══██╗    ██╔══██╗██╔══██╗██╔═══██╗     ██║██╔════╝██╔════╝╚══██╔══╝                ║
                    \t\t\t\t\t║\t\t\t       ██║███████║██║   ██║███████║    ██████╔╝██████╔╝██║   ██║     ██║█████╗  ██║        ██║                   ║
                    \t\t\t\t\t║\t\t\t  ██   ██║██╔══██║╚██╗ ██╔╝██╔══██║    ██╔═══╝ ██╔══██╗██║   ██║██   ██║██╔══╝  ██║        ██║                   ║
                    \t\t\t\t\t║\t\t\t  ╚█████╔╝██║  ██║ ╚████╔╝ ██║  ██║    ██║     ██║  ██║╚██████╔╝╚█████╔╝███████╗╚██████╗   ██║                   ║
                    \t\t\t\t\t║\t\t\t   ╚════╝ ╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═╝    ╚═╝     ╚═╝  ╚═╝ ╚═════╝  ╚════╝ ╚══════╝ ╚═════╝   ╚═╝                   ║
                    \t\t\t\t\t║                                                                                                                            ║
                    \t\t\t\t\t║                                                                                                                            ║
                    \t\t\t\t\t╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝
                    """;
    }

    public static void menu(){
        System.out.println("""
                    ╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
                    ║                                               ★彡[APPLICATION MENU]彡★                                              ║
                    ║=====================================================================================================================║
                    ║                                                                                                                     ║
                    ║   \t\t1. ADD NEW STUDENT        \t\t2. LIST ALL STUDENTS                  \t3. COMMIT DATA TO FILE            ║
                    ║   \t\t4. SEARCH STUDENT         \t\t5. UPDATE STUDENT' INFO BY ID    \t\t6. DELETE STUDENT'S DATA          ║
                    ║    \t\t7. GENERATE DATA TO FILE \t\t8. DELETE/CLEAR ALL DATA FROM DATA STORE                                  ║
                    ║                                           0, 99. EXIT                                                               ║
                    ║                                                                                            Copyright-CSTAD-2024     ║ 
                    ╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝""");


    }

}



class StudentController{
    Scanner sc = new Scanner(System.in);
    private static List<Student> students = new ArrayList<>();
    private boolean changesCommitted;
    private boolean delete;

    public List<Student> getStudents() {
        return students;
    }

    int randomNumber = generateRandomNumber(1000, 9999);
    String id = randomNumber + "CSTAD";

    // Method to generate a random number in a specified range
    private int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    String serializeStudent(Student student) {
        return String.format("%s,%s,%d,%d,%d,%s,%s,%s", student.getId(), student.getName(), student.getDay(), student.getMonth(),
                student.getYear(), student.getClassroom(), student.getSubject(), student.getCreateDate());
    }

    public void addNewStudent(){
        Scanner sc = new Scanner(System.in);

        System.out.println("🟢 INSERT STUDENT'S INFO");
        System.out.print("➡️ Insert student's name: ");
        String name = sc.nextLine();
        while (!isValidName(name)) {
//            System.out.print(RED);
            System.out.println("⚠️ Name cannot contain numbers. Please enter a valid name.");
//            System.out.print(RESET);
            System.out.print("➡️ Insert student's name: ");
            name = sc.nextLine();
        }

        System.out.println("🟢 STUDENT DATE OF BIRTH");

        Integer year = validaYear(sc, "➡️ Year (number): ");
        Integer month = validMonthDay(sc, "➡️ Month (number): ", 1, 12);
        Integer day = validMonthDay(sc, "➡️ Day (number): ", 1, 31);

        sc.nextLine(); // Consume newline

        System.out.println("🔵 YOU CAN INSERT MULTI CLASSES BY SPLITTING [,] SYMBOL (C1, C2).");
        System.out.print("➡️ Student's class: ");
        String classNamesInput = sc.nextLine();
        String[] classNames = classNamesInput.split(",");
        List<String> classes = new ArrayList<>();
        for (String className : classNames) {
            classes.add(className.trim());
        }

        System.out.println("🔵 YOU CAN INSERT MULTI SUBJECTS BY SPLITTING [,] SYMBOL (S1, S2).");
        System.out.print("➡️ Subject's studied: ");
        String subjectsInput = sc.nextLine();
        String[] subjects = subjectsInput.split(",");
        List<String> subjectsList = new ArrayList<>();
        for (String subject : subjects) {
            subjectsList.add(subject.trim());
        }

        int randomNumber;
        String id;
        do{
            randomNumber = generateRandomNumber(1000,9999);
            id = randomNumber + "CSTAD";
        }while (isDuplicateID(id));

        LocalDate date = LocalDate.now();
        String createDate = date.toString();

        Student newStudent = new Student();
        newStudent.setId(id);
        newStudent.setName(name);
        newStudent.setYear(year);
        newStudent.setMonth(month);
        newStudent.setDay(day);
        newStudent.setSubject(subjectsInput);
        newStudent.setClassroom(classNamesInput);
        newStudent.setCreateDate(createDate);

        students.add(newStudent);
        String serializedProduct = serializeStudent(newStudent);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transaction/transaction.dat", true))) {
            writer.write(serializedProduct + "\n");
            System.out.println("✅ STUDENT HAS BEEN ADD SUCCESSFULLY");
        } catch (IOException e) {
            System.err.println("⚠️ Error writing to transaction file: " + e.getMessage());
        }
        System.out.println("⚠️ TO STORE DATA PERMANENTLY, PLEASE COMMIT IT (START OPTIONS 3).");
    }

    private boolean isDuplicateID(String id)
    {
        for(Student student : students)
        {
            if(student.getId().equals(id))
            {
                return true;
            }
        }
        return false;
    }

    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z]+");
    }

    private Integer validaYear(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                return sc.nextInt();
            } else {
                System.out.println("⚠️ Invalid input! Please enter a valid number.");
                sc.next();
            }
        }
    }

    private Integer validMonthDay(Scanner sc, String message, int min, int max) {
        while (true) {
            Integer input = validaYear(sc, message);
            if (input >= min && input <= max) {
                return input;
            } else {

                System.out.println("⚠️ Invalid input! Input must be between " + min + " and " + max + ".");
            }
        }
    }

    public boolean hasUncommittedTransactions() {
        File file = new File("transaction/transaction.dat");
        return file.exists() && file.length() > 0;
    }

    public void commitChanges() {
        System.out.print("❔ Are you sure you want to commit? [Y/N]: ");
        String answer = sc.nextLine();
        if(answer.equalsIgnoreCase("y")){

            if (delete){
                try (BufferedReader reader = new BufferedReader(new FileReader("transaction/transaction.dat"));
                     BufferedWriter writer = new BufferedWriter(new FileWriter("data/student.dat"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine();
                    }
                    System.out.println("✅ Changes committed successfully!");
                    this.changesCommitted = true;
                } catch (IOException e) {
                    System.err.println("Error committing changes: " + e.getMessage());
                }
                delete=false;
                return;
            }
            if (students.isEmpty()){
                System.out.println("⚠️ No data, can't commit...!");
            }
            else{
                if (!hasUncommittedTransactions()) {
                    System.out.println("No uncommitted transactions to commit.");
                    return;
                }
                try (BufferedReader reader = new BufferedReader(new FileReader("transaction/transaction.dat"));
                     BufferedWriter writer = new BufferedWriter(new FileWriter("data/student.dat"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine();
                    }
                    System.out.println("✅ Changes committed successfully!");
                    this.changesCommitted = true;
                } catch (IOException e) {
                    System.err.println("Error committing changes: " + e.getMessage());
                }
            }
        }
        else if(answer.equalsIgnoreCase("n")){
            System.out.println("❗You didn't commit anything...!");
        }
        else System.out.println("Invalid input...!");
    }
}


class StudentView {
    static Integer currentPage = 1;
    static Integer pageSize = 1;
    static Integer rowPerPage = 5;

    public static void listAllStudent(List<Student> students) {
        pageSize = (int) Math.ceil((double) students.size() / rowPerPage);
        int startIndex = (currentPage * rowPerPage) - rowPerPage; // 5
        int endIndex = Math.min(startIndex + rowPerPage, students.size());

        System.out.println("Total Page : " + pageSize);
        System.out.println("[*] STUDENT'S DATA");

        Table table = new Table(6, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.CENTER);
        table.setColumnWidth(0, 30, 30);
        table.setColumnWidth(1, 30, 30);
        table.setColumnWidth(2, 30, 30);
        table.setColumnWidth(3, 30, 30);
        table.setColumnWidth(4, 30, 30);
        table.setColumnWidth(5, 30, 30);
        table.addCell("ID", cellStyle);
        table.addCell("STUDENT'S NAME", cellStyle);
        table.addCell("STUDENT'S DATE OF BIRTH", cellStyle);
        table.addCell("STUDENT CLASS", cellStyle);
        table.addCell("STUDENT'S SUBJECT", cellStyle);
        table.addCell("CREATE/ UPDATE AT", cellStyle);

        try {
            for (int i = startIndex; i < endIndex; i++) {
                table.addCell(students.get(i).getId(), cellStyle);
                table.addCell(students.get(i).getName(), cellStyle);
                table.addCell(students.get(i).getYear().toString() + "-" +
                        students.get(i).getMonth().toString() + "-" +
                        students.get(i).getDay().toString(), cellStyle);
                table.addCell(students.get(i).getClassroom(), cellStyle);
                table.addCell(students.get(i).getSubject(), cellStyle);
                table.addCell(students.get(i).getCreateDate(), cellStyle);
            }

            System.out.println(table.render());
            System.out.println("+" + "~".repeat(150) + "+");
            System.out.println("[*] Page number: " + currentPage + "\t\t" + " [*] Actual record: " + (endIndex - startIndex) + "\t\t" + " All Record: " + students.size());
            System.out.println("[+] Previous (P/p) \t\t [+] Next (N/n) \t\t\t [+] Back(B/b)");
            System.out.println("+" + "~".repeat(150) + "+");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("No records to display.");
        }
    }

}


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Student> students = new ArrayList<>();
        StudentController studentController = new StudentController();
        boolean exit = false;
//        Menu.banner();

        loadStudentData(studentController);

        while (!exit) {
            Menu.menu();
            System.out.print("🖲️Insert Option: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("=".repeat(50));
                    studentController.addNewStudent();
                    break;
                case 2:
                    System.out.println("=".repeat(50));
                    StudentView.listAllStudent(studentController.getStudents());
                    break;
                case 3:
                    System.out.println("3. COMMIT DATA TO FILE");
                    studentController.commitChanges();
                    break;
                case 4:
                    System.out.println("4. SEARCH STUDENT");
                    break;
                case 5:
                    System.out.println("5. UPDATE STUDENT'S INFO BY ID");
                    break;
                case 6:
                    System.out.println("6 DELETE STUDENT'S INFO BY ID");
                    break;
                case 7:
                    System.out.println("7. GENERATE DATA TO FILE");
                    break;
                case 8:
                    System.out.println("8. CLEAR ALL DATA FROM DATA STORE");
                    break;
                case 0,9:
                    System.out.println("Exiting...");
                    exit = true;
                    break;
                default:
                    System.out.println("⚠️Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void loadStudentData(StudentController studentController) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/student.dat"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Student student = new Student(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
                        Integer.parseInt(parts[4]), parts[5], parts[6], parts[7]);
                studentController.getStudents().add(student);
            }
        } catch (IOException e) {
            System.err.println("Error loading student data: " + e.getMessage());
        }
    }
}
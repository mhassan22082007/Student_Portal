import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// ================= 1. SUPERCLASS (INHERITANCE) =================
abstract class Person 
{
    protected String username;
    protected String password;
    protected String cnic;
    protected String phoneNo;

    public Person(String username, String password, String cnic, String phoneNo) 
    {
        this.username = username;
        this.password = password;
        this.cnic = cnic;
        this.phoneNo = phoneNo;
    }

    // Shared Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getCnic() { return cnic; }
    public void setCnic(String cnic) { this.cnic = cnic; }
    
    public String getPhoneNo() { return phoneNo; }
    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }
}

// ================= 2. SUBCLASSES =================
class Student extends Person 
{
    private String rollNo;
    private double cgpa;
    private int monthlyAttendance;
    private ArrayList<Integer> monthlyTestScores;

    public Student(String rollNo, String username, String password, String cnic, String phoneNo, double cgpa, int monthlyAttendance, ArrayList<Integer> monthlyTestScores) 
    {
        super(username, password, cnic, phoneNo);
        this.rollNo = rollNo;
        this.cgpa = cgpa;
        this.monthlyAttendance = monthlyAttendance;
        this.monthlyTestScores = monthlyTestScores;
    }

    public String getRollNo() { return rollNo; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }
    
    public double getCgpa() { return cgpa; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }
    
    public int getMonthlyAttendance() { return monthlyAttendance; }
    public void setMonthlyAttendance(int monthlyAttendance) { this.monthlyAttendance = monthlyAttendance; }
    
    public ArrayList<Integer> getMonthlyTestScores() { return monthlyTestScores; }
    public void setMonthlyTestScores(ArrayList<Integer> scores) { this.monthlyTestScores = scores; }

    public String getScoresAsString() 
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < monthlyTestScores.size(); i++) 
        {
            sb.append(monthlyTestScores.get(i));
            if (i < monthlyTestScores.size() - 1) sb.append("|");
        }
        return sb.toString();
    }

    public void displayPortalData(int rank, int totalStudents) 
    {
        System.out.println("\n==========================================");
        System.out.println("             STUDENT PORTAL               ");
        System.out.println("==========================================");
        System.out.printf(" Roll Number       : %s\n", rollNo);
        System.out.printf(" Username          : %s\n", username);
        System.out.printf(" CNIC              : %s\n", cnic);
        System.out.printf(" Phone No          : %s\n", phoneNo);
        System.out.printf(" Cumulative GPA    : %.2f\n", cgpa);
        System.out.printf(" Class Standing    : Rank %d out of %d students\n", rank, totalStudents);
        System.out.printf(" Monthly Attendance: %d%%\n", monthlyAttendance);
        System.out.print(" Recent Test Scores: ");
        for (int score : monthlyTestScores) 
        {
            System.out.print("[" + score + "/100] ");
        }
        System.out.println("\n==========================================");
    }
}

class Teacher extends Person 
{
    private String teacherId;
    private int monthlyAttendance;
    private double salary;

    public Teacher(String teacherId, String username, String password, String cnic, String phoneNo, int monthlyAttendance, double salary) 
    {
        super(username, password, cnic, phoneNo);
        this.teacherId = teacherId;
        this.monthlyAttendance = monthlyAttendance;
        this.salary = salary;
    }

    public String getTeacherId() { return teacherId; }
    public void setTeacherId(String teacherId) { this.teacherId = teacherId; }
    
    public int getMonthlyAttendance() { return monthlyAttendance; }
    public void setMonthlyAttendance(int monthlyAttendance) { this.monthlyAttendance = monthlyAttendance; }
    
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public void displayTeacherData() 
    {
        System.out.println("\n==========================================");
        System.out.println("             TEACHER PORTAL               ");
        System.out.println("==========================================");
        System.out.printf(" Teacher ID        : %s\n", teacherId);
        System.out.printf(" Username          : %s\n", username);
        System.out.printf(" CNIC              : %s\n", cnic);
        System.out.printf(" Phone No          : %s\n", phoneNo);
        System.out.printf(" Monthly Attendance: %d%%\n", monthlyAttendance);
        System.out.printf(" Monthly Salary    : Rs. %.2f\n", salary);
        System.out.println("==========================================");
    }
}

// ================= 3. PORTAL DATABASE ENGINE =================
class PortalSystem 
{
    private ArrayList<Student> studentDatabase = new ArrayList<>();
    private ArrayList<Teacher> teacherDatabase = new ArrayList<>();
    
    private final String STUDENT_FILE = "students_portal_data.txt";
    private final String TEACHER_FILE = "teachers_portal_data.txt";
    private final String MASTER_KEY = "Admin_2026"; // Your personal Master Key

    public PortalSystem() 
    {
        loadStudentsFromFile();
        loadTeachersFromFile();
        if (studentDatabase.isEmpty()) seedInitialStudents();
        if (teacherDatabase.isEmpty()) seedInitialTeachers();
    }

    public boolean verifyMasterKey(String inputKey) 
    {
        return MASTER_KEY.equals(inputKey);
    }

    private void seedInitialStudents() 
    {
        registerStudent("FA26-001", "ali_ahmed", "pass123", "35202-1111111-1", "0300-1111111", 3.82, 92, createScores(85, 90, 88));
        registerStudent("FA26-002", "zara_khan", "pass456", "35202-2222222-2", "0300-2222222", 3.95, 96, createScores(95, 98, 92));
        registerStudent("FA26-003", "usman_99", "pass789", "35202-3333333-3", "0300-3333333", 3.41, 80, createScores(70, 75, 82));
        registerStudent("FA26-004", "fatima_a", "secure77", "35202-4444444-4", "0300-4444444", 3.65, 88, createScores(80, 84, 89));
        registerStudent("FA26-005", "bilal_b", "pass111", "35202-5555555-5", "0300-5555555", 2.95, 75, createScores(60, 65, 70));
        registerStudent("FA26-006", "amna_dar", "pass222", "35202-6666666-6", "0300-6666666", 3.74, 91, createScores(87, 89, 91));
        registerStudent("FA26-007", "hamza_j", "pass333", "35202-7777777-7", "0300-7777777", 3.12, 83, createScores(72, 68, 75));
        registerStudent("FA26-008", "ayesha_e", "pass444", "35202-8888888-8", "0300-8888888", 3.58, 86, createScores(78, 82, 85));
        registerStudent("FA26-009", "zain_malik", "pass555", "35202-9999999-9", "0300-9999999", 2.71, 70, createScores(55, 62, 58));
        registerStudent("FA26-010", "sana_syed", "pass666", "35202-1010101-0", "0300-1010101", 3.89, 95, createScores(90, 93, 96));
    }

    private void seedInitialTeachers() 
    {
        registerTeacher("TCH-01", "prof_tariq", "teach123", "35201-9876543-1", "0321-9876543", 98, 120000.0);
        registerTeacher("TCH-02", "miss_nadia", "teach456", "35201-8765432-2", "0321-8765432", 95, 110000.0);
    }

    private ArrayList<Integer> createScores(int s1, int s2, int s3) 
    {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(s1); list.add(s2); list.add(s3);
        return list;
    }

    // --- Uniqueness Checks ---
    public boolean isUsernameTaken(String username) 
    {
        for (Student s : studentDatabase) 
        {
            if (s.getUsername().equalsIgnoreCase(username)) return true;
        }
        for (Teacher t : teacherDatabase) 
        {
            if (t.getUsername().equalsIgnoreCase(username)) return true;
        }
        return false;
    }

    // --- Student Operations ---
    public boolean registerStudent(String rollNo, String username, String password, String cnic, String phoneNo, double cgpa, int attendance, ArrayList<Integer> scores) 
    {
        for (Student s : studentDatabase) 
        {
            if (s.getRollNo().equalsIgnoreCase(rollNo)) return false;
        }
        if (isUsernameTaken(username)) return false;

        studentDatabase.add(new Student(rollNo, username, password, cnic, phoneNo, cgpa, attendance, scores));
        saveAllStudentsToFile();
        return true;
    }

    public Student loginStudent(String username, String password) 
    {
        for (Student s : studentDatabase) 
        {
            if (s.getUsername().equals(username) && s.getPassword().equals(password)) return s;
        }
        return null;
    }

    public Student findStudentByRoll(String rollNo) 
    {
        for (Student s : studentDatabase) 
        {
            if (s.getRollNo().equalsIgnoreCase(rollNo)) return s;
        }
        return null;
    }

    public boolean removeStudent(String rollNo) 
    {
        Student target = findStudentByRoll(rollNo);
        if (target != null) 
        {
            studentDatabase.remove(target);
            saveAllStudentsToFile();
            return true;
        }
        return false;
    }
        // --- Teacher Operations ---
    public boolean registerTeacher(String teacherId, String username, String password, String cnic, String phoneNo, int attendance, double salary) 
    {
        for (Teacher t : teacherDatabase) 
        {
            if (t.getTeacherId().equalsIgnoreCase(teacherId)) return false;
        }
        if (isUsernameTaken(username)) return false;

        teacherDatabase.add(new Teacher(teacherId, username, password, cnic, phoneNo, attendance, salary));
        saveAllTeachersToFile();
        return true;
    }
    public Teacher loginTeacher(String username, String password) 
    {
        for (Teacher t : teacherDatabase)
        {
            if (t.getUsername().equals(username) && t.getPassword().equals(password)) return t;
        }
        return null;
    }
    public boolean verifyAnyTeacher(String username, String password) 
    {
        return loginTeacher(username, password) != null;
    }
    public Teacher findTeacherById(String teacherId) 
    {
        for (Teacher t : teacherDatabase) 
        {
            if (t.getTeacherId().equalsIgnoreCase(teacherId)) return t;
        }
        return null;
    }
    public boolean removeTeacher(String teacherId) 
    {
        Teacher target = findTeacherById(teacherId);
        if (target != null) 
        {
            teacherDatabase.remove(target);
            saveAllTeachersToFile();
            return true;
        }
        return false;
    }
    // --- View All Lists (For Master Admin) ---
    public ArrayList<Student> getAllStudents() { return studentDatabase; }
    public ArrayList<Teacher> getAllTeachers() { return teacherDatabase; }

    public int getStudentRank(Student target) 
    {
        ArrayList<Student> sortedList = new ArrayList<>(studentDatabase);
        sortedList.sort((s1, s2) -> Double.compare(s2.getCgpa(), s1.getCgpa()));
        for (int i = 0; i < sortedList.size(); i++) 
        {
            if (sortedList.get(i).getRollNo().equals(target.getRollNo())) return i + 1;
        }
        return sortedList.size();
    }
    public int getTotalStudentCount() { return studentDatabase.size(); }
    // ================= 4. PERSISTENT FILE I/O =================
    void saveAllStudentsToFile() 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_FILE, false))) 
        {
            for (Student s : studentDatabase) 
            {
                writer.write(s.getRollNo() + "," + s.getUsername() + "," + s.getPassword() + "," +
                             s.getCnic() + "," + s.getPhoneNo() + "," + s.getCgpa() + "," +
                             s.getMonthlyAttendance() + "," + s.getScoresAsString());
                writer.newLine();
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }
    private void loadStudentsFromFile() 
    {
        File file = new File(STUDENT_FILE);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILE))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] data = line.split(",");
                if (data.length == 8) 
                {
                    String[] scoresRaw = data[7].split("\\|");
                    ArrayList<Integer> scores = new ArrayList<>();
                    for (String sc : scoresRaw) scores.add(Integer.parseInt(sc));
                    studentDatabase.add(new Student(data[0], data[1], data[2], data[3], data[4],
                                        Double.parseDouble(data[5]), Integer.parseInt(data[6]), scores));
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error loading students: " + e.getMessage());
        }
    }
    void saveAllTeachersToFile() 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEACHER_FILE, false))) 
        {
            for (Teacher t : teacherDatabase) 
            {
                writer.write(t.getTeacherId() + "," + t.getUsername() + "," + t.getPassword() + "," +
                             t.getCnic() + "," + t.getPhoneNo() + "," + t.getMonthlyAttendance() + "," +
                             t.getSalary());
                writer.newLine();
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error saving teachers: " + e.getMessage());
        }
    }
    private void loadTeachersFromFile() 
    {
        File file = new File(TEACHER_FILE);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(TEACHER_FILE))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] data = line.split(",");
                if (data.length == 7) 
                {
                    teacherDatabase.add(new Teacher(data[0], data[1], data[2], data[3], data[4],
                                        Integer.parseInt(data[5]), Double.parseDouble(data[6])));
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error loading teachers: " + e.getMessage());
        }
    }
}
// ================= 5. MAIN APPLICATION & MENUS =================
public class Student_Portal_Application 
{
    public static void main(String[] args) 
    {
        PortalSystem portal = new PortalSystem();
        Scanner sc = new Scanner(System.in);
        while (true) 
        {
            System.out.println("\n=== CAMPUS HUB MANAGEMENT SYSTEM ===");
            System.out.println("1. Student Login");
            System.out.println("2. Register New Student");
            System.out.println("3. Teacher Login");
            System.out.println("4. Register New Teacher (Requires Master Key)");
            System.out.println("5. Master Admin Panel (Requires Master Key)");
            System.out.println("6. Shut Down System");
            System.out.print("Select Action: ");
            int choice = getSafeInt(sc);
            sc.nextLine();
            if (choice == 1) 
            {
                // STUDENT LOGIN
                System.out.print("Username: ");
                String user = sc.nextLine();
                System.out.print("Password: ");
                String pass = sc.nextLine();
                Student s = portal.loginStudent(user, pass);
                if (s != null) 
                {
                    handleStudentDashboard(portal, sc, s);
                } 
                else 
                {
                    System.out.println("Access Denied: Invalid student credentials.");
                }
            } 
            else if (choice == 2) 
            {
                // REGISTER STUDENT
                addNewStudentFlow(portal, sc);
            } 
            else if (choice == 3) 
            {
                // TEACHER LOGIN
                System.out.print("Teacher Username: ");
                String user = sc.nextLine();
                System.out.print("Teacher Password: ");
                String pass = sc.nextLine();
                Teacher t = portal.loginTeacher(user, pass);
                if (t != null) 
                {
                    handleTeacherDashboard(portal, sc, t);
                } 
                else 
                {
                    System.out.println("Access Denied: Invalid teacher credentials.");
                }
            } 
            else if (choice == 4) 
            {
                // REGISTER TEACHER (REQUIRES MASTER KEY)
                System.out.print("Enter Master Key to authorize teacher registration: ");
                String key = sc.nextLine();
                if (portal.verifyMasterKey(key)) 
                {
                    addNewTeacherFlow(portal, sc);
                } 
                else 
                {
                    System.out.println("Authorization Failed: Incorrect Master Key!");
                }
            } 
            else if (choice == 5) 
            {
                // MASTER ADMIN PANEL
                System.out.print("Enter Master Key: ");
                String key = sc.nextLine();
                if (portal.verifyMasterKey(key)) 
                {
                    handleMasterAdminPanel(portal, sc);
                } 
                else 
                {
                    System.out.println("Access Denied: Incorrect Master Key!");
                }
            } 
            else if (choice == 6) 
            {
                System.out.println("Shutting down system cleanly...");
                break;
            } 
            else 
            {
                System.out.println("Invalid option!");
            }
        }
        sc.close();
    }

    private static int getSafeInt(Scanner sc) 
    {
        while (true) 
        {
            try 
            {
                int val = Integer.parseInt(sc.nextLine().trim());
                return val;
            } 
            catch (NumberFormatException e) 
            {
                System.out.print("Invalid input! Please enter a valid whole number: ");
            }
        }
    }

    private static double getSafeDouble(Scanner sc) 
    {
        while (true) 
        {
            try 
            {
                double val = Double.parseDouble(sc.nextLine().trim());
                return val;
            } 
            catch (NumberFormatException e) 
            {
                System.out.print("Invalid input! Please enter a valid decimal number: ");
            }
        }
    }
    // --- STUDENT DASHBOARD ---
    private static void handleStudentDashboard(PortalSystem portal, Scanner sc, Student s) 
    {
        boolean active = true;
        while (active) 
        {
            s.displayPortalData(portal.getStudentRank(s), portal.getTotalStudentCount());
            System.out.println("--- Student Actions ---");
            System.out.println("1. Change Password");
            System.out.println("2. Change Username (Requires Teacher Approval)");
            System.out.println("3. Logout");
            System.out.print("Select action: ");
            int act = getSafeInt(sc);
            sc.nextLine();
            if (act == 1) 
            {
                System.out.print("Enter new password: ");
                s.setPassword(sc.nextLine());
                portal.saveAllStudentsToFile();
                System.out.println("Password updated successfully!");
            } 
            else if (act == 2) 
            {
                System.out.println("\n--- Teacher Approval Required ---");
                System.out.print("Approving Teacher Username: ");
                String tUser = sc.nextLine();
                System.out.print("Approving Teacher Password: ");
                String tPass = sc.nextLine();
                if (portal.verifyAnyTeacher(tUser, tPass)) 
                {
                    System.out.print("Approval Granted! Enter desired new username: ");
                    String newUsername = sc.nextLine();
                    if (!portal.isUsernameTaken(newUsername)) 
                    {
                        s.setUsername(newUsername);
                        portal.saveAllStudentsToFile();
                        System.out.println("Username updated permanently!");
                    } 
                    else 
                    {
                        System.out.println("Error: That username is already taken.");
                    }
                } 
                else 
                {
                    System.out.println("Approval Denied: Invalid teacher credentials.");
                }
            } 
            else if (act == 3) 
            {
                active = false;
            } 
            else 
            {
                System.out.println("Invalid choice!");
            }
        }
    }

    private static void handleTeacherDashboard(PortalSystem portal, Scanner sc, Teacher t) 
    {
        boolean active = true;
        while (active) 
        {
            t.displayTeacherData();
            System.out.println("--- Teacher Actions ---");
            System.out.println("1. Modify a Student's Data");
            System.out.println("2. Modify Own Profile Data (Requires Master Key)");
            System.out.println("3. Logout");
            System.out.print("Select action: ");

            int act = getSafeInt(sc);

            if (act == 1) 
            {
                System.out.print("Enter Student Roll No to modify (e.g. FA26-001): ");
                Student target = portal.findStudentByRoll(sc.nextLine());
                if (target != null) modifyStudentHelper(portal, sc, target);
                else System.out.println("Student not found!");
            } 
            else if (act == 2) 
            {
                System.out.print("Enter Master Key to unlock profile editing: ");
                if (portal.verifyMasterKey(sc.nextLine())) modifyTeacherHelper(portal, sc, t);
                else System.out.println("Authorization Failed: Incorrect Master Key!");
            } 
            else if (act == 3) 
            {
                active = false;
            } 
            else 
            {
                System.out.println("Invalid choice!");
            }
        }
    }

    private static void handleMasterAdminPanel(PortalSystem portal, Scanner sc) 
    {
        boolean active = true;
        while (active) 
        {
            System.out.println("\n================ MASTER ADMIN PANEL ================");
            System.out.println("1. View All Students");
            System.out.println("2. Add New Student");
            System.out.println("3. Modify Any Student Data");
            System.out.println("4. Remove a Student");
            System.out.println("----------------------------------------------------");
            System.out.println("5. View All Teachers");
            System.out.println("6. Add New Teacher");
            System.out.println("7. Modify Any Teacher Data");
            System.out.println("8. Remove a Teacher");
            System.out.println("----------------------------------------------------");
            System.out.println("9. Exit Master Panel");
            System.out.print("Select Admin Action: ");

            int act = getSafeInt(sc);

            switch (act) 
            {
                case 1:
                    System.out.println("\n--- ALL REGISTERED STUDENTS ---");
                    for (Student s : portal.getAllStudents()) 
                    {
                        System.out.printf("Roll: %s | User: %s | CNIC: %s | Phone: %s | CGPA: %.2f | Attend: %d%%\n",
                        s.getRollNo(), s.getUsername(), s.getCnic(), s.getPhoneNo(), s.getCgpa(), s.getMonthlyAttendance());
                    }
                    break;
                case 2:
                    addNewStudentFlow(portal, sc);
                    break;
                case 3:
                    System.out.print("Enter Student Roll No to modify: ");
                    Student targetS = portal.findStudentByRoll(sc.nextLine());
                    if (targetS != null) modifyStudentHelper(portal, sc, targetS);
                    else System.out.println("Student not found.");
                    break;
                case 4:
                    System.out.print("Enter Student Roll No to remove: ");
                    if (portal.removeStudent(sc.nextLine())) System.out.println("Student removed permanently.");
                    else System.out.println("Student not found.");
                    break;
                case 5:
                    System.out.println("\n--- ALL REGISTERED TEACHERS ---");
                    for (Teacher t : portal.getAllTeachers()) {
                        System.out.printf("ID: %s | User: %s | CNIC: %s | Phone: %s | Attend: %d%% | Salary: Rs. %.2f\n",
                                t.getTeacherId(), t.getUsername(), t.getCnic(), t.getPhoneNo(), t.getMonthlyAttendance(), t.getSalary());
                    }
                    break;
                case 6:
                    addNewTeacherFlow(portal, sc);
                    break;
                case 7:
                    System.out.print("Enter Teacher ID to modify (e.g. TCH-01): ");
                    Teacher targetT = portal.findTeacherById(sc.nextLine());
                    if (targetT != null) modifyTeacherHelper(portal, sc, targetT);
                    else System.out.println("Teacher not found.");
                    break;
                case 8:
                    System.out.print("Enter Teacher ID to remove: ");
                    if (portal.removeTeacher(sc.nextLine())) System.out.println("Teacher removed permanently.");
                    else System.out.println("Teacher not found.");
                    break;
                case 9:
                    active = false;
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void addNewStudentFlow(PortalSystem portal, Scanner sc) 
    {
        System.out.print("Set Roll No (e.g. FA26-011): ");
        String roll = sc.nextLine();
        System.out.print("Set Username: ");
        String user = sc.nextLine();
        System.out.print("Set Password: ");
        String pass = sc.nextLine();
        System.out.print("Enter CNIC (e.g. 35202-1234567-1): ");
        String cnic = sc.nextLine();
        System.out.print("Enter Phone No (e.g. 0300-1234567): ");
        String phone = sc.nextLine();
        System.out.print("Enter CGPA: ");
        double cgpa = getSafeDouble(sc);
        System.out.print("Enter Monthly Attendance (%): ");
        int attend = getSafeInt(sc);

        ArrayList<Integer> scores = new ArrayList<>();
        System.out.println("Enter 3 recent monthly test scores:");
        for (int i = 1; i <= 3; i++) 
        {
            System.out.print("Test " + i + ": ");
            scores.add(getSafeInt(sc));
        }
        sc.nextLine();

        if (portal.registerStudent(roll, user, pass, cnic, phone, cgpa, attend, scores)) 
        {
            System.out.println("Student added and saved permanently!");
        } 
        else 
        {
            System.out.println("Failed: Roll No or Username already exists.");
        }
    }

    private static void addNewTeacherFlow(PortalSystem portal, Scanner sc) 
    {
        System.out.print("Set Teacher ID (e.g. TCH-03): ");
        String id = sc.nextLine();
        System.out.print("Set Username: ");
        String user = sc.nextLine();
        System.out.print("Set Password: ");
        String pass = sc.nextLine();
        System.out.print("Enter CNIC: ");
        String cnic = sc.nextLine();
        System.out.print("Enter Phone No: ");
        String phone = sc.nextLine();
        System.out.print("Enter Monthly Attendance (%): ");
        int attend = getSafeInt(sc);
        System.out.print("Enter Monthly Salary: ");
        double salary = getSafeDouble(sc);
        sc.nextLine();

        if (portal.registerTeacher(id, user, pass, cnic, phone, attend, salary)) 
        {
            System.out.println("Teacher added and saved permanently!");
        } 
        else 
        {
            System.out.println("Failed: Teacher ID or Username already exists.");
        }
    }

    private static void modifyStudentHelper(PortalSystem portal, Scanner sc, Student s) 
    {
        System.out.println("\nModifying Student: " + s.getRollNo() + " (" + s.getUsername() + ")");
        System.out.println("1. Update Roll No");
        System.out.println("2. Update Username");
        System.out.println("3. Update Password");
        System.out.println("4. Update CNIC & Phone No");
        System.out.println("5. Update CGPA");
        System.out.println("6. Update Monthly Attendance");
        System.out.println("7. Update Test Scores");
        System.out.print("Choose field to update: ");
        int opt = getSafeInt(sc);
        sc.nextLine();

        if (opt == 1) 
        {
            System.out.print("New Roll No: ");
            String newRoll = sc.nextLine();
            if (portal.findStudentByRoll(newRoll) == null || newRoll.equalsIgnoreCase(s.getRollNo())) 
            {
                s.setRollNo(newRoll);
            } 
            else 
            {
                System.out.println("Error: Roll No already exists.");
                return;
            }
        } 
        else if (opt == 2) 
        {
            System.out.print("New Username: ");
            String newU = sc.nextLine();
            if (!portal.isUsernameTaken(newU) || newU.equalsIgnoreCase(s.getUsername())) 
            {
                s.setUsername(newU);
            } 
            else 
            {
                System.out.println("Error: Username already taken.");
                return;
            }
        } 
        else if (opt == 3) 
        {
            System.out.print("New Password: ");
            s.setPassword(sc.nextLine());
        } 
        else if (opt == 4) 
        {
            System.out.print("New CNIC: ");
            s.setCnic(sc.nextLine());
            System.out.print("New Phone No: ");
            s.setPhoneNo(sc.nextLine());
        } 
        else if (opt == 5) 
        {
            System.out.print("New CGPA: ");
            s.setCgpa(getSafeDouble(sc));
            sc.nextLine();
        } 
        else if (opt == 6) 
        {
            System.out.print("New Attendance (%): ");
            s.setMonthlyAttendance(getSafeInt(sc));
            sc.nextLine();
        } 
        else if (opt == 7) 
        {
            ArrayList<Integer> newScores = new ArrayList<>();
            for (int i = 1; i <= 3; i++) 
            {
                System.out.print("New Test " + i + " Score: ");
                newScores.add(getSafeInt(sc));
            }
            sc.nextLine();
            s.setMonthlyTestScores(newScores);
        } 
        else 
        {
            System.out.println("Invalid choice.");
            return;
        }
        portal.saveAllStudentsToFile();
        System.out.println("Student record updated and saved to disk!");
    }

    private static void modifyTeacherHelper(PortalSystem portal, Scanner sc, Teacher t) 
    {
        System.out.println("\nModifying Teacher: " + t.getTeacherId() + " (" + t.getUsername() + ")");
        System.out.println("1. Update Teacher ID");
        System.out.println("2. Update Username & Password");
        System.out.println("3. Update CNIC & Phone No");
        System.out.println("4. Update Monthly Attendance");
        System.out.println("5. Update Salary");
        System.out.print("Choose field to update: ");
        int opt = getSafeInt(sc);
        sc.nextLine();

        if (opt == 1) 
        {
            System.out.print("New Teacher ID: ");
            String newId = sc.nextLine();
            if (portal.findTeacherById(newId) == null || newId.equalsIgnoreCase(t.getTeacherId())) 
            {
                t.setTeacherId(newId);
            } 
            else 
            {
                System.out.println("Error: Teacher ID already exists.");
                return;
            }
        } 
        else if (opt == 2) 
        {
            System.out.print("New Username: ");
            String newU = sc.nextLine();
            if (!portal.isUsernameTaken(newU) || newU.equalsIgnoreCase(t.getUsername())) 
            {
                t.setUsername(newU);
            } 
            else 
            {
                System.out.println("Username already taken, keeping old username.");
            }
            System.out.print("New Password: ");
            t.setPassword(sc.nextLine());
        } 
        else if (opt == 3) 
        {
            System.out.print("New CNIC: ");
            t.setCnic(sc.nextLine());
            System.out.print("New Phone No: ");
            t.setPhoneNo(sc.nextLine());
        } 
        else if (opt == 4) 
        {
            System.out.print("New Attendance (%): ");
            t.setMonthlyAttendance(getSafeInt(sc));
            sc.nextLine();
        } 
        else if (opt == 5) 
        {
            System.out.print("New Salary: ");
            t.setSalary(getSafeDouble(sc));
            sc.nextLine();
        } 
        else 
        {
            System.out.println("Invalid choice.");
            return;
        }
        portal.saveAllTeachersToFile();
        System.out.println("Teacher record updated and saved to disk!");
    }
}
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Student 
{
    private String rollNo;
    private String username;
    private String password;
    private double cgpa;
    private int monthlyAttendance; 
    private ArrayList<Integer> monthlyTestScores; 

    public Student(String rollNo, String username, String password, double cgpa, int monthlyAttendance, ArrayList<Integer> monthlyTestScores) 
    {
        this.rollNo = rollNo;
        this.username = username;
        this.password = password;
        this.cgpa = cgpa;
        this.monthlyAttendance = monthlyAttendance;
        this.monthlyTestScores = monthlyTestScores;
    }


    public String getRollNo() { return rollNo; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public double getCgpa() { return cgpa; }
    public int getMonthlyAttendance() { return monthlyAttendance; }
    public ArrayList<Integer> getMonthlyTestScores() { return monthlyTestScores; }
    

    public void setPassword(String newPassword) 
    {
        this.password = newPassword;
    }
    
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
        System.out.println("        WELCOME TO YOUR STUDENT PORTAL     ");
        System.out.println("==========================================");
        System.out.printf(" Roll Number       : %s\n", rollNo);
        System.out.printf(" Username          : %s\n", username);
        System.out.printf(" Cumulative GPA   : %.2f\n", cgpa);
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

class PortalSystem 
{
    private ArrayList<Student> studentDatabase = new ArrayList<>();
    private final String FILE_NAME = "students_portal_data.txt";

    public PortalSystem() 
    {
        loadDataFromFile();
        if (studentDatabase.isEmpty()) 
        {
            seedInitialData();
        }
    }

    private void seedInitialData() 
    {
        System.out.println("Initializing database with 10 sample students...");
        registerStudent("FA26-001", "ali_ahmed", "pass123", 3.82, 92, createScores(85, 90, 88));
        registerStudent("FA26-002", "zara_khan", "pass456", 3.95, 96, createScores(95, 98, 92));
        registerStudent("FA26-003", "usman_99", "pass789", 3.41, 80, createScores(70, 75, 82));
        registerStudent("FA26-004", "fatima_a", "secure77", 3.65, 88, createScores(80, 84, 89));
        registerStudent("FA26-005", "bilal_b", "pass111", 2.95, 75, createScores(60, 65, 70));
        registerStudent("FA26-006", "amna_dar", "pass222", 3.74, 91, createScores(87, 89, 91));
        registerStudent("FA26-007", "hamza_j", "pass333", 3.12, 83, createScores(72, 68, 75));
        registerStudent("FA26-008", "ayesha_e", "pass444", 3.58, 86, createScores(78, 82, 85));
        registerStudent("FA26-009", "zain_malik", "pass555", 2.71, 70, createScores(55, 62, 58));
        registerStudent("FA26-010", "sana_syed", "pass666", 3.89, 95, createScores(90, 93, 96));
    }

    private ArrayList<Integer> createScores(int s1, int s2, int s3) 
    {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(s1); list.add(s2); list.add(s3);
        return list;
    }

    public boolean registerStudent(String rollNo, String username, String password, double cgpa, int attendance, ArrayList<Integer> scores) 
    {
        for (Student s : studentDatabase) 
        {
            if (s.getRollNo().equalsIgnoreCase(rollNo)) return false;
            if (s.getUsername().equalsIgnoreCase(username)) return false;
        }
        Student newStudent = new Student(rollNo, username, password, cgpa, attendance, scores);
        studentDatabase.add(newStudent);
        saveAllDataToFile(); 
        return true;
    }

    public Student login(String username, String password) 
    {
        for (Student s : studentDatabase) 
        {
            if (s.getUsername().equals(username) && s.getPassword().equals(password)) 
            {
                return s;
            }
        }
        return null;
    }

    public int getStudentRank(Student target) 
    {
        ArrayList<Student> sortedList = new ArrayList<>(studentDatabase);
        sortedList.sort((s1, s2) -> Double.compare(s2.getCgpa(), s1.getCgpa()));
        for (int i = 0; i < sortedList.size(); i++) 
        {
            if (sortedList.get(i).getRollNo().equals(target.getRollNo())) 
            {
                return i + 1;
            }
        }
        return sortedList.size();
    }

    public int getTotalStudentCount() 
    {
        return studentDatabase.size();
    }


    void saveAllDataToFile() 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) 
        { 
            for (Student s : studentDatabase) 
            {
                writer.write(s.getRollNo() + "," + 
                             s.getUsername() + "," + 
                             s.getPassword() + "," + 
                             s.getCgpa() + "," + 
                             s.getMonthlyAttendance() + "," + 
                             s.getScoresAsString());
                writer.newLine();
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error keeping records clean on disk: " + e.getMessage());
        }
    }

    private void loadDataFromFile() 
    {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] data = line.split(",");
                if (data.length == 6) 
                {
                    String rollNo = data[0];
                    String username = data[1];
                    String password = data[2];
                    double cgpa = Double.parseDouble(data[3]);
                    int attendance = Integer.parseInt(data[4]);
                    
                    String[] scoresRaw = data[5].split("\\|");
                    ArrayList<Integer> scores = new ArrayList<>();
                    for (String s : scoresRaw) 
                    {
                        scores.add(Integer.parseInt(s));
                    }
                    studentDatabase.add(new Student(rollNo, username, password, cgpa, attendance, scores));
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Database load initialization interrupted: " + e.getMessage());
        }
    }
}

public class Student_Portal_Application 
{
    public static void main(String[] args) 
    {
        PortalSystem portal = new PortalSystem();
        Scanner sc = new Scanner(System.in);

        while (true) 
        {
            System.out.println("\n=== CAMPUS HUB STUDENT PORTAL ===");
            System.out.println("1. New Student Registration");
            System.out.println("2. Secure Student Login");
            System.out.println("3. Shut Down System");
            System.out.print("Select Action: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); 
            
            if (choice == 1) 
            {
                System.out.print("Set Roll No (e.g. FA26-011): ");
                String roll = sc.nextLine();
                System.out.print("Set Account Username: ");
                String user = sc.nextLine();
                System.out.print("Set Secure Password: ");
                String pass = sc.nextLine();
                System.out.print("Enter Current CGPA: ");
                double cgpa = sc.nextDouble();
                System.out.print("Enter Monthly Attendance (%): ");
                int attendance = sc.nextInt();
                
                ArrayList<Integer> testScores = new ArrayList<>();
                System.out.println("Provide exactly 3 recent monthly test scores (out of 100):");
                for(int i = 1; i <= 3; i++) 
                {
                    System.out.print("Test " + i + ": ");
                    testScores.add(sc.nextInt());
                }
                
                boolean successful = portal.registerStudent(roll, user, pass, cgpa, attendance, testScores);
                if (successful) 
                {
                    System.out.println("Record written down safely. Try logging in now!");
                } 
                else 
                {
                    System.out.println("Registration blocked! Unique Roll Number/Username requirement violated.");
                }
                
            } 
            else if (choice == 2) 
            {
                System.out.print("Account Username: ");
                String user = sc.nextLine();
                System.out.print("Secure Password: ");
                String pass = sc.nextLine();

                Student currentStudent = portal.login(user, pass);
                if (currentStudent != null) 
                {
                    boolean inPortal = true;
                    while (inPortal) 
                    {
                        int rank = portal.getStudentRank(currentStudent);
                        int total = portal.getTotalStudentCount();
                        currentStudent.displayPortalData(rank, total);
                        System.out.println("--- Dashboard Actions ---");
                        System.out.println("1. Change Security Password");
                        System.out.println("2. Return to Main Menu");
                        System.out.print("Select action: ");
                        int portalChoice = sc.nextInt();
                        sc.nextLine();
                        if (portalChoice == 1) 
                        {
                        System.out.print("Enter your new secure password: ");
                        String newPass = sc.nextLine();
                        currentStudent.setPassword(newPass);
                        portal.saveAllDataToFile();
                        System.out.println("Success: Security password updated permanently!");
                        } 
                        else if (portalChoice == 2) 
                        {
                        System.out.println("Logging out of portal session safely...");
                        inPortal = false;
                        } 
                        else 
                        {
                        System.out.println("Invalid action parameter!");
                        }
                    }
                }
                else 
                {
                    System.out.println("Access Denied: Combination match failed.");
                }
            }   
            else if (choice == 3) 
            {
                System.out.println("Deactivating interface... Core updated cleanly.");
                break;
            } 
            else 
            {
                System.out.println("Option out of bounds!");
            }
        } 
        sc.close();
    }
}
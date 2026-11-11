# Campus Hub - Management & Portal System (OOP Java)

A console-based Student and Teacher Management System built in Java using core Object-Oriented Programming (OOP) principles. The system features strict role-based access control (Headmaster/Master Admin, Teachers, and Students), inheritance-driven data modeling, dynamic CGPA class ranking, crash-proof input validation, and automated persistent text-file storage.

---

## Quick Start & Testing Credentials

The application automatically seeds **10 sample student accounts** and **2 sample teacher accounts** on first run so you can test all features immediately.

| Role | Login Credentials | Access & Capabilities |
| :--- | :--- | :--- |
| **Headmaster (Master Admin)** | Master Key: `ADMIN2026` | Full system access: view, add, modify, and remove any student or teacher record. |
| **Teacher (Sample 1)** | User: `prof_tariq` / Pass: `teach123` | Register new students, modify student records, update own password/phone, and approve student username changes. |
| **Teacher (Sample 2)** | User: `miss_nadia` / Pass: `teach456` | Register new students, modify student records, update own password/phone, and approve student username changes. |
| **Student (Top Rank)** | User: `zara_khan` / Pass: `pass456` | View personal portal (Rank **1 of 10**), change own password, or request a unique username change with teacher approval. |
| **Student (Mid Rank)** | User: `fatima_a` / Pass: `secure77` | View personal portal (Rank **5 of 10**), change own password, or request a unique username change with teacher approval. |

---

## Main Menu & Role Permissions

Public self-registration is disabled on the main screen to maintain realistic institutional security:

1. **Student Login:** Students can inspect their Roll Number, CNIC, Phone Number, CGPA, live class rank, attendance, and test scores. They can update their own password directly, or change their username (validated for uniqueness) after live teacher login approval.
2. **Teacher Login:** Faculty can view their profile, register new students, update student academic records, and change their own password or phone number. Modifying sensitive fields (Salary, Attendance, Teacher ID, CNIC, Username) requires the Headmaster's Master Key.
3. **Headmaster / Master Admin Panel:** Unlocked via the Master Key for full administrative control over all student and teacher accounts.
4. **Shut Down System:** Safely exits the application.

---

## OOP Architecture & Technical Highlights

* **Inheritance & Abstraction (`Person` Superclass):** Common attributes (`username`, `password`, `cnic`, `phoneNo`) are defined in an abstract `Person` superclass and inherited by both `Student` and `Teacher` subclasses.
* **Encapsulation:** All class fields are `private` or `protected`, exposing state updates strictly through validated getters and setters.
* **Collections & Sorting:** Uses dynamic `ArrayList` collections for runtime storage and clones the student list with a custom `Comparator` to compute real-time class rankings without altering database order.
* **Crash-Proof Input Handling:** Custom `getSafeInt` and `getSafeDouble` helpers catch invalid keyboard inputs (`NumberFormatException`) so accidental text entries never terminate the console loop.
* **Permanent File Storage:** Automatically reads and writes records to `students_portal_data.txt` and `teachers_portal_data.txt` so all changes persist across sessions.

---

## How to Run Locally

1. Clone this repository:
   ```bash
   git clone https://github.com
   ```
2. Navigate into the project directory and compile the Java source file:
   ```bash
   javac Student_Portal_Application.java
   ```
3. Run the application:
   ```bash
   java Student_Portal_Application
   ```
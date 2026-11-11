# Campus Hub - Management & Portal System (OOP Java)

A console-based Student and Teacher Management System built in Java using core Object-Oriented Programming (OOP) principles. The system features role-based access control (Master Admin, Teachers, and Students), inheritance-driven data modeling, dynamic CGPA class ranking, and automated persistent text-file storage.

---

## Quick Start & Testing Credentials

The application automatically seeds **10 sample student accounts** and **2 sample teacher accounts** on first run so you can test all features immediately.

| Role | Login Credentials | Access & Capabilities |
| :--- | :--- | :--- |
| **Master Admin** | Master Key: `ADMIN2026` | Full system control: view, add, modify, and remove any student or teacher record. |
| **Teacher (Sample 1)** | User: `prof_tariq` / Pass: `teach123` | View own profile, modify any student's records, and approve student username changes. |
| **Teacher (Sample 2)** | User: `miss_nadia` / Pass: `teach456` | View own profile, modify any student's records, and approve student username changes. |
| **Student (Top Rank)** | User: `zara_khan` / Pass: `pass456` | View personal portal (Rank **1 of 10**), change password, or change username with teacher approval. |
| **Student (Mid Rank)** | User: `fatima_a` / Pass: `secure77` | View personal portal (Rank **5 of 10**), change password, or change username with teacher approval. |

---

## Core Features

* **Master Admin Panel:** Protected by a Master Key to inspect all database records, register or remove teachers and students, and modify any profile field.
* **Teacher Portal:** Allows faculty to view attendance and salary details, update student academic records, and authorize student username changes. Editing a teacher's own profile requires Master Key authorization.
* **Student Portal:** Displays Roll Number, CNIC, Phone Number, CGPA, dynamic class standing, monthly attendance percentage, and recent test scores.
* **Permanent File Storage:** Automatically reads and writes records to `students_portal_data.txt` and `teachers_portal_data.txt` so all changes persist after shutting down the program.

---

## OOP Architecture

* **Inheritance & Abstraction (`Person` Superclass):** Common attributes (`username`, `password`, `cnic`, `phoneNo`) are defined in an abstract `Person` superclass and inherited by both `Student` and `Teacher` subclasses.
* **Encapsulation:** All class attributes are strictly `private` or `protected`, exposing state updates only through validated getters and setters.
* **Collections & Sorting:** Uses dynamic `ArrayList` collections for runtime storage and clones the student list with a custom `Comparator` to calculate real-time class rankings without altering database order.

---

## How to Run Locally

1. Clone this repository:
   ```bash
   git clone https://github.com/mhassan22082007/Student_Portal.git
   ```
2. Navigate into the folder and compile the Java source file:
   ```bash
   javac Student_Portal_Application.java
   ```
3. Run the application:
   ```bash
   java Student_Portal_Application
   ```
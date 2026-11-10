# Campus Hub - Persistent Student Portal (OOP Java)

A robust, console-based Student Portal application built in Java using core Object-Oriented Programming (OOP) paradigms. The system features a unique credential validation mechanism, a dynamic runtime ranking algorithm based on student CGPAs, and automated persistent text-file storage to save data across system restarts.

---

## Quick Start / Testing Credentials

The system initializes automatically with **10 pre-seeded student accounts** inside the tracking database so you can test features right away. 

To explore the portal, run the application, choose **Option 2 (Secure Student Login)**, and use any of these sample credentials:

| Username | Password | Expected Performance Metrics |
| :--- | :--- | :--- |
| **zara_khan** | `pass456` | Top Tier (Displays Rank **1 out of 10** | CGPA: `3.95`) |
| **fatima_a** | `secure77` | Mid Tier (Displays Rank **5 out of 10** | CGPA: `3.65`) |
| **bilal_b** | `pass111` | Lower Tier (Displays Rank **9 out of 10** | CGPA: `2.95`) |

---

## 🛠️ Data Pipeline & Architecture Map

The system cleanly bifurcates raw persistence streams on your storage drive from active, operational object trees inside your computer's RAM.

### 1. Persistent Storage Layer (`students_portal_data.txt`)
Data is saved on disk as flat strings using a nested structural delimiter approach:
```text
FA26-001,ali_ahmed,pass123,3.82,92,85|90|88
```
* **Comma delimiter (`,`)**: Isolates distinct structural data fields (Roll Number, Username, Password, CGPA, Attendance percentage, and raw scores string block).
* **Pipe delimiter (`|`)**: Used as an inner array separator to pack or unpack arbitrary lists of monthly test scores without corrupting the main layout parameters.

### 2. Operational Memory Layer (RAM Runtime Structure)
Upon boot, the program maps flat records into live, interacting object references:

```text
┌────────────────────────────────────────────────────────┐
│                   studentDatabase                      │
│                (ArrayList<Student>)                    │
└──────────────────────────┬─────────────────────────────┘
                           │
         ┌─────────────────┴─────────────────┐
         ▼                                   ▼
 ┌───────────────┐                   ┌───────────────┐
 │  Student [1]  │                   │  Student [2]  │
 ├───────────────┤                   ├───────────────┤
 │ Roll: ...001  │                   │ Roll: ...002  │
 │ User: ali...  │                   │ User: zara... │
 │ CGPA: 3.82    │                   │ CGPA: 3.95    │
 │ Attend: 92%   │                   │ Attend: 96%   │
 │ Scores: ──────┼──┐                │ Scores: ──────┼──┐
 └───────────────┘  │                └───────────────┘  │
                    ▼                                   ▼
         ┌───────────────────┐               ┌───────────────────┐
         │ ArrayList<Integer>│               │ ArrayList<Integer>│
         ├───────────────────┤               ├───────────────────┤
         │  -> 85            │               │  -> 95            │
         │  -> 90            │               │  -> 98            │
         │  -> 88            │               │  -> 92            │
         └───────────────────┘               └───────────────────┘
```

---

## OOP Principles Applied

* **Encapsulation**: All foundational fields inside `class Student` are explicitly defined as `private`. State modifications are blocked from external scopes, safely channeling access patterns strictly via immutable getter paths.
* **Collections & Framework Manipulation**: Utilizes dynamic `ArrayList` allocations over standard arrays to securely host scale-free data records. Runtime student standing ranks are processed by cloning a decoupled memory slice and organizing elements via inline polymorphic `Comparator` definitions.
* **Robust File I/O Management**: Replaces classic sequential C pointer hooks (`FILE *fp`) with automated Java streaming structures (`BufferedWriter` / `BufferedReader`). Bound directly inside try-with-resources conditions to guarantee safe file handle deallocation even during abrupt stack failures.

---

## How to Run Locally

### Prerequisites
* Java Development Kit (JDK 8 or higher) installed on your machine.
* Git installed locally.

### Execution Steps
1. Clone this repository directly into your local workspace:
   ```bash
   git clone https://github.com
   ```
2. Open your terminal inside the project directory and compile the main source code file:
   ```bash
   javac Student_Portal_Application.java
   ```
3. Run the compiled application file:
   ```bash
   java Main
   ```

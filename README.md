# 🚀 CMO DailyFlow

[![Java](https://img.shields.io/badge/Java-17-blue?logo=java)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Backend-green?logo=springboot)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Security-Spring%20Security-red?logo=springsecurity)](https://spring.io/projects/spring-security)
[![Database](https://img.shields.io/badge/Database-MySQL-lightblue?logo=mysql)](https://www.mysql.com/)
[![Maven](https://img.shields.io/badge/Build-Maven-orange?logo=apachemaven)](https://maven.apache.org/)

**CMO DailyFlow** is a role‑based **Daily Routine & Meeting Management System** designed specifically for **CMOs (Chief Marketing Officers)**.  
It streamlines scheduling, approvals, and daily routine tracking with secure authentication and dashboards for Admins, CMs, and Users.

---

## 🛠 Tech Stack
- ☕ **Java 17**
- 🌱 **Spring Boot**
- 🔒 **Spring Security** (role-based authentication)
- 🗄 **MySQL Database**
- 🎨 **Thymeleaf Templates** (UI rendering)

---

## 📦 Features
- 🔐 Secure login with **Spring Security**  
- 👤 User registration with **photo upload**  
- 📋 Meeting scheduling by users  
- 🧑‍💼 Meeting approval/rejection by CMs  
- 👨‍💻 Admin dashboard to view all users, CMs, and meetings  
- 📅 Daily routine tracking (today’s meetings)  
- 🖼 File uploads served via `/uploads/**`  

---

## ⚙️ Prerequisites
- Java 17+  
- MySQL running locally  
- Maven  

---

## 🚀 Getting Started

**1️⃣ Clone the repository**

git clone https://github.com/BhaveshPatil1808/CMO-DailyFlow.git
cd CMO-DailyFlow

---
**2️⃣ Configure Database**  
Update application.properties with your DB credentials:
spring.datasource.url=jdbc:mysql://localhost:3306/cmo_routine
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update


**3️⃣ Build the project**

mvn clean install

**4️⃣ Run the application**

mvn spring-boot:run

**💬 Usage (Role-Based Dashboards)**
**👤 User Dashboard**
Register/Login

Request meetings with CMs

View my meetings

**🧑‍💼 CM Dashboard**
View assigned meetings

Approve/Reject/Delete meetings

View today’s meetings

**👨‍💻 Admin Dashboard**
View all users

View all CMs

View all meetings

View today’s meetings


**📂 Project Structure**
```
CMO-DailyFlow/
 ├── com.Bhavesh.main/
 │    ├── CmDailyRoutineManagementApplication.java   # Spring Boot entry point
 │    ├── Configuration/                            # Security & Web Config
 │    ├── Controllers/                              # Admin, CM, User, Meeting, Login
 │    ├── Enityt/                                   # Entities (Users, Meeting)
 │    ├── Enums/                                    # Role, Status, Priority
 │    ├── Repository/                               # JPA Repositories
 │    └── Service/                                  # Services & Implementations
 └── resources/
      ├── templates/                                # Thymeleaf UI (admin-dashboard, cm-dashboard, user-dashboard, login, register, meetings, meeting-form)
      ├── static/                                   # CSS, JS, images
      └── application.properties                    # DB config
```

**🗄 Database Schema (Simplified)**
users

id, name, email, password, role, photoUrl

meetings

id, title, description, date, startTime, endTime, priority, status, user_id, cm_id

**🚀 Future Improvements**
🌐 Add REST APIs for mobile/web integration

🔐 Enhance security with JWT authentication

📊 Add analytics dashboard for meetings

🐳 Dockerize for deployment

📅 Calendar integration (Google/Outlook)

**👨‍💻 Author**
**Bhavesh Patil**

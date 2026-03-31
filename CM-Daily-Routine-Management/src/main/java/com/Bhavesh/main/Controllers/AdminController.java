package com.Bhavesh.main.Controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Bhavesh.main.Enityt.Meeting;
import com.Bhavesh.main.Enityt.Users;
import com.Bhavesh.main.Enums.Role;
import com.Bhavesh.main.Service.MeetingService;
import com.Bhavesh.main.Service.UserService;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private MeetingService meetingService;

    // ✅ Admin Dashboard
    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {

        // 👤 All Users
        List<Users> users = userService.getUsersByRole(Role.USER);

        // 👨‍💼 All CMs
        List<Users> cms = userService.getUsersByRole(Role.CM);

        // 📅 All Meetings
        List<Meeting> meetings = meetingService.getAllMeetings();

        // 📅 Today's Meetings
        List<Meeting> todayMeetings = meetings.stream()
                .filter(m -> m.getStartTime() != null &&
                        m.getStartTime().toLocalDate().equals(LocalDate.now()))
                .toList();

        model.addAttribute("users", users);
        model.addAttribute("cms", cms);
        model.addAttribute("meetings", meetings);
        model.addAttribute("todayMeetings", todayMeetings);

        return "admin-dashboard";
    }
}
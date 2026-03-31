package com.Bhavesh.main.Controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Bhavesh.main.Enityt.Meeting;
import com.Bhavesh.main.Enityt.Users;
import com.Bhavesh.main.Repository.UsersRepository;
import com.Bhavesh.main.Service.MeetingService;
import com.Bhavesh.main.Service.UserService;

@Controller
public class CmController {

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private UsersRepository usersRepository;

    // 📌 CM Dashboard
    @GetMapping("/cm/dashboard")
    public String cmDashboard(Model model, Principal principal) {

        // Logged in user email
    	String email = principal.getName();
        Users cm = userService.getUserByEmail(email);

        // ✅ FIX HERE
        List<Meeting> meetings = meetingService.getMeetingsByCm(cm);

        List<Meeting> todayMeetings = meetingService.getTodayMeetingsByCm(cm);
        model.addAttribute("user", cm);
        model.addAttribute("meetings", meetings);
        model.addAttribute("todayMeetings", todayMeetings);
        

        return "cm-dashboard";
    }
}
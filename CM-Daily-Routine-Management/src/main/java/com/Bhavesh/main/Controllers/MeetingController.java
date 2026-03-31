package com.Bhavesh.main.Controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.Bhavesh.main.Enityt.Meeting;
import com.Bhavesh.main.Enityt.Users;
import com.Bhavesh.main.Enums.Role;
import com.Bhavesh.main.Enums.Status;
import com.Bhavesh.main.Service.MeetingService;
import com.Bhavesh.main.Service.UserService;

@Controller
@RequestMapping("/meetings")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private UserService userService;

    // 📌 Show all meetings
    @GetMapping
    public String getAllMeetings(Model model) {
        List<Meeting> meetings = meetingService.getAllMeetings();
        model.addAttribute("meetings", meetings);
        return "meetings";
    }

    // 📌 Show request form (WITH CM LIST 🔥)
    @GetMapping("/request")
    public String showForm(Model model) {
        model.addAttribute("meeting", new Meeting());

        List<Users> cms = userService.getUsersByRole(Role.CM);
        model.addAttribute("cms", cms);

        return "meeting-form";
    }

    // 📌 Save meeting
    @PostMapping("/save")
    public String saveMeeting(@ModelAttribute Meeting meeting, Principal principal) {

        String email = principal.getName();
        Users user = userService.getUserByEmail(email);

        meeting.setUser(user); // requester
        meeting.setStatus(Status.PENDING);
        meeting.setDate(meeting.getStartTime().toLocalDate());

        meetingService.saveMeeting(meeting);

        return "redirect:/users/dashboard";
    }

    // 📌 Approve
    @GetMapping("/approve/{id}")
    public String approve(@PathVariable Long id) {
        meetingService.approveMeeting(id);
        return "redirect:/cm/dashboard";
    }

    // 📌 Reject
    @GetMapping("/reject/{id}")
    public String reject(@PathVariable Long id) {
        meetingService.rejectMeeting(id);
        return "redirect:/cm/dashboard";
    }

    // 📌 Delete
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        meetingService.deleteMeeting(id);
        return "redirect:/cm/dashboard";
    }
}
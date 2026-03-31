package com.Bhavesh.main.Controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.Bhavesh.main.Enityt.Meeting;
import com.Bhavesh.main.Enityt.Users;
import com.Bhavesh.main.Service.MeetingService;
import com.Bhavesh.main.Service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private MeetingService meetingService;
	
    @Autowired
    private UserService userService;

    // 📌 Register Page
    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new Users());
        return "register";
    }

    // 📌 Save User
    @PostMapping("/save")
    public String saveUser(@ModelAttribute Users user,
                           @RequestParam("photo") MultipartFile file) {

        try {
            String uploadDir = "uploads/";
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path path = Paths.get(uploadDir + fileName);

            // Create folder if not exists
            Files.createDirectories(path.getParent());

            // Save file
            Files.write(path, file.getBytes());

            // Save path in DB
            user.setPhotoUrl("/uploads/" + fileName);

        } catch (Exception e) {
            e.printStackTrace();
        }

        userService.registerUser(user);

        return "redirect:/login";
    }
    
    
    @GetMapping("/dashboard")
    public String userDashboard(Model model, Principal principal) {

        String email = principal.getName();
        Users user = userService.getUserByEmail(email);

        
        // 🔥 Get meetings created by this user
        List<Meeting> meetings = meetingService.getMeetingsByUser(user);

        model.addAttribute("user", user);
        model.addAttribute("meetings", meetings);

        return "user-dashboard";
    }
  
	
	
}
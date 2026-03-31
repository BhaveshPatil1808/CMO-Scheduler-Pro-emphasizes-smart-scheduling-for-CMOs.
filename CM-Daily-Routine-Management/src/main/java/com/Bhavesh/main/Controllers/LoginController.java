package com.Bhavesh.main.Controllers;
import com.Bhavesh.main.Repository.UsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.Bhavesh.main.Enityt.Users;

@Controller
public class LoginController {

   

	@GetMapping("/")
	public String login() {
		return "login";
	}
	
	
	
	@GetMapping("/login")
    public String loginPage() {
		
        return "login";
    }
}

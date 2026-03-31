package com.Bhavesh.main.Service;

import java.util.List;

import com.Bhavesh.main.Enityt.Users;
import com.Bhavesh.main.Enums.Role;

public interface UserService {

	void registerUser(Users user);

	Users getUserByEmail(String email);
	
    Users getUserById(Long id);

    List<Users> getAllUsers();

//    public List<Users> getAllCMs();
    List<Users> getUsersByRole(Role role);
    void deleteUser(Long id);
}

package com.Bhavesh.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Bhavesh.main.Enityt.Users;
import com.Bhavesh.main.Enums.Role;
import com.Bhavesh.main.Repository.UsersRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;
	@Override
	public void registerUser(Users user) {
		user.setPassword(encoder.encode(user.getPassword()));
		usersRepository.save(user);
		
	}

	@Override
	public Users getUserById(Long id) {
		// TODO Auto-generated method stub
		return usersRepository.findById(id).
				orElseThrow(
						()-> new RuntimeException("User not found"));
	}

	@Override
	public List<Users> getAllUsers() {
		// TODO Auto-generated method stub
		return usersRepository.findAll();
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub

		usersRepository.deleteById(id);
	}

	@Override
	public Users getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return usersRepository.findByEmail(email).get();
	}

	@Override
	public List<Users> getUsersByRole(Role role) {
		// TODO Auto-generated method stub
		return usersRepository.findByRole(role);
	}



}

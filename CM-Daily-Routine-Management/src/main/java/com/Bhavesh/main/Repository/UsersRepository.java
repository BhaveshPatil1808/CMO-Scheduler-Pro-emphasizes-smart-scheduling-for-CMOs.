package com.Bhavesh.main.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Bhavesh.main.Enityt.Users;
import com.Bhavesh.main.Enums.Role;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	Optional<Users> findUserByEmailAndPassword(String email, String password);
	
	List<Users> findByRole(Role role);
	
	Optional<Users> findByEmail(String email);
}

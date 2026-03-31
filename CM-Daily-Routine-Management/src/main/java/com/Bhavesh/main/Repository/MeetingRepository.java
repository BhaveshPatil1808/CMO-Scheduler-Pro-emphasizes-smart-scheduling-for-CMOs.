package com.Bhavesh.main.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Bhavesh.main.Enityt.Meeting;
import com.Bhavesh.main.Enityt.Users;


@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

	List<Meeting> findAll();

	List<Meeting> findByDate(LocalDate date);
	
	List<Meeting> findByCmAndDate(Users cm, LocalDate date);
	List<Meeting> getMeetingsByUser(Users user);
	List<Meeting> findByCm(Users cm);
	List<Meeting> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}

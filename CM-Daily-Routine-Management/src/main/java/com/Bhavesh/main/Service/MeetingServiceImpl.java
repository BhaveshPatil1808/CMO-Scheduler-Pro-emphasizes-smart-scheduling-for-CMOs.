package com.Bhavesh.main.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bhavesh.main.Enityt.Meeting;
import com.Bhavesh.main.Enityt.Users;
import com.Bhavesh.main.Enums.Status;
import com.Bhavesh.main.Repository.MeetingRepository;
import com.Bhavesh.main.Repository.UsersRepository;

@Service
public class MeetingServiceImpl implements MeetingService {

	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private MeetingRepository meetingRepository;

	@Override
	public Meeting requestMeeting(Meeting meeting) {
		meeting.setStatus(Status.PENDING);
		
		List<Meeting> list = meetingRepository.findByStartTimeBetween(
								meeting.getStartTime(), 
								meeting.getEndTime()
								);
		if(!list.isEmpty()) {
			throw new RuntimeException("Time Slot is already booked");
		}
		
		return meetingRepository.save(meeting);
	}

	@Override
	public List<Meeting> getAllMeetings() {
		// TODO Auto-generated method stub
		return meetingRepository.findAll();
	}

	@Override
	public List<Meeting> getMeetingsByUser(Long userId) {
		// TODO Auto-generated method stub
		return usersRepository.findById(userId)
				.orElseThrow(
						()->new RuntimeException("User not found"))
				.getMeetings();
	}

	@Override
	public Meeting approveMeeting(Long meetingId) {
		Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new RuntimeException("Meeting not found"));

        meeting.setStatus(Status.APPROVED);

        return meetingRepository.save(meeting);
	}

	@Override
	public Meeting rejectMeeting(Long meetingId) {
		Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new RuntimeException("Meeting not found"));

        meeting.setStatus(Status.REJECTED);

        return meetingRepository.save(meeting);
	}

	@Override
	public void deleteMeeting(Long meetingId) {
		// TODO Auto-generated method stub

		meetingRepository.deleteById(meetingId);
	}

	@Override
	public void saveMeeting(Meeting meeting) {
		// TODO Auto-generated method stub
		meetingRepository.save(meeting);
	}

	@Override
	public List<Meeting> getMeetingsByCm(Users cm) {
		// TODO Auto-generated method stub
		return meetingRepository.findByCm(cm);
	}

	@Override
	public List<Meeting> getMeetingsByUser(Users user) {
		// TODO Auto-generated method stub
		return meetingRepository.getMeetingsByUser(user);
	}

	@Override
	public List<Meeting> getTodayMeetingsByCm(Users cm) {
		// TODO Auto-generated method stub
		return meetingRepository.findByCmAndDate(cm, LocalDate.now());
	}

}

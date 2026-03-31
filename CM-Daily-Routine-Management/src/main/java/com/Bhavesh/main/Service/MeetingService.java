package com.Bhavesh.main.Service;

import java.util.List;

import com.Bhavesh.main.Enityt.Meeting;
import com.Bhavesh.main.Enityt.Users;

public interface MeetingService {

	Meeting requestMeeting(Meeting meeting);

    List<Meeting> getAllMeetings();

    void saveMeeting(Meeting meeting);
    
    List<Meeting> getMeetingsByUser(Users user);

    Meeting approveMeeting(Long meetingId);

    List<Meeting> getMeetingsByUser(Long userId);
    List<Meeting> getTodayMeetingsByCm(Users cm);
    List<Meeting> getMeetingsByCm(Users cm);
    Meeting rejectMeeting(Long meetingId);

    void deleteMeeting(Long meetingId);
}

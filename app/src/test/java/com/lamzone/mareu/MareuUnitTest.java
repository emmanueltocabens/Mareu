package com.lamzone.mareu;

import com.lamzone.mareu.DI.DependencyInjector;
import com.lamzone.mareu.model.Meeting;
import com.lamzone.mareu.model.Room;
import com.lamzone.mareu.service.FakeMareuGenerator;
import com.lamzone.mareu.service.MareuApiService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Local unit test, which will execute on the JVM development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MareuUnitTest {
    MareuApiService apiService;
    List<Room> roomList;
    List<Meeting> meetingList;

    /**
     * init api
     */
    @Before
    public void init(){
        apiService = DependencyInjector.getNewInstanceApiService();
        roomList = new ArrayList<>(FakeMareuGenerator.FAKE_ROOMS);
        meetingList = new ArrayList<>(FakeMareuGenerator.FAKE_MEETINGS);
    }

    /**
     *
     */
    @After
    public void reset(){

    }
    /**
     * tests for getMeetings()*
     */
    @Test
    public void getMeetingsTest(){
        assertTrue(apiService.getAllMeetings().containsAll(meetingList));
    }

    /**
     * tests for getAllRooms()
     */
    @Test
    public void getAllRoomsTest(){
        assertTrue(apiService.getAllRooms().containsAll(roomList));
    }

    /**
     * test for addMeeting()
     * creates a new meetings and adds it to the api
     */
    @Test
    public void addNewMeetingTest(){
        Calendar cal = Calendar.getInstance();
        List<String> participants = Arrays.asList(
                "utilisateur1@gmail.com",
                "utilisateur2@gmail.com",
                "utilisateur3@gmail.com");
        Meeting tmp = new Meeting(cal.getTime(), cal.getTime(), roomList.get(0), participants, "reu_meetingListTest_1");
        apiService.addNewMeeting(tmp);
        assertTrue(apiService.getAllMeetings().contains(tmp));
    }

    /**
     * test for removeMeeting()
     * gets the element at index 0 and removes it
     */
    @Test
    public void removeMeetingTest(){
        List<Meeting> testList = apiService.getAllMeetings();
        Meeting unexpected = testList.get(0);
        apiService.removeMeeting(unexpected);
        testList = apiService.getAllMeetings();
        assertFalse(apiService.getAllMeetings().contains(unexpected));
    }

    /**
     * test for room filter
     * picks the 2 meetings in fakeMareuGenerator that are in room "Salle 1"
     * removes them for list and test if filter worked properly
     */
    @Test
    public void filterByRoomTest(){
        Meeting expected1 = meetingList.get(0);
        Meeting expected2 = meetingList.get(3);
        List<Meeting> testList = apiService.filterByRoom(expected1.getRoom());
        List<Meeting> expectedList = Arrays.asList(expected1, expected2);
        assertTrue(testList.containsAll(expectedList));
        assertEquals(testList.size(), 2);
    }

    /**
     * test for date filter
     * filters meetings for tomorrow, verifies there is none, then adds 2, then asserts that both are in the list
     */
    @Test
    public void filterByDateTest(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,1);
        List<Meeting> testList = new ArrayList<>(apiService.filterByDate(c.getTime()));
        assertTrue(testList.isEmpty());
        Meeting tmp1 = new Meeting(
                c.getTime(),
                c.getTime(),
                roomList.get(1),
                new ArrayList<String>(Arrays.asList("p4","p5","p6","p7")),
                "test");
        Meeting tmp2 = new Meeting(
                c.getTime(),
                c.getTime(),
                roomList.get(0),
                new ArrayList<String>(Arrays.asList("p1","p2","p3")),
                "test");
        apiService.addNewMeeting(tmp1);
        apiService.addNewMeeting(tmp2);
        testList = apiService.filterByDate(c.getTime());
        assertTrue(testList.contains(tmp1));
        assertTrue(testList.contains(tmp2));
    }

    /**
     *
     */
    @Test
    public void getAvailableRoomsTest(){

    }
}
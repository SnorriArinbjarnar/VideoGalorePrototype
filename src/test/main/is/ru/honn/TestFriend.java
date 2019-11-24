package main.is.ru.honn;

import main.is.ru.honn.Entity.Friend;
import main.is.ru.honn.Service.FriendService;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class TestFriend {
    private Friend testFriend;
    private FriendService fService;

    @Test
    public void testAddToRentedFilms() {
        System.out.println("Creating a random Friend and random film id");
        testFriend = new Friend("Alfie","Sesam Street","Alfie@advania.is","555666");
        int testId = 1;
        System.out.println("Renting that movie");
        testFriend.rentVideo(testId);

        System.out.println("Assert that videoid goes into Friends rented films list");
        System.out.println("Same test fails Integration test");
        assertTrue(testFriend.getRentedFilms().contains(testId));

    }

    @Test
    public void testMaxFriendID() {
        fService = new FriendService();

        int maxId = fService.getMaxID();
        ArrayList<Friend> friends = fService.getFriends();

        for(int i = 0; i < friends.size(); i++) {
            if(maxId != friends.get(i).getFriendId()) {
                assertTrue(maxId > friends.get(i).getFriendId());
            }
        }
    }

    @Test
    public void testGetFriendById() {
        fService = new FriendService();

        int id = 2;
        Friend temp = fService.getFriendById(id);
        System.out.println("Assert that getFriendById function returns right friend");
        assertTrue(id == temp.getFriendId());

    }


}

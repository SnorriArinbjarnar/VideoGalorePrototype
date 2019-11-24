package main.is.ru.honn;

import main.is.ru.honn.Entity.Borrowed;
import main.is.ru.honn.Entity.Friend;
import main.is.ru.honn.Service.BorrowedService;
import main.is.ru.honn.Service.FriendService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertTrue;

public class TestBorrowed {
    private BorrowedService bService;
    private FriendService fService;

    @Test
    public void testBorrowedVideosByDate() {
        bService = new BorrowedService();
        fService = new FriendService();

        Friend temp = new Friend("Skuli Skarphedinnson","Alfheimum 66","skuli@skelfir.is","555666");
        temp.setId(fService.getMaxID());
        Friend temp2 = new Friend("Skuli Skarphedinnson","Alfheimum 66","skuli@skelfir.is","555666");
        temp2.setId(fService.getMaxID());
        Friend temp3 = new Friend("Skuli Skarphedinnson","Alfheimum 66","skuli@skelfir.is","555666");
        temp3.setId(fService.getMaxID());
        Friend temp4 = new Friend("Skuli Skarphedinnson","Alfheimum 66","skuli@skelfir.is","555666");
        temp4.setId(fService.getMaxID());

        GregorianCalendar tempFriend = new GregorianCalendar();
        tempFriend.set(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH);
        bService.rentAVideo(bService.getAvailableVideos(temp.getFriendId()).get(0).getVideoID(), temp.getFriendId());


        ArrayList<Borrowed> list = bService.getBorrowedVideosByDate(tempFriend);
        for(int i = 0; i < list.size(); i++) {
            assertTrue(list.get(i).getBorrowDate().equals(tempFriend));
        }

    }

    @Test
    public void testMonthOldVideos() {
        bService = new BorrowedService();

        ArrayList<Borrowed> daList = bService.getMonthOldLoans();

        Calendar x = new GregorianCalendar();
        int days = x.get(Calendar.DAY_OF_YEAR);

        for(int i = 0; i < daList.size(); i++) {
            GregorianCalendar daysBorrowed = daList.get(i).getBorrowDate();
            int daysBorrowedD = daysBorrowed.get(Calendar.DAY_OF_YEAR);
            assertTrue((days - daysBorrowedD) >= 30);

        }
    }

    @Test
    public void testIDS() {
        bService = new BorrowedService();
        ArrayList<Borrowed> b = bService.getListOfBorrowedVideosAndWhoHasThem();

        for(int i = 0; i < b.size(); i++) {
            System.out.println("ID: " + b.get(i).getID());
        }
    }

    @Test
    public void testReturnVideo() {
        bService = new BorrowedService();
        int friendId = bService.getListOfBorrowedVideosAndWhoHasThem().get(0).getFriendId();
        int videoId = bService.getListOfBorrowedVideosAndWhoHasThem().get(0).getVideoId();
        bService.returnVideo(friendId, videoId);

        assertTrue(bService.getListOfBorrowedVideosAndWhoHasThem().get(0).getReturnDate() == null);
        assertTrue(bService.getListOfBorrowedVideosAndWhoHasThem().get(0).isReturned() == true);



    }


}

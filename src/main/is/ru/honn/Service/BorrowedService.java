package main.is.ru.honn.Service;

import main.is.ru.honn.Entity.Borrowed;
import main.is.ru.honn.Data.BorrowedData;
import main.is.ru.honn.Entity.Friend;
import main.is.ru.honn.Entity.Video;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class BorrowedService {
    /**
     * Reads from and writes to BorrowedData
     */
    private BorrowedData bData;

    public BorrowedService() {
        bData = new BorrowedData();
        bData.init();
    }

    public ArrayList<Video> getListOfBorrowedVideos () {
        return bData.getBorrowedVideos();
    }

    public ArrayList<Borrowed>  getListOfBorrowedVideosAndWhoHasThem () {
        return bData.getBorrowedVideosAndFriend();
    }

    public ArrayList<Borrowed> getBorrowedVideosByDate (GregorianCalendar cal) {
        return bData.getBorrowedVideosByDate(cal);
    }

    public ArrayList<Friend> friendsWithMonthOldVideos(GregorianCalendar cal) {
        return bData.friendsWithMonthOldVideos(cal);
    }

    public ArrayList<Borrowed> monthOldLoans(GregorianCalendar cal) {
        return bData.monthOldLoans(cal);
    }

    public ArrayList<Video> getAvailableVideos (int friendId) {
        return bData.getAvailableVideos(friendId);
    }

    public ArrayList<Friend> getFriendsWithBorrowedVideosByDate (GregorianCalendar cal) {
        return bData.getFriendsWithBorrowedTapes(cal);
    }

    public void returnVideo(int friendId, int videoId) {
        bData.returnVideo(friendId, videoId);
    }

    public ArrayList<Borrowed> getMonthOldLoans() {
        return bData.monthOldLoans();
    }

    public int getBorrowedIndex(int friendId, int videoId) {
        return bData.getBorrowedIndex(friendId, videoId);
    }

    public Borrowed getBorrowByIndex(int index) {
        return bData.getBorrowByIndex(index);
    }

    public int getMaxID() {
        return bData.getMaxID();
    }

    public void rentAVideo(int videoId, int friendId) {
        bData.rentAVideo(videoId,friendId);
    }

    public void addToFriendsHash(int id, Friend f) {
        bData.addToHashMap(id, f);
    }


}

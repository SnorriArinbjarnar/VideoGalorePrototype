package main.is.ru.honn.Service;

import java.util.ArrayList;
import java.util.HashMap;

import main.is.ru.honn.Entity.Borrowed;
import main.is.ru.honn.Entity.Friend;
import main.is.ru.honn.Data.FriendData;

public class FriendService {
    private FriendData fData;

    /**
     * Reads from and writes to FriendsData
     */

    public FriendService() {

        fData = new FriendData();
        fData.init();

    }

    public ArrayList<Friend> getFriends() {
        return fData.getFriendList();
    }
    public HashMap<Integer,Friend> getFriendList() {return fData.getFriendHash();}

    public ArrayList<Borrowed> getBorrowedVideos() {
        return fData.getBorrowedList();
    }

    public Friend getFriendById(int id) {
        return fData.getFriendById(id);
    }

    public int getMaxID() {
        return fData.getMaxFriendID();
    }

    public void addToHashMap(int id, Friend f) {
        fData.addToHashMap(id, f);
    }

    public void addToList(Friend f) {
        fData.addToList(f);
    }
}

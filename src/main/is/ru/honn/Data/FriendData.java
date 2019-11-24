package main.is.ru.honn.Data;

import main.is.ru.honn.Entity.Borrowed;
import main.is.ru.honn.Entity.Friend;
import main.is.ru.honn.JSONReader.JsonFriendFile;
import main.is.ru.honn.Service.FriendService;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a database for friends,
 * in other words data of the registered users
 * in this simple prototype friends register
 * by username alone, not password
 */

public class FriendData {

    private FriendService service;
    private HashMap<Integer, Friend> friendList;
    private ArrayList<Friend> friends;
    private JsonFriendFile json;
    private ArrayList<Borrowed> borrowList;

    public FriendData() {
    }

    public void init() {
        json = new JsonFriendFile();
        friendList = json.getFriendList();
        friends = json.getFriends();
        borrowList = json.getBorrowedVideos();
    }

    public void printFriends() {
        for(int i = 0; i < friends.size()-1; i++) {
            System.out.println(friends.get(i).getName());
        }
    }

    public void addToHashMap(int id, Friend f) {
        friendList.put(id,f);
        friends.add(f);
    }

    public void addToList(Friend f) {
        friends.add(f);
    }

    public Friend getFriendById(int id) {
        Friend temp = friendList.get(id);
        return temp;
    }


    public ArrayList<Friend> getFriendList() {
        return friends;
    }

    public HashMap<Integer, Friend> getFriendHash () {
        return friendList;
    }

    public ArrayList<Borrowed> getBorrowedList () {
        return borrowList;
    }

    public int getMaxFriendID() {
        int max = 0;

        for(int i = 0; i < friends.size(); i++) {
            int id = friends.get(i).getFriendId();
            if(id > max) {
                max = id;
            }
        }
        return max;
    }
}

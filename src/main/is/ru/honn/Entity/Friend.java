package main.is.ru.honn.Entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a single friend
 * Each friend also has a list with ids
 * of movies he has rented
 */

public class Friend {
    private String name;
    private String Address;
    private String email;
    private String telephone;
    private int friendId;
    private HashMap <Integer, Video> rentedVideos;
    private ArrayList<Integer> rentedFilms;

    public Friend() {
        rentedVideos = new HashMap<Integer, Video>();
        rentedFilms = new ArrayList<Integer>();
    }

    public Friend(String name, String address, String email, String teli) {
        this.name = name;
        this.Address = address;
        this.email = email;
        this.telephone = teli;


        rentedVideos = new HashMap<Integer, Video>();
        rentedFilms = new ArrayList<Integer>();

    }

    public void rentVideo(int id) {
        rentedFilms.add(id);
    }

    public void getRentedVideos() {
        if(rentedFilms.size()-1 > 0) {
            System.out.println("Films rented:");
            for(int i = 0; i < rentedFilms.size(); i++) {
                System.out.println(rentedVideos.get(rentedFilms.get(i)).getTitle());
            }
        }
    }

    public ArrayList<Integer> getRentedFilms() {
        return this.rentedFilms;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String addr) {
        this.Address = addr;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTeli(String teli) {
        this.telephone = teli;
    }

    public String getEmail() {
        return this.email;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public String getAddress() {
        return this.Address;
    }

    public void setId(int fId) {
        this.friendId = fId;
    }



    public void rentVideo(Video v, int vid) {
        rentedVideos.put(vid, v);
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name + ",\n" + "Address: " + Address + ",\n" + "Email: " + email + ",\n" + "Telephone: " + telephone;
    }

    public int getFriendId() {
        return this.friendId;
    }
}

package main.is.ru.honn.Entity;

import main.is.ru.honn.Service.BorrowedService;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Borrowed {

    private GregorianCalendar borrowDate;
    private Calendar returnDate;
    private int friendId;
    private int videoId;
    private int ID;
    private boolean returned;


    public Borrowed() {

    }

    public Borrowed(int friendId, int videoId, GregorianCalendar borrowD, Calendar returnDate) {
        this.friendId = friendId;
        this.videoId = videoId;
        this.borrowDate = borrowD;
        this.returnDate = returnDate;

        if(returnDate == null) {
            this.returned = true;
        }
        this.returned = false;


    }

    public void returnTape() {
        this.returned = true;

        Calendar cal = Calendar.getInstance();
        //int month = cal.get(Calendar.MONTH);
        //month++;
        //cal.set(Calendar.YEAR,month,Calendar.DAY_OF_MONTH);
        this.returnDate = cal;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public void setBorrowDate(GregorianCalendar cal) {
        this.borrowDate = cal;
    }

    public void setReturnDate(Calendar cal) {
        this.returnDate = cal;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public  void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public int getID() {
        return this.ID;
    }

    public GregorianCalendar getBorrowDate() {
        return this.borrowDate;
    }

    public Calendar getReturnDate() {return this.returnDate;}

    public int getFriendId() {
        return this.friendId;
    }

    public boolean isReturned() {
        return this.returned;
    }

    public int getVideoId() {
        return this.videoId;
    }

    public String toString() {
        return "Friend with id " + this.getID() + " borrowed video with id " + this.getVideoId() + " " + this.getBorrowDate().get(Calendar.DAY_OF_MONTH) + "." +
                this.getBorrowDate().get(Calendar.MONTH) + "." + this.getBorrowDate().get(Calendar.YEAR) + "," + this.returnDate.get(Calendar.DAY_OF_MONTH) + "."
                + this.returnDate.get(Calendar.MONTH) + "." + this.returnDate.get(Calendar.YEAR);
    }


}

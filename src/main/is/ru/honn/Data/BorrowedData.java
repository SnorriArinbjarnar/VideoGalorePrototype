package main.is.ru.honn.Data;

import main.is.ru.honn.Entity.Borrowed;
import main.is.ru.honn.Entity.Friend;
import main.is.ru.honn.Service.FriendService;
import main.is.ru.honn.Service.VideoService;
import main.is.ru.honn.Entity.Video;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Represents a database for the renting management,
 * info about who rented what.
 *
 */

public class BorrowedData {
    private ArrayList<Borrowed> borrowedList;
    private HashMap<Integer, Friend> friends;
    private HashMap<Integer, Video> videos;
    private FriendService fService;
    private VideoService vService;

    public BorrowedData() {

    }

    public void init() {
        fService = new FriendService();
        vService = new VideoService();

        borrowedList = fService.getBorrowedVideos();
        friends = fService.getFriendList();
        videos = vService.getVideos();
    }

    public void addToHashMap(int id, Friend f) {
        friends.put(id, f);
    }

    public void rentAVideo(int videoId, int friendId) {
        Calendar cal = new GregorianCalendar();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        GregorianCalendar date = new GregorianCalendar();
        date.set(year,month,day);

        Calendar returnD = new GregorianCalendar();
        returnD = null;

        Borrowed temp = new Borrowed(friendId, videoId, date, returnD);
        borrowedList.add(temp);
        Friend tempF = fService.getFriendById(friendId);
        tempF.rentVideo(videoId);
        fService.getFriendById(friendId).rentVideo(vService.getVideo(videoId),videoId);

    }

    public void returnVideo(int friendId, int videoId) {
        int index = getBorrowedIndex(friendId, videoId);
        borrowedList.get(index).returnTape();
    }


    public Borrowed getBorrowByIndex(int index) {
        return borrowedList.get(index);
    }

    public int getBorrowedIndex(int friendId, int videoId) {
        for(int i = 0; i < borrowedList.size(); i++) {
            if((friendId == borrowedList.get(i).getFriendId()) && (videoId == borrowedList.get(i).getVideoId())){
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * @return list of all loans
     */
    public ArrayList<Video> getBorrowedVideos() {
        ArrayList<Video> tempArr = new ArrayList<Video>();
        for(int i = 0; i < borrowedList.size(); i++) {
            if(borrowedList.get(i).getReturnDate() == null){
                int id = borrowedList.get(i).getVideoId();
                Video v = videos.get(id);
                tempArr.add(v);
            }
        }
        return tempArr;
    }

    public ArrayList<Borrowed> getBorrowedVideosAndFriend() {
        return borrowedList;

    }

    /**
     *
     * @param date to compare to
     * @return list of all borrowed tapes at given date
     */
    public ArrayList<Borrowed> getBorrowedVideosByDate(GregorianCalendar date) {
        ArrayList<Borrowed> temp = new ArrayList<Borrowed>();
        date.clear(Calendar.MILLISECOND);

        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < borrowedList.size()-1; i++) {
            int iYear = borrowedList.get(i).getBorrowDate().get(Calendar.YEAR);
            int iMonth = borrowedList.get(i).getBorrowDate().get(Calendar.MONTH);
            int iDay = borrowedList.get(i).getBorrowDate().get(Calendar.DAY_OF_MONTH);

            if((year == iYear) && (month == iMonth) && (day == iDay)) {
                temp.add(borrowedList.get(i));
            }
        }
        return temp;
    }

    /**
     *
     * @param date to check
     * @return list of friends with loans at given date
     */
    public ArrayList<Friend> getFriendsWithBorrowedTapes (GregorianCalendar date) {
        ArrayList<Friend> friendsWithBorrowedTapes = new ArrayList<Friend>();

        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < borrowedList.size(); i++) {
            int iYear = borrowedList.get(i).getBorrowDate().get(Calendar.YEAR);
            int iMonth = borrowedList.get(i).getBorrowDate().get(Calendar.MONTH);
            int iDay = borrowedList.get(i).getBorrowDate().get(Calendar.DAY_OF_MONTH);

            if((iYear == year) && (iMonth == month) && (iDay == day)) {
                int friendId = borrowedList.get(i).getFriendId();
                Friend temp = friends.get(friendId);
                friendsWithBorrowedTapes.add(temp);
            }

        }
        return friendsWithBorrowedTapes;
    }

    /**
     * Takes in friendid and makes sure each friend
     * can only see available videos they have not
     * seen before
     */
    public ArrayList<Video> getAvailableVideos(int friendId) {

        ArrayList<Video> borrowed = getBorrowedVideos();
        ArrayList<Video> all = vService.getVideoList();
        ArrayList<Video> seenFilms = new ArrayList<Video>();
        Friend tempF = fService.getFriendById(friendId);

        ArrayList<Integer> seenFilmsId = tempF.getRentedFilms();
        if(seenFilmsId.size() != 0) {
            for(int i = 0; i < seenFilmsId.size(); i++) {
                Video x = vService.getVideo(seenFilmsId.get(i));
                seenFilms.add(x);
            }
        }

        ArrayList<Video> temp = new ArrayList<Video>();
        temp.addAll(all);
        temp.removeAll(borrowed);
        temp.removeAll(seenFilms);
        return temp;
    }

    /**
     *
     * @return list of all monthOldTapes
     * By todays date
     */
    public ArrayList<Borrowed> monthOldLoans() {

        Calendar cal = new GregorianCalendar();
        int days = cal.get(Calendar.DAY_OF_YEAR);

        ArrayList<Borrowed> temp = new ArrayList<Borrowed>();

        for(int i = 0; i < borrowedList.size(); i++) {
            GregorianCalendar x = borrowedList.get(i).getBorrowDate();
            int daysBorrowed = x.get(Calendar.DAY_OF_YEAR);
            int daysReturned = -1;

            if(borrowedList.get(i).getReturnDate() != null) {
                daysReturned = x.get(Calendar.DAY_OF_YEAR);
            }

            if(((days - daysBorrowed) >= 30) && (daysReturned == -1)||(daysReturned <= days)) {
                temp.add(borrowedList.get(i));
            }
        }
        return temp;
    }

    /**
     *
     * @param cal is the date we want to compare
     * @return list of all friends that at given date
     * had movies that they had for a whole month
     */
    public ArrayList<Friend> friendsWithMonthOldVideos (GregorianCalendar cal) {
        ArrayList<Friend> temp = new ArrayList<Friend>();

        ArrayList<Borrowed> tempBorrowed = monthOldLoans();
        int daysOfYearThen = cal.get(Calendar.DAY_OF_YEAR);

        for(int i = 0; i < tempBorrowed.size(); i++) {
            int daysOfYearBorrowed = tempBorrowed.get(i).getBorrowDate().get(Calendar.DAY_OF_YEAR);
            int daysOfYearReturned = -1;
            if(tempBorrowed.get(i).getReturnDate() != null) {
                daysOfYearReturned = tempBorrowed.get(i).getReturnDate().get(Calendar.DAY_OF_YEAR);
            }
            if((daysOfYearThen - daysOfYearBorrowed >= 30) &&(daysOfYearReturned == -1)||(daysOfYearReturned <= daysOfYearThen) ){
                Friend x = fService.getFriendById(tempBorrowed.get(i).getFriendId());

                if(!temp.contains(x)){
                    temp.add(x);
                }
            }
        }
        return temp;
    }

    /**
     *
     * @param cal the date to compare to
     * @return list of all loans that at given date, friend had for whole month
     */
    public ArrayList<Borrowed> monthOldLoans (GregorianCalendar cal) {
        ArrayList<Borrowed> temp = new ArrayList<Borrowed>();

        ArrayList<Borrowed> tempBorrowed = monthOldLoans();
        // at calendar date who at that days had tapes
        int daysOfYearThen = cal.get(Calendar.DAY_OF_YEAR);

        for(int i = 0; i < tempBorrowed.size(); i++) {
            int daysOfYearBorrowed = tempBorrowed.get(i).getBorrowDate().get(Calendar.DAY_OF_YEAR);
            int daysOfYearReturned = -1;
            if(tempBorrowed.get(i).getReturnDate() != null) {
                daysOfYearReturned = tempBorrowed.get(i).getReturnDate().get(Calendar.DAY_OF_YEAR);
            }

            if((daysOfYearThen - daysOfYearBorrowed >= 30) &&(daysOfYearReturned == -1)||(daysOfYearReturned <= daysOfYearThen) ){
                temp.add(tempBorrowed.get(i));
            }
        }
        return temp;
    }

    public int getMaxID() {
        int max = 0;
        if(borrowedList.isEmpty()){
            return max;
        }
        for(int i = 0; i < borrowedList.size(); i++) {
            if(borrowedList.get(i).getID() > max) {
                max = borrowedList.get(i).getID();
            }
        }
        return max;
    }

}
